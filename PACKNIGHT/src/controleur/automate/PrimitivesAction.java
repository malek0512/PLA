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
		
		if(perso.caseDisponible(Direction.bas) && perso.getOrientation()!=Direction.haut){
				direct[i]=Direction.bas;
				i++;
		}
		if(perso.caseDisponible(Direction.haut) && perso.getOrientation()!=Direction.bas){
				direct[i]=Direction.haut;
				i++;
		}
		if(perso.caseDisponible(Direction.gauche) && perso.getOrientation()!=Direction.droite){
			direct[i]=Direction.gauche;
			i++;
		}
		if(perso.caseDisponible(Direction.droite) && perso.getOrientation()!=Direction.gauche){
			direct[i]=Direction.droite;
			i++;
		}
		
		alea=rnd.nextInt(i+1);
		perso.setDirection(direct[alea]);
	}

}
