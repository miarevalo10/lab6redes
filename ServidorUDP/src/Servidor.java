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
	
	private static ArrayList<InfoCliente> clientes;
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
			System.out.println("Server socket created. Waiting for incoming data...");
			

			//communication loop
			while(true) 	
			{

				sock.receive(incoming);
				byte[] data = incoming.getData();
				
				String s = new String(data, 0, incoming.getLength());
				
				//Atributos básicos de conexion
				String ipCli = incoming.getAddress().getHostAddress();
				int portCli = incoming.getPort();

				//echo the details of incoming data - client ip : client port - client message
				System.out.println(ipCli + " : " + portCli + " - " + s);
				
				String timestampCli = s.split("timestamp: ")[1];
				Date ts = df.parse(timestampCli);
				long diferencia = (new Date().getTime() - ts.getTime());
				
				System.out.println("La diferencia es: " +diferencia+"ms");
				String secuencia = s.split(" ")[1];
				
				InfoCliente cli=null;
				
				if(Integer.parseInt(secuencia)==1)
				{
					String total = s.split(" ")[5];
					System.out.println("Numero de paquetes: " + total);
					cli = new InfoCliente(ipCli,portCli,Integer.parseInt(total),clientes.size()+1);
					clientes.add(cli);
				}
				else
				{
					//Si ya existe,	buscar el cliente en la lista para guardar la info
					cli = darCliente(incoming.getAddress().getHostAddress(), incoming.getPort());

				}

				s = secuencia + " : " + diferencia;
				System.out.println("Servidor: " +  s + "ms");
				
			
				cli.añadirSecuencia(secuencia, diferencia);
				
				
				DatagramPacket dp = new DatagramPacket(s.getBytes() , s.getBytes().length , incoming.getAddress() , incoming.getPort());
				sock.send(dp);
			}
		}

		catch(IOException e)
		{
			System.err.println("IOException " + e);
		} 
		catch (ParseException e) {
			System.err.println("ParseExepction " + e);

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
