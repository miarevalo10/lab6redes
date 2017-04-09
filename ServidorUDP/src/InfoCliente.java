import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class InfoCliente {

	private int totalObjetos;
	private int puerto;
	private String ip;
	private int recibidos;
	private double totaltiempo;
	private ArrayList<String> secuencias;
	private int idCliente;
	
	
	public InfoCliente(String pIp,int pPuerto,int pTotal, int pId)
	{
		totalObjetos = pTotal;
		puerto =pPuerto;
		ip=pIp;
		idCliente = pId;
		secuencias =  new ArrayList<String>();
	}
	
	public void añadirSecuencia(int secuencia, long diferencia)
	{
		recibidos++;
		totaltiempo+=diferencia;
		System.out.println("DESDE INFO " + secuencia + " : " + diferencia +"ms");
		secuencias.add(secuencia + " : " + diferencia +"ms");
		if(secuencia==totalObjetos)
		{
			guardar();
		}
	
		
	}
	
	public void guardar()
	{
		FileWriter fw=null;
	
		try 
		{
			fw = new FileWriter("cliente" + idCliente + ".txt", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter out = new PrintWriter(bw);
		out.println("Cliente: " + idCliente +" IP: " + ip+ " puerto: " + puerto);
		for (int i = 0; i < secuencias.size(); i++) {
			System.out.println(secuencias.get(i));
		    out.println(secuencias.get(i));

		}
		System.out.println("PROMEDIO: " + (double)totaltiempo/recibidos);
		out.println("PROMEDIO: " + (double)totaltiempo/recibidos);
		out.println("Numero de paquetes recibidos: " + recibidos);
		out.println("Numero de paquetes perdidos: " + (totalObjetos-recibidos));

    out.close();
	}
	
	public int getTotalObjetos() {
		return totalObjetos;
	}
	public int getPuerto() {
		return puerto;
	}
	public String getIp() {
		return ip;
	}
	
}
