package personnages;

import structure_terrain.Terrain;

public abstract class Personnage {
	
	static public Terrain terrain;
	protected int x;
	protected int y;
	protected Direction direction;

	protected boolean automatise;
	
	protected boolean est_automate() {
		return automatise;
	}

}