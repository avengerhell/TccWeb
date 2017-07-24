/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burgosanchez.tcc.venta.web.bean;

import com.burgosanchez.tcc.venta.ejb.Usuario;
import com.burgosanchez.tcc.venta.jpa.UsuarioFacade;
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
    private UsuarioFacade usuarioFacade;
    private Usuario user;
    List<Usuario> users;

    private String usuarioNombre;
    
    public UsuarioBean() {
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

    public void setUsers(List<Usuario> users) {
        this.users = users;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    
    
    
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

    //logout event, invalidate session
    public String logout() {
        HttpSession session = SessionBean.getSession();
        session.invalidate();
        return "login";
    }
    
    public void insertar() throws Exception {
        try {
            usuarioFacade.create(user);
            //log.info("El usuario:" + SessionBean.getUserName() + " agrego: "+ user.getUsuario()+","+user.getNombre());
        } catch (Exception ex) {
            //log.error("Ocurrio el sgte. error: ", ex);
        }

        user = new Usuario();
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

    

}
