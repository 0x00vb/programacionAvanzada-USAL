package vista;
import controlador.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Pantalla implements ActionListener{
	private Controlador c = new Controlador();
	private JFrame ventana = new JFrame("");
	
	private JLabel lblNumMesa = new JLabel("numero mesa:");
	private JTextField txtNumMesa = new JTextField(20);

	private JLabel lblProv = new JLabel("Provincia");
	private JComboBox comboProv = new JComboBox();
	
	private JLabel lblLoc = new JLabel("Localidad");
	private JComboBox comboLoc = new JComboBox();

	private JLabel lblCantVotA = new JLabel("votos a:");
	private JTextField txtCantVotA = new JTextField(20);
	
	private JLabel lblCantVotB = new JLabel("votos b:");
	private JTextField txtCantVotB = new JTextField(20);


	
	private ButtonGroup g = new ButtonGroup();
	private JRadioButton rbutF = new JRadioButton("f");
	private JRadioButton rbutM = new JRadioButton("m");
	private JRadioButton rbutAmbos = new JRadioButton("ambos");

	private JButton cargar = new JButton("cargar");
	private JButton ganadorBtn = new JButton("ganador");

	private JTextField ganador = new JTextField(20);
	private JTextField porcentaje = new JTextField(20);

	
	public Pantalla() {
		ventana.setSize(300,300);
		ventana.setLayout(new FlowLayout());
		
		ventana.add(lblNumMesa);
		ventana.add(txtNumMesa);
		
		ventana.add(lblProv);
		ventana.add(comboProv);
		comboProv.addActionListener(this);
		for(String n : c.getNombresProv()) {
			comboProv.addItem(n);
		}
		
		ventana.add(lblLoc);
		ventana.add(comboLoc);
		
		ventana.add(lblCantVotA);
		ventana.add(txtCantVotA);
		ventana.add(lblCantVotA);
		ventana.add(txtCantVotB);
		
		g.add(rbutF);
		g.add(rbutM);
		g.add(rbutAmbos);
		
		ventana.add(rbutF);
		ventana.add(rbutM);
		ventana.add(rbutAmbos);
		rbutAmbos.setSelected(true);
		
		ventana.add(cargar);
		cargar.addActionListener(this);
		ventana.add(ganadorBtn);
		ganador.addActionListener(this);
		
		ventana.add(ganador);
		ventana.add(porcentaje);
		
		ventana.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == comboProv) {
			comboProv.removeAllItems();
			for(String n : c.getNombresLoc((String) comboProv.getSelectedItem())) {
				comboLoc.addItem(n);
			}
		}
		
		if(e.getActionCommand().equals("cargar")) {
			int num = Integer.parseInt(txtNumMesa.getText());
			if(num < 1 || num > 9999) {
				JOptionPane.showConfirmDialog(null, "error", "error", JOptionPane.ERROR);
			}
			String nomLoc = (String) comboLoc.getSelectedItem();
			int cantVotA = Integer.parseInt(txtCantVotA.getText());
			int cantVotB = Integer.parseInt(txtCantVotB.getText());
			char tipo = 'a';
			if(rbutF.isSelected()) {
				tipo = 'f';
			}else if(rbutM.isSelected()) {
				tipo = 'm';
			}else {
				tipo = 'a';
			}
			
			c.cargarMesa(num, nomLoc, cantVotA, cantVotB, tipo);
		}
		if(e.getActionCommand().equals("ganador")) {
			char g = c.getPartidoGanador();
			ganador.setText("" + g);
			double p = c.getPorcentajeGanador();
			porcentaje.setText("" + p);
			int cantTotalV = c.cantTotVot();
		}
	}
	
	
	
	
}
