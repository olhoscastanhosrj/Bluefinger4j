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

import bluefinger4j.grid.allinterfaces.GridLimits;

/**
 *  Representa uma margem a ser usada em uma Região
 * @author renan_user
 */
public class GridMargin implements GridLimits {
    
    
    private int left;
    private int top;
    private int bottom;
    private int right;

    /**
     * Cria uma margem passndo para a mesma as suas dimensões
     * @param top valor a ser usado na margem superior
     * @param right valor a ser usado na margem direita
     * @param bottom valor a ser usado na margem inferior
     * @param left valor a ser usado na margem esquerda
     */
    public GridMargin (int top, int right, int bottom, int left) {
        this.left = left;
        this.top = top;
        this.bottom = bottom;
        this.right = right;
    }


    @Override
    public int getBottom () {
        return bottom;
    }
    
    @Override
    public int getLeft () {
        return left;
    }


    @Override
    public int getRight () {
        return right;
    }


    @Override
    public int getTop () {
        return top;
    }
    
    /**
     * Representação JSON desse objeto
     * @return String contendo o json que representa esse objeto de margem
     */
    public String toJson(){
        StringBuilder sb = new StringBuilder();
        sb.append('{').append("\"margin:\"");
        sb.append('{');
        sb.append("\"top:\"").append(String.valueOf(this.top));
        sb.append("\"right:\"").append(String.valueOf(this.right));
        sb.append("\"bottom:\"").append(String.valueOf(this.bottom));
        sb.append("\"left:\"").append(String.valueOf(this.left));
        sb.append('}').append('}');
        return sb.toString();
    }
    
    @Override
    public String toString(){
        return "<margin top=\" " + String.valueOf(this.top) +"\" -margin-righ=\"" + String.valueOf(this.right) 
                + " \" margin-bottom=\" " + String.valueOf(this.bottom) + " \" left= " + String.valueOf(this.left) +"\" />";
    }
    
}
