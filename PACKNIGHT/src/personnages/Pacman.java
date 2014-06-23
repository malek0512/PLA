/**
$******** * author : Alex et Rama
 * Class des pacmans
 * WARNING : Pensez à initialiser TERRAIN
 */
package personnages;

import hitBoxManager.HitBoxManager;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import structure_terrain.CoordCas;
import structure_terrain.CoordPix;
import structure_terrain.Direction;
import structure_terrain.CoordPix.position;

public abstract class Pacman extends Personnage {

	protected CoordPix pointDeRespawn;
	/**
	 * liste des pacmans sur le terrain
	 */
	static public List<Pacman> liste = new LinkedList<Pacman>();
	static final protected int tempsPasserMort = 40;
	
	/**
	 * Construit le pacman en initialisant son point de spawn*/
	public Pacman(String nom, CoordCas starter, Direction d, CoordCas spawn){
		super(nom,starter,d);
		this.pointDeRespawn = new CoordPix(spawn,position.hg);
		Pacman.liste.add((Pacman) this);
	}
	
	/**
	 * @param position ou on veut savoir si un personnage si trouve
	 * @return renvoie vrai si un objet Personnage se trouve sur la position indiquer
	 */
	static public boolean personnagePresent(CoordCas position)
	{
		Iterator<Pacman> i= Pacman.liste.iterator();
		while(i.hasNext())
		{
			if(position.equals(i.next().coord.CasCentre()))
				return true;
		}
		return false;
	}
	
	static public boolean hittingPerso(CoordPix position)
	{
		Iterator<Pacman> i= Pacman.liste.iterator();
		while (i.hasNext()) {
			if (HitBoxManager.personnageHittingPersonnage(i.next().coord,position))
				return true;
		}
		return false;
	}
	
	static public int distance(CoordCas c)
	{
		int min = Integer.MAX_VALUE;
		Iterator<Pacman> i = Pacman.liste.iterator();
		while(i.hasNext())
		{
			int aux = i.next().coord.CasCentre().distance(c);
			if( aux < min)
				min = aux;
		}
		return min;
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
		this.coord = new CoordPix(this.pointDeRespawn);
	}
}
