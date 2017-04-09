import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ManejadorInfo extends Thread{
	
	private String ipCli;
	private int portCli;
	private String message;
	
	public ManejadorInfo(String s, String ipCli, int portCli){
		message=s;
		this.ipCli=ipCli;
		this.portCli=portCli;
		
	}

	public void run(){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:SS'Z'");

		
		//echo the details of incoming data - client ip : client port - client message
		System.out.println(ipCli + " : " + portCli + " - " + message);
		
		String timestampCli = message.split("timestamp: ")[1];
		
		Date ts = null;
		try {
			ts = df.parse(timestampCli);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		long diferencia = (new Date().getTime() - ts.getTime());
		
		System.out.println("La diferencia es: " +diferencia+"ms");
		String secuencia = message.split(" ")[1];
		
		InfoCliente cli=null;
		
		if(Integer.parseInt(secuencia)==1)
		{
			String total = message.split(" ")[5];
			System.out.println("Numero de paquetes: " + total);
			cli = new InfoCliente(ipCli,portCli,Integer.parseInt(total),Servidor.clientes.size()+1);
			Servidor.clientes.add(cli);
		}
		else
		{
			//Si ya existe,	buscar el cliente en la lista para guardar la info
			cli = Servidor.darCliente(ipCli, portCli);

		}

		message = secuencia + " : " + diferencia;
		System.out.println("Servidor: " +  message + "ms");
		
	
		cli.añadirSecuencia(secuencia, diferencia);
		
		Servidor.cantidadT--;
		
		
		//DatagramPacket dp = new DatagramPacket(s.getBytes() , s.getBytes().length , incoming.getAddress() , incoming.getPort());
		//sock.send(dp);
	}
}
