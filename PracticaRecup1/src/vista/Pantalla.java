package vista;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import controlador.controlador;

public class Pantalla implements ActionListener{
	private controlador c = new controlador();
	private JFrame ventana = new JFrame();
	
	private JLabel lblNumC = new JLabel("num camarote");
	private JTextField txtNumC = new JTextField(20);
	
	private JLabel lblNom = new JLabel("nombre");
	private JTextField txtNom = new JTextField(20);
	private JLabel lblAp = new JLabel("apellido");
	private JTextField txtAp = new JTextField(20);

	private ButtonGroup g = new ButtonGroup();
	private JRadioButton butC = new JRadioButton("cartera");
	private JRadioButton butM = new JRadioButton("mochila");
	private JRadioButton butV = new JRadioButton("valija");

	private JLabel lblpeso = new JLabel("peso equipaje");
	private JTextField txtPeso = new JTextField(20);

	private JButton agregarE = new JButton("agregar");
	private JButton cargar = new JButton("cargar");
	
	public Pantalla() {
		ventana.setSize(300, 300);
		ventana.setLayout(new FlowLayout());
		
		ventana.add(lblNumC);
		ventana.add(txtNumC);
		ventana.add(lblNom);
		ventana.add(lblAp);
		ventana.add(txtAp);
		g.add(butC);
		g.add(butM);
		g.add(butV);
		
		ventana.add(butC);
		butC.setSelected(true);
		ventana.add(butM);
		ventana.add(butV);

		ventana.add(lblpeso);
		ventana.add(txtPeso);
		ventana.add(agregarE);
		agregarE.addActionListener(this);
		ventana.add(cargar);
		cargar.addActionListener(this);
		
		ventana.setVisible(true);
	}
	
	public HashMap<Character, Double> equipajes = new HashMap<>();
	
	@Override
	public void actionPerformed(ActionEvent e) {
		char tipo = 'c';
		if(butV.isSelected()) {
			tipo = 'v';
		}else if(butM.isSelected()) {
			tipo = 'm';
		}
		if(e.getActionCommand().equals("agregar")) {
			equipajes.put(tipo, Double.parseDouble(txtPeso.getText()));
		}
		else if(e.getActionCommand().equals("cargar")) {
			double resp = c.Registrar(
					Integer.parseInt(txtNumC.getText()), 
					txtNom.getText(), 
					txtAp.getText(), 
					equipajes);
					
			if(resp != 0) {
				JOptionPane.showConfirmDialog(agregarE, e)
			}
		}
		
	}
	
}
