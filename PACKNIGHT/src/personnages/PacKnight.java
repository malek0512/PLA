/**
 * author : Alex
 */
package personnages;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PacKnight extends Pacman{

	/**
	 * liste des PacKnight sur le terrain
	 */
	static public List<PacKnight> liste = new LinkedList<PacKnight>();
	static int vie = 10;
	
	
	/**
	 * @param position ou on veut savoir si un personnage si trouve
	 * @return renvoie vrai si un objet Personnage se trouve sur la position indiquer
	 */
	static public boolean personnagePresent(Coordonnees position)
	{
		Iterator<PacKnight> i= PacKnight.liste.iterator();
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
	static public PacKnight personnageReference(Coordonnees position)
	{
		Iterator<PacKnight> i=PacKnight.liste.iterator();
		while(i.hasNext())
		{
			PacKnight p = i.next();
			if(position.equals(p.coord))
				return p;
		}
		return null;
	}
	
	
	public PacKnight(String name, int x, int y, Direction d) {
		super(name,x,y,d);
		PacKnight.liste.add(this);
	}

	public boolean canRespawn() {
		return vie != 0;
	}

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
