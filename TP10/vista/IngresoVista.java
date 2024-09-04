package vista;
import javax.swing.*;

import controlador.*;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class IngresoVista extends JPanel implements ActionListener {

    private JTextField txtVehiculo, txtFecha, txtDescripcion;
    private JButton btnGuardar, btnCancelar;
    private JComboBox comboMarcaVehiculo = new JComboBox<>();
    private JComboBox comboModeloVehiculo = new JComboBox<>();
    private JPanel pnlRepuestos;
    private JTextArea leyenda;
    private JList<String> listaRepuestosDisponibles, listaRepuestosSeleccionados;
    private JRadioButton rbMantenimiento, rbReparacion;    
    private JCheckBox chkLavado, chkEntregaRapida;
    private RepuestosControlador repuestosControlador;
    private ReparacionControlador reparacionControlador;

    public IngresoVista() {
        setSize(300, 200);
        setLayout(new FlowLayout());

        try {
            repuestosControlador = new RepuestosControlador();
            reparacionControlador = new ReparacionControlador();    
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        txtVehiculo = new JTextField(20);
        txtFecha = new JTextField(20);
        txtDescripcion = new JTextField(20);
        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");

        // Leyenda
        leyenda = new JTextArea(3, 30);
        leyenda.setText("Por favor, ingrese todos los datos de la reparación. Asegúrese de ingresar correctamente la patente del vehículo, e incluir cualquier repuesto necesario.");
        leyenda.setEditable(false);
        leyenda.setLineWrap(true);
        leyenda.setWrapStyleWord(true);
        add(leyenda);

        add(new JLabel("Patente vehículo:"));
        add(txtVehiculo);
        add(new JLabel("Fecha regreso:"));
        add(txtFecha);
        add(new JLabel("Descripción:"));
        add(txtDescripcion);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String s : repuestosControlador.getNombresRepuestos()) {
            listModel.addElement(s);
        }

        // Create the JList and set its model
        JList<String> listaRepuestosDisponibles = new JList<>(listModel);

        listaRepuestosDisponibles.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        listaRepuestosSeleccionados = new JList<>();
        listaRepuestosSeleccionados.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        add(new JLabel("Repuestos disponibles:"));
        add(new JScrollPane(listaRepuestosDisponibles));
        add(new JLabel("Repuestos seleccionados:"));
        add(new JScrollPane(listaRepuestosSeleccionados));

        // Botones de opciones (radio buttons)
        JPanel panelOpciones = new JPanel();
        rbMantenimiento = new JRadioButton("Mantenimiento");
        rbReparacion = new JRadioButton("Reparación", true); // Opción por defecto
        ButtonGroup grupoOpciones = new ButtonGroup();
        grupoOpciones.add(rbMantenimiento);
        grupoOpciones.add(rbReparacion);
        panelOpciones.add(rbMantenimiento);
        panelOpciones.add(rbReparacion);
        add(panelOpciones);

        // Casillas de verificación
        chkLavado = new JCheckBox("Incluye lavado", true); // Activa por defecto
        chkEntregaRapida = new JCheckBox("Entrega rápida");
        add(chkLavado);
        add(chkEntregaRapida);  

        add(btnGuardar);
        add(btnCancelar);
        btnGuardar.addActionListener(this);
        btnCancelar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            String patente = txtVehiculo.getText();
            String fecha = txtFecha.getText();
            String descripcion = txtDescripcion.getText();
            ArrayList<String> repuestosSeleccionados = new ArrayList<>(listaRepuestosDisponibles.getSelectedValuesList());            


            // reparacionControlador.registrarReparacion(patente, descripcion, fecha);
        } else if (e.getSource() == btnCancelar) {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de cancelar?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if(confirm == JOptionPane.YES_OPTION) {
                limpiarCampos();
            }
        }
    }

    private void limpiarCampos() {
        txtVehiculo.setText("");
        txtFecha.setText("");
        txtDescripcion.setText("");
        pnlRepuestos.removeAll();
        pnlRepuestos.revalidate();
        pnlRepuestos.repaint();
    }
}