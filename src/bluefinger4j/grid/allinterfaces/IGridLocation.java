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

package bluefinger4j.grid.allinterfaces;

import bluefinger4j.point.allinterfaces.IPoint;


/**
 * Interface que é usada para implementar um local no grid
 * @author renan_user
 */
public interface IGridLocation {

    /**
     * Obtém o numero da coluna desse GridLocation
     * @return numero da coluna desse GirdLocation
     */
    int getColumn ();

    /**
     * Obtém o numero da linha desse GridLocation
     * @return numero da linha desse GirdLocation
     */
    int getLine ();

    /**
     * Obtem a escala ultilizada para calcular as posições desse location no eixo x
     * @return valor da escala x
     */
    double getScaleX ();
    
        /**
     * Obtem a escala ultilizada para calcular as posições desse location no eixo y
     * @return valor da escala y
     */
    double getScaleY ();

    /**
     * Obtém a posição relacionada a esse GridLocation no eixo x
     * @return valor do eixo x
     */
    int getX ();

    /**
     * Obtém a posição relacionada a esse GridLocation no eixo y
     * @return valor do eixo y
     */
    int getY ();

    
    /**
     * Retorna a representação do GridLocation como um IPoint
     * @return IPoint que representa esse GridLocation
     */
    IPoint toPoint ();
    
    /**
     * Rperesentação Json do GridLocation
     * @return String com a representação Json do GridLocation
     */
    public String toJson();


    
}
