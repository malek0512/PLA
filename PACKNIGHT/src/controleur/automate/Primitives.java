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
	 * @return vrai si un pacman ou plus se trouve sur les coordonnée indiquer
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
	 * Dans le sens ou un pac-man peut etre a la coordonée : (2,3 ; 4,4)
	 * Il faudrais plutot tester si il est dans l'intervalle : ([2,3] ; [4,5])
	 * et non pas si les coordonné sont parfaitement egales
	 * mysterious guy
	 * 
	 * @param position : coordonner du fantome
	 * @return vrai si un pacman est dans la croix et qu'il n'y a pas de mur entre les deux
	 * @author rama/vivien
	 */
	protected boolean pacmanEstDansCroix(CoordonneesFloat position) {
		CoordonneesFloat test=position;
		boolean res=false;
		for(Iterator<Pacman> i = Pacman.liste.iterator();i.hasNext();)
		{
			Pacman pac = i.next();
			CoordonneesFloat cord = pac.getCoord();
			CoordonneesFloat temp= position;
			if(cord.x == position.x){
				if (cord.y<position.y){
					while(!mur(temp) && cord.y != test.y){
						test.y--;
						temp.y=test.y;
					}
					if(!mur(temp)){
						auto.getPersonnage().setDirection(Direction.haut);
						return true;
						
					}
				}
				else {
					while(!mur(temp) && cord.y != test.y){
					test.y++;
					temp.y=test.y;
					}
					if(!mur(temp)){
						auto.getPersonnage().setDirection(Direction.bas);
						return true;
					}
				}
				
			}
			else if(cord.y == position.y){
					if (cord.x<position.x){
						while(!mur(temp) && cord.x != test.x){
							test.x--;
							temp.x=test.x;
						}
						if(!mur(temp)){
							auto.getPersonnage().setDirection(Direction.gauche);
							return true;
						}
					}
				
					else {
						while(!mur(temp) && cord.x != test.x){
							test.x++;
							temp.x=test.x;
						}
						if(!mur(temp)){
							auto.getPersonnage().setDirection(Direction.droite);
							return true;
						}
					}
				}	
		}
		return res;
	}
	
	/**
	 * TODO : mauvaise utilisation je pense
	 * Remarque : je pense qu'il faudrais utiliser d'autre fonction que celle utiliser
	 * ci dessous, les quels je ne serais dire, mais si j'ai bien compris
	 * cette fonction sert a savoir si un personnage peut avancer
	 * ou non dans une direction, right ? Si oui, go voir la fonction :
	 * "public boolean caseDisponible(Direction direction)" dans Personnage
	 * Cordialement
	 * Mysterious Guy
	 * 
	 * @param coord : Cordonnees de la case à tester si présence d'un mur
	 * @return boolean Vrai si il y a un mur faux sinon
	 * @author vivien
	 * */
	private boolean mur(Coordonnees coord) {
		boolean res=true;
		if (Personnage.getTerrain().getCase(coord.x,coord.y).isAccessable())
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
	public Coordonnees positionDevant(){
		Coordonnees coord = new Coordonnees(0,0);
		switch (auto.getPersonnage().getOrientation()){
		case haut : coord.x=this.auto.getPersonnage().getCoord().x; coord.y=this.auto.getPersonnage().getCoord().y-1;   break;
		case bas : coord.x=this.auto.getPersonnage().getCoord().x; coord.y=this.auto.getPersonnage().getCoord().y+1;    break;
		case gauche : coord.x=this.auto.getPersonnage().getCoord().x-1; coord.y=this.auto.getPersonnage().getCoord().y; break;
		case droite : coord.x=this.auto.getPersonnage().getCoord().x+1; coord.y=this.auto.getPersonnage().getCoord().y; break;
		}
		return coord;
	}
	
	//TODO javadoc
	public boolean estIntersection(Coordonnees coord){
		int n=0;
		Coordonnees tmp=coord;
		if(Personnage.getTerrain().getCase(tmp.x+1,tmp.y).isAccessable()){
			n++;
		}
		if(Personnage.getTerrain().getCase(tmp.x-1,tmp.y).isAccessable()){
			n++;
		}
		if(Personnage.getTerrain().getCase(tmp.x,tmp.y+1).isAccessable()){
			n++;
		}
		if(Personnage.getTerrain().getCase(tmp.x,tmp.y+1).isAccessable()){
			n++;
		}
		return n>2;
		
	}
}
