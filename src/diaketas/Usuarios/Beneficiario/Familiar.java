/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Usuarios.Beneficiario;

import java.util.Date;

/**
 *
 * @author kesada
 */
public class Familiar {
    public String Nombre_Apellidos;
    public Date Fecha_Nacimiento;
    public String Ocupacion;
    public int Cod_Familiar;
    

    public Familiar(String Nombre_Apellidos, Date Fecha_Nacimiento, String Ocupacion) {
        this.Nombre_Apellidos = Nombre_Apellidos;
        this.Fecha_Nacimiento = Fecha_Nacimiento;
        this.Ocupacion = Ocupacion;
        this.Cod_Familiar = 0;
    }
    
    void cambiarDatosFamiliar(String Nombre_Apellidos, Date Fecha_Nacimiento, String Ocupacion){
        this.Nombre_Apellidos = Nombre_Apellidos;
        this.Fecha_Nacimiento = Fecha_Nacimiento;
        this.Ocupacion = Ocupacion;
    }
    
}
