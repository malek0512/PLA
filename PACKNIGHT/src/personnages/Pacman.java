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

	/**
	 * liste des pacmans sur le terrain
	 */
	static public List<Pacman> liste = new LinkedList<Pacman>();
	
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
	
	private CoordonneesFloat pointDeRespawn;
	
	public Pacman(String nom, int x, int y, Direction d, CoordonneesFloat spawn){
		super(nom,x,y,d);
		this.pointDeRespawn = new CoordonneesFloat(spawn.x * 32, spawn.y * 32);
		Pacman.liste.add((Pacman) this);
	}
	
	/**
	 * @return true si le pacman peut revivre
	 * author : alex
	 */
	public abstract boolean canRespawn();
	
	protected void respawn() {
		this.coord = this.pointDeRespawn;
	}
}
