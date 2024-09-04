package controlador;
import modelo.*;
import java.util.*;

public class ClienteControlador {
    private ArrayList<Cliente> clientes = new ArrayList<>();

    public ArrayList<Integer>getDniClientes(){
        ArrayList<Integer> lista = new ArrayList<>();
        for(Cliente c : clientes){
            lista.add(c.getDNI());
        }
        return lista;
    }
    
    public Cliente buscarCliente(int dni) {
        for (Cliente cliente : clientes) {
            if (cliente.getDNI() == (dni)) {
                return cliente;
            }
        }
        return null;
    }

    public ArrayList<Cliente> getClientes(){
        return this.clientes;
    }
}
