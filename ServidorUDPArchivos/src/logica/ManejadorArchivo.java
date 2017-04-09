package logica;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ManejadorArchivo{
	
	private String ipCliente;
	private String nombreLibro;
	private int cantidad;
	private int tamano;
	
	private ParteArchivo[] buffer;

	public ManejadorArchivo(String ipCliente, String nombreLibro, int cantidad) {
	
		this.ipCliente = ipCliente;
		this.nombreLibro = nombreLibro;
		this.cantidad = cantidad;
		
		buffer= new ParteArchivo[cantidad];
	}

	public void guardarData(ParteArchivo parte) {
		
		tamano= parte.getData().length*parte.getTotal();
		
		System.out.println("guardando " + parte.getSecuencia() + " - "+ parte.getTotal());
		
		buffer[parte.getSecuencia()-1]=parte;
		if(verificarBuffer()){
			guardarLibro();
		};
	}

	public void guardarLibro() {
		
		System.out.println("creando" + nombreLibro);	
		FileOutputStream fos=null;
		try {
			fos = new FileOutputStream(nombreLibro, true);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		
		byte [] mybytearray  = new byte [tamano+ 1024*1024];
		
		int actual=0;
		
		for(int i=0; i<buffer.length;i++){
			
			ParteArchivo temp = buffer[i];
			System.arraycopy(temp.getData(), 0, mybytearray, actual, temp.getData().length);
			actual +=temp.getData().length;
		}
		
		try {
			bos.write(mybytearray, 0 , actual);
			bos.flush();
			System.out.println("done");
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}

	public String getIpCliente() {
		return ipCliente;
	}
	
	private boolean verificarBuffer() {
		
		for(int i=0; i<buffer.length;i++){
			if(buffer[i]==null){
				System.out.println("no existe " + i);
				return false;
			}
		}
		return true;
		
	}
}
