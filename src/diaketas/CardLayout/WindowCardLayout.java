/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diaketas.CardLayout;

/**
 *
 * @author Alex
 */
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.WindowConstants;
import javax.swing.BorderFactory;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowCardLayout{

    JFrame frame;
    CardLayout cardLayout;
    JPanel panelSuperior, panelInferior,panelInf1, panelInf2, panelInf3;
    JLabel etiqueta, etiqueta1, etiqueta2,etiqueta3;
    JComboBox combo;
    String [] vectorCadena = {"panel1","panel2","panel3"};

    public WindowCardLayout(){/*Constructor*/
        construyePanelSuperior();
        construyePanelInf1();
        construyePanelInf2();
        construyePanelInf3();
        construyePanelInferior();
        construyeVentana();}

    public void construyePanelSuperior(){
        etiqueta = new JLabel("Elegir Opcion");
        panelSuperior=new JPanel();
        panelSuperior.setBorder(BorderFactory.createTitledBorder("Demo por INFORUX"));
        combo = new JComboBox(vectorCadena);
        panelSuperior.setLayout(new FlowLayout());
        panelSuperior.add(etiqueta);
        panelSuperior.add(combo);
        panelSuperior.getPreferredSize();}

    public void construyePanelInferior(){
        panelInferior= new JPanel();
        panelInferior.setBorder(BorderFactory.createTitledBorder("Panel Inferior"));
        cardLayout=new CardLayout();
        panelInferior.setLayout(cardLayout);
        /*Al agregar necesitamos 2 argumentos, el objeto a agregar y un nombre referencial */
        panelInferior.add(panelInf1, "panel1");
        panelInferior.add(panelInf2, "panel2");
        panelInferior.add(panelInf3, "panel3");}

    public void construyePanelInf1(){
        etiqueta1 = new JLabel("Has Seleccionado el Panel 1");
        panelInf1 = new JPanel(new FlowLayout());
        panelInf1.setBackground(Color.white);
        panelInf1.add(etiqueta1);}

    public void construyePanelInf2(){
        etiqueta2 = new JLabel("increiblemente estas viendo el panel2");
        panelInf2 = new JPanel(new FlowLayout());
        panelInf2.setBackground(Color.orange);
        panelInf2.add(etiqueta2);}

    public void construyePanelInf3(){
        etiqueta3 = new JLabel("CardLayout permite solo uno a la vez, esta vez el panel 3");
        panelInf3 = new JPanel(new FlowLayout());
        panelInf3.setBackground(Color.green);
        panelInf3.add(etiqueta3);}

    public void construyeVentana(){
        frame= new JFrame();
        frame.setLayout(new BoxLayout(frame.getContentPane(),BoxLayout.Y_AXIS));
        frame.add(panelSuperior);
        frame.add(panelInferior);
        frame.getPreferredSize();
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        //creamos el objeto controlador de eventos
        ControlCardLayout control= new ControlCardLayout(this);
        combo.addActionListener(control);}

    public static void main (String [] inforux){
        new WindowCardLayout();
    }

}
