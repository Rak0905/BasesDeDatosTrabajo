package TrabajoBd.json1;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Hello world!
 *
 */


@JsonInclude(JsonInclude.Include.NON_NULL)
public class App {
	public static void main(String[] args) {
		
		Sorteo s = new  Sorteo(1, "2080-05-09", "2080-05-15", "2080-05-16",null);
		Jugador j = new Jugador(1, "trabajo@gmail.com","009997d", "1234A", 10);
		Apuestas a = new Apuestas(1, "2080-05-09","2080-05-16:10:14", null, j, s);
		File f = new File("Apuestas.json");
		try {
			// creación del flujo de salida
			PrintWriter printWriter = new PrintWriter(new FileWriter(f));
			String json2write = new ObjectMapper().writeValueAsString(a);
			printWriter.print(json2write);
			printWriter.flush();
			printWriter.close();
		} catch (IOException ex) {
			System.out.println("Error: " + ex.getLocalizedMessage());
		}
	}
}
