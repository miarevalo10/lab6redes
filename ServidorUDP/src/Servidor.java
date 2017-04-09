import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Servidor
{
	public static ArrayList<InfoCliente> clientes;
	public static int cantidadT;

	public static void main(String args[])
	{
		int puerto = 8081;
		DatagramSocket sock = null;
		clientes = new ArrayList<InfoCliente>();
		try	
		{
			//			puerto = Integer.parseInt(args[0]); --> Desdocumentar para exportar a jar y pasar puerto por consola

			//1. creating a server socket, parameter is local port number
			sock = new DatagramSocket(puerto);

			//buffer to receive incoming data
			byte[] buffer = new byte[65536];
			DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);

			//			TimeZone tz = TimeZone.getTimeZone("GMT-05:00");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:SS'Z'");
			//			df.setTimeZone(tz);

			//2. Wait for an incoming data
			System.out.println("Hello. Waiting for incoming data...");


			//communication loop
			while(true) 	
			{
				if(cantidadT <=100){
					sock.receive(incoming);
					byte[] data = incoming.getData();

					String s = new String(data, 0, incoming.getLength());

					//Atributos básicos de conexion
					String ipCli = incoming.getAddress().getHostAddress();
					int portCli = incoming.getPort();


					ManejadorInfo t = new ManejadorInfo(s, ipCli, portCli);
					t.run();
					cantidadT++;
				}
			}
		}

		catch(IOException e)
		{
			System.err.println("IOException " + e);
		} 
	}

	public static InfoCliente darCliente(String ip, int puerto)
	{
		InfoCliente cli = null;
		for (int i = 0; i < clientes.size() && cli==null; i++) {
			InfoCliente c = clientes.get(i);
			if(c.getIp().equals(ip)&&c.getPuerto()==puerto)
			{
				cli=c;
			}

		}


		return cli;
	}


}
