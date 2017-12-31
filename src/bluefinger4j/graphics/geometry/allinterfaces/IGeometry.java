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
package bluefinger4j.graphics.geometry.allinterfaces;


import bluefinger4j.point.origin.allinterfaces.IPointOrigin;
import java.util.Collection;

/**
 *
 * @author Renan
 */
public interface IGeometry {

    /**
     * Adiciona um nó a geometria
     *
     * @param node no a ser adicinado
     */
    void addNode(IPointOrigin node);

    /**
     * Adiciona uma coleção de nós na geometria
     *
     * @param nodes nós a serem adicionados
     */
    void addNodes(Collection<IPointOrigin> nodes);

    /**
     * Adiciona um array de nodes na geometria
     *
     * @param nodes nodes a serem adicionados
     */
    public void addNodes(IPointOrigin[] nodes);

    /**
     * Obtem um nó atraves do seu indice
     *
     * @param index indice do nó a ser retornado
     * @return nó retornado atraves do seu indice
     */
    IPointOrigin getNode(int index);

    /**
     * Obtem a quantidade de nós existentes na geometria
     *
     * @return inteiro contendo a quantidade de nós.
     */
    int getNodeCount();

    /**
     * Obtem uma coleção de nós usando o seu indice para determinar a posição de
     * inicio dos items retornados e o valor de count para a quantidade de item
     * que serão retornados
     *
     * @param index indice que da a posição inicial dos itens retornados
     * @param count quantidade de itens retornados
     * @return retorna uma lista contendo os itens retornados
     */
    Collection<IPointOrigin> getNodes(int index, int count);

    /**
     * Remove um nó da geometria
     *
     * @param node nó a ser removido
     */
    void removeNode(IPointOrigin node);

    /**
     * Remove uma coleção de nós da geometria
     *
     * @param nodes nós a serem removídos
     */
    void removeNodes(Collection<IPointOrigin> nodes);

    /**
     * Remove um array de nós da geometria
     *
     * @param nodes nós a serem removídos
     */
    public void removeNodes(IPointOrigin[] nodes);

    /**
     * Retorna uma representação wavefront da geometria
     *
     * @return represetação wavefront da geometria
     */
    String toWavefront();

}
