//74016116 MARTIN GOMEZ, JAVIER

import java.util.ArrayList;

public class Valores {
	private static class Relacion{
		private String nombre;
		 private double valor;
		
		public Relacion(String nom, double val) {
			if(val<0) valor=0.5;
			else valor=val;
			if(nom== null || nom.equals("")) nombre="pitaya";
			else nombre=nom;
		}
	}
	
	private static ArrayList<Relacion> clasificacion= new ArrayList<Relacion>();
		
		
	public static boolean add(String cad,double num) {
		
		int pos=-1;
		
		for(int i=0;i<clasificacion.size();i++) {
			if(clasificacion.get(i).nombre.equalsIgnoreCase(cad)) {
				pos=i;
			}
		}
		if(pos==-1) {
			Relacion var= new Relacion(cad,num);
			clasificacion.add(var);
			return true;
		}
		else {
			return false;
		}
	}

	
	public static double consulta(String cadena) {
		double dev=-1.0;
		for(int i=0; i<clasificacion.size();i++) {
			if(cadena.equalsIgnoreCase(clasificacion.get(i).nombre)) dev=clasificacion.get(i).valor;
		}
		return dev;
		
		
	}
	
	public static ArrayList<String> getNombres(){
		
		ArrayList<String> nombres= new ArrayList<String>();
		
			for(int i=0; i<clasificacion.size(); i++) {
				nombres.add(clasificacion.get(i).nombre);
			}
		return nombres;
	}
	
}
