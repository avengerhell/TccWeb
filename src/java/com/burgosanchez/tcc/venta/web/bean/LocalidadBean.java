/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burgosanchez.tcc.venta.web.bean;

import com.burgosanchez.tcc.venta.ejb.Localidad;
import com.burgosanchez.tcc.venta.jpa.LocalidadFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author TID01
 */
@ManagedBean(name = "localidadBeanMB")
@ViewScoped
public class LocalidadBean implements Serializable{

    @Inject
    private LocalidadFacade localidadFacade;

    private Localidad local;
    List<Localidad> locales;

    public LocalidadBean() {
        local = new Localidad();
    }

    public Localidad getLocal() {
        return local;
    }

    public void setLocal(Localidad local) {
        this.local = local;
    }

    public List<Localidad> getLocales() {
        locales = localidadFacade.findAll();
        return locales;
    }

    public void setLocales(List<Localidad> locales) {
        this.locales = locales;
    }

    public void insertar() {
        local.setCodLocalidad(String.valueOf(localidadFacade.obtenerSecuenciaVal()));
        localidadFacade.create(local);
        local = new Localidad();
    }

    public void modificar() {
        localidadFacade.edit(local);
        local = new Localidad();
    }

    public void eliminar() {
        localidadFacade.remove(local);
        local = new Localidad();
    }

}
