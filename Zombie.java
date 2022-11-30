//74016116 MARTIN GOMEZ, JAVIER

import java.util.ArrayList;

public class Zombie extends Persona{
	public Zombie(Persona personisima) {
		super(personisima.getNombre());
		setHuerta(personisima.getHuerta());
	}
	
	
	public boolean planta(Planta plantisima, Huerta huertisima) {
		boolean aparcada=false;
		
		if(plantisima!=null && huertisima!=null && huertisima==getHuerta()) {
					if(plantisima instanceof Trifido) aparcada=huertisima.planta(plantisima);
					else aparcada= huertisima.planta(new Trifido(plantisima));
		}
		return aparcada;
	}
	
	
	public Coordenada paseo() {
		if(getHuerta() != null) {
			return getHuerta().getLocalizacion();
		}
		else return null;
	}
	
	
	public ArrayList<Planta> malasHierbas(){
		ArrayList<Planta> arr= new ArrayList<Planta>();
		
		if(getHuerta()!= null) {
			for(int i=0;i<getHuerta().getHuerto().length;i++) {
				for(int j=0;j<getHuerta().getHuerto()[i].length;j++) {
					if(getHuerta().getHuerto()[i][j]!=null && getHuerta().getHuerto()[i][j] instanceof Trifido==false && getHuerta().getHuerto()[i][j].getEstado().equals("adulta")) {
								arr.add(getHuerta().getHuerto()[i][j]);
								getHuerta().getHuerto()[i][j].arranca();
								getHuerta().getHuerto()[i][j] = null;
					}
				}
			}
		}
		return arr;
	}
	
	
	public int abona(int cant, String ignorada) {
		int abon=0;
		
		if(getHuerta() != null) {
			for(int i=0;i<getHuerta().getHuerto().length;i++) {
				for(int j=0;j<getHuerta().getHuerto()[i].length;j++) {
					if(getHuerta().getHuerto()[i][j]!=null && getHuerta().getHuerto()[i][j] instanceof Trifido) {
							getHuerta().getHuerto()[i][j].abona(cant);
							abon++;
					}
				}	
			}
		}
		return abon;
	}
	
	
	public int abona() {
		Trifido trifidus;
		int abon=0;

		if(getHuerta()!= null) {
			for(int i=0;i<getHuerta().getHuerto().length;i++) {
				for(int j=0;j<getHuerta().getHuerto()[0].length;j++) {
					if(getHuerta().getHuerto()[i][j]!=null && getHuerta().getHuerto()[i][j] instanceof Trifido) {
							trifidus=(Trifido)getHuerta().getHuerto()[i][j];
							abon=abon+trifidus.abona();
					}
				}
			}
		}
		return abon;
	}
	
}








