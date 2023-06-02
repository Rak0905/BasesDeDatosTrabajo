package TrabajoBd.json1;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

/**
 * Creación de sorteo
 * @author tamara
 *
 */
public class Sorteo {
	
	private String fechaApertura;
	private String fechaCierre;
	private String fechaCelebracion;
	private String ganadora;
	private TipoSorteo tipoSorteo;

	public Sorteo(String fechaApertura, String fechaCierre, String fechaCelebracion, String ganadora,
			TipoSorteo tipoSorteo) {
		this.fechaApertura = fechaApertura;
		this.fechaCierre = fechaCierre;
		this.fechaCelebracion = fechaCelebracion;
		this.ganadora = ganadora;
		this.tipoSorteo = tipoSorteo;
	}

	public Sorteo() {
		super();
	}

	public String getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(String fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public String getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public String getFechaCelebracion() {
		return fechaCelebracion;
	}

	public void setFechaCelebracion(String fechaCelebracion) {
		this.fechaCelebracion = fechaCelebracion;
	}

	public String getGanadora() {
		return ganadora;
	}

	public void setGanadora(String ganadora) {
		this.ganadora = ganadora;
	}

	public TipoSorteo getTipoSorteo() {
		return tipoSorteo;
	}

	public void setTipoSorteo(TipoSorteo tipoSorteo) {
		this.tipoSorteo = tipoSorteo;
	}

	@Override
	public String toString() {
		return "Sorteo [ fechaApertura=" + fechaApertura + ", fechaCierre=" + fechaCierre + ", fechaCelebracion="
				+ fechaCelebracion + ", combinacionGenerosa=" + ganadora + "]";
	}

}
