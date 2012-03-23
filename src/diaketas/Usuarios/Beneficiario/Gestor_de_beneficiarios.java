/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Usuarios.Beneficiario;

import diaketas.Usuarios.Accion;
import diaketas.Usuarios.Email;
import diaketas.Usuarios.ONG;
import diaketas.Usuarios.Telefono;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author kesada
 */
public class Gestor_de_beneficiarios {
    
    
    static Beneficiario datosBeneficiario;
    static String NIF_Voluntario;

    static public boolean introducirDatosBeneficiario(String NIF_CIF, String Nombre, String Apellidos, Date FechaNac, String Localidad, String Email, int Telefono,
                                        String Nacionalidad, String Estado_civil, String Domicilio, int Codigo_Postal, Date Fecha_Inscripcion, 
                                        String Motivo, Double Precio_Vivienda, String Tipo_Vivienda,
                                        String NIF_Vol){
        
        datosBeneficiario = new Beneficiario (NIF_CIF, Nombre, Apellidos, FechaNac, Localidad, 1, new Date(), Email, Telefono, Nacionalidad,
                                    Estado_civil, Domicilio, Codigo_Postal, "", Fecha_Inscripcion, "", Motivo, Precio_Vivienda, Tipo_Vivienda);
        NIF_Voluntario = NIF_Vol;
        return ONG.comprobarExistenciaVoluntario(NIF_Voluntario);
    }

    static public void RegistrarOperacion(String DNI_Voluntario, String DNI, String Tipo){
        Accion ac = new Accion(DNI_Voluntario, DNI, Tipo, new Date());
        ONG.agregarAccion(ac);
    }

    static public void confirmarAltaBeneficiario(){
        /*Crear beneficiario*/
        Beneficiario nuevoBeneficiario = Beneficiario.crearBeneficiario(datosBeneficiario.NIF_CIF, datosBeneficiario.Nombre,
                datosBeneficiario.Apellidos, datosBeneficiario.FechaNac, datosBeneficiario.Localidad,  datosBeneficiario.Email,
                datosBeneficiario.Telefono, datosBeneficiario.Nacionalidad, datosBeneficiario.Estado_civil,
                datosBeneficiario.Domicilio, datosBeneficiario.Codigo_Postal, 
                datosBeneficiario.Fecha_Inscripcion, datosBeneficiario.Motivo,
                datosBeneficiario.Precio_Vivienda, datosBeneficiario.Tipo_Vivienda);
        
        /*Registrar Beneficiario*/
        ONG.agregarNuevoBeneficiario(nuevoBeneficiario);
        
        /*Registrar Operacion*/
        Gestor_de_beneficiarios.RegistrarOperacion(NIF_Voluntario, datosBeneficiario.NIF_CIF, "Alta_Beneficiario");
        

    }
}