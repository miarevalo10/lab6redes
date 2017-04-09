

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import logica.Objeto;


public class ManejadorInfo extends Thread{
	
	private String ipCli;
	private int portCli;
	private Objeto obj;
	private long date;
	
	public ManejadorInfo(Objeto obj, String ipCli, int portCli, long date){
		this.obj=obj;
		this.ipCli=ipCli;
		this.portCli=portCli;
		this.date=date;
	}

	public void run(){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss:SS'Z'");

		
		//echo the details of incoming data - client ip : client port - client message
		System.out.println(ipCli + " : " + portCli + " - " + "secuencia "+obj.getSecuencia()
		+ " timestamp: " + obj.getTimeStamp());
		
//		String timestampCli = message.split("timestamp: ")[1];
		
		Date ts = null;
		try {
			ts = df.parse(obj.getTimeStamp());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		long diferencia = (date - ts.getTime());
		
		System.out.println("La diferencia es: " +diferencia+"ms");
		int secuencia = obj.getSecuencia();
		
		InfoCliente cli=null;
		
		if(secuencia==1)
		{
			int total = obj.getTotal();
			System.out.println("Numero de paquetes: " + total);
			cli = new InfoCliente(ipCli,portCli,total,Servidor.clientes.size()+1);
			Servidor.clientes.add(cli);
		}
		else
		{
			//Si ya existe,	buscar el cliente en la lista para guardar la info
			cli = Servidor.darCliente(ipCli, portCli);

		}

		String message = secuencia + " : " + diferencia;
		System.out.println("Servidor: " +  message + "ms");
		
	
		cli.añadirSecuencia(secuencia, diferencia);
		
		Servidor.cantidadT--;
		
		
		//DatagramPacket dp = new DatagramPacket(s.getBytes() , s.getBytes().length , incoming.getAddress() , incoming.getPort());
		//sock.send(dp);
	}
}
