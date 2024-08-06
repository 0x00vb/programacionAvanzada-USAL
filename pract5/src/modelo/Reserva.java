package modelo;

public class Reserva {
	private Cliente cliente;
	private Evento evento;
	private int cantEntradas;
	public Reserva(Cliente cliente, Evento evento, int cantEntradas) {
		this.cliente = cliente;
		this.evento = evento;
		this.cantEntradas = cantEntradas;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	public int getCantEntradas() {
		return cantEntradas;
	}
	public void setCantEntradas(int cantEntradas) {
		this.cantEntradas = cantEntradas;
	}
	
}
