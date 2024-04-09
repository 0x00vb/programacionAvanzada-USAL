public class TratamientosPersonales {
    protected Tratamiento tratamiento;
    protected int cantidadSesiones;

    public TratamientosPersonales(){}

    public TratamientosPersonales(Tratamiento tratamiento, int cantidadSesiones){
        this.tratamiento = tratamiento;
        this.cantidadSesiones = cantidadSesiones;
    }

    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }

    public int getCantidadSesiones() {
        return cantidadSesiones;
    }

    public void setCantidadSesiones(int cantidadSesiones) {
        this.cantidadSesiones = cantidadSesiones;
    }

    public double calcularCostoTratamiento() {
        double costoTotal = tratamiento.getPrecio() * cantidadSesiones;
        return costoTotal;
    }
}
