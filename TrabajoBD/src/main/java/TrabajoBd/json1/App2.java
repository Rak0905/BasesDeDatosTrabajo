package TrabajoBd.json1;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Hello world!
 *
 */
public class App2 {
	public static void main(String[] args) {
		File f = new File("empresa.xml");
		Apuestas e = null;
		try {
			e = new ObjectMapper().readValue(f, Apuestas.class);
		} catch (IOException ex) {
			System.out.println("Error: " + ex.getLocalizedMessage());
		}
		if (e != null) {
			System.out.println(e);
		}
	}
}
