package vista;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controlador.ConsultaMasivaControlador;

public class ConsultaMasivaVista extends JPanel{
    private JTextField txtFiltroMarca, txtFiltroPatente;
    private JButton btn;
    private JTable tabla;
    private JLabel totalRegistros;
    private ConsultaMasivaControlador consultaMasivaControlador;

    public ConsultaMasivaVista() {
        consultaMasivaControlador = new ConsultaMasivaControlador(this);    
        setLayout(new BorderLayout());
        
        JPanel panelFiltros = new JPanel();
        panelFiltros.setLayout(new GridLayout(2,2));

        panelFiltros.add( new JLabel("Marca: ") );
        panelFiltros.add( txtFiltroMarca = new JTextField() );

        panelFiltros.add( new JLabel("Patente: ") );
        panelFiltros.add( txtFiltroPatente = new JTextField() );
        
        panelFiltros.add( btn = new JButton("Buscar") );
        add(panelFiltros, BorderLayout.NORTH);

        tabla = new JTable();
        add( new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel panelAbajo = new JPanel();
        totalRegistros = new JLabel("Total de registros: 0");
        panelAbajo.add(totalRegistros);
        add(panelAbajo, BorderLayout.SOUTH);

        btn.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultaMasivaControlador.buscarReparaciones();
            }
        } );

    }
    
    public void actualizarCampo(String codigoReparacion, String patente, int column, Object newValue) {
        consultaMasivaControlador.actualizarCampo(codigoReparacion, patente, column, newValue);
    }
    

    public String getFiltroMarca(){ return txtFiltroMarca.getText(); }
    public String getFiltroPatente(){ return txtFiltroPatente.getText(); }
    public JButton getBtn(){ return btn; }
    public void setDataTabla(Object[][] data, String[] columnas){
        DefaultTableModel model = new DefaultTableModel(data, new String[]{"Patente", "Marca", "Modelo", "codigo Reparacion", "descripcion", "costo", "repuestos"}){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 3;
            }
        };
        
        tabla.setModel( model ); 

        model.addTableModelListener(e -> {
            int row = e.getFirstRow();
            int column = e.getColumn();
            
            if (column != 3) { 
                Object newValue = model.getValueAt(row, column);
                String codigoReparacion = model.getValueAt(row, 3).toString();
                String patente = model.getValueAt(row, 0).toString();
                actualizarCampo(codigoReparacion, patente, column, newValue);
            }
        });
        
    }
    public void setTotalRegistros( int totalRegistros ){ this.totalRegistros.setText("Total de registros: " + totalRegistros); }
}
