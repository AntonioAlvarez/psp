
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class SimpleServerPrueba {
	
	
public static void monoHilo() throws IOException{
		
		final String newLine = "\r\n";
		int port = 8080;
		final String fileNameError404 = "fileError404.html";
		final String response200 = "HTTP/1.0 200 OK";
		final String response404 = "HTTP/1.0 404 Not Found";
		ServerSocket serverSocket = new ServerSocket(port);
		Socket socket = serverSocket.accept();
		
		Scanner scanner = new Scanner (socket.getInputStream());
		//System.out.println("Introduce el nombre de archivo a buscar");
		//Scanner leer = new Scanner(System.in);
		//String a=leer.nextLine();
		//String fileName=a+".html";
		
	//private static String getFileName (InputStream inputStream)	{
		
		final String defaultFileName = "index.html";
		String fileName="";
		
		while (true/*scanner.hasNextLine()*/)
		{
			String line = scanner.nextLine();//devuelve la linea
			if(line.startsWith("GET"))//GET /index.html HTTP/1.1
			{
				fileName = line.split(" ")[1].substring(1);//->index.html [dividimos la cadena]
				System.out.println("fileName=" + fileName);
			}
			System.out.println(line);
			if(line.equals(""))
				break;
			
			
		}
		
		//return !fileName.equals("") ? fileName : defaultFileName ;
	
	
		
		File file = new File(fileName);
		
		String responseFileName = file.exists() ? fileName : fileNameError404;
		String response = file.exists() ? response200 : response404;
		
		String header = response + newLine + newLine;
		byte [] headerBuffer = header.getBytes();
		
		OutputStream outputStream = socket.getOutputStream();
		outputStream.write(headerBuffer);
		
		final int bufferSize = 2048;
		byte [] buffer= new byte [bufferSize];
		
		FileInputStream fileInputStream = new FileInputStream(responseFileName);

		int count;

		while ( (count = fileInputStream.read(buffer)) != -1 )
			outputStream.write(buffer, 0, count);

		
		PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
		printWriter.println(response + newLine);
		printWriter.println(newLine);
		
		printWriter.close();
		serverSocket.close();
		
		socket.close();
		
		}
	//}

	public static void main(String [] args) throws IOException{
	
		monoHilo();
	
}
}
//***************************************************************************************

