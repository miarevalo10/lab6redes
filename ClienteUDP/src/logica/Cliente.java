package logica;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.security.MessageDigest;
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
			Objeto obj = new Objeto(contador,df.format(new Date()),cantidad);

			//			String timestamp = df.format(new Date());
			//            String s = "secuencia: "+contador+" timestamp: " + timestamp ;
			//            if(contador==1)
			//            	s+= "total:  " + cantidad;

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

			ObjectOutputStream os = new ObjectOutputStream(outputStream);

			os.writeObject(obj);


			byte[] data = outputStream.toByteArray();

			DatagramPacket  dp = new DatagramPacket(data , data.length , host , puerto);
			sock.send(dp);

			contador++;
		}

		sock.close();
	}

	public static void enviarArchivo(File archivo, String ip, int puerto, int tamBuffer) throws Exception {

		DatagramSocket sock = new DatagramSocket();
		InetAddress host = InetAddress.getByName(ip); 
		
		//buffer
		byte [] data= new byte[tamBuffer];

		
		FileInputStream fis = new FileInputStream(archivo);
		int tamano= (fis.available()/tamBuffer);
		int secuencia=1;
				
		//hash
		MessageDigest md = MessageDigest.getInstance("MD5");
		
		while (secuencia<=tamano) {

			fis.read(data);
			md.update(data);
			
			ParteArchivo temp = new ParteArchivo(tamano, secuencia, archivo.getName(), data);
			
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			ObjectOutputStream os = new ObjectOutputStream(outputStream);
			os.writeObject(temp);
			
			byte[] data2 = outputStream.toByteArray();

			DatagramPacket dp=new DatagramPacket(data2, data2.length, host, puerto);
			sock.send(dp);
			
			secuencia++;
			
		}

		byte[] digest = md.digest();
		ParteArchivo temp = new ParteArchivo(tamano, -1, archivo.getName(), digest);
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(outputStream);
		os.writeObject(temp);
		
		byte[] data3 = outputStream.toByteArray();
		
		DatagramPacket dp=new DatagramPacket(data3, data3.length, host, puerto);
		sock.send(dp);
		
		System.out.println("Hash " + convertByteArrayToHexString(digest));

		fis.close();
		sock.close();
	}

	private static String convertByteArrayToHexString(byte[] arrayBytes) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < arrayBytes.length; i++) {
			stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return stringBuffer.toString();
	}

}