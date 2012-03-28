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
    
    public void confirmarAltaBeneficiario();
    
    public void confirmarBajaBeneficiario();
    
    public void confirmarEliminacion();
    
    public void confirmarInsercion();
    
    public void confirmarModificacionBeneficiario();
    
    public Beneficiario consultarBeneficiario(String dniBeneficiario);
    
    /*
     * Devuelve un ArrayList, donde el primer objeto es de tipo familiar y
     * el segundo objeto es de tipo Parentesco
     */
    public ArrayList consultarFamiliar(String Nombre_Apellidos);

    public ArrayList<Familiar> iniciarConsultarFamiliar();
    
    public ArrayList<Familiar> inicioModificarFamiliar();
    
    public boolean introducirDatosBeneficiario(String NIF_CIF, String Nombre,
            String Apellidos, Date FechaNac, String Localidad, int Activo, Date Fecha_Desac,
            String Email, int Telefono, String Nacionalidad,
            String Estado_civil, String Domicilio, int Codigo_Postal,
            Date Fecha_Inscripcion, int Expediente, String Motivo,
            Double Precio_Vivienda, String Tipo_Vivienda, String Observaciones_Datos_Personales,
            String Observaciones_Familiares, String Observaciones_Vivienda,
            String Ciudad_Nacimiento, String Situacion_Economica, String Nivel_Estudios,
            String Profesion, String Experiencia_Laboral, String NIF_Vol);
    
    public void introducirDatosFamiliar(String Nombre_Apellidos, Date Fecha_Nac,
            String Parentesco, String Ocupacion);
    
    public boolean introducirDNIBeneficiario (String DNI_Beneficiario);
    
    public void modificarDatosFamiliar (String Nombre_Apellidos,
            Familiar nuevosDatosFamiliar, String parentesco);
}
