package logica;

public class ThreadServer extends Thread{

	private Objeto objeto;
	private String ipCliente;
	private int puerto;
	private long diferencia;
	
	public ThreadServer(Objeto objeto, String ipCliente, int puerto,long diferencia) {
		this.objeto = objeto;
		this.ipCliente = ipCliente;
		this.puerto = puerto;
		this.diferencia = diferencia;
	}

	public void run(){
		
		ManejadorInfoCliente manejador = Servidor.darManejador(ipCliente, puerto);	
		if(manejador!=null){
			manejador.agregarObjeto(objeto, diferencia);
		}
		else{
			System.out.println("nuevo");
			manejador= new ManejadorInfoCliente(ipCliente,puerto, objeto.getTotal());
			Servidor.manejadores.add(manejador);
			manejador.agregarObjeto(objeto, diferencia);
		}
	}
}
