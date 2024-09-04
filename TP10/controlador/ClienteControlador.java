package controlador;
import modelo.*;
import java.util.*;

public class ClienteControlador {
    private ArrayList<Cliente> clientes = new ArrayList<>();



    public Cliente buscarCliente(int dni) {
        for (Cliente cliente : clientes) {
            if (cliente.getDNI() == (dni)) {
                return cliente;
            }
        }
        return null;
    }
}
