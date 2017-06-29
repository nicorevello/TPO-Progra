package implementacion;

import auxiliares.Dias;
import tda.ColaTDA;
import tda.ConjuntoTDA;
import tda.DMedicionesTDA;

public class DMediciones implements DMedicionesTDA {
	
	class Elemento{
		int clave; //va a ser anio, mes, dia
		int valor; // va a ser la medicion
	}
	
	Elemento[] elementos;
	int cant;
	
	public void inicializar() {
		cant=0;
		elementos=new Elemento[1000];
	}

	public void agregar(int anio, int mes, int dia, int medicion) { 
		
		elementos[cant].clave=anio*10000+mes*100+dia;
		elementos[cant].valor=medicion;
		cant++;
	}
	
	public void eliminarAnio(int anio) {
		for(int mes = 12; mes != 0; mes--){
			for(int dia = 31; dia != 0; dia--){
				int pos=buscarPos(anio*10000+mes*100+dia);
				if(pos!=-1){
					elementos[pos]=elementos[cant-1];
					cant--;
					}
				}
			}
		}
	
	private int buscarPos(int clave){
		int i=cant-1;
		while(i>=0 && elementos[i].clave !=clave)
			i--;
		return i;
	}

	public void eliminarMes(int anio, int mes) {
		int cantdias=Dias.getInstancia().cantidadDias(mes,anio);
		for(int i=0;i<cantdias;i++){
			eliminarDia(anio,mes,i+1);
		}

	}

	public void eliminarDia(int anio, int mes, int dia) {
		int i;
		for(i = 0;i<cant &&elementos[i].clave!=anio*10000+mes*100+dia;i++)
			;
		if(elementos[i].clave==anio*10000+mes*100+dia){
			while(i<cant)
			{
				elementos[i]=elementos[i+1];
			}
			cant--;
			
		}

	}

	public ConjuntoTDA anios() {
		ConjuntoTDA c=new ConjuntoEstatico();
		c.inicializar();
		for(int i=0;i<cant;i++){
			c.agregar(elementos[i].clave/10000); 
		}
		return c;
	}

	public ConjuntoTDA meses(int anio) {
		ConjuntoTDA c = new ConjuntoEstatico();
		int valor;
		for(int i=0;i<cant;i++){
			valor= elementos[i].clave%10000;
			valor=valor/100;
			c.agregar(valor);
		}
		return c;
	}

	public ColaTDA mediciones() {
		ColaTDA c=new ColaDinamica();
		c.inicializarCola();
		for(int i=0;i<cant;i++){
			c.acolar(elementos[i].valor); 
		}
		return c;
	}

}
