package vista;
import javax.swing.*;

import controlador.*;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class IngresoVista extends JPanel implements ActionListener {

    private JTextField txtFecha, txtDescripcion, txtCosto;
    private JButton btnGuardar, btnCancelar;
    private JComboBox<Integer> comboCliente = new JComboBox<>();
    private JComboBox<String> comboVehiculos = new JComboBox<>();
    private JPanel pnlRepuestos;
    private JTextArea leyenda;
    private JList<String> listaRepuesto, listaRepuestosSeleccionados;
    private JRadioButton rbMantenimiento, rbReparacion;    
    private JCheckBox chkLavado, chkEntregaRapida;
    private RepuestosControlador repuestosControlador;
    private ReparacionControlador reparacionControlador;
    private ClienteControlador clienteControlador;
    private VehiculoControlador vehiculoControlador;

    public IngresoVista() {
        setSize(300, 200);
        setLayout(new FlowLayout());

        try {
            repuestosControlador = new RepuestosControlador();
            reparacionControlador = new ReparacionControlador();    
            clienteControlador = new ClienteControlador();
            vehiculoControlador = new VehiculoControlador(); 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        
        txtFecha = new JTextField(20);
        txtDescripcion = new JTextField(20);

        add( new JLabel("seleccionar dni cliente"));
        add(comboCliente);
        for(int i : clienteControlador.getDniClientes()){
            comboCliente.addItem(i);
        }
        comboCliente.addActionListener(this);

        add( new JLabel("seleccionar patente vehiculo"));
        add(comboVehiculos);


        add(new JLabel("Fecha regreso:"));
        add(txtFecha);
        add(new JLabel("Descripción:"));
        add(txtDescripcion);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String s : repuestosControlador.getNombresRepuestos()) {
            listModel.addElement(s);
        }


        JPanel panelOpciones = new JPanel();
        rbMantenimiento = new JRadioButton("Mantenimiento");
        rbReparacion = new JRadioButton("Reparación", true);
        ButtonGroup grupoOpciones = new ButtonGroup();
        grupoOpciones.add(rbMantenimiento);
        grupoOpciones.add(rbReparacion);
        panelOpciones.add(rbMantenimiento);
        panelOpciones.add(rbReparacion);
        add(panelOpciones);

        chkLavado = new JCheckBox("Incluye lavado", true); 
        chkEntregaRapida = new JCheckBox("Entrega rápida");
        add(chkLavado);
        add(chkEntregaRapida);  

        add( new JLabel("Costo mano obra:"));
        add(txtCosto = new JTextField(20));

        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");

        add(btnGuardar);
        add(btnCancelar);
        btnGuardar.addActionListener(this);
        btnCancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            String patente = (String) comboVehiculos.getSelectedItem();
            String fecha = txtFecha.getText();
            String descripcion = txtDescripcion.getText();

            boolean lavado = chkLavado.isSelected();
            boolean entregaRapida = chkEntregaRapida.isSelected();       
            double costo = Double.parseDouble(txtCosto.getText());


            int resp = reparacionControlador.registrarReparacion(patente, descripcion, fecha, repuestosSeleccionados, descripcion, lavado, entregaRapida, costo);
            if(resp == -1){
                JOptionPane.showMessageDialog(this, "Esta reparacion ya existe", "Error", JOptionPane.ERROR_MESSAGE);
                try {
                    throw new ExcepcionPropia("Reparacion ya existe");
                } catch (ExcepcionPropia e1) {
                    e1.printStackTrace();
                }
            }
        
        } else if (e.getSource() == btnCancelar) {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de cancelar?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if(confirm == JOptionPane.YES_OPTION) {
                limpiarCampos();
            }
        } else if(e.getSource() == comboCliente){
            int dni = (int)comboCliente.getSelectedItem();
            for(String patente : vehiculoControlador.buscarVehiculoPorCliente(dni)){
                comboVehiculos.addItem(patente);
            }
        }



    }

    private void actualizarListaRepuestos() {
        String marcaSeleccionada = listaMarcasRepuestos.getSelectedValue();
        if (marcaSeleccionada != null) {
            DefaultListModel<String> repuestosModel = new DefaultListModel<>();
            for (String repuesto : repuestosControlador.getRepuestosPorMarca(marcaSeleccionada)) {
                repuestosModel.addElement(repuesto);
            }
            listaRepuestos.setModel(repuestosModel);
        }
    }

    private void limpiarCampos() {
        comboCliente.setSelectedItem(null);
        comboVehiculos.setSelectedItem(null);
        txtFecha.setText("");
        txtDescripcion.setText("");
        pnlRepuestos.removeAll();
        pnlRepuestos.revalidate();
        pnlRepuestos.repaint();
    }
}