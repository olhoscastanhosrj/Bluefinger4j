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

import java.util.Collection;
import java.util.Map;
import java.util.Queue;

/**
 * Representa a interface que sera usada pela classe que tem a função de servir de intermediaria
 * entre a API local e a api do Bluefing4j
 * @author renan
 */
public interface BasicRasterEngine extends Runnable {
    
    
    /**
     * Nome dado a esse raster 
     * @return nome dado a esse raster
     */
    public String getName();
    
        
    /**
     * Obtem os rasters que deverão ser processados antes desse para que quando esse raster for executado
     * tenha um resultado de melhor qualidade, deve ser executado fora da classe, em 
     * situações que se deseja mais performaces os levels podem ser iginoradas
     * @return Colleção de raster que devem ser executadas antes desse BasicRasterEngine
     */
    public Collection<BasicRasterEngine> getLevels();
    
    /**
     * Queue contendo todas as instancias BasicRasterEntity que serão processadas por esse BasicRasterEngine
     * @return Queue que será usada para ser processada pelo metodo proccessEntity
     */    
    public Queue<BasicRasterEntity> getQueue();
    /**
     * Obtem um Map<String,String> contendo os parametros contidos e que serão uteis no processo das entidades
     * realizado no método process() são de responsabilidade da instância saber quais são os parametros validos 
     * @return parametros que serão utilizados 
     */
    public Map<String,String> getParameters();
    
    /**
     * Processo a ser executado por esse BasicRasterEngine é a qui que todas as entidades são passadas para a tela
     * @param entity entidade que será processada
     * @param parameters parametros que podem ser util para a renderização
     * @return retorna verdadeiro caso seja desejavel continuar o processo na aproxima entidade e falso caso contrario
     */
    
    public boolean process(BasicRasterEntity entity , Map<String, String> parameters);
    

    
}
