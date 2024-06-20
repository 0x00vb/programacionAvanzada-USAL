package modelo;

public class Cabaña extends Alojamiento{
    private boolean hidromasaje;
    private double porcentajeAdicional;

    public Cabaña() {}
    
    public Cabaña(int id, String area, TarifaDiaria TarifaDiaria, boolean disponible, boolean hidromasaje, double porcentajeAdicional) {
        super(id, area, TarifaDiaria, disponible);
        this.hidromasaje = hidromasaje;
        this.porcentajeAdicional = porcentajeAdicional;
    }

    public boolean getHidromasaje() {
        return this.hidromasaje;
    }

    public void setHidromasaje(boolean hidromasaje) {
        this.hidromasaje = hidromasaje;
    }

    public double getPorcentajeAdicional() {
        return this.porcentajeAdicional;
    }

    public void setPorcentajeAdicional(double porcentajeAdicional) {
        this.porcentajeAdicional = porcentajeAdicional;
    }
    
    public double calcularCostoDiario(){

        return 0.0;
    }
}