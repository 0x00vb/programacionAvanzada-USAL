package modelo;

public class ServicioBar extends Servicios{
    private Menu plato;
    private int cantidad;

    public ServicioBar() {}
    
    public Menu getPlato() {
        return this.plato;
    }

    public void setPlato(Menu plato) {
        this.plato = plato;
    }

    public int getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double calcularCostoConsumo(){
        double costoConsumo = 0.0;
        costoConsumo = plato.getPrecio() * cantidad;
        return costoConsumo;
    }

}
