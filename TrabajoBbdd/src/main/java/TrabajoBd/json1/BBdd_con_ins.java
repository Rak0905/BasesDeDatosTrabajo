package TrabajoBd.json1;

/*
 * Primero creamos el metodo para crear la conexion
 */
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BBdd_con_ins {
	public Connection createConection() throws ClassNotFoundException, SQLException, IOException {
		Connection connection = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/loteria?serverTimezone=UTC",
					"furiosa", "Imperat0r!");

			connection.setAutoCommit(false);

			System.out.println("Se ha establecido conexion");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw e;

		}

		return connection;

	}

	/*
	 * cerramos la conexion
	 */
	public void diconnect(Connection connection) throws SQLException {
		try {
			if (null != connection) {
				connection.close();
				connection = null;

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;

		}
	}

	public static long insertarSorteo(Sorteo sorteo, Connection connection) throws SQLException {
		PreparedStatement statement = null;
		ResultSet generatedKeys = null;
		long id = 0;
		try {

			String sql = "INSERT INTO sorteo (fechaApertura,fechaCierre,fechaCelebracion,combinacionGanadora) VALUES (?, ?, ?, ?)";
			statement = connection.prepareStatement(sql,statement.RETURN_GENERATED_KEYS);
			statement.setString(1, sorteo.getFechaApertura());
			statement.setString(2, sorteo.getFechaCierre());
			statement.setString(3, sorteo.getFechaCelebracion());
			statement.setString(4, sorteo.getGanadora());
			
			if (statement.executeUpdate() > 0) {
				generatedKeys = statement.getGeneratedKeys();
				if (generatedKeys.next()) {
					id = generatedKeys.getInt(1);
				}
			}
			connection.commit();
			System.out.println("Se ha insertado sorteo");
			return id;

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			if (generatedKeys != null) {
				generatedKeys.close();
			}
			if (statement != null) {
				statement.close();
			}
		}
		return id;
	}

	public static void insertApuesta(Apuestas apuesta, Connection connection, long ids1, long idj) {
		PreparedStatement statement = null;
		try {
			// Verificar saldo suficiente del jugador
			double saldoJugador = apuesta.getJugador().getSaldo();
			double montoApuesta = 10.0; // Reemplaza 10.0 por el monto real de la apuesta
			if (saldoJugador < montoApuesta) {
				System.out.println("Saldo insuficiente. No se puede realizar la apuesta.");

			} else {
				apuesta.getJugador().setSaldo(saldoJugador - montoApuesta);
				updateSaldo(apuesta.getJugador(), connection); // Método para actualizar saldo del jugador

				String sql = "INSERT INTO apuesta (precio, premio, fecha, apuesta, jugador_id, sorteo_id) VALUES (?, ?, ?, ?, ?)";
				statement = connection.prepareStatement(sql);
				statement.setString(1, apuesta.getFecha());
				statement.setString(2, apuesta.getFechayHoraCelebracion());
				statement.setString(3, apuesta.getApuesta());
				statement.setInt(4, (int) idj);
				statement.setInt(5, (int) ids1);

				System.out.println("Se ha insertado apuesta");
//				statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static long insertJugador(Jugador jugador, Connection connection) {
		PreparedStatement statement = null;
		ResultSet generatedKeys = null;
		long id = 0;
		try {
			String sql = "INSERT INTO jugador (correo, dni, contraseña,telefono, saldo) VALUES (?, ?, ?, ?, ?)";
			statement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);
			statement.setString(1, jugador.getCorreo());
			statement.setString(2, jugador.getDni());
			statement.setString(3, jugador.getContraseña());
			statement.setString(4, jugador.getTelefono());
			statement.setDouble(5, jugador.getSaldo());
			if (statement.executeUpdate() > 0) {
				generatedKeys = statement.getGeneratedKeys();
				if (generatedKeys.next()) {
					id = generatedKeys.getInt(1);
				}
			}
			connection.commit();
			System.out.println("Se ha insertado jugador");
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}

	public static void updateSaldo(Jugador jugador, Connection connection) {
		PreparedStatement statement = null;
		try {
			String sql = "UPDATE jugador SET saldo = ? WHERE id = ?";
			statement = connection.prepareStatement(sql);
			statement.setDouble(1, jugador.getSaldo());
			statement.setInt(2, jugador.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<Apuestas> seleccionarApuestas(Connection connection) throws SQLException {
		List<Apuestas> apuestas = new ArrayList<>();
		ResultSet resultados = null;
		String sql = "SELECT * FROM apuesta  ";

		try {

			// Preparar la sentencia
			PreparedStatement sentencia = connection.prepareStatement(sql);
			resultados = sentencia.executeQuery();
			while (resultados.next()) {
				double precio = resultados.getDouble("precio");

				long premio = resultados.getLong("premio");

				String fecha = resultados.getNString("fecha");
				String apuesta = resultados.getNString("apuesta");
				String jugador = resultados.getNString("jugador");
				Apuestas apuesta1 = new Apuestas();
				apuestas.add(apuesta1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;

		} finally {
			if (resultados != null) {
				resultados.close();
			}

		}
		return apuestas;

	}
}