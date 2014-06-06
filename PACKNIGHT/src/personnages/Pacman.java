/**
 * author : Alex et Rama
 * Class des pacmans
 * WARNING : Pensez à initialiser TERRAIN
 */
package personnages;

import structure_terrain.Terrain;

public class Pacman extends Personnage {
	
	public Pacman(Terrain terrain, int x,int y)
	{

		this.x = x;
		this.y = y;
	}
	
}
