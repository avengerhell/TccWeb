/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burgosanchez.tcc.venta.web.bean;

import com.burgosanchez.tcc.venta.ejb.IdentPersona;
import com.burgosanchez.tcc.venta.jpa.IdentPersonaFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author TID01
 */
@ManagedBean(name = "identPersonaBeanMB")
@ViewScoped

public class IdentPersonaBean implements Serializable {

    @Inject
    private IdentPersonaFacade identFacade;

    private IdentPersona identidad;
    private List<IdentPersona> identidades;

    public IdentPersonaBean() {
        identidad = new IdentPersona();
    }

    public IdentPersona getIdentidad() {
        return identidad;
    }

    public void setIdentidad(IdentPersona identidad) {
        this.identidad = identidad;
    }

    public List<IdentPersona> getIdentidades() {
        return identidades;
    }

    public void setIdentidades(List<IdentPersona> identidades) {
        this.identidades = identidades;
    }

    public List<IdentPersona> completeIdent(String query) {
        List<IdentPersona> allIdent = identFacade.findAll();
        List<IdentPersona> filteredIdent = new ArrayList<>();

        for (int i = 0; i < allIdent.size(); i++) {
            IdentPersona ide = allIdent.get(i);
            if (ide.getNumero().toLowerCase().startsWith(query)) {
                filteredIdent.add(ide);
            }
        }

        return filteredIdent;
    }
    
    public void modificar(){
    
    
    }
    
}
