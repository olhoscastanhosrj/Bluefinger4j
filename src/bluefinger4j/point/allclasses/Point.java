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
package bluefinger4j.point.allclasses;

import bluefinger4j.math.parsii.eval.Parser;
import bluefinger4j.math.parsii.eval.Scope;
import bluefinger4j.math.parsii.tokenizer.ParseException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import bluefinger4j.point.allinterfaces.IPoint;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Implementação padrão da interface IPoint
 *
 * @author renan
 */
public class Point implements IPoint, Cloneable, Serializable {

    private static final long serialVersionUID = -1249637905238508254L;
    private double x;
    private double y;
    private double z;
    private IPoint link;

    /**
     * Cria um ponto passando as coordenadas que serão representadas por esse
     * ponto
     *
     * @param x valor para o eixo x
     * @param y valor para o eixo y
     * @param z valor para o eixo z
     */
    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;

    }

    /**
     * Cria um ponto passando as coordenadas que serão representados por esse
     * ponto e o ponto que será associado ao mesmo, o link serve para quando se
     * deseja fazer path com varios pontos.
     *
     * @param x valor para o eixo x
     * @param y valor para o eixo y
     * @param z valor para o eixo z
     * @param link ponto que estara associado a essa instância
     */
    public Point(double x, double y, double z, IPoint link) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.link = link;
    }

    /**
     * Cria um Point usando a posição passada nos argumentos como posição
     * inicial que será calculada com a multiplicada pela escala
     *
     * @param x valor do eixo x
     * @param y valor do eixo y
     * @param z valor do eixo z
     * @param scale escala que será aplicada nas coordenadas passadas para
     * calcular a posição do ponto
     */
    public Point(int x, int y, int z, double scale) {
        this.x = x * scale;
        this.y = y * scale;
        this.z = z * scale;

    }

    /**
     * Cria um ponto ataraves de um ponto de referencia (que servira de ponto de
     * origem) onde a posição do ponto que esta sendo criado será dado pelo
     * ponto de origem passado em pRef delocado dos valores passados nos
     * parametros mX , mY, mZ
     *
     * @param ptRef ponto que servira de origem para o local que será
     * representado por esse ponto
     * @param mX o quanto se deve mover do ponto de origem no eixo x
     * @param mY o quanto se deve mover do ponto de origem no eixo y
     * @param mZ o quanto se deve mover do ponto de origem no eixo z
     */
    public Point(IPoint ptRef, int mX, int mY, int mZ) {
        this.x = ptRef.getX() + mX;
        this.y = ptRef.getY() + mY;
        this.z = ptRef.getZ() + mZ;

    }

    /**
     * Cria uma instância de Point usando o IPoint passado em pt como modelo,
     * caso o argumento pt for null cria um ponto na coordenada 0,0,0 e com
     * scala 1.0
     *
     * @param pt IPoint que será usado como modelo ou null caso se deseje criar
     * nas coordenas 0,0,0
     */
    public Point(IPoint pt) {
        if (pt != null) {
            this.x = pt.getX();
            this.y = pt.getY();
            this.z = pt.getZ();

        } else {
            this.x = 0;
            this.y = 0;
            this.z = 0;
            this.link = null;
        }
    }

    /**
     * Cria uma instância de Point usando o IPoint passado em pt como modelo e o
     * argumento scale para que os eixos x,y,z sejam escalados usando o valor
     * passado
     *
     * @param pt IPoint que será usado como modelo
     * @param scale escala que será usada para esse ponto ,ou seja, o valor que
     * será multiplicado pelo valores dos eixos do ponto pt cujo o resultado
     * sera as coordenadas x,y,z resprectivas.
     */
    public Point(IPoint pt, double scale) {
        this.x = pt.getX() * scale;
        this.y = pt.getY() * scale;
        this.z = pt.getZ() * scale;

    }

    /**
     * Obtém o valor do eixo x desse ponto
     *
     * @return valor do eixo x desse ponto
     */
    @Override
    public double getX() {
        return x;
    }

    @Override
    public int getX(int scale) {
        //utilizado o valor do eixo de forma indireta por que caso seja modificado 
        //o método esse método tambem terá o valor alterado
        //automaticamente
        return (int) Math.round(this.getX() * scale);
    }

    @Override
    public int getX(int origin, int scale) {
        return this.getX(scale) + origin;
    }

    /**
     * Obtem o valor do eixo y desse ponto
     *
     * @return valor do eixo y desse ponto
     */
    @Override
    public double getY() {
        //utilizado o valor do eixo de forma indireta por que caso seja modificado 
        //o método esse método tambem terá o valor alterado
        //automaticamente
        return y;
    }

    @Override
    public int getY(int scale) {
        //utilizado o valor do eixo de forma indireta por que caso seja modificado 
        //o método esse método tambem terá o valor alterado
        //automaticamente
        return (int) Math.round(this.getY() * scale);
    }

    @Override
    public int getY(int origin, int scale) {
        return this.getY(scale) + origin;
    }

    /**
     * Obtem o valor do eixo z desse ponto
     *
     * @return valor do eixo z desse ponto
     */
    @Override
    public double getZ() {
        return z;
    }

    @Override
    public int getZ(int scale) {
        //utilizado o valor do eixo de forma indireta por que caso seja modificado 
        //o método esse método tambem terá o valor alterado
        //automaticamente
        return (int) Math.round(this.getZ() * scale);
    }

    @Override
    public int getZ(int origin, int scale) {
        return this.getZ(scale) + origin;
    }

    @Override
    public double calculate(String eval, Map<String, Double> variables) throws ParseException {
        Scope scope = new Scope();

        Set<Map.Entry<String, Double>> vars = variables.entrySet();
        Iterator<Map.Entry<String, Double>> it = vars.iterator();

        while (it.hasNext()) {
            Map.Entry<String, Double> entry = it.next();
            scope.create(entry.getKey()).withValue(entry.getValue());
        }

        scope.create("x").withValue(this.x);
        scope.create("y").withValue(this.y);
        scope.create("z").withValue(this.z);

        return Parser.parse(eval, scope).evaluate();
    }

    @Override
    public IPoint atMiddle(IPoint exit) {
        double px = (this.getX() == 0 && exit.getX() == 0) ? 0 : ((this.getX() + exit.getX()) / 2);
        double py = (this.getY() == 0 && exit.getY() == 0) ? 0 : ((this.getY() + exit.getY()) / 2);
        double pz = (this.getZ() == 0 && exit.getZ() == 0) ? 0 : ((this.getZ() + exit.getZ()) / 2);

        return new Point(px, py, pz);
    }

    @Override
    public int compareTo(IPoint o) {

        double x1 = this.getX();
        double y1 = this.getY();
        double z1 = this.getZ();

        double x2 = o.getX();
        double y2 = o.getY();
        double z2 = o.getZ();

        long value1 = Math.round(y1
                + Math.pow(-1 + (x1 * 16384), z1));

        long value2 = Math.round(y2
                + Math.pow(-1 + (x2 * 16384), z2));

        if ((value1 - value2) > 0) {
            return 1;
        } else if ((value1 - value2) < 0) {
            return - 1;
        }

        return 0;

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Point pt = new Point(this.getX(), this.getY(), this.getZ());

        return pt;
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof Point) {
            Point pt = (Point) o;
            return (this.getX() == pt.getX()
                    && this.getY() == pt.getY()
                    && this.getZ() == pt.getZ());
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.z) ^ (Double.doubleToLongBits(this.z) >>> 32));
        hash += ((hash & 1) == 0 ? 5 : -3);
        return hash;
    }

    @Override
    public String toWaveFront() {
        return "v " + toString() + "\n";
    }

    /**
     * Representação textual do ponto no formato "x y z"
     *
     * @return representação textual do ponto com os valores de x y e z
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat();
        String text = "";

        df.setMaximumFractionDigits(4);
        df.setMaximumIntegerDigits(4);
        df.setMinimumFractionDigits(1);
        df.setMinimumIntegerDigits(1);
        df.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.US));

        text = df.format(this.getX())
                + "  " + df.format(this.getY())
                + "  " + df.format(this.getZ()).replace(",", "");

        return text;
    }

    @Override
    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<point ");
        sb.append(" x=\"").append(this.getX()).append("\" ");
        sb.append(" y=\"").append(this.getY()).append("\" ");
        sb.append(" x=\"").append(this.getZ()).append("\" ");
        sb.append(">\n");

        sb.append("</point>\n");

        return sb.toString();
    }

    @Override
    public String toJson() {

        StringBuilder sb = new StringBuilder();
        sb.append("{\"point\":{ ");
        sb.append(" \"x\":").append(this.getX()).append(" ");
        sb.append(",\"y\":").append(this.getY()).append(" ");
        sb.append(",\"z\":").append(this.getZ()).append(" ");

        sb.append("}}");

        return sb.toString();

    }

    @Override
    public double distance(IPoint p) {
        return Math.abs(
                Math.sqrt(Math.pow(p.getX() - this.getX(), 2)
                        + Math.pow(p.getY() - this.getY(), 2)
                        + Math.pow(p.getZ() - this.getZ(), 2)));
    }

    @Override
    public int[] toIntArray(double scale) {
        int[] array = new int[3];
        array[0] = (int) Math.round(this.getX() * scale);
        array[1] = (int) Math.round(this.getY() * scale);
        array[2] = (int) Math.round(this.getZ() * scale);
        return array;
    }
}
