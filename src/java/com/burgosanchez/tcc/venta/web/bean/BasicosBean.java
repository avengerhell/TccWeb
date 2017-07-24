/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burgosanchez.tcc.venta.web.bean;

import com.burgosanchez.tcc.venta.bean.Basicos;
import com.burgosanchez.tcc.venta.jpa.BasicosFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author TID01
 */
@ManagedBean(name = "basicosBeanMB")
@ViewScoped
public class BasicosBean implements Serializable{
    
    
    @Inject
    private BasicosFacade p;
    private List<Basicos> lista;

    public BasicosBean() {
    }

    public List<Basicos> getLista() {
        return lista;
    }

    public void setLista(List<Basicos> lista) {
        this.lista = lista;
    }


    
    public void prueba(String cod) {
        lista = p.obtenerBasicos(cod);
       
    }
}
