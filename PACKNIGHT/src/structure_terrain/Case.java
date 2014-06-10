/**
 * Une case est soit un mur soit du vide
 * d'autre choix sont prenable, et discutable
 * notre convention est donc celle ci car utiliser
 * par tout les dev d'indé
 * et le chef a choisi ca, et le chef decide
 * trust me
 * author : mysterious guy
 */


package src.structure_terrain;

public class Case {
	
	// 1 == Vide // 0 == Mur
	private boolean accessible; 
	
	public Case(boolean a){
		this.accessible = a;
	}
	
	protected void setAcessCase(boolean a){
		this.accessible = a;
	}
	
	public boolean isAccessable(){
		return accessible;
	}
	
	public String toString(){
		String s;
		
		if (isAccessable()){
			s=" ";
		}
		else 
			s="#";
		return s;
	}
}
