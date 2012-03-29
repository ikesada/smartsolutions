/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.ONG;


import com.mysql.jdbc.Statement;
import diaketas.ConexionBD;
import diaketas.Modelo.ONG.ONG;
import diaketas.Modelo.ONG.Usuarios;
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
    
    
    
    /**
     * Nacionalidad del voluntario
     */
    public String Nacionalidad;
    /**
     * Domicilio del voluntario
     */
    public String Domicilio;
    /**
     * Codigo postal del voluntario
     */
    public int Codigo_Postal;
    /**
     * Fecha en que se registro el voluntario en el sistema
     */
    public Date Fecha_Inicio;
    /**
     * Observaciones relacionadas con el voluntario
     */
    public String Observaciones;
    
   
    
    ConexionBD con = new ConexionBD();
    
    //OPERACIONES DE LA CLASE VOLUNTARIO
    
    
    //Constructor
    /**
     * Constructor de la clase Voluntario
     * @param NIF_CIF Dni del voluntario
     * @param Nombre Nombre del voluntario
     * @param Apellidos Apellidos del voluntario
     * @param FechaNac Fecha de nacimiento del voluntario
     * @param Localidad Localidad del voluntario
     * @param Activo Indica si el voluntario esta activo en el sistema o no
     * @param FechaDesac Fecha en que se desactivo voluntario en el sistema. Puede ser null.
     * @param Email Email del voluntario
     * @param Telefono Telefono del voluntario
     * @param Nacionalidad Nacionalidad del voluntario
     * @param Domicilio Domicilio del voluntario
     * @param Codigo_Postal Codigo postal de la ciudad del voluntario
     * @param Fecha_Inicio Fecha en la que el voluntario se registro en el sistema
     * @param Observaciones Observaciones relacionadas con el voluntario
     */
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


    
    
    
    
    
    //activo, fechaDesac no se pueden modificar, 
    /**
     * Funcion que actualiza los datos asociados a un voluntario
     * @param NIF_CIF Dni del voluntario
     * @param Nombre Nombre del voluntario
     * @param Apellidos Apellidos del voluntario
     * @param FechaNac Fecha de nacimiento del voluntario
     * @param Localidad Localidad del voluntario
     * @param Email Email del voluntario
     * @param Telefono Telefono del voluntario
     * @param Nacionalidad Nacionalidad del voluntario
     * @param Domicilio Domicilio del voluntario
     * @param Codigo_Postal Codigo postal de la ciudad del voluntario
     * @param Obs Observaciones relacionadas con el voluntario
     * @return Devuelve true si la operacion se ha realizado con exito
     */
                
    public boolean introducirDatosVoluntario(String NIF_CIF, String Nombre, String Apellidos, Date FechaNac, String Localidad, String Email, int Telefono,
                                        String Nacionalidad, String Domicilio, int Codigo_Postal, String Obs)
    {
    
        boolean exito=true;
        
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
                    + "NIF_CIF = \"" + this.NIF_CIF + "\", "
                    + "Nombre = \"" + this.Nombre + "\", "                    
                    + "Apellidos = \"" + this.Apellidos + "\", "                    
                    + "Fecha_Nacimiento_Fundacion = \"" + fecha_Nacimiento + "\", "                    
                    + "Localidad = \"" + this.Localidad + "\", "
                    + "Email = \"" + this.Email + "\", "
                    + "Telefono = \"" + this.Telefono + "\""
                    + " WHERE NIF_CIF = \""+NIF_CIF+"\"");
         
            //Ahora actualizo la tabla de Voluntario
            instruccion.executeUpdate("Update Voluntario SET "
                    + "NIF_CIF = \"" + this.NIF_CIF + "\", "
                    + "Nacionalidad = \"" + this.Nacionalidad + "\", "                    
                                   
                    + "Domicilio = \"" + this.Domicilio + "\", "                    
                    + "Codigo_Postal = \"" + this.Codigo_Postal + "\", "
                    + "Observaciones = \"" + this.Observaciones + "\" "
                   
                    + " WHERE NIF_CIF = \""+NIF_CIF+"\"");

         }
         //Captura de errores
         catch(SQLException e)
         { 
             exito=false;
             System.out.println(e); 
         }
         catch(Exception e)
         { 
             exito=false;
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
        
        return exito;
        
    }
    
    
    
    /**
     * Funcion que pone a inactivo a un voluntario del sistema
     * @param v Objeto voluntario a desactivar
     * @param fecha_desactivacion Fecha en que se desactiva el voluntario 
     * @return Devuelve true si la operacion se ha realizado con exito
     */
    public static boolean desactivarVoluntario(Voluntario v, Date fecha_desactivacion)
    {
        
        boolean exito = true;

        /*Modificamos los datos del objeto*/
        v.Activo = 0;
        v.FechaDesac = fecha_desactivacion;
        
        //Convertimos Date para trabajar
        java.sql.Timestamp fecha_Desac = new java.sql.Timestamp(v.FechaDesac.getTime());

        ConexionBD con = new ConexionBD();
        con.conectarBD();
        try {
            Statement instruccion = (Statement) con.conexion().createStatement();
            
            /* Desactivamos el usuario y actualizamos fecha de Baja*/
            instruccion.executeUpdate("UPDATE Usuario SET Activo = " + v.Activo + ", Fecha_Desactivacion = \""
                    +fecha_Desac+"\" WHERE NIF_CIF = \"" + v.NIF_CIF + "\"");
         }
         /*Captura de errores*/
         catch(SQLException e){ 
             System.out.println(e); 
             exito = false;
         }
         catch(Exception e){ 
             System.out.println(e);
             exito = false;
         }
         /*Desconexión de la BD*/
         finally {
            if (con.hayConexionBD()) {
                try {
                    con.desconectarBD();
                } catch (SQLException ex) {
                    Logger.getLogger(ONG.class.getName()).log(Level.SEVERE, null, ex);
                    exito = false;
                }
            }
        }         
    
        return exito;
        
    }
    
    
    
}
