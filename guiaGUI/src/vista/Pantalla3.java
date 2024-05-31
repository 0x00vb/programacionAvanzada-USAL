package vista;
import javax.swing.*;

import controlador.AutoControlador;

import java.awt.*;
import java.awt.event.*;
public class Pantalla3 implements ActionListener{
	private JFrame ventana = new JFrame("Consulta vehiculo");
	
	private JLabel lblPatente = new JLabel("Patente");
	private JTextField txtPatente = new JTextField(20);

	private JButton boton1 = new JButton("Buscar");
	private JButton boton2 = new JButton("Cancelar");

	
	public Pantalla3() {
		ventana.setSize(250,300);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLayout(new FlowLayout());

		ventana.add(lblPatente);
		ventana.add(txtPatente);
		
		ventana.add(boton1);	
		boton1.addActionListener(this);
		
		ventana.add(boton2);
		boton2.addActionListener(this);

	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Buscar")) {			
			String patente = txtPatente.getText();	
	        if (patente.isEmpty()) {
	            JOptionPane.showMessageDialog(ventana, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
	            return; 
	        }
			
	        String respuesta = AutoControlador.obtenerDatosAuto(patente);
			
			if(respuesta != null) {
			    JOptionPane.showMessageDialog(ventana, respuesta);
			}else {				
				JOptionPane.showMessageDialog(ventana, "Auto no encontrado!", "Error", JOptionPane.ERROR_MESSAGE);
			}

		}
		
		if(e.getActionCommand().equals("Cancelar")){
			ventana.setVisible(false);
		}

	}
	
	public void mostrar() {
		ventana.setVisible(true);
	}
}
