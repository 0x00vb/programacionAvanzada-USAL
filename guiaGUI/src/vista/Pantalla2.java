package vista;
import javax.swing.*;
import controlador.AutoControlador;
import controlador.MantenimientoControlador;

import java.awt.*;
import java.awt.event.*;
public class Pantalla2 implements ActionListener{
	private JFrame ventana = new JFrame("Nuevo mantenimiento");
	
	private JLabel lblPatente = new JLabel("Patente");
	private JTextField txtPatente = new JTextField(12);
	
	private JLabel lblTipo = new JLabel("Tipo mantenimiento");
	private JComboBox<String> comboTipo = new JComboBox<String>(new String[] {"Control preventivo", "Reparacion"});
	
	private JLabel lblCosto = new JLabel("Costo");
	private JTextField txtCosto = new JTextField(12);
	
	private JButton boton1 = new JButton("Cargar");
	private JButton boton2 = new JButton("Cancelar");
	
	public Pantalla2() {
		ventana.setSize(200, 300);
		ventana.setLayout(new FlowLayout());
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ventana.add(lblPatente);
		ventana.add(txtPatente);
		ventana.add(lblTipo);
		ventana.add(comboTipo);
		ventana.add(lblCosto);
		ventana.add(txtCosto);
		ventana.add(boton1);
		ventana.add(boton2);

		boton1.addActionListener(this);
		boton2.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Cargar")) {
			String patente = txtPatente.getText();
			String costoString = txtCosto.getText();
			String selectedItem = (String)comboTipo.getSelectedItem();
			
			if(patente == ""| costoString == "") {
				JOptionPane.showMessageDialog(ventana, "Complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			double costo = Double.parseDouble(costoString);
			char tipo = ' ';
			if(selectedItem == "Reparacion") {
				tipo = 'r';
			}else {
				tipo = 'c';
			}
			
			int respuesta = MantenimientoControlador.cargarMantenimiento(patente, tipo, costo);
			
			if(respuesta == 0) {
				JOptionPane.showMessageDialog(ventana, "Mantenimiento cargado correctamente.");
			}else {
				JOptionPane.showMessageDialog(ventana, "La patente no coincide con ninguna de los autos cargados.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}else if(e.getActionCommand().equals("Cancelar")) {
			ventana.setVisible(false);
		}
	}
	
	public void mostrar() {
		ventana.setVisible(true);
	}
	
}
