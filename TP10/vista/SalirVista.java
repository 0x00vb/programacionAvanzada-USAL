package vista;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class SalirVista extends JPanel {
    private JButton confirmarButton, cancelarButton;

    public SalirVista() {
        setLayout(new GridLayout(2, 1));

        confirmarButton = new JButton("Confirmar salida");
        cancelarButton = new JButton("Cancelar");

        add(confirmarButton);
        add(cancelarButton);
    }

    public void setConfirmarListener(ActionListener listener) {
        confirmarButton.addActionListener(listener);
    }

    public void setCancelarListener(ActionListener listener) {
        cancelarButton.addActionListener(listener);
    }
}
