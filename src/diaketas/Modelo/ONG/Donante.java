/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.ONG;

import diaketas.Modelo.ONG.Usuarios;
import java.util.Date;

/**
 *
 * @author Alex
 */
public class Donante extends Usuarios{
    
    /**
     * El donante puede ser una persona o una empresa
     */
    public String Tipo_Donante;
    /**
     * Fecha en la que el donante se inscribio en el sistema
     */
    public Date Fecha_Inscripcion;
    /**
     * Observaciones sobre el Donante
     */
    public String Observaciones;
    /**
     * La periodicidad de la donacion que depende del tipo de periodicidad
     */
    public int Periodicidad_Donaciones;
    /**
     * Sera al cantidad que se realizara en las donaciones
     */
    public double Cuantia_Donaciones;
    /**
     * Los tipos de periodicidad de las donaciones. Son: Ninguna, Dias, Meses y Anyos
     */
    public String Tipo_Periodicidad;

    /**
     * Constructor Por Defecto
     */
    public Donante() {
    }
    
    /**
     * Constructor por parametros del Donante
     * @param NIF_CIF Dni del donante a crear
     */
    public Donante(String NIF_CIF){
        this.NIF_CIF = NIF_CIF;
    }
        
   /**
     * 
     * Constructor por parametros que introduce todos los parametros del donante
     * @param NIF_CIF Dni del Donante
     * @param Nombre Nombre del Donante
     * @param Apellidos Apellidos del Donante
     * @param FechaNac Fecha de Nacimiento del Donante
     * @param Localidad Localidad del Donante
     * @param Email Email del Donante 
     * @param Telefono Telefono del Donante 
     * @param Tipo_Donante Tipo de Donante
     * @param Fecha_Inscripcion Fecha de Inscripcion del Donante
     * @param Observaciones Observaciones sobre el Donante
     * @param Periodicidad_Donaciones Periodicidad en la que se haran las donaciones
     * @param Cuantia_Donaciones Cuantia de las donaciones del Donante
     * @param Tipo_Periodicidad Tipo de periodicidad de las donaciones
     */ 
    public Donante(String NIF_CIF, String Nombre, String Apellidos, Date FechaNac, String Localidad, int Activo, Date FechaDesac, String Email, int Telefono,
            String Tipo_Donante, Date Fecha_Inscripcion, String Observaciones, int Periodicidad_Donaciones, double Cuantia_Donaciones, String Tipo_Periodicidad) {
       
        this.NIF_CIF = NIF_CIF;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.FechaNac = FechaNac;
        this.Localidad = Localidad;
        this.Activo = Activo;
        this.FechaDesac = FechaDesac;
        this.Email = Email;
        this.Telefono = Telefono;
        
        this.Tipo_Donante = Tipo_Donante;
        this.Fecha_Inscripcion = Fecha_Inscripcion;
        this.Observaciones = Observaciones;
        this.Periodicidad_Donaciones = Periodicidad_Donaciones;
        this.Cuantia_Donaciones = Cuantia_Donaciones;
        this.Tipo_Periodicidad = Tipo_Periodicidad;        
    }
    
    
    
   /**
     * 
     * Funcion que crea el nuevo donante y lo devuelve para su uso
     * @param NIF_CIF Dni del Donante
     * @param Nombre Nombre del Donante
     * @param Apellidos Apellidos del Donante
     * @param FechaNac Fecha de Nacimiento del Donante
     * @param Localidad Localidad del Donante
     * @param Email Email del Donante 
     * @param Telefono Telefono del Donante 
     * @param Tipo_Donante Tipo de Donante
     * @param Fecha_Inscripcion Fecha de Inscripcion del Donante
     * @param Observaciones Observaciones sobre el Donante
     * @param Periodicidad_Donaciones Periodicidad en la que se haran las donaciones
     * @param Cuantia_Donaciones Cuantia de las donaciones del Donante
     * @param Tipo_Periodicidad Tipo de periodicidad de las donaciones
     * @return Devuelve el donante creado
     */ 
    public static Donante crearDonante(String NIF_CIF, String Nombre, String Apellidos, Date FechaNac, String Localidad, String Email, int Telefono,
            String Tipo_Donante, Date Fecha_Inscripcion, String Observaciones, int Periodicidad_Donaciones, double Cuantia_Donaciones, String Tipo_Periodicidad){
        
        return (new Donante(NIF_CIF, Nombre, Apellidos, FechaNac, Localidad, 1, new Date(), Email, Telefono, Tipo_Donante, Fecha_Inscripcion,
                Observaciones, Periodicidad_Donaciones, Cuantia_Donaciones, Tipo_Periodicidad));

    }
    
    
}
