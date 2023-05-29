package TrabajoBd.json1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Primera App
 *
 */
public class App {
	public static void main(String[] args) {
		
		Sorteo s = new  Sorteo(1, "2080-05-09", "2080-05-15", "2080-05-16",null);
		Jugador j = new Jugador(1, "trabajo@gmail.com","009997d", "1234A", 10);
		Apuestas a = new Apuestas(1, "2080-05-09","2080-05-16:10:14", null, j, s);
		File f = new File("Apuestas.json");
		  String filePath = "loteria.json";
	        String jsonString = readFileAsString(filePath);
	        s.setCombinacionGanadora(jsonString);
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
	//para leer json y pasr a json
	 public static String readFileAsString(String filePath) {
	        StringBuilder stringBuilder = new StringBuilder();

	        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                stringBuilder.append(line);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return stringBuilder.toString();
	    }
}

