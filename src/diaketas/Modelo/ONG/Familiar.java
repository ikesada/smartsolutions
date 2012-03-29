/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.ONG;

import com.mysql.jdbc.Statement;
import diaketas.ConexionBD;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kesada
 */
public class Familiar {
    /**
     * 
     */
    public String Nombre_Apellidos;
    /**
     * 
     */
    public Date Fecha_Nacimiento;
    /**
     * 
     */
    public String Ocupacion;
    /**
     * 
     */
    public int Cod_Familiar;
    
    /*Parentesco del familiar con respecto al Beneficiario*/
    /**
     * 
     */
    public Parentesco parentesco;
    
    
    ConexionBD con = new ConexionBD();

    /*------------------------------Constructores------------------------------*/
    /**
     * 
     * @param Nombre_Apellidos
     * @param Fecha_Nacimiento
     * @param Ocupacion
     */
    public Familiar(String Nombre_Apellidos, Date Fecha_Nacimiento, String Ocupacion) {
        this.Nombre_Apellidos = Nombre_Apellidos;
        this.Fecha_Nacimiento = Fecha_Nacimiento;
        this.Ocupacion = Ocupacion;
        this.Cod_Familiar = 0;
    }
    
    /*------------------------------Modificadores------------------------------*/
    /**
     * 
     * @param Nombre_Apellidos
     * @param Fecha_Nacimiento
     * @param Ocupacion
     * @param parentesco
     */
    public void cambiarDatosFamiliar(String Nombre_Apellidos, Date Fecha_Nacimiento, String Ocupacion, Parentesco parentesco){
        /*Modificamos los datos del objeto*/
        this.Nombre_Apellidos = Nombre_Apellidos;
        this.Fecha_Nacimiento = Fecha_Nacimiento;
        this.Ocupacion = Ocupacion;
        this.parentesco = parentesco;
        
        /*Convertimos fecha*/
        java.sql.Timestamp fecha_Nacimiento = new java.sql.Timestamp(Fecha_Nacimiento.getTime());

        con.conectarBD();

         try {
            Statement instruccion = (Statement) con.conexion().createStatement();
            
            /*Actualizamos Familiar*/
            instruccion.executeUpdate("UPDATE  Familiar SET Nombre_Apellidos = \""
                    + Nombre_Apellidos + "\", Fecha_Nacimiento = \""+fecha_Nacimiento+"\", Ocupacion = \""
                    + Ocupacion + "\" WHERE Cod_Familiar = " + Cod_Familiar);
            
            /*Actualizamos Parentesco*/
            instruccion.executeUpdate("UPDATE  Parentesco SET Parentesco = \""
                    + parentesco.Parentesc + "\" WHERE Cod_Familiar = " + Cod_Familiar + " and "
                    + "DNI_CIF = \""+ ONG.gestorBeneficiarios.NIF_Beneficiario+"\"");
         }
         /*Captura de errores*/
         catch(SQLException e){ System.out.println(e); }
         catch(Exception e){ System.out.println(e);}
         /*Desconexión de la BD*/
         finally {
            if (con.hayConexionBD()) {
                try {
                    con.desconectarBD();
                } catch (SQLException ex) {
                    Logger.getLogger(Familiar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    /**
     * 
     * @param parentesco
     */
    public void agregarParentesco(Parentesco parentesco)    {
        /*Agregamos el parentesco*/
        this.parentesco = parentesco;
        
        /*Se agrega la relación familiar al sistema*/
        con.conectarBD();
        try {
            Statement instruccion = (Statement) con.conexion().createStatement();
            
            /*Introducimos al nuevo Familiar en el sistema*/
            instruccion.executeUpdate("INSERT INTO Parentesco "
                    + " VALUES (\""+Cod_Familiar  + "\",\"" + ONG.gestorBeneficiarios.NIF_Beneficiario + "\",\"" 
                    + parentesco.Parentesc + "\")");
           
          }
         /*Captura de errores*/
         catch(SQLException e){ System.out.println(e); }
         catch(Exception e){ System.out.println(e);}
         /*Desconexión de la BD*/
         finally {
            if (con.hayConexionBD()) {
                try {
                    con.desconectarBD();
                } catch (SQLException ex) {
                    Logger.getLogger(ONG.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }       
    }

    /**
     * 
     */
    public void eliminarParentesco(){
        parentesco = null;
              
        /*Eliminamos el parentesco*/
        con.conectarBD();
        try {
            Statement instruccion = (Statement) con.conexion().createStatement();

            /*Eliminamos el parentesco que guarda con el familiar*/
            instruccion.executeUpdate("DELETE FROM Parentesco WHERE"
                    + " Cod_Familiar = " + Cod_Familiar + " and DNI_CIF= \""
                    + ONG.gestorBeneficiarios.NIF_Beneficiario +"\"");
        }
        /*Captura de errores*/
        catch(SQLException e){ System.out.println(e); }
        catch(Exception e){ System.out.println(e);}
        /*Desconexión de la BD*/
        finally {
            if (con.hayConexionBD()) {
                try {
                    con.desconectarBD();
                } catch (SQLException ex) {
                    Logger.getLogger(ONG.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }       
    }
}
