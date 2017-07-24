/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burgosanchez.tcc.venta.web.bean;

import com.burgosanchez.tcc.venta.ejb.TipoIdentificacion;
import com.burgosanchez.tcc.venta.jpa.TipoIdentificacionFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author TID01
 */
@ManagedBean(name = "tipoIdentBeanMB")
@ViewScoped
public class TipoIdentificacionBean implements Serializable{

    @Inject
    private TipoIdentificacionFacade tipoFacade;

    private TipoIdentificacion tipo;
    private List<TipoIdentificacion> tipos;

    public TipoIdentificacionBean() { 
        tipo = new TipoIdentificacion();
    }

    public TipoIdentificacion getTipo() {
        return tipo;
    }

    public void setTipo(TipoIdentificacion tipo) {
        this.tipo = tipo;
    }

    public List<TipoIdentificacion> getTipos() {
        tipos = tipoFacade.findAll();
        return tipos;
    }

    public void setTipos(List<TipoIdentificacion> tipos) {
        this.tipos = tipos;
    }

}
