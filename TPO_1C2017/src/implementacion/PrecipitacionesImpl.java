package implementacion;

import tda.ColaPrioridadInvertidaTDA;
import tda.ConjuntoStringTDA;
import tda.ConjuntoTDA;
import tda.DiccionarioSimpleTDA;
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
	
	private void CargarString(ABBMedicionesTDA arbol, ConjuntoStringTDA c ){
		if(!arbol.hijoDerecho().arbolMedicionesVacio())
			CargarString(arbol.hijoDerecho(),c);
		if(!arbol.hijoIzquierdo().arbolMedicionesVacio())
			CargarString(arbol.hijoIzquierdo(),c);
		c.agregar(arbol.campo());
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
		/**float promedio;                                 otra posible manera!
		ABBMedicionesTDA aux = new ABBMedicionesImpl();
		aux = arbol;
		if(aux.campo().compareToIgnoreCase(campo) == 0){
			for(int i=12;i>0;i--){
				promedio =promedio + promedioMensual(campo, anio, i);
			}
		}
		else if(aux.campo().compareToIgnoreCase(campo) > 0){
			promedioAnual(aux.hijoDerecho().campo(), anio);
		}
		else if(aux.campo().compareToIgnoreCase(campo) < 0){
			promedioAnual(aux.hijoIzquierdo().campo(), anio);
		}
		return promedio;
	}**/
		float promedio = 0;
		for(int i = 12; i>0; i--){
			promedio = promedio + promedioMensual(campo, anio, i);
		}
		return promedio;
	}
		


	public float promedioMensual(String campo, int anio, int mes) {
		int suma=0;
		int total=0;
		ConjuntoTDA c= new ConjuntoEstatico();
		c.inicializar();
		int []vec= new int[1000];
		ABBMedicionesTDA aux= new ABBMedicionesImpl();
		aux= arbol;
		while(campo.compareToIgnoreCase(aux.campo()) != 0){
			if(campo.compareToIgnoreCase(aux.campo()) < 0)
				aux=aux.hijoIzquierdo();
			else if(campo.compareToIgnoreCase(aux.campo()) > 0)
				aux=aux.hijoDerecho();
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

	
	public ColaPrioridadInvertidaTDA comparativaMensual(String campos, int mes) {
		ColaPrioridadInvertidaTDA c= new ColaPrioridadInvertidaEstatica();
		c.inicializar();
		ABBMedicionesTDA aux= new ABBMedicionesImpl();
		DiccionarioSimpleTDA ds=new DiccionarioSimpleImpl();
		ds.inicializar();
		int []vec= new int[100];
		int suma;
		int cantanios=0;
		ConjuntoTDA anios = new ConjuntoEstatico();
		anios.inicializar();
		ConjuntoTDA meses = new ConjuntoEstatico();
		meses.inicializar();
		while(campos.compareTo(aux.campo()) != 0){
			if(campos.compareToIgnoreCase(aux.campo()) < 0)
				aux=aux.hijoDerecho();
			else if(campos.compareToIgnoreCase(aux.campo()) > 0)
				aux=aux.hijoIzquierdo();
		}
		anios=aux.mediciones().anios();
		for(int i=0;!anios.conjuntoVacio();i++)
		{
			vec[i]=anios.elegir();
			anios.sacar(vec[i]);
			cantanios++;
		}
		int i=0;
		int j=0;
		for(;i<cantanios;i++){
			meses=aux.mediciones().meses(vec[i]);
			if(meses.pertenece(mes)){
				ds=aux.mediciones().mediciones(vec[i], mes);
				suma=0;
				for(;i<Dias.getInstancia().cantidadDias(mes,vec[i]);j++)
					suma=ds.recuperar(j+1)+suma;
				c.acolar(suma ,vec[i]);
			}
		}
		return c;
		}
	}
