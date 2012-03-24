/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Usuarios.Beneficiario;

import com.mysql.jdbc.Statement;
import diaketas.ConexionBD;
import diaketas.Usuarios.ONG;
import diaketas.Usuarios.Usuarios;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    /*-----------------------------Constructores-------------------------------*/
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

    
    /*--------------------Modificadores Beneficiario---------------------------*/
    public void cambiarDatosBeneficiario(String NIF_CIF, String Nombre, String Apellidos, Date FechaNac, String Localidad, String Email, int Telefono,
                                        String Nacionalidad, String Estado_civil, String Domicilio, int Codigo_Postal, Date Fecha_Inscripcion, 
                                        String Motivo, Double Precio_Vivienda, String Tipo_Vivienda){
    
        this.NIF_CIF = NIF_CIF;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.FechaNac = FechaNac;
        this.Localidad = Localidad;
        this.Email = Email;
        this.Telefono = Telefono;
        this.Nacionalidad = Nacionalidad;
        this.Estado_civil = Estado_civil;
        this.Domicilio = Domicilio;
        this.Codigo_Postal = Codigo_Postal;
        this.Fecha_Inscripcion = Fecha_Inscripcion;
        this.Motivo = Motivo;
        this.Precio_Vivienda = Precio_Vivienda;
        this.Tipo_Vivienda = Tipo_Vivienda;
        
        /* Actualizamos los datos */
        ConexionBD con = new ConexionBD();
        con.conectarBD();

        java.sql.Timestamp fecha_Nacimiento = new java.sql.Timestamp(this.FechaNac.getTime());
        
        try {
            Statement instruccion = (Statement) con.conexion().createStatement();

            instruccion.executeUpdate("Update Usuario SET "
                    + "NIF_CIF = \"" + this.NIF_CIF + "\", "
                    + "Nombre = \"" + this.Nombre + "\", "                    
                    + "Apellidos = \"" + this.Apellidos + "\", "                    
                    + "Fecha_Nacimiento_Fundacion = \"" + fecha_Nacimiento + "\", "                    
                    + "Localidad = \"" + this.Localidad + "\", "
                    + "Email = \"" + this.Email + "\", "
                    + "Telefono = \"" + this.Telefono + "\""
                    + " WHERE NIF_CIF = \""+Gestor_de_beneficiarios.NIF_Beneficiario+"\"");
         
            instruccion.executeUpdate("Update Beneficiario SET "
                    + "NIF_CIF = \"" + this.NIF_CIF + "\", "
                    + "Nacionalidad = \"" + this.Nacionalidad + "\", "                    
                    + "Estado_Civil = \"" + this.Estado_civil + "\", "                    
                    + "Domicilio = \"" + this.Domicilio + "\", "                    
                    + "Codigo_Postal = \"" + this.Codigo_Postal + "\", "
                    + "Observaciones = \"" + this.Observaciones + "\", "
                    + "Motivo = \"" + this.Motivo + "\", "                  
                    + "Precio_Vivienda = \"" + this.Precio_Vivienda + "\", "
                    + "Tipo_Vivienda = \"" + this.Tipo_Vivienda + "\""
                    + " WHERE NIF_CIF = \""+Gestor_de_beneficiarios.NIF_Beneficiario+"\"");
         }
         /*Captura de errores*/
         catch(SQLException e){ System.out.println(e); }
         catch(Exception e){ System.out.println(e);}
         /*Desconexión de la BD*/
         finally {
            if (con.hayConexionBD()) {
                try {
                    con.desconectarBD();
                } catch (SQLException ex) {
                    Logger.getLogger(ONG.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }     
        
    }
    
    
    /*----------------------------Familiares-----------------------------------*/
    public Familiar buscarFamiliar(String Nombre_Apellidos){
        ConexionBD con = new ConexionBD();
        con.conectarBD();
        Familiar familiar = null;
        //RETOCAR DNI_CIF
        
        try {
            Statement instruccion = (Statement) con.conexion().createStatement();
            ResultSet rs = instruccion.executeQuery("Select * From Familiar f, Parentesco p "
                    + "WHERE p.Cod_Familiar = f.Cod_Familiar and p.DNI_CIF = \""
                    + this.NIF_CIF+"\" and f.Nombre_Apellidos =\""+Nombre_Apellidos+"\"");
         
            if (rs.next()){
                /*Creamos un familiar con los datos*/
                familiar = new Familiar (rs.getString(2),rs.getDate(3),rs.getString(4));
                /*Indicamos su Codigo Interno*/
                familiar.Cod_Familiar = rs.getInt(1);
            }
         }
         /*Captura de errores*/
         catch(SQLException e){ System.out.println(e); }
         catch(Exception e){ System.out.println(e);}
         /*Desconexión de la BD*/
         finally {
            if (con.hayConexionBD()) {
                try {
                    con.desconectarBD();
                } catch (SQLException ex) {
                    Logger.getLogger(ONG.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }     
        
        return familiar;        
    }
    
    public ArrayList<Familiar> consultarFamiliares(){
        ConexionBD con = new ConexionBD();
        con.conectarBD();
        ArrayList<Familiar> familiares = new ArrayList<Familiar>();
        //RETOCAR DNI_CIF
        
        try {
            Statement instruccion = (Statement) con.conexion().createStatement();
            ResultSet rs = instruccion.executeQuery("Select * From Familiar f, Parentesco p"
                    + " WHERE f.Cod_Familiar = p.Cod_Familiar and DNI_CIF = \""+ this.NIF_CIF+"\"");
         
            while (rs.next()){
                /*Creamos un familiar con los datos*/
                Familiar familiar = new Familiar (rs.getString(2),rs.getDate(3),rs.getString(4));
                /*Indicamos su Codigo Interno*/
                familiar.Cod_Familiar = rs.getInt(1);
                /*Agregamos a la lista*/
                familiares.add(familiar);
            }
         }
         /*Captura de errores*/
         catch(SQLException e){ System.out.println(e); }
         catch(Exception e){ System.out.println(e);}
         /*Desconexión de la BD*/
         finally {
            if (con.hayConexionBD()) {
                try {
                    con.desconectarBD();
                } catch (SQLException ex) {
                    Logger.getLogger(ONG.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }     
        
        return familiares;
    }

}
