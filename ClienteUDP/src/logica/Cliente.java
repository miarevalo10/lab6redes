package logica;


import java.io.File;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cliente {

	public static void enviarObjetos(String ip, int puerto, int cantidad) throws Exception {

		int contador=1;

		DatagramSocket sock = new DatagramSocket();
		InetAddress host = InetAddress.getByName(ip); 

		//        TimeZone tz = TimeZone.getTimeZone("GMT-05:00");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:SS'Z'");
		//		df.setTimeZone(tz);

		while(contador<=cantidad)
		{

			String timestamp = df.format(new Date());
			String s = "secuencia: "+contador+" timestamp: " + timestamp ;
			if(contador==1)
				s+= "total:  " + cantidad;

			System.out.println("Cliente envía: " + s);
			byte[] b = s.getBytes();

			DatagramPacket  dp = new DatagramPacket(b , b.length , host , puerto);
			sock.send(dp);
			System.out.println("despues de enviar cli");

			//now receive reply
			//buffer to receive incoming data
			//byte[] buffer = new byte[65536];
			//DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
			//sock.receive(reply);

			//byte[] data = reply.getData();
			//s = new String(data, 0, reply.getLength());
			//System.out.println("Cliente recibe " + s);
			contador++;
		}

		sock.close();
	}

	public static void enviarArchivo(File archivo, String ip, int puerto, int tamBuffer) throws Exception {

		DatagramSocket sock = new DatagramSocket();
		InetAddress host = InetAddress.getByName(ip); 

		FileInputStream fis = new FileInputStream(archivo);
		byte [] data= new byte[tamBuffer];
	
		int bytesRead=fis.read(data);
		
		while (bytesRead  > -1) {
			fis.read(data);
			DatagramPacket dp=new DatagramPacket(data, data.length, host, puerto);
			sock.send(dp);
		}
		
		fis.close();
		sock.close();
	}
}
