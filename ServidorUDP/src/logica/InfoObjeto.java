package logica;

public class InfoObjeto {
	
	private long diferencia;
	private Objeto objeto;
		
	public InfoObjeto(long diferencia, Objeto objeto) {
		this.diferencia = diferencia;
		this.objeto = objeto;
	}
	
	public long getDiferencia() {
		return diferencia;
	}
	public Objeto getObjeto() {
		return objeto;
	}
	
}
