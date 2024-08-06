package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.util.*;
import controlador.Controlador;

public class Pantalla implements ActionListener{
	Controlador c = new Controlador();
	private JFrame ventana = new JFrame();
	
	private JLabel lblPatente = new JLabel("patente");
	private JTextField txtPatente = new JTextField(20);
	
	private JLabel lblTipo = new JLabel("tipo");
	private JComboBox<String> comboTipo = new JComboBox<String>( new String[] {"auto", "moto", "camioneta"} );
	
	private JLabel lblNum = new JLabel("num cochera");
	private JComboBox<Integer> comboNum = new JComboBox<>();

	
	private JLabel lblLlaves = new JLabel("deja llaves");
	private ButtonGroup g = new ButtonGroup();
	private JRadioButton butS = new JRadioButton("si");
	private JRadioButton butN = new JRadioButton("no");

	private JLabel lblPaga = new JLabel("pago adelantado");
	private ButtonGroup g1 = new ButtonGroup();
	private JRadioButton butS1 = new JRadioButton("si");
	private JRadioButton butN1 = new JRadioButton("no");

	private JLabel lblcantHoras = new JLabel("horas de uso");
	private JTextField txtCantHoras = new JTextField(20);

	private JButton btn = new JButton("cargas");
	
	
	public Pantalla() throws ParseException{
		ventana.setSize(300, 300);
		ventana.setLayout( new FlowLayout() );
	
		ventana.add(lblPatente);
		ventana.add(txtPatente);
		ventana.add(lblTipo);
		ventana.add(comboTipo);
		comboTipo.addActionListener(this);
		
		ventana.add(lblNum);
		ventana.add(comboNum);
		ventana.add(lblLlaves);
		g.add(butS);
		g.add(butN);
		ventana.add(butS);
		ventana.add(butN);
		
		ventana.add(lblPaga);
		g1.add(butS1);
		g1.add(butN1);
		ventana.add(butS1);
		ventana.add(butN1);
		
		ventana.add(lblcantHoras);
		ventana.add(txtCantHoras);
		
		ventana.add(btn);
	
		ventana.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == comboTipo) {
			comboNum.removeAllItems();
			for(int i : c.buscarNCocheras( (char)comboTipo.getSelectedItem() )) {
				comboNum.addItem(i);
			}
		}
		
		if(e.getSource() == btn) {
			
		}
	}

}
