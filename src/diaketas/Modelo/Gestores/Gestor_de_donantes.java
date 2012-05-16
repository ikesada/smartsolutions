/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.Gestores;

import diaketas.ConexionBD;
import diaketas.Modelo.ONG.Donante;
import diaketas.Modelo.ONG.ONG;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
   /**
     * 
     * Funcion que introduce en el sistema los datos para manejarlos mas tarde
     * @param NIF_CIF Dni del Donante
     * @param Nombre Nombre del Donante
     * @param Apellidos Apellidos del Donante
     * @param FechaNac Fecha de Nacimiento del Donante
     * @param Localidad Localidad del Donante
     * @param Email Email del Donante 
     * @param Telefono Telefono del Donante 
     * @param Tipo_Donante Tipo de Donante
     * @param Fecha_Inscripcion Fecha de Inscripcion del Donante
     * @param Observaciones Observaciones sobre el Donante
     * @param Periodicidad_Donaciones Periodicidad en la que se haran las donaciones
     * @param Cuantia_Donaciones Cuantia de las donaciones del Donante
     * @param Tipo_Periodicidad Tipo de periodicidad de las donaciones
     * @param NIF_Vol Dni del voluntario que realiza la operacion
     * @return Devuelve True o False si el voluntario que realiza la operacion existe
     */    
    public boolean introducirDatosDonante(String NIF_CIF, String Nombre, String Apellidos, Date FechaNac, String Localidad, String Email, int Telefono,
            String Tipo_Donante, Date Fecha_Inscripcion, String Observaciones, int Periodicidad_Donaciones, double Cuantia_Donaciones,
            String Tipo_Periodicidad, String NIF_Vol) {

        datosDonante = new Donante(NIF_CIF, Nombre, Apellidos, FechaNac, Localidad, 1, new Date(), Email, Telefono, Tipo_Donante, Fecha_Inscripcion,
                Observaciones, Periodicidad_Donaciones, Cuantia_Donaciones, Tipo_Periodicidad);


        NIF_Voluntario = NIF_Vol;
        
        return ONG.gestorVoluntarios.comprobarExistenciaVoluntario(NIF_Voluntario);
    }


    @Override
   /**
     * 
     *  Funcion que introduce en el sistema el dni del donante y lo comprueba
     * @param NIF_CIF Dni del Donante a comprobar
     * @return Devuelve True o False dependiende de si el donante existe(y no esta dado de baja) o no
     */    
    public Boolean introducirDniDonante(String NIF_CIF) {

        datosDonante = new Donante(NIF_CIF);

        return comprobarDniDonante(NIF_CIF);
    }

    @Override
   /**
     *  Funcion que prueba que la consulta es valida
     * @return Devuelve los datos del Donante
     */    
    public Donante confimarConsulta() {

        return diaketas.diaketas.ong.buscarDonante(datosDonante.NIF_CIF);
    }


    @Override
    /**
     *  Funcion que finaliza la operacion de Alta Donante
     */   
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
        ONG.gestorHistoriales.RegistrarOperacion(NIF_Voluntario, datosDonante.NIF_CIF, "Alta Donante");
    }


    @Override
   /**
     *  Funcion que finaliza la operacion de Modificacion Donante
     */
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

        
        
        /*
         * Registrar Operacion
         */
        ONG.gestorHistoriales.RegistrarOperacion(NIF_Voluntario, datosDonante.NIF_CIF, "Modificacion Donante");
        
    }
    

    @Override
    /**
     *  Funcion que finaliza la operacion de Baja
     */
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
       
    
        
        /*
         * Registrar Operacion
         */
        ONG.gestorHistoriales.RegistrarOperacion(NIF_Voluntario, datosDonante.NIF_CIF, "Baja Donante");
        
    }
    
    /* Funciones no pertenecientes a la interfaz */
    
    /**
     * 
     * @param NIF_CIF
     * @return Devuelve True o False si el Donante existe
     */
    public Boolean comprobarDniDonante(String NIF_CIF) {

        Boolean existe = false;
        Donante donante = diaketas.diaketas.ong.buscarDonante(NIF_CIF);
                
        if(donante != null && donante.Activo == 1){
                existe = true;
       }

        return existe;
    }
    
    
    
    /**
     * Funcion que devuelve un listado con todos los Donantes del sistema
     * @return Devuelve un listado con todos los Donantes del sistema
     */
    public static ArrayList<Donante> obtenerDonantes()
    {
        
        ArrayList<Donante> usuarios = new ArrayList<Donante>();
        
        usuarios = diaketas.diaketas.ong.buscarDonantes();
        
        return usuarios;
        
    }
    
}
