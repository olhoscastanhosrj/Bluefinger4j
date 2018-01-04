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
package bluefinger4j.point.allinterfaces;

import bluefinger4j.math.parsii.tokenizer.ParseException;
import java.util.Map;

/**
 * Interface que serve para ser implementada e representa um ponto esse ponto
 * pode ser associado a outro ponto formando caminhos essa associação é feita usando ou 
 * o método get e set link. As operrações de rotação são dadas em relaão a coordenadas da tela e não do próprio objeto. 
 * @author renan
 */
public interface IPoint extends Comparable<IPoint> {

    /**
     * Obtém o valor do eixo x desse ponto
     * @return valor do eixo x desse ponto
     */
    public double getX ();

    /**
     * Retorna o valor do eixo x como inteiro sendo usado o parametro scale para escalar o ponto nesse eixo 
     * @param scale escala que será aplicada ao eixo x
     * @return inteiro que representa a coordenada do ponto no eixo x na escala passada
     */
    public int getX (int scale);

    /**
     * Retorna o valor do eixo x como inteiro sendo usado o parametro origin para determinar a sua origin scale para escalar o ponto nesse eixo 
     * @param origin valor que será usado como origem do eixo x ,ou seja, o equivalente a x + origin.
     * @param scale escala que será aplicada no eixo x usado no ponto
     * @return inteiro que representa a coordenada do eixo x deslocado  o valor passado no argumento origin e escalado pelo argumento scale.
     */
    public int getX (int origin, int scale);

    /**
     * Obtem o valor do eixo y desse ponto
     * @return valor do eixo y desse ponto
     */
    public double getY ();

    /**
     * Retorna o valor do eixo y como inteiro sendo usado o parametro scale para escalar o ponto nesse eixo 
     * @param scale escala que será aplicada ao eixo y
     * @return inteiro que representa a coordenada do ponto no eixo y na escala passada
     */
    public int getY (int scale);

    /**
     * Retorna o valor do eixo y como inteiro sendo usado o parametro origin para determinar a sua origin scale para escalar o ponto nesse eixo 
     * @param origin valor que será usado como origem do eixo y ,ou seja, o equivalente a y + origin.
     * @param scale escala que será aplicada no eixo y usado no ponto
     * @return inteiro que representa a coordenada do eixo y deslocado  o valor passado no argumento origin e escalado pelo argumento scale.
     */
    public int getY (int origin, int scale);

    /**
     * Obtem o valor do eixo z desse ponto
     * @return valor do eixo z desse ponto
     */
    public double getZ ();

    /**
     * Retorna o valor do eixo z como inteiro sendo usado o parametro scale para escalar o ponto nesse eixo 
     * @param scale escala que será aplicada ao eixo z
     * @return inteiro que representa a coordenada do ponto no eixo y na escala passada
     */
    public int getZ (int scale);

    /**
     * Retorna o valor do eixo z como inteiro sendo usado o parametro origin para determinar a sua origin scale para escalar o ponto nesse eixo 
     * @param origin valor que será usado como origem do eixo z ,ou seja, o equivalente a z + origin.
     * @param scale escala que será aplicada no eixo z usado no ponto
     * @return inteiro que representa a coordenada do eixo z deslocado  o valor passado no argumento origin e escalado pelo argumento scale.
     */
    public int getZ (int origin, int scale);

  
    /**
     * Calcula a distancia entre esse ponto e o ponto p
     * @param p ponto que desejamos saber a distancia do ponto atual
     * @return retorna a distancia em valores positivos
     */
    public double distance (IPoint p);

    /**
     * Retorna uma representação de IPoint que fica exatamente no meio 
     * do caminho entre esse IPoint e o IPoint passado no parametro
     * @param exit posição ao final da reta começada por esse IPoint
     * @return O ponto que fica exatamente no centro dessa reta
     */
    public IPoint atMiddle (IPoint exit);

    /**
     * Faz calculos usando as coordenadas x,y,z ,ou seja, na expressão matémática 
     * passada no argumento eval pode conter além dos numeros esses termos citados. 
     * Além disso é possivel passar outras variaveis por um Map passado no argumento 
     * variables
     * @param eval expressão matemática que se deseja calcular um resultado
     * @param variables variaveis extras que podem ser usadas na expressão ou null
     * caso não exista.
     * @return retorna o valor de calculo
     * @throws ParseException exceção chamada caso a expressão eteja com algum erro.
     */
     public double calculate(String eval , Map<String, Double > variables  ) throws ParseException;
   
     
    /**
     * Faz calculos usando as coordenadas x,y,z ,ou seja, na expressão matémática 
     * passada no argumento eval pode conter além dos numeros esses termos citados.
     * O resultado da chamada é o mesmo que uma chamada a caculate(eval,null) sendo eval
     * a expressão aritimética contida em uma String.
     * @param eval expressão matemática que se deseja calcular um resultado
     * @return retorna o valor de calculo
     * @throws ParseException exceção chamada caso a expressão eteja com algum erro.
     */
     public double calculate(String eval) throws ParseException;
    /**
     * Representa o ponto como uma string que corresponde a uma vertice no arquivo wavefront
     * @return string com a setença WaveFront
     */
    public String toWaveFront ();

    /**
     * Reprsentação das coordenadas do ponto atravez de um array de inteiro, o retorno desse metodo é escalado
     * pelo argumento scale a ordem dos eixos são 
     * respectivamente (x , y , z)
     * @param scale a escala que os eixos serão escalados para que seja apliado ou reduzido no retorno desse método
     * @return array de inteiros que representa as coordenadas escaladas na ordem (x,y,z)
     */
    public int[] toIntArray (double scale);

    /**
     * Representação xml do IPoint
     * @return String contendo a representação xml do IPoint
     */
    public String toXML ();

    /**
     * Representação JSON do IPoint
     * @return String contendo a representação JSON do IPoint
     */
    public String toJson ();
}
