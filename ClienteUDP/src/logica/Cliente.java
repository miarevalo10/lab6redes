package logica;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class Cliente {

	public static void enviarObjetos(String ip, int puerto, int cantidad) throws Exception {
		
		int contador=1;
		
        DatagramSocket sock = new DatagramSocket();
        InetAddress host = InetAddress.getByName(ip);
         
        while(contador<=cantidad)
        {
            String s = "secuencia: "+contador+" timestamp: " + new Date();
            byte[] b = s.getBytes();
             
            DatagramPacket  dp = new DatagramPacket(b , b.length , host , puerto);
            sock.send(dp);
            
            //now receive reply
            //buffer to receive incoming data
//            byte[] buffer = new byte[65536];
//            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
//            sock.receive(reply);
//             
//            byte[] data = reply.getData();
//            s = new String(data, 0, reply.getLength());
//            System.out.println(s);
            
            contador++;
        }
        
        sock.close();
	}
	
}
