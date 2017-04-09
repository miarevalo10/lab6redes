package logica;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;


public class ManejadorInfoCliente{

	private String ipCli;
	private int puerto;
	private InfoObjeto[] buffer;
	private int total;


	public ManejadorInfoCliente(String ipCli, int puerto, int total){

		this.ipCli=ipCli;
		this.puerto=puerto;
		this.total=total;

		buffer = new InfoObjeto[this.total];
	}

	public void agregarObjeto(Objeto obj, long diferencia){

		InfoObjeto temp = new InfoObjeto(diferencia,obj);
		
		buffer[obj.getSecuencia()-1]=temp;
		
		System.out.println("agregar: "+(obj.getSecuencia()-1)+" - "+buffer[obj.getSecuencia()-1]);
		
		if(obj.getSecuencia()==obj.getTotal()){
			try {
				guardarInfo();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void guardarInfo() throws Exception{
		
		int cantidadActual=0;
		int perdidos=0;
		int recibidos=0;
		
		FileWriter fw = new FileWriter("cliente" + ipCli + "-" + puerto + ".txt", true);
		
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter out = new PrintWriter(bw);
		
		for (int i = 0; i < total; i++) {
			
			System.out.println(i + ":" + buffer[i]);
			InfoObjeto temp= buffer[i];
			
			if(temp==null){
				perdidos++;
			}
			else{
				recibidos++;
				cantidadActual+=temp.getDiferencia();
			    out.println(temp.getObjeto().getSecuencia() + " : " + temp.getDiferencia()+"ms");
			}
		}

		out.println("Promedio: " + (cantidadActual/recibidos));
		out.println("Numero de paquetes recibidos: " + recibidos);
		out.println("Numero de paquetes perdidos: " + perdidos);

    out.close();

	}

	public String getIpCli() {
		return ipCli;
	}

	public int getPuerto() {
		return puerto;
	}


}
