package personnages;
import java.util.LinkedList;
import java.util.List;

public class Ghost extends Personnage {
	
	/**
	 * liste des fantomes du jeux
	 * cette liste est utiliser pour gerer les collisions, et pour
	 * que les fantomes puissent communiquer entre eux
	 */
	static List<Ghost> liste = new LinkedList<Ghost>();
	
	final int vision = 5;
	
	public Ghost(String nom, int x, int y, Direction d){
		super(nom,x,y,d);
		Ghost.liste.add(this);
	}
	
	@Override
	public void gererCollision() {
		// TODO Auto-generated method stub
		
	}
}