/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bluefinger4j.grid.allinterfaces;

import bluefinger4j.grid.allclasses.GridElement;

/**
 * Interface que implementa um node de um grid 
 * @author renan_user
 */
public interface IGridNode {

    /** 
     * adiciona um node ao no corrente
     * @param node node a ser adicionado
     */
    void addNode (GridComposite node);

    /**
     * Obtém o primeiro node associado a esse node
     * @return primeiro node que esta na lista dos adicionados a esse node
     */
    GridComposite firstNode ();

    /**
     * Obtem o elemento encapçulado nessa instância de GridNode
     * @return elemento encapçulado nessa instância
     */
    GridElement getElement ();

    /**
     * Obtém um node atravez do seu indice
     * @param index indice do node que será obtido
     * @return o node do indice correspondente
     * @throws IndexOutOfBoundsException caso o inidice não exista dispara essa exceção
     */
    GridComposite getNode (int index) throws IndexOutOfBoundsException;
   /**
     * Obtém varios nodes atravez do seu indice
     * @param indexArray array contendo os indices dos nodes que serão obtido
     * @return os nodes como os indices correspondentes
     * @throws IndexOutOfBoundsException caso um dos inidices não existam dispara essa exceção
     */
    GridComposite[] getNodes (int[] indexArray) throws IndexOutOfBoundsException;

    /**
     * Obtém o ultimo node associado a esse node
     * @return ultimo node que esta na lista dos adicionado a esse node
     */
    GridComposite lastNode ();

    /**
     * Quantidade de nodes associados a esse nó
     * @return inteiro que representa a quantidade de node contidos nessa instância
     */
    int nodeCount ();

    /**
     * Remove o node expecificado no argumento desse elemento 
     * @param node node a ser removido
     */
    void removeNode (GridComposite node);
}
