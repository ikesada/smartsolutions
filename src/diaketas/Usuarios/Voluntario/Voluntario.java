/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Usuarios.Voluntario;


import diaketas.Usuarios.Usuarios;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Cesar
 */

public class Voluntario extends Usuarios{
    
    
    public String Nacionalidad;
    public String Domicilio;
    public int Codigo_Postal;
    public Date Fecha_Inicio;
    public String Observaciones;
    
    //el voluntario tiene asociadas un conjunto de acciones
    
    
    
    //OPERACIONES DE LA CLASE VOLUNTARIO
    
    
    //Constructor
    public Voluntario ( String NIF_CIF, String Nombre, String Apellidos, Date FechaNac, String Localidad, int Activo,
                        Date FechaDesac, String Email, int Telefono, String Nacionalidad, String Domicilio, 
                        int Codigo_Postal, Date Fecha_Inicio, String Observaciones )
    {
        
        this.NIF_CIF = NIF_CIF;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.FechaNac = FechaNac;
        this.Localidad = Localidad;
        this.Activo = Activo;
        this.FechaDesac = FechaDesac;
        this.Email = Email;
        this.Telefono = Telefono;
        this.Nacionalidad = Nacionalidad;
        this.Domicilio = Domicilio;
        this.Codigo_Postal = Codigo_Postal;
        this.Fecha_Inicio = Fecha_Inicio;
        this.Observaciones = Observaciones;      
    }


    public static boolean crearVoluntario( String DNI, String nombre, String apellidos, Date fechaNacim, String ciudad, String email, int telf, String nacionalidad, String direccion, int codPost, String obs)
    {

        
        boolean confirma;
        
        
        //pongo por defecto los campos: Activo=1, FechaDesac=0/0/0, FechaInicio=fecha_actual
        Voluntario v = new Voluntario( DNI, nombre, apellidos, fechaNacim, ciudad, 1, new Date(0,0,0), email, telf, 
                                        nacionalidad, direccion, codPost, new Date(), obs );
        
        confirma = Gestor_de_voluntarios.añadirVoluntario();
       
        return confirma;
    
    
    }   
    
    
    
    
    
    
}
