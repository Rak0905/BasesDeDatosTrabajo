package TrabajoBd.json1;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * App2
 * 
 */
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;

/**
 * Clase el cual coge un json y lo guarda en una lista
 * @author Raquel
 *
 */
public class App2 {
	public static void main(String[] args) {
		File file = new File("Apuestas.json");
		List<Apuestas> apuestas = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			apuestas = objectMapper.readValue(file, new TypeReference<List<Apuestas>>() {
			});
		} catch (IOException ex) {
			System.out.println("Error: " + ex.getLocalizedMessage());
		}
		if (apuestas != null) {
			for (Apuestas apuesta : apuestas) {
				System.out.println(apuesta);

			}
		}
	}
}
