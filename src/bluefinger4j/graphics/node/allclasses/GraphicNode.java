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
package bluefinger4j.graphics.node.allclasses;

import bluefinger4j.graphics.element.allinterfaces.IGraphicElement;
import bluefinger4j.graphics.node.allinterfaces.IGraphicNode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;

/**
 * Implementação basica de GraphicNode
 * @author renan_user
 */
public class GraphicNode implements IGraphicNode{
    
    private IGraphicElement element;
    private List<IGraphicNode> nodes;

    /**
     * Cria o GraphicNode passando o elemento que será encapçulado pelo mesmo
     * @param element elemento que será encapculado por esse node
     */
    public GraphicNode(IGraphicElement element) {
        this.element = element;
        this.nodes = new ArrayList<IGraphicNode>();
    }
      
    @Override
    public IGraphicElement getElement() {
        return element;
    }

    @Override
    public void setElement(IGraphicElement element) {
        this.element = element;
    }

  
    @Override
    public boolean addNode(IGraphicNode node) {
         return  this.nodes.add(node);
    }

    @Override
    public boolean removeNode(IGraphicNode node) {
        return this.nodes.remove(node);
    }

    @Override
    public boolean addNodes(Collection<IGraphicNode> nodes) {
       return  this.nodes.addAll(nodes);
    }

    @Override
    public boolean removeNodes(Collection<IGraphicNode> nodes) {
              return  this.nodes.removeAll(nodes);
    }

    @Override
    public IGraphicNode getNode(int index) throws IndexOutOfBoundsException {
        return this.nodes.get(index);
    }

    @Override
    public IGraphicNode[] getNodes(int[] indexArray) throws IndexOutOfBoundsException {
        IGraphicNode[] nodeArray = new IGraphicNode[indexArray.length];
        
        for (int i = 0 ; i < nodeArray.length ; i++){
            nodeArray[i] = this.getNode(i);
        }
        
        return nodeArray;
    }

    @Override
    public int getNodeCount() {
       return this.nodes.size();
    }

    @Override
    public boolean hasNext() {
        return this.nodes.iterator().hasNext();
    }

    @Override
    public IGraphicNode next() {
        return this.nodes.iterator().next();
    }

    @Override
    public void remove() {
       this.nodes.iterator().remove();
    }
   
    @Override
    public LuaValue toLuaValue() {
        return CoerceJavaToLua.coerce(this);
    }    
}
