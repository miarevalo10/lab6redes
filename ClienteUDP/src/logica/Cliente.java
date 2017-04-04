package logica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente {

	public static void enviarObjetos(String ip, int puerto, int parseInt) {
		
        DatagramSocket sock = null;
        String s;
         
        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
         
        try
        {
            sock = new DatagramSocket();
             
            InetAddress host = InetAddress.getByName("localhost");
             
            while(true)
            {

                s = (String)cin.readLine();
                byte[] b = s.getBytes();
                 
                DatagramPacket  dp = new DatagramPacket(b , b.length , host , puerto);
                sock.send(dp);
                 
                //now receive reply
                //buffer to receive incoming data
                byte[] buffer = new byte[65536];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                sock.receive(reply);
                 
                byte[] data = reply.getData();
                s = new String(data, 0, reply.getLength());
                 
            }
        }
         
        catch(IOException e)
        {
            System.err.println("IOException " + e);
        }
		
	}
	
}
