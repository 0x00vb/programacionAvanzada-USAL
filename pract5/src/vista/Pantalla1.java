package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import controlador.Controlador;

public class Pantalla1  implements ActionListener{
	private Controlador c = new Controlador();
	private JFrame vent = new JFrame();
	
	private JLabel lblNomb = new JLabel("nombre");
	private JTextField txtNomb = new JTextField(20);
	
	private JLabel lblFecha = new JLabel("fecha (yyyy-mm-dd)");
	private JTextField txtFecha = new JTextField(20);

	private JLabel lblUbi = new JLabel("ubicacion");
	private JTextField txtUbi = new JTextField(20);

	private JLabel lblTipo = new JLabel("tipo");
	private JComboBox<String> comboTipo = new JComboBox<String>( new String[] {"conferecia", "conciero", "taller"} );
	
	private JButton btn = new JButton("cargar");
	
	public Pantalla1() {
		vent.setSize(300, 300);
		vent.setLayout( new FlowLayout() );
		
		vent.add(lblNomb);
		vent.add(txtNomb);
		vent.add(lblFecha);
		vent.add(txtFecha);
		vent.add(lblUbi);
		vent.add(txtUbi);
		vent.add(lblTipo);
		vent.add(comboTipo);
		vent.add(btn);
		btn.addActionListener(this);
	
		vent.setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("cargar")) {
			
		}
		
	}
	
}
