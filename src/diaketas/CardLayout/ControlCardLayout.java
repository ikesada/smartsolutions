/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.CardLayout;

/**
 *
 * @author Alex
 */
import diaketas.CardLayout.WindowCardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlCardLayout implements ActionListener{

WindowCardLayout ventana;
public ControlCardLayout( WindowCardLayout frame){
       ventana = frame ;}
public void actionPerformed (ActionEvent evento){
  if (evento.getSource()==ventana.combo){
     if (ventana.combo.getSelectedIndex()==0){
               ventana.cardLayout.show(ventana.panelInferior, "panel1");}
     if (ventana.combo.getSelectedIndex()==1){
               ventana.cardLayout.show(ventana.panelInferior, "panel2");}
     if (ventana.combo.getSelectedIndex()==2){
               ventana.cardLayout.show(ventana.panelInferior, "panel3");}}
   }
}