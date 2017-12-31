/*
 *Copyright (c) 2014, Renan T. dos Santos
 *All rights reserved.
 *
 *Redistribution and use in source and binary forms, with or without
 *modification, are permitted provided that the following conditions are met:
 *
 *
 * Redistributions of source code must retain the above copyright notice, this
 *  list of conditions and the following disclaimer.
 *
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 *
 *THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 *AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 *IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 *FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 *DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 *CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 *OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 *OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package bluefinger4j.lua.script.allclasses;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;
import java.util.Iterator;
import java.util.Map;
import bluefinger4j.lua.script.allinterfaces.IScript;

/**
 * Recipiente que serve de interface entre o script Lua e um objeto Java. O
 * objeto java será consumido pelo método de nome informado no método submit(methodName,javaObj)
 * sendo os argumentos do método submit respectivamente o nome do metodo no lua que recebera o 
 * objeto java e o objeto java que sera lido pelo método no lua.
 *
 * @author Renan
 * @param <E> classe do elemento que será esposto nessa istância
 */
public class Script<E> implements Runnable, IScript<E> {

    private Globals globals;
    private final String name;
    private final CharSequence script;
    private Map<String, E> elements;

    /**
     * Inicia a instancia com o seu respectivo nome e o script que será lido
     * nesse objeto.
     *
     * @param name nome dado a instância
     * @param script script que será executado no contexto dado por essa
     * instância
     */
    public Script(String name, CharSequence script) {
        this.name = name;
        this.script = script;
    }

    /**
     * Inicia a instancia passanod o seu respectivo nomo e script encapçulado em
     * um BufferedReader. Quando usado esse construtor também se deve informar o
     * tamanho do buffer no length
     *
     * @param name nome dado a essa instancia
     * @param script buffer que contem o script
     * @param length tamanho em caracteres do buffer necessário para a leitura
     * do script
     * @throws IOException caso ocorra um exelçao de IO essa exeção é disparada.
     */
    public Script(String name, BufferedReader script, int length) throws IOException {
        char[] buff = new char[(int) length + 1];
        script.read(buff);
        this.name = name;
        this.script = new String(buff).trim();
    }

    /**
     * Inicia a instancia com seu respectivo nome e o script que sera
     * devidamente extraido do arquivo passado como argumento script
     *
     * @param name nome dado a essa intância
     * @param script arquivo onde contem o script a ser executado no contexto
     * dessa instancia.
     * @throws FileNotFoundException essa exeção ocorre caso o arquivo não
     * exista.
     * @throws IOException essa exeção é chamada caso ocorra qualquer erro no
     * arquivo.
     */
    public Script(String name, File script) throws FileNotFoundException, IOException {
        this(name, new BufferedReader(new FileReader(script)), (int) script.length());
    }


    /**
     * Objeto que será emviado para a função que tem o nome passado no argumento methodName.
     * o método deve ter na sua assinatura um argumento que sera o objeto java passado no parametro
     * element.
     *
     * @param methodName nome do método que sera chamado no Lua e tratara o item passado no argumento element.
     * @param element elemento a ser enviado para a função setup do método lua
     * @return retorna this
     */
    @Override
    public Script submit(String methodName, E element) {
        if (this.elements == null) {
            this.elements = new HashMap<String, E>();
        }
        this.elements.put(methodName, element);
        return this;
    }

    @Override
    public void run() {
        this.globals = JsePlatform.standardGlobals();
        LuaValue chunk = this.globals.load(script.toString());
        chunk.call();
        
        if (this.elements != null) {
            Iterator<Map.Entry<String, E>> it = this.elements.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, E> e = it.next();
                String methodName      = e.getKey();
                LuaValue value         = CoerceJavaToLua.coerce(e.getValue());
                System.out.println(this.globals.get(methodName).call(value).toString());
            }
        }
    }

    /**
     * Cria uma thread para ser utilizada por essa classe. A função desse método
     * é facilitar e dar mais clareza ao código. É o euivalente a fazer new
     * Thread(luaHandleObj) .
     *
     * @return thread que esta preparada para executar as açoes do método run()
     * dessa instância.
     */
    @Override
    public Thread thread() {
        return new Thread(this);
    }

}
