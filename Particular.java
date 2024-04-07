import java.util.Calendar;

public class Particular extends Cliente{
    private double porcentajeDescuento; //Es un porcentaje.
    private static int contadorClientesParticulares = 0;

    public Particular(
        String nombre, int numeroDni, Calendar fechaNacimiento, String objetivo,
        Sucursal[] sucursales, char formaPago, double porcentajeDescuento
        ){
        super(nombre, numeroDni, fechaNacimiento, objetivo, sucursales, formaPago); 
        this.porcentajeDescuento = porcentajeDescuento;
        contadorClientesParticulares++;
    }

    public double getPorcentajeDescuento(){
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(double porcentajeDescuento){
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public static int getContadorClientesParticulares() {
        return contadorClientesParticulares;
    }

    public static void setContadorClientesParticulares(int nuevoContadorClientesParticulares) {
        contadorClientesParticulares = nuevoContadorClientesParticulares;
    }

    public double calcularCosto(){
        double costoFinal = 0.0d;
        for(TratamientosPersonales tratamientoP : this.getTratamientosPersonales()){
            costoFinal += tratamientoP.calcularCostoTratamiento();
        }
        if(this.formaPago == 'e'){
            porcentajeDescuento += porcentajeDescuento * 0.05; //porcentajeDescuento es incrementado un 5%78
        }
        costoFinal -= costoFinal * porcentajeDescuento;
        return costoFinal;
    }
}