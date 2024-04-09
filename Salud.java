public final class Salud extends TratamientosPersonales {
    private boolean consultaClinica;
    private static double valorAdicional;

    public Salud(){}

    public Salud(Tratamiento tratamiento, int cantidadSesiones, boolean consultaClinica){
        super(tratamiento, cantidadSesiones);
        this.consultaClinica = consultaClinica;
    }

    public boolean getConsultaClinica() {
        return consultaClinica;
    }

    public void setConsultaClinica(boolean consultaClinica) {
        this.consultaClinica = consultaClinica;
    }

    public double getValorAdicional() {
        return valorAdicional;
    }

    public static void setValorAdicional(double valorAdicional) {
        Salud.valorAdicional = valorAdicional;
    }

    @Override
    public double calcularCostoTratamiento() {
        double costoTotal;
        if(consultaClinica){
            costoTotal = (super.getTratamiento().getPrecio() + valorAdicional) * super.getCantidadSesiones();
        }else{
            costoTotal = super.calcularCostoTratamiento();
        }
        return costoTotal;
    }
}
