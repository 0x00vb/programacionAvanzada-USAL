package vista;

import javax.swing.*;

import controlador.EstadisticaControlador;

import java.awt.*;
import java.awt.event.*;

public class EstadisticaVista extends JPanel implements ActionListener{
    private JTextField txtPunto1, txt1Punto2, txt2Punto2, txt3Punto2, txt4Punto2, txtPunto3;
    private JButton btn;

    private EstadisticaControlador estadisticaControlador;

    public EstadisticaVista(int arg){
        estadisticaControlador = new EstadisticaControlador(arg);
        setLayout( new FlowLayout() );

        txtPunto1 = new JTextField(10);
        add( new JLabel("Punto 1: ") );
        add( txtPunto1 );
        txtPunto1.setEditable(false);

        add( new JLabel("Punto 2: ") );
        txt1Punto2 = new JTextField(10);
        txt1Punto2.setEditable(false);  
        txt2Punto2 = new JTextField(10);
        txt2Punto2.setEditable(false);
        txt3Punto2 = new JTextField(10);
        txt3Punto2.setEditable(false);
        txt4Punto2 = new JTextField(10);
        txt4Punto2.setEditable(false);
        add(txt1Punto2);
        add(txt2Punto2);
        add(txt3Punto2);
        add(txt4Punto2);

        add( new JLabel("Punto 3: "));
        txtPunto3 = new JTextField(10);
        txtPunto3.setEditable(false);
        add(txtPunto3);

        add(btn = new JButton("Cargar estadisticas"));
        btn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btn){
            txtPunto1.setText( estadisticaControlador.estadistica1() );

            String[] resp = estadisticaControlador.estadistica2().split(" ");
            txt1Punto2.setText(resp[0]);
            txt2Punto2.setText(resp[1]);
            txt3Punto2.setText(resp[2]);
            txt4Punto2.setText(resp[3]);

            txtPunto3.setText( "" + estadisticaControlador.estadistica3() );
        }
    }
    
}
