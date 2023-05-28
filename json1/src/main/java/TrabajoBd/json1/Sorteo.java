package TrabajoBd.json1;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Sorteo {
private int id;
private String fechaApertura;
private String fechaCierre;
private String fechaCelebracion;
private String combinacionGanadora;
public Sorteo(int id, String fechaApertura, String fechaCierre, String fechaCelebracion, String combinacionGenerosa) {
	super();
	this.id = id;
	this.fechaApertura = fechaApertura;
	this.fechaCierre = fechaCierre;
	this.fechaCelebracion = fechaCelebracion;
	this.combinacionGanadora = combinacionGenerosa;
}


public Sorteo() {
	super();
}


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
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
public String getCombinacionGanadora() {
	return combinacionGanadora;
}
public void setCombinacionGanadora(String combinacionGenerosa) {
	this.combinacionGanadora = combinacionGenerosa;
}

@Override
public String toString() {
	return "Sorteo [id=" + id + ", fechaApertura=" + fechaApertura + ", fechaCierre=" + fechaCierre
			+ ", fechaCelebracion=" + fechaCelebracion + ", combinacionGenerosa=" + combinacionGanadora + "]";
}

}
