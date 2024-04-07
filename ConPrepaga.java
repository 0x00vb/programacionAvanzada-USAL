import java.util.Calendar;

public class ConPrepaga extends Cliente{
    private Prepaga prepaga;
    private long numeroAfiliado;

    public ConPrepaga(
        String nombre, int numeroDni, Calendar fechaNacimiento, String objetivo,
        Sucursal[] sucursales, char formaPago, Prepaga prepaga, long numeroAfiliado
    ){
        super(nombre, numeroDni, fechaNacimiento, objetivo, sucursales, formaPago);
        this.prepaga = prepaga;
        this.numeroAfiliado = numeroAfiliado; 
    }

    public Prepaga getPrepaga() {
        return prepaga;
    }

    public void setPrepaga(Prepaga prepaga) {
        this.prepaga = prepaga;
    }

    public long getnumeroAfiliado() {
        return numeroAfiliado;
    }

    public void setnumeroAfiliado(long numeroAfiliado) {
        this.numeroAfiliado = numeroAfiliado;
    }

    @Override
    public double calcularCosto() {
        double costoFinal = 0.0d;
        for(TratamientosPersonales tratamientoP : this.getTratamientosPersonales()){
            costoFinal += tratamientoP.calcularCostoTratamiento();
        }
        costoFinal -= prepaga.getTopeReintegro();
        return costoFinal;
    }
}