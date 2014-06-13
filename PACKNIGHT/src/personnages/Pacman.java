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
			if(position.equals(i.next().coordFloat))
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
			if(position.equals(p.coordFloat))
				return p;
		}
		return null;
	}
	
	
	public Pacman(String nom, float x, float y, Direction d){
		super(nom,x,y,d);
		Pacman.liste.add((Pacman) this);
	}
	
	/**
	 * @return true si le pacman peut revivre
	 * author : alex
	 */
	public abstract boolean canRespawn();
	
	/**
	 * le pacman meurt dans d'atroces souffrances
	 * author : alex
	 */
	public abstract void meurtDansDatroceSouffrance();
	
	
}
