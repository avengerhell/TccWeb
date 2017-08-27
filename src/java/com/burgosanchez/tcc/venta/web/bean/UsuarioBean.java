/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burgosanchez.tcc.venta.web.bean;

import com.burgosanchez.tcc.venta.ejb.Persona;
import com.burgosanchez.tcc.venta.ejb.Usuario;
import com.burgosanchez.tcc.venta.ejb.UsuarioPK;
import com.burgosanchez.tcc.venta.jpa.PersonaFacade;
import com.burgosanchez.tcc.venta.jpa.UsuarioFacade;
import com.burgosanchez.tcc.venta.jpa.BasicosFacade;
import com.burgosanchez.tcc.venta.web.common.MsgUtil;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TID01
 */
@ManagedBean(name = "usuarioBeanMB")
@ViewScoped
public class UsuarioBean implements Serializable {

    //private static final Logger log = Logger.getLogger(UsuarioBean.class);
    @Inject
    PersonaFacade personaFacade;
    @Inject
    UsuarioFacade usuarioFacade;
    @Inject
    BasicosFacade basicosFacade;

    private Persona persona;
    private Usuario user;
    private List<Usuario> users;
    private List<Persona> personas;
    

    
    private String usuarioNombre;

    private String text;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    private String usuario;

    public UsuarioBean() {
        persona = new Persona();
        user = new Usuario();
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public List<Usuario> getUsers() {
        users = usuarioFacade.findAll();
        return users;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public void setUsers(List<Usuario> users) {
        this.users = users;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }
    
    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
        
    }
    
     public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
//<editor-fold>

//region Sesiones
    public String validateUsernamePassword() {
        boolean valid;
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("user", "%" + usuarioNombre.toLowerCase() + "%");
        parameters.put("pass", "%" + user.getPassword().toLowerCase() + "%");

        users = usuarioFacade.obtenerUsuario(parameters);
        valid = users != null;
        if (valid) {
            HttpSession session = SessionBean.getSession();
            session.setAttribute("username", user);
            return "welcomeFrame";

        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Usuario y contraseña incorrecta",
                            "Por favor ingrese el usuario y contraseña correcta"));
            return "login";
        }
    }

    public String logout() {
        HttpSession session = SessionBean.getSession();
        session.invalidate();
        return "login";
    }
//endregion 

//region ADMINISTRACION
    public void insertar() throws Exception {
        if (persona.getNombre() != null) {
            Integer codper = personaFacade.obtenerSecuenciaVal();
            String nomape = getNombreApellido();
            persona.setCodPersona(String.valueOf(codper));
            //user.setNomUser(String.valueOf(nomape));
            //user.getPersona().getCodPersona();
            user.setPersona(persona);
            UsuarioPK uPK = new UsuarioPK();
            uPK.setCodPersona(String.valueOf(codper));
            uPK.setCodUsuario(user.getNomUser());
            user.setUsuarioPK(uPK);
            personaFacade.create(persona);
            usuarioFacade.create(user);
            MsgUtil.addInfoMessage("Se creó exitosamente el usuario");
            limpiarCampos();

        } else {
            MsgUtil.addInfoMessage("Error en al creación del usuario verifique los campos");
        }

    }

    public void modificar() throws Exception {
        try {
            usuarioFacade.edit(user);
            //log.info("El usuario:" + SessionBean.getUserName() + " modifico: "+ user.getUsuario()+","+user.getNombre());
        } catch (Exception ex) {
            //log.error("Ocurrio el sgte. error: ", ex);
        }
        user = new Usuario();
    }

    public void eliminar() {
        try {
            usuarioFacade.remove(user);
            //log.info("El usuario:" + SessionBean.getUserName() + " elimino: "+ user.getUsuario()+","+user.getNombre());
        } catch (Exception ex) {
            //log.error("Ocurrio el sgte. error: ", ex);
        }
        user = new Usuario();
    }
    //enregion

    //region Funciones
    public String getNombreApellido() {

        String nombre = persona.getNombre();
        String apellido = persona.getApellido();
        String NombreyApellido = nombre + " " + apellido;
        return NombreyApellido;

    }

    public void limpiarCampos() {
        persona = new Persona(null);
        user = new Usuario(null);
    }
//endregion

    public void buscarUsuario() {

        if (usuario != null) {
            try {
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("user", "%" + usuario.toLowerCase() + "%");
                users = usuarioFacade.obtenerUsuario2(parameters);
            } catch (Exception ex) {

            }
        } else {
            users = null;
        }
    }

    public void modificar2() throws Exception {
        String a = user.getPersona().getCodPersona();
        String b = user.getUsuarioPK().toString();
        UsuarioPK uPK = new UsuarioPK();
        uPK.setCodUsuario(user.getNomUser());
        user.setUsuarioPK(uPK);
        modificar();
        personaFacade.edit(persona);
    }

    public void obtenerParametroPersona(Usuario us) {

        String cod = us.getUsuarioPK().getCodUsuario();
        personas  = personaFacade.obtenerPersonaXCodCliente(cod);
        if(personas ==null){
           personas = null;
        }
}
    
}
