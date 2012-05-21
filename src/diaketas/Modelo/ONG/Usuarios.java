/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Modelo.ONG;

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
    
    /**
     * DNI del usuario en cuestion
     */
    protected String NIF_CIF;
    /**
     * Nombre del usuario
     */
    protected String Nombre;
    /**
     * Apellidos del Usuario
     */
    protected String Apellidos;
    /**
     * Fecha de nacimiento del Usuario
     */
    protected Date FechaNac;
    /**
     * Localidad del Usuario
     */
    protected String Localidad;
    /**
     * Muestra si el usuario esta activo en el sistema o se dio de baja
     */
    protected int Activo;
    /**
     * Fecha de desactivacion del Usuario
     */
    protected Date FechaDesac;
    /**
     * Email del Usuario
     */
    protected String Email;
    /**
     * Telefono del Usuario
     */
    protected int Telefono;
    
    //----------------------------------- CONSULTORES --------------------------------------//
    
    public int obtenerActivo() {
        return Activo;
    }

    public String obtenerApellidos() {
        return Apellidos;
    }

    public String obtenerEmail() {
        return Email;
    }

    public Date obtenerFechaDesac() {
        return FechaDesac;
    }

    public Date obtenerFechaNac() {
        return FechaNac;
    }

    public String obtenerLocalidad() {
        return Localidad;
    }

    public String obtenerNIFCIF() {
        return NIF_CIF;
    }

    public String obtenerNombre() {
        return Nombre;
    }

    public int obtenerTelefono() {
        return Telefono;
    }
    
    // ---------------------------------- MODIFICADORES ------------------------------------//

    public void modificarActivo(int Activo) {
        this.Activo = Activo;
    }

    public void modificarApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public void modificarEmail(String Email) {
        this.Email = Email;
    }

    public void modificarFechaDesac(Date FechaDesac) {
        this.FechaDesac = FechaDesac;
    }

    public void modificarFechaNac(Date FechaNac) {
        this.FechaNac = FechaNac;
    }

    public void modificarLocalidad(String Localidad) {
        this.Localidad = Localidad;
    }

    public void modificarNIFCIF(String NIF_CIF) {
        this.NIF_CIF = NIF_CIF;
    }

    public void modificarNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void modificarTelefono(int Telefono) {
        this.Telefono = Telefono;
    }

    
    
}
