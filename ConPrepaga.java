import java.util.Calendar;

public class ConPrepaga extends Cliente{
    private Prepaga[] prepagas;
    private double aumentoAfiliado;

    public ConPrepaga(
        String nombre, int numeroDni, Calendar fechaNacimiento, String objetivo,
        Sucursal[] sucursales, char formaPago, Prepaga[] prepagas, double aumentoAfiliado
    ){
        super(nombre, numeroDni, fechaNacimiento, objetivo, sucursales, formaPago);
        this.prepagas = prepagas;
        this.aumentoAfiliado = aumentoAfiliado; 
    }

    public Prepaga[] getPrepagas() {
        return prepagas;
    }

    public void setPrepagas(Prepaga[] prepagas) {
        this.prepagas = prepagas;
    }

    public double getAumentoAfiliado() {
        return aumentoAfiliado;
    }

    public void setAumentoAfiliado(double aumentoAfiliado) {
        this.aumentoAfiliado = aumentoAfiliado;
    }

    @Override
    public double calcularCosto() {
        // Implement the abstract method for calculating the cost
        // This method will be specific to the ConPrepaga class
        // You can use the attributes of the class to calculate the cost
        return 0; // Placeholder, replace with actual logic
    }
}