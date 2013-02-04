
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;


/*hacerlo con metodos estaticos */
public class ThreadServer implements Runnable {
	

	private  final String newLine = "\r\n";
	private  String defaultFileName = "index.html";
	private String response200 = "HTTP/1.0 200 OK";
	private String response404 = "HTTP/1.0 404 NOT FOUND";
	private String fileNameError404 = "fileError404.html";
	
	private Socket socket;
	private InputStream inputStream;
	private OutputStream outputStream;
	private String fileName;
	
	public ThreadServer(Socket socket){
		//this. --> se refiere a un campo del objeto
		this.socket/*este socket es el campo*/ = socket;/*este socket es el parametro*/
	}

	@Override//override es una notacion,indica que un metodo sobrescribe a otro
	public void run() {
		
		//el metodo run no tiene parametros y por tanto si
		//necesitamos pasarle informacion tendremos que pasarselo
		//previamente o bien por un constructor o etc..
		
		System.out.println(Thread.currentThread().getName()+"Inicio");
		
		//for (int i=0;i<3;i++){
			//System.out.println(Thread.currentThread().getName() + "paso" + i);
			try{
			//	Thread.sleep(1000);
				inputStream=socket.getInputStream();
				outputStream=socket.getOutputStream();
			    getFileName();
				writeHeader();
				writeFile();
				socket.close();
				
			}catch(InterruptedException ex){
			}
			catch(IOException ex){	
			}
			System.out.println(Thread.currentThread().getName()+"fin");
		}






	private  String getFileName(){
	
		Scanner scanner = new Scanner(inputStream);
	
		String fileName = "";
	
		while (true){
			String line = scanner.nextLine();
			System.out.println(line);
			if (line.startsWith("GET")) { //GET /index.html HTTP/1.1
			fileName = line.substring (5, line.indexOf(" ", 5)); //-> index.html
		
			}
			if (line.equals(""))
				break;
		
		}
		
		if (fileName.equals(""))
			fileName = defaultFileName;
	
			System.out.println("fileName=" + fileName);
	
			return fileName;
		}

		private  void writeHeader() throws IOException {

			File file = new File(fileName);
			String response = file.exists() ? response200 : response404;
			String header = response + newLine + newLine;
			byte[] headerBuffer = header.getBytes();
			outputStream.write(headerBuffer);

		}

		private  void writeFile() throws IOException, InterruptedException {

		

			File file = new File(fileName);

			String responseFileName = file.exists() ? fileName : fileNameError404;

			final int bufferSize = 2048;

			byte [] buffer = new byte [bufferSize];

			FileInputStream fileInputStream = new FileInputStream(responseFileName);

			int count;
			//pausa
			while ((count = fileInputStream.read(buffer)) != -1){
				System.out.println(Thread.currentThread().getName() + ".");
				Thread.sleep(100);
				outputStream.write(buffer, 0, count);

			}
			fileInputStream.close();


	}
}