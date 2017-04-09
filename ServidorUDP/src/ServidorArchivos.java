import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;


public class ServidorArchivos {
	public static ArrayList<InfoCliente> clientes;

	public static void main(String[] args) {
		
		int puerto = 8081;
		DatagramSocket sock = null;
		int tamBuffer = 65536;

		clientes = new ArrayList<InfoCliente>();
		
		FileOutputStream fos;
		BufferedOutputStream bos;
		
		try	
		{
			//			puerto = Integer.parseInt(args[0]); --> Desdocumentar para exportar a jar y pasar puerto por consola

			sock = new DatagramSocket(puerto);
			byte[] buffer = new byte[tamBuffer];
			DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);

			//			TimeZone tz = TimeZone.getTimeZone("GMT-05:00");
			//DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:SS'Z'");
			//			df.setTimeZone(tz);

			System.out.println("Hello. Waiting for incoming data...");

			while(true) 	
			{
				sock.receive(incoming);
				byte[] data = incoming.getData();

			
				String path = "data/libro.pdf";
				fos = new FileOutputStream(path);
				bos = new BufferedOutputStream(fos);

				int bytesRead=0;

				while (bytesRead>-1) {

				}		
			}
		}

		catch(IOException e)
		{
			System.err.println("IOException " + e);
		} 

	}

}
