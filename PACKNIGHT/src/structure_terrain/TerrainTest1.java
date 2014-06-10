package structure_terrain;

import structure_terrain.Terrain;

public class TerrainTest1 extends Terrain {

	public TerrainTest1(int hauteur, int largeur) {
		super(hauteur, largeur);
		for(int i=0; i<hauteur; i++){
			for(int j=0; j<largeur; j++){
				this.terrain[i][j].setAcessCase(1) ;
			}
		}
	}

}
