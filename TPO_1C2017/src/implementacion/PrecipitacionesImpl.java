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
		int suma=0;
		int total=0;
		DiccionarioSimpleTDA d = new DiccionarioSimpleImpl();
		ConjuntoTDA c= new ConjuntoEstatico();
		int []vec= new int[1000];
		ABBMedicionesTDA aux= new ABBMedicionesImpl();
		aux= arbol;
		while(campo.compareTo(aux.campo()) != 0){
			if(campo.compareToIgnoreCase(aux.campo()) < 0)
				aux=aux.hijoDerecho();
			else if(campo.compareToIgnoreCase(aux.campo()) > 0)
				aux=aux.hijoIzquierdo();
		}
		c=aux.mediciones().mediciones(anio, mes).claves();
		for(int i=0;!c.conjuntoVacio();i++)
		{
			vec[i]=c.elegir();
			c.sacar(vec[i]);
			total++;
		}
		for(int i=0;i<total;i++)
			suma=aux.mediciones().mediciones(anio, mes).recuperar(vec[i]);
		return suma/total;
	}

	@Override
	public ColaPrioridadInvertidaTDA comparativaMensual(String campos, int mes) { //hay que terminar
		ColaPrioridadInvertidaTDA c= new ColaPrioridadInvertidaEstatica();
		ABBMedicionesTDA aux= new ABBMedicionesImpl();
		int [][]a�os= new int[100][2];
		while(campos.compareTo(aux.campo()) != 0){
			if(campos.compareToIgnoreCase(aux.campo()) < 0)
				aux=aux.hijoDerecho();
			else if(campos.compareToIgnoreCase(aux.campo()) > 0)
				aux=aux.hijoIzquierdo();
		}
		
		return null;
	}

}
