import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class HttpServer {
	
	public static void main(String [] args) throws IOException{
		int port = 8080;
		ServerSocket serverSocket = new ServerSocket(port);
		Socket socket = serverSocket.accept();
		
		Scanner scanner = new Scanner (socket.getInputStream());
		String fileName="index.html";
		
		while (true/*scanner.hasNextLine()*/)
		{
			String line = scanner.nextLine();//devuelve la linea
			//if(line.startsWith("GET"))
			System.out.println(line);
			if(line.equals(""))
				break;
		}
		
		FileInputStream fileInputStream = new FileInputStream(fileName);
		
		PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
		printWriter.println("HTTP/1.0 404 NOT FOUND" + /*newLine*/"\n");
		printWriter.println(""/*newLine*/);
		
		printWriter.close();
		serverSocket.close();
		
		socket.close();
	}
}
