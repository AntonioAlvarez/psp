
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Date;
import java.util.Scanner;


public class TCPClient {
	
	public static void main(String [] args) throws IOException{
		
		//holaMundo();
		//connectAndRead(args);
		connectUpvWeb();
	}
	private static void connectUpvWeb() throws IOException{
		String host = "www.upv.es";
		int port = 80;
		String line1 = "GET /index.html HTTP/1.1";
		String line2= "";
		
		InetAddress inetAddress= InetAddress.getByName(host);
		//establecemos la conexion
		Socket socket= new Socket(inetAddress, port);
		System.out.println("Se ha establecido la conexion" +
				"(es decir se han enviado y recibido los 3 paquetes iniciales )");
		//si pasa de esta linea quiere decir que ya se han enviado tres paquetes
		//uno de peticion el otro de vuelta de confirmacion y otro de aceptacion.
		PrintWriter printWriter= new PrintWriter(socket.getOutputStream(), true);//con el true vaciamos el buffer(auto-flush=true)
		printWriter.println(line1);
		printWriter.println(line2);
		
		InputStream inputStream = socket.getInputStream();
		Scanner scanner = new Scanner(inputStream);
		
		while(scanner.hasNextLine())
			System.out.println(scanner.nextLine());
		printWriter.close();
		scanner.close();
		socket.close();
		
	}

	private static void connectAndRead(String [] args) throws IOException{
		
		//debe de conectarse al host que se indique como primer parametro
				//al puerto que se indica como seg parametro, debe leer y mostrar por consola, todo lo que escribe el servidor
			/* Ejecutar en consola pasandole dos parametros:
			 * java .... Tcpclient www.google.es 80
			 * Los parametros van separados por espacios en blanco, y si queremos escribir una cadena de texto tenemos que 
			 * utilizar comillas
			 * 
			 * main (String[] args)
			 * string host = args[0]-> numero del parametro
			 * int port = Integer.parseInt(args[1]); -> Pasamos ese String a entero con parseInt();
			 * 
			 * tiene que haber un servidor escuchando por ese puerto-
			 */
	
		System.out.println("Argumentos: ");
		for(int index = 0; index < args.length; index++)
				System.out.println(args[index]);
		
		String host=args[0];
		int port= Integer.parseInt(args[1]);
		//conectarse mediante socket
		InetAddress inetAddress = InetAddress.getByName(host);
		Socket socket= new Socket(inetAddress, port);
		
		//si pasa de esta linea quiere decir que ya se han enviado tres paquetes
		//uno de peticion el otro de vuelta de confirmacion y otro de aceptacion.
		
		//flujo[es la propia informacion] de entrada
		Scanner scanner= new Scanner(socket.getInputStream());
		
		//ahora nos metemos en un ciclo para leer
		
		while(scanner.hasNextLine())//mientras halla nueva linea
				{
					System.out.println(scanner.nextLine());
				}
		
		socket.close();
	}
	
	
	private static void holaMundo() throws UnknownHostException, IOException
	{
		InetAddress inetAddress=InetAddress.getByName("127.0.0.1"); //localhost
		int port = 10001;
		Socket socket = new Socket(inetAddress, port);
		
		OutputStream outputStream = socket.getOutputStream();
		//primer parametro un flujo 
		PrintWriter printWriter = new PrintWriter(outputStream, true);
		printWriter.println("hola desde TCPClient a las " + new Date(port));
		
		printWriter.close();
		socket.close();
	}
	

}
