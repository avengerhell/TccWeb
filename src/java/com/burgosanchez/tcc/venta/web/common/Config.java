/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.burgosanchez.tcc.venta.web.common;

/**
 *
 * @author TID01
 */import java.io.Serializable;
import java.util.TimeZone;
import java.util.logging.Logger;
import javax.inject.Inject;


/**
 *
 * @author tid
 */
@javax.inject.Named(value = "config")
@javax.enterprise.context.SessionScoped
public class Config implements Serializable {
//    private static final Logger log = LoggerFactory.getLogger(Config.class);
    
    public TimeZone timeZoneDefault() {
        return TimeZone.getDefault();
    }
    
  
}
