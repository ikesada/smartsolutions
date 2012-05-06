/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.Gestores;

import diaketas.Modelo.ONG.*;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Cesar
 */
public class Gestor_de_ofertas implements iGestorOfertas{
    
    
    /**
     * Oferta sobre la que trabaja el Gestor
     */
    public static Oferta O;
    
    /**
     * Beneficiario sobre el que trabaja el Gestor
     */
    public static Beneficiario B;
    
    
    /**
     * DNI del responsable (voluntario) que realiza la operacion
     */
    private String dniV;
    
    
    /**
     * NIF del Voluntario que esta trabajando actualmente sobre el sistema
     */
    public static String NIF_Voluntario;  
    
    
    
    
    
    
    /*------------------OPERACIONES------------------*/
    
    /**
     * 
     * Funcion que devuelve el objeto Oferta con el que trabaja el Gestor
     * @return Devuelve el objeto Oferta
     */ 
    @Override
    public Oferta consultarDatosOferta()
    {
        
        Oferta datosOferta = O.obtenerDatosOferta();

        return datosOferta;
    }
    
    
    
    /**
     * 
     * Funcion que registra una accion de modificacion en el historial
     */ 
    @Override
    public void finModificarOferta()
    {
        registrarOperacionOfertas( NIF_Voluntario, O.cod_oferta, "Modificacion Oferta" );
    }
    

    /**
     * 
     * Funcion que registra una accion de eliminación en el historial
     */ 
    @Override
    public void finEliminarOferta()
    {
        registrarOperacionOfertas( NIF_Voluntario, O.cod_oferta, "Eliminación Oferta" );
    }
    
    
    /**
     * 
     * Funcion que registra una accion de agregación en el historial
     */ 
    @Override
    public void finAgregarOferta()
    {
        registrarOperacionOfertas( NIF_Voluntario, O.cod_oferta, "Agregar Oferta" );
    }
    

    /**
     * Funcion que modifica los datos asociados a la Oferta
     * @param Cod_Oferta Nuevo codigo a asociar a la oferta
     * @param Concepto Nuevo concepto a asociar a la oferta
     * @param FechaOf Nueva fecha a asociar a la oferta
     * @param Activada Nuevo valor de activacion a asociar a la oferta
     * @param Poblacion Nueva poblacion a asociar a la oferta 
     * @param Num_vacantes Nuevo numero de plazas vacantes a asociar a la oferta
     * @param Descripcion Nueva descripcion a asociar a la oferta
     * @param Req_minimos Nuevos requisitos minimos a asociar a la oferta
     * @param Tipo_contrato Nuevo tipo de contrato a asociar a la oferta
     * @param Jornada_laboral Nueva jornada laboral a asociar a la oferta
     * @param Salario Nuevo salario a asociar a la oferta
     * @param DNI_donante Nuevo dni de Donante a asociar a la oferta
     * @param Observaciones Nuevas observaciones a asociar a la oferta
     * @param dniV DNI del Voluntario que realiza la modificacion
     * @return Devuelve true si ha habido algun error al realizar la operacion
     */             
    @Override
    public Boolean introducirOferta( Integer Cod_Oferta, String Concepto, Date FechaOf, Integer Activada, String Poblacion, Integer Num_vacantes, String Descripcion, String Req_minimos, String Tipo_contrato, Integer Jornada_laboral, Double Salario, String DNI_donante, String Observaciones, String dniV )
    {
        
        
        
        Boolean error=true;
        
        Boolean encontradoUsuario = false;
        
        encontradoUsuario = ONG.gestorDonantes.comprobarDniDonante( DNI_donante );
        
        if( encontradoUsuario )
        {
            encontradoUsuario = ONG.gestorVoluntarios.comprobarExistenciaVoluntario( dniV );
        }
        
        if( encontradoUsuario ) //si ha encontrado a los dos usuarios, hago la modificacion sobre la oferta
        {
            
            //guardo en el gestor_de_ofertas cual es el usuario actual del sistema y modifico la oferta guardada
            NIF_Voluntario = dniV;
        
            error = confirmarModificacion( Cod_Oferta, Concepto, FechaOf, Activada, Poblacion, Num_vacantes, Descripcion, Req_minimos, Tipo_contrato, Jornada_laboral, Salario, DNI_donante, Observaciones );   //devuelve true si hay algun error
        }
        
        return error;

    
    }
    
    
    
    /**
     * Funcion que modifica los datos asociados a la Oferta
     * @param Cod_Oferta Nuevo codigo a asociar a la oferta
     * @param Concepto Nuevo concepto a asociar a la oferta
     * @param FechaOf Nueva fecha a asociar a la oferta
     * @param Activada Nuevo valor de activacion a asociar a la oferta
     * @param Poblacion Nueva poblacion a asociar a la oferta 
     * @param Num_vacantes Nuevo numero de plazas vacantes a asociar a la oferta
     * @param Descripcion Nueva descripcion a asociar a la oferta
     * @param Req_minimos Nuevos requisitos minimos a asociar a la oferta
     * @param Tipo_contrato Nuevo tipo de contrato a asociar a la oferta
     * @param Jornada_laboral Nueva jornada laboral a asociar a la oferta
     * @param Salario Nuevo salario a asociar a la oferta
     * @param DNI_donante Nuevo dni de Donante a asociar a la oferta
     * @param Observaciones Nuevas observaciones a asociar a la oferta
     * @param dniV DNI del Voluntario que realiza la modificacion
     * @return Devuelve true si ha habido algun error al realizar la operacion
     */             
    @Override
    public Boolean crearOferta(String Concepto, Date FechaOf, Integer Activada, String Poblacion, Integer Num_vacantes, String Descripcion, String Req_minimos, String Tipo_contrato, Integer Jornada_laboral, Double Salario, String DNI_donante, String Observaciones, String dniV )
    {
        
        Boolean error=true;
        
        Boolean encontradoUsuario = false;
        encontradoUsuario = ONG.gestorDonantes.comprobarDniDonante( DNI_donante );
        
        if( encontradoUsuario )
        {
            encontradoUsuario = ONG.gestorVoluntarios.comprobarExistenciaVoluntario( dniV );
        }
        
        if( encontradoUsuario ) //si ha encontrado a los dos usuarios, hago la modificacion sobre la oferta
        {
            error = false;
            //guardo en el gestor_de_ofertas cual es el usuario actual del sistema y modifico la oferta guardada
            NIF_Voluntario = dniV;
        
            O = new Oferta(-1, Concepto, FechaOf, Activada, Poblacion, Num_vacantes, Descripcion, Req_minimos, Tipo_contrato, Jornada_laboral, Salario, Observaciones, DNI_donante);
            O.agregar();
            //O.agregar(Cod_Oferta, Concepto, FechaOf, Activada, Poblacion, Num_vacantes, Descripcion, Req_minimos, Tipo_contrato, Jornada_laboral, Salario, DNI_donante, Observaciones);
            //error = confirmarModificacion( Cod_Oferta, Concepto, FechaOf, Activada, Poblacion, Num_vacantes, Descripcion, Req_minimos, Tipo_contrato, Jornada_laboral, Salario, DNI_donante, Observaciones );   //devuelve true si hay algun error
        }
        
        return error;

    
    }
    
    
    
    
    
    /**
     * Funcion que confirma la modificacion de los datos asociados a la Oferta
     * @param Cod_Oferta Nuevo codigo a asociar a la oferta
     * @param Concepto Nuevo concepto a asociar a la oferta
     * @param FechaOf Nueva fecha a asociar a la oferta
     * @param Activada Nuevo valor de activacion a asociar a la oferta
     * @param Poblacion Nueva poblacion a asociar a la oferta 
     * @param Num_vacantes Nuevo numero de plazas vacantes a asociar a la oferta
     * @param Descripcion Nueva descripcion a asociar a la oferta
     * @param Req_minimos Nuevos requisitos minimos a asociar a la oferta
     * @param Tipo_contrato Nuevo tipo de contrato a asociar a la oferta
     * @param Jornada_laboral Nueva jornada laboral a asociar a la oferta
     * @param Salario Nuevo salario a asociar a la oferta
     * @param DNI_donante Nuevo dni de Donante a asociar a la oferta
     * @param Observaciones Nuevas observaciones a asociar a la oferta
     * @return Devuelve true si ha habido algun error al realizar la operacion
     */     
    @Override
    public Boolean confirmarModificacion(Integer Cod_Oferta, String Concepto, Date FechaOf, Integer Activada, String Poblacion, Integer Num_vacantes, String Descripcion, String Req_minimos, String Tipo_contrato, Integer Jornada_laboral, Double Salario, String DNI_donante, String Observaciones)
    {
        
        Boolean hay_error;
        
        //la operacion "modificar" devuelve true si todo ha ido bien
        //modifico la Oferta "O" guardada en el gestor_de_ofertas
        Boolean exito = O.modificar(Cod_Oferta, Concepto, FechaOf, Activada, Poblacion, Num_vacantes, Descripcion, Req_minimos, Tipo_contrato, Jornada_laboral, Salario, DNI_donante, Observaciones);
        
        if( exito )
        {
            hay_error=false;
        }
        else
        {
            hay_error=true;
        }
        
        return hay_error;
    }
    
  
    
    /*
     * Función que elimina la Oferta de empleo
     */
    @Override
    public Boolean eliminarOferta(){
        Boolean hay_error;
        
        //la operacion "eliminar" devuelve true si todo ha ido bien
        //elimino la Oferta "O" guardada en el gestor_de_ofertas
        Boolean exito = O.eliminar();
        
        if( exito )
        {
            hay_error=false;
        }
        else
        {
            hay_error=true;
        }
        
        return hay_error;
    }
    

    /**
     * Funcion que obtiene la lista de ofertas que satisfacen unos criterios de búsqueda basados
     * en el código, concepto y población de las mismas.
     * @return Devuelve la lista de ofertas que cumple los criterios.
     */
    
    @Override
    public ArrayList<Oferta> filtrarOfertas(int codigo, String concepto, String poblacion) {
        return diaketas.diaketas.ong.obtenerOfertas(codigo, concepto, poblacion);
    }

    /**
     * Funcion que indica al gestor la oferta que ha sido seleccionada por el voluntario.
     */
    
    @Override
    public void seleccionarOferta(int codOferta) {
        O = diaketas.diaketas.ong.buscarOferta(codOferta);
    }
    
    /**
     * Funcion que obtiene las ofertas asociadas a un determinado beneficiario
     * @return Devuelve la lista de las ofertas relacionadas con un beneficiario concreto
     */

    @Override
    public ArrayList<Oferta> obtenerListaOfertas(String dniBeneficiario, boolean[] existeBeneficiario) {
        ArrayList<Oferta> ofertas_asociadas = new ArrayList();
        boolean existe;
        
        existe = diaketas.diaketas.ong.gestorBeneficiarios.comprobarExistenciaBeneficiario(dniBeneficiario);
        
        existeBeneficiario[0] = existe;
        
        if(existe) {
            B = diaketas.diaketas.ong.buscarBeneficiario(dniBeneficiario);
            ofertas_asociadas = B.obtenerOfertas();
        }
        
        return ofertas_asociadas;
    }

    /**
     * Función que comprueba si un determinado voluntario esta autorizado para realizar una operación
     * @param dniVoluntario DNI del voluntario sobre el que se quiere consultar
     * @return Indica si el voluntario está autorizado o no
     */
    @Override
    public boolean comprobarVoluntario(String dniVoluntario) {
        boolean exito = diaketas.diaketas.ong.gestorVoluntarios.comprobarExistenciaVoluntario(dniVoluntario);
        
        if(exito) {
            // Se almacena el dni del responsable
            dniV = dniVoluntario;
        }
        
        return exito;
    }
    
    /**
     * Finaliza la operación de asociar un beneficiario a una determinada oferta.
     */
    
    @Override
    public void finAsociar() {
        // Se agrega la oferta al beneficiario
        B.AgregarOferta(O);
        // Registrar la accion por parte del voluntario
        registrarOperacionOfertas(dniV,O.cod_oferta,"Asociar Beneficiario");
    }

    /**
     * Registra una acción realizada en la gestión de la bolsa de trabajo por parte de un voluntario.
     */
    @Override
    public void registrarOperacionOfertas(String NIF_CIF, int cod_oferta, String accion) {
        Voluntario responsable;
        Oferta oferta;
        Date fecha_actual = new Date();
        
        AccionOferta accion_oferta = new AccionOferta(accion,fecha_actual);
                
        responsable = diaketas.diaketas.ong.buscarVoluntario(NIF_CIF);
        oferta = diaketas.diaketas.ong.buscarOferta(cod_oferta);
        
        // Asociamos los objetos Responsables y oferta
        accion_oferta.responsable = responsable;
        accion_oferta.oferta = oferta;
        
        diaketas.diaketas.ong.agregarAccionOferta(accion_oferta);
        
    }
    
}
