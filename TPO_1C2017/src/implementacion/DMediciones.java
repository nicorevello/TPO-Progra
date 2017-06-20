package implementacion;

import tda.ColaTDA;
import tda.ConjuntoMesesTDA;
import tda.ConjuntoTDA;
import tda.DMedicionesTDA;

public class DMediciones implements DMedicionesTDA {
	
	class nodoClave{
		int anio;
		int mes;
		int dia;
	}
	
	class Elemento{
		nodoClave clave;
		int valor;
	}
	
	Elemento[] elementos;
	int cant;
	
	public void inicializar() {
		cant=0;
		elementos=new Elemento[1000]; //modificar numero?
	}

	public void agregar(int anio, int mes, int dia, float medicion) { 
		int clave= anio+mes+dia;
		int pos= Clave2Indice(clave);
		if(pos==1){
			pos=cant;
			elementos[pos]=new Elemento();
			elementos[pos].clave=anio,mes,dia;
			cant++;
		}
		elementos[]
	}
	
	private int Clave2Indice(int clave){
		int i=cant-1;
		while(i>=0&&elementos[i].clave!=clave){
			i--;
		}
		return i;
	}

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
			c.agregar(elementos[i].clave); //ESTA MAL, COMO CARGO SOLO EL AÑO?
		}
		return c;
	}

	public ConjuntoMesesTDA meses(int anio) { //falta implementar conjunto meses todavia
		
	}

	public ColaTDA mediciones() {
		ColaTDA c=new ColaDinamica();
		c.inicializarCola();
		for(int i=0;i<cant;i++){
			c.acolar(elementos[i].clave); ////ESTA MAL, COMO CARGO SOLO LAS MEDICIONES?
		}
		return c;
	}

}
