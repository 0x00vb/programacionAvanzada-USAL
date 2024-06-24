package modelo;

public class Limpieza extends Servicios{
    private static double precio;

    public Limpieza() {}

    public static double getprecio(){
        return precio;
    }

    public static void setPrecio(int nuevoPrecio){
        precio = nuevoPrecio;
    }

}
