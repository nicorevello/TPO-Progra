package tda;


public interface DMMedicionesTDA {

	public void inicializar();
	
	public void agregar(int anio, int mes, int dia, int medicion);
	
	public void eliminarAnio(int anio);
	
	public void eliminarMes(int mes, int anio);
	
	/** Es m�todo pone en cero la precipitacion para ese d�a. Si todas las precipitaciones de 
	 *  de ese mes quedan en cero, se debe eliminar el mes tambien. Si todos los meses de ese a�o
	 *  quedaron en cero tambien se elimina el a�o.
	 *  */
	public void eliminarDia(int anio, int mes, int dia);
	
	public ConjuntoTDA anios();
	
	public ConjuntoTDA meses(int anio);
	
	/** Este m�todo devuelve todas las precipitaciones del mes. 
	 *  Si alg�n d�a no hay precipitacion devuelve cero 
	 *  */
	public DiccionarioSimpleTDA mediciones(int anio, int mes);
	
	 
}
