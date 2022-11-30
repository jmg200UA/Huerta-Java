//74016116 MARTIN GOMEZ, JAVIER

import java.util.ArrayList;

public class Huerta {
	private Planta[][] huerto;
	private Persona cuidador;
	private Coordenada localizacion;
	private static ArrayList<Huerta> localizadas= new ArrayList<Huerta>();
	
	public Huerta(int i, int j) {
		if(i<=0) i=2;
		if(j<=0) j=2;
		huerto=new Planta[i][j];
		cuidador= null;
		localizacion= null;	
	}
	
	public boolean planta(Planta planteta) {
		String nomplant=null;
		boolean dev=false;
		boolean enc=false;
		int i=0;
		int j=0;
		
		if(planteta!=null) {
			if(planteta.getPlantada()==null && planteta.getEstado().equals("semilla")) {
				for(i=huerto.length-1;i>=0 && dev==false;i--) {
					enc=false;
					nomplant=null;
					for(j=huerto[i].length-1;j>=0 && enc==false;j--) {
						if(huerto[i][j]!=null) {
							nomplant=huerto[i][j].getNombre();
							enc=true;
						}
					}
					if(enc==false || nomplant!=null && nomplant.equals(planteta.getNombre())) {
							for(j=huerto[i].length-1;j>=0;j--) {
								if(huerto[i][j]==null) {
									huerto[i][j]=planteta;
									dev=true;
									planteta.setPlantada(this);
									break;
								}
							}
						
					}
				}
			}
		}
		return dev;
		
	}
	
	public ArrayList<Fruto> recolecta(String nomfrutos){
		ArrayList<Fruto> rec= new ArrayList<Fruto>();
		
			for(int i=0;i<huerto.length;i++) {
				for(int j=0;j<huerto[0].length;j++) {
					if(huerto[i][j]!=null) {
						if(huerto[i][j].getFruto().equalsIgnoreCase(nomfrutos)) {
							rec.addAll(huerto[i][j].recolecta());
						}
					}	
				}
			}
		
		return rec;
	}
	
	public int abona(int cant,String cad ) {
		int contador=0;
		if(cant>0) {
			for(int i=0;i<huerto.length;i++) {
				for(int j=0;j<huerto[i].length;j++) {
						if(huerto[i][j]!=null) {
							if(huerto[i][j].getNombre().equalsIgnoreCase(cad)==true) {
								if(huerto[i][j].abona(cant)) contador++;
							}
						}
				}
			}
		}
		return contador;
		
		
	}
	
	public String consulta(int n1,int n2) {
		String devolver= null;
		if(n1>=0 && n1<huerto.length && n2>=0 && n2<huerto[0].length){
			if(huerto[n1][n2]!= null){
				devolver=huerto[n1][n2].getNombre();
			}
		}
				
		return devolver;
	}
	
	public Planta arranca(String nom,int n1,int n2) {
		Planta plantita=null;
		if(nom!=null && n1>=0 && n1<huerto.length && n2>=0 && n2<huerto[0].length){
			if(huerto[n1][n2]!=null){
				if(huerto[n1][n2].getNombre().equalsIgnoreCase(nom)){
					if(cuidador instanceof Inmune==true || huerto[n1][n2] instanceof Trifido==false) {
						huerto[n1][n2].arranca();
						plantita=huerto[n1][n2];
						plantita.setPlantada(null);
						huerto[n1][n2]=null;
					}
				}
			}
		}
		return plantita;
		 
	}
	
	public void localiza(double latitud,double longitud) {
		if(localizacion==null){
			localizacion= new Coordenada(latitud,longitud);
			localizadas.add(this);
		}
	}
	
	public Coordenada getLocalizacion() {
		return localizacion;
	}
	
	public void setCoordenada(Coordenada coord) {
		localizacion=coord;
	}
	
	public Persona getCuidador() {
		return this.cuidador;
	}
	
	public ArrayList<String> getAdultas(){
		ArrayList<String> adultas = new ArrayList<String>();
		
		for(int i=0; i<=huerto.length;i++) {
			for(int j=0; j<=huerto[0].length;j++) {
				if(huerto[i][j]!=null) {
					if(huerto[i][j].getEstado().equals("adulta")) {
						adultas.add(huerto[i][j].getNombre());
					}
				}
			}
		}
		return adultas;
	}
	
	public static ArrayList<Huerta> getLocalizadas(){
		return localizadas;
	}
	
	
	public void setCuidador(Persona cuidador) {
		this.cuidador=cuidador;
	}
	
	public Planta[][] getHuerto(){
		return huerto;
	}

	
	
	
	//Metodos para localizar huertas y cuidadores para la clase trifido

	public static int HMasCercanaConCuidador(Coordenada coord) {
		double dist=0,distt=0; //distancia y mejor distancia
		int posicion=-1;
		
		for(int i=0;i<localizadas.size();i++) {
			if(localizadas.get(i).getCuidador()!=null && localizadas.get(i).getLocalizacion()!=coord) { //comprobamos la huerta y el cuidador
				dist=coord.distancia(localizadas.get(i).getLocalizacion());
				if(posicion==-1) {
					posicion=i;
					distt=dist;
				}
				else { //comprobamos para que no sean iguales
					if(dist<distt) {
						posicion=i;
						distt=dist;
					}
				}
			}
		}
		return posicion;
	}
	// mas cercana dando igual el cuidador o no cuidador.
	public static int HMasCercana(Coordenada coord) {
		double dist=0,distt=0; //distancia y mejor distancia
		int posicion=-1;
		
		for(int i=0;i<localizadas.size();i++) {
			if(localizadas.get(i).getLocalizacion()!=coord) { //comprobamos solamente la huerta
				dist=coord.distancia(localizadas.get(i).getLocalizacion());
				if(posicion==-1) {
					posicion=i;
					distt=dist;
				}
				else { //comprobamos para que no sean iguales
					if(dist<distt) {
						posicion=i;
						distt=dist;
					}
				}
			}
		}
		return posicion;
	}
	
	
	//Devuelve la primera huerta con cuidador
	public static int HPrimiConCuidador() {
		int posicion=-1;
		for(int i=0;i<localizadas.size() && posicion==-1; i++) { //Comprobamos que tenga cuidador y que este en la primera posicion
			if(localizadas.get(i).getCuidador()!=null) posicion=i;
		}
		return posicion;
	}
	
	
	//Devuelve la primera huerta sin cuidador y sino -1
	public static int HPrimiSinCuidador() {
		int posicion=-1;
		for(int i=0;i<localizadas.size() && posicion==-1;i++) { // //Comprobamos que NO tenga cuidador y que este en la primera posicion
			if(localizadas.get(i).getCuidador()==null) posicion=i;
		}
		return posicion;
	}

}

