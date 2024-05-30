package vista;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Menu implements ActionListener{
	private JFrame ventana = new JFrame("Menu principal");
	private JButton boton1 = new JButton("Punto a");
	private JButton boton2 = new JButton("Punto b");
	private JButton boton3 = new JButton("Punto c");
	
	public Menu() {
		ventana.setSize(300, 300);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLayout(new FlowLayout());

		ventana.add(boton1);
		ventana.add(boton2);
		ventana.add(boton3);
        boton1.addActionListener(this);
        boton2.addActionListener(this);
        boton3.addActionListener(this);
		ventana.setVisible(true);
	}
	
    public void actionPerformed(ActionEvent e) {
    	switch(e.getActionCommand()) {
    		case "Punto a":
    			Pantalla1 pantalla1 = new Pantalla1();
    			pantalla1.mostrar();
    			break;
    		case "Punto b":
    			break;
    			
    			
    		default:
    			break;
    	}
    }
	
}
