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
	 * @return vrai si un pacman est dans la croix et qu'il n'y a pas de mur entre les deux
	 * @author rama/vivien
	 */
	protected boolean pacmanEstDansCroix(Coordonnees position) {
		Coordonnees test=position;
		boolean res=false;
		for(Iterator<Pacman> i = Pacman.liste.iterator();i.hasNext();)
		{
			Pacman pac = i.next();
			Coordonnees cord = pac.position();
			Coordonnees temp= position;
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
	 * @param coord Corrdonnees de la case à tester si présence d'un mur
	 * @return boolean Vrai si il y a un mur faux sinon
	 * @author vivien*/
	public boolean mur(Coordonnees coord) {
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
}
