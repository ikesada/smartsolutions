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
    
    
    
    public void confirmar(String dniV){
        /*Confirmamos el movimiento*/
        confirmado = true;
        
        ConexionBD con = new ConexionBD();
        con.conectarBD();

         try {
            Statement instruccion = (Statement) con.conexion().createStatement();
            
            /*Confirmamos el movimiento*/
            instruccion.executeUpdate("UPDATE  Movimiento SET Confirmado = 1 WHERE Cod_Movimiento = \"" + Cod_Movimiento + "\"");
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
                    Logger.getLogger(Familiar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void modificar(double cuantia, String descripcion) {
        this.cuantia = cuantia;
        this.descripcion = descripcion;
        
               
        ConexionBD con = new ConexionBD();
        con.conectarBD();

         try {
            Statement instruccion = (Statement) con.conexion().createStatement();
            
            /*Actualizamos Familiar*/
            instruccion.executeUpdate("UPDATE  Movimiento SET Cuantia = \""
                    + cuantia + "\", Descripcion = \""+descripcion+ "\" WHERE Cod_Movimiento = \"" + Cod_Movimiento + "\"");
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
                    Logger.getLogger(Familiar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
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
         /*Desconexión de la BD*/
         finally {
            if (con.hayConexionBD()) {
                try {
                    con.desconectarBD();
                } catch (SQLException ex) {
                    Logger.getLogger(Familiar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
     

