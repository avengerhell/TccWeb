/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burgosanchez.tcc.venta.web.bean;

import com.burgosanchez.tcc.venta.ejb.LocalidadEvento;
import com.burgosanchez.tcc.venta.ejb.LocalidadEventoPK;
import com.burgosanchez.tcc.venta.jpa.LocalidadEventoFacade;
import com.burgosanchez.tcc.venta.web.common.MsgUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author TID01
 */
@ManagedBean(name = "localidadEventoBeanMB")
@ViewScoped
public class LocalidadEventoBean implements Serializable{

    @Inject
    private LocalidadEventoFacade localidadFacade;

    private LocalidadEvento localidad;
    List<LocalidadEvento> localidades;
    private LocalidadEventoPK localPK;
    private String event;
    private String codLocal;

    public LocalidadEventoBean() {
        localPK = new LocalidadEventoPK();
        localidad = new LocalidadEvento();
    }

    public LocalidadEvento getLocalidad() {
        return localidad;
    }

    public void setLocalidad(LocalidadEvento localidad) {
        this.localidad = localidad;
    }

    public List<LocalidadEvento> getLocalidades() {
        return localidades;
    }

    public void setLocalidades(List<LocalidadEvento> localidades) {
        this.localidades = localidades;
    }

    public LocalidadEventoPK getLocalPK() {
        return localPK;
    }

    public void setLocalPK(LocalidadEventoPK localPK) {
        this.localPK = localPK;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getCodLocal() {
        return codLocal;
    }

    public void setCodLocal(String codLocal) {
        this.codLocal = codLocal;
    }
    
    public void obtenerLocalidadEvento(String cod){
        localidades = localidadFacade.obtenerLocal(cod);
    }

    public void insertar() {
        localPK.setCodEvento(event);
        localPK.setCodLocalidad(codLocal);
        localPK.setCodLocEve(String.valueOf(localidadFacade.obtenerSecuenciaVal()));
        localidad.setLocalidadEventoPK(localPK);
        localidadFacade.create(localidad);
        localidad = new LocalidadEvento();
        MsgUtil.addInfoMessage("Se creó exitosamente la localidad para el evento");
    }

    public void modificar() {
        localidadFacade.edit(localidad);
        localidad = new LocalidadEvento();
    }

    public void eliminar() {
        localidadFacade.remove(localidad);
        localidad = new LocalidadEvento();
    }

}
