package modelo;

import java.util.ArrayList;

public class Suite extends Alojamiento{
    private ArrayList<String> dispElectronicos;

    public Suite() {}
    
    public Suite(int id, String area, TarifaDiaria TarifaDiaria, boolean disponible, ArrayList<String> dispElectronicos){
        super(id, area, TarifaDiaria, disponible);
        this.dispElectronicos = dispElectronicos;
    }

    public ArrayList<String> getDispElectronicos() {
        return this.dispElectronicos;
    }

    public void setDispElectronicos(ArrayList<String> dispElectronicos) {
        this.dispElectronicos = dispElectronicos;
    }

    public double calcularCostoDiario(){
        return 0.0;
    }
}
