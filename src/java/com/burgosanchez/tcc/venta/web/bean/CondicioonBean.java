/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burgosanchez.tcc.venta.web.bean;

import com.burgosanchez.tcc.venta.ejb.Condicion;
import com.burgosanchez.tcc.venta.jpa.CondicionFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author TID01
 */
@ManagedBean(name = "condicionBeanMB")
@ViewScoped
public class CondicioonBean implements Serializable{

    @Inject
    private CondicionFacade condicionFacade;

    private Condicion condicion;
    List<Condicion> condiciones;

    public CondicioonBean() {
        condicion = new Condicion();
    }

    public Condicion getCondicion() {
        return condicion;
    }

    public void setCondicion(Condicion condicion) {
        this.condicion = condicion;
    }

    public List<Condicion> getCondiciones() {
        return condiciones;
    }

    public void setCondiciones(List<Condicion> condiciones) {
        this.condiciones = condiciones;
    }

    public void insertar() {
        condicion.setCodCondicion(String.valueOf(condicionFacade.obtenerSecuenciaVal()));
        condicionFacade.create(condicion);
        condicion = new Condicion();
    }

    public void modificar() {
        condicionFacade.edit(condicion);
        condicion = new Condicion();
    }

    public void eliminar() {
        condicionFacade.remove(condicion);
        condicion = new Condicion();
    }

}
