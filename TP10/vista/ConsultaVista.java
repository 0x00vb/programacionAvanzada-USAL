package vista;
import javax.swing.*;

import controlador.*;

import java.awt.*;
import java.awt.event.*;

public class ConsultaVista extends JPanel {
    private JTextField txtCod, txtPatente, txtTipoReparacion, txtCosto, txtFechaIngreso, txtFechaEntrega;
    private JCheckBox chkLavado, chkEntregaRapida;
    private JButton btnBuscar, btnEditar, btnAnular, btnAceptar, btnCancelar;
    
    private ConsultaControlador consultaControlador;

    public ConsultaVista(ReparacionControlador reparacionControlador) {
        this.consultaControlador = new ConsultaControlador(this, reparacionControlador);   
        setSize(300, 200);
        setLayout(new FlowLayout());
    
        txtPatente = new JTextField(20);
        add(new JLabel("Patente:"));
        add(txtPatente);

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
        
        chkLavado = new JCheckBox("Incluye lavado");
        chkLavado.setEnabled(false);
        add(chkLavado);

        chkEntregaRapida = new JCheckBox("Entrega rápida");
        chkEntregaRapida.setEnabled(false);
        add(chkEntregaRapida);

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
        btnCancelar.addActionListener(e -> consultaControlador.cancelarEdicion());
        add(btnCancelar);

        btnAnular = new JButton("Anular");
        btnAnular.setEnabled(false);
        btnAnular.addActionListener(e -> consultaControlador.anularReparacion());
        add(btnAnular);

    }

    public JTextField getTxtCod() { return txtCod; }
    public JTextField getTxtPatente() { return txtPatente; }
    public JTextField getTxtTipoReparacion() { return txtTipoReparacion; }
    public JTextField getTxtCosto() { return txtCosto; }
    public JTextField getTxtFechaEntrega() { return txtFechaEntrega; }
    public JTextField getTxtFechaIngreso() { return txtFechaIngreso; }
    public JCheckBox getChkLavado() { return chkLavado; }
    public JCheckBox getChkEntregaRapida() { return chkEntregaRapida; }
    public JButton getBtnAceptar() { return btnAceptar; }
    public JButton getBtnCancelar() { return btnCancelar; }
    public JButton getBtnEditar() { return btnEditar; }
    public JButton getBtnAnular() { return btnAnular; }
}
