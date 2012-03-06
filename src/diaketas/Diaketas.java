/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas;

import java.sql.*;

public class Diaketas {
    public static void main(String args[]){
        try {
            //Cargar clase de controlador de base de datos
            Class.forName("com.mysql.jdbc.Driver");
            //Crear el objeto de conexion a la base de datos
            Connection conexion = DriverManager.getConnection("jdbc:mysql://SQL09.FREEMYSQL.NET/3306/diaketas");
            //Crear objeto Statement para realizar queries a la base de datos
            Statement instruccion = conexion.createStatement();
            //Un objeto ResultSet, almacena los datos de resultados de una consulta
            ResultSet tabla = instruccion.executeQuery("SELECT cod , nombre FROM datos");
            System.out.println("Codigo\tNombre");
            while(tabla.next())
                System.out.println(tabla.getInt(1)+"\t"+tabla.getString(2));
        }
        catch(ClassNotFoundException e){ System.out.println(e); }
        catch(SQLException e){ System.out.println(e); }
        catch(Exception e){ System.out.println(e); }
    }
}