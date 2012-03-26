/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Usuarios.Beneficiario;

import com.mysql.jdbc.Statement;
import diaketas.ConexionBD;
import diaketas.Usuarios.ONG;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kesada
 */
public class Familiar {
    public String Nombre_Apellidos;
    public Date Fecha_Nacimiento;
    public String Ocupacion;
    public int Cod_Familiar;
    
    ConexionBD con = new ConexionBD();

    /*------------------------------Constructores------------------------------*/
    public Familiar(String Nombre_Apellidos, Date Fecha_Nacimiento, String Ocupacion) {
        this.Nombre_Apellidos = Nombre_Apellidos;
        this.Fecha_Nacimiento = Fecha_Nacimiento;
        this.Ocupacion = Ocupacion;
        this.Cod_Familiar = 0;
    }
    
    /*------------------------------Modificadores------------------------------*/
    void cambiarDatosFamiliar(String Nombre_Apellidos, Date Fecha_Nacimiento, String Ocupacion, String Parentesco){
        /*Modificamos los datos del objeto*/
        this.Nombre_Apellidos = Nombre_Apellidos;
        this.Fecha_Nacimiento = Fecha_Nacimiento;
        this.Ocupacion = Ocupacion;
        
        /*Convertimos fecha*/
        java.sql.Timestamp fecha_Nacimiento = new java.sql.Timestamp(Fecha_Nacimiento.getTime());

        con.conectarBD();

        //REVISAR
         try {
            Statement instruccion = (Statement) con.conexion().createStatement();
            
            /*Actualizamos Familiar*/
            instruccion.executeUpdate("UPDATE  Familiar SET Nombre_Apellidos = \""
                    + Nombre_Apellidos + "\", Fecha_Nacimiento = \""+fecha_Nacimiento+"\", Ocupacion = \""
                    + Ocupacion + "\" WHERE Cod_Familiar = " + Cod_Familiar);
            
            /*Actualizamos Parentesco*/
            instruccion.executeUpdate("UPDATE  Parentesco SET Parentesco = \""
                    + Parentesco + "\" WHERE Cod_Familiar = " + Cod_Familiar + " and "
                    + "DNI_CIF = \""+Gestor_de_beneficiarios.datosBeneficiario.NIF_CIF+"\"");
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
    
    /*------------------------------Acceso-------------------------------------*/
    public Parentesco obtenerDatosFamiliar(){
        con.conectarBD();
        
        Parentesco parentesco = null;

        //REVISAR
         try {
            Statement instruccion = (Statement) con.conexion().createStatement();
            
            /*Obtenemos el parentesco del familiar con respecto al beneficiario*/
            ResultSet rs = instruccion.executeQuery("Select p.Parentesco from Parentesco p WHERE "
                    + "DNI_CIF = \""+Gestor_de_beneficiarios.datosBeneficiario.NIF_CIF+"\" and "
                    + " Cod_Familiar="+this.Cod_Familiar);
         
            if (rs.next()){
                parentesco = new Parentesco(Cod_Familiar, Gestor_de_beneficiarios.datosBeneficiario.NIF_CIF,rs.getString(1));
            }
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
        return parentesco;
    }
    
    
}
