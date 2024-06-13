package modelo;

import java.util.Calendar;

public class Mesa {
    private int numero;
    private Provincia provincia;
    private Localidad localidad;
    private int votosPartidoA;
    private int votosPartidoB;
    private String tipoVotantes; // Femenino, Masculino, Ambos
    private Calendar fecha;

    public Mesa(int numero, Provincia provincia, Localidad localidad, int votosPartidoA, int votosPartidoB, String tipoVotantes) {
        this.numero = numero;
        this.provincia = provincia;
        this.localidad = localidad;
        this.votosPartidoA = votosPartidoA;
        this.votosPartidoB = votosPartidoB;
        this.tipoVotantes = tipoVotantes;
        this.fecha = Calendar.getInstance();
    }


    public int getNumero() {
        return this.numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Provincia getProvincia() {
        return this.provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Localidad getLocalidad() {
        return this.localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public int getVotosPartidoA() {
        return this.votosPartidoA;
    }

    public void setVotosPartidoA(int votosPartidoA) {
        this.votosPartidoA = votosPartidoA;
    }

    public int getVotosPartidoB() {
        return this.votosPartidoB;
    }

    public void setVotosPartidoB(int votosPartidoB) {
        this.votosPartidoB = votosPartidoB;
    }

    public String getTipoVotantes() {
        return this.tipoVotantes;
    }

    public void setTipoVotantes(String tipoVotantes) {
        this.tipoVotantes = tipoVotantes;
    }

    public Calendar getFecha() {
        return this.fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

}
