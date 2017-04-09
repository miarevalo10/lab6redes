package logica;

import java.io.Serializable;

public class Objeto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int secuencia;
	private String timeStamp;
	
	private int total;
	
	public Objeto(int secuencia, String timestamp, int total)
	{
		this.secuencia = secuencia;
		this.timeStamp = timestamp;
		this.total = total;

	}
	
	public int getSecuencia() {
		return secuencia;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public int getTotal() {
		return total;
	}
	
	
	
	
}
