package vista;
import controlador.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pantalla implements ActionListener{
	Controlador controlador = new Controlador();
	private JFrame ventana = new JFrame("Reparacion");
	
	private JLabel lblCodigoAscensor = new JLabel("Codigo ascensor");
	private JTextField txtCodigoAscensor = new JTextField(20);
	
	private JRadioButton cambioLuces = new JRadioButton("cambio luces");
	private JRadioButton cambioBotonera = new JRadioButton("cambio botonera");
	private JRadioButton mejoraEstructura = new JRadioButton("mejora estructura");

	private JLabel lblDniTecnico = new JLabel("DNI tecnico");
	private JTextField txtDniTecnico = new JTextField(20);

	private JButton boton = new JButton("cargar");
	
	public Pantalla() {
		ventana.setSize(300,300);
		ventana.setLayout(new FlowLayout());
		
		ventana.add(lblCodigoAscensor);
		ventana.add(txtCodigoAscensor);

		
		ventana.add(cambioLuces);
		ventana.add(cambioBotonera);
		ventana.add(mejoraEstructura);
		
		ventana.add(lblDniTecnico);
		ventana.add(txtDniTecnico);
		
		ventana.add(boton);
		boton.addActionListener(this);
		
		ventana.setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("cargar")) {
			int codigoAscensor = Integer.parseInt(txtCodigoAscensor.getText());
			boolean cambioLucesBoolean = cambioLuces.isSelected();
			boolean cambioBotoneraBoolean = cambioBotonera.isSelected();
			boolean mejoraEstructuraBoolean = mejoraEstructura.isSelected();
			int dniTecnico = Integer.parseInt(txtDniTecnico.getText());
			
			int confirmacion = JOptionPane.showConfirmDialog(null,  "codigo Asc: " + codigoAscensor + "\ndni tecnico: " + dniTecnico, "Confirmacion", JOptionPane.YES_NO_OPTION);
			if(confirmacion == JOptionPane.YES_OPTION) {
				int respuesta = controlador.cargarReparacion(codigoAscensor, cambioLucesBoolean, cambioBotoneraBoolean, mejoraEstructuraBoolean, dniTecnico);
				
			}
			
		}
	}

}
