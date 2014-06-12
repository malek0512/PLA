/**
 * author : Alex et Rama
 * Class des pacmans
 * WARNING : Pensez à initialiser TERRAIN
 */
package personnages;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class Pacman extends Personnage {

	/**
	 * liste des pacmans sur le terrain
	 */
	static public List<Pacman> liste = new LinkedList<Pacman>();
	
	
	/**
	 * @param position ou on veut savoir si un personnage si trouve
	 * @return renvoie vrai si un objet Personnage se trouve sur la position indiquer
	 */
	static public boolean personnagePresent(CoordonneesFloat position)
	{
		Iterator<Pacman> i= Pacman.liste.iterator();
		while(i.hasNext())
		{
			if(position.equals(i.next().coordFloat))
				return true;
		}
		return false;
	}
	
	/**
	 * @param position a tester
	 * @return null si pas de personnage, la reference du perso si il n'y a pas de perso renvoie null
	 */
	static public Pacman personnageReference(CoordonneesFloat position)
	{
		Iterator<Pacman> i= Pacman.liste.iterator();
		while(i.hasNext())
		{
			Pacman p = i.next();
			if(position.equals(p.coordFloat))
				return p;
		}
		return null;
	}
	
	
	public Pacman(String nom, float x, float y, Direction d){
		super(nom,x,y,d);
		Pacman.liste.add((Pacman) this);
	}
	
	/**
	 * @return true si le pacman peut revivre
	 * author : alex
	 */
	public abstract boolean canRespawn();
	
	/**
	 * le pacman meurt dans d'atroce soufrance
	 * author : alex
	 */
	public abstract void meurtDansDatroceSouffrance();
	
	
	/**
	 * @return Une ENTREE de l'automate. Voir les constante d'ENTREE et SORTIE dans classe Automate
	 * @author malek
	 */
	/*
	public int configCaseDevant(){
		Coordonnees caseDevant = positionDevant();
		if (caseDevant.x < 0 || caseDevant.x>Personnage.terrain.getLargeur()-1 || caseDevant.y <0 || caseDevant.y>Personnage.terrain.getHauteur()-1){
			return Automate.SORTIE_TERRAIN;
		} else if (terrain.getCase(coord.x, coord.y).isAccessable()){
				return Automate.CASE_LIBRE;
			} else {
				return Automate.CASE_OCCUPEE;
		}
	}
	*/
	
	/*
	public void suivant() throws Exception{
		int entreeAutomate = configCaseDevant();
		int sortieAutomate = ((Automate) this.c).effectuerTransition(entreeAutomate);
		
		switch (sortieAutomate) {
		case Automate.AVANCER: avancerBetement(); break;
		case Automate.DROIT: tournerDroite();  break;
		case Automate.GAUCHE: tournerGauche(); break;
		}
		this.getControleur().incrementerTransition();
	}
	*/
	
	/**
	 * Selon l'automate pre defini si, l'etat courant est dans un etat final, c'est que le robot est sorti
	 * @return True si le PM est sortie
	 * @author malek
	 */
	/*
	public boolean estSortie(){
		return ((Automate) c).isEtatFinal();
	}
	
	public Automate getControleur(){
		return (Automate) c;
	}
	*/
}
