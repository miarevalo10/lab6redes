package logica;

public class ThreadServer extends Thread{
	
	private String ipCliente;
	private int puerto;
	private ParteArchivo objeto;
	
	public ThreadServer(String ipCliente, int puerto, ParteArchivo objeto) {
		this.ipCliente = ipCliente;
		this.puerto = puerto;
		this.objeto = objeto;
	}
	
	public void run(){
		
		ManejadorArchivo a = ServidorArchivos.darManejador(ipCliente);
		if(a!=null){
			if(objeto.getSecuencia()==1){
				a = new ManejadorArchivo(ipCliente, puerto, objeto.getNombreArchivo(),objeto.getTotal());
				a.guardarData(objeto);	
			}
			else{
				a.guardarData(objeto);
			}

		}
		else{
			a = new ManejadorArchivo(ipCliente,puerto, objeto.getNombreArchivo(),objeto.getTotal());
			ServidorArchivos.clientes.add(a);
			a.guardarData(objeto);	
		}
	}

}
