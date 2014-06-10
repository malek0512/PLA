package controleur.automate;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import personnages.*;
import structure_terrain.Case;

/**
 * Classe contenant l'ensemble de fonction intermedaire permettant l'elaboration des primitives 
 * de test et d'action 
 * @author malek
 */
public class Primitives {
	Automate auto;
	
	/**
	 * @author malek
	 * @param c
	 * @param d
	 * @return
	 */
	protected Coordonnees getCase(Coordonnees c, Direction d){
		Coordonnees coord = new Coordonnees(c);
		switch (d){
		case haut :   coord.y--;  break;
		case bas :    coord.y++;   break;
		case gauche : coord.x--;  break;
		case droite : coord.x++;   break;
		}
		return coord;
	}
	
	/**
	 * @author malek
	 * @param coord
	 * @return vraie si les coordonnée sont hors du terrain, faux sinon
	 * 
	 * rmq : tu peut tout simplement demander au terrain si la cas est accessible, non ?
	 * Je ne comprend pas l'idée de vérifier si la case est dans le terrain, si on veut faire
	 * ce test autant le mettre dans la classe Terrain non ?
	 * Si une classe voulais aussi cette information, c'est pas ici qu'elle viendrais cherher si il existe déja
	 * une fonction pour ca :p
	 */
	protected boolean estDansLeTerrain(Coordonnees coord){
		return (coord.x < 0
		|| coord.x > Personnage.getTerrain().getLargeur() - 1
		|| coord.y < 0
		|| coord.y > Personnage.getTerrain().getHauteur() - 1);
	}


	/**
	 * author : alex
	 * @param c : coordonée du point a tester
	 * @return vrai si un pacman ou plus se trouve sur les coordonnée indiquer
	 * 
	 * remarque : peut etre la supprimer, et faire directement le test ^^
	 */
	protected boolean caseEstPM(Coordonnees c){
		return Pacman.personnagePresent(c);
	}
	
	
	/**
	 * @param position : coordonner du fantome
	 * @param rayon : rayon de vision du Fantome a la position donnée
	 * @return la liste des pacman de le champ de vision
	 */
	protected List<Pacman> pacmanEstDansRayon(Coordonnees position, int rayon) {
		List<Pacman> res = new LinkedList<Pacman>();

		int someXYSource = position.sommmeXY();
		for(Iterator<Pacman> i = Pacman.liste.iterator();i.hasNext();)
		{
			Pacman pac = i.next();
			int someXYTester = i.next().position().sommmeXY();
			if(someXYSource - rayon <= someXYTester && someXYTester <= someXYSource + rayon)
				res.add(pac);
		}
		return res;
	}
	
	/**
	 * @param position : coordonner du fantome
	 * @param rayon : rayon de vision du Fantome a la position donnée
	 * @return la liste des pacman de le champ de vision
	 * @author rama
	 */
	protected boolean pacmanEstDansCroix(Coordonnees position) {
		Coordonnees test=position;
		for(Iterator<Pacman> i = Pacman.liste.iterator();i.hasNext();)
		{
			Pacman pac = i.next();
			Coordonnees cord = pac.position();
			if(cord.x == position.x){
				if (cord.y<position.y){
					while(!mur()||cord.y == test.y){
						
					}
						
				}
				
			}
			if(cord.y == position.y){
				return true;
			}
				
		}
		return false;
	}
	private boolean mur() {
		return false;
	}
	
	private void estIntersection(){
		
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
}
