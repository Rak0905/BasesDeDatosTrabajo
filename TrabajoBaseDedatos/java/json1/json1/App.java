package agustincrespo.json1;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Direccion d = new Direccion("Calle"," Galicia",52,null,null,null,null,null,13002,"Ciudad Real");
		Empresa e = new Empresa("Pescanova", d, 2009, 88);

		File f = new File("empresa.json");
		try {
			// creación del flujo de salida
			PrintWriter printWriter = new PrintWriter(new FileWriter(f));
			String json2write = new ObjectMapper().writeValueAsString(e);
			printWriter.print(json2write);
			printWriter.flush();
			printWriter.close();
		} catch (IOException ex) {
			System.out.println("Error: " + ex.getLocalizedMessage());
		}
	}
}
