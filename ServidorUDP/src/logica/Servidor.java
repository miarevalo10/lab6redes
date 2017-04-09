package logica;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Servidor
{
	public static ArrayList<ManejadorInfoCliente> manejadores;

	public static void main(String args[])
	{
		int puerto = 8081;
		
		DatagramSocket sock = null;
		manejadores = new ArrayList<ManejadorInfoCliente>();
		
		try	
		{

			//			puerto = Integer.parseInt(args[0]);
			//--> Desdocumentar para exportar a jar y pasar puerto por consola


			//1. creating a server socket
			sock = new DatagramSocket(puerto);

			//buffer to receive incoming data
			byte[] buffer = new byte[65536];
			DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);

			//date format secuencias
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:SS'Z'");

			
			//2. Wait for an incoming data
			System.out.println("Hello. Waiting for incoming data...");


			//communication loop
			while(true) 	
			{

				sock.receive(incoming);
				byte[] data = incoming.getData();

				long date = new Date().getTime();

				//String s = new String(data, 0, incoming.getLength());
				
				ByteArrayInputStream in = new ByteArrayInputStream(data);
				ObjectInputStream is = new ObjectInputStream(in);
				Objeto obj = null;
				try {
					obj = (Objeto) is.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

				//Atributos básicos de conexion
				String ipCli = incoming.getAddress().getHostAddress();
				int portCli = incoming.getPort();
				
				Date ts = null;
				try {
					ts = df.parse(obj.getTimeStamp());
				} catch (ParseException e) {
					e.printStackTrace();
				}	
				long diferencia = (date - ts.getTime());
				
				ManejadorInfoCliente manejador = darManejador(ipCli, portCli);	
				if(manejador!=null){
					manejador.agregarObjeto(obj, diferencia);
				}
				else{
					System.out.println("nuevo");
					manejador= new ManejadorInfoCliente(ipCli,portCli, obj.getTotal());
					manejadores.add(manejador);
					manejador.agregarObjeto(obj, diferencia);
				}
			}
		}

		catch(IOException e)
		{
			System.err.println("IOException " + e);
		} 
	}

	public static ManejadorInfoCliente darManejador(String ip, int port)
	{
		for (int i = 0; i < manejadores.size(); i++) {
			
			ManejadorInfoCliente c = manejadores.get(i);
			if(c.getIpCli().equals(ip)&& c.getPuerto()==port)
			{
				return c;
			}
		}
		return null;
	}
}
