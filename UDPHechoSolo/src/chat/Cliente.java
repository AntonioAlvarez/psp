package chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente {
	
	
	
	public static void main(String [] args) throws IOException, InterruptedException
	{
		
		Scanner teclado = new Scanner(System.in);
		
		byte[] bufEnviar=new byte[2048];
		byte[] bufRecibir=new byte[2048];
		
		//poner la direccion a la que enviamos
		InetAddress direccion=InetAddress.getByName("127.0.0.1");
		int puerto=10001;
		DatagramSocket socket=new DatagramSocket();
		
		while(true)
		{
			
			System.out.println("Introduce el mensaje a enviar: ");
			String text=teclado.nextLine();
				
			bufEnviar=text.getBytes();
			
			//creamos el paquete a enviar y lo rellenamos con los datos correspondientes
			DatagramPacket paqueteEnviar=
					new DatagramPacket(bufEnviar,bufEnviar.length,direccion,puerto);
			
			
			socket.send(paqueteEnviar);
			//socket.setReuseAddress(true);
			
			System.out.println("El mensaje se ha enviado correctamente");
			
			DatagramPacket paqueteRecibir=
					new DatagramPacket(bufRecibir,bufRecibir.length);
			System.out.println("el cliente va a escuchar...");
			//DatagramSocket socketRecibir=new DatagramSocket();
			socket.receive(paqueteRecibir);
			
			//socket.setReuseAddress(true);
			
			String mensajeRecibido=new String( paqueteRecibir.getData(),0,paqueteRecibir.getLength());
			System.out.println("El mensaje recibido es: " + mensajeRecibido + 
					" y la direccion de la que proviene es: " + paqueteRecibir.getAddress() );
			Thread.sleep(5000);
		
		}
	}

}
