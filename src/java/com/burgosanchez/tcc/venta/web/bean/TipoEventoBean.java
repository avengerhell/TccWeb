/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burgosanchez.tcc.venta.web.bean;

import com.burgosanchez.tcc.venta.ejb.TipoEvento;
import com.burgosanchez.tcc.venta.jpa.TipoEventoFacade;
import com.burgosanchez.tcc.venta.web.common.MsgUtil;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author TID01
 */
@ManagedBean(name = "tipoEventoBeanMB")
//@ViewScoped
@ApplicationScoped
public class TipoEventoBean implements Serializable {

    @Inject
    private TipoEventoFacade tipoFacade;
    private TipoEvento tipo;
    List<TipoEvento> tipos;

    public TipoEventoBean() {
        tipo = new TipoEvento();
    }

    public TipoEvento getTipo() {
        return tipo;
    }
      
    public void setTipo(TipoEvento tipo) {
        this.tipo = tipo;
    }

    public List<TipoEvento> getTipos() {
        tipos = tipoFacade.findAll();
        return tipos;
    }

    public void setTipos(List<TipoEvento> tipos) {
        this.tipos = tipos;
    }

    public void PreInsertar() {
        String message = getTipo().getDescripcion();
        if (message.isEmpty()) {

            MsgUtil.addInfoMessage("El campo tipo de evento no debe estar vacio");

        } else {
            insertar();
            MsgUtil.addInfoMessage("Tipo de evento creado Correctamente");
            vaciarForm();
        }
    }

    public void insertar() {

        tipo.setCodTipo(String.valueOf(tipoFacade.obtenerSecuenciaVal()));
        tipoFacade.create(tipo);
        tipo = new TipoEvento();

    }

    public void modificar() {
        tipoFacade.edit(tipo);
        tipo = new TipoEvento();
    }

    public void eliminar() {
        tipoFacade.remove(tipo);
        tipo = new TipoEvento();
    }

    public void vaciarForm() {
        getTipo().setDescripcion(null);

    }
    
    public void onRowEdit(RowEditEvent event) {
        modificar();
        FacesMessage msg = new FacesMessage("Registro editado", ((TipoEvento) event.getObject()).getCodTipo());
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edicion cancelada", ((TipoEvento) event.getObject()).getDescripcion());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
   
}
