package implementacion;

import tda.ColaPrioridadInvertidaTDA;
import tda.ConjuntoStringTDA;
import tda.ConjuntoTDA;
//import tda.DiccionarioSimpleTDA;
import tda.PrecipitacionesTDA;
import auxiliares.Dias;
import tda.ABBMedicionesTDA;

public class PrecipitacionesImpl implements PrecipitacionesTDA {
	
	ABBMedicionesTDA arbol = new ABBMedicionesImpl();
	
	public void inicializar() {
		arbol.inicializar();
	}

	public void agregar(String campo, int anio, int mes, int dia, int precipitacion) {
		arbol.agregar(campo,anio,mes,dia,precipitacion);
	}

	public void eliminar(String campo) {
		arbol.eliminar(campo);
	}

	public void eliminarMedicion(String campo, int anio, int mes, int dia) {
		arbol.eliminarMedicionDia(campo,anio,mes,dia);
	}

	public int medicionDeUnDia(String campo, int anio, int mes, int dia) {
		return arbol.mediciones().mediciones(anio,mes).recuperar(dia); 
	}

	public ConjuntoStringTDA campos() {
		ConjuntoStringTDA c = new ConjuntoStringEstatico();
		c.inicializar();
		if(!arbol.arbolMedicionesVacio())
			CargarString(arbol,c);
		return c;	
	}
	
	private void CargarString(ABBMedicionesTDA arbol, ConjuntoStringTDA c ){
		if(!arbol.arbolMedicionesVacio()) {
			c.agregar(arbol.campo());
		//if(!arbol.hijoDerecho().arbolMedicionesVacio())
			CargarString(arbol.hijoDerecho(),c);
		//if(!arbol.hijoIzquierdo().arbolMedicionesVacio())
			CargarString(arbol.hijoIzquierdo(),c);
		//c.agregar(arbol.campo());
		}
	}

	public ColaPrioridadInvertidaTDA mediciones(String campo, int anio, int mes) {
		ColaPrioridadInvertidaTDA c = new ColaPrioridadInvertidaEstatica();
		c.inicializar();
		ABBMedicionesTDA arbolAux = arbol;
		while(!arbolAux.campo().equalsIgnoreCase(campo)){
			if(arbolAux.campo().compareToIgnoreCase(campo)>0)
			arbolAux = arbolAux.hijoDerecho();
			if(arbolAux.campo().compareToIgnoreCase(campo)<0)
				arbolAux = arbolAux.hijoIzquierdo();
		}
		int cant = Dias.getInstancia().cantidadDias(mes, anio);
		for(int i = 0; i<cant; i++){
			int precipitacion = arbolAux.mediciones().mediciones(anio, mes).recuperar(i);
			c.acolar(precipitacion, i);
		}
		return c;
	}

	public int promedioAnual(String campo, int anio) {
		int promedio = 0;
		for(int i = 12; i>0; i--){
			promedio = promedio + promedioMensual(campo, anio, i);
			}
		return promedio;
	}
	


	public int promedioMensual(String campo, int anio, int mes) {
		int promedio = 0;
		ABBMedicionesTDA aux = buscarCampo(arbol, campo);
		int precipitacion, cantidad = 0;
		if(aux != null){
			ConjuntoTDA c = aux.mediciones().mediciones(anio, mes).claves();
			while(!c.conjuntoVacio()){
				precipitacion = c.elegir();
				promedio = promedio + aux.mediciones().mediciones(anio, mes).recuperar(precipitacion);
				c.sacar(precipitacion);
				cantidad++;
			}
		}
		if(cantidad != 0)
			return promedio/cantidad;
		else
			return promedio;
	}
	
	
	
	private ABBMedicionesTDA buscarCampo(ABBMedicionesTDA arbol, String campo){
		if (!arbol.arbolMedicionesVacio()){
			if(arbol.campo().equalsIgnoreCase(campo))
				return arbol;
			else if(arbol.campo().compareToIgnoreCase(campo)>0){
				return buscarCampo(arbol.hijoIzquierdo(), campo);
			}
			else{
				return buscarCampo(arbol.hijoDerecho(), campo);
			}
		}
		else
			return null;
	}
	
	
	public ColaPrioridadInvertidaTDA comparativaMensual(String campos, int mes) {
		ColaPrioridadInvertidaTDA cola= new ColaPrioridadInvertidaEstatica();
		cola.inicializar();
		ABBMedicionesTDA aux=buscarCampo(arbol, campos);
		if(aux!=null){
			ConjuntoTDA anios=aux.mediciones().anios();
			if(!anios.conjuntoVacio()){
				int a,promedio;
				while(!anios.conjuntoVacio()){
					a=anios.elegir();
					promedio=promedioMensual(campos, a, mes);
					cola.acolar(promedio, a);
					anios.sacar(a);
				}
			}
		}
		return cola;
		}
	}
