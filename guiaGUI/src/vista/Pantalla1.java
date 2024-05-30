package vista;
import javax.swing.*;

import controlador.AutoControlador;

import java.awt.*;
import java.awt.event.*;

public class Pantalla1 implements ActionListener{
	private JFrame ventana = new JFrame("Ingreso de informacion");

	private JLabel lblPatente = new JLabel("Patente");
	private JTextField txtPatente = new JTextField(20);
	
	private JLabel lblAnioPatentado = new JLabel("Anio patentacion");
	private JTextField txtAnioPatentado = new JTextField(20);
	
	private JLabel lblPrecio = new JLabel("Precio");
	private JTextField txtPrecio = new JTextField(20);
	
	private JButton boton = new JButton("Cargar");
	
	public Pantalla1() {
		ventana.setSize(250,300);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLayout(new FlowLayout());

		ventana.add(lblPatente);
		ventana.add(txtPatente);
		ventana.add(lblAnioPatentado);
		ventana.add(txtAnioPatentado);
		ventana.add(lblPrecio);
		ventana.add(txtPrecio);
		
		ventana.add(boton);	
		boton.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Cargar")) {			
			String patente = txtPatente.getText();
			String anioPatentadoString = txtAnioPatentado.getText();
			String precioString = txtPrecio.getText();

	        if (patente.isEmpty() || anioPatentadoString.isEmpty() || precioString.isEmpty()) {
	            JOptionPane.showMessageDialog(ventana, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
	            return; 
	        }
			
			int anioPatentado = Integer.parseInt(anioPatentadoString);
			double precio = Double.parseDouble(txtPrecio.getText());
	        
			int respuesta = AutoControlador.cargarAuto(patente, anioPatentado, precio);
			if(respuesta == 0) {
			    JOptionPane.showMessageDialog(ventana, "Auto cargado correctamente.");
			}else {				
				String mensaje = null;
				switch(respuesta) {
				case -1:
					mensaje = "El año de la patente no coincide con el año proporcionado.";
					break;
				case -2:
					mensaje = "Ya existe un auto con la misma patente.";
					break;
				}
				JOptionPane.showMessageDialog(ventana, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
			}

		}
	}
	
	public void mostrar() {
		ventana.setVisible(true);
	}
	
}
