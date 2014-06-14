package controleur.automate;


import java.util.Iterator;
import java.util.List;
import java.util.Random;

import personnages.Direction;
import personnages.Ghost;
import personnages.Pacman;
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
	/** Rend une direction aléatoire parmis celle disponible lors de l'arrivée d'une intersection
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
		alea=rnd.nextInt(i);
		perso.setDirection(direct[alea]);
	}
	
	/** Donne la prochaine direction disponible si la prochaine case est indisponible
	 * @param personnage auquel on veut changer la direction aléatoirement
	 * */
	public void prochaineDirection(Personnage perso){
		
		if(!perso.caseDevantDisponible()){
			for(Direction d : Direction.values()){
				if(perso.caseDisponible(d)){
					perso.setDirection(d);
				}
			}
		}		
	}
	/**
	 * Donne la direction du chemin le plus court vers le pacman
	 * 
	 * */
	public void directionCheminPlusCourt(Personnage perso){
		List<Pacman> res = pacmanEstDansRayon(auto.getPersonnage().getCoord(),((Ghost) auto.getPersonnage()).getVision());
		Pacman min=res.get(0);
		Iterator<Pacman> i= res.iterator();
		Pacman pac;
		while(i.hasNext())
		{
			pac = i.next();
			if(perso.getCoord().distance(pac.getCoord())<perso.getCoord().distance(min.getCoord()))
				min = pac;
		}
		Direction mind=Direction.haut;
		for(Direction d : Direction.values()){
			
			if(positionAdjacente(d).distance(min.getCoord())<positionAdjacente(mind).distance(min.getCoord()))
				mind=d;
		}
	perso.setDirection(mind);
	}
	/**
	 * Reçoit un ordre du Fantôme Lord et avance vers la case désignée tant qu'il ne l'a pas atteinte
	 * */
	public void avancerVersPoint(){
		
		
	}
	
	

}
