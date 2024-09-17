package controlador;
import java.util.*;
import java.util.stream.*;
import vista.*;
import modelo.*;

public class ConsultaMasivaControlador {
    private ConsultaMasivaVista vista;
    private VehiculoControlador vehiculoControlador;
    private ReparacionControlador reparacionControlador;
    private RepuestosControlador repuestosControlador;
    private ArrayList<Vehiculo> vehiculos;

    public ConsultaMasivaControlador(ConsultaMasivaVista vista){
        this.vista = vista;
        vehiculoControlador = new VehiculoControlador();
        this.reparacionControlador = new ReparacionControlador();
        this.vehiculos = vehiculoControlador.getVehiculos();
        this.repuestosControlador = new RepuestosControlador();
        if (this.vehiculos == null) {
            this.vehiculos = new ArrayList<>();
        }
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

        data.sort(Comparator.comparingInt(o -> Integer.parseInt(o[3].toString())));

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

    public void actualizarCampo(String codigoReparacion, int column, Object newValue){
        Reparacion reparacion = reparacionControlador.buscarReparacion(Integer.parseInt(codigoReparacion));
        if (reparacion != null) {
            switch (column) {
                case 4: // Descripción (Tipo de Reparación)
                    reparacion.setTipoReparacion(newValue.toString());
                    break;
                case 5: // Costo
                    reparacion.setCosto(Double.parseDouble(newValue.toString()));
                    break;
                case 6: // Repuestos
                    ArrayList<Repuesto> nuevosRepuestos = parseRepuestos(newValue.toString());
                    reparacion.setRepuestos(nuevosRepuestos);
                    break;
            }
        }
    }

    public ArrayList<Repuesto> parseRepuestos(String repuestosString) {
        ArrayList<Repuesto> repuestos = new ArrayList<>();
        
        String[] repuestoNombres = repuestosString.split(",\\s*");
        
        for (String nombre : repuestoNombres) {
            Repuesto repuesto = repuestosControlador.buscarRepuesto(nombre.trim()); 
            repuestos.add(repuesto);
        }
        
        return repuestos;
    }
}
