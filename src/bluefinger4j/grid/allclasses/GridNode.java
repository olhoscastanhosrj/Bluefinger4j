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
package bluefinger4j.grid.allclasses;

import bluefinger4j.grid.allinterfaces.IGridNode;
import bluefinger4j.grid.allinterfaces.GridComposite;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * GridNode representa um node da composição o grid node foi desenvolvido de forma a ter 
 * somente a tarefa de ser uma infraestrutura para os GridElement dentro de uma composição
 * @author renan
 */
public class GridNode implements GridComposite, Serializable, IGridNode {

    private static final long serialVersionUID = 7678034351581702319L;
    private List<GridComposite> composite;
    private GridElement element;

    /**
     * Cria o GridNode passando para o mésmo o elemento que será encapçulado
     * @param element elemento que sera emcapçulado
     * @throws IllegalArgumentException exceção disparada caso o argumento element passado for igual a null (element==null)
     */
    public GridNode(GridElement element) throws IllegalArgumentException {
        this.composite = new ArrayList<GridComposite>();
        if (element != null) {
            this.element = element;
        } else {
            throw new IllegalArgumentException("element is null");
        }
    }
    
    /**
     * Cria um GridNode passando para o mesmo um GridNode que servira de modelo passado no parametro source. 
     * Nesse construtor você pode tambem definir se após a construção dessa instancia só ela possuira os filhos de source
     * bastando para isso setar o argumento exclusiveNode para true. Essa configuração é util caso seja necessário
     * mudar o GridNode na arvore de componentes do Grid.
     * @param source GridNode que servira de fonte para extração de dados para a configuração dessa instancia
     * @param exclusiveNode parametro que indica se os GridNodes filhos seram esclusivos dessa classe que esta sendo criado, caso seja true os GridNode's 
     * contidos no GridNode passado no argumento source seram copiados e removidos do mesmo.
     * @throws IllegalArgumentException exceção disparada caso o argumento element passado for igual a null (element==null)
     */
    public GridNode(GridNode source , boolean exclusiveNode) throws IllegalArgumentException{
        this(source.getElement());

        for (int i = 0 ; i < source.nodeCount() ; i++){
            this.composite.add(source.getNode(i));
            if (exclusiveNode){
                source.removeNode(this.composite.get(this.composite.size() - 1));
            }
        }
        
        
    }
    /**
     * Obtem o elemento encapçulado nessa instância de GridNode
     * @return elemento encapçulado nessa instância 
     */
    @Override
    public GridElement getElement() {
        return element;
    }

    @Override
    public void addNode(GridComposite node) {
        this.composite.add(node);
    }

    @Override
    public void removeNode(GridComposite node) {
        this.composite.remove(node);
    }

    @Override
    public GridComposite getNode(int index) throws IndexOutOfBoundsException {
        return this.composite.get(index);
    }

    @Override
    public GridComposite[] getNodes(int[] indexArray) throws IndexOutOfBoundsException {
        GridComposite[] cArray = new GridComposite[indexArray.length];
        for (int i = 0; i < indexArray.length; i++) {
            cArray[i] = this.getNode(indexArray[i]);
        }
        return cArray;
    }

    @Override
    public GridComposite firstNode() {
        if (this.composite.isEmpty()) {
            return null;
        }
        return this.getNode(0);
    }

    @Override
    public GridComposite lastNode() {
        if (this.composite.isEmpty()) {
            return null;
        }
        return this.getNode(this.nodeCount() - 1);
    }

    @Override
    public int nodeCount() {
        return this.composite.size();
    }
}
