/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.Gestores;

import diaketas.Modelo.ONG.Beneficiario;
import diaketas.Modelo.ONG.Familiar;
import diaketas.Modelo.ONG.Parentesco;
import java.util.ArrayList;
import java.util.Date;

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
        return diaketas.diaketas.ong.gestorVoluntarios.comprobarExistenciaVoluntario(NIF_Voluntario);
    }
    
    public Boolean comprobarExistenciaBeneficiario(String DNI){
        
        /*Buscamos el beneficiario*/
        datosBeneficiario = diaketas.diaketas.ong.buscarBeneficiario(DNI);
        
        if (datosBeneficiario !=  null)
            return (datosBeneficiario.Activo == 1);
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
            return datosBeneficiario;
        }

        return null;
    }
  
    private void eliminarBeneficiario(String DNI){

        /*Obtenemos el beneficiario*/
        //Ya es conocido
        datosBeneficiario = diaketas.diaketas.ong.buscarBeneficiario(NIF_Beneficiario);

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
        diaketas.diaketas.ong.gestorHistoriales.RegistrarOperacion(NIF_Voluntario, datosBeneficiario.NIF_CIF, "Alta Beneficiario");
        
        /*Registrar beneficiario*/
        /*Beneficiario ya tiene asociado Vivienda, email y telefono*/
    }
    
    public void confirmarBajaBeneficiario(){
        /*Registrar Operacion*/
        diaketas.diaketas.ong.gestorHistoriales.RegistrarOperacion(NIF_Voluntario, NIF_Beneficiario, "Baja Beneficiario");
        
        /*Eliminar beneficiario*/
        eliminarBeneficiario(NIF_Beneficiario);
    }
    
    public void confirmarModificacionBeneficiario(){
         /*Registrar Operacion*/
        diaketas.diaketas.ong.gestorHistoriales.RegistrarOperacion(NIF_Voluntario, datosBeneficiario.NIF_CIF, "Modificar Beneficiario");   
    
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
        
        /* Buscamos Beneficiario en el sistema */
        //Ya es conocido
        datosBeneficiario = diaketas.diaketas.ong.buscarBeneficiario(datosBeneficiario.NIF_CIF);
        
        /*Se busca si el familiar ya existe*/
        familiar = diaketas.diaketas.ong.buscarFamiliar(datosFamiliar.Nombre_Apellidos, datosFamiliar.Fecha_Nacimiento);

        /*Si No existe, se crea el nuevo familiar y se añade*/
        if (familiar == null){
            familiar = new Familiar(datosFamiliar.Nombre_Apellidos, datosFamiliar.Fecha_Nacimiento, datosFamiliar.Ocupacion);
            
            /*Agregar familiar*/
            datosBeneficiario.agregarFamiliar(familiar);
        }

        /*Se crea relacion familiar*/
        Parentesco relacion_familiar = new Parentesco(parentesco);

        familiar.agregarParentesco(relacion_familiar);
    }    
    
    /*
     * ConfirmarEliminacionFamiliar // ConfirmarEliminacion
     */
    public void confirmarEliminacion(){

        /* Buscamos Beneficiario en el sistema */
        //Ya es conocido
        datosBeneficiario = diaketas.diaketas.ong.buscarBeneficiario(datosBeneficiario.NIF_CIF);

        /* Buscamos el familiar del beneficiario*/
        /* buscarFamiliar  == buscarParentesco */
        Parentesco parentesc = datosBeneficiario.buscarParentesco(NombreApellidosFamiliar);
        
        /*Eliminamos el parentesco al familiar*/
        datosBeneficiario.buscarFamiliar(NombreApellidosFamiliar).eliminarParentesco();
        
        /*Eliminamos al familiar de la lista de familiares de beneficiario*/
        int indexFamiliar = datosBeneficiario.familiares.indexOf(datosBeneficiario.buscarFamiliar(NombreApellidosFamiliar));
        datosBeneficiario.familiares.remove(indexFamiliar);
    }
    
    public ArrayList<Familiar> iniciarConsultarFamiliar(){

        /*Obtenemos el beneficiario*/
        //Ya es conocido
        datosBeneficiario = diaketas.diaketas.ong.buscarBeneficiario(NIF_Beneficiario);

        /*Devolvemos los familiares*/
        return datosBeneficiario.familiares;
    }
    

    public ArrayList<Familiar> inicioModificarFamiliar(){
        /*Obtenemos el beneficiario*/
        //Ya es conocido
        datosBeneficiario = diaketas.diaketas.ong.buscarBeneficiario(NIF_Beneficiario);
       
        return datosBeneficiario.familiares;
    }
    
    public Familiar consultarFamiliar(String Nombre_Apellidos){
        /*Obtenemos el beneficiario*/
        //Ya es conocido
        datosBeneficiario = diaketas.diaketas.ong.buscarBeneficiario(NIF_Beneficiario);
        
        /*Devolvemos los datos del familiar*/
        return datosBeneficiario.buscarFamiliar(Nombre_Apellidos);
    }
    
    public void modificarDatosFamiliar (String Nombre_Apellidos, Familiar nuevosDatosFamiliar, String parentesco){
        actualizarFamiliar(Nombre_Apellidos, nuevosDatosFamiliar, parentesco);
    }
    
    private void actualizarFamiliar (String Nombre_Apellidos, Familiar nuevosDatosFamiliar, String parentesco){
        /* Buscamos Beneficiario en el sistema */
        //Ya es conocido
        datosBeneficiario = diaketas.diaketas.ong.buscarBeneficiario(datosBeneficiario.NIF_CIF);
        
        /*Buscamos el familiar*/
        datosFamiliar = datosBeneficiario.buscarFamiliar(Nombre_Apellidos);
        
        /*Cambiar datos Familiar */
        datosFamiliar.cambiarDatosFamiliar(nuevosDatosFamiliar.Nombre_Apellidos, nuevosDatosFamiliar.Fecha_Nacimiento,
                nuevosDatosFamiliar.Ocupacion, new Parentesco (parentesco));
    }

    public void introducirDatosFamiliar(String Nombre_Apellidos, Date Fecha_Nac, String Parentesco, String Ocupacion){
        datosFamiliar = new Familiar (Nombre_Apellidos,Fecha_Nac,Ocupacion);
        parentesco = Parentesco;
    }  
}
