package controleur.automate;


import graph.Aetoile;
import graph.Graph;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import personnages.CoordonneesFloat;
import personnages.Direction;
import personnages.Ghost;
import personnages.Ghost.AvisDeRecherche;
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
	public void obeir(){
		Iterator<Pacman> i = Ghost.central.keySet().iterator();
		if (i.hasNext()){
			Pacman max = i.next();
			while (i.hasNext()){
				Pacman next = i.next();
				if (Ghost.central.get(next).timer>Ghost.central.get(max).timer)
					max = next;
			}
		((Ghost) auto.getPersonnage()).donnerDesOrdres(max);
		}
		
	}
	
	/**
	 * Bloc le personnage pendant un certain temps (10 cycles actuellement)*/
	public void stun(){
		
		((Ghost)auto.getPersonnage()).stun();
	}
	
	/**
	 * PACKPRINCESSE : Appelle le plus proche packnight de la princesse
	 * Si le knight est deja au service de la princesse :
	 * 		- on lui renseigne le nouvel fantome a chasser, si pas mort
	 * @author malek
	 * @throws Exception 
	 */
	public void auSecours() throws Exception{
		//recuperation du personnage
		PacPrincess bitch = (PacPrincess) auto.getPersonnage();
		//pour chaque ghost dans le rayons
		int i =0;
		for(Ghost violeur : bitch.violeurs)
		{
			if(violeur.hitting())
			{
				//selection du héro
				PacKnight p = this.whichHero(bitch);
				if (p!=null) 
				{
					System.out.println("trouver");
					p.princesseEnDetresse = bitch; //on parametre le packnight
					p.ghostEnChasse = violeur;
					bitch.violeurs.remove(i); //on retire le violeur, il est en cours de "traitement"
				}
			}
			else
				bitch.violeurs.remove(i);
		i++;
		}
	}

	/**
	 * PACKNIGHT
	 * chasse le fantome poursuivant la princesse
	 * @author malek
	 * @throws Exception Si ghostEnChasse==null ou princesseEnDetresse==null
	 */
	public void protegerPrincesse()
	{
		//cast du perso
		PacKnight knight = ((PacKnight) auto.getPersonnage());
		if(knight.ghostEnChasse != null)
		{
			suivre(knight.ghostEnChasse.getCoord().CasCentre());
		}
	}
	
	
	/**
	 * Donne des ordre au fantomes pour coincé un pacman donné
	 */
	public void suivre(CoordonneesFloat ref)
	{
			Personnage p = this.auto.getPersonnage();
			CoordonneesFloat cord= p .coord;
			CoordonneesFloat src = cord.CasCentre();
			
			if(cord.CasBG().equals(cord.CasHD()))
			{
				Aetoile graph = new Aetoile(src);
				List<CoordonneesFloat> l = graph.algo(ref);
				l.remove(0);
				Direction d = mysteriousFunction(src, l.get(0));
				this.auto.getPersonnage().setDirection(d);
			}
			else
				this.auto.getPersonnage().avancer();
	}

	
	public void suivre(){
		Iterator<Pacman> i = Ghost.central.keySet().iterator();
	if (i.hasNext()){
			Pacman min = i.next();
			while (i.hasNext()){
				Pacman next = i.next();
				if (next.getCoord().CasCentre().distance(auto.getPersonnage().getCoord().CasCentre())
						< next.getCoord().CasCentre().distance(min.getCoord().CasCentre()))
					min = next;
			}
			
			suivre(min.getCoord().CasCentre());
		}
	}
	

	/**
	 * envoie le personnage manger des pac-gomm
	 */
	public void fetch()
	{
		// 0 : pac-gom
		// 1 : distance
		// 2 : personnage
		
		// 3 : avenir pac-gom
		// 4 : avenir distance
		// 5 : avenir personnage
		CoordonneesFloat c = new CoordonneesFloat(auto.getPersonnage().coord); 
		if(c.CasBG().equals(c.CasHD()) && estIntersection(c))
		{	
			int tab[][] = laFonctionQuiFaitTout(c.CasCentre());
			
			int cpt =0;
			int meilleurCandidat = Integer.MIN_VALUE;
			Direction meilleurCandidatDirection = null;
			for(Direction d : Direction.values())
			{
				if(tab[cpt][1] != 0)
				{//sinon la direction est un mur !!
					int candidat = 0;
					for(int k = 0; k <3; k++)
						candidat += ImportanceRacine*tab[cpt][k];
					for(int k = 3; k<6; k++)
						candidat += ImportanceBranche*tab[cpt][k];
					if(meilleurCandidat<candidat)
					{
						meilleurCandidat = candidat;
						meilleurCandidatDirection = d;
					}
				}
			}
			
			this.auto.getPersonnage().setDirection(meilleurCandidatDirection);
			this.auto.getPersonnage().avancer();
		}
		else
		{
			setDirectionAleatoire(this.auto.getPersonnage());
			this.auto.getPersonnage().avancer();
		}
	}
}
