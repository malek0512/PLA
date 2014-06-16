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

		float someXYSource = position.sommeXY();
		for(Iterator<Pacman> i = Pacman.liste.iterator();i.hasNext();)
		{
			Pacman pac = i.next();
			float someXYTester = i.next().getCoord().sommeXY();
			if(someXYSource - rayon <= someXYTester && someXYTester <= someXYSource + rayon)
				res.add(pac);
		}
		return res;
	}
	
	/**
	 * TODO : regler les problème après avoir changer mur() de preference
	 * ATTENTION : les coordonée étant desormais en float, on ne peut plus comparer
	 * les valeurs et mettre un equal
	 * Dans le sens ou un pac-man peut etre a la coordonnée : (2,8 ; 4,4)
	 * Il faudrait plutot tester si il est dans l'intervalle : ([2,3] ; [4,5])
	 * et non pas si les coordonnées sont parfaitement egales
	 * mysterious guy
	 * 
	 * @param position : coordonnees du fantome
	 * @return vrai si un pacman est dans la croix et qu'il n'y a pas de mur entre les deux
	 * @author rama/vivien
	 */
	protected boolean pacmanEstDansCroix(CoordonneesFloat position) {
		CoordonneesFloat test=position;
		int j=1;
		boolean res=false;
		for(Iterator<Pacman> i = Pacman.liste.iterator();i.hasNext();)
		{
			Pacman pac = i.next();
			CoordonneesFloat cord = pac.getCoord();
			if(cord.x == position.x){
				if (cord.y<position.y){
					while(!mur(position,j,Direction.haut) && cord.y >= test.y && cord.y<(test.y-1)){
						j++;
					}
					if(!mur(position,j,Direction.haut)){
						auto.getPersonnage().setDirection(Direction.haut);
						return true;
						
					}
				}
				else {
					while(!mur(position,j,Direction.bas) && cord.y >= test.y && cord.y<(test.y+1)){
						j++;
					
					}
					if(!mur(position,j,Direction.bas)){
						auto.getPersonnage().setDirection(Direction.bas);
						return true;
					}
				}
				
			}
			else if(cord.y == position.y){
					if (cord.x<position.x){
						while(!mur(position,j,Direction.gauche) && cord.x >= test.x && cord.x<test.x-1){
							j++;
						}
						if(!mur(position,j,Direction.gauche)){
							auto.getPersonnage().setDirection(Direction.gauche);
							return true;
						}
					}
				
					else {
						while(!mur(position,j,Direction.droite) && cord.x >= test.x && cord.x<test.x+1){
							test.x++;
						}
						if(!mur(position,j,Direction.droite)){
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
		boolean res=true;
		if (Personnage.getTerrain().caseAcessible(test.x, test.y, i, d))
			res=false;
		return res;
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
		case haut : coord.x=this.auto.getPersonnage().getCoord().x; coord.y=this.auto.getPersonnage().getCoord().y-1;   break;
		case bas : coord.x=this.auto.getPersonnage().getCoord().x; coord.y=this.auto.getPersonnage().getCoord().y+1;    break;
		case gauche : coord.x=this.auto.getPersonnage().getCoord().x-1; coord.y=this.auto.getPersonnage().getCoord().y; break;
		case droite : coord.x=this.auto.getPersonnage().getCoord().x+1; coord.y=this.auto.getPersonnage().getCoord().y; break;
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
		case haut : coord.x=this.auto.getPersonnage().getCoord().x; coord.y=this.auto.getPersonnage().getCoord().y-1;   break;
		case bas : coord.x=this.auto.getPersonnage().getCoord().x; coord.y=this.auto.getPersonnage().getCoord().y+1;    break;
		case gauche : coord.x=this.auto.getPersonnage().getCoord().x-1; coord.y=this.auto.getPersonnage().getCoord().y; break;
		case droite : coord.x=this.auto.getPersonnage().getCoord().x+1; coord.y=this.auto.getPersonnage().getCoord().y; break;
		}
		return coord;
	}
	
}
