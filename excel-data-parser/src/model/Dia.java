package model;

public enum Dia {
	SEGUNDA(0),
	TERCA(1),
	QUARTA(2),
	QUINTA(3),
	SEXTA(4);
	
	private int valor;
	
	Dia(int valor) {
		this.valor = valor;
	}
	
	public int getValor() {
		return valor;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
	}
}
