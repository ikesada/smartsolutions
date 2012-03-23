/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Usuarios.Beneficiario;

import diaketas.Usuarios.Accion;
import diaketas.Usuarios.ONG;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author kesada
 */
public class Gestor_de_beneficiarios {
    
    
    static Beneficiario datosBeneficiario;
    static String NIF_Voluntario;
    static Familiar datosFamiliar;
    static String parentesco;
    

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

    /*AKA ConfirmarInsercion()*/
    static public void confirmarAltaFamiliar(){   
        Familiar familiar;
        
        /*Se busca si el familiar ya existe*/
        familiar = ONG.buscarFamiliar(datosFamiliar.Nombre_Apellidos, datosFamiliar.Fecha_Nacimiento);
        System.out.println("A1");
        /*Si No existe, se crea el nuevo familiar y se añade*/
        if (familiar == null){
            familiar = new Familiar(datosFamiliar.Nombre_Apellidos, datosFamiliar.Fecha_Nacimiento, datosFamiliar.Ocupacion);
            ONG.agregarNuevoFamiliar(familiar);
                    System.out.println("A2");
            /*Obtenemos el Codigo_Familiar asignado*/
            familiar.Cod_Familiar = ONG.buscarFamiliar(datosFamiliar.Nombre_Apellidos, datosFamiliar.Fecha_Nacimiento).Cod_Familiar;
             System.out.println("A3");
        }

        /*Se crea relacion familiar*/
        Parentesco relacion_familiar = new Parentesco(familiar.Cod_Familiar, datosBeneficiario.NIF_CIF, parentesco);

        /*Se busca el Beneficiario*/
        ONG.asociarParentesco(relacion_familiar);            System.out.println("A4");
    }
    
    static public void introducirDatosFamiliar(String Nombre_Apellidos, Date Fecha_Nac, String Parentesco, String Ocupacion){
        datosFamiliar = new Familiar (Nombre_Apellidos,Fecha_Nac,Ocupacion);
        parentesco = Parentesco;
    }
}