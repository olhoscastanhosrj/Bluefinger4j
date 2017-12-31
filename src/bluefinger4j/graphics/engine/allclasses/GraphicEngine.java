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
package bluefinger4j.graphics.engine.allclasses;

import bluefinger4j.graphics.engine.allinterfaces.IGraphicEngine;
import bluefinger4j.graphics.node.allclasses.GraphicNode;
import bluefinger4j.pixel.allinterfaces.BitmapAdapter;
import java.util.HashMap;
import java.util.Map;

/**
 * Motor grafico, serve de meio de comunicaçao entre as classes do Bluefinger4j
 * e o componentes graficos que vão renderizar as imagens. 
 * 
 * @author Renan
 */
public class GraphicEngine implements IGraphicEngine {

       
    private final GraphicNode root;
    private Map<String,BitmapAdapter> textures;
    
    /**
     * Cria o GraphicEngine com o node que sera a raiz dos nodes processados
     * @param root node raiz a ser processado nessa instância
     */
    public GraphicEngine(GraphicNode root) {
        this.root = root;
    }
    
    /**
     * Registra uma textura
     * @param name nome da textura
     * @param texture o BitmapAdapter que servira da textura
     * @return retorna this
     */
    @Override
    public GraphicEngine register(String name , BitmapAdapter texture){
        if(this.textures == null) this.textures = new HashMap<String, BitmapAdapter>() ;
        this.textures.put(name, texture);
        return this;
    }
    
    /**
     * Deregistra uma textura
     * @param name nome da textura a ser removida 
     * @return retorna this
     */
    @Override
    public GraphicEngine unregister(String name){
        if(this.textures != null) this.textures.remove(name);
        return this;
    }
    
    /**
     * Dispara o driver para o mesmo processar os nodes
     * @param driver Objeto que serve de driver, ou seja, faz algo como a saida do Bluefinger4j
     * @return retorna this.
     */
    @Override
    public GraphicEngine compile(IGraphicEngine.GraphicDriver driver){
        for(int i = 0 ; i < this.root.getNodeCount(); i++){
           driver.encode(this, this.root.getNode(i), textures);
        }
         driver.encode(this, root, textures);
        return this;
    }
    
}
