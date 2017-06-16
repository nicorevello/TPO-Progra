package implementacion;

import tda.ColaPrioridadInvertidaTDA;

public class ColaPrioridadInvertidaEstatica implements ColaPrioridadInvertidaTDA {
	final int MAX=1000;
	int []valores;
	int []prioridades;
	int cant;
	
	public void inicializar(){
		valor= new int[MAX];
		prioridad = new int[MAX];
		cant=0;
	}
	
	public void acolar(int valor, int prioridad){
	    	int i;
		for(i=0;i<cant;i++);{
			if(prioridades[i]>prioridad)
				break;
		}
		if(i=cant){
			valores[cant]=valor;
			prioridades[cant]=prioridad;
			cant++;
		} else{
			

	}
	
	public void desacolar(){
	   
	}
		
	public int primero(){
	    return valor[0];
	}
	
	public int prioridad(){
	    return prioridad[cant-1];//preguntar
	}
	
	public boolean colaVacia(){
	    return cant==0;
	}
}
