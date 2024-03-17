import java.util.Calendar;

abstract class Cliente {
    protected String nombre;
    protected int numeroDni;
    protected Calendar fechaNacimiento;
    protected String objetivo;
    protected Calendar fechaInicio;
    protected Sucursal[] sucursales;
    protected TratamientosPersonales[] tratamientos;
    protected char formaPago;

    public Cliente(){
        
    }   

    public Cliente(String nombre, int numeroDni, Calendar fechaNacimiento, String objetivo, Sucursal[] sucursales, char formaPago){
        this();

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroDni() {
        return numeroDni;
    }

    public void setNumeroDni(int numeroDni) {
        this.numeroDni = numeroDni;
    }

    public Calendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Calendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public Calendar getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Calendar fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Sucursal[] getSucursales() {
        return sucursales;
    }

    public void setSucursales(Sucursal[] sucursales) {
        this.sucursales = sucursales;
    }

    public TratamientosPersonales[] getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(TratamientosPersonales[] tratamientos) {
        this.tratamientos = tratamientos;
    }

    public char getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(char formaPago) {
        this.formaPago = formaPago;
    }

    public abstract double calcularCosto();
}
