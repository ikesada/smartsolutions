/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.Gestores;

import diaketas.Modelo.ONG.Beneficiario;
import diaketas.Modelo.ONG.Familiar;
import diaketas.Modelo.ONG.ONG;
import java.util.ArrayList;
import java.util.Date;
 
/**
 *
 * @author kesada
 */
public class Gestor_de_beneficiarios implements iGestorBeneficiarios{
    
    
    Beneficiario datosBeneficiario;
    /**
     * 
     */
    public String NIF_Voluntario;
    /**
     * 
     */
    public String NIF_Beneficiario;
    
    Familiar datosFamiliar;
    
    String Nombre_Apellidos_Familiar;
    Date Fecha_Nacimiento_Familiar;
    
    
    /*---------------------------Beneficiario----------------------------------*/
    /**
     * Introduce el DNI del beneficiario en el gestor
     * @param DNI_Beneficiario
     * @return Boolean indicando si existe un beneficiario asociado al DNI
     */
    @Override
    public boolean introducirDNIBeneficiario (String DNI_Beneficiario){
        
        NIF_Beneficiario = DNI_Beneficiario;
        
        /*Comprobamos si el Beneficiario existe */
        return comprobarExistenciaBeneficiario(DNI_Beneficiario);        
    }

    /**
     * Introduce los datos del beneficiario en el gestor
     * @param NIF_CIF Nif del beneficiario
     * @param Nombre Nombre del beneficiario
     * @param Apellidos Apellidos del beneficiario
     * @param FechaNac Fecha de nacimiento del beneficiario
     * @param Localidad Localidad del beneficiario
     * @param Activo Indica si el usuario esta activo (1) o inactivo (0)
     * @param Fecha_Desac Fecha de desactivacion del beneficiario
     * @param Email Email del beneficiario
     * @param Telefono Telefono del beneficiario
     * @param Nacionalidad Nacionalidad del beneficiario
     * @param Estado_civil Estado civil del beneficiario
     * @param Domicilio Domicilio del beneficiario
     * @param Codigo_Postal Codigo postal del beneficiario
     * @param Fecha_Inscripcion Fecha de inscripcion del beneficiario
     * @param Expediente Expediente del beneficiario
     * @param Motivo Motivo del beneficiario
     * @param Precio_Vivienda Precio de la vivienda del beneficiario
     * @param Tipo_Vivienda Tipo de vivienda del beneficiario
     * @param Observaciones_Datos_Personales Observaciones acerca de los datos personales
     * @param Observaciones_Familiares Observaciones acerca de los familiares
     * @param Observaciones_Vivienda Observaciones acerca de la vivienda
     * @param Ciudad_Nacimiento Ciudad de nacimiento del beneficiario
     * @param Situacion_Economica Situacion economica del beneficiario
     * @param Nivel_Estudios Nivel de estudios del beneficiario
     * @param Profesion Profesion del beneficiario
     * @param Experiencia_Laboral Experiencia laboral del beneficiario
     * @param NIF_Vol Nif del voluntario que introduce los datos del beneficiario
     * @return Devuelve True o False indicando si el Voluntario que realiza la operación existe
     */
    @Override
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
        return ONG.gestorVoluntarios.comprobarExistenciaVoluntario(NIF_Voluntario);
    }
    
    /**
     * Comprueba si existe un beneficiario en el sistema con un DNI
     * @param DNI Dni del beneficiario que se quiere encontrar en el sistema
     * @return True o False indicando si se ha encontrado un beneficiario con el DNI especificado
     */
    public Boolean comprobarExistenciaBeneficiario(String DNI){
        
        /*Buscamos el beneficiario*/
        datosBeneficiario = diaketas.diaketas.ong.buscarBeneficiario(DNI);
        
        if (datosBeneficiario !=  null)
            return (datosBeneficiario.Activo == 1);
        else
            return false;
    }
    
    /**
     * Consulta en el sistema los datos de un beneficiario
     * @param DNI del beneficiario del que se quieren consultar sus datos
     * @return Un objeto Beneficiario incluyendo todos los datos del beneficiario
     */
    @Override
    public Beneficiario consultarBeneficiario (String DNI){

        /*Actualimos NIF*/
        NIF_Beneficiario = DNI;
        
        datosBeneficiario = null;

        /*Si existe el beneficiario obtenemos los datos del beneficiario y la lista de familiares*/
        if (comprobarExistenciaBeneficiario(DNI)){
            /*Obtenemos los datos del beneficiario*/
            datosBeneficiario = diaketas.diaketas.ong.buscarBeneficiario(DNI);
            return datosBeneficiario;
        }

        return null;
    }
    
   /**
     * Elimina al beneficiario con el DNI especificado del sistema
     * @param DNI del beneficiario que se quiere eliminar del sistema
     */
    private void eliminarBeneficiario(String DNI){

        /*Obtenemos el beneficiario*/
        //Ya es conocido
        NIF_Beneficiario = DNI;
        
        datosBeneficiario = diaketas.diaketas.ong.buscarBeneficiario(NIF_Beneficiario);

        /*Desactivamos al usuario*/
        datosBeneficiario.desactivarUsuario(new Date());
    }
  
    /**
     * Modifica al beneficiario con el DNI especificado del sistema
     * @param DNI del beneficiario que se quiere modificar
     */
    private void modificarBeneficiario(Beneficiario nuevosDatosBeneficiario){
        
        /*Buscamos beneficiario*/
        datosBeneficiario = diaketas.diaketas.ong.buscarBeneficiario(datosBeneficiario.NIF_CIF);

        /*Modificamos sus datos*/
        datosBeneficiario.cambiarDatosBeneficiario(nuevosDatosBeneficiario);
    }

    /**
     * Confirmar el alta de un beneficiario en el sistema
     */
    @Override
    public void confirmarAltaBeneficiario(){
        
        /*Crear beneficiario*/
        Beneficiario nuevoBeneficiario =  new Beneficiario(datosBeneficiario);
        
        /*Agregamos Beneficiario*/
        diaketas.diaketas.ong.agregarNuevoBeneficiario(nuevoBeneficiario);
        
        /*Registrar Operacion*/
        ONG.gestorHistoriales.RegistrarOperacion(NIF_Voluntario.toUpperCase(), datosBeneficiario.NIF_CIF.toUpperCase(), "Alta Beneficiario");
    }
    
    /**
     * Confirmar el baja de un beneficiario en el sistema
     */
    @Override
    public void confirmarBajaBeneficiario(String NIF_Voluntario){
        
        this.NIF_Voluntario = NIF_Voluntario;
        
        /*Registrar Operacion*/
        ONG.gestorHistoriales.RegistrarOperacion(NIF_Voluntario.toUpperCase(), NIF_Beneficiario.toUpperCase(), "Baja Beneficiario");
        
        /*Eliminar beneficiario*/
        eliminarBeneficiario(NIF_Beneficiario);
    }
    
    /**
     * Confirmar la modificación de los datos del beneficiario en el sistema
     */
    @Override
    public void confirmarModificacionBeneficiario(){
         /*Registrar Operacion*/
        ONG.gestorHistoriales.RegistrarOperacion(NIF_Voluntario.toUpperCase(), datosBeneficiario.NIF_CIF.toUpperCase(), "Modificar Beneficiario");   
    
        /*Modificar beneficiario*/
        modificarBeneficiario(datosBeneficiario);
    }
   
    /*--------------------------------Familiar---------------------------------*/
    
    /**
     * Introduce el Nombre_Apellidos del familiar que se quiere consultar en el sistema
     * @param Nombre_Apellidos Nombre_Apellidos del familiar del beneficiario que se quiere consultar
     */
    public void seleccionarFamiliar(String Nombre_Apellidos, Date Fecha_Nacimiento){
        Nombre_Apellidos_Familiar = Nombre_Apellidos;
        Fecha_Nacimiento_Familiar = Fecha_Nacimiento;
    }
   
    /**
     * Confirma la inserción de un nuevo familiar al beneficiario
     */
    @Override
    public void confirmarInsercion(){   
        Familiar familiar;
        
        /* Buscamos Beneficiario en el sistema */
        //Ya es conocido
        datosBeneficiario = diaketas.diaketas.ong.buscarBeneficiario(datosBeneficiario.NIF_CIF);
        
        familiar = new Familiar(datosFamiliar.Nombre_Apellidos, datosFamiliar.Fecha_Nacimiento, datosFamiliar.Ocupacion, datosFamiliar.parentesco);
            
        /*Agregar familiar*/
        datosBeneficiario.agregarFamiliar(familiar);

    }    
    
    /**
     * Confirma la eliminacion de un familiar del beneficiario
     */
    @Override
    public void confirmarEliminacion(){

        /* Buscamos Beneficiario en el sistema */
        //Ya es conocido
        datosBeneficiario = diaketas.diaketas.ong.buscarBeneficiario(datosBeneficiario.NIF_CIF);

        /* Buscamos el familiar del beneficiario*/
        Familiar f = datosBeneficiario.buscarFamiliar(Nombre_Apellidos_Familiar, Fecha_Nacimiento_Familiar);
        
        /*Eliminamos el familiar*/
        f.destruir();
    }
    
    /**
     * Inicia la consulta de los familiares
     * @return Devuelve una lista con los familiares de un beneficiario
     */
    @Override
    public ArrayList<Familiar> iniciarConsultarFamiliar(){

        /*Obtenemos el beneficiario*/
        //Ya es conocido
        datosBeneficiario = diaketas.diaketas.ong.buscarBeneficiario(NIF_Beneficiario);

        /*Devolvemos los familiares*/
        return datosBeneficiario.consultarFamiliares();
    }
    

    /**
     * Incia la modificación de los familiares
     * @return Devuelve una lista con los familiares de un beneficiario
     */ 
    @Override
    public ArrayList<Familiar> inicioModificarFamiliar(){
        /*Obtenemos el beneficiario*/
        //Ya es conocido
        datosBeneficiario = diaketas.diaketas.ong.buscarBeneficiario(NIF_Beneficiario);
       
        /*Devolvemos los familiares*/
        return datosBeneficiario.consultarFamiliares();
    }
    
    /**
     * Devuelve los datos de un familiar
     * @param Nombre_Apellidos Nombre y Apellidos del familiar que se quiere consultar
     * @return Los datos de un familiar
     */
    @Override
    public Familiar consultarFamiliar(String Nombre_Apellidos, Date Fecha_Nacimiento){
        /*Obtenemos el beneficiario*/
        //Ya es conocido
        datosBeneficiario = diaketas.diaketas.ong.buscarBeneficiario(NIF_Beneficiario);
        
        /*Devolvemos los datos del familiar*/
        return datosBeneficiario.buscarFamiliar(Nombre_Apellidos, Fecha_Nacimiento);
    }
    
    /**
     * Modifica los datos de un familiar
     * @param Nombre_Apellidos Nombre y apellidos del familiar que se quiere modificar
     * @param nuevosDatosFamiliar Nuevos datos del familiar
     * @param parentesco Relación de parentesco entre el familiar y el beneficiario
     */
    @Override
    public void modificarDatosFamiliar (String Nombre_Apellidos, Date Fecha_Nac, Familiar nuevosDatosFamiliar){
        actualizarFamiliar(Nombre_Apellidos, Fecha_Nac, nuevosDatosFamiliar);
    }
    
    /**
     * Actualiza los datos de un familiar
     * @param Nombre_Apellidos Nombre y apellidos del familiar que se quiere actualizar
     * @param nuevosDatosFamiliar Nuevos datos del familiar
     * @param parentesco Relación de parentesco entre el familiar y el beneficiario
     */
    private void actualizarFamiliar (String Nombre_Apellidos, Date Fecha_Nac, Familiar nuevosDatosFamiliar){
        /* Buscamos Beneficiario en el sistema */
        //Ya es conocido
        datosBeneficiario = diaketas.diaketas.ong.buscarBeneficiario(datosBeneficiario.NIF_CIF);
        
        /*Buscamos el familiar*/
        datosFamiliar = datosBeneficiario.buscarFamiliar(Nombre_Apellidos, Fecha_Nac);
        
        /*Cambiar datos Familiar */
        datosFamiliar.cambiarDatosFamiliar(nuevosDatosFamiliar.Nombre_Apellidos, nuevosDatosFamiliar.Fecha_Nacimiento,
                nuevosDatosFamiliar.Ocupacion, nuevosDatosFamiliar.parentesco);
    }

    /**
     * Introduce los datos de un nuevo familiar para el beneficiario en el gestor
     * @param Nombre_Apellidos Nombre y apellidos del familiar
     * @param Fecha_Nac Fecha de nacimiento del beneficiario
     * @param Parentesco Parentesco del beneficiario
     * @param Ocupacion Ocupación del beneficiario
     */
    @Override
    public void introducirDatosFamiliar(String Nombre_Apellidos, Date Fecha_Nac, String Parentesco, String Ocupacion){
        datosFamiliar = new Familiar (Nombre_Apellidos,Fecha_Nac,Ocupacion,Parentesco);
    }  
    
    
    
     
    /**
     * Funcion que devuelve un listado con todos los Beneficiarios del sistema
     * @return Devuelve un listado con todos los Beneficiarios del sistema
     */
    public static ArrayList<Beneficiario> obtenerBeneficiarios()
    {
        
        ArrayList<Beneficiario> usuarios = new ArrayList<Beneficiario>();
        
        usuarios = diaketas.diaketas.ong.buscarBeneficiarios();
        
        return usuarios;
        
    }
}
