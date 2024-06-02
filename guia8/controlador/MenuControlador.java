package controlador;

import modelo.Estadia;
import modelo.Menu;
import modelo.ServicioBar;
import modelo.Servicios;
import modeloDAO.MenuJSON;
import vista.MenuVista;

public class MenuControlador {
    private MenuJSON menuJSON = new MenuJSON();
    private Menu[] menu = menuJSON.leerMenus();
    private EstadiaControlador estadiaControlador = new EstadiaControlador();

    public void metodoAdicional1(String[] args){
        Menu[] menus = getMenus();
        Estadia[] estadias = estadiaControlador.getEstadias();
        double precioArgumento = Double.parseDouble(args[0]);

        for(Menu menu : menus){
            if(menu.getTipo() == "plato principal" && menu.getPrecio() > precioArgumento){
                boolean solicitado = false;
                for(Estadia estadia : estadias){
                    for(Servicios servicio : estadia.getServicios()){
                        if(servicio instanceof ServicioBar && ((ServicioBar)servicio).getPlato().getNombre() == menu.getNombre()){
                            solicitado = true;
                        }
                    }
                }
                if(!solicitado){
                    MenuVista.mostrarTexto(menu.getNombre());
                }
            }
        }
    }

    public Menu buscarMenu(String nombre){
        for(Menu plato : menu){
            if(plato.getNombre() == nombre){return plato;}
        }
        return null;
    }

    public Menu[] getMenus(){
        return menu;
    }
}