package vista;
import javax.swing.*;

import controlador.ReparacionControlador;

import java.awt.*;
import java.awt.event.*;
import vista.*;
public class Pantalla implements ActionListener{
    private JFrame ventana = new JFrame();
    
    private JMenuBar menuBar;
    private JMenu menuOperaciones, menuEstadisticas, menuSistema, menuSalir;
    private JMenuItem menuIngreso, menuConsulta, menuActualizar, menuAcercaDe, menuSalirItem;
    private JLabel titleLabel;
    private CardLayout cardLayout;
    private JPanel panel;
    private IngresoVista ingresoVista;
    private VistaAcercaDe vistaAcercaDe;
    private ConsultaVista consultaVista;
    private ConsultaMasivaVista consultaMasivaVista; 
    private EstadisticaVista estadisticaVista;

    public Pantalla(int arg){
        ventana.setSize(800, 600);
        ventana.setTitle("Sistema de gestion taller");
        ventana.setLayout( new BorderLayout() );
        
        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);
        ingresoVista = new IngresoVista();   
        vistaAcercaDe = new VistaAcercaDe();
        consultaVista = new ConsultaVista(new ReparacionControlador());
        estadisticaVista = new EstadisticaVista(arg);
        consultaMasivaVista = new ConsultaMasivaVista();

        panel.add(ingresoVista, "Ingreso");
        panel.add(vistaAcercaDe, "AcercaDe");
        panel.add(consultaVista, "Consulta");
        panel.add(estadisticaVista, "Estadisticas");
        panel.add(consultaMasivaVista, "ConsultaMasiva");


        menuBar = new JMenuBar();
        ventana.setJMenuBar(menuBar);

        titleLabel = new JLabel("Ingreso", JLabel.CENTER);
        ventana.add(titleLabel, BorderLayout.NORTH);

        menuOperaciones = new JMenu("Operaciones");
        menuIngreso = new JMenuItem("Ingreso");
        menuIngreso.addActionListener(this);
        menuConsulta = new JMenuItem("Consulta");
        menuConsulta.addActionListener(this);
        menuActualizar = new JMenuItem("Actualizacion");
        menuActualizar.addActionListener(this);

        menuOperaciones.add(menuIngreso);
        menuOperaciones.add(menuConsulta);
        menuOperaciones.add(menuActualizar);
        menuBar.add(menuOperaciones);

        menuEstadisticas = new JMenu("Estadísticas");
        JMenuItem menuEstadisticasItem = new JMenuItem("Estadisticas");
        menuEstadisticas.add(menuEstadisticasItem);
        menuEstadisticasItem.addActionListener(this);
        menuBar.add(menuEstadisticas);
        menuSistema = new JMenu("Sistema");
        menuAcercaDe = new JMenuItem("Acerca de...");
        menuAcercaDe.addActionListener(this);
        menuSistema.add(menuAcercaDe);
        menuBar.add(menuSistema);

        menuSalir = new JMenu("Salir");
        menuBar.add(menuSalir);
        menuSalirItem = new JMenuItem("Salir");
        menuSalir.add(menuSalirItem); 
        menuSalirItem.addActionListener(this);

        ventana.setContentPane(panel);
        cardLayout.show(panel, "Ingreso");
        ventana.setVisible(true);
    }
    


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Ingreso":
                cardLayout.show(panel, "Ingreso");
                titleLabel.setText("Ingreso");
                break;
            case "Actualizacion":
                cardLayout.show(panel, "Consulta");
                titleLabel.setText("Consultar y actualizar");
                break;
            case "Consulta":
                cardLayout.show(panel, "ConsultaMasiva");
                titleLabel.setText("Consulta masiva");
                break;
            case "Acerca de...":
                cardLayout.show(panel, "AcercaDe");
                break;
            case "Estadisticas":
                cardLayout.show(panel, "Estadisticas");
                break;
            case "Salir":
                int confirm = JOptionPane.showConfirmDialog(
                    ventana,
                    "¿Está seguro que desea salir?",
                    "Confirmar salida",
                    JOptionPane.YES_NO_OPTION
                );
                
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
                break;
            default:
                break;
        }
    }

}
