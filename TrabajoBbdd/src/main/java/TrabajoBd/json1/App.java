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
		// Creación de clases
		Sorteo s = new Sorteo("2016-01-01", "2016-01-11", "2016-01-12", null, "Euromillon");
		Sorteo s2 = new Sorteo("2017-06-08", "2017-06-09", "2017-06-10", null, "Loteria Nacional");
		Jugador j = new Jugador(1, "trabajo@gmail.com", "009997d", "1234A", 10);
		Apuestas e = new Apuestas(1, "2016-01-11", "2016-01-12:10:14", null, j, s);
		Apuestas ln = new Apuestas(1, "2017-06-09", "2017-06-10:21:00", null, j, s2);
		// Creación de ficheros
		File f = new File("Apuestas.json");
		String filePath = "loteriaEuro.json";
		String filePathN = "loteriaNacio.json";

		String jsonString = readFileAsString(filePath);
		String jsonStringN = readFileAsString(filePathN);
		s.setCombinacionGanadora(jsonString);
		s2.setCombinacionGanadora(jsonStringN);
		String apuesta = "apuesta.json";
		String jsonStringA = readFileAsString(apuesta);
		e.setApuesta(apuesta);
		ln.setApuesta(apuesta);

		// Creación del flujo de salida
		try (PrintWriter printWriter = new PrintWriter(new FileWriter(f));) {
			String json2write = new ObjectMapper().writeValueAsString(e);
			String json2write2 = new ObjectMapper().writeValueAsString(ln);
			printWriter.print(json2write);
			printWriter.print(json2write2);
			printWriter.flush();
		} catch (IOException ex) {
			System.out.println("Error: " + ex.getLocalizedMessage());
		}
	}

	// Para leer json y pasar a json
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
