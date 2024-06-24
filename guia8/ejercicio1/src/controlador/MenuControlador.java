package controlador;

import java.util.ArrayList;

import modelo.Estadia;
import modelo.Menu;
import modelo.ServicioBar;
import modelo.Servicios;
import modeloDAO.MenuJSON;
import vista.CLIVista;

public class MenuControlador {
    private MenuJSON menuJSON = new MenuJSON();
    private ArrayList<Menu> menu = menuJSON.leerMenus();
    private EstadiaControlador estadiaControlador = new EstadiaControlador();

    public void metodoAdicional1(String[] args){
    	ArrayList<Menu> menus = getMenus();
    	ArrayList<Estadia> estadias = estadiaControlador.getEstadias();
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
                	CLIVista.mostrarTexto(menu.getNombre());
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

    public ArrayList<Menu> getMenus(){
        return menu;
    }
}