package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.ParseException;

import controlador.*;

public class Pantalla implements ActionListener {
	private Controlador c = new Controlador();
	private JFrame ventana = new JFrame("");
	
	private JLabel lblNum = new JLabel("num reserva");
	private JTextField txtNum = new JTextField(20);
	
	private JLabel lblNom = new JLabel("nombre");
	private JTextField txtNom = new JTextField(20);

	private JLabel lblAp = new JLabel("apellido");
	private JTextField txtAp = new JTextField(20);
	
	private JLabel lblTipo = new JLabel("tipo vehiculo");
	private JComboBox<String> comboTipo = new JComboBox<String>( new String[] {"turismo", "suv", "furgoneta"} );
	
	private JLabel lblFechI = new JLabel("fehca inicio");
	private JTextField txtFechI = new JTextField(20);
	
	private JLabel lblFechF = new JLabel("fecha fin");
	private JTextField txtFechF = new JTextField(20);

	private JCheckBox gps = new JCheckBox("gps");
	private JCheckBox sillaBebe = new JCheckBox("silla bb");
	
	private JButton btn = new JButton("cargar");
	
	public Pantalla() {
		ventana.setSize(300, 300);
		ventana.setLayout(new FlowLayout());
		
		ventana.add(lblNum);
		ventana.add(txtNum);
		ventana.add(lblNom);
		ventana.add(txtNom);
		ventana.add(lblAp);
		ventana.add(txtAp);
		ventana.add(lblFechI);
		ventana.add(txtFechI);
		ventana.add(lblFechF);
		ventana.add(txtFechF);
		ventana.add(gps);
		gps.setSelected(false);
		ventana.add(sillaBebe);
		sillaBebe.setSelected(false);
		ventana.add(btn);
		btn.addActionListener(this);
		
		ventana.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("cargar")) {
			
			int num;
			try {
				num = Integer.parseInt(txtNum.getText());
			}catch(NumberFormatException err) {
				throw new Ecepcion("error formateando num reserva");
			}
			
			try {
				int resp = c.cargarReserva(
						num,
						txtNom.getText(),
						txtAp.getText(),
						((String)comboTipo.getSelectedItem()).charAt(0),
						txtFechI.getText(),
						txtFechF.getText(),
						gps.isSelected(),
						sillaBebe.isSelected()
				);
			} catch (ParseException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	
	
}
