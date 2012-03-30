/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.Gestores;

import diaketas.Modelo.ONG.Beneficiario;
import diaketas.Modelo.ONG.Familiar;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author kesada
 */
public interface iGestorBeneficiarios {
    
    /**
     * @brief Confirmar el alta de un beneficiario en el sistema
     */
    public void confirmarAltaBeneficiario();
    
    /**
     * @brief Confirmar el baja de un beneficiario en el sistema
     */
    public void confirmarBajaBeneficiario();
    
    /**
     * @brief Confirma la eliminacion de un familiar del beneficiario
     */
    public void confirmarEliminacion();
    
    /**
     * @brief Confirma la inserción de un nuevo familiar al beneficiario
     */
    public void confirmarInsercion();
    
    /**
     * @brief Confirmar la modificación de los datos del beneficiario en el sistema
     */
    public void confirmarModificacionBeneficiario();
    
    /**
     * @brief Consulta en el sistema los datos de un beneficiario
     * @param DNI del beneficiario del que se quieren consultar sus datos
     * @return Un objeto Beneficiario incluyendo todos los datos del beneficiario
     */
    public Beneficiario consultarBeneficiario(String dniBeneficiario);
    
    /**
     * @brief Devuelve los datos de un familiar
     * @param Nombre_Apellidos Nombre y Apellidos del familiar que se quiere consultar
     * @return Los datos de un familiar
     */
    public Familiar consultarFamiliar(String Nombre_Apellidos);

    /**
     * @brief Inicia la consulta de los familiares
     * @return Devuelve una lista con los familiares de un beneficiario
     */
    public ArrayList<Familiar> iniciarConsultarFamiliar();
    
    /**
     * @brief Incia la modificación de los familiares
     * @return Devuelve una lista con los familiares de un beneficiario
     */
    public ArrayList<Familiar> inicioModificarFamiliar();
    
    /**
     * @brief Introduce los datos del beneficiario en el gestor
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
    public boolean introducirDatosBeneficiario(String NIF_CIF, String Nombre,
            String Apellidos, Date FechaNac, String Localidad, int Activo, Date Fecha_Desac,
            String Email, int Telefono, String Nacionalidad,
            String Estado_civil, String Domicilio, int Codigo_Postal,
            Date Fecha_Inscripcion, int Expediente, String Motivo,
            Double Precio_Vivienda, String Tipo_Vivienda, String Observaciones_Datos_Personales,
            String Observaciones_Familiares, String Observaciones_Vivienda,
            String Ciudad_Nacimiento, String Situacion_Economica, String Nivel_Estudios,
            String Profesion, String Experiencia_Laboral, String NIF_Vol);
    
    /**
     * @brief Modifica los datos de un familiar
     * @param Nombre_Apellidos Nombre y apellidos del familiar que se quiere modificar
     * @param nuevosDatosFamiliar Nuevos datos del familiar
     * @param parentesco Relación de parentesco entre el familiar y el beneficiario
     */
    public void introducirDatosFamiliar(String Nombre_Apellidos, Date Fecha_Nac,
            String Parentesco, String Ocupacion);
    
    /**
     * @brief Modifica los datos de un familiar
     * @param Nombre_Apellidos Nombre y apellidos del familiar que se quiere modificar
     * @param nuevosDatosFamiliar Nuevos datos del familiar
     * @param parentesco Relación de parentesco entre el familiar y el beneficiario
     */ 
    public void modificarDatosFamiliar (String Nombre_Apellidos,
            Familiar nuevosDatosFamiliar, String parentesco);
}
