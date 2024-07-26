package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pantalla implements ActionListener{
	private JFrame ventana = new JFrame("");
	
	private JLabel lblNumA = new JLabel("numero ascensor");
	private JTextField txtNumA = new JTextField(20);
	
	private JLabel lblNumT = new JLabel("dni tecnico");
	private JTextField txtNumT = new JTextField(20);

	private JCheckBox boxA = new JCheckBox("cambio luces");
	private JCheckBox boxB = new JCheckBox("cambio botonera");
	private JCheckBox boxC = new JCheckBox("mejora infra");

	private JButton btn = new JButton("Cargar");
	
	public Pantalla() {
		ventana.setSize(300, 300);
		ventana.setLayout(new FlowLayout());
	
		ventana.add(lblNumA);
		ventana.add(txtNumA);
		
		ventana.add(lblNumT);
		ventana.add(txtNumT);
		
		ventana.add(boxA);
		ventana.add(boxB);
		ventana.add(boxC);
		
		ventana.add(btn);
		btn.addActionListener(this);
		
		ventana.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Cargar")) {
			int numA = Integer.parseInt(txtNumA.getText());
			int numT = Integer.parseInt(txtNumT.getText());
			boolean cambioLuces = boxA.isSelected();
	        boolean cambioBotonera = boxB.isSelected();
	        boolean mejoraInfraestructura = boxC.isSelected();
	        
            String mensaje = "Número ascensor: " + numA + "\n" +
                    "Número técnico: " + numT + "\n" +
                    "Cambio luces: " + cambioLuces + "\n" +
                    "Cambio botonera: " + cambioBotonera + "\n" +
                    "Mejora infraestructura: " + mejoraInfraestructura + "\n\n" +
                    "¿Es correcta esta información?";

        	int resp = JOptionPane.showConfirmDialog(null, mensaje, "Confirmar información", JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_OPTION) {
                // Procesar y guardar los datos si la respuesta es sí
                System.out.println("Información confirmada y guardada.");
            }
		
		
		
		
		}
	}
	
}
