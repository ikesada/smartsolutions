/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.UI.HistorialesyAcciones;

import diaketas.ConexionBD;
import diaketas.Modelo.Gestores.Gestor_de_beneficiarios;
import diaketas.Modelo.ONG.Beneficiario;
import diaketas.UI.UI;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


/**
 *
 * @author cesar
 */
public class jListadoBeneficiarios extends javax.swing.JPanel {

     
    /**
     * Creates new form jAltaBeneficiario
     */
    public jListadoBeneficiarios() {
        
        initComponents();
        
        ArrayList<Beneficiario> usuarios = Gestor_de_beneficiarios.obtenerBeneficiarios();
               
        //Para establecer el modelo al JTable
        DefaultTableModel modelo = new DefaultTableModel();
        jTable1.setModel(modelo);
        
        //Creamos columnas
        modelo.addColumn("NIF");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Fecha Nacimiento");        
        modelo.addColumn("Nacionalidad");
        modelo.addColumn("Estado Civil");
        modelo.addColumn("Domicilio");
        modelo.addColumn("Localidad");
        modelo.addColumn("Telefono");
        modelo.addColumn("Email");
        modelo.addColumn("Fecha inscripcion");
        modelo.addColumn("Expediente");
        modelo.addColumn("Profesion");

        
        Object[] fila = new Object[13];
        
        /*Obtenemos todas las filas*/
        
        for (int i = 0; i < usuarios.size(); i++){
            fila[0] = usuarios.get(i).obtenerNIFCIF();
            fila[1] = usuarios.get(i).obtenerNombre();
            fila[2] = usuarios.get(i).obtenerApellidos();
            //fila[3] = usuarios.get(i).FechaNac;
            fila[4] = usuarios.get(i).obtenerNacionalidad();
            fila[5] = usuarios.get(i).obtenerEstadoCivil();
            fila[6] = usuarios.get(i).obtenerDomicilio();
            fila[7] = usuarios.get(i).obtenerLocalidad();
            fila[8] = usuarios.get(i).obtenerTelefono();
            fila[9] = usuarios.get(i).obtenerEmail();
            //fila[10] = usuarios.get(i).Fecha_Inscripcion;
            fila[11] = usuarios.get(i).obtenerExpediente();
            fila[12] = usuarios.get(i).obtenerProfesion();
            

            //  El elemento jTable no permite mostrar datos del tipo Date, por ello, antes de mostrar el dato
            //  se comprueba si es de tipo Date, y si es asi, se pasa a string para mostrarlo en la tabla.
            //  Primero se lee el tipo Date, y despues con las dos ultimas instrucciones se transforma a String


            if( usuarios.get(i).obtenerFechaNac()!=null  )
            {
                java.util.Date date = (java.util.Date) usuarios.get(i).obtenerFechaNac();   //primero leo el objeto fecha
                java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");
                fila[3] = (String) sdf.format(date);          
            }
            else{
                fila[3] = "";
            }

            if( usuarios.get(i).obtenerFechaInscripcion()!=null  )
            {
                java.util.Date date = (java.util.Date) usuarios.get(i).obtenerFechaInscripcion();   //primero leo el objeto fecha
                java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");
                fila[10] = (String)sdf.format(date);              
            }
            else{
                fila[10] = "";
            }


            /*Añadimos la fila*/
            modelo.addRow(fila);     
        }
        
        /*Adjustamos el tamaño de las columnas*/
        int numColumnas = fila.length;
        for (int columna = 0; columna < numColumnas; columna++){          
            
            TableColumn tableColumn = jTable1.getColumnModel().getColumn(columna); 
            int maxWidth =  (int)jTable1.getTableHeader().getDefaultRenderer().getTableCellRendererComponent(
                    jTable1, tableColumn.getIdentifier() , false, false, -1, columna).getPreferredSize().getWidth();
                    
                       
            /*Buscamos la mayor longitud entre los campos*/
            for(int i = 0; i < usuarios.size(); i++)
            {
                Object cellValue = jTable1.getValueAt(i, columna);
                if(cellValue != null)
                    maxWidth = Math.max(jTable1.getCellRenderer(i, columna).getTableCellRendererComponent(jTable1, cellValue, false, false, i, columna).getPreferredSize().width + jTable1.getIntercellSpacing().width, maxWidth);
            }
            maxWidth++;
            jTable1.getColumnModel().getColumn(columna).setWidth(maxWidth);
            jTable1.getColumnModel().getColumn(columna).setMaxWidth(maxWidth);            
        }



    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jTitulo2 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        botonOK = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jTitulo1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(233, 246, 155));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        jTitulo2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jTitulo2.setText("Listado beneficiarios");

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        botonOK.setText("Ok");
        botonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonOKActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "NIF", "Nombre", "Apellidos", "Fecha nacimiento", "Nacionalidad", "Estado civil", "Domicilio", "Codigo postal", "Localidad", "Telefono", "Observaciones", "Fecha inscripcion", "Expediente", "Motivo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/diaketas/Iconos/historiales.png"))); // NOI18N

        jTitulo1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jTitulo1.setText("Historial y Acciones");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jSeparator3))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(442, 442, 442)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jTitulo2)
                                .addGap(34, 34, 34)
                                .addComponent(botonOK, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4)
                                .addGap(42, 42, 42)
                                .addComponent(jTitulo1)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jTitulo1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTitulo2)
                    .addComponent(botonOK))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonOKActionPerformed
        // TODO add your handling code here:       
            
            UI.cl.show(UI.jPrincipal, "HistorialyAcciones");
       
    }//GEN-LAST:event_botonOKActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        // TODO add your handling code here:
        int ancho, alto;
        ancho = this.getSize().width;
        alto = this.getSize().height;

        double fuente = 13 + (ancho - 1262) / 30;
        for (int i = 0; i < this.getComponentCount(); i++) {
            this.getComponent(i).setFont(new Font("Courier", Font.BOLD, (int) fuente));
        }
        
        jTitulo1.setFont(new Font("Courier", Font.BOLD, (int) fuente+12));
        jTitulo2.setFont(new Font("Courier", Font.BOLD, (int) fuente+2));
    }//GEN-LAST:event_formComponentResized

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonOK;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jTitulo1;
    private javax.swing.JLabel jTitulo2;
    // End of variables declaration//GEN-END:variables
}
