package logica;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.Date;


public class ServidorArchivos {

	public static ArrayList<ManejadorArchivo> clientes;

	public static void main(String[] args) {

		clientes = new ArrayList<ManejadorArchivo>();

		int puerto = 8081;
		DatagramSocket sock = null;
		int tamBuffer = 65536;

		try	
		{
			//			puerto = Integer.parseInt(args[0]); --> Desdocumentar para exportar a jar y pasar puerto por consola

			sock = new DatagramSocket(puerto);
			byte[] buffer = new byte[tamBuffer];
			DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);

			System.out.println("Hello. Waiting for incoming data...");

			while(true) 	
			{
				sock.receive(incoming);
				byte[] data = incoming.getData();

				ByteArrayInputStream in = new ByteArrayInputStream(data);
				ObjectInputStream is = new ObjectInputStream(in);
				ParteArchivo obj = null;

				try {
					obj = (ParteArchivo) is.readObject();
					System.out.println("Object received = "+obj);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

				//Atributos básicos de conexion
				String ipCli = incoming.getAddress().getHostAddress();
				int portCli = incoming.getPort();


				//delegar al manejador
				ManejadorArchivo a = darManejador(ipCli);
				if(a!=null){
					if(obj.getSecuencia()==1){
						a = new ManejadorArchivo(ipCli, obj.getNombreArchivo(),obj.getTotal());
						a.guardarData(obj);	
					}
					else{
						a.guardarData(obj);
					}

				}
				else{
					a = new ManejadorArchivo(ipCli, obj.getNombreArchivo(),obj.getTotal());
					clientes.add(a);
					a.guardarData(obj);	
				}
			}
		}

		catch(IOException e)
		{
			System.err.println("IOException " + e);
		} 

	}

	public static ManejadorArchivo darManejador(String ipCliente){
		for(int i = 0; i<clientes.size();i++){
			ManejadorArchivo temp = clientes.get(i);
			if(temp.getIpCliente().equals(ipCliente)){
				return temp;
			}
		}
		return null;
	}
}
