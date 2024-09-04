package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import controlador.*;

public class Pantalla implements ActionListener {
	private Controlador c = new Controlador();
	private JFrame ventana = new JFrame("ventana");
	
	private JLabel lblNum = new JLabel("numero mesa");
	private JTextField txtNum = new JTextField(20);
	
	private JLabel lblProv = new JLabel("provincia");
	private JComboBox<String> comboProv = new JComboBox<>();
	private JLabel lblLoc = new JLabel("localidad");
	private JComboBox<String> comboLoc = new JComboBox<>();

	private JLabel lblVotA = new JLabel("votos a");
	private JTextField txtVotA = new JTextField(20);
	
	private JLabel lblVotB = new JLabel("votos b");
	private JTextField txtVotB = new JTextField(20);
	
	private JLabel lblTipo = new JLabel("tipo votantes");
	private ButtonGroup g = new ButtonGroup();
	private JRadioButton radM = new JRadioButton("masculino");
	private JRadioButton radF = new JRadioButton("femenino");
	private JRadioButton radA = new JRadioButton("ambos");

	private JButton btn = new JButton("cargar");
	
	private JButton btnResult = new JButton("resultado");
	private JTextField result = new JTextField(20);
	
	public Pantalla() {
		ventana.setSize(300,300);
		ventana.setLayout( new FlowLayout() );
		
		ventana.add(lblNum);
		ventana.add(txtNum);
		ventana.add(lblProv);
		ventana.add(comboProv);
		comboProv.addActionListener(this);
		
		for(String s : c.getNombresProvincias()) {
			comboProv.addItem(s);
		}
		
		ventana.add(lblLoc);
		ventana.add(comboLoc);
		
		ventana.add(lblVotA);
		ventana.add(txtVotA);
		ventana.add(lblVotB);
		ventana.add(txtVotB);
		
		g.add(radM);
		g.add(radF);
		g.add(radA);
		
		ventana.add(radM);
		ventana.add(radF);
		ventana.add(radA);
		radA.setSelected(true);
		
		ventana.add(btn);
		btn.addActionListener(this);
		
		ventana.add(btnResult);
		ventana.add(result);
		
		ventana.setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == comboProv) {
			comboLoc.removeAllItems();
			for(String s : c.getNombresLoc( (String) comboProv.getSelectedItem() )) {
				comboLoc.addItem(s);
			}
		}else if(e.getSource() == btn) {
			
			String tipo = "";
			if(radM.isSelected()) {
				tipo = "m";
			}else if(radF.isSelected()) {
				tipo = "f";
			}else {
				tipo = "a";
			}
			
			int resp = c.cargarMesa(
						Integer.parseInt(txtNum.getText()),
						(String) comboProv.getSelectedItem(),
						(String) comboLoc.getSelectedItem(),
						Integer.parseInt(txtVotA.getText()),
						Integer.parseInt(txtVotB.getText()),
						tipo.charAt(0)
					);
					
		}else if(e.getSource() == btnResult) {
			String data = c.calcResult();
			result.setText(data);
		}
		
		
		
		
	}	
}
