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
package bluefinger4j.pixel.allinterfaces;

import bluefinger4j.pixel.allclasses.Pixel;
import java.util.Collection;
import java.util.List;

/**
 * Implementação basica para que um Bitmap possa interagir dentro desse framework
 * @author renan
 */
public interface BitmapAdapter {


    /**
     * Verifica se esse BitmapAdapter esta vazio
     * @return true caso esteja vazio e false caso contrario
     */
    public boolean empty();

    /**
     * Configura um pixel nesse raster. O pixel será configurado usando a instancia Pixel passada no parametro, lembrando que 
     * esse método deve ser seguro e não pode retorna erro. Caso o pixel não puder ser escrito ou o pixel sera iginorado ou sera tomada
     * outra ação determinada pela implementação.
     * @param pixel pixel que será configurado nesse raster 
     */
    public void setPixel(Pixel pixel);

    /**
     * Configura uma coleção de pixel nesse raster. Os pixels serão configurados usando as instancias Pixel's passada no parametro.
     * O efeito dve ser o mesmo que chamar setPixel(Pixel pixel) para todos os elementos da coleção.
     * @param pixels pixels que serão usados na configurado paraa esse raster 
     */
    public void setPixels(Collection<Pixel> pixels);

    /**
     * Configura uma array de pixel nesse raster Os pixels serão configurados usando as instancias Pixel's passada no parametro.
     * O efeito dve ser o mesmo que chamar setPixel(Pixel pixel) para todos os elementos desse array.
     * @param pixels pixels que serão usados na configurado paraa esse raster 
     */
    public void setPixels(Pixel[] pixels);

    /**
     * Obem o pixel pela sua posição no eixo x , y. 
     * Atenção mesmo que não exista um pixel correspondênte essa classe fornecerá
     * uma istância valida.
     * @param x valor do eixo x
     * @param y valor do eixo y
     * @return O pixel correspondente a posição. 
     */
    public Pixel getPixel(int x, int y);

    /**
     * Obem o pixel pela sua posição no eixo x , y. e que seja aprovado pelo validator. 
     * Atenção mesmo que não exista um pixel correspondênte essa classe fornecerá
     * uma istância valida nesse caso a posição de x e y serão as dadadas pelos 
     * argumentos passados por esse método  e a cor sera igual a zero.
     * @param x valor do eixo x
     * @param y valor do eixo y
     * @param validator implementação que fara a validação
     * @return O pixel correspondente a posição. 
     */
    public Pixel getPixel(int x, int y, PixelValidate validator);

    /**
     * Obem os pixels a partir da posição no eixo x , y. e que seja aprovado pelo validator. 
     * Atenção mesmo que não exista um pixel correspondênte essa classe fornecerá
     * uma istância valida nesse caso a posição de x e y serão as dadadas pelos 
     * argumentos passados por esse método  e a cor sera igual a zero.
     * @param x valor do eixo x
     * @param y valor do eixo y
     * @param width largura do retangulo da aréa que será selecionada
     * @param height altura do retangulo da area que sera selecionada
     * @param validator implementação que fara a validação
     * @return Os pixels correspondente a area e que foram validados pelo validator . 
     */
    public List<Pixel> getPixels(int x, int y, int width, int height, PixelValidate validator);

    /**
     * Obem os pixels a partir da posição no eixo x , y. e que seja aprovado pelo validator. 
     * Atenção mesmo que não exista um pixel correspondênte essa classe fornecerá
     * uma istância valida nesse caso a posição de x e y serão as dadadas pelos 
     * argumentos passados por esse método  e a cor sera igual a zero.
     * @param x valor do eixo x
     * @param y valor do eixo y
     * @param width largura do retangulo da area que será selecionada
     * @param height altura do retangulo da area que sera selecionada
     * @return Os pixels correspondentes a area. 
     */
    public List<Pixel> getPixels(int x, int y, int width, int height);

    /**
     * verifica se a posição passada existe um pixel
     * @param x posição no eixo x
     * @param y posição no eixo y
     * @return retorna true caso exista e false caso contrario
     */
    public boolean hasPosition(int x, int y);

    /**
     * Verifica se exitem pixels na coluna contida entre x e getWidth() que esteja na linha y
     * @param x posição no eixo x a ser testada 
     * @param y posição no eixo y a ser testada
     * @return retorna true se a posição passada no parametro existir e false caso contrario
     */
    public boolean hasPositionX(int x, int y);

    /**
     * Verifica se exitem pixels na linha contida entre y e getHeight() que esteja na coluna x
     * @param y posição no eixo y a ser testada 
     * @return retorna true se a posição passada no parametro existir e false caso contrario
     */
    public boolean hasPositionY(int x,int y);

    /**
     * Obtém a largura da imagem 
     * @return largura da imagem
     */
    public int getWidth();

    /**
     * Obtem a altura da imagem
     * @return altura da imagem
     */
    public int getHeight();
}
