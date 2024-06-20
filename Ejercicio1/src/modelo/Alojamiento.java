package modelo;

public abstract class Alojamiento{
    private int id;
    private String area;
    private TarifaDiaria TarifaDiaria;
    private boolean disponible;

    public Alojamiento(){}

    public Alojamiento(int id, String area, TarifaDiaria TarifaDiaria, boolean disponible) {
        this.id = id;
        this.area = area;
        this.TarifaDiaria = TarifaDiaria;
        this.disponible = disponible;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public TarifaDiaria getTarifaDiaria() {
        return this.TarifaDiaria;
    }

    public void setTarifaDiaria(TarifaDiaria TarifaDiaria) {
        this.TarifaDiaria = TarifaDiaria;
    }

    public boolean getDisponible() {
        return this.disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    abstract public double calcularCostoDiario();
}