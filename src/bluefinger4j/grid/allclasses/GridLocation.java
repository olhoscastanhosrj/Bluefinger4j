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

import bluefinger4j.grid.allinterfaces.IGridLocation;
import bluefinger4j.point.allclasses.Point;
import bluefinger4j.point.allinterfaces.IPoint;

import java.io.Serializable;

/**
 * Representa um local demarcado pela interceção de uma linha com uma coluna no grid
 * @author renan
 */
public class GridLocation implements Comparable<GridLocation>, Serializable, IGridLocation {

    private static final long serialVersionUID = -275740423567920836L;
    private int originX = 0;
    private int originY = 0;
    private int line;
    private int column;
    private double scaleX;
    private double scaleY;

    /**
     * Cria um GridLocation passando a linha e coluna que esse objeto vai representa
     * e passando a escala que servira para calcular a posição exata no eixo x e y
     * @param line numero da linha representada por esse GridLocation
     * @param column numero da coluna representada por esse GridLocation
     * @param scaleX escala usada para o eixo x, esse valor e utilizado para ser calculado a posição real de x
     * @param scaleY escala usada para o eixo y, esse valor e utilizado para ser calculado a posição real de y
     */
    public GridLocation (int line, int column, double scaleX, double scaleY) {
        this.line = line;
        this.column = column;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    /**
     * Cria um GridLocation passando a linha e coluna que esse objeto vai representa alén da coordenada de origem para esse grid location,
     * além disso também é passando a escala que servira para calcular a posição exata no eixo x e y
     * @param originX coordenada de origem no eixo x
     * @param originY  coordenada de origem no eixo y
     * @param line numero da linha representada por esse GridLocation
     * @param column numero da coluna representada por esse GridLocation
     * @param scaleX escala usada para o eixo x, esse valor e utilizado para ser calculado a posição real de x
     * @param scaleY escala usada para o eixo y, esse valor e utilizado para ser calculado a posição real de y
     */
    public GridLocation (int originX, int originY, int line, int column, double scaleX, double scaleY) {
        this.originX = originX;
        this.originY = originY;
        this.line = line;
        this.column = column;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    /**
     * Obtém a posição relacionada a esse GridLocation no eixo x
     * @return valor do eixo x
     */
    @Override
    public int getX () {
        return (int) (this.originX + Math.round(line * scaleX));
    }

    /**
     * Obtém a posição relacionada a esse GridLocation no eixo y
     * @return valor do eixo y
     */
    @Override
    public int getY () {
        return (int) (this.originY + Math.round(column * scaleY));
    }

    /**
     * Obtém o numero da coluna desse GridLocation
     * @return numero da coluna desse GirdLocation
     */
    @Override
    public int getColumn () {
        return column;
    }

    /**
     * Obtém o numero da linha desse GridLocation
     * @return numero da linha desse GirdLocation
     */
    @Override
    public int getLine () {
        return line;
    }

    /**
     * Obtem a escala ultilizada para calcular as posições desse location no eixo x
     * @return valor da escala no eixo x
     */
    @Override
    public double getScaleX () {
        return scaleX;
    }

    /**
     * Obtem a escala ultilizada para calcular as posições desse location no eixo y
     * @return valor da escala no eixo y
     */
    @Override
    public double getScaleY () {
        return scaleY;
    }

   
    /**
     * Retorna a representação do GridLocation como um IPoint
     * @return IPoint que representa esse GridLocation
     */
    @Override
    public IPoint toPoint () {
        return new Point(this.getX(), this.getY(), 0);
    }

    @Override
    public int compareTo (GridLocation o) {
        int diferenceLine = this.getLine() - o.getLine();
        int diferenceColumn = this.getColumn() - o.getColumn();

        return diferenceLine + (diferenceColumn * 8192);
    }
    
    
    @Override
    public String toJson(){
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        sb.append("\"GridLocation\": ").append('{');
        sb.append("\"x\":").append(this.getX()).append(", ");
        sb.append("\"y\":").append(this.getY()).append(", ");
        sb.append("\"line\":").append(this.getLine()).append(", ");
        sb.append("\"column\":").append(this.getColumn()).append(", ");
        sb.append("\"scale_x\":").append(this.getScaleX()).append(", ");
        sb.append("\"scale_y\":").append(this.getScaleX());
        sb.append('}').append('}');
        return sb.toString();
    }

    @Override
    public String toString () {
        StringBuilder sb = new StringBuilder();
        sb.append("<GridLocation ");
        sb.append("x=\"").append(this.getX()).append("\" ");
        sb.append("y=\"").append(this.getY()).append("\" ");
        sb.append("line=\"").append(this.getLine()).append("\" ");
        sb.append("column=\"").append(this.getColumn()).append("\" ");
        sb.append("scale_x=\"").append(this.getScaleX()).append("\" ");
        sb.append("scale_y=\"").append(this.getScaleX()).append("\" ");
        sb.append("/>\n");
        return sb.toString();
    }
}
