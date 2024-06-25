package modelo;
import java.util.*;

public class Estadia implements ICalculable{
    private static int contadorEstadias = 0;
    private int id;
    private Calendar fechaIngreso;
    private int cantidadDias;
    private Alojamiento alojamiento;
    private ArrayList<Huesped> huespedes;
    private ArrayList<Servicios> servicios;

    public Estadia(){
        servicios = new ArrayList<Servicios>();
    }

    public Estadia(Calendar fechaIngreso, int cantidadDias, Alojamiento alojamiento, ArrayList<Huesped> huespedes){
        this();
        this.id = contadorEstadias++;
        this.fechaIngreso = fechaIngreso;
        this.cantidadDias = cantidadDias;
        this.alojamiento = alojamiento;
        this.huespedes = huespedes;
    }

    public Calendar getFechaIngreso() {
        return this.fechaIngreso;
    }

    public void setFechaIngreso(Calendar fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int getCantidadDias() {
        return this.cantidadDias;
    }

    public void setCantidadDias(int cantidadDias) {
        this.cantidadDias = cantidadDias;
    }

    public Alojamiento getAlojamiento() {
        return this.alojamiento;
    }

    public void setAlojamiento(Alojamiento alojamiento) {
        this.alojamiento = alojamiento;
    }

    public ArrayList<Huesped> getHuespedes() {
        return this.huespedes;
    }

    public void setHuespedes(ArrayList<Huesped> huespedes) {
        this.huespedes = huespedes;
    }

    public void addHuesped(Huesped huesped) {
    	huespedes.add(huesped);
    }
    
    public ArrayList<Servicios> getServicios() {
        return this.servicios;
    }

    public void setServicios(ArrayList<Servicios> servicios) {
        this.servicios = servicios;
    }

    public void addServicioLimpieza(Calendar fecha, Huesped huesped){
    	Limpieza servicioL = new Limpieza();
    	servicioL.setFecha(fecha);
    	servicioL.setHuesped(huesped);
    	servicios.add(servicioL);
    }

    public void addServicioBar(Calendar fecha, Huesped huesped, Menu plato, int cantidad){
    	ServicioBar servicioBar = new ServicioBar();
    	servicioBar.setFecha(fecha);
    	servicioBar.setHuesped(huesped);
    	servicioBar.setPlato(plato);
    	servicioBar.setCantidad(cantidad);
    	servicios.add(servicioBar);
    }

    public void addInscripcionExcursion(Excursion excursion, ArrayList<Huesped> huespedes) {
    	InscripcionExcursiones inscripcion = new InscripcionExcursiones();
    	inscripcion.setExcursion(excursion);
    	inscripcion.setHuespedes(huespedes);
    	
    }
    
    public static int getContador() {
        return contadorEstadias;
    }
    
    public int getId(){
        return this.id;
    }


    public double calcularCostoTotal(){
        double costoTotal = 0.0;
        double costoConsumos = 0.0;
        double costoAdicionalLimpieza = 0.0;
        double costoExcursiones = 0.0;
        costoTotal += alojamiento.getTarifaDiaria().getTarifa() * cantidadDias;
        
        if(alojamiento instanceof Cabaña){
            costoTotal -= costoTotal * (((Cabaña)alojamiento).getPorcentajeAdicional() / 100);
        }

        if(cantidadDias > 3){
            costoTotal -= costoTotal * (7.0 / 100.0);
        }

        for(Servicios servicio : servicios){
            if(servicio instanceof ServicioBar){
                costoConsumos += ((ServicioBar)servicio).calcularCostoConsumo();
            }else if(servicio instanceof Limpieza){
                costoAdicionalLimpieza += Limpieza.getprecio();
            }else {
            	costoExcursiones += ((InscripcionExcursiones)servicio).getExcursion().getCosto();
            }
        }


        costoTotal += costoConsumos;
        costoTotal += costoAdicionalLimpieza;


        return costoTotal;
    }
}
