package implementacion;

import tda.ABBMedicionesTDA;
import tda.DMMedicionesTDA;

public class ABBMedicionesImpl implements ABBMedicionesTDA {

	private NodoCampo raiz;
	
	public void inicializar() {		
		raiz = null;
	}

	public void agregar(String campo, int anio, int mes, int dia, int medicion) {//preguntar float
		if (raiz==null){
			raiz=new NodoCampo();
			raiz.ciudad=campo;
			raiz.mediciones=new DicMultMedicionesIMP();
			raiz.mediciones.inicializar();
			raiz.mediciones.agregar(anio, mes, dia, medicion);
			raiz.hijoI=new ABBMedicionesImpl();
			raiz.hijoD=new ABBMedicionesImpl();
			raiz.hijoI.inicializar();
			raiz.hijoD.inicializar();
		}
		else if(campo.compareToIgnoreCase(raiz.ciudad)>0){ //compareTo compara lexicograficamente
			raiz.hijoD.agregar(campo, anio, mes, dia, medicion);
		}
		else if(campo.compareToIgnoreCase(raiz.ciudad)<0){ 
			raiz.hijoI.agregar(campo, anio, mes, dia, medicion);
		}

	}

	public void eliminar(String campo) {
		if (raiz!=null){
			if (raiz.ciudad.equalsIgnoreCase(campo)&&raiz.hijoI.arbolMedicionesVacio()&&raiz.hijoD.arbolMedicionesVacio()){ 
				raiz=null;
			}
			else if (raiz.ciudad.equalsIgnoreCase(campo)&&!raiz.hijoI.arbolMedicionesVacio()){
				raiz.ciudad=mayor(raiz.hijoI);
				raiz.hijoI.eliminar(raiz.ciudad);
			}
			else if (raiz.ciudad.equalsIgnoreCase(campo)&&!raiz.hijoD.arbolMedicionesVacio()){
				raiz.ciudad=menor(raiz.hijoD);
				raiz.hijoD.eliminar(raiz.ciudad);
			}
			else if (campo.compareToIgnoreCase(raiz.ciudad)<0){
				raiz.hijoI.eliminar(campo);
			}
			else if (campo.compareToIgnoreCase(raiz.ciudad)>0){
				raiz.hijoD.eliminar(campo);
			}
		}

	}
	
	private String menor(ABBMedicionesTDA a) {
		if (a.hijoIzquierdo().arbolMedicionesVacio())
			return a.campo();
		else
			return menor (a.hijoIzquierdo());
	}

	private String mayor(ABBMedicionesTDA a) {
		if (a.hijoDerecho().arbolMedicionesVacio())
			return a.campo();
		else
			return mayor(a.hijoDerecho());
	}

	public void eliminarMedicionDia(String campo, int anio, int mes, int dia) {
		if (campo.equalsIgnoreCase(raiz.ciudad)){
			raiz.mediciones.eliminarDia(anio, mes, dia);			
		}
		else if (campo.compareToIgnoreCase(raiz.ciudad)>0){
			raiz.hijoD.eliminarMedicionDia(campo, anio, mes, dia);
		}
		else if (campo.compareToIgnoreCase(raiz.ciudad)>0){
			raiz.hijoI.eliminarMedicionDia(campo, anio, mes, dia);
		}

	}

	public String campo() {
		return raiz.ciudad;
	}

	public DMMedicionesTDA mediciones() {
		return raiz.mediciones;
	}

	public ABBMedicionesTDA hijoIzquierdo() {
		return raiz.hijoI;
	}

	public ABBMedicionesTDA hijoDerecho() {
		return raiz.hijoD;
	}

	public boolean arbolMedicionesVacio() {
		return (raiz==null);
	}

}
