package modelo;
import java.util.Calendar;

public class Estadia implements ICalculable{
    private static int contadorEstadias = 0;
    private int id;
    private Calendar fechaIngreso;
    private int cantidadDias;
    private Alojamiento alojamiento;
    private Huesped[] huespedes;
    private Servicios servicios[];
    private int contadorServicios = 0;

    public Estadia(){
        for(int i = 0; i < 10; i++){
            servicios[i] = new Servicios();
        }
    }

    public Estadia(Calendar fechaIngreso, int cantidadDias, Alojamiento alojamiento, Huesped[] huespedes){
        this();
        this.id = contadorEstadias++;;
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

    public Huesped[] getHuespedes() {
        return this.huespedes;
    }

    public void setHuespedes(Huesped[] huespedes) {
        this.huespedes = huespedes;
    }

    public Servicios[] getServicios() {
        return this.servicios;
    }

    public void setServicios(Servicios[] servicios) {
        this.servicios = servicios;
    }

    public void addServicioLimpieza(Calendar fecha, int dniSolicitante, int numeroAlojamiento){
        servicios[contadorServicios].setFecha(fecha);
        servicios[contadorServicios].setDniSolicitante(dniSolicitante);
        servicios[contadorServicios].setNumeroAlojamiento(numeroAlojamiento);
        this.contadorServicios++;
    }

    public void addServicioBar(Calendar fecha, int dniSolicitante, int numeroAlojamiento, Menu plato, int cantidad){
        servicios[contadorServicios].setFecha(fecha);
        servicios[contadorServicios].setDniSolicitante(dniSolicitante);
        servicios[contadorServicios].setNumeroAlojamiento(numeroAlojamiento);
        ((ServicioBar)servicios[contadorServicios]).setPlato(plato);
        ((ServicioBar)servicios[contadorServicios]).setCantidad(cantidad);;
        this.contadorServicios++;
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
            }else{
                costoAdicionalLimpieza += Limpieza.getprecio();
            }
        }

        costoTotal += costoConsumos;
        costoTotal += costoAdicionalLimpieza;


        return costoTotal;
    }
}
