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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Agrupa um GridLocations 
 * @author renan
 */
public class GridMetrics implements List<GridLocation> {

    private List<GridLocation> locations;

    /**
     * Cria um GridMetrics vazio
     */
    protected GridMetrics () {
        this.locations = new ArrayList<GridLocation>();
    }

    /**
     * Cria um GridMetrics que ocupe a area determinada pelos GridLocation's start e exit e tenha a quantidade de
     * linhas e colunas dadas respectivamente por maxLine e maxColumn a escala é de 1.0.
     * @param start local de onde esse grid começa a ser calculado
     * @param exit loca onde esse grid deixa de ser calculado
     * @param maxLine numero máximo de linhas (deve ser diferente de zero)
     * @param maxColumn numero máximo de colunas (deve ser diferente de zero)
     */
    public GridMetrics (GridLocation start, GridLocation exit, int maxLine, int maxColumn) {
        super();
        int originX = start.getX();
        int originY = start.getY();
        int scaleX = (exit.getX() - start.getX()) / maxColumn;
        int scaleY = (exit.getY() - start.getY()) / maxLine;

        for (int column = 0; column < maxColumn; column++) {
            for (int line = 0; line < maxLine; line++) {
                this.locations.add(new GridLocation(originX, originY, line, column, scaleX, scaleY));

            }
        }
    }

    @Override
    public int size () {
        return this.locations.size();
    }

    @Override
    public boolean isEmpty () {
        return this.locations.isEmpty();
    }

    @Override
    public boolean contains (Object o) {
        return this.locations.contains(o);
    }

    @Override
    public Iterator<GridLocation> iterator () {
        return this.locations.iterator();
    }

    @Override
    public Object[] toArray () {
        return this.locations.toArray();
    }

    @Override
    public <T> T[] toArray (T[] a) {
        return this.locations.toArray(a);
    }

    @Override
    public boolean add (GridLocation e) {
        return this.locations.add(e);
    }

    @Override
    public boolean remove (Object o) {
        return this.locations.remove(o);
    }

    @Override
    public boolean containsAll (Collection<?> c) {
        return this.locations.containsAll(c);
    }

    @Override
    public boolean addAll (Collection<? extends GridLocation> c) {
        return this.locations.addAll(c);
    }

    @Override
    public boolean addAll (int index, Collection<? extends GridLocation> c) {
        return this.locations.addAll(index, c);
    }

    @Override
    public boolean removeAll (Collection<?> c) {
        return this.locations.removeAll(c);
    }

    @Override
    public boolean retainAll (Collection<?> c) {
        return this.locations.retainAll(c);
    }

    @Override
    public void clear () {
        this.locations.clear();
    }

    @Override
    public GridLocation get (int index) {
        return this.locations.get(index);
    }

    @Override
    public GridLocation set (int index, GridLocation element) {
        return this.locations.set(index, element);
    }

    @Override
    public void add (int index, GridLocation element) {
        this.locations.add(index, element);
    }

    @Override
    public GridLocation remove (int index) {
        return this.locations.remove(index);
    }

    @Override
    public int indexOf (Object o) {
        return this.locations.indexOf(o);
    }

    @Override
    public int lastIndexOf (Object o) {
        return this.locations.lastIndexOf(o);
    }

    @Override
    public ListIterator<GridLocation> listIterator () {
        return this.locations.listIterator();
    }

    @Override
    public ListIterator<GridLocation> listIterator (int index) {
        return this.locations.listIterator(index);
    }

    @Override
    public List<GridLocation> subList (int fromIndex, int toIndex) {
        return this.locations.subList(fromIndex, toIndex);
    }

    /**
     * verifica se existe nesse metrics um local igual ao passado no argumento
     * @param x valor do eixo x a ser testado
     * @param y valor do eixo y a ser testado
     * @return true em caso de sucesso e false caso contrário
     */
    public boolean exists (int x, int y) {
        Iterator<GridLocation> it = this.locations.iterator();

        while (it.hasNext()) {
            GridLocation loc = it.next();
            if (loc.getX() == x && loc.getY() == y) {
                return true;
            }

        }
        return false;
    }

    /**
     * verifica se existe nesse metrics um local igual ao passado no argumento
     * @param line valor da linha a ser testado
     * @param column valor da clouna a ser testado
     * @return true em caso de sucesso e false caso contrário
     */
    public boolean existsLocation (int line, int column) {
        Iterator<GridLocation> it = this.locations.iterator();

        while (it.hasNext()) {
            GridLocation loc = it.next();
            if (loc.getLine() == line && loc.getColumn() == column) {
                return true;
            }

        }
        return false;
    }

    /**
     * Localiza o GridLocation na linha e coluna dada nos argumentos
     * @param line inteiro que representa linha que sera pesquisada
     * @param column inteiro que representa coluna que sera pesquisada
     * @return retorna o elemento daquela linha e coluna ou null caso não exista
     */
    public GridLocation findLocation (int line, int column) {
        Iterator<GridLocation> it = this.locations.iterator();

        while (it.hasNext()) {
            GridLocation loc = it.next();
            if (loc.getLine() == line && loc.getColumn() == column) {
                return loc;
            }

        }
        return null;
    }
}
