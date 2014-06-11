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
			if(position.equals(i.next().coordFloat))
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
			if(position.equals(p.coordFloat))
				return p;
		}
		return null;
	}
	
	
	public PacPrincess(String name, float x, float y, Direction d) {
		super(name,x,y,d);
		PacPrincess.liste.add(this);
	}

	public boolean canRespawn() {
		return vie !=0;
	}

	@Override
	public void meurtDansDatroceSouffrance() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void respawn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gererCollision() {
		// TODO Auto-generated method stub
		
	}
	

}
