package modelo;
import java.util.*;

import controlador.NotFoundException;

public class GestionTratamiento {
    private ArrayList<Integer> valoresEnteros;
    private ArrayList<Tratamiento> tratamientos;

    public GestionTratamiento(int capacidadInicial, Vector<Tratamiento> tratamientos){
        this.valoresEnteros = new ArrayList<Integer>(capacidadInicial);

        Random random = new Random();
        for(int i = 0; i < valoresEnteros.size(); i++){
            valoresEnteros.add(1000 + random.nextInt(9000));
        }

        this.tratamientos = new ArrayList<Tratamiento>(tratamientos);
    }

    public ArrayList<Integer> getValoresEnteros(){
        return this.valoresEnteros;
    }

    public void setValoresEnteros(ArrayList<Integer> valoresEnteros){
        this.valoresEnteros = valoresEnteros;
    }

    public ArrayList<Tratamiento> getTratamientos(){
        return this.tratamientos;
    }

    public void setTratamientos(ArrayList<Tratamiento> tratamientos){
        this.tratamientos = tratamientos;
    }

    public int valorMaximo(){
        int valorMaximo = 0;
        for(int i = 0 ; i < this.valoresEnteros.size(); i++){
            if(this.valoresEnteros.get(i) > valorMaximo){
                valorMaximo = this.valoresEnteros.get(i);
            }
        }
        return valorMaximo;
    }

    public void quitarObjeto(Tratamiento tratamiento) throws NotFoundException{
        if (!this.tratamientos.remove(tratamiento)) {
            throw new NotFoundException("El tratamiento no se encontró en la lista.");
        }
    }

    public ArrayList<Tratamiento> ordenDescendente(){
        this.tratamientos.sort(Comparator.comparingDouble(Tratamiento::getPrecio).reversed());
        return this.tratamientos;
    }

    public boolean hayRepetidos(){
        Set<String> nombres = new HashSet<String>();
        for(Tratamiento t : this.tratamientos){
            if(!nombres.add(t.getNombre())){
                return true;
            }
        }
        return false;
    }

    public boolean igualValores(List<Integer> array){
        for(int i : array){
            if(this.valoresEnteros.contains(i))
                return true;
        }
        return false;
    }

    public void agregar(List<Tratamiento> nuevosTratamientos){
        this.tratamientos.addAll(nuevosTratamientos);
    }

    public int busquedaMultiple(int i) throws NotFoundException{
        Map<Integer, Integer> contador = new HashMap<Integer, Integer>();
        for(Integer valor : this.valoresEnteros){
            contador.put(valor, contador.getOrDefault(contador, 0) + 1);
        }
        int cantidad = contador.get(i);
        
        if(cantidad == 0)
            throw new NotFoundException(null);
        
        return cantidad;
    }

    public List<Tratamiento> distintosValores(List<Tratamiento> array){
        List<Tratamiento> tratamientos = new ArrayList<Tratamiento>(this.tratamientos);
        List<Tratamiento> coinciden = new ArrayList<Tratamiento>(this.tratamientos);
        coinciden.retainAll(array);
        tratamientos.removeAll(coinciden);
        return tratamientos;
    }

    public List<Integer> copiarLista(int pos1, int pos2){
        if(pos1 < 0 || pos2 >= valoresEnteros.size()){
            throw new IllegalArgumentException("Posiciones no validas");
        }

        return new ArrayList<Integer>(valoresEnteros.subList(pos1, pos2));
    }

}
