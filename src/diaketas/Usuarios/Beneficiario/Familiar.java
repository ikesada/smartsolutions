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
    public int Cod_Familiar;
    public String Nombre_Apellidos;
    public Date Fecha_Nacimiento;
    public String Ocupacion;
    

    public Familiar(int Cod_Familiar, String Nombre_Apellidos, Date Fecha_Nacimiento, String Ocupacion) {
        this.Cod_Familiar = Cod_Familiar;
        this.Nombre_Apellidos = Nombre_Apellidos;
        this.Fecha_Nacimiento = Fecha_Nacimiento;
        this.Ocupacion = Ocupacion;
    }
    
    void cambiarDatosFamiliar(int Cod_Familiar, String Nombre_Apellidos, Date Fecha_Nacimiento, String Ocupacion){
        this.Cod_Familiar = Cod_Familiar;
        this.Nombre_Apellidos = Nombre_Apellidos;
        this.Fecha_Nacimiento = Fecha_Nacimiento;
        this.Ocupacion = Ocupacion;
    }
    
}
