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
	public int perimetreSecurite = 3;
	public List<Ghost> violeurs; //Les fantomes qui ose toucher a la princesse. Pour l'instant un violeur a la fois ^^. Par la suite pk pas une liste :D
	
	public PacPrincess(String name, int x, int y, Direction d, CoordonneesFloat respawn) {
		super(name,x,y,d,respawn);
		PacPrincess.liste.add(this);
		violeurs = new LinkedList<Ghost>();
	}
	
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
	
	static public boolean personnagePresentCas(CoordonneesFloat position)
	{
		Iterator<PacPrincess> i= PacPrincess.liste.iterator();
		while (i.hasNext()) {
			if (i.next().coord.CasCentre().equals(position))
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
			if(g.hitting() && hitBoxManager.HitBoxManager.personnageHittingPersonnage(this.coord, g.coord))
			{
				this.meurtDansDatroceSouffrance();
				break;
			}
		}
	}

	public boolean parametrable() {
		return !(this.agonise);
	}

	public void avancerAnimation() {
		if(agonise)
		{
			if(this.timerAnimation < Pacman.tempsPasserMort)
			{
				this.timerAnimation++;
				//faire avancer d'un cran l'animation
			}
			else
			{
				this.timerAnimation=0;
				this.agonise=false;
				this.respawnWOA();
			}
		}
	}

	public boolean hitting() {
		return !(agonise);
	}
	

}
