/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Usuarios.Donante;

import diaketas.Usuarios.Email;
import diaketas.Usuarios.Telefono;
import diaketas.Usuarios.Usuarios;
import java.util.Date;

/**
 *
 * @author Alex
 */
public class Donante extends Usuarios{
    
    public String TipoDonante;
    public Date FechaInscrip;
    public String Observaciones;
    public int Periodicidad;
    public double Cuantia;
    public String TipoPeriodicidad;

    public Donante() {
    }
        
    public Donante(String NIF_CIF, String Nombre, String Apellidos, Date FechaNac, String Localidad, Boolean Activo, Date FechaDesac, /*Email Email, Telefono Telefono,*/
            String TipoDonante, Date FechaInscrip, String Observaciones, int Periodicidad, double Cuantia, String TipoPeriodicidad) {
       
        this.NIF_CIF = NIF_CIF;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.FechaNac = FechaNac;
        this.Localidad = Localidad;
        this.Activo = Activo;
        this.FechaDesac = FechaDesac;
        //this.Email = Email;
        //this.Telefono = Telefono;
        
        this.TipoDonante = TipoDonante;
        this.FechaInscrip = FechaInscrip;
        this.Observaciones = Observaciones;
        this.Periodicidad = Periodicidad;
        this.Cuantia = Cuantia;
        this.TipoPeriodicidad = TipoPeriodicidad;        
    }

    
    
    
}
