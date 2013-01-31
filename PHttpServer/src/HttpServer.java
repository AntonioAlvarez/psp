import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/*
//si tenemos muchas peticiones con un solo hilo, hasta
//que no termine de resolverse la primera peticion no
//nos ponemos a escuchar la siguiente
 * 
*/
public class HttpServer {
	
	
	public static void main(String [] args) throws IOException, InterruptedException{
		final int port = 8080;
		ServerSocket serverSocket = new ServerSocket(port);
		while(true)
		{
			Socket socket = serverSocket.accept();
			SimpleServer.Process(socket);
		}
		
	}
	

}
