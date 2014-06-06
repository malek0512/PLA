package personnages;

import automate.Automate;
import structure_terrain.Terrain;

protected Automate automate;
public abstract class Personnage {
	
	protected Terrain terrain;
	protected int x;
	protected int y;
	protected Direction direction;

	protected boolean automatise;
	
	protected boolean est_automate() {
		return automatise;
	}

}