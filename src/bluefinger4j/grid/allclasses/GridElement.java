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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Cria um elemento que representa um Grid 
 * @author renan
 */
public class GridElement implements Serializable {

    private static final long serialVersionUID = 7653703122946355920L;
    private List<GridLocation> locations;
    private int depth;

    /**
     * Cria um GridElement passando o numero maximo de linha e colunas que terão e a escala
     * que será usada para o intervalo da linha e coluna a escala afeta os locations
     * @param countLine quantidade de linhas
     * @param countColumn quantidade de colunas
     * @param scaleX escala utilizada no eixo X
     * @param scaleY escala utilizada no eixo Y
     */
    public GridElement(int countLine, int countColumn, double scaleX, double scaleY) {
        this.locations = new ArrayList<GridLocation>();
       
        for (int column = 0; column < countColumn ; column++) {
            for (int line = 0; line < countLine; line++) {
                this.locations.add(new GridLocation(0,0 ,line , column, scaleX , scaleY));

            }
        
        }

    }

    /**
     * Obtem a profundidade do GridElement esse valor é usado para configurar o eixo z do ponto
     * @return valor referente a profundidade do GridElement
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Configura a profundidade do GridElement esse  valor é usado para configurar o eixo z do ponto  
     * @param depth valor referente a profundidade do GridElement
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }
    
    

    /**
     * Adiciona uma novo local para a lista de locais contidas nesse GridElement
     * @param location local a ser adicionado a esse GridElement
     */
    public void addLocation(GridLocation location) {
        this.locations.add(location);
    }

    /**
     * Remove um local contido nesse GridElement após chamar remove ele não tera vinculo com essa instancia
     * @param location local a ser removido desse GridElement
     */
    public void removeLocation(GridLocation location) {
        this.locations.remove(location);
    }

    /**
     * Adiciona uma coleção de locais para essa instância
     * @param locations coleção de locais a ser adicionado nessa instância
     */
    public void addLocations(Collection<GridLocation> locations) {
        this.locations.addAll(locations);
    }

    /**
     * Remove uma coleção de locais dessa instância
     * @param locations collection com locais a serem removidos dessa instancia
     */
    public void removeLocations(Collection<GridLocation> locations) {
        this.locations.removeAll(locations);
    }

    /**
     * Obtem uma coleção contendo os GridLocation 's que estão associados a essa instancia
     * esse método retorna uma lista copiada, sendo assim, mudança nessa collection não afetam a collection interna da classe
     * para atualizar a collection interna use setLocations(Collection<GridLocation> locations)
     * @return retrona uma Collection contendo os GridLocation 's associado a essa instância
     */
    public Collection<GridLocation> getLocations() {
        return new ArrayList<GridLocation>(this.locations);
    }

    /**
     * Configura uma coleção de locais que serão os locais do grid representado por essa instancia. Esse método discarta
     * todos os elementos contido anteriormente 
     * @param locations locais que serão adicionados a esse GridElement
     */
    public void setLocations(Collection<GridLocation> locations) {
        this.locations.clear();
        this.locations.addAll((locations));
    }
    
    

    /**
     * Retorna um GridLocation associado a essa instancia através do seu indice
     * @param index indice do elemento que se deseja retornar 
     * @return retrona o elemento que tem o seu indice igual ao que foi passado
     * @throws retorna  IndexOutOfBoundsException se o argumento index estiver fora dos limites validos
     */
    public GridLocation getLocation(int index) throws IndexOutOfBoundsException {
        return this.locations.get(index);
    }

    /**
     * Retorna um array de GridLocation 's associado a essa instancia atraves dos seus indices passados no argumento
     * indexArray
     * @param indexArray array contendo os indices dos elementos que serão retornados
     * @return retrona um array com os elementos que tem os indices especificados no parametro indexArray
     * @throws IndexOutOfBoundsException exceção disparada se o argumento index estiver fora dos limites validos
     */
    public GridLocation[] getLocations(int[] indexArray) throws IndexOutOfBoundsException {
        GridLocation[] loc = new GridLocation[indexArray.length];
        
        for(int i = 0 ; i < indexArray.length ; i++){
            loc[i] =this.getLocation(indexArray[i]);
        }

        return loc;
    }

    
    /**
     * Obtém um GridLocation associado a essa instancia se o mesmo existir na posição dada em x,y
     * @param x coordenada do eixo x de onde se tenta extrair o location 
     * @param y coordenada do eixo y de onde se tenta extrair o location
     * @return retorna o grid location que tem a posição x,y correspondente ou null caso não tenha um grid location naquela posição
     */
    public GridLocation getLocationByPosition(int x , int y){
        
        for (int i = 0 ; i < this.locationCount() ; i++){
            if (x == this.getLocation(i).getX() && y == this.getLocation(i).getY()){
                return this.getLocation(i);
            } 
        }
        
        return null;
    }
     
    /**
     * Obtém um GridLocation associado a essa instancia se o mesmo existir dentro de um retangulo delimitado pelos eixo x , y passados no argumento e extando entre os
     * limites dados por width e height.
     * @param x coordenada do eixo x de onde se começa a extração dos GridLocation 
     * @param y coordenada do eixo y de onde se começa a extração dos GridLocation
     * @param  width largura da area de extração de onde se faz a extração dos GridLocation
     * @param height altura da area de extração de onde se faz a extração dos GridLocation
     * @return retorna o grid location que está na area delimitada por esse retangulo
     */
    public Collection <GridLocation> getLocationByRectangle(int x , int y , int width, int height ){
        Collection<GridLocation> locArray = new ArrayList<GridLocation>();
        int boundX = width + x;
        int boundY = height + y;
        
        for (int i = 0 ; i < locationCount(); i++){
                GridLocation loc = this.getLocation(i);
                boolean accept = (x > loc.getX() && x < boundX ) && (y > loc.getY() && y < boundY ); 
                 if (accept){
                    locArray.add(loc);
                 }
        }
        
        return locArray;
    }

    
    /**
     * retorna a quantidade de locations associada a essa instancia
     * @return inteiro com a quantidade de locations associada a essa instancia
     */
    public int locationCount() {
        return this.locations.size();
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        sb.append("<GridElement ");
        sb.append("locationCount=\"").append(this.locations.size()).append("\"");
        sb.append(">\n");
        
        for (GridLocation loc : this.locations){
            sb.append("\t").append(loc.toString());
        }
        
        sb.append("\n</GridElement>");
        
        return sb.toString();
    }
}
