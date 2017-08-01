/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burgosanchez.tcc.venta.web.bean;

/*Importacion Paquetes de telefono*/
import com.burgosanchez.tcc.venta.ejb.TelPersona;
import com.burgosanchez.tcc.venta.jpa.TelPersonaFacade;

/*Importacion Paquetes de Direccion*/
import com.burgosanchez.tcc.venta.ejb.DireccionPersona;
import com.burgosanchez.tcc.venta.jpa.DireccionPersonaFacade;

/*Importacion Paquetes de Identificacion*/
import com.burgosanchez.tcc.venta.ejb.IdentPersona;
import com.burgosanchez.tcc.venta.jpa.IdentPersonaFacade;

/*Importacion Paquetes de telefono*/

 /*Importacion Paquetes de telefono*/
import com.burgosanchez.tcc.venta.ejb.Persona;
import com.burgosanchez.tcc.venta.ejb.Proveedor;
import com.burgosanchez.tcc.venta.web.bean.PersonaBean;
import com.burgosanchez.tcc.venta.jpa.PersonaFacade;
import com.burgosanchez.tcc.venta.jpa.ProveedorFacade;
import com.burgosanchez.tcc.venta.web.common.MsgUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author TID01
 */
@ManagedBean(name = "proveedorBeanMB")
@ViewScoped
public class ProveedorBean implements Serializable {

    /*CREACION DE OBJETOS*/
    @Inject
    private ProveedorFacade provFacade;

    private PersonaBean personaBean;
    private Persona persona;

    private PersonaFacade personaFacade;

    private TelPersona telefono;
    private TelPersonaFacade telefonoFacade;

    private DireccionPersona direccion;
    private DireccionPersonaFacade direccionFacade;

    private IdentPersona identificacion;
    private IdentPersonaFacade identFacade;

    private Proveedor proveedor;
    List<Proveedor> proveedores;

    /*CREACION DE VARIABLES GLOBALES*/
    private String re;
    private boolean tipop = false;

    /*CONSTRUCTOR*/
    public ProveedorBean() {
        persona = new Persona();
        telefono = new TelPersona();
        direccion = new DireccionPersona();
        identificacion = new IdentPersona();
        proveedor = new Proveedor();
    }

    public boolean getTipop() {
        return tipop;
    }

    public void setTipop(boolean tipop) {
        this.tipop = tipop;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public List<Proveedor> getProveedores() {
        //if (re.isEmpty()) {
        proveedores = provFacade.findAll();
        //}
        return proveedores;
    }

    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    public String getRe() {
        return re;
    }

    public void setRe(String re) {
        this.re = re;
    }

    public List<Proveedor> completeProveedor(String query) {
        List<Proveedor> allIdent = provFacade.findAll();
        List<Proveedor> filteredIdent = new ArrayList<>();

        for (int i = 0; i < allIdent.size(); i++) {
            Proveedor ide = allIdent.get(i);
            if (ide.getCodPersona().getNombre().toLowerCase().startsWith(query)) {
                filteredIdent.add(ide);
            }
        }

        return filteredIdent;
    }

    public void obtenerProveedor(Persona pe) {
        proveedores = null;
        re = pe.getCodPersona().toLowerCase();
        //re = "%"+re+"%";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("per", "%" + re + "%");
        proveedores = provFacade.obtenerProveedor(re);
    }

    public void modificar() {
        provFacade.edit(proveedor);
        proveedor = new Proveedor();
    }

    public void eliminar() {
        provFacade.remove(proveedor);
        proveedor = new Proveedor();
    }

    public boolean Tipop(String tipop) {
        if ("J".equals(tipop)) {
            return false;
        }

        return true;
    }

    public void insertarProveedor() {
        
        /****** CREACION DE LA PERSONA ********/
        String nombre = persona.getNombre();
        if (persona.getNombre() != null) {

            Integer codper = personaFacade.obtenerSecuenciaVal();
            persona.setCodPersona(String.valueOf(codper));
            personaFacade.create(persona);
        }
        
        /* ***** CREACION DEL TELEFONO DE LA PERSONA ********/ 
        Integer codtel = telefonoFacade.obtenerSecuenciaVal();
        telefono.setCodPersona(persona);
        telefono.setCodTelefono(String.valueOf(codtel));
        telefonoFacade.create(telefono);

        /****** CREACION DE LA DIRECCION ********/
        Integer coddir = direccionFacade.obtenerSecuenciaVal();
        direccion.setCodPersona(persona);
        direccion.setCodDireccion(String.valueOf(coddir));
        direccionFacade.create(direccion);

        /****** CREACION DE LA IDENTIFICACION ********/
        Integer codident = identFacade.obtenerSecuenciaVal();
        identificacion.setCodIdentificacion(String.valueOf(codident));
        identificacion.setCodPersona(persona);
        identFacade.create(identificacion);

        /******CREACION DEL PROVEEDOR********/
        proveedor.setCodPersona(persona);
        proveedor.setCodProveedor(String.valueOf(provFacade.obtenerSecuenciaVal()));
        provFacade.create(proveedor);

        /******MENSAJE DE CONFIRMACION********/
        MsgUtil.addInfoMessage("Se creó exitosamente el proveedor");
        blanqueo();

        }
    public void blanqueo()
    {
        persona = new Persona(null);
        telefono = new TelPersona(null);
        direccion = new DireccionPersona(null);
        identificacion = new IdentPersona(null);
        proveedor = new Proveedor(null);    
    }
}
