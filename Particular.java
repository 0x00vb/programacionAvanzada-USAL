import java.util.Calendar;

public class Particular extends Cliente{
    private static double descuento;

    public Particular(
        String nombre, int numeroDni, Calendar fechaNacimiento, String objetivo,
        Sucursal[] sucursales, char formaPago
        ){
        super(nombre, numeroDni, fechaNacimiento, objetivo, sucursales, formaPago); 
    }

    public double getDescuento(){
        return descuento;
    }

    public void setDescuento(double descuento){
        Particular.descuento = descuento;
    }

    public double calcularCosto(){

        return 0;
    }
}