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
import java.text.*;

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
    
    String NIF_Donante_Movil;
    Donante datosDonanteMovil;
    

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

        return diaketas.diaketas.ong.buscarDonante(datosDonante.obtenerNIFCIF());
    }


    @Override
    /**
     *  Funcion que finaliza la operacion de Alta Donante
     */   
    public void confirmarFinAlta() {
        /*
         * Crear Donante
         */
        Donante nuevoDonante = Donante.crearDonante(datosDonante.obtenerNIFCIF(), datosDonante.obtenerNombre(),
                datosDonante.obtenerApellidos(), datosDonante.obtenerFechaNac(), datosDonante.obtenerLocalidad(), datosDonante.obtenerEmail(),
                datosDonante.obtenerTelefono(), datosDonante.obtenerTipoDonante(), datosDonante.obtenerFechaInscripcion(),
                datosDonante.obtenerObservaciones(), datosDonante.obtenerPeriodicidadDonaciones(),
                datosDonante.obtenerCuantiaDonaciones(), datosDonante.obtenerTipoPeriodicidad());

        /*
         * Registrar Donante
         */
        diaketas.diaketas.ong.agregarNuevoDonante(nuevoDonante);

        /*
         * Registrar Operacion
         */
        ONG.gestorHistoriales.RegistrarOperacion(NIF_Voluntario, datosDonante.obtenerNIFCIF(), "Alta Donante");
    }


    @Override
   /**
     *  Funcion que finaliza la operacion de Modificacion Donante
     */
    public void confirmarFinModificacion() {

        Donante d = diaketas.diaketas.ong.buscarDonante(datosDonante.obtenerNIFCIF());

        d.modificarDatos(d.obtenerNIFCIF(), datosDonante.obtenerNombre(), datosDonante.obtenerApellidos(), new java.sql.Timestamp(datosDonante.obtenerFechaNac().getTime()),
                datosDonante.obtenerLocalidad(), datosDonante.obtenerActivo(), null, datosDonante.obtenerEmail(), datosDonante.obtenerTelefono(),
                datosDonante.obtenerTipoDonante(), new java.sql.Timestamp(datosDonante.obtenerFechaInscripcion().getTime()), datosDonante.obtenerObservaciones(),
                datosDonante.obtenerPeriodicidadDonaciones(), datosDonante.obtenerCuantiaDonaciones(), datosDonante.obtenerTipoPeriodicidad());
        
        d.registrarCambios();
        
        /*
         * Registrar Operacion
         */
        ONG.gestorHistoriales.RegistrarOperacion(NIF_Voluntario, d.obtenerNIFCIF(), "Modificacion Donante");
        
    }
    

    @Override
    /**
     *  Funcion que finaliza la operacion de Baja
     */
    public void confirmarFinBaja(){
                
        datosDonante = diaketas.diaketas.ong.buscarDonante(datosDonante.obtenerNIFCIF());
        
        datosDonante.modificarActivo(0);
        datosDonante.modificarFechaDesac(new Date());
        
        /*Convertimos Date para trabajar*/
        java.sql.Timestamp fecha_Desac = new java.sql.Timestamp(datosDonante.obtenerFechaDesac().getTime());

        ConexionBD con = new ConexionBD();
        con.conectarBD();
        try {
            com.mysql.jdbc.Statement instruccion = (com.mysql.jdbc.Statement) con.conexion().createStatement();
            
            /* Desactivamos el usuario y actualizamos fecha de Baja*/
            instruccion.executeUpdate("UPDATE Usuario SET Activo = " + datosDonante.obtenerActivo() + ", Fecha_Desactivacion = \""
                    +fecha_Desac+"\" WHERE NIF_CIF = \"" + datosDonante.obtenerNIFCIF() + "\" LIMIT 1");
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
                    Logger.getLogger(Gestor_de_donantes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }         
    
        
        /*
         * Registrar Operacion
         */
        ONG.gestorHistoriales.RegistrarOperacion(NIF_Voluntario, datosDonante.obtenerNIFCIF(), "Baja Donante");
        
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
                
        if(donante != null && donante.obtenerActivo() == 1){
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
    
    @Override
    /**
     *  Función que comprueba si los datos de un donante que intenta acceder por móvil
     *  son correctos
     * @param dni DNI del donante que intenta identificarse
     * @param password Contraseña del donante que intenta identificarse (1º apellido
     * seguido de la fecha de nacimiento en formato DDMMYYYY
     */
    public boolean identificarse(String dni, String password){
        if(password.length() < 8)
            return false;
        
        boolean identificado = comprobarDniDonante(dni);
        Date fecha = null;
        String strFecha, apellido;
        
        if(identificado){
            NIF_Donante_Movil = dni;
            Donante d = diaketas.diaketas.ong.buscarDonante(dni);
            
            datosDonante = d;
            
            strFecha = password.substring(password.length()-8,password.length());
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yy");
            try {
                fecha = formatoFecha.parse(
                        strFecha.substring(0,2)+"/"
                        +strFecha.substring(2,4)+"/"
                        +strFecha.substring(4,8)
                        );
            } catch (ParseException ex) {}
            
            apellido = (d.obtenerApellidos().split(" ")[0]).toLowerCase();
            apellido=apellido.replaceAll("á", "a");
            apellido=apellido.replaceAll("é", "e");
            apellido=apellido.replaceAll("í", "i");
            apellido=apellido.replaceAll("ó", "o");
            apellido=apellido.replaceAll("ú", "u");
            apellido=apellido.replaceAll("à", "a");
            apellido=apellido.replaceAll("è", "e");
            apellido=apellido.replaceAll("ì", "i");
            apellido=apellido.replaceAll("ò", "o");
            apellido=apellido.replaceAll("ù", "u");
            apellido=apellido.replaceAll("ä", "a");
            apellido=apellido.replaceAll("ë", "e");
            apellido=apellido.replaceAll("ï", "i");
            apellido=apellido.replaceAll("ö", "o");
            apellido=apellido.replaceAll("ü", "u");
           
            identificado = fecha.equals(d.obtenerFechaNac()) && password.substring(0,password.length()-8).equals(apellido);
        }
       
        return identificado;
    }

    @Override
    public Donante obtenerDatosDonante() {
        datosDonanteMovil = diaketas.diaketas.ong.buscarDonante(NIF_Donante_Movil);
        return datosDonanteMovil;
    }

    @Override
    public void guardarDatosDonante() {
       datosDonanteMovil.registrarCambios();
    }

    @Override
    public void modificarElemento(Object valor, int indice) {
        switch(indice) {
            case 0: //fecha
                datosDonanteMovil.modificarFechaNac((Date)valor);
            break;
                
            case 1: //localidad
                datosDonanteMovil.modificarLocalidad(((String)valor));
            break;
                
            case 2: //telefono
                datosDonanteMovil.modificarTelefono(((Integer)valor).intValue());
            break;
                
            case 3: //email
                datosDonanteMovil.modificarEmail(((String)valor));
            break;
                
            case 4: //Tipo periodicidad
                datosDonanteMovil.modificarTipoPeriodicidad(((String)valor));
            break;
                
            case 5: //Periodicidad
                datosDonanteMovil.modificarPeriodicidadDonaciones(((Integer)valor).intValue());
            break;
                
            case 6: //Cuantia
                datosDonanteMovil.modificarCuantiaDonaciones(((Double) valor).doubleValue());
            break;
                
            default: //No hacer nada
            break;
        }
    }
}