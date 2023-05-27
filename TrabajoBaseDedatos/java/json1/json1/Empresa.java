/**
 * 
 */
package agustincrespo.json1;

/**
 * Clase que guarda los datos de una empresa
 * 
 * @author Agustín Crespo
 *
 */
public class Empresa {
	private String nombre;
	private Direccion dirección;
	private int añoCreación;
	private int numeroEmpleados;

	public Empresa() {
		super();
	}

	



	public Empresa(String nombre, Direccion dirección, int añoCreación, int numeroEmpleados) {
		super();
		this.nombre = nombre;
		this.dirección = dirección;
		this.añoCreación = añoCreación;
		this.numeroEmpleados = numeroEmpleados;
	}





	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public Direccion getDirección() {
		return dirección;
	}


	public void setDirección(Direccion dirección) {
		this.dirección = dirección;
	}


	public int getAñoCreación() {
		return añoCreación;
	}

	public void setAñoCreación(int añoCreación) {
		this.añoCreación = añoCreación;
	}

	public int getNumeroEmpleados() {
		return numeroEmpleados;
	}

	public void setNumeroEmpleados(int numeroEmpleados) {
		this.numeroEmpleados = numeroEmpleados;
	}

	@Override
	public String toString() {
		return "Empresa [nombre=" + nombre + ", dirección=" + dirección + ", añoCreación=" + añoCreación
				+ ", numeroEmpleados=" + numeroEmpleados + "]";
	}

}
