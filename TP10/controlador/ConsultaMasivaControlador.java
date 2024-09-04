package controlador;
import java.util.*;
import java.util.stream.*;
import java.awt.event.*;
import vista.*;
import modelo.*;

public class ConsultaMasivaControlador {
    private ConsultaMasivaVista vista;
    private VehiculoControlador vehiculoControlador = new VehiculoControlador();
    private ArrayList<Vehiculo> vehiculos = vehiculoControlador.getVehiculos();

    public ConsultaMasivaControlador(ConsultaMasivaVista vista){
        this.vista = vista;
        this.vista.getBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarReparaciones();
            }
        });
    }

    public void buscarReparaciones(){
        String filtroMarca = vista.getFiltroMarca();
        String filtroPatente = vista.getFiltroPatente();
        List<Object[]> data = new ArrayList<>();

        for( Vehiculo vehiculo : vehiculos ){
            if(coincideConFiltros(vehiculo, filtroMarca, filtroPatente)){
                for(Reparacion reparacion : vehiculo.getReparaciones()){
                    crearFilaReparacion(vehiculo, reparacion);
                }
            }
        }

        Object[][] dataArray = data.toArray(new Object[0][]);
        vista.setDataTabla(dataArray, new String[]{"Patente", "Marca", "Modelo", "Código Reparación", "Descripción", "Costo", "Repuestos"});
        vista.setTotalRegistros(dataArray.length);
    }

    private boolean coincideConFiltros(Vehiculo vehiculo, String filtroMarca, String filtroPatente) {
        return (filtroMarca.isEmpty() || vehiculo.getMarca().contains(filtroMarca))
            && (filtroPatente.isEmpty() || vehiculo.getPatente().contains(filtroPatente));
    }

    private Object[] crearFilaReparacion(Vehiculo vehiculo, Reparacion reparacion) {
        return new Object[]{
            vehiculo.getPatente(),
            vehiculo.getMarca(),
            vehiculo.getModelo(),
            reparacion.getCodigoReparacion(),
            reparacion.getTipoReparacion(),
            reparacion.getCosto(),
            String.join(", ", reparacion.getRepuestos().stream().map(Repuesto::getNombre).collect(Collectors.toList()))
        };
    }

}
