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
import java.util.List;
import bluefinger4j.pixel.allinterfaces.BitmapAdapter;
import bluefinger4j.pixel.allinterfaces.PixelValidate;
import java.util.Collection;

/**
 * Implementação padrão para BitmapAdapter essa é uma classe abstrata foi feita para 
 * economizar código na hora de precisar se programar um raster compativel com o framework
 * @author renan
 */
public abstract class AbstractBitmapAdapter implements BitmapAdapter {

    @Override
    public abstract Pixel getPixel(int x, int y);
    
    @Override
    public abstract void setPixel(Pixel pixel);
    
    @Override
    public void setPixels(Collection<Pixel> pixels){
        for ( Pixel p : pixels){
            this.setPixel(p);
        }
    }

    @Override
    public void setPixels(Pixel[] pixels){
        for(int i = 0 ; i < pixels.length; i++) this.setPixel(pixels[i]);
    }

    @Override
    public Pixel getPixel(int x, int y, PixelValidate validator) {
        if (validator.validate(this, this.getPixel(x, y))) {
            return this.getPixel(x, y);
        } else {
            return new Pixel(x, y, new Color(0));
        }
    }

    @Override
    public List<Pixel> getPixels(int x, int y, int width, int height) {
        List<Pixel> result = new ArrayList<Pixel>();

        int boundX = (width + x);
        int boundY = (height + y);

        for (int cy = y; cy < boundY; cy++) {
            for (int cx = x; cx < boundX; cx++) {
                result.add(this.getPixel(cx, cy));

            }
        }
        return result;
    }

    @Override
    public List<Pixel> getPixels(int x, int y, int width, int height, PixelValidate validator) {
        List< Pixel> result = this.getPixels(x, y, width, height);
        for (int i = 0; i < result.size(); i++) {
            if (!validator.validate(this, result.get(i))) {
                result.remove(i);
            }
        }
        return result;
    }

    @Override
    public boolean hasPosition(int x, int y) {
        return this.getPixels(x, y , 1 , 1).isEmpty();
    }

    @Override
    public boolean hasPositionX(int x, int y) {
        return this.getPixels(x, y, this.getWidth(), y).isEmpty();
    }

    @Override
    public boolean hasPositionY(int x ,int y) {
        return this.getPixels(x, y, x, this.getHeight()).isEmpty();
    }
    

}
