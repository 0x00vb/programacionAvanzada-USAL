package modelo;

public class TarifaDiaria {
    private int capacidad;
    private char categoria;
    private double tarifa;

    public TarifaDiaria(){}

    public TarifaDiaria(int capacidad, char categoria, double tarifa) {
        this.capacidad = capacidad;
        this.categoria = categoria;
        this.tarifa = tarifa;
    }
    
    public double getTarifa() {
        return this.tarifa;
    }

    public void setTarifa(double tarifa) {
        this.tarifa = tarifa;
    }
    
    public int getCapacidad() {
        return this.capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public char getCategoria() {
        return this.categoria;
    }

    public void setCategoria(char categoria) {
        this.categoria = categoria;
    }
}
