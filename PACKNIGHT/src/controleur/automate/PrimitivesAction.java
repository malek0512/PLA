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
		int	alea=rnd.nextInt(4);
		int i=0;
		Direction[] direct=new Direction[4];
		if(perso.caseDisponible(Direction.bas) && Direction.bas!=perso.getOrientation()){
			direct[i]=Direction.bas;
			i++;
		}
		if(perso.caseDisponible(Direction.haut)){}
		if(perso.caseDisponible(Direction.haut)){}
		if(perso.caseDisponible(Direction.haut)){}
		
		while(!perso.caseDisponible(direct[alea]) && direct[alea]==perso.getOrientation()){
			alea=rnd.nextInt(i+1);
		}
		perso.setDirection(direct[alea]);
	}

}
