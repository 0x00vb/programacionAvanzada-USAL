public class Salud extends TratamientosPersonales {
    private boolean consultaClinica;
    private static double valorAdicional;

    public Salud(){}

    public boolean getConsultaClinica() {
        return consultaClinica;
    }

    public void setConsultaClinica(boolean consultaClinica) {
        this.consultaClinica = consultaClinica;
    }

    public double getValorAdicional() {
        return valorAdicional;
    }

    public void setValorAdicional(double valorAdicional) {
        Salud.valorAdicional = valorAdicional;
    }

    @Override
    public double calcularCostoTratamiento() {
        double costoBase = super.calcularCostoTratamiento(); // Calling superclass method
        // Calculate additional cost based on whether consultation is required
        double costoTotal = consultaClinica ? (costoBase + valorAdicional) : costoBase;
        return costoTotal;
    }
}
