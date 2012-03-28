/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.Gestores;

import com.mysql.jdbc.Statement;
import diaketas.ConexionBD;
import diaketas.Modelo.ONG.ONG;
import diaketas.Modelo.ONG.Beneficiario;
import diaketas.Modelo.ONG.Familiar;
import diaketas.Modelo.ONG.Parentesco;
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
public class Gestor_de_beneficiarios implements iGestorBeneficiarios{
    
    
    Beneficiario datosBeneficiario;
    public String NIF_Voluntario;
    public String NIF_Beneficiario;
    Familiar datosFamiliar;
    String NombreApellidosFamiliar;
    String parentesco;
    Statement instruccion;
    
    ConexionBD con = new ConexionBD();

    
    public Gestor_de_beneficiarios() {
    }

    
    /*---------------------------Beneficiario----------------------------------*/
    public boolean introducirDNIBeneficiario (String DNI_Beneficiario){
        
        NIF_Beneficiario = DNI_Beneficiario;
        
        /*Comprobamos si el Beneficiario existe */
        return comprobarExistenciaBeneficiario(DNI_Beneficiario);        
    }

    public boolean introducirDatosBeneficiario(String NIF_CIF, String Nombre,
            String Apellidos, Date FechaNac, String Localidad, int Activo, Date Fecha_Desac,
            String Email, int Telefono, String Nacionalidad,
            String Estado_civil, String Domicilio, int Codigo_Postal,
            Date Fecha_Inscripcion, int Expediente, String Motivo,
            Double Precio_Vivienda, String Tipo_Vivienda, String Observaciones_Datos_Personales,
            String Observaciones_Familiares, String Observaciones_Vivienda,
            String Ciudad_Nacimiento, String Situacion_Economica, String Nivel_Estudios,
            String Profesion, String Experiencia_Laboral, String NIF_Vol){
        NIF_Beneficiario = NIF_CIF;
        
        /*Se almacenan los datos del beneficiario y NIF_Voluntario en el sistema */
        datosBeneficiario = new Beneficiario (NIF_CIF, Nombre, Apellidos, FechaNac,
                Localidad, Activo, Fecha_Desac, Email, Telefono, Nacionalidad, Estado_civil,
                Domicilio, Codigo_Postal, Fecha_Inscripcion, Expediente, Motivo,
                Precio_Vivienda, Tipo_Vivienda, Observaciones_Datos_Personales,
                Observaciones_Familiares, Observaciones_Vivienda, Ciudad_Nacimiento,
                Situacion_Economica, Nivel_Estudios,Profesion, Experiencia_Laboral);
        NIF_Voluntario = NIF_Vol;
        
        /*Devuelve la existencia del voluntario*/
        return diaketas.diaketas.gestorVoluntarios.comprobarExistenciaVoluntario(NIF_Voluntario);
    }
    
    public Boolean comprobarExistenciaBeneficiario(String DNI){
        
        /*Buscamos el beneficiario*/
        Beneficiario beneficiario = diaketas.diaketas.ong.buscarBeneficiario(DNI);
        
        if (beneficiario !=  null)
            return (beneficiario.Activo == 1);
        else
            return false;
    }
    
    public Beneficiario consultarBeneficiario (String DNI){

        /*Actualimos NIF*/
        NIF_Beneficiario = DNI;
        
        datosBeneficiario = null;

        /*Si existe el beneficiario obtenemos los datos del beneficiario y la lista de familiares*/
        if (comprobarExistenciaBeneficiario(DNI)){
            
            /*Obtenemos los datos del beneficiario*/
            datosBeneficiario = diaketas.diaketas.ong.buscarBeneficiario(DNI);
        }

        return datosBeneficiario;
    }
  
    private void eliminarBeneficiario(String DNI){

        /*Obtenemos el beneficiario*/
        datosBeneficiario = diaketas.diaketas.ong.buscarBeneficiario(DNI);  

        /*Desactivamos al usuario*/
        datosBeneficiario.desactivarUsuario(new Date());
    }
  
    private void modificarBeneficiario(Beneficiario nuevosDatosBeneficiario){
        
        /*Buscamos beneficiario*/
        datosBeneficiario = diaketas.diaketas.ong.buscarBeneficiario(datosBeneficiario.NIF_CIF);

        /*Modificamos sus datos*/
        datosBeneficiario.cambiarDatosBeneficiario(nuevosDatosBeneficiario);
    }

    public void confirmarAltaBeneficiario(){
        
        /*Crear beneficiario*/
        Beneficiario nuevoBeneficiario =  new Beneficiario(datosBeneficiario);
        
        /*Registrar Beneficiario*/
        diaketas.diaketas.ong.agregarNuevoBeneficiario(nuevoBeneficiario);
        
        /*Registrar Operacion*/
        diaketas.diaketas.gestorHistoriales.RegistrarOperacion(NIF_Voluntario, datosBeneficiario.NIF_CIF, "Alta Beneficiario");
        
        /*Registrar beneficiario*/
        /*Beneficiario ya tiene asociado Vivienda, email y telefono*/
    }
    
    public void confirmarBajaBeneficiario(){
        /*Registrar Operacion*/
        diaketas.diaketas.gestorHistoriales.RegistrarOperacion(NIF_Voluntario, NIF_Beneficiario, "Baja Beneficiario");
        
        /*Eliminar beneficiario*/
        eliminarBeneficiario(NIF_Beneficiario);
    }
    
    public void confirmarModificacionBeneficiario(){
         /*Registrar Operacion*/
        diaketas.diaketas.gestorHistoriales.RegistrarOperacion(NIF_Voluntario, datosBeneficiario.NIF_CIF, "Modificar Beneficiario");   
    
        /*Modificar beneficiario*/
        modificarBeneficiario(datosBeneficiario);
    }
   
    /*--------------------------------Familiar---------------------------------*/
    
    public void seleccionarFamiliar(String Nombre_Apellidos){
        NombreApellidosFamiliar = Nombre_Apellidos;
    }
   
    /*
     * ConfirmarAltaFamiliar // ConfirmarInsercion
     */
    public void confirmarInsercion(){   
        Familiar familiar;
        
        /*Se busca si el familiar ya existe*/
        familiar = diaketas.diaketas.ong.buscarFamiliar(datosFamiliar.Nombre_Apellidos, datosFamiliar.Fecha_Nacimiento);

        /*Si No existe, se crea el nuevo familiar y se añade*/
        if (familiar == null){
            familiar = new Familiar(datosFamiliar.Nombre_Apellidos, datosFamiliar.Fecha_Nacimiento, datosFamiliar.Ocupacion);
            
            /*Agregar familiar*/
            datosBeneficiario.agregarFamiliar(familiar);
        }

        /*Se crea relacion familiar*/
        Parentesco relacion_familiar = new Parentesco(familiar.Cod_Familiar, datosBeneficiario.NIF_CIF, parentesco);

        familiar.agregarParentesco(relacion_familiar);
    }    
    
    /*
     * ConfirmarEliminacionFamiliar // ConfirmarEliminacion
     */
    public void confirmarEliminacion(){

        /* Buscamos Beneficiario en el sistema */
        datosBeneficiario = diaketas.diaketas.ong.buscarBeneficiario(datosBeneficiario.NIF_CIF);

        /* Buscamos el familiar del beneficiario*/
        /* buscarFamiliar  == buscarParentesco */
        Parentesco parentesc = datosBeneficiario.buscarParentesco(NombreApellidosFamiliar);

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
 
    public ArrayList<Familiar> iniciarConsultarFamiliar(){
        ArrayList<Familiar> familiares;

        /*Obtenemos el beneficiario*/
        datosBeneficiario = diaketas.diaketas.ong.buscarBeneficiario(NIF_Beneficiario);

        /*Obtenemos la lista de familiares*/
        familiares = consultarFamiliares();
        
        return familiares;
    }
    
    public ArrayList<Familiar> inicioModificarFamiliar(){
        ArrayList<Familiar> familiares;

        /*Obtenemos el beneficiario*/
        datosBeneficiario = diaketas.diaketas.ong.buscarBeneficiario(NIF_Beneficiario);

        /*Obtenemos la lista de familiares*/
        familiares = consultarFamiliares();
        
        return familiares;
    }
    
    public ArrayList consultarFamiliar(String Nombre_Apellidos){

        /*Obtenemos el beneficiario*/
        datosBeneficiario = diaketas.diaketas.ong.buscarBeneficiario(NIF_Beneficiario);  

         /*Buscamos el familiar cuyo nombre se indica*/
        datosFamiliar = datosBeneficiario.buscarFamiliar(Nombre_Apellidos);

        /*Obtenemos los datos que faltan, parentesco*/
        Parentesco parentescoFamiliar = obtenerDatosFamiliar();

        /*Agrupamos los datos del familiar para proceder al envio
            1. Fatos del familiar
            2. Parentesco con el beneficiario
            */
        ArrayList datos_Familiar = new ArrayList();
        datos_Familiar.add(datosFamiliar);
        datos_Familiar.add(parentescoFamiliar);

        return datos_Familiar;
    }
    
    public void modificarDatosFamiliar (String Nombre_Apellidos, Familiar nuevosDatosFamiliar, String parentesco){
        actualizarFamiliar(Nombre_Apellidos, nuevosDatosFamiliar, parentesco);
    }
    
    private void actualizarFamiliar (String Nombre_Apellidos, Familiar nuevosDatosFamiliar, String parentesco){
        /* Buscamos Beneficiario en el sistema */
        datosBeneficiario = diaketas.diaketas.ong.buscarBeneficiario(datosBeneficiario.NIF_CIF);
        
        /*Buscamos el familiar*/
        datosFamiliar = datosBeneficiario.buscarFamiliar(Nombre_Apellidos);
        
        /*Cambiar datos Familiar */
        datosFamiliar.cambiarDatosFamiliar(nuevosDatosFamiliar.Nombre_Apellidos, nuevosDatosFamiliar.Fecha_Nacimiento,
                nuevosDatosFamiliar.Ocupacion, parentesco);
    }

    public void introducirDatosFamiliar(String Nombre_Apellidos, Date Fecha_Nac, String Parentesco, String Ocupacion){
        datosFamiliar = new Familiar (Nombre_Apellidos,Fecha_Nac,Ocupacion);
        parentesco = Parentesco;
    }

    private Parentesco obtenerDatosFamiliar(){
        con.conectarBD();
        
        Parentesco parentesco = null;

        //REVISAR
         try {
            instruccion = (Statement) con.conexion().createStatement();
            
            /*Obtenemos el parentesco del familiar con respecto al beneficiario*/
            ResultSet rs = instruccion.executeQuery("Select p.Parentesco from Parentesco p WHERE "
                    + "DNI_CIF = \""+datosBeneficiario.NIF_CIF+"\" and "
                    + " Cod_Familiar="+datosFamiliar.Cod_Familiar);
         
            if (rs.next()){
                parentesco = new Parentesco(datosFamiliar.Cod_Familiar,
                        datosBeneficiario.NIF_CIF,rs.getString(1));
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
    
    public ArrayList<Familiar> consultarFamiliares(){
        con.conectarBD();
        ArrayList<Familiar> familiares = new ArrayList<Familiar>();

        
        try {
            instruccion = (Statement) con.conexion().createStatement();
            ResultSet rs = instruccion.executeQuery("Select * From Familiar f, Parentesco p"
                    + " WHERE f.Cod_Familiar = p.Cod_Familiar and DNI_CIF = \""+ datosBeneficiario.NIF_CIF+"\"");
         
            while (rs.next()){
                /*Creamos un familiar con los datos*/
                Familiar familiar = new Familiar (rs.getString(2),rs.getDate(3),rs.getString(4));
                /*Indicamos su Codigo Interno*/
                familiar.Cod_Familiar = rs.getInt(1);
                /*Agregamos a la lista*/
                familiares.add(familiar);
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
                    Logger.getLogger(ONG.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }     
        
        return familiares;
    }
   
    /*----------------------------------Otros----------------------------------*/
    
    public boolean introducirDNIVoluntario(String DNI_Voluntario){
        
        NIF_Voluntario = DNI_Voluntario;
        
        /*Comprobamos si el Voluntario existe */
        return diaketas.diaketas.gestorVoluntarios.comprobarExistenciaVoluntario(NIF_Voluntario);
    }
    
}
