package logica;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class Cliente {

	public static void enviarObjetos(String ip, int puerto, int cantidad) throws Exception {
		
		int contador=1;
		
        DatagramSocket sock = new DatagramSocket();
        InetAddress host = InetAddress.getByName("localhost");
         
        while(contador<=cantidad)
        {
            String s = "secuencia: "+contador+"timestamp" + new Date();
            byte[] b = s.getBytes();
             
            DatagramPacket  dp = new DatagramPacket(b , b.length , host , puerto);
            sock.send(dp);
        }
        
        sock.close();
	}
	
}
