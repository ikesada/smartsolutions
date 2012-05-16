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
    public String Nombre_Apellidos;
    /**
     * Fecha de nacimiento del familiar
     */
    public Date Fecha_Nacimiento;
    /**
     * Ocupacion del familiar
     */
    public String Ocupacion;
    
    /**
     * Parentesco del familiar con respecto al Beneficiario
     */
    public String parentesco;
       
    ConexionBD con = new ConexionBD();

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

    }
}
