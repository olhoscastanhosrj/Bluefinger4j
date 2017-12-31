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
package bluefinger4j.lua.script.allinterfaces;

import bluefinger4j.lua.script.allclasses.Script;

/**
 * Interface que representa uma ponte entre os objetos java e os métodos em lua.
 * @author Renan
 * @param <E> Tipo de elemento que deve ser encapçulado por esse método
 */
public interface IScript<E> {

    /**
     * Objeto que será enviado para a função que tem o nome passado no argumento methodName.
     * o método deve ter na sua assinatura um argumento que sera o objeto java passado no parametro
     * element.
     *
     * @param methodName nome do método que sera chamado no Lua e tratara o item passado no argumento element.
     * @param element elemento a ser enviado para a função setup do método lua
     * @return retorna this
     */
    Script submit(String methodName, E element);

    /**
     * Cria uma thread para ser utilizada por essa classe. A função desse método
     * é facilitar e dar mais clareza ao código. É o euivalente a fazer new
     * Thread(luaHandleObj) .
     *
     * @return thread que esta preparada para executar as açoes do método run()
     * dessa instância.
     */
    Thread thread();
    
}
