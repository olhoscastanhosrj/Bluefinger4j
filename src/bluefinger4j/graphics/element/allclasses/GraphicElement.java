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
package bluefinger4j.graphics.element.allclasses;

import bluefinger4j.graphics.element.allinterfaces.IGraphicElement;
import bluefinger4j.graphics.geometry.allinterfaces.IGeometry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;


/**
 * implementação padrão do IGraphicElement
 * @author renan_user
 */
public class GraphicElement implements IGraphicElement, Iterable<IGeometry> {

    private String name;
    private List<IGeometry> geometries;
    

    /**
     * Construtor que recebe um nome para ser usado por esse IGraphicElement
     * @param name nome a ser usado por esse elemento
     */
    public GraphicElement (String name) {
        this.geometries = new ArrayList<IGeometry>();
        this.name = name;
    }


    @Override
    public String getName () {
        return name;
    }

    @Override
    public void connect(IGeometry geometry){
        this.geometries.add(geometry);
    }
    
    @Override
     public void connect(Collection<IGeometry> geometries){
        this.geometries.addAll (geometries);
    }
 
    @Override
    public void connect(IGeometry[] geometries){
        this.geometries.addAll(Arrays.asList(geometries));
    }

    @Override
    public void disconnect(IGeometry geometry) {
        this.geometries.remove(geometry);
    }
    
    @Override
    public void disconnect(Collection<IGeometry> geometries){
        this.geometries.removeAll (geometries);
    }
 
    @Override
    public void disconnect(IGeometry[] geometries){
        this.geometries.removeAll(Arrays.asList(geometries));
    }

    @Override
    public Iterator<IGeometry> iterator() {
        return this.geometries.iterator();
    }

    @Override
    public LuaValue toLuaValue() {
        return CoerceJavaToLua.coerce(this);
    }
    
    
}
