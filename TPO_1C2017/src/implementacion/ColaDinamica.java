package implementacion;

import tda.ColaTDA;
import implementacion.NodoCola;

public class ColaDinamica implements ColaTDA {

	private NodoCola primero;
	private NodoCola ultimo;
	
	public void inicializarCola() {
		primero=null;
		ultimo=null;
	}

	public void acolar(int valor) {
		NodoCola nuevo=new NodoCola();
		nuevo.valor=valor;
		nuevo.siguiente=null;
		if (ultimo!=null){
			ultimo.siguiente=nuevo;
		}
		ultimo=nuevo;
		if (primero==null){
			primero=ultimo;
		}
	}

	public void desacolar() {
		primero=primero.siguiente;
		if (primero==null){
			ultimo=null;
		}
	}

	public int primero() {
		return primero.valor;
	}

	public boolean colaVacia() {
		return (ultimo==null);
	}

}
