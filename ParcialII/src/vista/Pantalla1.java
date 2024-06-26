package vista;

import javax.swing.*;

import Controlador.*;
import modelo.PesoExcedidoException;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Pantalla1 implements ActionListener{
	private JFrame ventana = new JFrame("");
	
	private JLabel lblNumeroCamarote = new JLabel("Numero camarote");
	private JTextField txtNumeroCamarote = new JTextField(20);
	
	private JLabel lblNombre;
	private JTextField txtNombre;
	
	private JLabel lblApellido;
	private JTextField txtApellido;
	
	private JLabel lblTipo = new JLabel("Tipo equipaje");
	private JComboBox<String> comboTipoValija;
		
	private JLabel lblPesoE = new JLabel("Peso: ");
	private JTextField txtPesoEquipaje = new JTextField(20);
	
	private JButton botonCargaEquipaje;

	private JButton botonRegistrar;
	
	private JLabel lblCostoAdicional = new JLabel("Costo adicional");
	private JTextField txtCostoAdicional = new JTextField(20);
	
	public Pantalla1() {
		ventana.setSize(1000,300);
		ventana.setLayout(new FlowLayout());
	
		lblNombre = new JLabel("Nombre: ");
		txtNombre = new JTextField(20);
		lblApellido = new JLabel("Apellido: ");
		txtApellido = new JTextField(20);
		comboTipoValija = new JComboBox<String>(new String[] {"", "cartera", "valija", "mochila"});
		botonCargaEquipaje = new JButton("Agregar equipaje");
		botonRegistrar = new JButton("Registrar");

		ventana.add(lblNumeroCamarote);
		ventana.add(txtNumeroCamarote);
		ventana.add(lblNombre);
		ventana.add(txtNombre);
		ventana.add(lblApellido);
		ventana.add(txtApellido);
		ventana.add(lblTipo);
		ventana.add(comboTipoValija);
		ventana.add(lblPesoE);
		ventana.add(txtPesoEquipaje);
		ventana.add(lblCostoAdicional);
		ventana.add(txtCostoAdicional);
		ventana.add(botonCargaEquipaje);

		ventana.add(botonRegistrar);

		botonCargaEquipaje.addActionListener(this);
		botonRegistrar.addActionListener(this);
	
	
		ventana.setVisible(true);
	}

	private HashMap<String, Double> equipajes = new HashMap<>();
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Agregar equipaje")) {
			String tipo = (String) comboTipoValija.getSelectedItem();
			double peso = Double.parseDouble(txtPesoEquipaje.getText());
			equipajes.put(tipo, peso);
			comboTipoValija.setSelectedIndex(0);
		}
		
		if(e.getActionCommand().equals("Registrar")) {
			PasajerosControlador pc = new PasajerosControlador();
			String nombre = txtNombre.getText();
			String apellido = txtApellido.getText();
			int numCamarote = Integer.parseInt(txtNumeroCamarote.getText());
			
			try {
				double respuesta = pc.registrarPasajero(nombre, apellido, numCamarote, equipajes);
				if(respuesta != 0.0) {
					txtCostoAdicional.setText("" + respuesta);
				}
				
			} catch (PesoExcedidoException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	

}
