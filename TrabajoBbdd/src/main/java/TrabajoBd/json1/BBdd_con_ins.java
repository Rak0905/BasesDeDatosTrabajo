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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.processing.Generated;

public class BBdd_con_ins {
	public Connection createConection() throws ClassNotFoundException, SQLException, IOException {
		Connection connection = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://192.168.56.103:3306/socios?serverTimezone=UTC",
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
	
	public static void InsertarSorteo(Sorteo sorteo, Connection connection) {
		PreparedStatement statement = null;
		try {
             
                 String sql=    "INSERT INTO sorteo (fechaApertura,fechaCierre,fechaCelebracion,combinacionGanadora) VALUES (?, ?, ?, ?)";
               statement = connection.prepareStatement(sql);
            statement.setString(1, sorteo.getFechaApertura());
            statement.setString(2,sorteo.getFechaCierre());
            statement.setString(3,sorteo.getFechaCelebracion() );
            statement.setString(4, sorteo.getCombinacionGanadora());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
