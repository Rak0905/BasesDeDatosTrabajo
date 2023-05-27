package agustincrespo.json1;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Direccion {
private String tipoVia;
private String nombreVia;
private int numeros;
private String escalera;
private String portal;
private String bloque;
private String piso;
private String puerta;
private int cp;
private String ciudad;
public Direccion(String tipoVia, String nombreVia, int numeros, String escalera, String portal, String bloque,
		String piso, String puerta, int cp, String ciudad) {
	super();
	this.tipoVia = tipoVia;
	this.nombreVia = nombreVia;
	this.numeros = numeros;
	this.escalera = escalera;
	this.portal = portal;
	this.bloque = bloque;
	this.piso = piso;
	this.puerta = puerta;
	this.cp = cp;
	this.ciudad = ciudad;
}
public Direccion() {
	super();
}
public String getTipoVia() {
	return tipoVia;
}
public void setTipoVia(String tipoVia) {
	this.tipoVia = tipoVia;
}
public String getNombreVia() {
	return nombreVia;
}
public void setNombreVia(String nombreVia) {
	this.nombreVia = nombreVia;
}
public int getNumeros() {
	return numeros;
}
public void setNumeros(int numeros) {
	this.numeros = numeros;
}
public String getEscalera() {
	return escalera;
}
public void setEscalera(String escalera) {
	this.escalera = escalera;
}
public String getPortal() {
	return portal;
}
public void setPortal(String portal) {
	this.portal = portal;
}
public String getBloque() {
	return bloque;
}
public void setBloque(String bloque) {
	this.bloque = bloque;
}
public String getPiso() {
	return piso;
}
public void setPiso(String piso) {
	this.piso = piso;
}
public String getPuerta() {
	return puerta;
}
public void setPuerta(String puerta) {
	this.puerta = puerta;
}
public int getCp() {
	return cp;
}
public void setCp(int cp) {
	this.cp = cp;
}
public String getCiudad() {
	return ciudad;
}
public void setCiudad(String ciudad) {
	this.ciudad = ciudad;
}
@Override
public String toString() {
	return "Direccion [tipoVia=" + tipoVia + ", nombreVia=" + nombreVia + ", numeros=" + numeros + ", escalera="
			+ escalera + ", portal=" + portal + ", bloque=" + bloque + ", piso=" + piso + ", puerta=" + puerta + ", cp="
			+ cp + ", ciudad=" + ciudad + "]";
}

}
