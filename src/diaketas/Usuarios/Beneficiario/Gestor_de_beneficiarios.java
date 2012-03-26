/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Usuarios.Beneficiario;

import com.mysql.jdbc.Statement;
import diaketas.ConexionBD;
import diaketas.Usuarios.Historial.Gestor_de_historiales;
import diaketas.Usuarios.ONG;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kesada
 */
public class Gestor_de_beneficiarios {
    
    
    static Beneficiario datosBeneficiario;
    static String NIF_Voluntario;
    static String NIF_Beneficiario;
    static Familiar datosFamiliar;
    static String NombreApellidosFamiliar;
    static String parentesco;
    
    static ConexionBD con = new ConexionBD();
    static Statement instruccion;
    
    /*---------------------------Beneficiario----------------------------------*/
    static public boolean introducirDNIBeneficiario (String DNI_Beneficiario){
        
        NIF_Beneficiario = DNI_Beneficiario;
        
        /*Comprobamos si el Beneficiario existe */
        return ONG.comprobarExistenciaBeneficiario(DNI_Beneficiario);        
    }

    static public boolean introducirDatosBeneficiario(String NIF_CIF, String Nombre,
            String Apellidos, Date FechaNac, String Localidad,  String Email, int Telefono, String Nacionalidad,
            String Estado_civil, String Domicilio, int Codigo_Postal,
            Date Fecha_Inscripcion, int Expediente, String Motivo,
            Double Precio_Vivienda, String Tipo_Vivienda, String Observaciones_Datos_Personales,
            String Observaciones_Familiares, String Observaciones_Vivienda,
            String Ciudad_Nacimiento, String Situacion_Economica, String Nivel_Estudios,
            String Profesion, String Experiencia_Laboral, String NIF_Vol){
        NIF_Beneficiario = NIF_CIF;
        
        /*Se almacenan los datos del beneficiario y NIF_Voluntario en el sistema */
        datosBeneficiario = new Beneficiario (NIF_CIF, Nombre, Apellidos, FechaNac,
                Localidad, 1, null, Email, Telefono, Nacionalidad, Estado_civil,
                Domicilio, Codigo_Postal, Fecha_Inscripcion, Expediente, Motivo,
                Precio_Vivienda, Tipo_Vivienda, Observaciones_Datos_Personales,
                Observaciones_Familiares, Observaciones_Vivienda, Ciudad_Nacimiento,
                Situacion_Economica, Nivel_Estudios,Profesion, Experiencia_Laboral);
        NIF_Voluntario = NIF_Vol;
        
        /*Devuelve la existencia del voluntario*/
        return ONG.comprobarExistenciaVoluntario(NIF_Voluntario);
    }

    static public Beneficiario consultarBeneficiario (String DNI){
        
        /*Actualimos NIF*/
        NIF_Beneficiario = DNI;
        
        datosBeneficiario = null;

        /*Si existe el beneficiario obtenemos los datos del beneficiario y la lista de familiares*/
        if (ONG.comprobarExistenciaBeneficiario(DNI)){
            
            /*Obtenemos los datos del beneficiario*/
            datosBeneficiario = ONG.buscarBeneficiario(DNI);
        }
        
        return datosBeneficiario;
    }
  
    static private void eliminarBeneficiario(String DNI){

        /*Obtenemos el beneficiario*/
        Beneficiario beneficiario = ONG.buscarBeneficiario(DNI);  

        /*Desactivamos al usuario*/
        beneficiario.desactivarUsuario(new Date());
    }
  
    static private void modificarBeneficiario(Beneficiario datosBeneficiario){
        
        /*Buscamos beneficiario*/
        Beneficiario beneficiario = ONG.buscarBeneficiario(datosBeneficiario.NIF_CIF);

        /*Modificamos sus datos*/
        cambiarDatosBeneficiario(datosBeneficiario);
    }

    static public void cambiarDatosBeneficiario (Beneficiario datosBeneficiario){
    
      
        /* Actualizamos los datos */
        con.conectarBD();

        java.sql.Timestamp fecha_Nacimiento = new java.sql.Timestamp(datosBeneficiario.FechaNac.getTime());
        
        try {
            instruccion = (Statement) con.conexion().createStatement();

            instruccion.executeUpdate("Update Usuario SET "
                    + "NIF_CIF = \"" + datosBeneficiario.NIF_CIF + "\", "
                    + "Nombre = \"" + datosBeneficiario.Nombre + "\", "                    
                    + "Apellidos = \"" + datosBeneficiario.Apellidos + "\", "                    
                    + "Fecha_Nacimiento_Fundacion = \"" + fecha_Nacimiento + "\", "                    
                    + "Localidad = \"" + datosBeneficiario.Localidad + "\", "
                    + "Email = \"" + datosBeneficiario.Email + "\", "
                    + "Telefono = \"" + datosBeneficiario.Telefono + "\""
                    + " WHERE NIF_CIF = \""+Gestor_de_beneficiarios.NIF_Beneficiario+"\"");
         
            instruccion.executeUpdate("Update Beneficiario SET "
                    + "NIF_CIF = \"" + datosBeneficiario.NIF_CIF + "\", "
                    + "Nacionalidad = \"" + datosBeneficiario.Nacionalidad + "\", "                    
                    + "Estado_Civil = \"" + datosBeneficiario.Estado_civil + "\", "                    
                    + "Domicilio = \"" + datosBeneficiario.Domicilio + "\", "                    
                    + "Codigo_Postal = " + datosBeneficiario.Codigo_Postal + ", "
                    + "Expediente = " + datosBeneficiario.Expediente + ", "
                    + "Motivo = \"" + datosBeneficiario.Motivo + "\", "                  
                    + "Precio_Vivienda = " + datosBeneficiario.Precio_Vivienda + ", "
                    + "Tipo_Vivienda = \"" + datosBeneficiario.Tipo_Vivienda + "\""
                    + "Observaciones_Datos_Personales = \"" + datosBeneficiario.Observaciones_Datos_Personales + "\""
                    + "Observaciones_Familiares = \"" + datosBeneficiario.Observaciones_Familiares + "\""
                    + "Observaciones_Vivienda = \"" + datosBeneficiario.Observaciones_Vivienda + "\""
                    + "Ciudad_Nacimiento = \"" + datosBeneficiario.Ciudad_Nacimiento + "\""
                    + "Situacion_Economica = \"" + datosBeneficiario.Situacion_Economica + "\""
                    + "Nivel_Estudios = \"" + datosBeneficiario.Nivel_Estudios + "\""
                    + "Profesion = \"" + datosBeneficiario.Profesion + "\""
                    + "Experiencia_Laboral = \"" + datosBeneficiario.Experiencia_Laboral + "\""
                    + " WHERE NIF_CIF = \""+Gestor_de_beneficiarios.NIF_Beneficiario+"\"");
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
    
    
    static public void confirmarAltaBeneficiario(){
        
        /*Crear beneficiario*/
        Beneficiario nuevoBeneficiario =  new Beneficiario(datosBeneficiario);
        
        /*Registrar Beneficiario*/
        ONG.agregarNuevoBeneficiario(nuevoBeneficiario);
        
        /*Registrar Operacion*/
        Gestor_de_historiales.RegistrarOperacion(NIF_Voluntario, datosBeneficiario.NIF_CIF, "Alta Beneficiario");
        
        /*Registrar beneficiario*/
        /*Beneficiario ya tiene asociado Vivienda, email y telefono*/
    }
    
    static public void confirmarBajaBeneficiario(){
        /*Registrar Operacion*/
        Gestor_de_historiales.RegistrarOperacion(NIF_Voluntario, NIF_Beneficiario, "Baja Beneficiario");
        
        /*Eliminar beneficiario*/
        Gestor_de_beneficiarios.eliminarBeneficiario(NIF_Beneficiario);
    }
    
    static public void confirmarModificacionBeneficiario(){
         /*Registrar Operacion*/
        Gestor_de_historiales.RegistrarOperacion(NIF_Voluntario, datosBeneficiario.NIF_CIF, "Modificar Beneficiario");   
    
        /*Modificar beneficiario*/
        modificarBeneficiario(datosBeneficiario);
    }
   
    /*--------------------------------Familiar---------------------------------*/
    
    static public void seleccionarFamiliar(String Nombre_Apellidos){
        NombreApellidosFamiliar = Nombre_Apellidos;
    }
    /*
     * ConfirmarAltaFamiliar // ConfirmarInsercion
     */
    static public void confirmarInsercion(){   
        Familiar familiar;
        
        /*Se busca si el familiar ya existe*/
        familiar = ONG.buscarFamiliar(datosFamiliar.Nombre_Apellidos, datosFamiliar.Fecha_Nacimiento);

        /*Si No existe, se crea el nuevo familiar y se añade*/
        if (familiar == null){
            familiar = new Familiar(datosFamiliar.Nombre_Apellidos, datosFamiliar.Fecha_Nacimiento, datosFamiliar.Ocupacion);
            
            /*Agregamos el nuevo familiar al sistema*/
            con.conectarBD();
            /*Convertimos Date para trabajar*/
            java.sql.Timestamp fecha_Nacimiento = new java.sql.Timestamp(familiar.Fecha_Nacimiento.getTime());

            try {
                instruccion = (Statement) con.conexion().createStatement();

                /*Introducimos al nuevo Familiar en el sistema*/
                instruccion.executeUpdate("INSERT INTO Familiar (Nombre_Apellidos, Fecha_Nacimiento, Ocupacion)"
                        + " VALUES (\""+familiar.Nombre_Apellidos + "\",\"" + fecha_Nacimiento + "\",\"" 
                        + familiar.Ocupacion + "\")");
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

            /*Obtenemos el Codigo_Familiar asignado*/
            familiar.Cod_Familiar = ONG.buscarFamiliar(datosFamiliar.Nombre_Apellidos, datosFamiliar.Fecha_Nacimiento).Cod_Familiar;
        }

        /*Se crea relacion familiar*/
        Parentesco relacion_familiar = new Parentesco(familiar.Cod_Familiar, datosBeneficiario.NIF_CIF, parentesco);

        /*Se agrega la relación familiar al sistema*/
        con.conectarBD();
        try {
            instruccion = (Statement) con.conexion().createStatement();
            
            /*Introducimos al nuevo Familiar en el sistema*/
            instruccion.executeUpdate("INSERT INTO Parentesco "
                    + " VALUES (\""+relacion_familiar.Cod_Familiar  + "\",\"" + relacion_familiar.DNI_Beneficiario + "\",\"" 
                    + relacion_familiar.Parentesc + "\")");
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
    
    /*
     * ConfirmarEliminacionFamiliar // ConfirmarEliminacion
     */
    static public void confirmarEliminacion(){

        /* Buscamos Beneficiario en el sistema */
        Beneficiario beneficiario = ONG.buscarBeneficiario(datosBeneficiario.NIF_CIF);

        /* Buscamos el familiar del beneficiario*/
        /* buscarFamiliar  == buscarParentesco */
        Parentesco parentesc = beneficiario.buscarParentesco(NombreApellidosFamiliar);

        /*Eliminamos el parentesco*/
        con.conectarBD();
        
        try {
            Statement instruccion = (Statement) con.conexion().createStatement();

            /*Eliminamos el parentesco que guarda con el familiar*/
            instruccion.executeUpdate("DELETE FROM Parentesco WHERE"
                    + " Cod_Familiar = " + parentesc.Cod_Familiar + " and DNI_CIF= \""
                    + parentesc.DNI_Beneficiario+"\"");
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
 
    static public ArrayList<Familiar> iniciarConsultarFamiliar(){
        ArrayList<Familiar> familiares;

        /*Obtenemos el beneficiario*/
        Beneficiario beneficiario = ONG.buscarBeneficiario(NIF_Beneficiario);

        /*Obtenemos la lista de familiares*/
        familiares = beneficiario.consultarFamiliares();
        
        return familiares;
    }
    
    static public ArrayList consultarFamiliar(String Nombre_Apellidos){

        /*Obtenemos el beneficiario*/
        Beneficiario beneficiario = ONG.buscarBeneficiario(NIF_Beneficiario);  

         /*Buscamos el familiar cuyo nombre se indica*/
        Familiar familiar = beneficiario.buscarFamiliar(Nombre_Apellidos);

        /*Obtenemos los datos que faltan, parentesco*/
        Parentesco parentescoFamiliar = familiar.obtenerDatosFamiliar();

        /*Agrupamos los datos del familiar para proceder al envio
            1. Fatos del familiar
            2. Parentesco con el beneficiario
            */
        ArrayList datos_Familiar = new ArrayList();
        datos_Familiar.add(familiar);
        datos_Familiar.add(parentescoFamiliar);

        return datos_Familiar;
    }
    
    static public void modificarDatosFamiliar (String Nombre_Apellidos, Familiar nuevosDatosFamiliar, String parentesco){
        actualizarFamiliar(Nombre_Apellidos, nuevosDatosFamiliar, parentesco);
    }
    
    static private void actualizarFamiliar (String Nombre_Apellidos, Familiar nuevosDatosFamiliar, String parentesco){
        /* Buscamos Beneficiario en el sistema */
        Beneficiario beneficiario = ONG.buscarBeneficiario(datosBeneficiario.NIF_CIF);
        
        /*Buscamos el familiar*/
        Familiar familiar = beneficiario.buscarFamiliar(Nombre_Apellidos);
        
        /*Cambiar datos Familiar */
        familiar.cambiarDatosFamiliar(nuevosDatosFamiliar.Nombre_Apellidos, nuevosDatosFamiliar.Fecha_Nacimiento,
                nuevosDatosFamiliar.Ocupacion, parentesco);
    }

    static public void introducirDatosFamiliar(String Nombre_Apellidos, Date Fecha_Nac, String Parentesco, String Ocupacion){
        datosFamiliar = new Familiar (Nombre_Apellidos,Fecha_Nac,Ocupacion);
        parentesco = Parentesco;
    }
    
    /*----------------------------------Otros----------------------------------*/
    
    static public boolean introducirDNIVoluntario(String DNI_Voluntario){
        
        NIF_Voluntario = DNI_Voluntario;
        
        /*Comprobamos si el Voluntario existe */
        return ONG.comprobarExistenciaVoluntario(NIF_Voluntario);
    }
    
}