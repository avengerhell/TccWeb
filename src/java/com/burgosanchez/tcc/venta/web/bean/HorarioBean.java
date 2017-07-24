/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burgosanchez.tcc.venta.web.bean;

import com.burgosanchez.tcc.venta.ejb.Horario;
import com.burgosanchez.tcc.venta.ejb.HorarioPK;
import com.burgosanchez.tcc.venta.jpa.HorarioFacade;
import com.burgosanchez.tcc.venta.web.common.MsgUtil;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author TID01
 */
@ManagedBean(name = "horarioBeanMB")
@ViewScoped
public class HorarioBean implements Serializable {

    @Inject
    private HorarioFacade horarioFacade;

    private Horario hora;
    List<Horario> horas;
    private String event;
    private String codHora;
    private HorarioPK horaPK;

    private Date hora_desde;
    private Date hora_hasta;
    private Date fecha_desde;
    private Date fecha_hasta;
    private String dia_desde;
    private String dia_hasta;
    private String periodo;

    public HorarioBean() {
        hora = new Horario();
        horaPK = new HorarioPK();
    }

    public Horario getHora() {
        return hora;
    }

    public void setHora(Horario hora) {
        this.hora = hora;
    }

    public List<Horario> getHoras() {
        return horas;
    }

    public void setHoras(List<Horario> horas) {
        this.horas = horas;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getCodHora() {
        return codHora;
    }

    public void setCodHora(String codHora) {
        this.codHora = codHora;
    }

    public HorarioPK getHoraPK() {
        return horaPK;
    }

    public void setHoraPK(HorarioPK horaPK) {
        this.horaPK = horaPK;
    }

    public Date getHora_desde() {
        return hora_desde;
    }

    public void setHora_desde(Date hora_desde) {
        this.hora_desde = hora_desde;
    }

    public Date getHora_hasta() {
        return hora_hasta;
    }

    public void setHora_hasta(Date hora_hasta) {
        this.hora_hasta = hora_hasta;
    }

    public Date getFecha_desde() {
        return fecha_desde;
    }

    public void setFecha_desde(Date fecha_desde) {
        this.fecha_desde = fecha_desde;
    }

    public Date getFecha_hasta() {
        return fecha_hasta;
    }

    public void setFecha_hasta(Date fecha_hasta) {
        this.fecha_hasta = fecha_hasta;
    }

    public String getDia_desde() {
        return dia_desde;
    }

    public void setDia_desde(String dia_desde) {
        this.dia_desde = dia_desde;
    }

    public String getDia_hasta() {
        return dia_hasta;
    }

    public void setDia_hasta(String dia_hasta) {
        this.dia_hasta = dia_hasta;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    
    public void obtenerHorarios(String cod){
        horas = horarioFacade.obtenerHorarioEven(cod);
    }

    public void insertar() {
        horaPK.setCodEvento(event);
        horaPK.setCodHorario(codHora);
        codHora = String.valueOf(horarioFacade.obtenerSecuenciaVal());
        hora.setHorarioPK(horaPK);
        horarioFacade.create(hora);
        hora = new Horario();
    }

    public void modificar() {
        horarioFacade.edit(hora);
        hora = new Horario();
    }

    public void eliminar() {
        horarioFacade.remove(hora);
        hora = new Horario();
    }

    public void horarios() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatH = new SimpleDateFormat("HH:mm");
            horarioFacade.generaHorarios(event,
                    String.valueOf(formatH.format(hora_desde)),
                    String.valueOf(formatH.format(hora_hasta)),
                    String.valueOf(format.format(fecha_desde)),
                    String.valueOf(format.format(fecha_hasta)),
                    Integer.valueOf(dia_desde),
                    Integer.valueOf(dia_hasta),
                    Integer.valueOf(periodo));
            MsgUtil.addInfoMessage("Se crearon los horarios para el evento seleccionado");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
