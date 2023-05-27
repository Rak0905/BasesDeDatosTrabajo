package TrabajoBd.json1;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
public class Apuestas {
	private int id;
	private String fecha;
	private String fechayHoraCelebracion;
	private String apuesta;
	private Jugador jugador;
	private Sorteo sorteo;
	public Apuestas(int id, String fecha, String fechayHoraCelebracion, String apuesta, Jugador jugador,
			Sorteo sorteo) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.fechayHoraCelebracion = fechayHoraCelebracion;
		this.apuesta = apuesta;
		this.jugador = jugador;
		this.sorteo = sorteo;
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
	public String getFechayHoraCelebracion() {
		return fechayHoraCelebracion;
	}
	public void setFechayHoraCelebracion(String fechayHoraCelebracion) {
		this.fechayHoraCelebracion = fechayHoraCelebracion;
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
	@Override
	public String toString() {
		return "Apuestas [id=" + id + ", fecha=" + fecha + ", fechayHoraCelebracion=" + fechayHoraCelebracion
				+ ", apuesta=" + apuesta + ", jugador=" + jugador + ", sorteo=" + sorteo + "]";
	}
	
}