/**
 * Une case est soit un mur soit du vide
 * d'autre choix sont prenable, et discutable
 * notre convention est donc celle ci car utiliser
 * par tout les dev d'indé
 * et le chef a choisi ca, et le chef decide
 * trust me
 * author : mysterious guy
 */


package structure_terrain;

public class Case {
	
	// 2 == Pac-gomm // 1 == Vide // 0 == Mur
	private int accessible; 
	
	public Case(int a){
		this.accessible = a;
	}
	
	protected void setAcessCase(int a){
		this.accessible = a;
	}
	
	public boolean isAccessable(){
		return (accessible!=0);
	}
	
	public int caseValeur(){
		return accessible;
	}
	
	public String toString(){
		String s;
		
		if (accessible==1)
			s=" ";
		else if (accessible==0)
			s="#";
		else
			s="x";
		return s;
	}
}
