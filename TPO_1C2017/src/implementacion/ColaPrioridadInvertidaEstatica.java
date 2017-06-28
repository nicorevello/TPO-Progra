package implementacion;

import tda.ColaPrioridadInvertidaTDA;

public class ColaPrioridadInvertidaEstatica implements ColaPrioridadInvertidaTDA {
	final int MAX=1000;
	int []valores;
	int []prioridades;
	int cant;
	
	public void inicializar(){
		valores= new int[MAX];
		prioridades = new int[MAX];
		cant=0;
	}
	
	public void acolar(int valor, int prioridad){
		int i;
		for(i=0;i<cant;i++){
			if(prioridades[i]>prioridad)
				break;
		}
		if(i==cant){
			valores[cant]=valor;
			prioridades[cant]=prioridad;
			cant++;
		} 
		else{
			for(int j=cant;j>i;j--){
				valores[j]=valores[j-1];
				prioridades[j]=prioridades[j-1];
			}
			valores[i]=valor;
			prioridades[i]=prioridad;
			cant++;
		}
	}
	
	
	public void desacolar(){
		for (int i=0; i<cant-1;i++){
			valores[i]=valores[i+1];
			prioridades[i]=prioridades[i+1];
		}
		cant--;
	   
	}
		
	public int primero(){
	    return valores[0];
	}
	
	public int prioridad(){
	    return prioridades[0];
	}
	
	public boolean colaVacia(){
	    return cant==0;
	}
}
