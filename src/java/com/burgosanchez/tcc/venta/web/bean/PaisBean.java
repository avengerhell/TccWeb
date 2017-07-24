/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burgosanchez.tcc.venta.web.bean;

import com.burgosanchez.tcc.venta.ejb.Pais;
import com.burgosanchez.tcc.venta.jpa.PaisFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author TID01
 */
@ManagedBean(name = "paisBeanMB")
@ViewScoped
public class PaisBean implements Serializable{

    @Inject
    private PaisFacade paisFacade;

    private Pais pais;
    List<Pais> paises;

    public PaisBean() {
        pais = new Pais();
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public List<Pais> getPaises() {
        paises = paisFacade.findAll();
        return paises;
    }

    public void setPaises(List<Pais> paises) {
        this.paises = paises;
    }

}
