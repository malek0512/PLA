/**
 * author : Alex et Rama
 * Class des pacmans
 * WARNING : Pensez à initialiser TERRAIN
 */
package personnages;

import controleur.automate.Automate;
import structure_terrain.Terrain;

public class Pacman extends Personnage {
	
	public Pacman(int x,int y, Direction d){
		super("PM",0,0,d);
	}
	
}
