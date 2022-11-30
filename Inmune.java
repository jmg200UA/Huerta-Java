//74016116 MARTIN GOMEZ, JAVIER

import java.util.ArrayList;
import java.util.Collections;

public class Inmune extends Persona{
	private Vehiculo vehiculo;
	
	public Inmune(String nompers) {
		super(nompers);
		vehiculo=null;
	} 
	
	public Vehiculo getVehiculo() {
		return this.vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo=vehiculo;
	}

	
	
	public boolean planta(Planta plantisima, Huerta huertisima) {
		boolean aparcada=false;
		Planta inocua=null;
		
		if(plantisima!=null && huertisima!=null) {
			if(plantisima instanceof Trifido) {
				inocua=new Planta((Trifido)plantisima); 
				aparcada=huertisima.planta(inocua);
			}
			else aparcada=huertisima.planta(plantisima);
		}
		return aparcada;
	}


	
	
	public ArrayList<Planta> malasHierbas(){
		ArrayList<Planta> PlantasArrancadas = new ArrayList<Planta>();

		if(getHuerta()!=null) {
			for(int i=0;i<getHuerta().getHuerto().length;i++) {
				for(int j=0;j<getHuerta().getHuerto()[0].length;j++) {
					if(getHuerta().getHuerto()[i][j] != null) {
						if(getHuerta().getHuerto()[i][j] instanceof Trifido) {
							PlantasArrancadas.add(getHuerta().getHuerto()[i][j]);
							getHuerta().getHuerto()[i][j].arranca();
							getHuerta().getHuerto()[i][j]=null;
						}
					}
				}
			}
		}
		return PlantasArrancadas;
	}
	
	
	public int abona(int cant,String cad) {
		int contador=0;
		
		if(getHuerta()!=null && cad!=null) {
			if(cant>0) {
				for(int i=0;i<getHuerta().getHuerto().length;i++) {
					for(int j=0;j<getHuerta().getHuerto()[i].length;j++) {
							if(getHuerta().getHuerto()[i][j]!=null) {
								if(getHuerta().getHuerto()[i][j].getFruto().equalsIgnoreCase(cad)) {
									if(getHuerta().getHuerto()[i][j].abona(cant)) contador++;
								}
							}
					}
				}
			}
		}
		return contador;
	}
	
	
	public boolean apropia(Vehiculo vehiculo) {
		boolean aprop=false;
		if(vehiculo!=null && vehiculo.getOcupante()==null) {
			if(this.vehiculo==null) {
				vehiculo.setOcupante(this);
				this.vehiculo=vehiculo;
				aprop=true;
			}
		}
		return aprop;
	}
	
	
	public boolean abandona() { 
		boolean ab=false;
		if(vehiculo!=null) {
			ab=true;
			vehiculo.setOcupante(null);
			vehiculo=null;
		}
		return ab;
	}
	
	
	public ArrayList<String> repostaje(){
		ArrayList<String> nomfrutos=new ArrayList<String>();
		ArrayList<Fruto> rec=new ArrayList<Fruto>();
		
		if(getHuerta() != null) {
			for(int i=0;i<getHuerta().getHuerto().length;i++) {
				for(int j=0;j<getHuerta().getHuerto()[i].length;j++) {
					if(getHuerta().getHuerto()[i][j]!=null) rec.addAll(((Planta)getHuerta().getHuerto()[i][j]).recolecta());
				}
			}
			getVehiculo().repostaje(rec);
			for(int x=0;x<rec.size();x++) {
					nomfrutos.add(rec.get(x).getNombre());
			}	
			Collections.sort(nomfrutos);
		}
		return nomfrutos;
	}

	
	public int paseo(Coordenada coord) {
		int err=0;
		boolean desplaza;
		
		if(coord!=null) {
			if(getHuerta()!=null) {
				if(this.vehiculo!=null) {
					desplaza= true;
					for(int i=0;i<Huerta.getLocalizadas().size() && desplaza==true;i++) {
						if(Huerta.getLocalizadas().get(i).getLocalizacion().iguales(coord)) {
							desplaza=this.vehiculo.traslada(coord); // traslada nos mueve a la Huerta
							if(desplaza==true) {
								Huerta.getLocalizadas().get(i).setCuidador(this);
								setHuerta(Huerta.getLocalizadas().get(i));
								err=err+malasHierbas().size();
								Huerta.getLocalizadas().get(i).setCuidador(Huerta.getLocalizadas().get(i).getCuidador());
								if(Huerta.getLocalizadas().get(i).getCuidador() instanceof Zombie) {
									Huerta.getLocalizadas().get(i).getCuidador().setHuerta(null);
									Huerta.getLocalizadas().get(i).setCuidador(null);
								}
							}
						}
					}
				}
			}
			setHuerta(getHuerta());
		}
		return err;
	}
	
}




