package implementacion;

import tda.ConjuntoTDA;
import tda.DMMedicionesTDA;
import tda.DiccionarioSimpleTDA;
import auxiliares.Dias;

public class DicMultMedicionesIMP implements DMMedicionesTDA {

	NodoMedicionesAnio origen;
	
	public void inicializar() {
		origen = null;
	}

	
	public void agregar(int anio, int mes, int dia, int medicion) {
		int posicion = dia-1;
		NodoMedicionesAnio aux = clavehelp(anio);
		if(aux == null){
			aux = new NodoMedicionesAnio();
			aux.anio = anio;
			aux.siguienteAnio = origen;
			origen = aux;
		}
		NodoMedicionesMes aux2 = aux.medicionMes;
		if(aux2 == null){
			aux2.cantidadDiasMes = Dias.getInstancia().cantidadDias(mes, anio);
			aux2.precipitacionPorDia = new int [aux2.cantidadDiasMes];
			for(int i=0; i<aux2.cantidadDiasMes; i++){
				aux2.precipitacionPorDia[i] = 0;
			}
			aux2.mes = mes;
		}
		aux2 = clavehelp2(mes);
		aux2.precipitacionPorDia[posicion] = medicion;
		aux2.siguienteMes = aux.medicionMes;
		aux.medicionMes = aux2;
	}

	private NodoMedicionesAnio clavehelp(int anio){ //clavehelp busca nodo del año
		NodoMedicionesAnio aux = origen;
		while(aux != null && aux.anio != anio){
			aux = aux.siguienteAnio;
		}
		return aux;
	}
	
	private NodoMedicionesMes clavehelp2(int mes){ //clavehelp2 busca el nodo del mes.
		NodoMedicionesMes aux = origen.medicionMes;
		while(aux != null && aux.mes != mes){
			aux = aux.siguienteMes;
		}
		return aux;
	}
	
	public void eliminarAnio(int anio) { //ver
		if(origen!= null ) {
			if (origen.anio == anio) {
			origen = origen.siguienteAnio;
			}
			else {
			NodoMedicionesAnio aux = origen;
			while (aux.siguienteAnio != null && aux.siguienteAnio.anio != anio){
			aux = aux.siguienteAnio;
			}
			if (aux.siguienteAnio!= null ) {
			aux.siguienteAnio= aux.siguienteAnio.siguienteAnio;
			}
		}
	}
}

	
	public void eliminarMes(int mes, int anio) {
		if(origen!=null){
			if(origen.anio == anio){
				Eliminar(origen, mes);
				if(origen.medicionMes == null){
					origen = origen.siguienteAnio;
				}
			}
			else {
				NodoMedicionesAnio aux = origen;
				while(aux.siguienteAnio != null && aux.siguienteAnio.anio != anio){
					aux = aux.siguienteAnio;
				}
				if(aux.siguienteAnio != null){
					Eliminar(aux.siguienteAnio, mes);
					if(aux.siguienteAnio.medicionMes == null){
						aux.siguienteAnio = aux.siguienteAnio.siguienteAnio;
					}
				}
			}
		}
	}

	private void Eliminar(NodoMedicionesAnio nodo, int mes){
		if(nodo.medicionMes != null){
			if(nodo.medicionMes.mes == mes){
				nodo.medicionMes = nodo.medicionMes.siguienteMes;
			}
			else{
				NodoMedicionesMes aux = nodo.medicionMes;
				while(aux.siguienteMes != null && aux.siguienteMes.mes != mes){
					aux = aux.siguienteMes;
				}
				if(aux.siguienteMes != null){
					aux.siguienteMes = aux.siguienteMes.siguienteMes;
				}
			}
		}
	}
	
	public void eliminarDia(int anio, int mes, int dia) {
		NodoMedicionesAnio aux = origen;
		aux = clavehelp(anio);
		aux.medicionMes = clavehelp2(mes);
		aux.medicionMes.precipitacionPorDia[dia-1] = 0;
		int a = 0;
		for(int i=0; i<aux.medicionMes.cantidadDiasMes; i++){
			if(aux.medicionMes.precipitacionPorDia[i] == 0){
				a++;
			}
		}
		if(a == aux.medicionMes.cantidadDiasMes)
			eliminarMes(mes, anio);
	}


	public ConjuntoTDA anios() {//retorno conjunto de años de el campo
		ConjuntoTDA resultado = new ConjuntoEstatico();
		resultado.inicializar();
		NodoMedicionesAnio aux = origen;
		while(aux != null){
			resultado.agregar(aux.anio);
			aux = aux.siguienteAnio;
		}
		return resultado;
	}


	public ConjuntoTDA meses(int anio) {//retorno conjunto de meses que tiene el año pedido
		ConjuntoTDA resultado = new ConjuntoEstatico();
		resultado.inicializar();
		NodoMedicionesAnio aux = clavehelp(anio);
		while(aux != null){
			resultado.agregar(aux.medicionMes.mes);
			aux.medicionMes = aux.medicionMes.siguienteMes;
		}
		return resultado;
	}


	public DiccionarioSimpleTDA mediciones(int anio, int mes) {
		DiccionarioSimpleTDA d = new DiccionarioSimpleImpl();
		d.inicializar();
		NodoMedicionesAnio a = origen;
		a = clavehelp (anio);
		a.medicionMes = clavehelp2(mes);
		for(int i=0; i<a.medicionMes.cantidadDiasMes; i++){
			d.agregar(i+1, a.medicionMes.precipitacionPorDia[i]);
		}
		return d;
	}

}
