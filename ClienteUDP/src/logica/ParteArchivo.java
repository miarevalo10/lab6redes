package logica;

import java.io.Serializable;

public class ParteArchivo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int total;
	private int secuencia;
	private String nombreArchivo;
	private byte[] data;
	
	
	public ParteArchivo(int total, int secuencia, String nombreArchivo,byte[] data) {
		this.total = total;
		this.secuencia = secuencia;
		this.nombreArchivo = nombreArchivo;
		this.data = data;
	}


	public int getTotal() {
		return total;
	}


	public int getSecuencia() {
		return secuencia;
	}


	public String getNombreArchivo() {
		return nombreArchivo;
	}


	public byte[] getData() {
		return data;
	}
	
}
