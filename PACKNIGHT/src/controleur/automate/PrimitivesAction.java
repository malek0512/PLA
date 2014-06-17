package controleur.automate;


import java.util.Iterator;
import java.util.List;
import java.util.Random;

import personnages.Coordonnees;
import personnages.CoordonneesFloat;
import personnages.Direction;
import personnages.Ghost;
import personnages.PacKnight;
import personnages.PacPrincess;
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
	 * Donne la direction du chemin le plus court vers un pacman dans le rayon de vision
	 * 
	 * */
	public void directionCheminPlusCourt(Personnage perso){
		
		List<Pacman> res = pacmanEstDansRayon(auto.getPersonnage().getCoord(),((Ghost) auto.getPersonnage()).getVision());
		Pacman min=res.get(0);
		Iterator<Pacman> i= res.iterator();
		Pacman pac;
		//Recherche du pacman le plus proche
		while(i.hasNext())
		{
			pac = i.next();
			if(perso.getCoord().CasCentre().distance(pac.getCoord().CasCentre())<perso.getCoord().CasCentre().distance(min.getCoord().CasCentre()))
				min = pac;
		}
		Direction mind=Direction.haut;
		//calcul de la direction permettant de se rapprocher le plus de pacman
		for(Direction d : Direction.values()){
			
			if(perso.caseDisponible(d) && positionAdjacente(d).CasCentre().distance(min.getCoord().CasCentre())<positionAdjacente(mind).CasCentre().distance(min.getCoord().CasCentre()))
				mind=d;
		}
	perso.setDirection(mind);
	}
	
	/**
	 * Reçoit un ordre du Fantôme Lord et avance vers la case désignée tant qu'il ne l'a pas atteinte
	 * */
	public void obeir(List<CoordonneesFloat> d){
		
	}
	
	/**
	 * Bloc le personnage pendant un certain temps (10 cycles actuellement)*/
	public void stun(){
		
		((Ghost)auto.getPersonnage()).stun();
	}
	
	/**
	 * PACKPRINCESSE : Appelle le plus proche packnight de la princesse
	 * @author malek
	 */
	public void auSecours(){
		PacKnight p = this.whichHero((PacPrincess) auto.getPersonnage());
		p.princesseEnDetresse = (PacPrincess) auto.getPersonnage();
		p.ghostEnChasse = ((PacPrincess) auto.getPersonnage()).violeurs.get(0);
	}
	
	/**
	 * PACKNIGHT
	 * @param Perimetre
	 * @author malek
	 */
	public void protegerPrincesse(int Perimetre){
		assert (PacPrincess.liste.size()!=1) : "Il ne doit y a avoir qu'une seule princesse";
		PacPrincess bitch = PacPrincess.liste.get(0);
		Coordonnees prochain;
		//Si la distance, bitch-Packnight^2 > perimetre^2, alors c'est que le packnight doit avancer 
		//juqu'a arriver dans le perimetre de securité de bitch
		if (bitch.getCoord().toCoordonnees().distance_square(auto.getPersonnage().getCoord().toCoordonnees())
				> Math.pow(Perimetre, 2)){
			prochain = prochaineCase(auto.getPersonnage().getCoord().toCoordonnees());
			this.auto.getPersonnage().setCoord(prochain);
		} else {
			//Cherche les Fantome a tuer, indiquer par la princesse
			List<Ghost> agresseurs = bitch.violeurs;
			//S'il y a un un mechant violeur
			if (agresseurs.size() != 0){
				prochain = prochaineCase(auto.getPersonnage().getCoord().toCoordonnees());
				this.auto.getPersonnage().setCoord(prochain);
			}
		}
	}

}
