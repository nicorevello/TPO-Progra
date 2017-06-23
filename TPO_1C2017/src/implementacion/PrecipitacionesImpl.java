package implementacion;

import tda.ColaPrioridadInvertidaTDA;
import tda.ConjuntoStringTDA;
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
		CargarString(arbol,c);
		return c;	
	}
	
	private void CargarString(ABBMedicionesTDA arbol, ConjuntoStringTDA c ){ //CAMBIAR
		if(arbol.hijoDerecho().arbolMedicionesVacio() && arbol.hijoIzquierdo().arbolMedicionesVacio())
		c.agregar(arbol.campo());
		else if(!arbol.hijoDerecho().arbolMedicionesVacio())
			CargarString(arbol.hijoDerecho(),c);
		else if(!arbol.hijoIzquierdo().arbolMedicionesVacio())
			CargarString(arbol.hijoIzquierdo(),c);
		
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

	public float promedioAnual(String campo, int anio) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float promedioMensual(String campo, int anio, int mes) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ColaPrioridadInvertidaTDA comparativaMensual(String campos, int mes) {
		// TODO Auto-generated method stub
		return null;
	}

}
