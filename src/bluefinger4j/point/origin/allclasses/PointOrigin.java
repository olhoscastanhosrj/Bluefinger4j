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
package bluefinger4j.point.origin.allclasses;

import bluefinger4j.point.allclasses.Point;
import java.util.Collection;
import java.util.List;
import bluefinger4j.point.allinterfaces.IPoint;
import java.util.ArrayList;
import bluefinger4j.point.origin.allinterfaces.IPointOrigin;

/**
 * O PointOrigin é o ponto que serve de origem e contem um conjunto de pontos que serve para 
 * que a partir desse ponto se forme os tringulos que forma a geometria
 * @author renan
 */
public class PointOrigin extends Point implements IPointOrigin {

    private static final long serialVersionUID = 3480689220314400700L;
    List<IPoint> points;

    /**
     * Constroi um PointGroup passando a lista de pontos que serão trabalhados com
     * a instância que esta sendo criada
     * @param points Lista de pontos que serão tratadas nessa instância
     * @param x valor para o eixo x desse PointGroup
     * @param y valor para o eixo y desse PointGroup
     * @param z valor para o eixo z desse PointGroup
     */
    public PointOrigin(List<IPoint> points, double x, double y, double z) {
        super(x, y, z);
        this.points = points;
    }


    /**
     * Cria uma instância de PointGroup usando o IPoint passado em pt como modelo 
     * @param pt IPoint que será usado como modelo
     */
    public PointOrigin(IPoint pt) {
        super(pt);
        this.points = new ArrayList<IPoint>();
    }

  
    /**
     * Obtem todos os elementos desse grupo
     * @return  todos os elementos desse grupo
     */
    @Override
    public List<IPoint> all() {
        return this.points;
    }

    /**
     * Adiciona o nó neste PointOrigin
     * @param node node a ser adicionado
     * @return retorna true em caso de sucesso e false em caso contrario
     */
    @Override
    public boolean addNode(IPoint node) {
        return this.points.add(node);
    }

    /**
     * Adiciona uma coleção de nó nesse PointOrigin
     * @param nodeCollection nodes a serem adicionados a esse PointOrigin
     * @return retorna true em caso de sucesso e false em caso contrario
     */
    @Override
    public boolean addNodes(Collection<IPoint> nodeCollection) {
        boolean result = this.points.addAll(nodeCollection);

        return result;
    }

    /**
     * Remove o nó assoicados a esse PointOrigin
     * @param node node a ser removido desse PointOrigin
     * @return retorna true em caso de sucesso e false em caso contrario
     */
    @Override
    public boolean removeNode(IPoint node) {
        boolean result = this.points.remove(node);
        return result;
    }

    /**
     * Obtem um ponto atraves do inidice passado no argumento index
     * @param index indice quie se deseja recuperar
     * @return O ponto que tem como o argumento passada pelo indice  
     */
    @Override
    public IPoint getNode(int index) {
        return this.points.get(index);
    }

    /**
     * Obtem um array de ponto atraves de um array de indice a posição do ponto é dado na 
     * ordem que aparece no array passado atraves do argumento indexArray
     * @param indexArray array de indices que se deseja retornar
     * @return array de pontos que tem os seus respectivos indices passado no argumento idexArray
     */
    @Override
    public IPoint[] getNodes(int[] indexArray) {
        IPoint[] results = new IPoint[indexArray.length];

        for (int i = 0; i < results.length; i++) {
            results[i] = this.getNode(indexArray[i]);
        }

        return results;
    }

    /**
     * Obtém a quantidade de pontos contidos nessa instancia, ou seja, a quantidade de ponto que podem ser
     * retornados pelos métodos getPoint(IPoint) e getPoints(IPoint)
     * @return quantidade de pontos existentes nessa instancia
     */
    @Override
    public int nodeCount() {
        return this.points.size();
    }

    /**
     * Apenda o node passado no argumento e o retorna
     * @param node node a ser apendado
     * @return retorna o node adicionado
     */
    @Override
    public IPoint appendNode(IPoint node) {
        this.addNode(node);
        return node;
    }

    /**
     * Apenda os nodes passado no argumento e os retornas
     * @param nodes nodes a serem apendados
     * @return retorna os nodes apendados
     */
    @Override
    public IPoint[] appendNodes(IPoint[] nodes) {

        for (int i = 0; i < nodes.length; i++) {
            this.addNode(nodes[i]);
        }

        return nodes;
    }

    /**
     * Verifica se existe um ponto na posição determinada pelos argumentos x , y , z. 
     * O ponto a ser testado deve esta diretamente vinculado a este PointOrigin
     * @param x posição no eixo x que se deseja testar
     * @param y posição no eixo y que se deseja testar
     * @param z posição no eixo z que se deseja testar
     * @return retrona true em caso existir e false caso contrário
     */
    @Override
    public boolean existsInPosition(double x, double y, double z) {
        Boolean exists = false;

        for (int i = 0; i < this.points.size(); i++) {
            if (this.points.get(i).getX() == x
                    && this.points.get(i).getY() == y
                    && this.points.get(i).getZ() == z) {
                return true;
            }
        }

        return false;
    }

  
}
