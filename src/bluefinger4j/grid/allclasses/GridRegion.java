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

/**
 * Representa uma região do Grid onde o bitmap sera desenhado
 * @author renan_user
 */
public class GridRegion {

    private GridLocation startLocation;
    private GridLocation endLocation;
    private GridMargin margin;
    private GridPadding padding;

    /**
     * Cria uma Região no grid
     * @param startLocation ponto onde se inicializa essa região
     * @param endLocation ponto onde se finaliza essa região
     */
    public GridRegion (GridLocation startLocation, GridLocation endLocation) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
    }

    /**
     * Obtem á area onde termina essa região
     * @return GridLocation que representa o local onde termina essa região é sempre retornado um grid location válido
     * mesmo quando se passa no costrutor um grid location null, nesse caso o local usado e o 0,0 com scala 1.0
     */
    public GridLocation getEndLocation () {
        return (this.endLocation == null) ? new GridLocation(0, 0, 1.0, 1.0) : endLocation;
    }

    /**
     * Obtem á area onde começa essa região
     * @return GridLocation que representa o local onde começa essa região é sempre retornado um grid location válido
     * mesmo quando se passa no costrutor um grid location null, nesse caso o local usado e o 0,0 com scala 1.0
     */
    public GridLocation getStartLocation () {
        return (this.startLocation == null) ? new GridLocation(0, 0, 1.0, 1.0) : startLocation;
    }

    /**
     * Obem a margem que esta sendo usado por esse GridRegion 
     * @return GridMargin configurado para essa region, esse método munca retorna null caso não tenha sido setado
     * uma marign para essa região sera utilizado a margin como tendo seu top, right , bottom e left como 0. 
     */
    public GridMargin getMargin () {
        return (this.margin == null) ? new GridMargin(0, 0, 0, 0) : margin;
    }

    /**
     * Configura uma margin para ser usada nessa região
     * @param margin margem a ser usada nessa região
     */
    public void setMargin (GridMargin margin) {
        this.margin = margin;
    }

    /**
     * Obtem o padding que esta sendo usado por esse GridRegion 
     * @return GridPadding configurado o padding para essa region, esse método munca retorna null caso não tenha sido setado
     * uma marign para essa região sera utilizado a padding como tendo seu top, right , bottom e left como 0. 
     */
    public GridPadding getPadding () {
        return (this.padding == null) ? new GridPadding(0, 0, 0, 0) : padding;
    }

    /**
     * Configura uma padding para ser usada nessa região
     * @param padding padding a ser usada nessa região
     */
    public void setPadding (GridPadding padding) {
        this.padding = padding;
    }

    public String toJson () {
        StringBuilder sb = new StringBuilder();
        sb.append('{').append('"').append(this.getClass().getSimpleName()).append('"').append(':').append('{');
        sb.append("\"start\":").append(this.getStartLocation().toJson()).append(',');
        sb.append("\"end\":").append(this.getEndLocation().toJson()).append('}').append(',');
        sb.append("\"options\":");
        sb.append('[').append(this.getMargin().toJson()).append(',').append(this.getPadding().toJson()).append(']');
        sb.append('}');

        return sb.toString();
    }
}
