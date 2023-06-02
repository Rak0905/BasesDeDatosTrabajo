package TrabajoBd.json1;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
/**
 * Creación de clase Apuestas
 * @author Raquel
 *
 */
public class Apuestas {

	private int id;
	private double precio;
	private String fecha;
	private double premio;
	private String apuesta;
	private Jugador jugador;
	private Sorteo sorteo;

	public Apuestas(int id, double precio, String fecha, double premio, String apuesta, Jugador jugador,
			Sorteo sorteo) {
		this.id = id;
		this.precio = precio;
		this.fecha = fecha;
		this.premio = premio;
		this.apuesta = apuesta;
		this.jugador = jugador;
		this.sorteo = sorteo;
	}

	public Apuestas() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getPremio() {
		return premio;
	}

	public void setPremio(double premio) {
		this.premio = premio;
	}

	public String getApuesta() {
		return apuesta;
	}

	public void setApuesta(String apuesta) {
		this.apuesta = apuesta;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public Sorteo getSorteo() {
		return sorteo;
	}

	public void setSorteo(Sorteo sorteo) {
		this.sorteo = sorteo;
	}

	public void haGanado(Sorteo sorteo) {

		if (sorteo.getGanadora() != null) {
			if (sorteo.getGanadora().equals(apuesta)) {
				System.out.println("HA GANADO");
			} else {
				System.out.println("ha perdido");
			}
		} else {
			System.out.println("No se ha celebrado el sorteo");
		}

	}

	@Override
	public String toString() {
		return "Apuestas [id=" + id + ", fecha=" + fecha + ", fechayHoraCelebracion=" + premio + ", apuesta=" + apuesta
				+ ", jugador=" + jugador + ", sorteo=" + sorteo + "]";
	}

}