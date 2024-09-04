package controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;

import modelo.*;
import vista.*;

public class ConsultaControlador {
    private ConsultaVista vista;
    private ReparacionControlador reparacionControlador;
    private Reparacion reparacionActual;

    public ConsultaControlador(ConsultaVista vista, ReparacionControlador reparacionControlador) {
        this.vista = vista;
        this.reparacionControlador = reparacionControlador;
    }

    public void buscarReparacion() {
        try {
            String codigoStr = vista.getTxtCod().getText();
            int codigo = Integer.parseInt(codigoStr);

            Reparacion reparacion = reparacionControlador.buscarReparacion(codigo);
            
            if (reparacion != null) {
                reparacionActual = reparacion;
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                vista.getTxtTipoReparacion().setText(reparacion.getTipoReparacion());
                vista.getTxtCosto().setText(String.valueOf(reparacion.getCosto()));
                vista.getTxtFechaIngreso().setText(dateFormat.format(reparacion.getFechaIngreso().getTime()));
                vista.getTxtFechaEntrega().setText(dateFormat.format(reparacion.getFechaEntrega().getTime()));
            
                vista.getBtnEditar().setEnabled(true);
                vista.getBtnAnular().setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(vista, "Reparación no encontrada.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "Código inválido. Por favor ingrese un número.");
        }
    }

    public void habilitarEdicion() {
        vista.getTxtTipoReparacion().setEditable(true);
        vista.getTxtCosto().setEditable(true);
        vista.getTxtFechaEntrega().setEditable(true);
        vista.getBtnAceptar().setEnabled(true);
        vista.getBtnCancelar().setEnabled(true);
        vista.getChkLavado().setEnabled(true);
        vista.getChkEntregaRapida().setEnabled(true);
    }

    public void cancelarEdicion() {
        vista.getTxtTipoReparacion().setEditable(false);
        vista.getTxtCosto().setEditable(false);
        vista.getTxtFechaEntrega().setEditable(false);
        vista.getBtnAceptar().setEnabled(false);
        vista.getBtnCancelar().setEnabled(false);
        vista.getChkLavado().setEnabled(false);
        vista.getChkEntregaRapida().setEnabled(false);
        limpiarCampos();
    }

    public void guardarCambios() {
        if (reparacionActual != null) {
            reparacionActual.setTipoReparacion(vista.getTxtTipoReparacion().getText());
            reparacionActual.setCosto(Double.parseDouble(vista.getTxtCosto().getText()));
            reparacionActual.setLavado(vista.getChkLavado().isSelected());
            reparacionActual.setEntregaRapida(vista.getChkEntregaRapida().isSelected());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            try {
                reparacionActual.setFechaEntrega(Calendar.getInstance());
                reparacionActual.getFechaEntrega().setTime(dateFormat.parse(vista.getTxtFechaEntrega().getText()));
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(vista, "Formato de fecha incorrecto. Use dd/MM/yyyy.");
                return;
            }

            JOptionPane.showMessageDialog(vista, "Reparación actualizada exitosamente.");
        }
    }

    public void anularReparacion() {
        if (reparacionActual != null) {
            int confirm = JOptionPane.showConfirmDialog(vista, "¿Estás seguro de que deseas anular esta reparación?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                reparacionControlador.eliminarReparacion(reparacionActual.getCodigoReparacion());
                JOptionPane.showMessageDialog(vista, "Reparación anulada exitosamente.");
                limpiarCampos();
            }
        }
    }

    private void limpiarCampos() {
        vista.getTxtCod().setText("");
        vista.getTxtTipoReparacion().setText("");
        vista.getTxtCosto().setText("");
        vista.getTxtFechaIngreso().setText("");
        vista.getTxtFechaEntrega().setText("");
        vista.getTxtTipoReparacion().setEditable(false);
        vista.getTxtCosto().setEditable(false);
        vista.getTxtFechaEntrega().setEditable(false);
        vista.getBtnEditar().setEnabled(false);
        vista.getBtnAnular().setEnabled(false);
    }
}
