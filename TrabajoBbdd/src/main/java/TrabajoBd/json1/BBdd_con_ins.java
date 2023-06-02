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

			String sql = "INSERT INTO sorteo (fechaApertura,fechaCierre,fechaCelebracion,ganadora) VALUES (?, ?, ?, ?)";
			statement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);
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

	public static void insertApuesta(Apuestas apuesta, Connection connection, long s, long idj) {
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

				String sql = "INSERT INTO apuesta (id, precio, premio, fecha, apuestas, jugador, sorteo) VALUES (?, ?, ?, ?, ?, ?, ?)";
				statement = connection.prepareStatement(sql);
				statement.setInt(1, apuesta.getId());
				statement.setDouble(2, apuesta.getPrecio());
				statement.setDouble(3, apuesta.getPremio());
				statement.setString(4, apuesta.getFecha());
				statement.setString(5, apuesta.getApuesta());
				statement.setInt(6, (int) idj);
				statement.setInt(7, (int) s);

				System.out.println("Se ha insertado apuesta");
				
				statement.executeUpdate();
				connection.commit();
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
			String sql = "INSERT INTO jugador (nombre, correo, dni, contraseña,telefono, saldo) VALUES (?, ?, ?, ?, ?, ?)";
			statement = connection.prepareStatement(sql, statement.RETURN_GENERATED_KEYS);
			statement.setString(1, jugador.getNombre());
			statement.setString(2, jugador.getCorreo());
			statement.setString(3, jugador.getDni());
			statement.setString(4, jugador.getContraseña());
			statement.setString(5, jugador.getTelefono());
			statement.setDouble(6, jugador.getSaldo());
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
		String sql = "SELECT * FROM apuesta";
		String sql2 = "SELECT * FROM jugador WHERE id = ?";
		String sql3 = "SELECT * FROM sorteo WHERE id = ?";

		try {
			// Preparar la sentencia
			PreparedStatement sentencia = connection.prepareStatement(sql);
			resultados = sentencia.executeQuery();

			while (resultados.next()) {
				int id = resultados.getInt("id");
				double precio = resultados.getDouble("precio");
				double premio = resultados.getLong("premio");
				String fecha = resultados.getString("fecha");
				String apuesta = resultados.getString("apuestas");
				int jugadorId = resultados.getInt("jugador");
				int sorteoId = resultados.getInt("sorteo");

				// Obtener el objeto Jugador correspondiente
				PreparedStatement sentencia2 = connection.prepareStatement(sql2);
				sentencia2.setInt(1, jugadorId);
				ResultSet resultados2 = sentencia2.executeQuery();

				Jugador jugador = null;
				if (resultados2.next()) {
					// Obtener los datos del jugador

					String nombre = resultados2.getString("nombre");
					String correo = resultados2.getString("correo");
					String dni = resultados2.getString("dni");
					String contraseña = resultados2.getString("contraseña");
					String telefono = resultados2.getString("telefono");
					double saldo = resultados2.getDouble("saldo");
					// Crear el objeto Jugador
					jugador = new Jugador(nombre, correo, dni, contraseña, telefono, saldo);
				}

				// Obtener el objeto Sorteo correspondiente
				PreparedStatement sentencia3 = connection.prepareStatement(sql3);
				sentencia3.setInt(1, sorteoId);
				ResultSet resultados3 = sentencia3.executeQuery();

				Sorteo sorteo = null;
				if (resultados3.next()) {
					// Obtener los datos del sorteo
					String fechaApertura = resultados3.getString("fechaApertura");
					String fechaCierre = resultados3.getString("fechaCierre");
					String fechaCelebracion = resultados3.getString("fechaCelebracion");
					String ganadora = resultados3.getString("ganadora");

					// Crear el objeto Sorteo
					sorteo = new Sorteo(fechaApertura, fechaCierre, fechaCelebracion, ganadora, null);
				}

				Apuestas apuesta1 = new Apuestas(id, precio, fecha, premio, apuesta, jugador, sorteo);
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