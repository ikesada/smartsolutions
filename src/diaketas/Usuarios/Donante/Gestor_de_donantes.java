/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Usuarios.Donante;

import diaketas.ConexionBD;
import diaketas.Usuarios.Accion;
import diaketas.Usuarios.ONG;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class Gestor_de_donantes {

    static Statement instruccion;
    static ResultSet tabla;
    static ConexionBD con = new ConexionBD();
    static Donante datosDonante;
    static String NIF_Voluntario;

    static public boolean introducirDatosDonante(String NIF_CIF, String Nombre, String Apellidos, Date FechaNac, String Localidad, String Email, int Telefono,
            String Tipo_Donante, Date Fecha_Inscripcion, String Observaciones, int Periodicidad_Donaciones, double Cuantia_Donaciones,
            String Tipo_Periodicidad, String NIF_Vol) {

        datosDonante = new Donante(NIF_CIF, Nombre, Apellidos, FechaNac, Localidad, 1, new Date(), Email, Telefono, Tipo_Donante, Fecha_Inscripcion,
                Observaciones, Periodicidad_Donaciones, Cuantia_Donaciones, Tipo_Periodicidad);


        NIF_Voluntario = NIF_Vol;
        
        //return ONG.comprobarExistenciaVoluntario(NIF_Voluntario);
        return true;
    }

    static public Boolean introducirDniDonante(String NIF_CIF) {

        datosDonante = new Donante(NIF_CIF);

        return comprobarDniDonante(NIF_CIF);
    }
    
    static public Boolean introducirDniVoluntario(String NIF_CIF) {

        NIF_Voluntario = NIF_CIF;

        return ONG.comprobarExistenciaVoluntario(NIF_Voluntario);
    }
    
    static public Boolean comprobarDniVoluntario(String NIF_CIF){
        
        return true;
    }
   
    static public Donante confimarConsulta() {

       /* con.conectarBD();
        
        try {
            instruccion = con.conexion().createStatement();

            tabla = instruccion.executeQuery("select NIF_CIF, Nombre, Apellidos, Fecha_Nacimiento_Fundacion, Localidad, Activo, Email, Telefono from Usuario where NIF_CIF = '"
                    + datosDonante.NIF_CIF + "' LIMIT 1");
            tabla.next();

            datosDonante.NIF_CIF = (String) tabla.getObject("NIF_CIF");
            datosDonante.Nombre = (String) tabla.getObject("Nombre");
            datosDonante.Apellidos = (String) tabla.getObject("Apellidos");
            datosDonante.FechaNac = (Date) tabla.getObject("Fecha_Nacimiento_Fundacion");
            datosDonante.Localidad = (String) tabla.getObject("Localidad");
            if ((Boolean)tabla.getObject("Activo") == true) {
                datosDonante.Activo = 1;
            } else {
                datosDonante.Activo = 0;
            }
            //datosDonante.FechaDesac = (Date) tabla.getObject("Fecha_Desactivacion");
            datosDonante.Email = (String) tabla.getObject("Email");
            datosDonante.Telefono = (Integer) tabla.getObject("Telefono");

            tabla = instruccion.executeQuery("select Tipo_donante, Fecha_Inscripcion, Observaciones, Periodicidad_Donaciones, Cuantia_Donaciones, Tipo_Periodicidad from Donante where NIF_CIF = '"
                    + datosDonante.NIF_CIF + "' LIMIT 1");
            tabla.next();


            datosDonante.Tipo_Donante = (String) tabla.getObject("Tipo_Donante");
            datosDonante.Fecha_Inscripcion = (Date) tabla.getObject("Fecha_Inscripcion");
            datosDonante.Observaciones = (String) tabla.getObject("Observaciones");
            datosDonante.Periodicidad_Donaciones = (Integer) tabla.getObject("Periodicidad_Donaciones");
            datosDonante.Cuantia_Donaciones = (Double) tabla.getObject("Cuantia_Donaciones");
            datosDonante.Tipo_Periodicidad = (String) tabla.getObject("Tipo_Periodicidad");


        } catch (SQLException ex) {
            Logger.getLogger(Gestor_de_donantes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally {
            if (con.hayConexionBD()) {
                try {
                    con.desconectarBD();
                } catch (SQLException ex) {
                    Logger.getLogger(Gestor_de_donantes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        * 
        * 
        return new Donante(datosDonante.NIF_CIF, datosDonante.Nombre,
                datosDonante.Apellidos, datosDonante.FechaNac, datosDonante.Localidad, 1, new Date(), datosDonante.Email,
                datosDonante.Telefono, datosDonante.Tipo_Donante, datosDonante.Fecha_Inscripcion,
                datosDonante.Observaciones, datosDonante.Periodicidad_Donaciones,
                datosDonante.Cuantia_Donaciones, datosDonante.Tipo_Periodicidad);
*/
        return ONG.buscarDonante(datosDonante.NIF_CIF);
    }

    static public void RegistrarOperacion(String DNI_Voluntario, String DNI, String Tipo) {
        Accion ac = new Accion(DNI_Voluntario, DNI, Tipo, new Date());
        ONG.agregarAccion(ac);
    }

    public static void confirmarFinAlta() {

        /*
         * Crear Donante
         */
        Donante nuevoDonante = Donante.crearDonante(datosDonante.NIF_CIF, datosDonante.Nombre,
                datosDonante.Apellidos, datosDonante.FechaNac, datosDonante.Localidad, datosDonante.Email,
                datosDonante.Telefono, datosDonante.Tipo_Donante, datosDonante.Fecha_Inscripcion,
                datosDonante.Observaciones, datosDonante.Periodicidad_Donaciones,
                datosDonante.Cuantia_Donaciones, datosDonante.Tipo_Periodicidad);

        /*
         * Registrar Donante
         */
        ONG.agregarNuevoDonante(nuevoDonante);

        /*
         * Registrar Operacion
         */
        Gestor_de_donantes.RegistrarOperacion(NIF_Voluntario, datosDonante.NIF_CIF, "Alta_Donante");
    }

    public static Boolean comprobarDniDonante(String NIF_CIF) {

        Boolean existe = false;
        
        if(ONG.buscarDonante(NIF_CIF) != null)
            existe = true;

        /*con.conectarBD();
        
        try {
            instruccion = con.conexion().createStatement();
            tabla = instruccion.executeQuery("select NIF_CIF from Donante where NIF_CIF = '"
                    + NIF_CIF + "' LIMIT 1");
            if (tabla.next()) {
                existe = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_de_donantes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        finally {
            if (con.hayConexionBD()) {
                try {
                    con.desconectarBD();
                } catch (SQLException ex) {
                    Logger.getLogger(Gestor_de_donantes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }*/
        
        return existe;
    }

    public static void confirmarFinModificacion() {

        
        datosDonante = ONG.buscarDonante(datosDonante.NIF_CIF);
        
        con.conectarBD();
        /*Convertimos Date para trabajar*/
        java.sql.Timestamp fecha_Nacimiento = new java.sql.Timestamp(datosDonante.FechaNac.getTime());
        
         try {
            instruccion = (com.mysql.jdbc.Statement) con.conexion().createStatement();
    
            /*Actualizamos la parte de Usuario*/
            instruccion.executeUpdate("UPDATE Usuario SET Nombre = \"" + datosDonante.Nombre + "\", Apellidos = \"" + datosDonante.Apellidos + "\", Fecha_Nacimiento_Fundacion = \""  + fecha_Nacimiento
                    + "\", Localidad = \"" + datosDonante.Localidad + "\", Email = \"" + datosDonante.Email + "\", Telefono = " + datosDonante.Telefono + " WHERE NIF_CIF = \"" + datosDonante.NIF_CIF+"\" LIMIT 1");
            /*Introducimos la parte de Donante*/
            instruccion.executeUpdate("UPDATE Donante SET Tipo_Donante = \"" + datosDonante.Tipo_Donante + "\", Observaciones = \""  + datosDonante.Observaciones
                    + "\", Periodicidad_Donaciones = \"" + datosDonante.Periodicidad_Donaciones + "\", Cuantia_Donaciones = \""   + datosDonante.Cuantia_Donaciones
                    + "\", Tipo_Periodicidad = \"" + datosDonante.Tipo_Periodicidad+"\" WHERE NIF_CIF = \"" + datosDonante.NIF_CIF + "\"  LIMIT 1");           
         }
         /*Captura de errores*/
         catch(SQLException e){ System.out.println(e); }
         
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
        
        
        /*
         * Registrar Operacion
         */
        Gestor_de_donantes.RegistrarOperacion(NIF_Voluntario, datosDonante.NIF_CIF, "modificacion_donante");
        
    }
    
    public static void confirmarFinBaja(){
        
        datosDonante = ONG.buscarDonante(datosDonante.NIF_CIF);
        
        datosDonante.Activo = 0;
        datosDonante.FechaDesac = new Date();
        
        /*Convertimos Date para trabajar*/
        java.sql.Timestamp fecha_Desac = new java.sql.Timestamp(datosDonante.FechaDesac.getTime());

        ConexionBD con = new ConexionBD();
        con.conectarBD();
        try {
            com.mysql.jdbc.Statement instruccion = (com.mysql.jdbc.Statement) con.conexion().createStatement();
            
            /* Desactivamos el usuario y actualizamos fecha de Baja*/
            instruccion.executeUpdate("UPDATE Usuario SET Activo = " + datosDonante.Activo + ", Fecha_Desactivacion = \""
                    +fecha_Desac+"\" WHERE NIF_CIF = \"" + datosDonante.NIF_CIF + "\" LIMIT 1");
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
    
        
        /*
         * Registrar Operacion
         */
        Gestor_de_donantes.RegistrarOperacion(NIF_Voluntario, datosDonante.NIF_CIF, "baja_donante");
        
        //datosDonante.desactivarUsuario(new Date());
        
        /* try {
            instruccion = (com.mysql.jdbc.Statement) con.conexion().createStatement();
    
            /*Actualizamos la parte de Usuario*/
         /*   instruccion.executeUpdate("UPDATE Usuario Set Activo = "+ datosDonante.Activo + ", Fecha_Desactivacion = '" + Fecha_Desactivacion + "' WHERE NIF_CIF = \"" + datosDonante.NIF_CIF + "\"  LIMIT 1"); 
            
         }
         /*Captura de errores*/
        /*catch(SQLException e){ System.out.println(e); }
         
         /*Desconexión de la BD*/
         /*finally {
            if (con.hayConexionBD()) {
                try {
                    con.desconectarBD();
                } catch (SQLException ex) {
                    Logger.getLogger(ONG.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }*/
         
    }
}
