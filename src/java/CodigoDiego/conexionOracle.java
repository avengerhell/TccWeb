/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodigoDiego;

import com.burgosanchez.tcc.venta.ejb.TipoEvento;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.sql.*;
import java.util.List;
import java.util.Locale;
import static jdk.nashorn.internal.objects.NativeFunction.call;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author Diego
 * Conexion a base de datos
 */
  
   
 public class conexionOracle        
  { 
     /*Valores para la conexion*/
    String usuario ="tcc";
    String password ="tcc";
    String driver ="jdbc:oracle:thin";
    String ip ="@192.168.1.4";
    String puerto ="1521:xe";
    Connection conn;
    String cadenaConexion;
    List <TipoEvento> list;
     public conexionOracle()
     {
        this.cadenaConexion = (driver+":" + ip +":" +puerto);
        Locale.setDefault(new Locale("es","ES"));
        
         try
         {
             Class.forName("oracle.jdbc.OracleDriver").newInstance();
             conn = DriverManager.getConnection(cadenaConexion,usuario,password);
             
             System.out.println("Conexion a Base de Datos " + usuario + " Ok");
         }
         catch (Exception exc)
         {
             System.out.println("Error al tratar de abrir la base de Datos" + usuario + " : " + exc);
             System.out.println( cadenaConexion);
         }
     /***********************************************************************************/
     }
     /*FUNCIONES DE CONEXION*/
     public Connection getConexion()
     {
        return conn;
    }

    public Connection CerrarConexion() throws SQLException {
        conn.close();
        conn = null;
        return conn;
    }
   public String insertarTipoEvento(String nombreEvento) throws SQLException
    {   
     String mensaje="";   
     try
     {    
    conexionOracle conexion = new conexionOracle();
    conexion.getConexion();
    CallableStatement proce = conn.prepareCall("{call tcc.pkg_adm.sp_alta_tipo_evento(?,?)}");
    proce.setString(1,nombreEvento);
    proce.registerOutParameter(2, Types.VARCHAR);
    proce.execute();    
    //final ResultSet rs = proce.getResultSet();
    
    mensaje = proce.getNString(2);
    }
    catch (Exception e)
    { 
        conn.rollback();  
           
    }
    finally
    {  
        conn.close();  
    }
        
    return mensaje; 
  }
 
   public List<TipoEvento> traeTipoeventos(String nombreEvento) throws SQLException
   {
        List<TipoEvento> element = null; 
       try
       {
           TipoEvento evento = new TipoEvento();
          
           conexionOracle conexion = new conexionOracle();
           conexion.getConexion();
           CallableStatement proce = conn.prepareCall("{call pkg_adm.sp_trae_tipo_evento(?,?);}");
           proce.setString(1,nombreEvento);
           proce.registerOutParameter(2, OracleTypes.CURSOR);
           proce.execute();
           final ResultSet rs = proce.getResultSet();
           while (rs.next())
           {			
                element.add(evento);
           }
       }
       catch (Exception exc)
       {}
        finally
         {  
        conn.close();  
    }
        return element;
    }
  
 }
     
    
    
 
   

