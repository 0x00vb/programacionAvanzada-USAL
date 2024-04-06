public class TratamientosPersonales {
    private Tratamiento tratamiento;
    private int cantidadSesiones;

    public TratamientosPersonales(){}

    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }

    public int getcantidadSesiones() {
        return cantidadSesiones;
    }

    public void setcantidadSesiones(int cantidadSesiones) {
        this.cantidadSesiones = cantidadSesiones;
    }

    public double calcularCostoTratamiento() {

        return 0.0;
    }
}
