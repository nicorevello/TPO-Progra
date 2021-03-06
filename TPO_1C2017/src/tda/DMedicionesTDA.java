package tda;

public interface DMedicionesTDA {

	public void inicializar();
	
	public void agregar(int anio, int mes, int dia, int medicion);
	
	public void eliminarAnio(int anio);
	
	public void eliminarMes(int anio,int mes);
	
	public void eliminarDia(int anio, int mes, int dia);
	
	public ConjuntoTDA anios();
	
	public ConjuntoTDA meses(int anio);
	
	public ColaTDA mediciones();
	
	 
}
