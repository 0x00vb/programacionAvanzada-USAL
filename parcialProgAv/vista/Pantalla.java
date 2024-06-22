package vista;
import controlador.Controlador;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Pantalla implements ActionListener{
    private JFrame ventana = new JFrame("Pantalla");

    private JLabel lblNumMesa = new JLabel("Numero de mesa");
    private JTextField txtNumMesa = new JTextField(20);
    
    private JLabel lblProvincia = new JLabel("Provincia");
    private JComboBox<String> comboProvincia = new JComboBox<String>();

    private JLabel lblLocalidad = new JLabel("Localidad");
    private JComboBox<String> comboLocalidades = new JComboBox<String>();

    private JLabel lblPartidoA = new JLabel("Votos Partido A");
    private JTextField txtVotosPartidoA = new JTextField(10);

    private JLabel lblPartidoB = new JLabel("Votos Partido B");
    private JTextField txtVotosPartidoB = new JTextField(10);

    private JLabel lblSexo = new JLabel("Sexo de votantes");
	private ButtonGroup grupoBotones = new ButtonGroup();
    private JRadioButton rbFemenino = new JRadioButton("Solo femenino");
    private JRadioButton rbMasculino = new JRadioButton("Solo masculino");
    private JRadioButton rbAmbos = new JRadioButton("Ambos (por defecto)", true);

    private JButton btnGuardar = new JButton("Guardar");

    private JButton mostrarResultado = new JButton("Resultado");
    private JTextField txtResultado = new JTextField(10);

    private JLabel cantVotantesLbl = new JLabel("Votantes totales");
    private JTextField cantVotantesTxt = new JTextField(10);


    private Controlador controlador;
    public Pantalla(Controlador controlador){
        this.controlador = controlador;
        ventana.setSize(400,300);
        ventana.setLayout(new FlowLayout());
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ventana.add(lblNumMesa);
        ventana.add(txtNumMesa);

        ventana.add(lblProvincia);
        ventana.add(comboProvincia);
        ArrayList<String> nombresProvincias = controlador.obtenerNombresProvs();
        for(String nombre : nombresProvincias){
            comboProvincia.addItem(nombre);
        }
        comboProvincia.addActionListener(this);

        ventana.add(lblLocalidad);
        ventana.add(comboLocalidades);

        ventana.add(lblPartidoA);
        ventana.add(txtVotosPartidoA);

        ventana.add(lblPartidoB);
        ventana.add(txtVotosPartidoB);

        ventana.add(lblSexo);
        ventana.add(rbFemenino);
        ventana.add(rbMasculino);
        ventana.add(rbAmbos);

        grupoBotones.add(rbFemenino);
        grupoBotones.add(rbMasculino);
        grupoBotones.add(rbAmbos);

        rbFemenino.addActionListener(this);
        rbMasculino.addActionListener(this);
        rbAmbos.addActionListener(this);

        ventana.add(btnGuardar);
        btnGuardar.addActionListener(this);

        ventana.add(mostrarResultado);
        mostrarResultado.addActionListener(this);

        ventana.add(txtResultado);
        txtResultado.setEditable(false);

        ventana.add(cantVotantesLbl);
        ventana.add(cantVotantesTxt);

        ventana.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboProvincia) {
            String nombreProvincia = (String) comboProvincia.getSelectedItem();

            ArrayList<String> nombresLocalidades = controlador.obtenerNombresLocalidades(nombreProvincia);

            comboLocalidades.removeAllItems();
            for (String nombreLocalidad : nombresLocalidades) {
                comboLocalidades.addItem(nombreLocalidad);
            }
        }

        if(e.getActionCommand().equals("Guardar")){
            int numeroMesa = Integer.parseInt(txtNumMesa.getText());
            String provincia = (String) comboProvincia.getSelectedItem();
            String localidad = (String) comboLocalidades.getSelectedItem();
            int votosPartidoA = Integer.parseInt(txtVotosPartidoA.getText());
            int votosPartidoB = Integer.parseInt(txtVotosPartidoB.getText());
            String sexoVotantes = obtenerSexoSeleccionado();

            int respuesta = controlador.guardarRegistro(numeroMesa, provincia, localidad, votosPartidoA, votosPartidoB, sexoVotantes);
            switch (respuesta) {
                case -1:
                    JOptionPane.showMessageDialog(ventana, "Numero de mesa invalido", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            
                default:
                    JOptionPane.showMessageDialog(ventana, "Registro guardado correctamente", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                    break;
            }
        }

        if(e.getActionCommand().equals("Resultado")){
            String partidoGanador = controlador.getPartidoGanador();
            txtResultado.setText(partidoGanador);
            String cantidadTotal = controlador.getCantVotantes();
            cantVotantesTxt.setText(cantidadTotal);
        }
    }

    private String obtenerSexoSeleccionado() {
        if (rbFemenino.isSelected()) {
            return "Femenino";
        } else if (rbMasculino.isSelected()) {
            return "Masculino";
        } else {
            return "Ambos";
        }
    }
    
}
