package personnages;

import structure_terrain.Terrain;

public class Ghost extends Personnage {

	
	public Ghost(Terrain terrain, int i, int j){
		
		this.terrain = terrain;
		this.x = i;
		this.y = j;
		
	}


}