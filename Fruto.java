//74016116 MARTIN GOMEZ, JAVIER

public class Fruto {
private boolean estado;
private double peso;
private String nombre;
	
	public Fruto(Fruto fruto) {
		this.peso=fruto.peso;
		this.nombre=fruto.nombre;
		this.estado=fruto.estado;
	}

	public Fruto(String nomFruto) {
		this.peso=0;
		this.estado=false;
		if(Valores.consulta(nomFruto)==-1) {
			this.nombre = "pitaya";
			Valores.add("pitaya", 0);
		}
		else this.nombre=nomFruto;
		}


	public boolean transforma(int entero) {
		
		boolean est= false;
		double aumento=0.0;
		aumento=entero*0.2;
		if(entero>0) {
			peso=peso+aumento;
			if(peso>=0.3) {
				if(estado==false) {
					estado=true;
					est= true;
				}
			}
		}
		return est;
	}
	
	public double valorCalorico() {
		double valorC=0.0;
		valorC=peso*Valores.consulta(nombre);
		return valorC;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getEstado() {
		String estfruto;
		if(estado==true) estfruto="comestible";
		else estfruto="inmaduro";
		return estfruto;
	}
	
	public double getPeso() {
		return peso;
	}
	

	
}
