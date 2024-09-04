package vista;
import java.awt.*;
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

    }

    public String getFiltroMarca(){ return txtFiltroMarca.getText(); }
    public String getFiltroPatente(){ return txtFiltroPatente.getText(); }
    public JButton getBtn(){ return btn; }
    public void setDataTabla(Object[][] data, String[] columnas){
        tabla.setModel( new DefaultTableModel(data, new String[]{"Patente", "Marca", "Modelo", "codigo Reparacion", "descripcion", "costo", "repuestos"}) ); 
    }
    public void setTotalRegistros( int totalRegistros ){ this.totalRegistros.setText("Total de registros: " + totalRegistros); }
}
