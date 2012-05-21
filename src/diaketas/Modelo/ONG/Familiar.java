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
     * Nombre y apellidos del familiar
     */
    private String Nombre_Apellidos;
    /**
     * Fecha de nacimiento del familiar
     */
    private Date Fecha_Nacimiento;
    /**
     * Ocupacion del familiar
     */
    private String Ocupacion;
    
    /**
     * Parentesco del familiar con respecto al Beneficiario
     */
    private String parentesco;
       
    ConexionBD con = new ConexionBD();
    
    // --------------------------------- CONSULTORES ----------------------------- //

    public Date obtenerFechaNacimiento() {
        return Fecha_Nacimiento;
    }

    public String obtenerNombreApellidos() {
        return Nombre_Apellidos;
    }

    public String obtenerOcupacion() {
        return Ocupacion;
    }

    public String obtenerParentesco() {
        return parentesco;
    }
        
    // --------------------------------- MODIFICADORES --------------------------- //

    public void modificarFechaNacimiento(Date Fecha_Nacimiento) {
        this.Fecha_Nacimiento = Fecha_Nacimiento;
    }

    public void modificarNombreApellidos(String Nombre_Apellidos) {
        this.Nombre_Apellidos = Nombre_Apellidos;
    }

    public void modificarOcupacion(String Ocupacion) {
        this.Ocupacion = Ocupacion;
    }

    public void modificarParentesco(String parentesco) {
        this.parentesco = parentesco;
    }
    
    
    /*------------------------------Constructores------------------------------*/
    /**
     * Crea un nuevo familiar
     * @param Nombre_Apellidos Nombre y apellidos del familiar
     * @param Fecha_Nacimiento Fecha de nacimiento del familiar
     * @param Ocupacion Ocupación del familiar
     */
    public Familiar(String Nombre_Apellidos, Date Fecha_Nacimiento, String Ocupacion, String Parentesco) {
        this.Nombre_Apellidos = Nombre_Apellidos;
        this.Fecha_Nacimiento = Fecha_Nacimiento;
        this.Ocupacion = Ocupacion;
        this.parentesco = Parentesco;
    }
    
    /*------------------------------Modificadores------------------------------*/
    /**
     * Cambia los datos del familiar por los datos especificados
     * @param Nombre_Apellidos Nombre y apellidos del familiar
     * @param Fecha_Nacimiento Fecha de nacimiento del familiar
     * @param Ocupacion Ocupación del familiar
     * @param parentesco Relación de parentesco del familiar con respecto al beneficiario
     */
    public void cambiarDatosFamiliar(String Nombre_Apellidos, Date Fecha_Nacimiento, String Ocupacion, String parentesco){
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
                    + Ocupacion + "\" , Parentesco = \"" + parentesco + "\" WHERE Nombre_Apellidos = \"" + Nombre_Apellidos + "\" and Fecha_Nacimiento = \""
                    + fecha_Nacimiento+  "\" and DNI_Beneficiario = \"" + ONG.gestorBeneficiarios.NIF_Beneficiario+"\"");
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
    
    public void destruir(){
        con.conectarBD();
        
        /*Convertimos fecha*/
        java.sql.Timestamp fecha_Nacimiento = new java.sql.Timestamp(Fecha_Nacimiento.getTime());
        
         try {
            Statement instruccion = (Statement) con.conexion().createStatement();
     
            /*Actualizamos Familiar*/
            instruccion.executeUpdate("DELETE FROM Familiar WHERE Nombre_Apellidos = \"" + Nombre_Apellidos + "\" and Fecha_Nacimiento = \""
                    + fecha_Nacimiento+  "\" and DNI_Beneficiario = \"" + ONG.gestorBeneficiarios.NIF_Beneficiario+"\"");
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
}
