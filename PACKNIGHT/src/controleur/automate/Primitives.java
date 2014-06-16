package controleur.automate;

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
	
	/**
	 * RIP : getCase
	 * a était move dans Terrain
	 */

	/**
	 * RIP : estDansLeTerrain
	 * deplacer dans Terrain
	 */
	
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
	protected List<Pacman> pacmanEstDansRayon(CoordonneesFloat position, float rayon) {
		List<Pacman> res = new LinkedList<Pacman>();

		float someXYSource = position.CasCentre().sommeXY();
		for(Iterator<Pacman> i = Pacman.liste.iterator();i.hasNext();)
		{
			Pacman pac = i.next();
			float someXYTester = i.next().getCoord().CasCentre().sommeXY();
			if(someXYSource - rayon <= someXYTester && someXYTester <= someXYSource + rayon)
				res.add(pac);
		}
		return res;
	}
	
	/**
	 * @param position : coordonner du fantome
	 * @return vrai si un pacman est dans la croix et qu'il n'y a pas de mur entre les deux
	 * @author rama/vivien
	 */
	protected boolean pacmanEstDansCroix(CoordonneesFloat position) {
		int j=1;
		boolean res=false;
		
		for(Iterator<Pacman> i = Pacman.liste.iterator();i.hasNext();)
		{
			Pacman pac = i.next();
			CoordonneesFloat coordFC=position.CasCentre(); //coordonnée du centre du fantome
			CoordonneesFloat coord = pac.getCoord().CasCentre();//on récupére la case dans lequel est le centre du pacman
			if(coord.x==coordFC.x){
				if (coord.y<coordFC.y){
					while(!mur(coordFC,j,Direction.haut) && coord.y != (coordFC.y -j)){
						j++;
					}
					if(!mur(coordFC,j,Direction.haut)){
						auto.getPersonnage().setDirection(Direction.haut);
						return true;
						
					}
				}
				else {
					while(!mur(coordFC,j,Direction.bas) && coord.y != (coordFC.y + j)){
						j++;
					
					}
					if(!mur(coordFC,j,Direction.bas)){
						auto.getPersonnage().setDirection(Direction.bas);
						return true;
					}
				}
				
			}
			else if(coord.y == coordFC.y){
					if (coord.x<coordFC.x){
						while(!mur(coordFC,j,Direction.gauche) && coord.x != (coordFC.x - j)){
							j++;
						}
						if(!mur(coordFC,j,Direction.gauche)){
							auto.getPersonnage().setDirection(Direction.gauche);
							return true;
						}
					}
				
					else {
						while(!mur(coordFC,j,Direction.droite) && coord.x != (coordFC.x + j)){
							j++;
						}
						if(!mur(coordFC,j,Direction.droite)){
							auto.getPersonnage().setDirection(Direction.droite);
							return true;
						}
					}
				}	
		}
		return res;
	}
	
	/**
	 * @param temp: Cordonnees de la case à tester si présence d'un mur
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
}
