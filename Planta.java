//74016116 MARTIN GOMEZ, JAVIER

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Planta {
	
	private String estado;
	private String nombre;
	private String fruto;
	private Huerta plantada;
	private Fruto[] frutos;
	
	public Planta(String nomplanta, String nomfrutos, int maxfrutos) {
		estado = "semilla";
		if(nomplanta != null && nomplanta.equals("") == false) nombre = nomplanta;
		else nombre = "vegetal";
		if(nomfrutos != null && nomfrutos.equals("") == false) fruto = nomfrutos;
		else fruto = "pitaya";
		if(maxfrutos<1) maxfrutos=1;
		frutos=new Fruto[maxfrutos];
	}

	
	public boolean abona(int num) {
		int p=-1;
		boolean adulto, dev=false;
		
		if(plantada!=null) {
			if(estado=="semilla") {
				estado="germinado";
				dev= true;
			}
			else if(estado=="germinado") {
				estado="brote";
				dev= true;
			}
			else if(estado=="brote") {
				estado="adulta";
				dev=true;
			}
			else if(estado.equals("adulta")) {
				Fruto frutaso=new Fruto(fruto);
				for(int i=0;i<frutos.length && p==-1;i++) {
					if(frutos[i]==null) p=i;
				}
				if(p!=-1) {
					frutos[p]=frutaso;
					dev= true;
				}
				for(int i=0;i<frutos.length;i++) {
					if(i!=p) {
						if(frutos[i]!=null) {
							adulto=frutos[i].transforma(num);
							if(adulto==true) dev= true;
							if(num<frutos[i].getPeso()) {
								frutos[i]=null;
								dev=true;
							}
						}
					}
				}

			}
		}
		return dev;
		
	}

	public ArrayList<Fruto> recolecta() {
		
		ArrayList<Fruto> rec= new ArrayList<>();
		for(int i=0;i<frutos.length;i++) {
			if(frutos[i]!=null) {
				if(frutos[i].getEstado().equals("comestible")) {
					rec.add(frutos[i]);
					frutos[i]=null;
				}
			}
		}
		Collections.sort(rec, new Comparator<Fruto>() {
			public int compare(Fruto primerfruto, Fruto segundofruto) {
				if(primerfruto.getPeso()>segundofruto.getPeso()) return 1;
				else return -1;
		}		
	});

		return rec;
	}
	
	public void arranca() {
		plantada = null;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public Huerta getPlantada() {
		return plantada;
	}
	
	public void setPlantada(Huerta h) {
		plantada = h;
	}
	
	public Fruto [] getFrutos() {
		return frutos;
	}
	
	public String getFruto() {
		return fruto;
	}
	
	
	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	public Planta (Trifido trifidus) {
		frutos=new Fruto[trifidus.getFrutos().length];
		fruto=trifidus.getFruto();
		estado=trifidus.getEstado();
		nombre=trifidus.getNombre();
		
		for(int i=0;i<frutos.length;i++) {
			if(trifidus.getFrutos()[i]!=null) frutos[i]=new Fruto(trifidus.getFrutos()[i]);
		}
	}
	
	
}
