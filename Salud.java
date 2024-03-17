public class Salud extends TratamientosPersonales {
    private boolean consultClinica;
    private double valorAdicional;

    public boolean isConsultClinica() {
        return consultClinica;
    }

    public void setConsultClinica(boolean consultClinica) {
        this.consultClinica = consultClinica;
    }

    public double getValorAdicional() {
        return valorAdicional;
    }

    public void setValorAdicional(double valorAdicional) {
        this.valorAdicional = valorAdicional;
    }

    @Override
    public double calcularCostoTratamiento() {
        double costoBase = super.calcularCostoTratamiento(); // Calling superclass method
        // Calculate additional cost based on whether consultation is required
        double costoTotal = consultClinica ? (costoBase + valorAdicional) : costoBase;
        return costoTotal;
    }
}
