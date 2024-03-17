import java.util.Calendar;

public class Particular extends Cliente{
    private double descuento;

    public Particular(
        String nombre, int numeroDni, Calendar fechaNacimiento, String objetivo,
        Sucursal[] sucursales, char formaPago, double descuento
        ){
        super(nombre, numeroDni, fechaNacimiento, objetivo, sucursales, formaPago);
            
    }

    public double getDescuento(){
        return descuento;
    }

    public void setDescuento(double descuento){
        this.descuento = descuento;
    }

    public double calcularCosto(){

        return 0;
    }
}