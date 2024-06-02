package modelo;

public class Huesped {
    private int documento;
    private String nombre;
    private String apellido;
    private String paisRecidencia;

    public Huesped(int documento, String nombre, String apellido, String paisRecidencia) {
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.paisRecidencia = paisRecidencia;
    }

    public int getDocumento() {
        return this.documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPaisRecidencia() {
        return this.paisRecidencia;
    }

    public void setPaisRecidencia(String paisRecidencia) {
        this.paisRecidencia = paisRecidencia;
    }
}
