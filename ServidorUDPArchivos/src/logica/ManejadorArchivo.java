package logica;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.security.MessageDigest;

public class ManejadorArchivo{
	
	private String ipCliente;
	private String nombreLibro;
	private int cantidad;
	private int tamano;
	private String hashCliente;
	
	private ParteArchivo[] buffer;

	public ManejadorArchivo(String ipCliente, String nombreLibro, int cantidad) {
	
		this.ipCliente = ipCliente;
		this.nombreLibro = nombreLibro;
		this.cantidad = cantidad;
		this.hashCliente="";
		
		buffer= new ParteArchivo[this.cantidad];
	}

	public void guardarData(ParteArchivo parte) {
		
		if(parte.getSecuencia()==-1){
			hashCliente=convertByteArrayToHexString(parte.getData());
		}
		else{
			 tamano= parte.getData().length*parte.getTotal();
		     buffer[parte.getSecuencia()-1]=parte;
		}
			
		if(verificarBuffer()&&!hashCliente.equals("")){
			try {
				guardarLibro();
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	}

	public void guardarLibro() throws Exception {
		
		System.out.println("creando" + nombreLibro);	
		
		//hash
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    
	    FileOutputStream fos=new FileOutputStream(nombreLibro);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		
		byte [] mybytearray  = new byte [tamano+ 1024*1024];
		
		int actual=0;
		
		for(int i=0; i<buffer.length;i++){
			
			ParteArchivo temp = buffer[i];
			System.arraycopy(temp.getData(), 0, mybytearray, actual, temp.getData().length);
			actual +=temp.getData().length;
			
			md.update(temp.getData());
		}
		
		bos.write(mybytearray, 0 , actual);
		bos.flush();
		System.out.println("done");
		bos.close();
		
		byte[] digest = md.digest();
		String hashServer = convertByteArrayToHexString(digest);
		if(hashServer.equals(hashCliente)){
			System.out.println("Ambos hash son iguales");
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
	
	private static String convertByteArrayToHexString(byte[] arrayBytes) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < arrayBytes.length; i++) {
			stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return stringBuffer.toString();
	}
}
