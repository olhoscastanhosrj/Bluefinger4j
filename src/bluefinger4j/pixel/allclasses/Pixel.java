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
package bluefinger4j.pixel.allclasses;

import bluefinger4j.pixel.color.allclasses.Color;
import java.io.Serializable;

/**
 * Contém os dados de um pixel de um raster
 *
 * @author renan
 */
public class Pixel implements Comparable<Pixel>, Serializable {

    private static final long serialVersionUID = -3654091555727440337L;
    private int x = 0;
    private int y = 0;
    private Color color = null;

    /**
     * Cria uma instância da classe pixel passando a sua posição e cor, Apesar de ser possível usar numero negativo para
     * os argumentos x,y não é aconselhavel.
     *
     * @param x posição no eixo x
     * @param y posição no eixo y
     * @param color cor do pixel
     */
    public Pixel (int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = (color != null ) ? color : new Color(0) ;
    }

    /**
     * Obtem a cor resultante do tratamento da cor do pixel pela cor da mascara A cor é calculada pelo and bit a bit
     * entre a mascara e a cor do pixel.
     *
     * @param mask mascara que sera aplicada
     * @return retorna a cor resultante do processo.
     */
    public int getColor (int mask) {
        return new Color(this.color.get(mask)).get();
    }

    /**
     * Obtem a cor do pixel
     *
     * @return a cor do pixel
     */
    public int getColor () {
        return color.get();
    }

    /**
     * Obtem a instância de color que esta sendo usada por essa instância
     *
     * @return objeto color com a cor dessa instancia
     */
    public Color getColorInstance () {
        return this.color;
    }

    /**
     * Obtém o valor para o eixo x do pixel
     *
     * @return valor do eixo x do pixel
     */
    public int getX () {
        return x;
    }

    /**
     * Obtém o valor para o eixo y do pixel
     *
     * @return valor do eixo y do pixel
     */
    public int getY () {
        return y;
    }

    @Override
    public int hashCode () {
        int hash = 7;
        hash = 89 * hash + this.x;
        hash = 89 * hash + this.y;
        hash = 89 * hash + this.getColor();
        return hash;
    }

    @Override
    public boolean equals (Object o) {
        if (o instanceof Pixel) {
            Pixel p = (Pixel) o;
            return p.getX() == this.getX() && p.getY() == this.getY() && this.getColor() == p.getColor();
        }
        return false;
    }

    @Override
    public int compareTo (Pixel o) {
        if (this.getX() == o.getX() && this.getY() == o.getY()) {
            return (this.getColor() > o.getColor()) ? 1 : (this.getColor() < o.getColor()) ? -1 : 0;
        } else {
            int aux = Math.round(this.getX() + this.getY() * 8192) - (o.getX() + o.getY() * 8192);
            return (aux > 0) ? 1 : (aux < 0) ? -1 : 0;
        }
    }

    @Override
    public String toString () {
        return toXML();
    }

    /**
     * Retorna uma representação JSON do pixel
     *
     * @return representação no formato JSON do pixel
     */
    public String toJson () {

        return "{\"x\":" + Integer.toString(this.getX()) + ", \"y\":" + Integer.toString(this.getY())
                + ", \"color\": \"#" + Integer.toHexString(this.getColor()) + "\" }";

    }

    /**
     * Retorna uma representação XML do pixel
     *
     * @return representação XML do pixel
     */
    public String toXML () {
        StringBuilder sb = new StringBuilder();

        sb.append("<Pixel ");
        sb.append(" x=\"").append(this.getX()).append("\" ");
        sb.append(" y=\"").append(this.getY()).append("\" ");
        sb.append(" color=\"#").append(Integer.toHexString(this.getColor())).append("\" />\n");

        return sb.toString();
    }
}
