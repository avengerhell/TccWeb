/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burgosanchez.tcc.venta.web.bean;

import com.burgosanchez.tcc.venta.ejb.Cliente;
import com.burgosanchez.tcc.venta.ejb.DireccionPersona;
import com.burgosanchez.tcc.venta.ejb.IdentPersona;
import com.burgosanchez.tcc.venta.ejb.Persona;
import com.burgosanchez.tcc.venta.ejb.Proveedor;
import com.burgosanchez.tcc.venta.ejb.TelPersona;
import com.burgosanchez.tcc.venta.jpa.ClienteFacade;
import com.burgosanchez.tcc.venta.jpa.DireccionPersonaFacade;
import com.burgosanchez.tcc.venta.jpa.IdentPersonaFacade;
import com.burgosanchez.tcc.venta.jpa.PersonaFacade;
import com.burgosanchez.tcc.venta.jpa.ProveedorFacade;
import com.burgosanchez.tcc.venta.jpa.TelPersonaFacade;
import com.burgosanchez.tcc.venta.web.common.MsgUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author TID01
 */
@ManagedBean(name = "personaBeanMB")
@ViewScoped
public class PersonaBean implements Serializable {

    @Inject
    private PersonaFacade personaFacade;
    @Inject
    private ClienteFacade clienteFacade;
    @Inject
    private TelPersonaFacade telefonoFacade;
    @Inject
    private DireccionPersonaFacade direccionFacade;
    @Inject
    private IdentPersonaFacade identFacade;
    @Inject
    private ProveedorFacade provFacade;

    private Boolean esProv = false;
    public MsgUtil mensaje;
    private Persona persona;
    private Cliente cliente;
    private TelPersona telefono;
    private DireccionPersona direccion;
    private IdentPersona identificacion;
    private Proveedor proveedor;
    private String docu;
    private String nom;

    private List<Persona> personas;

    public PersonaBean() {
        persona = new Persona();
        cliente = new Cliente();
        telefono = new TelPersona();
        direccion = new DireccionPersona();
        identificacion = new IdentPersona();
        proveedor = new Proveedor();
    }

    public List<Persona> getPersonas() {
        if ((nom == null || nom.isEmpty()) && (docu == null || docu.isEmpty())) {
            personas = personaFacade.findAll();
        }
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TelPersona getTelefono() {
        return telefono;
    }

    public void setTelefono(TelPersona telefono) {
        this.telefono = telefono;
    }

    public IdentPersona getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(IdentPersona identificacion) {
        this.identificacion = identificacion;
    }

    public DireccionPersona getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionPersona direccion) {
        this.direccion = direccion;
    }

    public Boolean getEsProv() {
        return esProv;
    }

    public void setEsProv(Boolean esProv) {
        this.esProv = esProv;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public String getDocu() {
        return docu;
    }

    public void setDocu(String docu) {
        this.docu = docu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void blanqueo() {
        persona = new Persona();
        cliente = new Cliente();
        telefono = new TelPersona();
        direccion = new DireccionPersona();
        identificacion = new IdentPersona();
        proveedor = new Proveedor();
    }

    public List<Persona> completePersona(String query) {
        List<Persona> allPer = personaFacade.findAll();
        List<Persona> filteredPerson = new ArrayList<>();

        for (int i = 0; i < allPer.size(); i++) {
            Persona per = allPer.get(i);
            if (per.getNombre().toLowerCase().startsWith(query)) {
                filteredPerson.add(per);
            }
        }

        return filteredPerson;
    }

    public void obtenerParametroPersona() {
        if (nom != null || docu != null) {
            if (nom == null) {
                nom = "";
            }
            if (docu == null) {
                docu = "";
            }
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("docu", "%" + docu.toLowerCase() + "%");
            parameters.put("nom", "%" + nom.toLowerCase() + "%");

            personas = personaFacade.obtenerPersona(parameters);
        } else {
            personas = null;
        }
    }

    public String getNombreApellido() {

        PersonaBean nuevo = new PersonaBean();
        String nombre = nuevo.getNom();
        String apellido = "";
        String NombreyApellido = nombre + apellido;
        return NombreyApellido;

    }

    public void insertar() {
        /*
         * ***** CREACION DE LA PERSONA *******
         */
        if (persona.getNombre() != null) {

            Integer codper = personaFacade.obtenerSecuenciaVal();
            persona.setCodPersona(String.valueOf(codper));
            personaFacade.create(persona);
        }
        /*ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
Validator validator = (Validator) factory.getValidator();

Set<ConstraintViolation<Persona>> constraintViolations = validator.validate(persona);

if (constraintViolations.size() > 0 ) {
System.out.println("Constraint Violations occurred..");
for (ConstraintViolation<Persona> contraints : constraintViolations) {
System.out.println(contraints.getRootBeanClass().getSimpleName()+
"." + contraints.getPropertyPath() + " " + contraints.getMessage());
  }
}        */

        /**
         * ***** CREACION DEL TELEFONO DE LA PERSONA *******
         */
        Integer codtel = telefonoFacade.obtenerSecuenciaVal();
        telefono.setCodPersona(persona);
        telefono.setCodTelefono(String.valueOf(codtel));
        telefonoFacade.create(telefono);

        /**
         * ***** CREACION DE LA DIRECCION *******
         */
        Integer coddir = direccionFacade.obtenerSecuenciaVal();
        direccion.setCodPersona(persona);
        direccion.setCodDireccion(String.valueOf(coddir));
        direccionFacade.create(direccion);

        /**
         * ***** CREACION DE LA IDENTIFICACION *******
         */
        Integer codident = identFacade.obtenerSecuenciaVal();
        identificacion.setCodIdentificacion(String.valueOf(codident));
        identificacion.setCodPersona(persona);
        identFacade.create(identificacion);

        /**
         * ***** CREACION DEL CLIENTE *******
         */
        Integer codcli = clienteFacade.obtenerSecuenciaVal();
        cliente.setCodPersona(persona);
        cliente.setCodCliente(String.valueOf(codcli));
        clienteFacade.create(cliente);

        if (esProv) {
            proveedor.setCodPersona(persona);
            proveedor.setCodProveedor(String.valueOf(provFacade.obtenerSecuenciaVal()));
            provFacade.create(proveedor);
        }

        /**
         * ***** VOLVER A BLANQUEAR LAS VARIABLES *******
         */
        MsgUtil.addInfoMessage("Se creó exitosamente la persona");
        blanqueo();

        /*FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,
        "Insertado Correctamente",
        ""));*/    }

}
