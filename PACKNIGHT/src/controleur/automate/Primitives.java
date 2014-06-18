package controleur.automate;

import graph.Aetoile;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import personnages.*;


/**
 * Classe contenant l'ensemble de fonction intermedaire permettant l'elaboration des primitives 
 * de test et d'action 
 * @author malek
 */
public class Primitives {
	Automate auto;
	List<CoordonneesFloat> chemin; //Utilisé par prochaineCase. 
	int nbCout = 0; //Utilisé par prochaineCase.
	/**
	 * TODO : a move dans PacMan
	 * Test si un objet est en contact d'un pacman
	 * author : alex
	 * @param cord : coordonée de l'objet a tester
	 * @return vrai si un pacman ou plus se trouve sur les coordonnée indiquée
	 */
	protected boolean caseEstPM(CoordonneesFloat cord){
		return Pacman.personnagePresent(cord);
	}

	/**
	 * @param position : coordonner du fantome
	 * @param rayon : rayon de vision du Fantome a la position donnée
	 * @return la liste des pacman de le champ de vision
	 */
	protected List<Pacman> pacmanEstDansRayon(CoordonneesFloat position, int rayon) {
		List<Pacman> res = new LinkedList<Pacman>();
		

		for(Iterator<Pacman> i = Pacman.liste.iterator();i.hasNext();)
		{
			Pacman pac = i.next();
			if(position.CasCentre().distance(pac.getCoord().CasCentre())<=rayon){
				res.add(pac);
				if(Ghost.central.containsKey(pac)){
					Ghost.central.get(pac).coord=pac.getCoord().CasCentre();
					Ghost.central.get(pac).timer=24;
				}
				else
					Ghost.central.put(pac,((Ghost)auto.getPersonnage()).new AvisDeRecherche(pac.getCoord().CasCentre()));
			}
			
				
		}
		return res;
	}
	
	/**
	 * @param position : coordonner du fantome
	 * @return vrai si un pacman est dans la croix et qu'il n'y a pas de mur entre les deux
	 * @author rama/vivien
	 */
	protected boolean pacmanEstDansCroix(CoordonneesFloat position) {
		int j=0;
		boolean res=false;
		
		for(Iterator<Pacman> i = Pacman.liste.iterator();i.hasNext();)
		{
			Pacman pac = i.next();
			CoordonneesFloat coordFC=position.CasCentre(); //coordonnée du centre du fantome
			CoordonneesFloat coord = pac.getCoord().CasCentre();//on récupére la case dans lequel est le centre du pacman
			if(coord.x==coordFC.x){
				if (coord.y<coordFC.y){
					while(mur(coordFC,j,Direction.haut) && coord.y != (coordFC.y -j)){
						j++;
					}
					if(mur(coordFC,j,Direction.haut)){
						auto.getPersonnage().setDirection(Direction.haut);
						return true;
						
					}
				}
				else {
					while(mur(coordFC,j,Direction.bas) && coord.y != (coordFC.y + j)){
						j++;
					
					}
					if(mur(coordFC,j,Direction.bas)){
						auto.getPersonnage().setDirection(Direction.bas);
						return true;
					}
				}
				
			}
			else if(coord.y == coordFC.y){
					if (coord.x<coordFC.x){
						while(mur(coordFC,j,Direction.gauche) && coord.x != (coordFC.x - j)){
							j++;
						}
						if(mur(coordFC,j,Direction.gauche)){
							auto.getPersonnage().setDirection(Direction.gauche);
							return true;
						}
					}
				
					else {
						while(mur(coordFC,j,Direction.droite) && coord.x != (coordFC.x + j)){
							j++;
						}
						if(mur(coordFC,j,Direction.droite)){
							auto.getPersonnage().setDirection(Direction.droite);
							return true;
						}
					}
				}	
		}
		return res;
	}
	
	/**
	 * @param temp: Coordonnees de la case à tester si présence d'un mur, A UNE DISTANCE i
	 * @return boolean Vrai si il y a un mur faux sinon
	 * @author vivien
	 * */
	private boolean mur(CoordonneesFloat test, int i, Direction d) {
		return Personnage.getTerrain().caseAcessible(test.x, test.y, i, d);
	}
	
	/**
	 * @return dans les parametres la case devant le Personnage selon sa direction actuelle
	 * @author malek
	 * @param d
	 * @param x
	 * @param y
	 */
	public CoordonneesFloat positionDevant(){
		CoordonneesFloat coord = new CoordonneesFloat(0,0);
		switch (auto.getPersonnage().getOrientation()){
		case haut : coord.x=this.auto.getPersonnage().getCoord().CasCentre().x; coord.y=this.auto.getPersonnage().getCoord().CasCentre().y-1;   break;
		case bas : coord.x=this.auto.getPersonnage().getCoord().CasCentre().x; coord.y=this.auto.getPersonnage().getCoord().CasCentre().y+1;    break;
		case gauche : coord.x=this.auto.getPersonnage().getCoord().CasCentre().x-1; coord.y=this.auto.getPersonnage().getCoord().CasCentre().y; break;
		case droite : coord.x=this.auto.getPersonnage().getCoord().CasCentre().x+1; coord.y=this.auto.getPersonnage().getCoord().CasCentre().y; break;
		}
		return coord;
	}

	/**Pas merci ! :)
	 * @return Vrai si la case est une intersection
	 */
	public boolean estIntersection(CoordonneesFloat coord){
		int n=0;

		for(Direction d : Direction.values())
			if(this.auto.getPersonnage().caseDisponible(d))
				n++;
		
		return n>2;
	}
	
	/**
	 * Renvoie les coordonnées de la case devant le robot selon sa direction
	 * @param d
	 * @return
	 */
	public CoordonneesFloat positionAdjacente(Direction d){
		CoordonneesFloat coord = new CoordonneesFloat(0,0);
		switch (d){
		case haut : coord.x=this.auto.getPersonnage().getCoord().CasCentre().x; coord.y=this.auto.getPersonnage().getCoord().CasCentre().y-1;   break;
		case bas : coord.x=this.auto.getPersonnage().getCoord().CasCentre().x; coord.y=this.auto.getPersonnage().getCoord().CasCentre().y+1;    break;
		case gauche : coord.x=this.auto.getPersonnage().getCoord().CasCentre().x-1; coord.y=this.auto.getPersonnage().getCoord().CasCentre().y; break;
		case droite : coord.x=this.auto.getPersonnage().getCoord().CasCentre().x+1; coord.y=this.auto.getPersonnage().getCoord().CasCentre().y; break;
		}
		return coord;
	}
	
	public void pass(){
		
	}
	
	/**
	 * Fait super attention aux transformations. Parfois tu parles en pixel alors que tu veux parler de case et vice versa
	 * getCoord() te donne le PIXEL en haut à gauche
	 * Attention aux transformations de Coordonnees à CoordonneesFloat il vaut mieux uniformiser plûtot que de faire des transformations invalides lorsqu'on changera le nom
	 * Les méthodes du genre pixelFromCase case existe ou caseFromPixel existent déjà.(Nommées différement ^^)
	 * 
	 * 
	 * TODO A adapter lors de la disponibilité de l'algorithme A etoile
	 * Renvoie les coordonnées de la prochaine case, afin d'atteindre la coordonnée c.
	 * Le chemin est mis a jour tous les 3 couts. A eventuellement modifier afin de prendre en compte la distance
	 * Utilise la variable globale, List<Coordonnees> chemin, et int nbCout
	 * @param c
	 * @author malek
	 */
	protected CoordonneesFloat prochaineCase (CoordonneesFloat c){
		//Si nous somme deja sur la case demandé
		if (auto.getPersonnage().getCoord().equals(c.CasCentre()))
			return c;
		
		//On met a jour le chemin vers c, dans l'un des cas stipulé dans la condition 
		if (chemin == null || chemin.size()==0 || nbCout >3 ){
			Aetoile depart = new Aetoile(c);
			chemin = depart.algo(auto.getPersonnage().getCoord().CasCentre()); //case approximative TODO
			nbCout=0;
		}
		
		//Declenche une erreur si l'assertion est verifiée
		assert (chemin.size()==0) : "Erreur fonction (primitives.java) prochaineCase. La chemin.size()==0. " +
				"N'existe-t-il pas de chemin vers la coordonnées donnée en parametre ?"; 
		
		nbCout++;
		CoordonneesFloat prochain = chemin.get(0);
		chemin.remove(0);
		return prochain;
	}

	/**
	 * @return le Packnight le plus proche de la princesse
	 * @author malek
	 */
	protected PacKnight whichHero(PacPrincess bitch){
		assert (PacKnight.liste.size()!=0) : "Il n'y a aucun Packnight dans PacKnight.liste !";
		PacKnight captainBitch=PacKnight.liste.get(0);
		for(PacKnight knight : PacKnight.liste){
			if(knight.getCoord().toCoordonnees().distance_square(bitch.getCoord().toCoordonnees())
					< captainBitch.getCoord().toCoordonnees().distance_square(bitch.getCoord().toCoordonnees()))
				captainBitch = knight;
		}
		return captainBitch;
	}
	
	/**
	 * PacPrincesse et PacKnight
	 * @param rayon : rayon de vision du personnage de l'automate
	 * @return la liste des fantomes de le champ de vision de la princesse
	 */
	protected List<Ghost> fantomeEstDansRayon(int rayon) {
		List<Ghost> res = new LinkedList<Ghost>();
		CoordonneesFloat position = auto.getPersonnage().getCoord();
		
		float someXYSource = position.CasCentre().sommeXY();
		for(Ghost pac : Ghost.liste){
			float someXYTester = pac.getCoord().CasCentre().sommeXY();
			if(someXYSource - rayon <= someXYTester && someXYTester <= someXYSource + rayon)
				res.add(pac);
		}
		return res;
	}

	/**
	 * fonction misterieuse qui renvoie la direction a prendre pour aller
	 * de la case src vers la case dest
	 * @param src : case source
	 * @param dest : case destination
	 * @return la direction a suivre pour aller a dest
	 * @author Mysterious Guy
	 */
	public Direction mysteriousFunction(CoordonneesFloat src, CoordonneesFloat dest)
	{
		int x = src.x - dest.x;
		int y = src.y - dest.y;
		
		if(x==0)
		{
			if(x==-1)
				return Direction.droite; 
			else
				return Direction.gauche;
		}
		else//y == 0
		{
			if(y==-1)
				return Direction.bas;
			else
				return Direction.haut;
		}
	}
}
