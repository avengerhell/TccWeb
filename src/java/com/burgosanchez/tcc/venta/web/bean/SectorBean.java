/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burgosanchez.tcc.venta.web.bean;

import com.burgosanchez.tcc.venta.ejb.EventoCab;
import com.burgosanchez.tcc.venta.ejb.Sector;
import com.burgosanchez.tcc.venta.ejb.SectorPK;
import com.burgosanchez.tcc.venta.jpa.SectorFacade;
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
@ManagedBean(name = "sectorBeanMB")
@ViewScoped
public class SectorBean implements Serializable{

    @Inject
    private SectorFacade sectorFacade;

    private Sector sector;
    List<Sector> sectores;
    private SectorPK sectorPK;
    private String event;
    private EventoCab evento;
    private Boolean ilimitado = false;

    public SectorBean() {
        sector = new Sector();
        sectorPK = new SectorPK();
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public List<Sector> getSectores() {
        //sectores = (List<Sector>) sectorFacade.obtenerSectorEven(event);
        return sectores;
    }

    public void setSectores(List<Sector> sectores) {
        this.sectores = sectores;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public EventoCab getEvento() {
        return evento;
    }

    public void setEvento(EventoCab evento) {
        this.evento = evento;
    }

    public Boolean getIlimitado() {
        return ilimitado;
    }

    public void setIlimitado(Boolean ilimitado) {
        this.ilimitado = ilimitado;
    }

    public void insertar() {
        if (ilimitado) {
            sector.setIlimitado("S");
        } else {
            sector.setIlimitado("N");
        }
        sectorPK.setCodEvento(event);
        sectorPK.setCodSector(String.valueOf(sectorFacade.obtenerSecuenciaVal()));
        sector.setSectorPK(sectorPK);
        sectorFacade.create(sector);
        sector = new Sector();
        MsgUtil.addInfoMessage("Se creó exitosamente el sector");
    }

    public void modificar() {
        sectorFacade.edit(sector);
        sector = new Sector();
    }

    public void eliminar() {
        sectorFacade.remove(sector);
        sector = new Sector();
    }
    
    public void obtenerSectores(String cod){
        sectores = sectorFacade.obtenerSectorEven(cod);
    }

}
