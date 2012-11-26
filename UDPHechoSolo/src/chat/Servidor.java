package chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class Servidor {
	
	
	public static void main(String [] args) throws IOException, InterruptedException
	{
		
		
		//InetAddress direccion=InetAddress.getByName("127.0.0.1");
		//int puertoAlQueEnivio=10002;
		
		byte[]bufEnviar=new byte[2048];
		byte[]bufRecibir=new byte[2048];
		
		
		while(true)
		{
		
			DatagramPacket paqueteRecibir=
					new DatagramPacket(bufRecibir,bufRecibir.length);
			DatagramSocket socket=new DatagramSocket(10001);
			System.out.println("servidor esta escuchando....");
			
			
			socket.receive(paqueteRecibir);
			socket.close();
			
			//****************************************************************
			//cogemos la direccion y el puerto del que proviene
			//direccion=socket.getInetAddress();
			
			//int puertoSalida=socket.getPort();
			//****************************************************************
			//socket.close();
			
			System.out.println("El mensaje se ha recibido");
			
			//***********************************************************************
			int puertoAlQueEnvio=paqueteRecibir.getPort();
			System.out.println("El puerto del que proviene es: " + puertoAlQueEnvio);
			InetAddress direccion=paqueteRecibir.getAddress();
			System.out.println("La direccion de la que proviene es: " + direccion.toString());
			
			String text=new String(paqueteRecibir.getData(), 0, paqueteRecibir.getLength());
			System.out.println("El mensaje es: " + text);
			Thread.sleep(2000);
			System.out.println("El mensaje va ha ser pasado a mayusculas");
			Thread.sleep(2000);
			
			String mayusculas=text.toUpperCase();
			System.out.println("El mensaje pasado a mayusculas es " + mayusculas);
			bufEnviar=mayusculas.getBytes();
			DatagramPacket paqueteEnviar=
					new DatagramPacket(bufEnviar,bufEnviar.length,direccion,puertoAlQueEnvio);
			DatagramSocket socket1=new DatagramSocket();
			socket1.send(paqueteEnviar);
			socket1.close();
			//socket1.setReuseAddress(true);
			
			//socket.close();
			System.out.println("El mensaje enviado es: " + mayusculas);
			
			
		}

	}
	
}
