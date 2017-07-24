/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burgosanchez.tcc.venta.web.bean;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author Diego
 */
@ManagedBean(name = "validacionesMB")
public class Validaciones {
    
    public String Devuelve()
    {
   
    PersonaBean nuevo = new PersonaBean();
     String nombre=nuevo.getNom();
    String apellido="";
    String NombreyApellido = nombre + apellido;
    return NombreyApellido;
    
    }
    
}
