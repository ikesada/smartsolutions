/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Usuarios;

import com.mysql.jdbc.Statement;
import diaketas.ConexionBD;
import diaketas.UI.Beneficiarios.jAltaBeneficiario;
import diaketas.Usuarios.Beneficiario.Beneficiario;
import diaketas.Usuarios.Voluntario.Voluntario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Alex
 */
public class ONG {
      static Statement instruccion;
      static ResultSet tabla;
      static ConexionBD con = new ConexionBD();
    
    /********************************ACCION*************************************/
    public static void agregarAccion(Accion ac){
        
        con.conectarBD();
        /*Convertimos Date para trabajar*/
        java.sql.Timestamp fecha = new java.sql.Timestamp(ac.Fecha.getTime());
         try {
            instruccion = (Statement) con.conexion().createStatement();
            instruccion.executeUpdate("INSERT INTO Accion(Nombre, Fecha,"
                                        + "NIF_CIF_Voluntario, NIF_CIF_Usuario) VALUES (\""
                                        + ac.tipo + "\",\"" + fecha + "\",\"" + ac.DNI_Voluntario+"\",\""+ac.DNI_Usuario+"\")");
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
    
    /**************************BENEFICIARIO*************************************/
    /*
    static Beneficiario buscarBeneficiario(String dni){
//        Beneficiario benef = new Beneficiario();
    
    
     //   return benef;
    };*/
    
    static public Boolean comprobarExistenciaBeneficiario(String dni){
        
        
        return true;
    }
    
    static public void agregarNuevoBeneficiario(Beneficiario nuevoBeneficiario){
        con.conectarBD();
        /*Convertimos Date para trabajar*/
        java.sql.Timestamp fecha_Nacimiento = new java.sql.Timestamp(nuevoBeneficiario.FechaNac.getTime());
        java.sql.Timestamp fecha_Activacion = new java.sql.Timestamp(nuevoBeneficiario.Fecha_Inscripcion.getTime());
        
         try {
            instruccion = (Statement) con.conexion().createStatement();
            /*Introducimos la parte de Usuario*/

            instruccion.executeUpdate("INSERT INTO Usuario VALUES (\""+nuevoBeneficiario.NIF_CIF + "\",\""
                    + nuevoBeneficiario.Nombre + "\",\"" + nuevoBeneficiario.Apellidos + "\",\""  + fecha_Nacimiento
                    + "\",\"" + nuevoBeneficiario.Localidad + "\",\""   + nuevoBeneficiario.Activo + "\",\"" + "0/0/0"
                    + "\",\"" + nuevoBeneficiario.Email + "\",\"" + nuevoBeneficiario.Telefono + "\")");
            /*Introducimos la parte de Beneficiario*/
             instruccion.executeUpdate("INSERT INTO Beneficiario VALUES (\""+nuevoBeneficiario.NIF_CIF + "\",\""
                    + nuevoBeneficiario.Nacionalidad + "\",\"" + nuevoBeneficiario.Estado_civil + "\",\""  + nuevoBeneficiario.Domicilio
                    + "\",\"" + nuevoBeneficiario.Codigo_Postal + "\",\""   + nuevoBeneficiario.Observaciones
                    + "\",\"" + fecha_Activacion + "\",\"" + nuevoBeneficiario.Expediente + "\",\"" + nuevoBeneficiario.Motivo
                    + "\",\"" + nuevoBeneficiario.Precio_Vivienda + "\",\"" + nuevoBeneficiario.Tipo_Vivienda+"\")");           
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
            
    
    
    
    
    /**************************VOLUNTARIO*************************************/
    static Voluntario buscarVoluntario(String DNI){
        Voluntario v = null;
        try {
            instruccion = (Statement) con.conexion().createStatement();
            tabla = instruccion.executeQuery("SELECT COUNT(v.NIF_CIF) FROM Usuario u, Voluntario v"
                    + " WHERE u.NIF_CIF = v.NIF_CIF and v.NIF_CIF = \""+DNI+"\"");
            if(tabla.next()){
                //Crear Voluntario
                
            }
                
        }
        catch(SQLException e){ System.out.println(e); }
        catch(Exception e){ System.out.println(e); }        
        
        return v;
    }
    static public boolean comprobarExistenciaVoluntario(String DNI){
        Voluntario vol = buscarVoluntario(DNI);
        return (vol != null);
    }
    
}
