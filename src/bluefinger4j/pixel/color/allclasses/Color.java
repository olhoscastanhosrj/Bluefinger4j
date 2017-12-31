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
package bluefinger4j.pixel.color.allclasses;

import java.io.Serializable;

/**
 * Cor a ser usada em um pixel
 *
 * @author renan_user
 */
public class Color implements Serializable, Comparable<Color> {

    private static final long serialVersionUID = -5007224638694654148L;

    private int color = 0xFF000000;

    /**
     * Cria uma instacia de cor passando o valor em rgb
     *
     * @param red valor do canal vermelho no intervalo de valores de 0 a 255
     * @param green valor do canal verde no intervalo de valores de 0 a 255
     * @param blue valor do canal azul no intervalo de valores de 0 a 255
     */
    public Color (int red, int green, int blue) {
        this.color = (0xFF << 24) & (red & 0xFF << 16) & (green & 0xFF << 8) & (blue & 0xFF);
    }
   
    /**
     * Cria uma instacia de cor passando o valor em rgb em percentual, onde 0.0 equivale a 0% e 1.0 equivale a 100%
     *
     * @param red valor do canal vermelho no intervalo de valores de 0.0 a 1.0
     * @param green valor do canal verde no intervalo de valores de 0.0 a 1.0
     * @param blue valor do canal azul no intervalo de valores de 0.0 a 1.0
     */
    public Color (float red, float green , float blue){
        this( (byte) (0xFF * red) , (byte) (0xFF * green) , (byte) (0xFF * blue) );
    }

    /**
     * Cria uma intância de cor passando o valor argb 
     *
     * @param alpha valor do canal alpha no intervalo de valores de 0 a 255
     * @param red valor do canal vermelho no intervalo de valores de 0 a 255
     * @param green valor do canal verde no intervalo de valores de 0 a 255
     * @param blue valor do canal azul no intervalo de valores de 0 a 255
     */
    public Color (int alpha, int red, int green, int blue) {
        this.color = (alpha & 0xFF << 24) & (red & 0xFF << 16) & (green & 0xFF << 8) & (blue & 0xFF);
    }
    
    
    /**
     * Cria uma intância de cor passando o valor argb em percentual, onde 0.0 equivale a 0% e 1.0 equivale a 100%
     *
     * @param alpha valor do canal alpha no intervalo de valores de 0.0 a 1.0
     * @param red valor do canal vermelho no intervalo de valores de 0.0 a 1.0
     * @param green valor do canal verde no intervalo de valores de 0.0 a 1.0
     * @param blue valor do canal azul no intervalo de valores de 0.0 a 1.0
     */
    public Color (float alpha, float red, float green, float blue) {
        this( (byte) (0xFF * alpha) , (byte) (0xFF * red) , (byte) (0xFF * green) , (byte) (0xFF * blue) );
    }

    /**
     * Cria uma instância de color passando o valor como inteiro onde o canal:<br/>
     * <ul>
     * <li>alpha é formado pelo bit 31 a 24    </li>
     * <li>vermelho é formado pelo bit 23 a 16 </li>
     * <li>green é formado pelo bit 15 a 8     </li>
     * <li>blue é formado pelo bit 7 a 0       </li>
     * </ul>
     *
     * @param color cor do pixel
     */
    public Color (int color) {
        this.color = color;
    }
    
    /**
     * Cria uma instancia de color usando a cor existente em uma outra instância de color passada no argumento
     * @param color Objeto color que tera sua cor copiada
     */
    public Color (Color color){
        this( (color != null) ? color.get() : 0);
    }

    /**
     * Obtem a cor dessa instância
     *
     * @return cor dessa instância
     */
    public int get () {
        return this.color;
    }

    /**
     * Obtem a cor dessa instância aplicando a mascara passada como argumento
     *
     * @param mask mascara de cor
     * @return cor dessa instância
     */
    public int get (int mask) {
        return this.color & mask;
    }
    
    

    /**
     * Cria uma instância pixel que tem a sua cor como a representada pela negação da cor atual. O canal alpha é iginorado
     * na operação sendo repetido o atual.
     * @return Color que tem sua cor como o inverso da cor desse pixel
     */
    public Color not () {
        return new Color(this.get(0xFF000000) | ~this.get());
    }

    /**
     * Cria uma intância Color que tem a sua cor como representação da cor do pixel atual com resultado da operação AND
     * bit a bit com a cor do pixel passado como argumento o canal alpha é iginorado sendo repetido o atual.
     *
     * @param color cor que fara a operação and com o cor atual
     * @return Color resultante da operação AND
     */
    public Color and (Color color) {
        return new Color(this.get(0xFF000000) | (this.get() & color.get()) );
    }

    /**
     * Cria uma intância de cor que tem a sua representação do pixel atual com resultado da operação OR bit a bit com a
     * cor do pixel passado como argumento o canal alpha é iginorado sendo repetido o atual.
     *
     * @param color cor que fara a operação and com o pixel atual
     * @return Color resultante da operação OR
     */
    public Color or (Color color) {
        return new Color( this.get(0xFF000000) |  (this.get() | color.get()) );
    }

    /**
     * Cria uma intância Color que tem a sua cor como representação da cor do pixel atual com resultado da operação XOR
     * bit a bit com a cor passado como argumento o canal alpha é iginorado sendo repetido o atual.
     *
     * @param color cor que fara a operação and com o pixel atual
     * @return Color resultante da operação XOR
     */
    public Color xor (Color color) {
        return new Color(this.get(0xFF000000) | (this.get() ^ color.get()) );
    }

    /**
     * Cria uma intância Color que tem a sua cor como representação da soma da cor passado no argumento com a cor do
     * pixel dessa istância.
     *
     * @param color cor do pixel que fara a operação com o pixel atual
     * @return Color resultante da operação
     */
    public Color sum (Color color) {
        return new Color(this.get() + color.get() );
    }
    
        /**
     * Cria uma intância Color que tem a sua cor como representação da soma da cor passado no argumento com a cor do
     * pixel dessa istância se preserveAlpha estiver marcada o canal alpha é preservado.
     *
     * @param color cor do pixel que fara a operação com o pixel atual
     * @param preserveAlpha se true o canal alpha é preservado
     * @return Color resultante da operação
     */
    public Color sum (Color color , boolean preserveAlpha) {
        if (preserveAlpha){
            return new Color( this.get(0xFF000000) | (this.get() + color.get()) );
        }else{
            return new Color(this.get() + color.get() );
        }
    }

    /**
     * Cria uma intância de color que tem a sua cor como representação da subtração de cor passado no argumento com a
     * cor do pixel dessa istância.
     *
     * @param color cor que fara a operação com o pixel atual
     * @return Color resultante da operação
     */
    public Color subtract (Color color) {
        return new Color(this.get() - color.get() );
    }
    
    /**
     * Cria uma intância de color que tem a sua cor como representação da subtração de cor passado no argumento com a
     * cor do pixel dessa istância se preserveAlpha estiver marcada o canal alpha é preservado.
     *
     * @param color cor que fara a operação com o pixel atual
     * @param preserveAlpha se true o canal alpha é preservado
     * @return Color resultante da operação
     */
    public Color subtract (Color color, boolean preserveAlpha) {
        if(preserveAlpha){
            return new Color( this.get(0xFF000000) | (this.get() - color.get()));
        }else{
            return new Color(this.get() - color.get() );
        }
    }
     
    

    /**
     * Cria uma intância Color que tem a sua cor como representação da divisão do color passado no argumento pela a cor
     * do pixel dessa istância
     *
     * @param color cor que fara a operação com a cor atual
     * @return Color resultante da operação
     * @throws ArithmeticException Exceção levantada quando o valor de um dos canais da cor passado por argumento ou
     * dessa istância for zero e não for posivel fazer a divisão
     */
    public Color div (Color color) throws ArithmeticException {
        int a = 0xFF & ((this.get(0xFF000000) >> 24) / (color.get(0xFF000000) >> 24) << 24);
        int r = 0xFF & ((this.get(0x00FF0000) >> 16) / (color.get(0x00FF0000) >> 16) << 16);
        int g = 0xFF & ((this.get(0x0000FF00) >> 8) / (color.get(0x0000FF00) >> 8) << 8);
        int b = (this.get(0x000000FF) / color.get(0x0000FFFF));

        return new Color(a | r | g | b);
    }
    
    
        /**
     * Cria uma intância Color que tem a sua cor como representação da divisão do color passado no argumento pela a cor
     * do pixel dessa istância se preserveAlpha estiver marcada o canal alpha é preservado.
     *
     * @param color cor que fara a operação com a cor atual
     * @param preserveAlpha se true o canal alpha é preservado
     * @return Color resultante da operação
     * @throws ArithmeticException Exceção levantada quando o valor de um dos canais da cor passado por argumento ou
     * dessa istância for zero e não for posivel fazer a divisão
     */
    public Color div (Color color, boolean preserveAlpha) throws ArithmeticException {
        int a = (preserveAlpha) ? this.get(0xFF000000) >> 24 : 0xFF & ((this.get(0xFF000000) >> 24) / (color.get(0xFF000000) >> 24) << 24);
        int r = 0xFF & ((this.get(0x00FF0000) >> 16) / (color.get(0x00FF0000) >> 16) << 16);
        int g = 0xFF & ((this.get(0x0000FF00) >> 8) / (color.get(0x0000FF00) >> 8) << 8);
        int b = (this.get(0x000000FF) / color.get(0x0000FFFF));

        return new Color(a | r | g | b);
    }
    
    

    /**
     * Cria uma intância de Color que tem a sua cor como representação da multiplicação da cor do pixel passado no
     * argumento pela a cor do pixel dessa istância
     *
     * @param color cor que fara a operação com a cor atual
     * @return Color resultante da operação
     */
    public Color mul (Color color) {
        int a = 0xFF & (this.get(0xFF000000) >> 24) * (color.get(0xFF000000) >> 24) << 24;
        int r = 0xFF & (this.get(0x00FF0000) >> 16) * (color.get(0x00FF0000) >> 16) << 16;
        int g = 0xFF & (this.get(0x0000FF00) >> 8) * (color.get(0x0000FF00) >> 8) << 8;
        int b = (this.get(0x000000FF) * color.get(0x0000FFFF));

        return new Color(a | r | g | b);
    }

    
    /**
     * Cria uma intância de Color que tem a sua cor como representação da multiplicação da cor do pixel passado no
     * argumento pela a cor do pixel dessa istância se preserveAlpha estiver marcada o canal alpha é preservado.
     *
     * @param color cor que fara a operação com a cor atual
     * @param preserveAlpha se true o canal alpha será preservado
     * @return Color resultante da operação
     */
    public Color mul (Color color, boolean preserveAlpha) {
        int a = (preserveAlpha) ? this.get(0xFF000000) >> 24 : 0xFF & (this.get(0xFF000000) >> 24) * (color.get(0xFF000000) >> 24) << 24;
        int r = 0xFF & (this.get(0x00FF0000) >> 16) * (color.get(0x00FF0000) >> 16) << 16;
        int g = 0xFF & (this.get(0x0000FF00) >> 8) * (color.get(0x0000FF00) >> 8) << 8;
        int b = (this.get(0x000000FF) * color.get(0x0000FFFF));

        return new Color(a | r | g | b);
    }

    
    
    /**
     * Obtem o componente alpha da cor
     *
     * @return componente alpha da cor
     */
    public int getAlpha () {
        return this.color >> 24 & 0xFF;
    }

    /**
     * Obtem o componente vermelho da cor
     *
     * @return componente vermelho da cor
     */
    public int getRed () {
        return this.color >> 16 & 0xFF;
    }

    /**
     * Obtem o componente verde da cor l
     *
     * @return componente verde da cor
     */
    public int getGreen () {
        return this.color >> 8 & 0xFF;
    }

    /**
     * Obtem o componente azul da cor
     *
     * @return componente azul da cor
     */
    public int getBlue () {
        return this.color & 0xFF;
    }

    /**
     * Retorna as cores do pixel como um vetor com os repectivos valores dos canais alpha, red, green , blue
     *
     * @return array contendo a representação argb da cor desse pixel
     */
    public int[] toARGBChannels () {
        int[] argb = {this.getAlpha(), this.getRed(), this.getGreen(), this.getBlue()};
        return argb;
    }

   
    /**
     * Representação do percentual em relação ao canal alpha,red,green,blue respectivos
     * @return array contendo a representação argb da cor , 
     * os canais são codificados de forma que 1.0 corresponda ao toma mais claro do canal
     * e 0.0 o tom mais escuro
     */
    public float[] toARGBPercents(){
        float a = (float) ((this.getAlpha()) & 0xFF);
        float r = (float) ((this.getRed()  ) & 0xFF);
        float g = (float) ((this.getGreen()) & 0xFF);
        float b = (float) ((this.getBlue() ) & 0xFF);
        
        a = (a == 0) ? 0.0f : (float) (a / 0xFF);
        r = (r == 0) ? 0.0f : (float) (r / 0xFF);
        g = (g == 0) ? 0.0f : (float) (g / 0xFF);
        b = (b == 0) ? 0.0f : (float) (b / 0xFF);
        
        float[] argb = { a , r , g , b };
        
        return argb;
        
    }

    @Override
    public int hashCode () {
        int hash = 5;
        hash = 53 * hash + this.color;
        return hash;
    }

    @Override
    public boolean equals (Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Color other = (Color) obj;
        if (this.color != other.color) {
            return false;
        }
        return true;
    }

    /**
     * retorna a representação da cor do pixel no padrão YUV
     *
     * @return array contendo o elementos y , que equivale a luminancia e u,v que representa a cormancia
     */
    public int[] toYUV () {
        int r = this.get(0x00FF0000) >> 16;
        int g = this.get(0x0000FF00) >> 8;
        int b = this.get(0x000000FF);

        int y = (int) (0.299 * r + 0.587 * g + 0.114 * b);
        int u = (int) ((b - y) * 0.492f);
        int v = (int) ((r - y) * 0.877f);

        int[] yuv = {y, u, v};

        return yuv;
    }

    /**
     * retorna a representação da cor do pixel no padrão YIQ
     *
     * @return array contendo o elementos (Y,I,Q) respectivamente.
     */
    public int[] toYIQ () {
        //   Y = 0.299 R + 0.587 G + 0.114 B
        //   I = 0.596 R - 0.275 G - 0.321 B
        //   Q = 0.212 R - 0.523 G + 0.311 B

        int r = this.get(0x00FF0000) >> 16;
        int g = this.get(0x0000FF00) >> 8;
        int b = this.get(0x000000FF);

        int y = (int) (0.299 * r + 0.587 * g + 0.114 * b);
        int i = (int) (0.596 * r - 0.275 * g - 0.321 * b);
        int q = (int) (0.212 * r - 0.523 * g + 0.311 * b);

        int[] yiq = {y, i, q};

        return yiq;
    }

    @Override
    public int compareTo (Color o) {
        return (this.get(0x00FFFFFF) < o.get(0x00FFFFFF)) ? -1 : (this.get(0x00FFFFFF) > o.get(0x00FFFFFF)) ? 1 : 0; 
    }


}
