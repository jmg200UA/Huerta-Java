//74016116 MARTIN GOMEZ, JAVIER

import java.lang.Math;

public class Coordenada {
private double latitud;
private double longitud;

	public Coordenada(double x, double y) { /*Constructor*/
		if(-90. <= x && x <= 90.)	latitud=x;
		else latitud=0;
		if(-180. <= y && y <= 180.)	longitud=y;
		else  longitud=0;
		
	}
	public boolean iguales(Coordenada coord) {
		return coord != null && latitud==coord.latitud
				&& longitud==coord.longitud;
	}
	public double distancia(Coordenada coord) {
		
		double distancia=-1;
		if(coord==null) return -1;
		else{
			
			distancia=Math.sqrt(Math.pow(latitud-coord.latitud,2)+Math.pow(longitud-coord.longitud,2));
			return distancia;
		}	
	}  
	
	public double getLongitud() {
		return this.longitud;
	}
	
	public double getLatitud() {
		return this.latitud;
	}


}
