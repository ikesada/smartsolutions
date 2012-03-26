/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.Usuarios;

import com.mysql.jdbc.Statement;
import diaketas.ConexionBD;
import diaketas.Usuarios.Beneficiario.Beneficiario;
import diaketas.Usuarios.Beneficiario.Familiar;
import diaketas.Usuarios.Beneficiario.Parentesco;
import diaketas.Usuarios.Donante.Donante;
import diaketas.Usuarios.Voluntario.Voluntario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class ONG {
      static Statement instruccion;
      static ResultSet tabla;
      static ConexionBD con = new ConexionBD();
    
    /********************************ACCION*************************************/
    public static void agregarAccion(Accion ac){
        
        con.conectarBD();
        /*Convertimos Date para trabajar*/
        java.sql.Timestamp fecha = new java.sql.Timestamp(ac.Fecha.getTime());
         try {
            instruccion = (Statement) con.conexion().createStatement();
            instruccion.executeUpdate("INSERT INTO Accion(Nombre, Fecha,"
                                        + "NIF_CIF_Voluntario, NIF_CIF_Usuario) VALUES (\""
                                        + ac.tipo + "\",\"" + fecha + "\",\"" + ac.DNI_Voluntario+"\",\""+ac.DNI_Usuario+"\")");
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
    
    /**************************BENEFICIARIO*************************************/    
    static public Beneficiario buscarBeneficiario(String DNI){
        Beneficiario beneficiario = null;
        con.conectarBD();

         try {
            instruccion = (Statement) con.conexion().createStatement();
            ResultSet rs = instruccion.executeQuery("Select * From Usuario u ,Beneficiario b WHERE u.NIF_CIF = b.NIF_CIF and u.NIF_CIF = \""+ DNI+"\"");

            /*Si se ha encontrado una tubla*/
            if (rs.next()){

                beneficiario = new Beneficiario(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getInt(6),
                                                rs.getDate(7), rs.getString(8), rs.getInt(9),rs.getString(11), rs.getString(12), rs.getString(13),
                                                rs.getInt(14), rs.getDate(15), rs.getInt(16), rs.getString(17), rs.getDouble(18), rs.getString(19),
                                                rs.getString(20), rs.getString(21), rs.getString(22), rs.getString(23), rs.getString(24), rs.getString(25),
                                                rs.getString(26), rs.getString(27));
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
         
         return beneficiario;
    }
    
    static public Boolean comprobarExistenciaBeneficiario(String DNI){
        
        /*Buscamos el beneficiario*/
        Beneficiario beneficiario = buscarBeneficiario(DNI);
  
        return (beneficiario != null);
    }
    
    static public void agregarNuevoBeneficiario(Beneficiario nuevoBeneficiario){
        con.conectarBD();
        /*Convertimos Date para trabajar*/
        java.sql.Timestamp fecha_Nacimiento = new java.sql.Timestamp(nuevoBeneficiario.FechaNac.getTime());
        java.sql.Timestamp fecha_Activacion = new java.sql.Timestamp(nuevoBeneficiario.Fecha_Inscripcion.getTime());
        
         try {
            instruccion = (Statement) con.conexion().createStatement();
            
            /*Introducimos la parte de Usuario*/
            instruccion.executeUpdate("INSERT INTO Usuario VALUES (\""+nuevoBeneficiario.NIF_CIF + "\",\""
                    + nuevoBeneficiario.Nombre + "\",\"" + nuevoBeneficiario.Apellidos + "\",\""  + fecha_Nacimiento
                    + "\",\"" + nuevoBeneficiario.Localidad + "\",\""   + nuevoBeneficiario.Activo + "\", NULL, \"" + nuevoBeneficiario.Email + "\",\"" + nuevoBeneficiario.Telefono + "\")");
            
            /*Introducimos la parte de Beneficiario*/
             instruccion.executeUpdate("INSERT INTO Beneficiario VALUES (\""+nuevoBeneficiario.NIF_CIF + "\",\""
                    + nuevoBeneficiario.Nacionalidad + "\",\"" + nuevoBeneficiario.Estado_civil + "\",\""  + nuevoBeneficiario.Domicilio
                    + "\",\"" + nuevoBeneficiario.Codigo_Postal + "\",\""  + fecha_Activacion + "\",\"" +  nuevoBeneficiario.Expediente
                    + "\",\"" + nuevoBeneficiario.Motivo + "\",\"" + nuevoBeneficiario.Precio_Vivienda + "\",\"" + nuevoBeneficiario.Tipo_Vivienda
                    + "\",\"" + nuevoBeneficiario.Observaciones_Datos_Personales + "\",\"" + nuevoBeneficiario.Observaciones_Familiares
                    + "\",\"" + nuevoBeneficiario.Observaciones_Vivienda + "\",\"" + nuevoBeneficiario.Ciudad_Nacimiento
                    + "\",\"" + nuevoBeneficiario.Situacion_Economica + "\",\"" + nuevoBeneficiario.Nivel_Estudios
                    + "\",\"" + nuevoBeneficiario.Profesion + "\",\"" + nuevoBeneficiario.Experiencia_Laboral +"\")");           
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
    
    /*****************************FAMILIAR************************************/
    static public Familiar buscarFamiliar (String Nombre_Apellidos, Date Fecha_Nac){
        Familiar familiar = null;
        con.conectarBD();
        /*Convertimos Date para trabajar*/
        java.sql.Timestamp fecha = new java.sql.Timestamp(Fecha_Nac.getTime());
         try {
            instruccion = (Statement) con.conexion().createStatement();
            ResultSet rs = instruccion.executeQuery("Select f.Nombre_Apellidos, f.Fecha_Nacimiento, f.Ocupacion, f.Cod_Familiar"
                    + " From Familiar f WHERE Nombre_Apellidos = \""
                    + Nombre_Apellidos+"\" and Fecha_Nacimiento=\""+fecha+"\"");
         
            if (rs.next()){
                familiar = new Familiar (rs.getString(1),rs.getDate(2),rs.getString(3));
                //Indicamos su Codigo Interno
                familiar.Cod_Familiar = rs.getInt(4);
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
    
    static public void agregarNuevoFamiliar (Familiar familiar){
        con.conectarBD();
        /*Convertimos Date para trabajar*/
        java.sql.Timestamp fecha_Nacimiento = new java.sql.Timestamp(familiar.Fecha_Nacimiento.getTime());
        
         try {
            instruccion = (Statement) con.conexion().createStatement();
            
            /*Introducimos al nuevo Familiar en el sistema*/
            instruccion.executeUpdate("INSERT INTO Familiar (Nombre_Apellidos, Fecha_Nacimiento, Ocupacion)"
                    + " VALUES (\""+familiar.Nombre_Apellidos + "\",\"" + fecha_Nacimiento + "\",\"" 
                    + familiar.Ocupacion + "\")");
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
    
    static public void asociarParentesco (Parentesco parentesco){
        con.conectarBD();

        try {
            instruccion = (Statement) con.conexion().createStatement();
            
            /*Introducimos al nuevo Familiar en el sistema*/
            instruccion.executeUpdate("INSERT INTO Parentesco "
                    + " VALUES (\""+parentesco.Cod_Familiar  + "\",\"" + parentesco.DNI_Beneficiario + "\",\"" 
                    + parentesco.Parentesc + "\")");
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
    
    
    /*****************************DONANTE************************************/
    
        static public void agregarNuevoDonante(Donante nuevoDonante){
        con.conectarBD();
        /*Convertimos Date para trabajar*/
        java.sql.Timestamp fecha_Nacimiento = new java.sql.Timestamp(nuevoDonante.FechaNac.getTime());
        java.sql.Timestamp fecha_Activacion = new java.sql.Timestamp(nuevoDonante.Fecha_Inscripcion.getTime());
        
         try {
            instruccion = (Statement) con.conexion().createStatement();
            /*Introducimos la parte de Usuario*/

            instruccion.executeUpdate("INSERT INTO Usuario VALUES (\""+nuevoDonante.NIF_CIF + "\",\""
                    + nuevoDonante.Nombre + "\",\"" + nuevoDonante.Apellidos + "\",\""  + fecha_Nacimiento
                    + "\",\"" + nuevoDonante.Localidad + "\",\""   + nuevoDonante.Activo + "\", NULL, \"" + nuevoDonante.Email + "\",\"" + nuevoDonante.Telefono + "\")");
            
            /*Introducimos la parte de Donante*/
             instruccion.executeUpdate("INSERT INTO Donante VALUES (\""+nuevoDonante.NIF_CIF + "\",\""
                    + nuevoDonante.Tipo_Donante + "\",\"" + fecha_Activacion + "\",\""  + nuevoDonante.Observaciones
                    + "\",\"" + nuevoDonante.Periodicidad_Donaciones + "\",\""   + nuevoDonante.Cuantia_Donaciones
                    + "\",\"" + nuevoDonante.Tipo_Periodicidad+"\")");           
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
        
        static public Donante buscarDonante(String DNI){
            
            Donante donante = null;
            con.conectarBD();
        
        try {
            instruccion = (Statement) con.conexion().createStatement();

            tabla = instruccion.executeQuery("Select * From Usuario u ,Donante d WHERE u.NIF_CIF = d.NIF_CIF and u.NIF_CIF = \""+ DNI+"\" LIMIT 1");
            
            if(tabla.next()){
                donante = new Donante((String)tabla.getObject("NIF_CIF"), (String)tabla.getObject("Nombre"), (String)tabla.getObject("Apellidos"), (Date)tabla.getObject("Fecha_Nacimiento_Fundacion"),
                        (String)tabla.getObject("Localidad"), (Integer)tabla.getInt("Activo"), (Date)tabla.getDate("Fecha_Desactivacion"), (String)tabla.getString("Email"), (Integer)tabla.getInt("Telefono"), 
                        (String)tabla.getString("Tipo_Donante"), tabla.getDate("Fecha_Inscripcion"), (String)tabla.getString("Observaciones"), (Integer)tabla.getInt("Periodicidad_Donaciones"),
                        (Double)tabla.getDouble("Cuantia_Donaciones"), (String)tabla.getString("Tipo_Periodicidad"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ONG.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally {
            if (con.hayConexionBD()) {
                try {
                    con.desconectarBD();
                } catch (SQLException ex) {
                    Logger.getLogger(ONG.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return donante;
        }
            
    
    
    /******************************VOLUNTARIO**************************/
    
    
    static public Voluntario buscarVoluntario(String DNI){
        Voluntario v = null;
        
        con.conectarBD();
        
        try {
            System.out.println("Hola\n");
        
            
            instruccion = (Statement) con.conexion().createStatement();
            //System.out.println("DNI del voluntario buscado: "+DNI);
            
            tabla = (ResultSet) instruccion.executeQuery("SELECT * FROM Usuario u, Voluntario v"
                    + " WHERE u.NIF_CIF = v.NIF_CIF and v.NIF_CIF = \""+(String)DNI+"\"");
            
            //rs = (ResultSet) instruccion.executeQuery("SELECT * FROM Usuario u, Voluntario v"
            //        + " WHERE u.NIF_CIF = v.NIF_CIF and v.NIF_CIF = \""+(String)DNI+"\"");
    
            System.out.println("Hola1\n");
 
            if(tabla.next())
            {System.out.println("Hola2\n");
                //desde el 1-9 son datos del usuario, y el 10 vuelve a ser el NIF_DNI pero de la tabla voluntario
                System.out.println("Los datos obtenidos son: \n"+tabla.getString(1)+" "+ tabla.getString(2)+" "+ tabla.getString(3)+" "+ tabla.getDate(4)+" "+ tabla.getString(5)+" "+ tabla.getInt(6)+" "+ tabla.getDate(7)+" "+ tabla.getString(8)+" "+ tabla.getInt(9)+" "+tabla.getString(11)+" "+ tabla.getString(12)+" "+ tabla.getInt(13)+" "+ tabla.getDate(14)+" "+ tabla.getString(15));
            
                //creo un nuevo voluntario v con esos datos
                v = new Voluntario(tabla.getString(1), tabla.getString(2), tabla.getString(3), tabla.getDate(4), tabla.getString(5), tabla.getInt(6), tabla.getDate(7), tabla.getString(8), tabla.getInt(9),tabla.getString(11), tabla.getString(12), tabla.getInt(13), tabla.getDate(14), tabla.getString(15));
                                
            }
      
        }
        catch(SQLException e){ System.out.println(e); }
        catch(Exception e){ System.out.println(e); }        
        
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
        return v;
    }
    
    
    static public boolean comprobarExistenciaVoluntario(String DNI){
        Voluntario vol = buscarVoluntario(DNI);
        
        
        return (vol != null);
    }
    
    
    
    
    static public void agregarNuevoVoluntario(Voluntario nuevoVoluntario){
        con.conectarBD();
        /*Convertimos Date para trabajar*/
        java.sql.Timestamp fecha_Nacimiento = new java.sql.Timestamp(nuevoVoluntario.FechaNac.getTime());
        java.sql.Timestamp fecha_Inicio = new java.sql.Timestamp(nuevoVoluntario.Fecha_Inicio.getTime());
        
         try {
            instruccion = (Statement) con.conexion().createStatement();
            
            /*Introducimos la parte de Usuario*/
            instruccion.executeUpdate("INSERT INTO Usuario VALUES (\""+nuevoVoluntario.NIF_CIF + "\",\""
                    + nuevoVoluntario.Nombre + "\",\"" + nuevoVoluntario.Apellidos + "\",\""  + fecha_Nacimiento
                    + "\",\"" + nuevoVoluntario.Localidad + "\",\""   + nuevoVoluntario.Activo + "\", NULL, \"" + nuevoVoluntario.Email + "\",\"" + nuevoVoluntario.Telefono + "\")");
            
            /*Introducimos la parte de Voluntario*/
             instruccion.executeUpdate("INSERT INTO Voluntario VALUES (\""+nuevoVoluntario.NIF_CIF + "\",\""
                    + nuevoVoluntario.Nacionalidad + "\",\"" + nuevoVoluntario.Domicilio + "\",\""  + nuevoVoluntario.Codigo_Postal + "\",\""   + fecha_Inicio + "\",\"" + nuevoVoluntario.Observaciones +"\")");           
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
    
   
}
