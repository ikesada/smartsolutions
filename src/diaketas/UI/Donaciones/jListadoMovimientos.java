/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.UI.Donaciones;

import diaketas.ConexionBD;
import diaketas.Modelo.ONG.Movimiento;
import diaketas.UI.UI;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author cesar
 */
public class jListadoMovimientos extends javax.swing.JPanel {

    JPanel panel;
    int jPanelSiguiente;
    ArrayList<Movimiento> movimientosEncontrados = null;
    /**
     * Creates new form jAltaBeneficiario
     */
    public jListadoMovimientos(int jPanelSiguiente) {
        this.jPanelSiguiente = jPanelSiguiente;
        initComponents();
        
        switch(this.jPanelSiguiente){
            case 0:
                titulo.setText(titulo.getText() + "  (Consultar)");
                break;
            case 1:
                titulo.setText(titulo.getText() + "  (Modificar)");
                break;
            case 2:
                titulo.setText(titulo.getText() + "  (Eliminar)");
                break;
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

        buttonGroup2 = new javax.swing.ButtonGroup();
        titulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        listar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listadoMovimientos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cantidad1 = new javax.swing.JTextField();
        cantidad2 = new javax.swing.JTextField();
        fecha1 = new javax.swing.JTextField();
        fecha2 = new javax.swing.JTextField();
        operadorCantidad = new javax.swing.JComboBox();
        operadorFecha = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        voluntario = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        involucrado = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tagDescripcionScroll = new javax.swing.JScrollPane();
        tagDescripcion = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        confirmado = new javax.swing.JComboBox();
        seleccionar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        tipo = new javax.swing.JComboBox();

        setBackground(new java.awt.Color(238, 225, 192));

        titulo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        titulo.setText("Listar Movimientos");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        listar.setText("Listar");
        listar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarActionPerformed(evt);
            }
        });

        listadoMovimientos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cuantia", "Fecha", "Tipo", "Voluntario", "Implicado", "Confirmado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        listadoMovimientos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(listadoMovimientos);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/diaketas/Iconos/historiales.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("Lista de movimientos");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("Cuantía (€)");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setText("Fecha");

        cantidad1.setText("0");
        cantidad1.setEnabled(false);
        cantidad1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidad1ActionPerformed(evt);
            }
        });

        cantidad2.setText("0");
        cantidad2.setEnabled(false);
        cantidad2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidad2ActionPerformed(evt);
            }
        });

        fecha1.setText("yyyy/mm/dd");
        fecha1.setEnabled(false);
        fecha1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fecha1ActionPerformed(evt);
            }
        });

        fecha2.setText("yyyy/mm/dd");
        fecha2.setEnabled(false);
        fecha2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fecha2ActionPerformed(evt);
            }
        });

        operadorCantidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ninguno", "Mayor", "Menor", "Igual", "Entre" }));
        operadorCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                operadorCantidadActionPerformed(evt);
            }
        });

        operadorFecha.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ninguno", "Mayor", "Menor", "Igual", "Entre" }));
        operadorFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                operadorFechaActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setText("Voluntario DNI");

        voluntario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voluntarioActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setText("Involucrado");

        involucrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                involucradoActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setText("Descripción");

        tagDescripcion.setColumns(20);
        tagDescripcion.setRows(5);
        tagDescripcionScroll.setViewportView(tagDescripcion);

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setText("Confirmado");

        confirmado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cualquiera", "Sí", "No" }));
        confirmado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmadoActionPerformed(evt);
            }
        });

        seleccionar.setText("Seleccionar");
        seleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setText("Tipo Mov.");

        tipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cualquiera", "Donacion Efectiva", "Donacion Bancaria", "Donacion Material", "Ayuda Efectiva", "Ayuda Bancaria", "Ayuda Material", "Gasto" }));
        tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(442, 442, 442)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(31, 31, 31)
                        .addComponent(titulo))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(29, 29, 29)
                                    .addComponent(jLabel3))
                                .addGroup(layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel5))
                                    .addGap(35, 35, 35)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(fecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(18, 18, 18)
                                            .addComponent(operadorFecha, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(cantidad2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(cantidad1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(18, 18, 18)
                                            .addComponent(operadorCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGap(52, 52, 52)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel10)
                                                .addComponent(jLabel8))
                                            .addGap(35, 35, 35)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(involucrado)
                                                .addComponent(confirmado, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(51, 51, 51)
                                            .addComponent(jLabel7)
                                            .addGap(35, 35, 35)
                                            .addComponent(voluntario, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel9)
                                .addComponent(tagDescripcionScroll)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addGap(35, 35, 35)
                                    .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(seleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 921, Short.MAX_VALUE)
                                .addComponent(jSeparator3))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(417, 417, 417)
                        .addComponent(listar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(titulo)
                        .addGap(37, 37, 37)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(tagDescripcionScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cantidad1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cantidad2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fecha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(operadorCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(operadorFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(voluntario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(involucrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(confirmado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(14, 14, 14)
                .addComponent(listar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(seleccionar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void listarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarActionPerformed
        // TODO add your handling code here:       

        DefaultTableModel tabla = (DefaultTableModel) listadoMovimientos.getModel();
        tabla.setRowCount(0);
        
        try{
            Float.parseFloat(cantidad1.getText());
            Float.parseFloat(cantidad2.getText());
        }catch(NumberFormatException n){
            
        }
        
        movimientosEncontrados = diaketas.diaketas.ong.gestorDonaciones.filtrarMovimientos(operadorCantidad.getSelectedIndex(),cantidad1.getText(),cantidad2.getText(),operadorFecha.getSelectedIndex(),fecha1.getText(),fecha2.getText(),voluntario.getText(),involucrado.getText(),confirmado.getSelectedIndex(), tipo.getSelectedIndex(),tagDescripcion.getText());
        
        int nMovimientos = movimientosEncontrados.size();
        
        if(nMovimientos != 0) {
            //Oferta oferta_actual;
            Object[] fila = new Object[6];
            //for(int i = 0 ; i < nMovimientos ; i++) {
                //oferta_actual = ofertasEncontradas.get(i);
            
            for(Movimiento m : movimientosEncontrados){
                fila[0] = m.obtenerCuantia();
                fila[1] = m.obtenerFecha();
                fila[2] = m.obtenerTipoMovimiento();
                fila[3] = m.obtenerVoluntarioCrea();
                fila[4] = m.obtenerInvolucrado();
                fila[5] = (m.estaConfirmado() ? "Si" : "No");
                
                tabla.addRow(fila);
            }
            seleccionar.setEnabled(true);
        }
        else
            seleccionar.setEnabled(false);
        
        //UI.cl.show(UI.jPrincipal, "HistorialyAcciones");
       
    }//GEN-LAST:event_listarActionPerformed

    private void cantidad1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidad1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantidad1ActionPerformed

    private void cantidad2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidad2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantidad2ActionPerformed

    private void fecha1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fecha1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fecha1ActionPerformed

    private void fecha2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fecha2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fecha2ActionPerformed

    private void voluntarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voluntarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_voluntarioActionPerformed

    private void involucradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_involucradoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_involucradoActionPerformed

    private void operadorCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_operadorCantidadActionPerformed
        // TODO add your handling code here:
        switch(operadorCantidad.getSelectedIndex()){
            case 0:
                cantidad1.setEnabled(false);
                cantidad2.setEnabled(false);
                break;
            case 1:
                cantidad1.setEnabled(true);
                cantidad2.setEnabled(false);
                break;
            case 2:
                cantidad1.setEnabled(true);
                cantidad2.setEnabled(false);
                break;
            case 3:
                cantidad1.setEnabled(true);
                cantidad2.setEnabled(false);
                break;
            case 4:
                cantidad1.setEnabled(true);
                cantidad2.setEnabled(true);
                break;
        }

    }//GEN-LAST:event_operadorCantidadActionPerformed

    private void operadorFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_operadorFechaActionPerformed
        // TODO add your handling code here:
        
        switch(operadorFecha.getSelectedIndex()){
            case 0:
                fecha1.setEnabled(false);
                fecha2.setEnabled(false);
                break;
            case 1:
                fecha1.setEnabled(true);
                fecha2.setEnabled(false);
                break;
            case 2:
                fecha1.setEnabled(true);
                fecha2.setEnabled(false);
                break;
            case 3:
                fecha1.setEnabled(true);
                fecha2.setEnabled(false);
                break;
            case 4:
                fecha1.setEnabled(true);
                fecha2.setEnabled(true);
                break;
        }
    }//GEN-LAST:event_operadorFechaActionPerformed

    private void confirmadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_confirmadoActionPerformed

    private void seleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarActionPerformed
        // TODO add your handling code here:
        
        int movimiento_seleccionado = listadoMovimientos.getSelectedRow();
        
        if(movimiento_seleccionado != -1) {
            diaketas.diaketas.ong.gestorDonaciones.seleccionarMovimiento(movimientosEncontrados.get(movimiento_seleccionado).obtenerCodMovimiento());
        
        
        switch(jPanelSiguiente) {
            case 0: // Consultar
                    panel = new jConsultarMovimiento();
                    UI.jPrincipal.add("ConsultarMovimiento", panel);
                    UI.cl.show(UI.jPrincipal, "ConsultarMovimiento");  

            break;
            case 1: // Modificar
                panel = new jModificarMovimiento();
                UI.jPrincipal.add("ModificarMovimiento", panel);
                UI.cl.show(UI.jPrincipal, "ModificarMovimiento");  

            break;
            case 2: // Eliminar
                panel = new jEliminarMovimiento();
                UI.jPrincipal.add("EliminarMovimiento", panel);
                UI.cl.show(UI.jPrincipal, "EliminarMovimiento");                    
            break;

        }

        }
        
        
        
    }//GEN-LAST:event_seleccionarActionPerformed

    private void tipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JTextField cantidad1;
    private javax.swing.JTextField cantidad2;
    private javax.swing.JComboBox confirmado;
    private javax.swing.JTextField fecha1;
    private javax.swing.JTextField fecha2;
    private javax.swing.JTextField involucrado;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable listadoMovimientos;
    private javax.swing.JButton listar;
    private javax.swing.JComboBox operadorCantidad;
    private javax.swing.JComboBox operadorFecha;
    private javax.swing.JButton seleccionar;
    private javax.swing.JTextArea tagDescripcion;
    private javax.swing.JScrollPane tagDescripcionScroll;
    private javax.swing.JComboBox tipo;
    private javax.swing.JLabel titulo;
    private javax.swing.JTextField voluntario;
    // End of variables declaration//GEN-END:variables
}
