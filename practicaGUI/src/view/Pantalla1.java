package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Pantalla1 implements ActionListener {
	private JFrame ventana = new JFrame("Practica");
	
	private JLabel titulo = new JLabel("Selection boxes");
	
	private JCheckBox checkBox1 = new JCheckBox("Ingles");
	
	private JCheckBox checkBox2 = new JCheckBox("Frances");
	
	private JCheckBox checkBox3 = new JCheckBox("Portugues");
	
	private ButtonGroup g = new ButtonGroup();
	
	private JRadioButton button1 = new JRadioButton("Opcion 1");
	private JRadioButton button2 = new JRadioButton("Opcion 2");
	private JRadioButton button3 = new JRadioButton("Opcion 3");

	private JLabel selection;
	
	public Pantalla1() {
		
		selection = new JLabel("Selection: ");
		
		ventana.setSize(300,300);
		ventana.setLayout(new FlowLayout());
		
		ventana.add(titulo);
		
		ventana.add(button1);
		ventana.add(button2);
		ventana.add(button3);
				
		g.add(button1);
		g.add(button2);
		g.add(button3);
		ventana.add(selection);
		
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);

		
		
		ventana.setVisible(true);
	}	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() != "") {
				selection.setText("Selected: " + e.getActionCommand());
		}
	}
}
