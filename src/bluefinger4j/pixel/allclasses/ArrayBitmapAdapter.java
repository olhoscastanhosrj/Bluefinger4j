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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Implementação de BitmapAdapter que usa um Array de Pixel para ser criado, nessa implementação algumas operações 
 * são executadas automaticamente para que sempre os pixels dentro dela estejam otimizados para o uso.
 * @author renan
 */
public class ArrayBitmapAdapter extends AbstractBitmapAdapter {

    private List<Pixel> pixels;

    /**
     * Construtor protegido de ArrayBitmapAdapter ele existe para que se possa  ser usado 
     * em implementações que derivem dessa e que usam algum tipo de estrutura de dados 
     * baseada no framework Collection do java
     * @param pixels Collection contendo os pixel que serão utilizadas nessa classe 
     * @param freeze true caso não deva ser ampliado para conter novos dados e false caso contrário
     */
    protected ArrayBitmapAdapter (Collection<Pixel> pixels, boolean freeze) {
        if (freeze) {
            this.pixels = new ArrayList<Pixel>(pixels.size());
        } else {
            this.pixels = new ArrayList<Pixel>();
        }
        this.pixels.addAll(pixels);
        Collections.sort(this.pixels);
    }

    /**
     * Construtor publico de ArrayBitmapAdapter ele cria uma instância de ArrayBitmapAdapter passando  os pixels que os
     * compõem no array
     * @param pixels array contendo os pixels que serão usados por essa instância
     */
    public ArrayBitmapAdapter (Pixel[] pixels) {
        this(Arrays.asList(pixels), false);
    }

    /**
     * Construtor sem parametros. Cria um ArrayBitmapAdapter vazio.
     */
    public ArrayBitmapAdapter(){
        this(new ArrayList<Pixel>(), false);
    }
    
    @Override
    public Pixel getPixel (int x, int y) {
        x = (x > this.getWidth() - 1) ? (x + 1) % this.getWidth() : Math.abs(x);
        y = (y > this.getHeight() - 1) ? (y + 1) % this.getHeight() : Math.abs(y);

        for (int i = 0; i < this.pixels.size(); i++) {
            if (this.pixels.get(i).getX() == x && this.pixels.get(i).getY() == y) {
                return this.pixels.get(i);
            }
        }

        return new Pixel(x, y, new Color(0));
    }

    @Override
    public void setPixel (Pixel pixel) {


        int x = Math.abs(pixel.getX());
        int y = Math.abs(pixel.getY());

        if (this.hasPosition(x, y)) {

            for (int i = 0; i < this.pixels.size(); i++) {
                Pixel px = this.pixels.get(i);
                if (px.getX() == pixel.getX() && px.getY() == pixel.getY()) {
                    this.pixels.remove(px); //remove o pixel que já consta na lista para que 
                    //seja adicionado o pixel passado como argumento que será inserido na mesma posição
                }
            }

            this.pixels.add(new Pixel(x, y, pixel.getColorInstance()));
            Collections.sort(this.pixels);
        }
    }

    @Override
    public int getWidth () {
        int x1 = 0;
        int x2 = 0;

        for (int i = 0; i < this.pixels.size(); i++) {
            x1 = (x1 < this.pixels.get(i).getX()) ? this.pixels.get(i).getX() : x1;
            x2 = (x2 > this.pixels.get(i).getX()) ? this.pixels.get(i).getX() : x2;
        }       

        return (x2 - x1);
    }

    @Override
    public int getHeight () {
        int y1 = 0;
        int y2 = 0;

        for (int i = 0; i < this.pixels.size(); i++) {
            y1 = (y1 < this.pixels.get(i).getY()) ? this.pixels.get(i).getY() : y1;
            y2 = (y2 > this.pixels.get(i).getY()) ? this.pixels.get(i).getY() : y2;
        }

        return (y2 - y1);
    }

    @Override
    public boolean empty() {
        return this.pixels.isEmpty();
    }
}
