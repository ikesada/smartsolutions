/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Usuarios.Beneficiario;

import diaketas.Usuarios.Email;
import diaketas.Usuarios.Telefono;
import diaketas.Usuarios.Usuarios;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author kesada
 */
public class Beneficiario extends Usuarios{
    public String Nacionalidad;
    public String Estado_civil;
    public String Domicilio;
    public int Codigo_Postal;
    public String Observaciones;
    public Date Fecha_Inscripcion;
    public String Expediente;
    public String Motivo;
    public String Tipo_Vivienda;
    public Double Precio_Vivienda;
    
    //BORRAR
    public int Telefono;
    public String Email;
    
    
    
    public Beneficiario (String NIF_CIF, String Nombre, String Apellidos, Date FechaNac, String Localidad, int Activo, Date FechaDesac, String Email, int Telefono,
                                        String Nacionalidad, String Estado_civil, String Domicilio, int Codigo_Postal, String Observaciones, Date Fecha_Inscripcion, String Expediente,
                                        String Motivo, Double Precio_Vivienda, String Tipo_Vivienda){
        
        this.Nacionalidad = Nacionalidad;
        this.Estado_civil = Estado_civil;
        this.Domicilio = Domicilio;
        this.Codigo_Postal = Codigo_Postal;
        this.Observaciones = Observaciones;
        this.Fecha_Inscripcion = Fecha_Inscripcion;
        this.Expediente = Expediente;
        this.Motivo = Motivo;
        this.NIF_CIF = NIF_CIF;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.FechaNac = FechaNac;
        this.Localidad = Localidad;
        this.Activo = Activo;
        this.FechaDesac = FechaDesac;
        this.Email = Email;
        this.Telefono = Telefono;
        this.Precio_Vivienda = Precio_Vivienda;
        this.Tipo_Vivienda = Tipo_Vivienda;
    }
    
    public static Beneficiario crearBeneficiario(String NIF_CIF, String Nombre, String Apellidos, Date FechaNac, String Localidad, String Email, int Telefono,
                                        String Nacionalidad, String Estado_civil, String Domicilio, int Codigo_Postal, Date Fecha_Inscripcion, 
                                        String Motivo, Double Precio_Vivienda, String Tipo_Vivienda){
        
        return (new Beneficiario (NIF_CIF, Nombre, Apellidos, FechaNac, Localidad, 1, new Date(), Email, Telefono, Nacionalidad,
                                    Estado_civil, Domicilio, Codigo_Postal, "", Fecha_Inscripcion, "", Motivo, Precio_Vivienda, Tipo_Vivienda));
    }   
}
