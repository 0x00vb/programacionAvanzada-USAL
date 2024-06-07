import java.util.*;



public class Main{
		private String nombre;
		private int numero;
		
		public Main(String nombre, int numero) {
			this.nombre = nombre;
			this.numero = numero;
		}
	
		public String getNombre() {
			return nombre;
		}


		public void setNombre(String nombre) {
			this.nombre = nombre;
		}


		public int getNumero() {
			return numero;
		}


		public void setNumero(int numero) {
			this.numero = numero;
		}
	public static void main(String[] args) {
		Main objeto1 = new Main("a", 1);
		Main objeto2 = new Main("b", 2);
		Main objeto3 = new Main("c", 3);
		
		TreeSet<Main> set = new TreeSet<>();
		
		set.add(objeto1);
		set.add(objeto2);
		set.add(objeto3);
		
		Iterator<Main> des = ((TreeSet<Main>) set).descendingIterator();

        while(des.hasNext()) {
        	Main obj = des.next();
        	System.out.println(obj.getNumero());
        }
	}



}