/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burgosanchez.tcc.venta.web.bean;

import com.burgosanchez.tcc.venta.ejb.CondicionEvento;
import com.burgosanchez.tcc.venta.ejb.CondicionEventoPK;
import com.burgosanchez.tcc.venta.jpa.CondicionEventoFacade;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author TID01
 */
@ManagedBean(name = "condicionEventoBeanMB")
@ViewScoped
public class CondicionEventoBean implements Serializable{

    @Inject
    private CondicionEventoFacade codEveFacade;

    private CondicionEvento condicion;
    List<CondicionEvento> condiciones;
    private CondicionEventoPK condicionPK;
    private String evento;
    private String codCond;

    public CondicionEventoBean() {
        condicion = new CondicionEvento();
        condicionPK = new CondicionEventoPK();
    }

    public CondicionEvento getCondicion() {
        return condicion;
    }

    public void setCondicion(CondicionEvento condicion) {
        this.condicion = condicion;
    }

    public List<CondicionEvento> getCondiciones() {
        return condiciones;
    }

    public void setCondiciones(List<CondicionEvento> condiciones) {
        this.condiciones = condiciones;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getCodCond() {
        return codCond;
    }

    public void setCodCond(String codCond) {
        this.codCond = codCond;
    }
    
    public void obtenerCondicionEve(String cod){
        condiciones = codEveFacade.obtenerCondicionEven(cod);
    }

    public void insertar() {
        condicionPK.setCodEvento(evento);
        condicionPK.setCodCondicion(codCond);
        condicionPK.setCodConEve(String.valueOf(codEveFacade.obtenerSecuenciaVal()));
        condicion.setCondicionEventoPK(condicionPK);
        codEveFacade.create(condicion);
        condicion = new CondicionEvento();
        condicionPK.setCodEvento(evento);
    }

    public void modificar() {
        codEveFacade.edit(condicion);
        condicion = new CondicionEvento();
    }

    public void eliminar() {
        codEveFacade.remove(condicion);
        condicion = new CondicionEvento();
    }

}
