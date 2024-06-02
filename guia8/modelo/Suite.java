package modelo;

public class Suite extends Alojamiento{
    private String[] dispElectronicos;

    public Suite(int id, String area, TarifaDiaria TarifaDiaria, boolean disponible, String[] dispElectronicos){
        super(id, area, TarifaDiaria, disponible);
        this.dispElectronicos = dispElectronicos;
    }

    public String[] getDispElectronicos() {
        return this.dispElectronicos;
    }

    public void setDispElectronicos(String[] dispElectronicos) {
        this.dispElectronicos = dispElectronicos;
    }

    public double calcularCostoDiario(){
        return 0.0;
    }
}
