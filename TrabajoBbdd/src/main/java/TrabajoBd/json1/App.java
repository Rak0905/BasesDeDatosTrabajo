package TrabajoBd.json1;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Primera App
 *
 */
public class App {
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// Creación de clases
		Sorteo s = new Sorteo("2016-01-01", "2016-01-11", "2016-01-12", "combinación:[1, 3, 3, 4, 5],estrellas:[1,2] ",
				TipoSorteo.EUROMILLONES);
		Sorteo s2 = new Sorteo("2017-06-08", "2017-06-09", "2017-06-10", "numero-premiado:32987,reintegro:7",
				TipoSorteo.LOTERIA_NACIONAL);
		Jugador j = new Jugador(1, "trabajo@gmail.com", "009997d", "1234A", "112233445", 200);
		Apuestas e = new Apuestas(1, "2016-01-11", "2016-01-12:10:14", "combinación:[1, 2, 3, 4, 5],estrellas:[1,2] ",
				j, s);
		Apuestas ln = new Apuestas(1, "2017-06-09", "2017-06-10::21:00", "numero-premiado:32977,reintegro:7", j, s2);
		List<Apuestas> listaApuesta = new ArrayList<>();

		// listaApuesta.add(e);
		// listaApuesta.add(ln);
		BBdd_con_ins base = new BBdd_con_ins();
		Connection con = base.createConection();
		long idj = base.insertJugador(j, con);
		long ids1 = base.insertarSorteo(s, con);
		long ids2 = base.insertarSorteo(s2, con);
		base.insertApuesta(e, con, ids1, idj);
		base.insertApuesta(ln, con, ids2, idj);
		// Creación de ficheros
		listaApuesta = base.seleccionarApuestas(con);
		File f = new File("Apuestas.json");
		String jsonPersona = "Apuestas.json";

		// Creación del flujo de salida
		try (PrintWriter printWriter = new PrintWriter(new FileWriter(f));) {
			String json2write = new ObjectMapper().writeValueAsString(listaApuesta);
			printWriter.print(json2write);
			printWriter.flush();
		} catch (IOException ex) {
			System.out.println("Error: " + ex.getLocalizedMessage());
		}
	}

	// Para leer json y pasr a json
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
