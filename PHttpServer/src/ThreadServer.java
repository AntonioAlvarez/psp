import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class ThreadServer extends Thread {

	final static  String newLine = "\r\n";
	final static int  port = 8080;
	final static String fileNameError404 = "fileError404.html";
	final static String response200 = "HTTP/1.0 200 OK";
	final static  String response404 = "HTTP/1.0 404 Not Found";
	
	

	
	public static void multiHilo() throws IOException{
		
		
		
		while(true){
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
	}
	
	public static void main(String [] args) throws IOException{
		
		multiHilo();
		
	}
}
