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
 * @author Miguel Á. Quesada
 * @author Jesús Carabaño Bravo
 * @author Fran Dios Buitrago
 * @author Alejandro Portillo Guerrero
 */
public class Movimiento {
    
    /**
     * Codigo identificativo del movimiento
     */
    public int Cod_Movimiento;
    
    /** 
     * Tipo de movimiento
     */
    public String Tipo_Movimiento;
    
    /**
     * Cuantia del movimiento
     */
    public double cuantia;
    
    /**
     * NIF del Involucrado 
     */
    public String involucrado;
    
    /**
     * NIF del voluntario que crea
     */
    public String voluntario_crea;
    
    /**
     * NIF del voluntario que confirma el movimiento
     */
    public String voluntario_confirma;
    
    /**
     * Descripción del movimiento
     */
    public String descripcion;
    
    /**
     * Fecha de creación del movimiento
     */
    public Date Fecha;
    
    /**
     * Indica si el movimiento está confirmado
     */
    public boolean confirmado;
    
    
    /**
     * Constructor por defecto
     */
    public Movimiento(){
    }
    
    
    /* METODOS */
    
    public Movimiento(int Cod_Movimiento, String Tipo_Movimiento, double cuantia, String descripcion, Date Fecha, boolean confirmado,
                    String involucrado, String voluntario_crea, String voluntario_confirma) {
        this.Cod_Movimiento =Cod_Movimiento;
        this.Tipo_Movimiento = Tipo_Movimiento;
        this.cuantia = cuantia;
        this.descripcion = descripcion;
        this.Fecha = Fecha;
        this.confirmado = confirmado;
        this.involucrado = involucrado;
        this.voluntario_crea = voluntario_crea;
        this.voluntario_confirma = voluntario_confirma;
    }    
    
    /**
     * Constructor de Movimiento 
     */
    public Movimiento(String tipo,double cuantia, String involucrado, String descripcion, Date fecha, String voluntario_crea){
        this.Tipo_Movimiento = tipo;
        this.cuantia = cuantia;
        this.involucrado = involucrado;
        this.descripcion = descripcion;
        this.Fecha = fecha;
        this.voluntario_crea = voluntario_crea;
    }

    /**
     * Obtiene los datos de un movimiento
     * @return Devuelve el objeto movimiento
     */
    public Movimiento obtenerDatosMovimiento() {
        return this;
    }
    
    /**
     * Confirma un movimiento para que no pueda volver a ser modificado
     * @param dniV Dni del voluntario que confirma el movimiento
     */    
    public void confirmar(String dniV){
        /*Confirmamos el movimiento*/
        confirmado = true;
        involucrado = dniV;
        
        ConexionBD con = new ConexionBD();
        con.conectarBD();

         try {
            Statement instruccion = (Statement) con.conexion().createStatement();
            
            /*Confirmamos el movimiento*/
            instruccion.executeUpdate("UPDATE  Movimiento SET Confirmado = 1  , NIF_CIF_Confirma = \"" +
                    dniV + "\" WHERE Cod_Movimiento = \"" + Cod_Movimiento + "\"");
         }
         /*Captura de errores*/
         catch(SQLException e){ System.out.println(e); }
         catch(Exception e){ System.out.println(e);}
    }

    /**
     * Modifica los datos del movimiento
     * @param tipo_movimiento Tipo de movimiento
     * @param cuantia Cuantia del nuevo movimiento
     * @param descripcion Descripción del movimiento
     * @param involucrado DNI de la persona involucrada
     */
    public void modificar(String tipo_movimiento, double cuantia, String descripcion, String involucrado) {
        this.cuantia = cuantia;
        this.descripcion = descripcion;
        this.Tipo_Movimiento = tipo_movimiento;
        this.involucrado = involucrado;
        
               
        ConexionBD con = new ConexionBD();
        con.conectarBD();

         try {
            Statement instruccion = (Statement) con.conexion().createStatement();
            
            /*Actualizamos Familiar*/
            instruccion.executeUpdate("UPDATE  Movimiento SET Tipo = \"" + tipo_movimiento +"\" , Cuantia = \""
                    + cuantia + "\", Descripcion = \""+descripcion+ "\", NIF_CIF_Implica = \"" + involucrado + "\" WHERE Cod_Movimiento = \"" + Cod_Movimiento + "\"");
         }
         /*Captura de errores*/
         catch(SQLException e){ System.out.println(e); }
         catch(Exception e){ System.out.println(e);}

    } 
       
    public void eliminar(int cod_Movimiento){
        this.Cod_Movimiento = cod_Movimiento;
       
         ConexionBD con = new ConexionBD();
        con.conectarBD();

         try {
            Statement instruccion = (Statement) con.conexion().createStatement();
            
            /*Actualizamos Familiar*/
            instruccion.executeUpdate("DELETE FROM Movimiento "
                    + "WHERE Cod_Movimiento = \"" + Cod_Movimiento + "\"");
         }
         /*Captura de errores*/
         catch(SQLException e){ System.out.println(e); }
         catch(Exception e){ System.out.println(e);}
    }
}
     

