/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burgosanchez.tcc.venta.web.common;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author TID01
 */
public class MsgUtil {

    public static void addInfoMessage(String str) {
        FacesContext context = FacesContext.getCurrentInstance();
        //ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
        //String message = bundle.getString(str);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, str, ""));
    }

    public static void addInfoMessage(String summary, String detail) {
        FacesContext context = FacesContext.getCurrentInstance();
        //ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
        //String message = bundle.getString(summary);
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, null, detail));
    }

    public static void addInfoMessage(String str, Object... args) {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
        String message = bundle.getString(str);
        if (args != null) {
            message = MessageFormat.format(message, args);
        }
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
    }

    public static void addInfoMessageWithoutKey(String summary, String detail) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
    }

    public static void addMessageWithoutKey(FacesMessage.Severity fms, String summary, String detail) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(fms, summary, detail));
    }
}
