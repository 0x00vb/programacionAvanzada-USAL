package vista;
import java.awt.BorderLayout;

import javax.swing.*;

public class VistaAcercaDe extends JPanel {
    public VistaAcercaDe() {
        setLayout(new BorderLayout());
        JTextArea acercaDeText = new JTextArea("Nombre: Gestión de Taller Mecánico\n"
                + "Versión: 1.0\n"
                + "Año: 2024\n"
                + "Autores: Valentino Balatti");
        acercaDeText.setEditable(false);
        add(acercaDeText, BorderLayout.CENTER);
    }
}
