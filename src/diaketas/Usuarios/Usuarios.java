/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Usuarios;

import java.util.Date;

/**
 *
 * @author Alex
 */
public abstract class Usuarios {
    
    public String NIF_CIF;
    public String Nombre;
    public String Apellidos;
    public Date FechaNac;
    public String Localidad;
    public Boolean Activo;
    public Date FechaDesac;
    public Email Email;
    public Telefono Telefono;

    /*
    public Usuarios(String NIF_CIF, String Nombre, String Apellidos, Date FechaNac, String Localidad, Boolean Activo, Date FechaDesac, Email Email, Telefono Telefono) {
        this.NIF_CIF = NIF_CIF;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.FechaNac = FechaNac;
        this.Localidad = Localidad;
        this.Activo = Activo;
        this.FechaDesac = FechaDesac;
        this.Email = Email;
        this.Telefono = Telefono;
    }
    */
    

}
