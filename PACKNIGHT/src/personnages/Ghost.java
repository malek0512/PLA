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
	private boolean control = false;
	private boolean prisonner = false; //le fantome est dans la prison

	private CoordonneesFloat pointDeRespawn;
	final static private int tempsPasserEnPrison = 1; 
	
	public Ghost(String nom, int x, int y, Direction d,CoordonneesFloat spawn) {
		super(nom, x, y, d);
		this.seMeurt = false;
		this.pointDeRespawn = new CoordonneesFloat(spawn.x * 32, spawn.y * 32);
		Ghost.liste.add(this);
		
		
	}
	//getter de base
	public boolean getisAlive(){
		return !(seMeurt);
	}
	/**
	 * Met à jour l'état vivant ou mort du fantome*/
	public void setIsAlive(boolean a){
		seMeurt=a;
		
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
		while(i.hasNext()&& this.seMeurt)
		{
			PacKnight g = i.next();
			if(g.hitting() && hitBoxManager.HitBoxManager.personnageHittingPersonnage(this.coord, g.coord))
			{
				this.meurtDansDatroceSouffrance();
				g.meurtDansDatroceSouffrance();
				break;
			}
		}		
		
  
		if(this.seMeurt)
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
	public void respawn()
	{
		this.seMeurt = true;
	}
	
	/**
	 * parametre le pac-man pour qu'il fasse un respawn sans animation
	 * effectuer une fois que l'animation est fini
	 */
	protected void respawnWOA() {
		this.coord = new CoordonneesFloat(this.pointDeRespawn);
	}
	
	public void meurtDansDatroceSouffrance() {
		this.seMeurt = true;
	}

	public boolean parametrable() {
		return !(seMeurt && prisonner);
	}

	public void avancerAnimation() {
		if(seMeurt)
		{
			if(this.timerAnimation < Pacman.tempsPasserMort)
			{
				this.timerAnimation++;
				//faire avancer d'un cran l'animation
			}
			else
			{
				this.timerAnimation=0;
				this.seMeurt=false;
				this.respawnWOA();
			}
		}
		if(prisonner)
		{
			if(this.timerAnimation < Ghost.tempsPasserEnPrison)
			{
				this.timerAnimation++;
			}
			else
			{
				this.timerAnimation = 0;
				this.prisonner = false;
			}
		}
	}

	public boolean hitting() {
		return !(seMeurt);
	}
	
	
}