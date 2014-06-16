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
	private final int vision = 5;
	private boolean control=false;
	private boolean isAlive;
	private CoordonneesFloat pointDeRespawn;
	
	public Ghost(String nom, int x, int y, Direction d,CoordonneesFloat spawn) {
		super(nom, x, y, d);
		this.isAlive = true;
		this.pointDeRespawn = new CoordonneesFloat(spawn.x * 32, spawn.y * 32);
		Ghost.liste.add(this);
		
		
	}
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
	 * Le central repertorie l'ensemble des information des PM en fuite
	 */
	protected static Map<Pacman, AvisDeRecherche> central;

	

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
	
	
	public boolean getControle(){
		return control;
	}
	
	
	public void setControl(boolean a){
		control=a;
		
	}

	public int getVision() {
		return vision;
	}

	/**
	 * Pourquoi ne pas la mettre dans personnage car elle est commune aux pacman et aux Ghost
	 * */
	public void respawn() {
		this.coord = new CoordonneesFloat(pointDeRespawn);
	}
	
	
	@Override
	public void meurtDansDatroceSouffrance() {
		isAlive = false;
		System.out.println("fantome est mort : " + this.nom);
	}
	
	
}