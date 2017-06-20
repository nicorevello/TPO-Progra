package implementacion;

import tda.ColaTDA;
import tda.ConjuntoMesesTDA;
import tda.ConjuntoTDA;
import tda.DMedicionesTDA;

public class DMediciones implements DMedicionesTDA {
	
	class Elemento{
		int clave; //va a ser anio, mes, dia
		int valor; // va a ser la medicion
	}
	
	Elemento[] elementos;
	int cant;
	
	public void inicializar() {
		cant=0;
		elementos=new Elemento[1000]; //modificar numero?
	}

	public void agregar(int anio, int mes, int dia, float medicion) { 
		int clave=anio*10000+mes*100+dia;
		
	}
	

	public void eliminarAnio(int anio) {
		

	}

	public void eliminarMes(int dia) {
	

	}

	public void eliminarDia(int anio, int mes, int dia) {
		// TODO Auto-generated method stub

	}

	public ConjuntoTDA anios() {
		ConjuntoTDA c=new ConjuntoEstatico();
		c.inicializar();
		for(int i=0;i<cant;i++){
			c.agregar(elementos[i].clave); //ESTA MAL, COMO CARGO SOLO EL AÑO?
		}
		return c;
	}

	public ConjuntoMesesTDA meses(int anio) { //falta implementar conjuntoMeses todavia
		
	}

	public ColaTDA mediciones() {
		ColaTDA c=new ColaDinamica();
		c.inicializarCola();
		for(int i=0;i<cant;i++){
			c.acolar(elementos[i].valor); 
		}
		return c;
	}

}
