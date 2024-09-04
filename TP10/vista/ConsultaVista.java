package vista;
import javax.swing.*;

import controlador.*;

import java.awt.*;
import java.awt.event.*;

public class ConsultaVista extends JPanel {
    private JTextField txtCod, txtTipoReparacion, txtCosto, txtFechaIngreso, txtFechaEntrega;
    private JButton btnBuscar, btnEditar, btnAnular, btnAceptar, btnCancelar;
    private ConsultaControlador consultaControlador;

    public ConsultaVista(ReparacionControlador reparacionControlador) {
        this.consultaControlador = new ConsultaControlador(this, reparacionControlador);   
        setSize(300, 200);
        setLayout(new FlowLayout());
    
        txtCod = new JTextField(20);
        add(new JLabel("Código:"));
        add(txtCod);

        txtTipoReparacion = new JTextField(20);
        add(new JLabel("Tipo de reparación:"));
        txtTipoReparacion.setEditable(false);
        add(txtTipoReparacion);
        
        txtCosto = new JTextField(20);
        txtCosto.setEditable(false); 
        add(new JLabel("Costo:"));
        add(txtCosto);
        
        txtFechaIngreso = new JTextField(20);
        txtFechaIngreso.setEditable(false);
        add(new JLabel("Fecha de ingreso:"));
        add(txtFechaIngreso);
        
        txtFechaEntrega = new JTextField(20);
        txtFechaEntrega.setEditable(false);
        add(new JLabel("Fecha de entrega:"));
        add(txtFechaEntrega);
        
        btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> consultaControlador.buscarReparacion());
        add(btnBuscar);

            
        btnEditar = new JButton("Editar");
        btnEditar.setEnabled(false);
        btnEditar.addActionListener(e -> consultaControlador.habilitarEdicion());
        add(btnEditar);
    
        btnAceptar = new JButton("Aceptar");
        btnAceptar.setEnabled(false);
        btnAceptar.addActionListener(e -> consultaControlador.guardarCambios());
        add(btnAceptar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(e -> consultaControlador.guardarCambios());
        add(btnCancelar);

        btnAnular = new JButton("Anular");
        btnAnular.setEnabled(false);
        btnAnular.addActionListener(e -> consultaControlador.anularReparacion());
        add(btnAnular);

    }

    public JTextField getTxtCod() { return txtCod; }
    public JTextField getTxtTipoReparacion() { return txtTipoReparacion; }
    public JTextField getTxtCosto() { return txtCosto; }
    public JTextField getTxtFechaEntrega() { return txtFechaEntrega; }
    public JTextField getTxtFechaIngreso() { return txtFechaIngreso; }
    public JButton getBtnAceptar() { return btnEditar; }
    public JButton getBtnCancelar() { return btnAceptar; }
    public JButton getBtnEditar() { return btnCancelar; }
    public JButton getBtnAnular() { return btnAnular; }
}
