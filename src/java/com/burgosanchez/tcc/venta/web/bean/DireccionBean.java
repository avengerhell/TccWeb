/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burgosanchez.tcc.venta.web.bean;

import com.burgosanchez.tcc.venta.ejb.DireccionPersona;
import com.burgosanchez.tcc.venta.jpa.DireccionPersonaFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author TID01
 */

@ManagedBean(name = "direccionBeanMB")
@ViewScoped
public class DireccionBean implements Serializable{
    @Inject
    private DireccionPersonaFacade direccionFacade;
    
    private DireccionPersona direccion;
    List<DireccionPersona> direcciones;

    public DireccionBean() {
        direccion = new DireccionPersona();
    }

    public DireccionPersona getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionPersona direccion) {
        this.direccion = direccion;
    }

    public List<DireccionPersona> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<DireccionPersona> direcciones) {
        this.direcciones = direcciones;
    }
    
    public void buscarDir(String per, String dir){
        direccion = direccionFacade.obtenerDireccion(per, dir);
    }
    
    public void modificar(){
        direccionFacade.edit(direccion);
        direccion = new DireccionPersona();
    }
    
    public void eliminar(){
        direccionFacade.remove(direccion);
        direccion = new DireccionPersona();
    }
    
}
