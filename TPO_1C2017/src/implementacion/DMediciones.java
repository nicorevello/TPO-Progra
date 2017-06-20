package implementacion;

import tda.ColaTDA;
import tda.ConjuntoMesesTDA;
import tda.ConjuntoTDA;
import tda.DMedicionesTDA;

public class DMediciones implements DMedicionesTDA {
	
	class Elemento{
		int clave;
		int valor;
	}
	Elemento[] elementos;
	int cant;
	
	public void inicializar() {
		cant=0;
		elementos=new Elemento[1000]; //modificar numero?
	}

	public void agregar(int anio, int mes, int dia, float medicion) { //cual seria la clave?
		

	}

	@Override
	public void eliminarAnio(int anio) {
	

	}

	@Override
	public void eliminarMes(int dia) {
	

	}

	@Override
	public void eliminarDia(int anio, int mes, int dia) {
		// TODO Auto-generated method stub

	}

	public ConjuntoTDA anios() {
		ConjuntoTDA c=new ConjuntoEstatico();
		c.inicializar();
		for(int i=0;i<cant;i++){
			c.agregar(elementos[i].clave);
		}
		return c;
	}

	@Override
	public ConjuntoMesesTDA meses(int anio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ColaTDA mediciones() {
		// TODO Auto-generated method stub
		return null;
	}

}
