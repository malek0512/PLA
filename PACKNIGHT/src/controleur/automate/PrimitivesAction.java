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
import structure_terrain.Terrain;

/**
 * Classe contenant l'ensemble des primitives d'action
 * 
 * @author malek
 */
public class PrimitivesAction extends Primitives {

	public PrimitivesAction(Automate a) {
		super();
		this.auto = a;
	}

	/**
	 * Rend une direction aléatoire parmis celle disponible lors de l'arrivée
	 * d'une intersection
	 * 
	 * @param personnage
	 *            auquel on veut changer la direction aléatoirement
	 * */
	public void setDirectionAleatoire(Personnage perso) {
		Random rnd = new Random();
		int i = 0;
		int alea;
		Direction[] direct = new Direction[4];

		for (Direction d : Direction.values()) {
			if (perso.caseDisponible(d)
					&& perso.getOrientation() != d.opposer()) {
				direct[i] = d;
				i++;
			}
		}
		alea = rnd.nextInt(i);
		perso.setDirection(direct[alea]);
	}

	/**
	 * Donne la direction du chemin le plus court vers un pacman dans le rayon
	 * de vision
	 * 
	 * */
	public void directionCheminPlusCourt(Personnage perso) {

		List<Pacman> res = pacmanEstDansRayon(auto.getPersonnage().getCoord(),
				((Ghost) auto.getPersonnage()).getVision());
		Pacman min = res.get(0);
		Iterator<Pacman> i = res.iterator();
		Pacman pac;
		// Recherche du pacman le plus proche
		while (i.hasNext()) {
			pac = i.next();
			if (perso.getCoord().CasCentre()
					.distance(pac.getCoord().CasCentre()) < perso.getCoord()
					.CasCentre().distance(min.getCoord().CasCentre()))
				min = pac;
		}
		Direction mind = Direction.haut;
		// calcul de la direction permettant de se rapprocher le plus de pacman
		for (Direction d : Direction.values()) {

			if (perso.caseDisponible(d)
					&& positionAdjacente(d).CasCentre().distance(
							min.getCoord().CasCentre()) < positionAdjacente(
							mind).CasCentre().distance(
							min.getCoord().CasCentre()))
				mind = d;
		}
		perso.setDirection(mind);
	}

	/**
	 * Reçoit un ordre du Fantôme Lord et avance vers la case désignée tant
	 * qu'il ne l'a pas atteinte
	 * */
	public void obeir() {
		Iterator<Pacman> i = Ghost.central.keySet().iterator();
		if (i.hasNext()) {
			Pacman max = i.next();
			while (i.hasNext()) {
				Pacman next = i.next();
				if (Ghost.central.get(next).timer > Ghost.central.get(max).timer)
					max = next;
			}
			System.out.println("Je donne des ordres aux fantomes");
			((Ghost) auto.getPersonnage()).donnerDesOrdres(max);
			System.out.println("J'ai fini de donner des ordres aux fantomes");
		}

	}

	/**
	 * Bloc le personnage pendant un certain temps (10 cycles actuellement)
	 */
	public void stun() {

		((Ghost) auto.getPersonnage()).stun();
	}

	/**
	 * PACKPRINCESSE : Appelle le plus proche packnight de la princesse Si le
	 * knight est deja au service de la princesse : - on lui renseigne le nouvel
	 * fantome a chasser, si pas mort
	 * 
	 * @author malek
	 * @throws Exception
	 */
	public void auSecours() throws Exception{
		//recuperation du personnage
//		System.out.println("COUCOU auSecours");
		PacPrincess bitch = (PacPrincess) auto.getPersonnage();
		//pour chaque ghost dans le rayons
		int i =0;
		for(Ghost violeur : bitch.violeurs)
		{
			if(violeur.hitting() && !isAlreadyChassed(violeur))
			{
				//selection du héro
				PacKnight p = this.whichHero(bitch);
				if (p!=null) 
				{
//					System.out.println("trouver");
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
	 * @throws Exception
	 *             Si ghostEnChasse==null ou princesseEnDetresse==null
	 */
	public void protegerPrincesse() throws Exception
	{
		//cast du perso
		PacKnight knight = ((PacKnight) auto.getPersonnage());
		
		if(knight.ghostEnChasse != null)
		{
			suivre(knight.ghostEnChasse.getCoord());
		}

	}
	

	/**
	 * Donne des ordre au fantomes pour coincé un pacman donné
	 */
	public void suivre(CoordonneesFloat ref) {
		
		if(auto.sneaky == null)
		{
			
			CoordonneesFloat src = this.auto.getPersonnage().getCoord().CasCentre();
			Aetoile graph = new Aetoile(src);
			List<CoordonneesFloat> l = graph.algo(ref.CasCentre());
			System.out.println(l.size());
				l.remove(0);
			auto.sneaky = mysteriousFunction(src, l.get(0));
			}
		
		else
		{
			
			if(auto.getPersonnage().caseDisponible(auto.sneaky))
			{
				System.out.println("COUCOU Suivre");
				
				auto.getPersonnage().setDirection(auto.sneaky);
				auto.sneaky = null;
			}
			else {
				CoordonneesFloat src = this.auto.getPersonnage().getCoord().CasCentre();
				Aetoile graph = new Aetoile(src);
				List<CoordonneesFloat> l = graph.algo(ref.CasCentre());
				System.out.println(l.size());
				if(l.size()!=0){
					l.remove(0);}
				auto.sneaky = mysteriousFunction(src, l.get(0));
			}
		}
		this.auto.getPersonnage().avancer();
	}

	public void suivre() {
		Iterator<Pacman> i = Ghost.central.keySet().iterator();
		if (i.hasNext()) {
			Pacman min = i.next();
			while (i.hasNext()) {
				Pacman next = i.next();
				if (next.getCoord().CasCentre()
						.distance(auto.getPersonnage().getCoord().CasCentre()) < next
						.getCoord().CasCentre()
						.distance(min.getCoord().CasCentre()))
					min = next;
			}

			suivre(new CoordonneesFloat(min.getCoord()));
		}
	}

	/**
	 * envoie le personnage manger des pac-gomm
	 */
	public void fetch() {
		// 0 : pac-gom
		// 1 : distance
		// 2 : personnage

		// 3 : avenir pac-gom
		// 4 : avenir distance
		// 5 : avenir personnage
		System.out.println("FETCH DEBUT");
		CoordonneesFloat caseDuPerso = new CoordonneesFloat(
				auto.getPersonnage().coord);
		if (caseDuPerso.CasBG().equals(caseDuPerso.CasHD())
				&& estIntersection(caseDuPerso)) {// si le perso est bien sur
													// une case, et donc si il
													// est sur une intersection
													// :
			int tab[][] = laFonctionQuiFaitTout(caseDuPerso.CasCentre());

			int meilleurCandidat = Integer.MIN_VALUE;
			Direction meilleurCandidatDirection = null;
			int cpt = 0;
			for (Direction d : Direction.values()) {// pour chaque direction
				if (tab[cpt][1] != 0) {// sinon la direction est un mur ou il y
										// a aucun pac-gomm
					if (tab[cpt][0] + tab[cpt][3] > 0) {// sinon il n'y a pas de
														// pac-gomm
						int candidat = 0;
						for (int k = 0; k < 3; k++)
							candidat += ImportanceRacine * tab[cpt][k];
						for (int k = 3; k < 6; k++)
							candidat += ImportanceBranche * tab[cpt][k];
						if (meilleurCandidat < candidat) {
							meilleurCandidat = candidat;
							meilleurCandidatDirection = d;
						}
					}
				}
				cpt++;
			}
			if (meilleurCandidatDirection != null) {
				this.auto.getPersonnage().setDirection(
						meilleurCandidatDirection);
				this.auto.getPersonnage().avancer();
			} else {
				// aucun candidat n'a aboutie vers des pac-gomm
				// Actuellement on fait un set alea
				// faudrai faire en sorte qu'il se dirige vers un pac-gomm
				// setDirectionAleatoire(this.auto.getPersonnage());
				// this.auto.getPersonnage().avancer();
				Terrain t = Personnage.getTerrain();
				CoordonneesFloat dest = null;
				for (int i = 0; i < t.largeur; i++) {
					for (int j = 0; j < t.hauteur; j++) {
						if (t.ValueCase(i, j) == 2) {
							dest = new CoordonneesFloat(i, j);
							break;
						}
					}
					if (dest != null)
						break;
				}
				suivre(dest);
			}
		} else {
			setDirectionAleatoire(this.auto.getPersonnage());
			this.auto.getPersonnage().avancer();
		}
		System.out.println("FETCH FIN");
	}
	
}
