package bluefinger4j.point.origin.allinterfaces;

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


import bluefinger4j.point.allinterfaces.IPoint;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author renan
 */
public interface IPointOrigin extends IPoint{

    /**
     * Adiciona o nó neste PointGroup
     * @param node node a ser adicionado
     * @return retorna true em caso de sucesso e false em caso contrario
     */
    boolean addNode(IPoint node);

    /**
     * Adiciona uma coleção de nó nesse PointGroup
     * @param nodeCollection nodes a serem adicionados a esse PointGroup
     * @return retorna true em caso de sucesso e false em caso contrario
     */
    boolean addNodes(Collection<IPoint> nodeCollection);

    /**
     * Obtem todos os elementos desse grupo
     * @return  todos os elementos desse grupo
     */
    List<IPoint> all();

    /**
     * Verifica se existe um ponto na posição determinada pelos argumentos x , y , z.
     * O ponto a ser testado deve esta diretamente vinculado a este PointGroup
     * @param x posição no eixo x que se deseja testar
     * @param y posição no eixo y que se deseja testar
     * @param z posição no eixo z que se deseja testar
     * @return retrona true em caso existir e false caso contrário
     */
    boolean existsInPosition(double x, double y, double z);

    /**
     * Remove o nó assoicados a esse PointGroup
     * @param node node a ser removido desse PointGroup
     * @return retorna true em caso de sucesso e false em caso contrario
     */
    boolean removeNode(IPoint node);

    /**
     * Obtém a quantidade de pontos contidos nessa instancia, ou seja, a quantidade de ponto que podem ser
     * retornados pelos métodos getPoint(IPoint) e getPoints(IPoint)
     * @return quantidade de pontos existentes nessa instancia
     */
    int nodeCount();

    /**
     * Obtem um ponto atraves do inidice passado no argumento index
     * @param index indice quie se deseja recuperar
     * @return O ponto que tem como o argumento passada pelo indice
     */
    IPoint getNode(int index);

    /**
     * Obtem um array de ponto atraves de um array de indice a posição do ponto é dado na
     * ordem que aparece no array passado atraves do argumento indexArray
     * @param indexArray array de indices que se deseja retornar
     * @return array de pontos que tem os seus respectivos indices passado no argumento idexArray
     */
    IPoint[] getNodes(int[] indexArray);
    
    
    /**
     * Apenda um node e retrona o node que foi apendado
     * @param node node que será apendado
     * @return  retrona o node passado no argumento 
     */
    IPoint appendNode(IPoint node);
    
    
      /**
     * Apenda os nodes passado no argumento e os retornas
     * @param nodes nodes a serem apendados
     * @return retorna os nodes apendados
     */
    IPoint[] appendNodes(IPoint[] nodes) ;
        
  
    
}
