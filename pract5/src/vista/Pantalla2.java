package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import controlador.Controlador;

public class Pantalla2 implements ActionListener{
	private Controlador c = new Controlador();
	private JFrame vent = new JFrame();
	
	private JLabel lblNum = new JLabel("num id");
	private JTextField txtNum = new JTextField(20);
	
	private JLabel lblNomb = new JLabel("nombre evento");
	private JComboBox<String> comboNomb = new JComboBox<>();
	
	private JLabel lblCantE = new JLabel("Cantidad entradas");
	private JComboBox<Integer> comboCantE = new JComboBox<>();
		
	private JButton btn = new JButton("reservar");
	
	public Pantalla2() {
		vent.setSize(300, 300);
		vent.setLayout( new FlowLayout() );
		
		vent.add(lblNum);
		vent.add(txtNum);
		vent.add(lblNomb);
		vent.add(comboNomb);
		comboNomb.removeAllItems();
		for(String s : c.getNombresEventos()) {
			comboNomb.addItem(s);
		}
		comboNomb.addActionListener(this);
		
		vent.add(lblCantE);
		vent.add(comboCantE);

		vent.add(btn);
		btn.addActionListener(this);
		
		vent.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == comboNomb) {
			String nombreE = (String) comboNomb.getSelectedItem();
			for(int i : c.getCantEntradas(nombreE)) {
				comboCantE.addItem(i);
			}
		}
		else if(e.getActionCommand().equals("reservar")) {
			
		}
	}
	
}
