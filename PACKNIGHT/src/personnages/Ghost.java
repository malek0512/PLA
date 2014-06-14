package personnages;
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

	@Override
	public void gererCollision() {
		// TODO Auto-generated method stub
		
	}//nom du personnage//nom du personnage  
	
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