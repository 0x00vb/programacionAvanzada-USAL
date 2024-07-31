package modelo;

import java.util.*;

public class Libro {
	private int cod;
	private String titulo;
	private String autor;
	private Calendar fechaPublicacion;
	private ArrayList<Prestamo> prestamos;
	
	public Libro(int cod, String titulo, String autor, Calendar fechaPublicacion) {
		this.cod = cod;
		this.titulo = titulo;
		this.autor = autor;
		this.fechaPublicacion = fechaPublicacion;
		this.prestamos = new ArrayList<>();
	}
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public Calendar getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(Calendar fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	public ArrayList<Prestamo> getPrestamos() {
		return prestamos;
	}
	public void setPrestamos(ArrayList<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}
	public void addPrestamo(int dni, Calendar f0, Calendar f1) {
		this.prestamos.add(new Prestamo(dni, f0, f1));
	}
	
}
