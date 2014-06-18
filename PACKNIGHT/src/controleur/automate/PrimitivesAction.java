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
	public void obeir(List<CoordonneesFloat> d){
		
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
	 */
	public void auSecours(){
		PacPrincess bitch = (PacPrincess) auto.getPersonnage();
		for(Ghost violeur : bitch.violeurs){
			if (PacKnight.liste.size()>0) {
				PacKnight p = this.whichHero(bitch);
				p.princesseEnDetresse = bitch;
				p.ghostEnChasse = violeur;
			}
			else {
				//TODO FUIRE, il n'y a aucun knight pour la proteger 
			}
		}
	}
	public void auSecours2(){
		PacPrincess bitch = (PacPrincess) auto.getPersonnage();
//			if (PacKnight.liste.size()>0) {
				PacKnight p = this.whichHero(bitch);
					p.princesseEnDetresse = bitch;
					p.ghostEnChasse = new Ghost("",1,1,Direction.droite,new CoordonneesFloat(1,1));
//			}
//			else {
//				//TODO FUIRE, il n'y a aucun knight pour la proteger 
//			}
		}
	
	/**
	 * PACKNIGHT
	 * Dirige le knight dans le perimetre de la princesse, et chasse le fantome poursuivant la princesse
	 * @param Perimetre
	 * @author malek
	 * @throws Exception Si ghostEnChasse==null ou princesseEnDetresse==null
	 */
	public void protegerPrincesse(int Perimetre) throws Exception{
		PacKnight knight = ((PacKnight) auto.getPersonnage());
		//Si le knight est vivant
		if (knight.hitting()){
			PacPrincess bitch = knight.princesseEnDetresse;
			//Si la princesse ne s'est pas identifiée, princesseEnDetresse==null
			if (bitch==null)
				throw new Exception("Erreur ! Je suis un knight, on me demande de proteger princesse, sans renseignement (princesseEnDetresse==null)");
			
			//Si la distance, bitch-Packnight^2 > perimetre^2, alors c'est que le packnight doit avancer 
			//juqu'a arriver dans le perimetre de securité de bitch. Cela permet de se rapprocher de la princesse
			//en priorite. Au lieu de courrir après un fantome, aleatoire, par exemple
			if (bitch.getCoord().distance_square(knight.getCoord())
					> Math.pow(Perimetre, 2))
			{
				//Avance vers la princesse
				suivre(bitch.getCoord());
			} else 	{
				//Une fois dans le perimetre, si la princesse a renseignée son violeur ghostEnChasse!=null
				if (knight.ghostEnChasse == null)
					throw new Exception("Erreur ! Je suis un knight, on me demande de chasser un ghost, sans renseignement (ghostEnChasse==null)");

				suivre(knight.ghostEnChasse.getCoord());
			}
		} else {
			//Sinon on le reinitialise
			knight.princesseEnDetresse = null;
			knight.ghostEnChasse = null;
		}
	}
	
	/**
	 * Donne des ordre au fantomes pour coincé un pacman donné
	 */
	public void suivre(CoordonneesFloat ref)
	{
		CoordonneesFloat src = this.auto.getPersonnage().coord;
		Aetoile graph = new Aetoile(src);
		List<CoordonneesFloat> l = graph.algo(ref);
		l.remove(0);
		this.auto.getPersonnage().setDirection(mysteriousFunction(src, l.get(1)));
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
			
			suivre(min.getCoord());
		}
	}
}
