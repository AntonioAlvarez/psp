package serpis.psp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
	
	public static void main(String[]args){
		
		//averiguar funcionamiento de los patrones de busqueda(regex)
		String input = "GET /index.html HTTP/1.1";
		String regex="GET /(.*) HTTP/1.1";
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher =pattern.matcher(input);
		String fileName = matcher.group(1);
		System.out.println(fileName);
	}

}
