package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.util.ArrayList;

import controlador.Controlador;

public class Pantalla implements ActionListener{
	private Controlador c = new Controlador();
	private JFrame ventana = new JFrame("ventana");
	
	private JLabel lblNumC = new JLabel("num");
	private JTextField txtNumC = new JTextField(20);
	
	private JLabel lblNom = new JLabel("nombre");
	private JTextField txtNom = new JTextField(20);
	private JLabel lblAp = new JLabel("apellido");
	private JTextField txtAp = new JTextField(20);

	private JLabel lblFecha = new JLabel("fecha");
	private JTextField txtFecha = new JTextField(20);

	private ButtonGroup g = new ButtonGroup();
	private JRadioButton radC = new JRadioButton("cartera");
	private JRadioButton radM = new JRadioButton("mochila");
	private JRadioButton radV = new JRadioButton("valija");

	private JLabel lblCod = new JLabel("codigo");
	private JTextField txtCod = new JTextField(20);
	
	private JLabel lblPeso = new JLabel("peso");
	private JTextField txtPeso= new JTextField(20);

	private JButton btnCargar = new JButton("cargar");
	private JButton btnRegistrar = new JButton("registrar");
	
	public Pantalla() {
		ventana.setSize(300,300);
		ventana.setLayout( new FlowLayout() );
		
		ventana.add(lblNumC);
		ventana.add(txtNumC);
		ventana.add(lblNom);
		ventana.add(lblNom);
		ventana.add(lblAp);
		ventana.add(lblAp);
		
		g.add(radC);
		g.add(radM);
		g.add(radV);
		
		ventana.add(radC);
		ventana.add(radM);
		ventana.add(radV);
		
		ventana.add(lblCod);
		ventana.add(txtCod);
		ventana.add(lblPeso);
		ventana.add(txtPeso);
		
		ventana.add(btnCargar);
		btnCargar.addActionListener(this);
		ventana.add(btnRegistrar);
		btnRegistrar.addActionListener(this);
		
		ventana.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<String[]> infoEq = new ArrayList<>();
		if(e.getActionCommand().equals("cargar")) {
			String t = "";
			if(radC.isSelected()) {
				t = "c";
			}else if(radM.isSelected()) {
				t = "m";
			}else {
				t = "v";
			}
			
			String[] eq = new String[3];
			eq[0] = txtCod.getText();
			eq[1] = t;
			eq[2] = txtPeso.getText();
			infoEq.add(eq);
			
		}else if(e.getActionCommand().equals("registrar")) {
			try {
				c.cargarInfo(
						Integer.parseInt(txtNumC.getText()),
						txtNom.getText(),
						txtAp.getText(),
						txtFecha.getText(),
						infoEq
				);
			} catch (NumberFormatException | ParseException e1) {
				e1.printStackTrace();
			}
			
			
		}
		
		
		
	}

}
