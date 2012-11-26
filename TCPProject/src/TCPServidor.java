

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.util.Scanner;


public class TCPServidor {
	
	public static void main(String [] args) throws IOException{
		
		//holaMundo();
		connectAndWrite();
	}
	
	private static void connectAndWrite() throws IOException{
		
		 int port=10001;
		 
		 ServerSocket serverSocket = new ServerSocket(port);
		 
		 Socket socket = serverSocket.accept();
		 
		 PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);//autoflush=true
		 
		 printWriter.println("Hola desde el servidor ");
		 
		 socket.close();
		 
		 serverSocket.close();
	}
	
	private static void holaMundo() throws IOException
	{
		
		int port = 10001;
		
		ServerSocket serverSocket = new ServerSocket(port);
		
		Socket socket = serverSocket.accept();
		
		Scanner scanner = new Scanner(socket.getInputStream());
		
		//primer parametro un flujo 
		String line = scanner.nextLine();
		
		System.out.print("mensaje recibido= " + line);
		
		socket.close();
		
		serverSocket.close();
	}
	

}
