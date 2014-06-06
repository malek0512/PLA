package personnages;
import automate.Automate;
import structure_terrain.Terrain;

public abstract class Personnage {

	protected Terrain terrain;
	protected int x;
	protected int y;
	protected Direction direction;
	protected Automate automate;

}
