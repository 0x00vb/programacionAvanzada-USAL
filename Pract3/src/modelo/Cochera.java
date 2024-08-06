package modelo;

public class Cochera {
	private int num;
	private char tipo;
	private double tarifa;
	private int maxHoras;
	private boolean disponible;

	public Cochera() {}
	
	public Cochera(int num, char tipo, double tarifa, int maxHoras, boolean disp) {
		this.num = num;
		this.tipo = tipo;
		this.tarifa = tarifa;
		this.maxHoras = maxHoras;
		this.disponible = disp;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	public double getTarifa() {
		return tarifa;
	}

	public void setTarifa(double tarifa) {
		this.tarifa = tarifa;
	}

	public int getMaxHoras() {
		return maxHoras;
	}

	public void setMaxHoras(int maxHoras) {
		this.maxHoras = maxHoras;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
}
