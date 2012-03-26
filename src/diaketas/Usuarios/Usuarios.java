/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Usuarios;

import com.mysql.jdbc.Statement;
import diaketas.ConexionBD;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public int Activo;
    public Date FechaDesac;
    public String Email;
    public int Telefono;

    /*----------------------------Constructores--------------------------------*/
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
