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
package bluefinger4j.graphics.engine.allinterfaces;

import bluefinger4j.graphics.engine.allclasses.GraphicEngine;
import bluefinger4j.graphics.node.allinterfaces.IGraphicNode;
import bluefinger4j.pixel.allinterfaces.BitmapAdapter;
import java.util.Map;

/**
 *
 * @author Renan
 */
public interface IGraphicEngine {

    /**
     * Driver grafico da Bluefinger4j essa é a ponte entre as classes do
     * Bluefinger4j e os componentes externos.
     */
    public interface GraphicCompile {

        /**
         * Codifica os dados que estão nos nodes do Bluefinger4J para os
         * componentes externos.
         *
         * @param context o GraphicEngine que esse driver esta associado
         * @param node node que será tratado pelo driver
         * @param textures hash contendo o nome da textura como chave e a
         * textura como valor. Nao é obrigatorio o uso de texturas.
         */
        public void codify(GraphicEngine context, IGraphicNode node, Map<String, BitmapAdapter> textures);
    }

    /**
     * Dispara o driver para o mesmo processar os nodes
     *
     * @param compile Objeto que serve para compilar os GraphicNode em uma saida
     * correspondente a desejada.
     * @return retorna this.
     */
    GraphicEngine compile(GraphicCompile compile);

    /**
     * Registra uma textura
     *
     * @param name nome da textura
     * @param texture o BitmapAdapter que servira da textura
     * @return retorna this
     */
    GraphicEngine register(String name, BitmapAdapter texture);

    /**
     * Deregistra uma textura
     *
     * @param name nome da textura a ser removida
     * @return retorna this
     */
    GraphicEngine unregister(String name);

}
