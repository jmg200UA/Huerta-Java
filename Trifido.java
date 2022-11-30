//74016116 MARTIN GOMEZ, JAVIER

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Trifido extends Planta{
	private int [] posicion; 
	
	public Trifido(String nomplanta, String nomfruto, int maxfrutos) {
		super(nomplanta, nomfruto, maxfrutos);
		this.posicion=null;
	}
	
	public Trifido(Planta plantisima) {
		super(plantisima.getNombre(),plantisima.getFruto(),plantisima.getFrutos().length);
		for(int i=0;i<plantisima.getFrutos().length;i++) {
			if(plantisima.getFrutos()[i]!=null) getFrutos()[i]=new Fruto(plantisima.getFrutos()[i]);
		}
	}
	
	
	private void fueraFrutos() {
		for(int i=0;i<getFrutos().length;i++) getFrutos()[i]=null;
	}

	
	
	public boolean TFrutos() {
		boolean tfrutos=false;
		for(int i=0;i<getFrutos().length;i++) {
			if(getFrutos()[i]!= null) tfrutos=true;
		}
		return tfrutos;
	}
	
	
	
	public boolean abona(int cant) {
		int i,p,gen=0;
		boolean abon = false;
		if(getPlantada() != null) {
			if(getPlantada().getCuidador()!=null && getPlantada().getCuidador() instanceof Inmune==false) {			
				if(getEstado().equals("adulta")) {
					for(i=0;i<getFrutos().length; i++) {
						if(getFrutos()[i] != null) getFrutos()[i].transforma(cant);
					}
				}
				else setEstado("adulta");
				for(i=0;i<getFrutos().length && gen<2;i++) {
					if(getFrutos()[i]==null) {
						getFrutos()[i]=new Fruto(getFruto());
						gen++;
					}
				}
				getPlantada().setCuidador(new Zombie(getPlantada().getCuidador()));
				abon=true;
			}
			else if(TFrutos()) {
					p=Huerta.getLocalizadas().indexOf(getPlantada());
					if(p!=-1) p= Huerta.HMasCercana(getPlantada().getLocalizacion());	
					else 	  p=Huerta.HPrimiConCuidador();
					if(p!=-1) {
						for(i=0; i<Huerta.getLocalizadas().get(p).getHuerto().length && abon==false;i++) {
							for(int j=0; j < Huerta.getLocalizadas().get(p).getHuerto()[i].length && abon==false;j++) {
								if(Huerta.getLocalizadas().get(p).getHuerto()[i][j] == null) {
									getPlantada().getHuerto()[posicion[0]][posicion[1]] = null; //La quito poniendo la posicion a null
									Huerta.getLocalizadas().get(p).getHuerto()[i][j]=this; 
									posicion=new int [2];
									posicion[0]=i;
									posicion[1]=j;
									super.setPlantada(Huerta.getLocalizadas().get(p));
									abon=true;
								}
							}
						}
						abon=false;
					}
					fueraFrutos();
			}
		}
		return abon;
	}

	

	public ArrayList<Fruto> recolecta(){
		ArrayList<Fruto> rec=new ArrayList<Fruto>();
		int p=Huerta.getLocalizadas().indexOf(getPlantada());
		Planta plantisima;
		
		if(TFrutos()) { 
			if(p==-1) rec=super.recolecta();
			else {
				p=Huerta.HMasCercana(getPlantada().getLocalizacion()); 
				if(p!=-1) {
					getPlantada().getHuerto()[posicion[0]][posicion[1]]=null;
					super.setPlantada(Huerta.getLocalizadas().get(p));
					if(Huerta.getLocalizadas().get(p).getHuerto()[0][0]==null) {
						Huerta.getLocalizadas().get(p).getHuerto()[0][0]=this;
						setPlantada(Huerta.getLocalizadas().get(p)); 
					}
					else {
						plantisima=Huerta.getLocalizadas().get(p).getHuerto()[0][0];
						for(int i=0; i <plantisima.getFrutos().length; i++) {
							if(plantisima.getFrutos()[i] != null) rec.add(plantisima.getFrutos()[i]);

						}
						Collections.sort(rec, new Comparator<Fruto>() {
							public int compare(Fruto primerfruto, Fruto segundofruto) {
								if(primerfruto.getPeso()>segundofruto.getPeso()) return -1;
								else return 1;
							}	
						});
						Huerta.getLocalizadas().get(p).getHuerto()[0][0] = this;
						setPlantada(Huerta.getLocalizadas().get(p)); 	
					}
				}
			}
		}
		return rec;
	}

	public void arranca() {
		Persona personisima;
		
		if(getPlantada()!=null) { 
			personisima=getPlantada().getCuidador();
			if(personisima!=null) {
				if(personisima instanceof Inmune) {
					int j=this.posicion[1];
					int i=this.posicion[0];
					getPlantada().getHuerto()[i][j] = null;
					this.posicion=null; 
					super.arranca(); 
				}
			}
		}
	}

	
	public void setPlantada(Huerta huertillo) {
		boolean enc=false;
		int i,j;
		if(huertillo!=null) {
			super.setPlantada(huertillo);
			for(i=0;i<huertillo.getHuerto().length && enc==false;i++) {
				for(j=0;j<huertillo.getHuerto()[i].length && enc==false;j++) {
					if(huertillo.getHuerto()[i][j]==this) {
						this.posicion=new int [2];
						this.posicion[0]=i;
						this.posicion[1]=j;
						enc=true;
					}
				}
			}
		}
	}
	
	
	public boolean otea() {
		boolean aparcada=false;
		
		if(getPlantada()==null) {
			if(Huerta.HPrimiSinCuidador()!=-1) {
				Huerta colocada= Huerta.getLocalizadas().get(Huerta.HPrimiSinCuidador());
				Planta [][] huertisimo=Huerta.getLocalizadas().get(Huerta.HPrimiSinCuidador()).getHuerto();
				for(int i=0;i<huertisimo.length && !aparcada;i++) {
					for(int j=0;j<huertisimo[0].length && !aparcada;j++) {
						if(huertisimo[i][j] == null) {
							super.setPlantada(colocada); 
							huertisimo[i][j]=this;
							posicion=new int [2];
							posicion[1]=j;
							posicion[0]=i;
							aparcada=true;
							setEstado("adulta");
						}
					}
				}
			}
		}
		return aparcada;
	}

	public int abona() {
		int i,j,dev=0,fila,col,numfila,numcol;
		Fruto [] frutos;
		
		if(getPlantada()!= null) {
			fila=this.posicion[0];
			col=this.posicion[1];;
			numfila=getPlantada().getHuerto().length;
			numcol=getPlantada().getHuerto()[0].length;
			for(i=-1;i<=1;i++) {
				for(j=-1;j<=1;j++) {
					if(j+col>=0 && j+col<numcol && i+fila>=0 && i+fila<numfila) {
						if(getPlantada().getHuerto()[i+fila][j+col]!=null) {
							if(getPlantada().getHuerto()[i+fila][j+col] instanceof Trifido==false) {
								dev++;
								getPlantada().getHuerto()[i+fila][j+col].arranca();
								getPlantada().getHuerto()[i+fila][j+col]=null;
								if(getEstado().equals("adulta")==true) {
									frutos = getFrutos();
									for(int k=0;k<frutos.length; k++) {
										if(frutos[k]==null) {
											frutos[k]=new Fruto(getFruto());
											break;
										}
									}
								}
								else setEstado("adulta");
							}
						}
					}
				}
			}
		}
		return dev;
	}

	

	public int [] getPosicion() {
		return this.posicion;
	}
}




