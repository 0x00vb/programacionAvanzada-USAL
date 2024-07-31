package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import controlador.Controlador;
public class Pantalla implements ActionListener{
	Controlador c = new Controlador();
	private JFrame ventana = new JFrame();
	
	private JTextField txtDni = new JTextField(20);
	private JTextField txtFechaP = new JTextField(20);
	private JTextField txtFechaD = new JTextField(20);

	private JButton btn = new JButton("cargar");
	
	public Pantalla() {
		ventana.setSize(300,300);
		ventana.setLayout(new FlowLayout());
		
		ventana.add(txtDni);
		ventana.add(txtFechaP);
		ventana.add(txtFechaD);
		ventana.add(btn);		
		btn.addActionListener(this);
		
		ventana.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("cargar")) {
			
		}
		
	}

}
