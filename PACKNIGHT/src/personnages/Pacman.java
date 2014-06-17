/**
 * author : Alex et Rama
 * Class des pacmans
 * WARNING : Pensez à initialiser TERRAIN
 */
package personnages;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class Pacman extends Personnage {

	protected CoordonneesFloat pointDeRespawn;
	/**
	 * liste des pacmans sur le terrain
	 */
	static public List<Pacman> liste = new LinkedList<Pacman>();
	static final protected int tempsPasserMort = 40;
	
	/**
	 * Construit le pacman en initialisant son point de spawn*/
	public Pacman(String nom, int x, int y, Direction d, CoordonneesFloat spawn){
		super(nom,x,y,d);
		this.pointDeRespawn = new CoordonneesFloat(spawn.x * 32, spawn.y * 32);
		Pacman.liste.add((Pacman) this);
	}
	/**
	 * @param position ou on veut savoir si un personnage si trouve
	 * @return renvoie vrai si un objet Personnage se trouve sur la position indiquer
	 */
	static public boolean personnagePresent(CoordonneesFloat position)
	{
		Iterator<Pacman> i= Pacman.liste.iterator();
		while(i.hasNext())
		{
			if(position.equals(i.next().coord))
				return true;
		}
		return false;
	}
	
	/**
	 * @param position a tester
	 * @return null si pas de personnage, la reference du perso si il n'y a pas de perso renvoie null
	 */
	static public Pacman personnageReference(CoordonneesFloat position)
	{
		Iterator<Pacman> i= Pacman.liste.iterator();
		while(i.hasNext())
		{
			Pacman p = i.next();
			if(position.equals(p.coord))
				return p;
		}
		return null;
	}
	
	/**
	 * @return true si le pacman peut revivre
	 * author : alex
	 */
	public abstract boolean canRespawn();
	
	/**
	 * parametre le pac-man pour qu'il passe en animation de mort
	 */
	public void respawn()
	{
		this.agonise = true;
	}
	
	/**
	 * parametre le pac-man pour qu'il fasse un respawn sans animation
	 * effectuer une fois que l'animation est fini
	 */
	protected void respawnWOA() {
		this.coord = new CoordonneesFloat(this.pointDeRespawn);
	}
}
