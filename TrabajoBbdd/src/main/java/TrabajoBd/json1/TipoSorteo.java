package TrabajoBd.json1;

public enum TipoSorteo {
	LOTERIA_NACIONAL("Lotería Nacional"), 
	QUINIELA("Quiniela"), 
	EUROMILLONES("Euromillones"),
	PRIMITIVA("Primitiva"),
	EL_GORDO("El Gordo");

	private String nombre;

	private TipoSorteo(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;

	}
}
