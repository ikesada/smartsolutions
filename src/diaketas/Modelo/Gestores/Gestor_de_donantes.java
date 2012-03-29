/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.Gestores;

import diaketas.ConexionBD;
import diaketas.Modelo.ONG.Accion;
import diaketas.Modelo.ONG.ONG;
import diaketas.Modelo.ONG.Donante;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Alex
 */
public class Gestor_de_donantes implements iGestorDonantes {

    Statement instruccion;
    ResultSet tabla;
    ConexionBD con = new ConexionBD();
    Donante datosDonante;
    String NIF_Voluntario;

    @Override
    public boolean introducirDatosDonante(String NIF_CIF, String Nombre, String Apellidos, Date FechaNac, String Localidad, String Email, int Telefono,
            String Tipo_Donante, Date Fecha_Inscripcion, String Observaciones, int Periodicidad_Donaciones, double Cuantia_Donaciones,
            String Tipo_Periodicidad, String NIF_Vol) {

        datosDonante = new Donante(NIF_CIF, Nombre, Apellidos, FechaNac, Localidad, 1, new Date(), Email, Telefono, Tipo_Donante, Fecha_Inscripcion,
                Observaciones, Periodicidad_Donaciones, Cuantia_Donaciones, Tipo_Periodicidad);


        NIF_Voluntario = NIF_Vol;
        
        //return ONG.comprobarExistenciaVoluntario(NIF_Voluntario);
        return true;
    }

    @Override
    public Boolean introducirDniDonante(String NIF_CIF) {

        datosDonante = new Donante(NIF_CIF);

        return comprobarDniDonante(NIF_CIF);
    }
    
    @Override
    public Donante confimarConsulta() {

        return diaketas.diaketas.ong.buscarDonante(datosDonante.NIF_CIF);
    }

    @Override
    public void confirmarFinAlta() {

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
        diaketas.diaketas.ong.agregarNuevoDonante(nuevoDonante);

        /*
         * Registrar Operacion
         */
        diaketas.diaketas.ong.gestorHistoriales.RegistrarOperacion(NIF_Voluntario, datosDonante.NIF_CIF, "Alta_Donante");
    }

    @Override
    public void confirmarFinModificacion() {

        
        datosDonante = diaketas.diaketas.ong.buscarDonante(datosDonante.NIF_CIF);
        
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
        diaketas.diaketas.ong.gestorHistoriales.RegistrarOperacion(NIF_Voluntario, datosDonante.NIF_CIF, "modificacion_donante");
        
    }
    
    @Override
    public void confirmarFinBaja(){
        
        datosDonante = diaketas.diaketas.ong.buscarDonante(datosDonante.NIF_CIF);
        
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
        diaketas.diaketas.ong.gestorHistoriales.RegistrarOperacion(NIF_Voluntario, datosDonante.NIF_CIF, "baja_donante");
        
    }
    
    /* Funciones no pertenecientes a la interfaz */
    
    public Boolean comprobarDniDonante(String NIF_CIF) {

        Boolean existe = false;
        Donante donante = diaketas.diaketas.ong.buscarDonante(NIF_CIF);
                
        if(donante != null && donante.Activo == 1){
                existe = true;
       }

        return existe;
    }
    
    public Boolean introducirDniVoluntario(String NIF_CIF) {

        NIF_Voluntario = NIF_CIF;

        return diaketas.diaketas.ong.gestorVoluntarios.comprobarExistenciaVoluntario(NIF_Voluntario);
    }
}
