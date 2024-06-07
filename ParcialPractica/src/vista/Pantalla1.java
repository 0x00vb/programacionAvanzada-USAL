package vista;
import controlador.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Pantalla1 implements ActionListener{
	EstadiaControlador estadiaControlador = new EstadiaControlador();
	CocheraControlador cocheraControlador = new CocheraControlador();
	
	private JFrame ventana;
	
	private JLabel lblPatente;
	private JTextField txtPatente;
	
	private JComboBox<String> comboTipo;
	
	private JLabel cantHoras;
	private JTextField txtCantHoras;
	
	private JLabel lblNumeroCochera;
	private JComboBox<Integer> comboNumeroCochera;
	
	private JLabel lblLlaves;
	private ButtonGroup grupoBotonesLlaves;
	private JRadioButton llavesSi;
	private JRadioButton llavesNo;

	private JLabel pagoAdelantado;
	private ButtonGroup grupoBotonesPago;
	private JRadioButton pagoSi;
	private JRadioButton pagoNo;

	private JButton boton;
	
	private JLabel lblTotal;
	private JLabel lblResultadoTotal;
	
	
	public Pantalla1(){
		ventana = new JFrame("");
		ventana.setSize(300,300);
		ventana.setLayout(new FlowLayout());
		
		lblPatente = new JLabel("Patente");
		txtPatente = new JTextField(20);
		
		comboTipo = new JComboBox<String>(new String[] {"auto", "camioneta", "moto"});
		comboTipo.addActionListener(this);
		
		cantHoras = new JLabel("Cantidad horas");
		txtCantHoras = new JTextField(20);
		
		lblNumeroCochera = new JLabel("Numero cochera");
		comboNumeroCochera = new JComboBox<Integer>();
		
		lblLlaves = new JLabel("Deja llaves:");
		grupoBotonesLlaves = new ButtonGroup();
		llavesSi = new JRadioButton("si");
		llavesNo = new JRadioButton("no");
		
		pagoAdelantado = new JLabel("Paga por adelantado:");
		grupoBotonesPago = new ButtonGroup();
		pagoSi = new JRadioButton("si");
		pagoNo = new JRadioButton("no");
		
		boton = new JButton("Cargar");
		
		lblTotal = new JLabel("Total: ");
		lblResultadoTotal = new JLabel("");
		
		ventana.add(lblPatente);
		ventana.add(txtPatente);
		ventana.add(comboTipo);
		
		ventana.add(cantHoras);
		ventana.add(txtCantHoras);
		ventana.add(lblNumeroCochera);
		ventana.add(comboNumeroCochera);
		
		ventana.add(lblLlaves);
		ventana.add(llavesSi);
		ventana.add(llavesNo);
		grupoBotonesLlaves.add(llavesSi);
		grupoBotonesLlaves.add(llavesNo);
		ventana.add(pagoAdelantado);
		ventana.add(pagoSi);
		ventana.add(pagoNo);
		grupoBotonesPago.add(pagoSi);
		grupoBotonesPago.add(pagoNo);
		
		ventana.add(lblTotal);
		ventana.add(lblResultadoTotal);
		
		ventana.add(boton);
		boton.addActionListener(this);
		
		ventana.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == comboTipo) {
			String tipo = (String) comboTipo.getSelectedItem();
			ArrayList<Integer> numerosCocherasDisp = cocheraControlador.buscarCochera(tipo);
			comboNumeroCochera.removeAllItems();
			for(Integer cochera : numerosCocherasDisp) {
				comboNumeroCochera.addItem(cochera);
			}
			System.out.println(numerosCocherasDisp.indexOf(1));
		}
	
		if(e.getActionCommand().equals("cargar")) {
			int patente = Integer.parseInt(txtPatente.getText());
			String tipo = (String) comboTipo.getSelectedItem();
			int numeroCochera = Integer.parseInt(txtCantHoras.getText());
			int cantidadHoras = Integer.parseInt(txtCantHoras.getText());
			boolean llaves = llavesSi.isSelected();
			boolean pagaAdelantado = pagoSi.isSelected();
			estadiaControlador.registrarEstadia(patente, tipo, numeroCochera, cantidadHoras, llaves, pagaAdelantado);
			
		}
	}

}
