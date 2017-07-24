/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burgosanchez.tcc.venta.web.bean;

import com.burgosanchez.tcc.venta.ejb.Persona;
import com.burgosanchez.tcc.venta.ejb.Proveedor;
import com.burgosanchez.tcc.venta.jpa.ProveedorFacade;
import java.io.Serializable;
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
@ManagedBean(name = "proveedorBeanMB")
@ViewScoped
public class ProveedorBean implements Serializable {

    @Inject
    private ProveedorFacade provFacade;

    private Proveedor proveedor;
    List<Proveedor> proveedores;
    private String re;

    public ProveedorBean() {
        proveedor = new Proveedor();
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

}
