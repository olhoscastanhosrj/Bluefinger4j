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
package bluefinger4j.grid.allinterfaces;

import bluefinger4j.grid.allclasses.GridElement;

/**
 * Implementação do padrão composite para os objetos que pertencem ao grid e precisam ser tratados como componente
 * @author renan
 */
public interface GridComposite {
    /**
     * Obtem o elemento contido nesse composite 
     * @return o elemento que esta contido nesse composite ou null caso o composite esteja vazio
     */
    public GridElement getElement();
    
    /**
     * Adiciona um node para a composição
     * @param node node a ser adicionado a composição
     */
    public void addNode ( GridComposite node );
    
    /**
     * Remove um node da composição caso o mesmo exista nessa composição
     * @param node node a ser removido da composição
     */
    public void removeNode(GridComposite node);
    
    /**
     * Obtém um node dessa composição atraves de seu indice
     * @param index indice do node que se deseja obter
     * @return retorna o node que se tem o indice igual ao valor passado no argumento index
     * @throws IndexOutOfBoundsException exeção disparada caso o index não esteja dentro dos limites validos
     */
    public GridComposite getNode(int index) throws  IndexOutOfBoundsException;
    
    /**
     * Obtem um array de nodes dessa composição atraves de seu indice
     * @param indexArray array contendo os indices que se deseja retornar
     * @return retorna um array de nodes contendo os elementos extraidos atraves do seus repectivos indices passados no argumento
     * @throws IndexOutOfBoundsException exeção disparada caso os indexs passados não estejam dentro dos limites validos
     */
    public GridComposite[] getNodes(int[] indexArray) throws IndexOutOfBoundsException;
    
    /**
     * Obtem o primeiro node dessa composiçao
     * @return retorna o primeiro node da composição ou null caso a composição estiver vazia
     */
    public GridComposite firstNode() ;
    
    /**
     * Obtem o ultimo node dessa composição
     * @return retorna o ultimo node dessa composição ou null caso a composição estiver vazia
     */
    public GridComposite lastNode();
    
    /**
     * Obtem a quantidade de nodes contidas nessa composição
     * @return quantidade de nodes contidas nessa composição
     */
    public int nodeCount();
    
    
    
}
