//74016116 MARTIN GOMEZ, JAVIER

import java.util.ArrayList;

public class Vehiculo {
	private Persona ocupante;
	private boolean [] combustible;
	
	
	public Vehiculo(int combtam) {
		if(combtam<2) combtam= 2;
		this.combustible=new boolean[combtam];
		this.ocupante=null;
	}

	
	private int busca(int in,Coordenada fin) {
		int p=-1;
		ArrayList<Huerta> localizadas;
		localizadas = Huerta.getLocalizadas();
		
		for(int i=in;i<localizadas.size() && p==-1;i++) {
			if(localizadas.get(i).getLocalizacion().iguales(fin)) {
				p=i;
			}
		}
		return p;
	}
	
	
	private void barrasFuera(int combtam) {
		int quit=0;
		for(int i=this.combustible.length-1;i>=0 && quit<combtam;i--) {
			if(this.combustible[i]) {
				this.combustible[i] = false;
				quit++;
			}
		}
	}
	
	public boolean traslada(Coordenada def) {
		boolean tras=false;
		int dist;
		int barras=0;
		int p=busca(0,def);
		
		if(this.ocupante.getHuerta() != null && def!=null && this.ocupante!=null) {
			dist=(int) def.distancia(ocupante.getHuerta().getLocalizacion());
			for(int i=0;i< combustible.length && combustible[i]==true;i++) {
				barras++;
			}
			if(dist<=barras) {
				if(p==-1) {
					this.ocupante.getHuerta().setCoordenada(def);
					tras=true;
				}
				else {
					if(Huerta.getLocalizadas().get(p)==this.ocupante.getHuerta()) {
						p=busca(p+1,def);
					}
					if(p!=-1) {
						this.ocupante.setHuerta(Huerta.getLocalizadas().get(p));
						tras=true;
					}
				}
				if(tras) barrasFuera(dist);
			}
		}
		return tras;
	}
	
	
	public int repostaje(ArrayList<Fruto> frutos) {
		int barras=0,barritas=0;
		double suma=0;
		
		for(int i=0;i<frutos.size();i++) suma=suma+frutos.get(i).valorCalorico();
		barras=(int)suma;
		barritas=0;
		for(int i=0;i<combustible.length && barritas<barras;i++) {
			if(combustible[i]==false) {
				combustible[i]=true;
				barritas++;
			}
		}
		return barritas;
	}
	public boolean sube(Persona personisima) {
		boolean subido=false;
		Inmune inmune;
		if(personisima!=null && this.ocupante==null) {
			if(personisima instanceof Inmune) {
				inmune=(Inmune) personisima;
				if(inmune.getVehiculo()==null) {
					subido=true;
					inmune.setVehiculo(this);
					ocupante=personisima;
				}
			}
		}
		return subido;
	}
	public boolean baja() {
		boolean bajado=false;
		Inmune inmune;
		if(this.ocupante!=null) {
			inmune=(Inmune) this.ocupante;
			inmune.setVehiculo(null);
			bajado=true;
			this.ocupante=null;
		}
		return bajado;
	}
	public void setOcupante(Inmune inmune) {
		this.ocupante=inmune;
	}
	public Persona getOcupante() {
		return this.ocupante;
	}
	public boolean [] getCombustible() {
		return this.combustible;
	}
}



