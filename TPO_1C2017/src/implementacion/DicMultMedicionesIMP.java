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
		/*int posicion = dia-1;
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
	//ESTE ES EL ORIGINAL
	*/
		
		NodoMedicionesAnio aux = clavehelp(anio);                        // revisar esta forma!!
		if(aux == null){
			aux = new NodoMedicionesAnio();
			aux.anio = anio;
			aux.siguienteAnio = origen;
			origen = aux;
			NodoMedicionesMes aux2 = aux.medicionMes;
			aux2 = new NodoMedicionesMes();
			aux2.mes = mes;
			aux2.cantidadDiasMes = Dias.getInstancia().cantidadDias(mes, anio);
			aux2.precipitacionPorDia = new int [aux2.cantidadDiasMes];
			for(int i = 0; i<aux2.cantidadDiasMes; i++){
				aux2.precipitacionPorDia[i]=0;
			}
			aux2.siguienteMes = aux.medicionMes;
			aux.medicionMes = aux2;
			aux2.precipitacionPorDia[dia-1] = medicion;
		}
		else{
			NodoMedicionesMes auxm =new NodoMedicionesMes();
				auxm=clavehelp2(aux, mes);
			if(auxm == null){
				NodoMedicionesMes aux2 = new NodoMedicionesMes();
				 aux2 = aux.medicionMes;
				 aux2 = new NodoMedicionesMes();
				//aux2 = new NodoMedicionesMes();
				aux2.mes = mes;
				aux2.cantidadDiasMes = Dias.getInstancia().cantidadDias(mes, anio);
				aux2.precipitacionPorDia = new int [aux2.cantidadDiasMes];
				for(int i = 0; i<aux2.cantidadDiasMes; i++ ){
					aux2.precipitacionPorDia[i] = 0;
					}
				//aux2.siguienteMes = aux.medicionMes;
				//aux.medicionMes = aux2;
				aux2.precipitacionPorDia[dia-1] = medicion;
				aux2=aux2.siguienteMes;
				}
		}
	}

	//Revisar esto. El error puede llegar estar aca.

	private NodoMedicionesAnio clavehelp(int anio){ //clavehelp busca nodo del año
		NodoMedicionesAnio aux = origen;
		while(aux != null && aux.anio != anio){
			aux = aux.siguienteAnio;
			
		}
		/**if(aux==null){
		return aux = new NodoMedicionesAnio();
		}
		else**/
			return aux;
	}
 //
	
	/*private NodoMedicionesMes clavehelp2(int mes){ //clavehelp2 busca el nodo del mes.
		NodoMedicionesMes aux = origen.medicionMes;
		while(aux != null && aux.mes != mes){
			aux = aux.siguienteMes;
		}
		return aux;
		
		//ESTA ES EL ORIGINAL
	}*/
		
	private NodoMedicionesMes clavehelp2(NodoMedicionesAnio aux, int mes){       // Revisar esta forma!!
		NodoMedicionesMes auxm = new NodoMedicionesMes();
		auxm = aux.medicionMes; 
		//rastrear aux
		while(auxm != null && auxm.mes != mes){
			auxm = auxm.siguienteMes;
		}
		return auxm;
		
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
		NodoMedicionesAnio aux = new NodoMedicionesAnio();
		aux = origen;
		aux = clavehelp(anio);
		aux.medicionMes = clavehelp2(aux, mes);
		if(aux.medicionMes != null) {
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
		NodoMedicionesMes aux=clavehelp2(a, mes);
		if(aux != null) {
		for(int i=0; i<aux.cantidadDiasMes; i++){
			d.agregar(i+1, aux.precipitacionPorDia[i]);
		}
		}
		return d;
	}

}

