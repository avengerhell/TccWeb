/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodigoDiego;

import com.burgosanchez.tcc.venta.ejb.TipoEvento;
import com.burgosanchez.tcc.venta.web.bean.TipoEventoBean;
import com.burgosanchez.tcc.venta.web.common.MsgUtil;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Diego
 */
@ManagedBean(name = "bsLayerMB")

@ViewScoped
public class capaNegocio implements Serializable {
    @ManagedProperty(value="#{tipoEventoBeanMB")
    private TipoEvento evento;

    public void setTipo(TipoEvento evento) {
        this.evento = evento;
    }
    public TipoEvento getTipo()
    {
        return evento;
    }    
     
    public capaNegocio() {
    }

    public void InsertarTipoEvento() throws SQLException {
        String message = evento.getDescripcion();
        if (message.isEmpty()) {

            MsgUtil.addInfoMessage("El campo tipo de evento no debe estar vacio");

        } else {
            TipoEventoBean evento1 = new TipoEventoBean();
            evento1.insertar();
            MsgUtil.addInfoMessage("Tipo de evento creado Correctamente");
            vaciarForm();
        }
    }

    public void vaciarForm() {
        evento.setDescripcion(null);

    }

    public void onRowEdit(RowEditEvent event) {
        TipoEventoBean tipo = new TipoEventoBean();
        tipo.modificar();
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
