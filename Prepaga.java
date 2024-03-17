public class Prepaga {
    private int id;
    private String nombre;
    private String[] planes;
    private double topeReintegro;

    public Prepaga(int id, String nombre, String[] planes, double topeReintegro){
        this.id = id;
        this.nombre = nombre;
        this.planes = planes;
        this.topeReintegro = topeReintegro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
    	return nombre;
    }
    public void setNombre(String nombre) {
    	this.nombre = nombre;
    }

    public String[] getPlanes() {
        return planes;
    }

    public void setPlanes(String[] planes) {
        this.planes = planes;
    }

    public double getTopeReintegro() {
        return topeReintegro;
    }

    public void setTopeReintegro(double topeReintegro) {
        this.topeReintegro = topeReintegro;
    }
}
