package vista;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import controlador.*;
import java.util.*;

public class Pantalla implements ActionListener{
	private Controlador c = new Controlador();
	private JFrame ventana = new JFrame("");
	
	private JLabel lblNumM = new JLabel("num mesa");
	private JTextField txtNumM = new JTextField(20);
	
	private JLabel lblProv = new JLabel("seleccionar prov");
	private JComboBox<String> comboProv = new JComboBox<String>();
	
	private JLabel lblLoc = new JLabel("seleccionar localidad");
	private JComboBox<String> comboLoc = new JComboBox<String>();

	private JLabel lblVa = new JLabel("num votos a");
	private JTextField txtNumVa = new JTextField(20);

	private JLabel lblVb = new JLabel("num votos b");
	private JTextField txtNumVb = new JTextField(20);

	
	private JLabel lblT = new JLabel("tipo mesa");
	private ButtonGroup g = new ButtonGroup();
	private JRadioButton butM = new JRadioButton("masculino");
	private JRadioButton butF = new JRadioButton("femenino");
	private JRadioButton butA = new JRadioButton("ambos");

	private JButton btn = new JButton("cargar");
	
	private JTextField txtG = new JTextField(20);
	private JTextField txtGpor = new JTextField(20);

	
	public Pantalla() {
		ventana.setSize(300, 300);
		ventana.setLayout(new FlowLayout());
		
		ventana.add(lblNumM);
		ventana.add(txtNumM);
		ventana.add(lblProv);
		ventana.add(comboProv);
		
		for(String s : c.getNombresProvs()) {
			comboProv.addItem(s);
		}
		comboProv.addActionListener(this);
		
		ventana.add(lblLoc);
		ventana.add(comboLoc);
				
		ventana.add(lblVa);
		ventana.add(txtNumVa);
		ventana.add(lblVb);
		ventana.add(txtNumVb);

		
		ventana.add(lblT);
		g.add(butM);
		g.add(butF);
		g.add(butA);
		ventana.add(butM);
		ventana.add(butF);
		ventana.add(butA);
		butA.setSelected(true);
		
		ventana.add(btn);
		btn.addActionListener(this);
		
		ventana.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == comboProv) {
			String prov = (String) comboProv.getSelectedItem();
			comboLoc.removeAllItems();
			for(String s : c.getNombresLoc(prov)) {
				comboLoc.addItem(s);
			}
		}
	
		if(e.getActionCommand().equals("cargar")) {
			char t= ' ';
			if(butM.isSelected()) {
				t = 'm';
			}else if(butF.isSelected()) {
				t ='f';
			}else {
				t = 'a';
			}
			try {
				c.cargarMesa(
						Integer.parseInt(txtNumM.getText()), 
						(String) comboProv.getSelectedItem(), 
						(String) comboLoc.getSelectedItem(), 
						Integer.parseInt(txtNumVa.getText()),
						Integer.parseInt(txtNumVb.getText()),
						t
				);
				
				String g = c.partGanador();
				String[] gData = g.split(" ");
				txtG.setText(gData[0]);
				txtGpor.setText(gData[1]);
			} catch (NumberFormatException | EceptionP e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

}
