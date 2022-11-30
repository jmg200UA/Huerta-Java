//74016116 MARTIN GOMEZ, JAVIER

import java.util.ArrayList;

public class Persona {
	private String nombre;
	private Huerta huerta;
	
	public Persona(String pers) {
		if(pers==null || pers.equals("")) nombre="John Doe";
		else nombre=pers;
		huerta=null;
	}
	
	
	public boolean planta(Planta plant,Huerta huert) {
		boolean dev=false;
		if(huert!=null && plant!=null) {
			dev=huert.planta(plant);
		}
		
		return dev;
	
	}
	
	
	public Coordenada paseo() {
		Coordenada sitio = null;
		ArrayList<Huerta> loc;
		
		if(huerta!=null) sitio=huerta.getLocalizacion();
		else {
			loc=Huerta.getLocalizadas();
			for(int i=0;i<loc.size() && huerta==null;i++) {
				if(loc.get(i).getCuidador()==null) {
					huerta=loc.get(i);
					huerta.setCuidador(this);
					sitio=huerta.getLocalizacion();
				}
			}
		}
		
		return sitio;
	}
	
	
	public ArrayList<Planta> malasHierbas(){
		ArrayList<Planta> malashierbas= new ArrayList<Planta>();
		boolean tfrut=true;
		if(huerta!=null) {
			for(int i=0;i<huerta.getHuerto().length;i++){
				for(int j=0;j<huerta.getHuerto()[0].length;j++) {
					if(huerta.getHuerto()[i][j]!=null) {
						if(huerta.getHuerto()[i][j].getEstado().equals("adulta")) {
							tfrut=false;
							for(int k=0;k<huerta.getHuerto()[i][j].getFrutos().length;k++) {
								if(huerta.getHuerto()[i][j].getFrutos()[k]!=null) tfrut=true;
							}
						}
						if(tfrut==false) malashierbas.add(huerta.arranca(huerta.getHuerto()[i][j].getNombre(),i,j));	
					}	
				}
			}
		}
		return malashierbas;
		
	}
	
	
	public int abona(int ent,String cad) {
		int abon=0;
		if(huerta!=null && cad!=null) {
			abon=huerta.abona(ent, cad);
		}
		return abon;
		
	}
	
	
	public String getNombre() {
		return nombre;
	}
	
	
	public Huerta getHuerta() {
		return huerta;	
	}

	
	public void setHuerta(Huerta huerta) {
		this.huerta=huerta;
	}
	
}
