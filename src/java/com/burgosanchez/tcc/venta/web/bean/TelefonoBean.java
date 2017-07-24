/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burgosanchez.tcc.venta.web.bean;

import com.burgosanchez.tcc.venta.ejb.TelPersona;
import com.burgosanchez.tcc.venta.jpa.TelPersonaFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author TID01
 */
@ManagedBean(name = "telefonoBeanMB")
@ViewScoped
public class TelefonoBean implements Serializable{
    @Inject
    private TelPersonaFacade telFacade;
    
    private TelPersona telefono;
    List<TelPersona> telefonos;

    public TelefonoBean() {
        telefono = new TelPersona();
    }

    public TelPersona getTelefono() {
        return telefono;
    }

    public void setTelefono(TelPersona telefono) {
        this.telefono = telefono;
    }

    public List<TelPersona> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<TelPersona> telefonos) {
        this.telefonos = telefonos;
    }
    
    public void buscarTel(String cod,String tel){
        telefono = telFacade.obtenerTelefono(cod,tel);
    }
    
    public void modificar(){
        telFacade.edit(telefono);
        telefono = new TelPersona();
    }
    
    public void eliminar(){
        telFacade.remove(telefono);
        telefono = new TelPersona();
    }
    
}
