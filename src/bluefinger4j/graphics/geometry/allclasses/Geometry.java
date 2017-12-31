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
package bluefinger4j.graphics.geometry.allclasses;

import bluefinger4j.point.origin.allinterfaces.IPointOrigin;
import bluefinger4j.point.origin.allclasses.PointOrigin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import bluefinger4j.graphics.geometry.allinterfaces.IGeometry;
import java.util.Arrays;

/**
 * Representa uma geometria
 *
 * @author Renan
 */
public class Geometry implements IGeometry {

    private String name;
    private List<IPointOrigin> nodes;

    /**
     * Cria uma geometria passando para mesma o seu nome
     *
     * @param name nome dado a essa geometria
     */
    public Geometry(String name) {
        this.nodes = new ArrayList<IPointOrigin>();
        this.name = name;
    }

    /**
     * Cria uma geometria passando um nome e o nodes que a compoem
     *
     * @param name nome da geometria
     * @param nodes nós que a compoem
     */
    public Geometry(String name, Collection<IPointOrigin> nodes) {
        this(name);
        this.nodes.addAll(nodes);
    }

    /**
     * Adiciona um nó a geometria
     *
     * @param node no a ser adicinado
     */
    @Override
    public void addNode(IPointOrigin node) {
        this.nodes.add(node);
    }

    /**
     * Remove um nó da geometria
     *
     * @param node nó a ser removido
     */
    @Override
    public void removeNode(IPointOrigin node) {
        this.nodes.remove(node);
    }

    /**
     * Adiciona uma coleção de nós na geometria
     *
     * @param nodes nós a serem adicionados
     */
    @Override
    public void addNodes(Collection<IPointOrigin> nodes) {
        this.nodes.addAll(nodes);
    }
    
    /**
     * Adiciona um array de nodes na geometria
     * @param nodes nodes a serem adicionados
     */
    @Override
    public void addNodes(IPointOrigin[] nodes) {
        this.nodes.addAll(Arrays.asList(nodes));
    }

    /**
     * Remove uma coleção de nós da geometria
     *
     * @param nodes nós a serem removídos
     */
    @Override
    public void removeNodes(Collection<IPointOrigin> nodes) {
        this.nodes.removeAll(nodes);
    }
    
       /**
     * Remove um array de nós da geometria
     *
     * @param nodes nós a serem removídos
     */
    @Override
    public void removeNodes(IPointOrigin[] nodes) {
        this.nodes.removeAll(Arrays.asList(nodes));
    }

    /**
     * Obtem um nó atraves do seu indice
     *
     * @param index indice do nó a ser retornado
     * @return nó retornado atraves do seu indice
     */
    @Override
    public IPointOrigin getNode(int index) {
        return this.nodes.get(index);
    }

    /**
     * Obtem uma coleção de nós usando o seu indice para determinar a posição de
     * inicio dos items retornados e o valor de count para a quantidade de item
     * que serão retornados
     *
     * @param index indice que da a posição inicial dos itens retornados
     * @param count quantidade de itens retornados
     * @return retorna uma lista contendo os itens retornados
     */
    @Override
    public Collection<IPointOrigin> getNodes(int index, int count) {
        return this.nodes.subList(index, index + count);
    }

    /**
     * Obtem a quantidade de nós existentes na geometria
     *
     * @return inteiro contendo a quantidade de nós.
     */
    @Override
    public int getNodeCount() { 
        return this.nodes.size();
    }

    /** 
     * Retorna uma representação wavefront da geometria
     * @return represetação wavefront da geometria
     */
    @Override
    public String toWavefront() {
        StringBuilder script = new StringBuilder();
        StringBuilder vertexScript = new StringBuilder();
        StringBuilder faceScript = new StringBuilder();
        int vertexIndex = 1;
        
        script.append("o ").append(name).append("\n");
        
        for (int i = 0; i < this.getNodeCount(); i++) {
            if (this.getNode(i) instanceof PointOrigin) {
                PointOrigin origin = (PointOrigin) this.getNode(i);
                int count = origin.nodeCount() / 2;
                for (int index = 0; index < count; i += 2) {
                    vertexScript.append(origin.toWaveFront());
                    vertexScript.append(origin.getNode(index).toWaveFront());
                    if (origin.getNode(index + 1) == null) {
                        this.addNode((IPointOrigin) origin.getNode(index));
                    }
                    vertexScript.append(origin.getNode(index + 1).toWaveFront());
                    faceScript.append("f ")
                            .append(vertexIndex)
                            .append(vertexIndex + 1)
                            .append(vertexIndex + 2)
                            .append("\n");
                    vertexIndex += 3;
                }
            }
        }
        
        script.append(vertexScript.toString());
        script.append(faceScript.toString());
        return script.toString();
    }

}
