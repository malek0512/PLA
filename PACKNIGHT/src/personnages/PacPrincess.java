/**
 * author alex
 */

package personnages;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PacPrincess extends Pacman{
	
	/**
	 * liste des PacPrincess sur le terrain
	 */
	static public List<PacPrincess> liste = new LinkedList<PacPrincess>();
	private int vie = 2;
	
	/**
	 * @param position ou on veut savoir si un personnage si trouve
	 * @return renvoie vrai si un objet Personnage se trouve sur la position indiquer
	 */
	static public boolean personnagePresent(CoordonneesFloat position)
	{
		Iterator<PacPrincess> i= PacPrincess.liste.iterator();
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
	static public PacPrincess personnageReference(CoordonneesFloat position)
	{
		Iterator<PacPrincess> i= PacPrincess.liste.iterator();
		while(i.hasNext())
		{
			PacPrincess p = i.next();
			if(position.equals(p.coord))
				return p;
		}
		return null;
	}
	
	
	public PacPrincess(String name, int x, int y, Direction d, CoordonneesFloat respawn) {
		super(name,x,y,d,respawn);
		PacPrincess.liste.add(this);
	}

	public boolean canRespawn() {
		return vie !=0;
	}

	public void meurtDansDatroceSouffrance() {
		vie--;
		if(vie != 0)
			respawn();
	}

	public void gererCollision() {
		Iterator<Ghost> i = Ghost.liste.iterator();
		while(i.hasNext())
		{
			Ghost g = i.next();
			if(g.getisAlive() && hitBoxManager.HitBoxManager.personnageHittingPersonnage(this.coord, g.coord))
			{
				this.meurtDansDatroceSouffrance();
				break;
			}
		}
	}
	

}
