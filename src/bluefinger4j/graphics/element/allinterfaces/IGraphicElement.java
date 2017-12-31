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
package bluefinger4j.graphics.element.allinterfaces;

import bluefinger4j.graphics.geometry.allinterfaces.IGeometry;
import java.util.Arrays;
import java.util.Collection;
import org.luaj.vm2.LuaValue;

/**
 * Objeto que serve para representar um desenho na tela
 * @author renan_user
 */
public interface IGraphicElement {
    
    /**
     * Obtém o nome do IGraphicElement
     * @return nome do IGraphicElement.
     */
    public String getName();
    
    /**
     * Conecta uma camada ao elemento 
     * @param geometry
     */
    public void connect(IGeometry geometry);
    
    /**
     * Connecta um conjunto de geometrias
     * @param geometries geometrias a ser adicionadas
     */
    public void connect(Collection<IGeometry> geometries);
 
    /**
     *Connecta um array de geometrias 
     * @param geometries array de gemoetrias a serem adicionadas
     */
    public void connect(IGeometry[] geometries);
    
    /**
     * desconecta uma geometria do elemento
     * @param geometry geometria a ser excluida
     */
    public void disconnect(IGeometry geometry);

    /**
     * Desconecta uma coleção de gemoetrias
     * @param geometries  coleção de geometrias a ser excluida
     */
    public void disconnect(Collection<IGeometry> geometries);
    
    /**
     * Desconecta um array de gemoetrias
     * @param geometries  array de geometrias a ser excluida
     */
    public void disconnect(IGeometry[] geometries);
    
    /**
     * Cria uma representação compativel com scripts lua dessa instancia
     * @return  LuaValue que faz referencia a esse objeto
     */
    public LuaValue toLuaValue();
}
