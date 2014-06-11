package controleur.automate;


import java.util.Random;
import personnages.Direction;
import personnages.Personnage;

/**
 * Classe contenant l'ensemble des primitives d'action 
 * @author malek
 */
public class PrimitivesAction extends Primitives{

	public PrimitivesAction(Automate a) {
		super();
		this.auto = a;
	}
	/**
	 * @param personnage auquel on veut changer la direction aléatoirement
	 * */
	public void setDirectionAleatoire(Personnage perso){
		Random rnd = new Random();
		int i=0;
		int alea;
		Direction[] direct=new Direction[4];
		
		for(Direction d : Direction.values()){
			if(perso.caseDisponible(d) && perso.getOrientation()!=d.opposer()){
				direct[i]=d;
				i++;
			}
		}
		alea=rnd.nextInt(i+1);
		perso.setDirection(direct[alea]);
	}
	
	public void prochaineDirection(Personnage perso){
		
		if(!perso.caseDisponible(perso.getOrientation())){
			for(Direction d : Direction.values()){
				if(perso.caseDisponible(d)){
					perso.setDirection(d);
				}
			}
		}		
	}
	
	public void directionCheminPlusCourt(Personnage perso){
		
		
	}
	

}
