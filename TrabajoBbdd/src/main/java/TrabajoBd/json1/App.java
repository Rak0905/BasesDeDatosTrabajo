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
 * Creación del main donde se crea todas las clases y se crea el json.
 * @author tamara y raquel
 *
 */
public class App {
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// Creación de clases
		Sorteo s = new Sorteo("2016-01-01", "2016-01-11", "2016-01-12", "combinacion:[1, 3, 3, 4, 5],estrellas:[1,2] ",
				TipoSorteo.EUROMILLONES);
		Sorteo s2 = new Sorteo("2017-06-08", "2017-06-09", "2017-06-10", "numero-premiado:32987,reintegro:7",
				TipoSorteo.LOTERIA_NACIONAL);
		Jugador j = new Jugador("Lara", "Lara@gmail.com", "009997d", "1234A", "112233445", 200);
		Jugador j2 = new Jugador("Sara", "Sara@gmail.com", "009888d", "1154A", "111511515", 100);
		Apuestas e = new Apuestas(1, 20, "2017-06-09", 1000, "combinacion:[1, 2, 3, 4, 5],estrellas:[1,2]", j, s);
		Apuestas ln = new Apuestas(2, 10, "2017-06-09", 500, "numero-premiado:32977,reintegro:7", j, s2);
		List<Apuestas> listaApuesta = new ArrayList<>();

		// listaApuesta.add(e);
		// listaApuesta.add(ln);
		BBdd_con_ins base = new BBdd_con_ins();
		Connection con = base.createConection();
		//Insertamos los jugadores
		long idj = base.insertJugador(j, con);
		long idj2 = base.insertJugador(j2, con);
		//Insertamos los sorteos
		long ids1 = base.insertarSorteo(s, con);
		long ids2 = base.insertarSorteo(s2, con);
		//Insertamos las apuestas con los jugadores
		base.insertApuesta(e, con, ids1, idj);
		base.insertApuesta(ln, con, ids2, idj2);
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

	/**
	 * Para leer json y pasar a json
	 * @param filePath
	 * @return
	 */
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
