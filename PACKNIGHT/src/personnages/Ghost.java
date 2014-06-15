package personnages;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Ghost extends Personnage {
	/**
	 * liste des fantomes du jeux
	 * cette liste est utiliser pour gerer les collisions, et pour
	 * que les fantomes puissent communiquer entre eux
	 * author : alex
	 */
	
	
	public static List<Ghost> liste = new LinkedList<Ghost>();
	private final float vision = 5;
	private int compteurAction=4;
	private boolean control;
	private boolean isAlive;
	
	//getter de base
	public boolean getisAlive(){
		return isAlive;
	}
	/**
	 * Met à jour l'état vivant ou mort du fantome*/
	public void setIsAlive(boolean a){
		isAlive=a;
		
	}
	
	/**
	 * Structure qui repertorie l'ensemble des information d'un PM en fuite
	 * */
	public class AvisDeRecherche {
		boolean repere, Mort;
		Coordonnees coord;
		int nbVu = 0;

		public AvisDeRecherche(Coordonnees c) {
			Mort = false;
			repere = true;
			coord = new Coordonnees(c);
		}
	}

	/**
	 * Le central repertorie l'ensemble des information des PM en suite
	 */
	protected static Map<Pacman, AvisDeRecherche> central;

	public Ghost(String nom, int x, int y, Direction d) {
		super(nom, x, y, d);

	}

	public void gererCollision() {
		Iterator<PacKnight> i = PacKnight.liste.iterator();
		while(i.hasNext()&& this.isAlive)
		{
			PacKnight g = i.next();
			if(hitBoxManager.HitBoxManager.personnageHittingPersonnage(this.coord, g.coord))
			{
				this.meurtDansDatroceSouffrance();
				g.meurtDansDatroceSouffrance();
				break;
			}
		}		
		
		if(this.isAlive)
		{
			Iterator<PacPrincess> j = PacPrincess.liste.iterator();
			while(i.hasNext())
			{
				PacPrincess g = j.next();
				if(hitBoxManager.HitBoxManager.personnageHittingPersonnage(this.coord, g.coord))
				{
					g.meurtDansDatroceSouffrance();
					break;
				}
			}
		}
	}
	
	/**
	 * @return Nombre d'action avancer() à effectuer pour avancer d'une case
	 * */
	public int getCompteurAction(){
		return this.compteurAction;
	}
	
	public boolean getControle(){
		return control;
	}
	
	
	public void setControl(boolean a){
		control=a;
		
	}

	public float getVision() {
		return vision;
	}

	@Override
	public void respawn() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void meurtDansDatroceSouffrance() {
		
	}
	
	
}