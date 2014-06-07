package structure_terrain;

public class Case {
	
	//private Boolean accessible;
	//private Bonus bonus;
	public enum Objet {LIBRE,MUR,PM,PGUM,GHOST,BONUS};
	public Objet objet;
	
	public Case(Objet obj){
		this.objet = obj;
	}
	
	public Case(Boolean a){
		if (a) this.objet = Objet.LIBRE;
		else this.objet = Objet.MUR;
	}
	
	protected void setAcessCase(Boolean a){
		if (a) this.objet = Objet.LIBRE;
		else this.objet = Objet.MUR;
	}
	
	public boolean isAccessable(){
		return objet != (Objet.MUR);
	}
	
	public Objet getObjet(){
		return objet;
	}
	public String toString(){
		String s;
		
		if (isAccessable()){
			s="-";
		}
		else 
			s="*";
		return s;
	}
}
