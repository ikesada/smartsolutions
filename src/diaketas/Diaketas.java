/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas;

import java.sql.*;

public class Diaketas {
    public static void main(String args[]){
       Conexion conexion = new Conexion();
       conexion.conectar();
    }
}