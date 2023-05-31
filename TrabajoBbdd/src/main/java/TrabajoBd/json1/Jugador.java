package TrabajoBd.json1;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Jugador {
	private int id;
	private String correo;
	private String dni;
	private String contraseña;
	private double saldo;

	public Jugador(int id, String correo, String dni, String contraseña, double saldo) {
		super();
		this.id = id;
		this.correo = correo;
		this.dni = dni;
		this.contraseña = contraseña;
		this.saldo = saldo;
	}

	public Jugador() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "Jugador [id=" + id + ", correo=" + correo + ", dni=" + dni + ", contraseña=" + contraseña + ", saldo="
				+ saldo + "]";
	}

}
