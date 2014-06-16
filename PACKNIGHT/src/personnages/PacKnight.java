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
	
	public PacKnight(String name, int x, int y, Direction d, CoordonneesFloat respawn) {
		super(name,x,y,d,respawn);
		PacKnight.liste.add(this);
	}
	
	/**
	 * @param position ou on veut savoir si un personnage si trouve
	 * @return renvoie vrai si un objet Personnage se trouve sur la position indiquer
	 */
	static public boolean personnagePresent(CoordonneesFloat position)
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
	static public PacKnight personnageReference(CoordonneesFloat position)
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
	
	public boolean canRespawn() {
		return vie != 0;
	}

	public void meurtDansDatroceSouffrance() {
		vie--;
		System.out.println("Un pac est mort, vie restante : " + PacKnight.vie);
		if(vie > 0)
			respawn();
		else
			System.out.println("fin de game");
		System.out.println("Cordonne après mort du pacman : " + this.coord);
	}

	public void gererCollision() {
		Iterator<Ghost> i = Ghost.liste.iterator();
		while(i.hasNext())
		{
			Ghost g = i.next();
			if(g.getisAlive() && hitBoxManager.HitBoxManager.personnageHittingPersonnage(this.coord, g.coord))
			{
				System.out.println("fantome tester : " + g.nom );
				this.meurtDansDatroceSouffrance();
				g.meurtDansDatroceSouffrance(); //vengeance !!!
				break;
			}
		}
		if(Personnage.terrain.ValueCase(this.coord.CasCentre()) == 2)
		{
			this.mangePacGomm();
		}
	}
	
	private void mangePacGomm()
	{
		Personnage.terrain.SetCase(this.coord.CasCentre(),1);
	}

	
}
