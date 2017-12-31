/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
