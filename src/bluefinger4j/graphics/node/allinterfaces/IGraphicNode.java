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
package bluefinger4j.graphics.node.allinterfaces;

import bluefinger4j.graphics.element.allinterfaces.IGraphicElement;
import java.util.Collection;
import java.util.Iterator;
import org.luaj.vm2.LuaValue;

/**
 * Representa um node gráfico
 * @author renan_user
 */
public interface IGraphicNode extends Iterator<IGraphicNode> {
    
    
    /**
     * Configura o elemento que será usado nesse node
     * @param element elemento que será usado nesse node
     */
    public void setElement(IGraphicElement element);
    
    /**
     * Obtem o elemento que será usado nesse node
     * @return elemento que será usado nesse node
     */
    public IGraphicElement getElement();

    /**
     * Adiciona um node como filho desse IGraphicNode
     * @param node node a ser adicionado
     * @return true em caso de sucesso e false caso contrário
     */
    public boolean addNode(IGraphicNode node);

    /**
     * Remove um node como filho desse IGraphicNode
     * @param node node a ser removido
     * @return true em caso de sucesso e false caso contrário
     */
    public boolean removeNode(IGraphicNode node);

    /**
     * Adicionar uma coleção de nodes filhos desse IGraphicNode
     * @param nodes nodes a serem adicionados
     * @return true em caso de sucesso e false caso contrário
     */
    public boolean addNodes(Collection<IGraphicNode> nodes);

    /**
     * remover uma coleção de nodes filhos desse IGraphicNode
     * @param nodes nodes a serem removidos
     * @return true em caso de sucesso e false caso contrário
     */
    public boolean removeNodes(Collection<IGraphicNode> nodes);
    
    /**
     * Obtem um node filho através do seu indice
     * @param index indice que será localizado
     * @return o node da posição especificada 
     * @throws IndexOutOfBoundsException exeção disparada caso o indice seja inválido
     */
    public IGraphicNode getNode(int index) throws IndexOutOfBoundsException;
    
      /**
     * Obtem um array de node filho através do seu indice
     * @param indexArray indices que seram localizado
     * @return Array com os nodes das posições especificada 
     * @throws IndexOutOfBoundsException exeção disparada caso algum dos indices sejam inválido
     */
    public IGraphicNode[] getNodes(int[] indexArray) throws IndexOutOfBoundsException;
    
    /**
     * Obtem a quantidade de nodes filhos desse node
     * @return quantidade de filhos
     */
    public int getNodeCount();
    
        /**
     * Cria uma representação compativel com scripts lua dessa instancia
     * @return  LuaValue que faz referencia a esse objeto
     */
    public LuaValue toLuaValue();
}
