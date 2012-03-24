/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Usuarios.Beneficiario;

import diaketas.Usuarios.Accion;
import diaketas.Usuarios.ONG;
import diaketas.Usuarios.Voluntario.Gestor_de_voluntarios;
import java.util.ArrayList;
import java.util.Date;

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
    

    /*---------------------------Beneficiario----------------------------------*/
    static public boolean introducirDNIBeneficiario (String DNI_Beneficiario){
        
        NIF_Beneficiario = DNI_Beneficiario;
        
        /*Comprobamos si el Beneficiario existe */
        return ONG.comprobarExistenciaBeneficiario(DNI_Beneficiario);        
    }

    static public boolean introducirDatosBeneficiario(String NIF_CIF, String Nombre, String Apellidos, Date FechaNac, String Localidad, String Email, int Telefono,
                                        String Nacionalidad, String Estado_civil, String Domicilio, int Codigo_Postal, Date Fecha_Inscripcion, 
                                        String Motivo, Double Precio_Vivienda, String Tipo_Vivienda,
                                        String NIF_Vol){
        
        /*Se almacenan los datos del beneficiario y NIF_Voluntario en el sistema */
        datosBeneficiario = new Beneficiario (NIF_CIF, Nombre, Apellidos, FechaNac, Localidad, 1, new Date(), Email, Telefono, Nacionalidad,
                                    Estado_civil, Domicilio, Codigo_Postal, "", Fecha_Inscripcion, "", Motivo, Precio_Vivienda, Tipo_Vivienda);
        NIF_Voluntario = NIF_Vol;
        
        /*Devuelve la existencia del voluntario*/
        //return ONG.comprobarExistenciaVoluntario(NIF_Voluntario);
        return true;
    }

    static public Beneficiario consultarBeneficiario (String DNI){
        
        /*Actualimos NIF*/
        NIF_Beneficiario = DNI;

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
  
    static private void modificarBeneficiario(){
        
        /*Buscamos beneficiario*/
        Beneficiario beneficiario = ONG.buscarBeneficiario(datosBeneficiario.NIF_CIF);

        beneficiario.cambiarDatosBeneficiario(datosBeneficiario.NIF_CIF, datosBeneficiario.Nombre,
                datosBeneficiario.Apellidos, datosBeneficiario.FechaNac, datosBeneficiario.Localidad,  datosBeneficiario.Email,
                datosBeneficiario.Telefono, datosBeneficiario.Nacionalidad, datosBeneficiario.Estado_civil,
                datosBeneficiario.Domicilio, datosBeneficiario.Codigo_Postal, 
                datosBeneficiario.Fecha_Inscripcion, datosBeneficiario.Motivo,
                datosBeneficiario.Precio_Vivienda, datosBeneficiario.Tipo_Vivienda);
    }
    static public void confirmarAltaBeneficiario(){
        
        /*Crear beneficiario*/
        Beneficiario nuevoBeneficiario = Beneficiario.crearBeneficiario(datosBeneficiario.NIF_CIF, datosBeneficiario.Nombre,
                datosBeneficiario.Apellidos, datosBeneficiario.FechaNac, datosBeneficiario.Localidad,  datosBeneficiario.Email,
                datosBeneficiario.Telefono, datosBeneficiario.Nacionalidad, datosBeneficiario.Estado_civil,
                datosBeneficiario.Domicilio, datosBeneficiario.Codigo_Postal, 
                datosBeneficiario.Fecha_Inscripcion, datosBeneficiario.Motivo,
                datosBeneficiario.Precio_Vivienda, datosBeneficiario.Tipo_Vivienda);

        /*Registrar Operacion*/
        Gestor_de_beneficiarios.RegistrarOperacion(NIF_Voluntario, datosBeneficiario.NIF_CIF, "Alta Beneficiario");
        
        /*Registrar Beneficiario*/
        ONG.agregarNuevoBeneficiario(nuevoBeneficiario);
    }
    
    static public void confirmarBajaBeneficiario(){
        /*Registrar Operacion*/
        Gestor_de_beneficiarios.RegistrarOperacion(NIF_Voluntario, NIF_Beneficiario, "Baja Beneficiario");
        
        /*Eliminar beneficiario*/
        Gestor_de_beneficiarios.eliminarBeneficiario(NIF_Beneficiario);
    }
    
    static public void confirmarModificacionBeneficiario(){
         /*Registrar Operacion*/
        Gestor_de_beneficiarios.RegistrarOperacion(NIF_Voluntario, datosBeneficiario.NIF_CIF, "Modificar Beneficiario");   
    
        /*Modificar beneficiario*/
        modificarBeneficiario();
    }
   
    /*--------------------------------Familiar---------------------------------*/
    
    static public void seleccionarFamiliar(String Nombre_Apellidos){
        NombreApellidosFamiliar = Nombre_Apellidos;
    }
    /*
     * ConfirmarAltaFamiliar // ConfirmarInsercion
     */
    static public void confirmarAltaFamiliar(){   
        Familiar familiar;
        
        /*Se busca si el familiar ya existe*/
        familiar = ONG.buscarFamiliar(datosFamiliar.Nombre_Apellidos, datosFamiliar.Fecha_Nacimiento);

        /*Si No existe, se crea el nuevo familiar y se añade*/
        if (familiar == null){
            familiar = new Familiar(datosFamiliar.Nombre_Apellidos, datosFamiliar.Fecha_Nacimiento, datosFamiliar.Ocupacion);
            
            /*Agregamos el nuevo familiar al sistema*/
            ONG.agregarNuevoFamiliar(familiar);

            /*Obtenemos el Codigo_Familiar asignado*/
            familiar.Cod_Familiar = ONG.buscarFamiliar(datosFamiliar.Nombre_Apellidos, datosFamiliar.Fecha_Nacimiento).Cod_Familiar;
        }

        /*Se crea relacion familiar*/
        Parentesco relacion_familiar = new Parentesco(familiar.Cod_Familiar, datosBeneficiario.NIF_CIF, parentesco);

        /*Se agrega la relación familiar al sistema*/
        ONG.asociarParentesco(relacion_familiar);
    }    
    
    /*
     * ConfirmarEliminacionFamiliar // ConfirmarEliminacion
     */
    static public void confirmarEliminacionFamiliar(){

        /* Buscamos Beneficiario en el sistema */
        Beneficiario beneficiario = ONG.buscarBeneficiario(datosBeneficiario.NIF_CIF);

        /* Buscamos el familiar del beneficiario*/
        Familiar familiar = beneficiario.buscarFamiliar(NombreApellidosFamiliar);

        /*Eliminamos Familiar*/
        familiar.eliminarFamiliar();
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

    static public void RegistrarOperacion(String DNI_Voluntario, String DNI, String Tipo){
  
        /*Nueva acción con Dni de voluntario y beneficiario asociado, junto con fecha actual*/
        Accion ac = new Accion(DNI_Voluntario, DNI, Tipo, new Date());
        
        /*Se guarda la accion en el sistema*/
        ONG.agregarAccion(ac);
    }
    
    static public boolean introducirDNIVoluntario(String DNI_Voluntario){
        
        NIF_Voluntario = DNI_Voluntario;
        
        /*Comprobamos si el Voluntario existe */
        //return ONG.comprobarExistenciaVoluntario(NIF_Voluntario);
        
        return true;
    }
    
}