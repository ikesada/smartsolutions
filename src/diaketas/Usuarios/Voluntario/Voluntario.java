/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Usuarios.Voluntario;


import com.mysql.jdbc.Statement;
import diaketas.ConexionBD;
import diaketas.Usuarios.ONG;
import diaketas.Usuarios.Usuarios;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cesar
 */

public class Voluntario extends Usuarios{
    
    public String DNI_consultado;   //es un parametro auxiliar que me servira para ver 
    
    public String Nacionalidad;
    public String Domicilio;
    public int Codigo_Postal;
    public Date Fecha_Inicio;
    public String Observaciones;
    
    //el voluntario tiene asociadas un conjunto de acciones
    
    ConexionBD con = new ConexionBD();
    
    //OPERACIONES DE LA CLASE VOLUNTARIO
    
    
    //Constructor
    public Voluntario ( String NIF_CIF, String Nombre, String Apellidos, Date FechaNac, String Localidad, int Activo,
                        Date FechaDesac, String Email, int Telefono, String Nacionalidad, String Domicilio, 
                        int Codigo_Postal, Date Fecha_Inicio, String Observaciones )
    {
        
        this.NIF_CIF = NIF_CIF;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.FechaNac = FechaNac;
        this.Localidad = Localidad;
        this.Activo = Activo;
        this.FechaDesac = FechaDesac;
        this.Email = Email;
        this.Telefono = Telefono;
        this.Nacionalidad = Nacionalidad;
        this.Domicilio = Domicilio;
        this.Codigo_Postal = Codigo_Postal;
        this.Fecha_Inicio = Fecha_Inicio;
        this.Observaciones = Observaciones;      
    }


    
    
    
    //Modificador de voluntario
    
    //activo, fechaDesac no se pueden modificar, 
    public void cambiarDatosVoluntario(String NIF_CIF, String Nombre, String Apellidos, Date FechaNac, String Localidad, String Email, int Telefono,
                                        String Nacionalidad, String Domicilio, int Codigo_Postal, String Obs)
    {
    
//        boolean exito=true;
        
        this.NIF_CIF = NIF_CIF;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.FechaNac = FechaNac;
        this.Localidad = Localidad;
        this.Email = Email;
        this.Telefono = Telefono;
        this.Nacionalidad = Nacionalidad;
        this.Domicilio = Domicilio;
        this.Codigo_Postal = Codigo_Postal;
        this.Observaciones = Obs;
        
        
        
        // Actualizamos los datos 
        con.conectarBD();

        //transformo los tipo Date pasados
        java.sql.Timestamp fecha_Nacimiento = new java.sql.Timestamp(this.FechaNac.getTime());
        
        
        try {
            Statement instruccion = (Statement) con.conexion().createStatement();

            //Primero actualizo la tabla de Usuario
            instruccion.executeUpdate("Update Usuario SET "
                    /*+ "NIF_CIF = \"" + this.NIF_CIF + "\", "*/
                    + "Nombre = \"" + this.Nombre + "\", "                    
                    + "Apellidos = \"" + this.Apellidos + "\", "                    
                    + "Fecha_Nacimiento_Fundacion = \"" + fecha_Nacimiento + "\", "                    
                    + "Localidad = \"" + this.Localidad + "\", "
                    + "Email = \"" + this.Email + "\", "
                    + "Telefono = \"" + this.Telefono + "\""
                    + " WHERE NIF_CIF = \""+NIF_CIF+"\"");
         
            //Ahora actualizo la tabla de Voluntario
            instruccion.executeUpdate("Update Voluntario SET "
                    /*+ "NIF_CIF = \"" + this.NIF_CIF + "\", "*/
                    + "Nacionalidad = \"" + this.Nacionalidad + "\", "                    
                                   
                    + "Domicilio = \"" + this.Domicilio + "\", "                    
                    + "Codigo_Postal = \"" + this.Codigo_Postal + "\", "
                    + "Observaciones = \"" + this.Observaciones + "\" "
                   
                    + " WHERE NIF_CIF = \""+NIF_CIF+"\"");
//            exito=true;
//            System.out.println("Exito:"+exito);
         }
         //Captura de errores
         catch(SQLException e)
         { 
//             exito=false;
             System.out.println(e); 
         }
         catch(Exception e)
         { 
//             exito=false;
             System.out.println(e);
         }
         //Desconexión de la BD
         finally {
            if (con.hayConexionBD()) {
                try {
                    con.desconectarBD();
                } catch (SQLException ex) {
                    Logger.getLogger(ONG.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
//        return exito;
        
    }
    
    
    
}
