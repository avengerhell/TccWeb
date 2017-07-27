/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burgosanchez.tcc.venta.web.bean;


import com.burgosanchez.tcc.venta.ejb.EventoCab;
import com.burgosanchez.tcc.venta.ejb.Horario;
import com.burgosanchez.tcc.venta.ejb.Sector;
import com.burgosanchez.tcc.venta.jpa.EventoCabFacade;
import com.burgosanchez.tcc.venta.web.common.MsgUtil;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author TID01
 */
@ManagedBean(name = "eventoCabBeanMB")
@ViewScoped
public class EventoCabBean implements Serializable {

    @Inject
    private EventoCabFacade eventoFacade;

    private EventoCab evento;
    List<EventoCab> eventos;
    private String nombre;
    private String proveedor;

    public EventoCabBean() {
        evento = new EventoCab();
    }

    public EventoCab getEvento() {
        return evento;
    }

    public void setEvento(EventoCab evento) {
        this.evento = evento;
    }

    public List<EventoCab> getEventos() {
        if (eventos == null) {
            eventos = eventoFacade.findAll();
        }
        return eventos;
    }

    public void setEventos(List<EventoCab> eventos) {
        this.eventos = eventos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public List<EventoCab> completeEvent(String query) {
        List<EventoCab> allIdent = eventoFacade.findAll();
        List<EventoCab> filteredIdent = new ArrayList<>();

        for (int i = 0; i < allIdent.size(); i++) {
            EventoCab ide = allIdent.get(i);
            if (ide.getNombre().toLowerCase().startsWith(query)) {
                filteredIdent.add(ide);
            }
        }

        return filteredIdent;
    }

    public void insertar() {
        evento.setCodEvento(String.valueOf(eventoFacade.obtenerSecuenciaVal()));
        eventoFacade.create(evento);
        MsgUtil.addInfoMessage("Se creó exitosamente el evento");
        //evento = new EventoCab();
    }

    public void modificar() {
        eventoFacade.edit(evento);
        evento = new EventoCab();
    }

    public void eliminar() {
        eventoFacade.remove(evento);
        evento = new EventoCab();
    }

    public void obtenerParametroEvento() {
        if (nombre != null || proveedor != null) {
            if (nombre == null) {
                nombre = "";
            }
            if (proveedor == null) {
                proveedor = "";
            }
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("prov", "%" + proveedor.toLowerCase() + "%");
            parameters.put("nom", "%" + nombre.toLowerCase() + "%");

            eventos = eventoFacade.obtenerEvento(parameters);
        } else {
            eventos = null;
        }
    }
   /*  public String InsertarTipoEvento(String nameEvento) throws SQLException
    {
        conexionOracle op = new conexionOracle();
        mensaje= op.insertarTipoEvento(nameEvento);
        return mensaje;
    }
    */

}
