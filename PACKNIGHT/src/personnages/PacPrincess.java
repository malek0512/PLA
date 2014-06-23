/**
 * author alex
 */

package personnages;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import music.MusicManager;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

import structure_terrain.CoordCas;
import structure_terrain.CoordPix;
import structure_terrain.Direction;

public class PacPrincess extends Pacman{
	
	/**
	 * liste des PacPrincess sur le terrain
	 */
	static public List<PacPrincess> liste = new LinkedList<PacPrincess>();
	public static int vie = 2;
	public int perimetreSecurite = 10;
	public List<Ghost> violeurs; //Les fantomes qui ose toucher a la princesse. Pour l'instant un violeur a la fois ^^. Par la suite pk pas une liste :D
	static public CoordCas cordDeFuite = new CoordCas(1, 1);
	public PacKnight protecteur=null;
	static private Music dead;
	static public int perimetreSecurite2 = 10;
	
	public PacPrincess(String name, CoordCas start, Direction d, CoordCas respawn) {
		super(name,start,d,respawn);
		PacPrincess.liste.add(this);
		violeurs = new LinkedList<Ghost>();
	}
	
	/**
	 * @param position ou on veut savoir si un personnage si trouve
	 * @return renvoie vrai si un objet Personnage se trouve sur la position indiquer
	 */
	static public boolean personnagePresent(CoordCas position)
	{
		Iterator<PacPrincess> i= PacPrincess.liste.iterator();
		while(i.hasNext())
		{
			if(position.equals(i.next().coord.CasCentre()))
				return true;
		}
		return false;
	}
	
	static public boolean hittingPerso(CoordPix position)
	{
		Iterator<PacPrincess> i= PacPrincess.liste.iterator();
		while (i.hasNext()) {
			if (i.next().coord.equals(position))
				return true;
		}
		return false;
	}
	
	public boolean canRespawn() {
		return vie !=0;
	}

	public void meurtDansDatroceSouffrance() {
		vie--;
		this.coord = new CoordPix(this.pointDeRespawn);
		if(vie != 0)
		{
			MusicManager.play_Dead_Princess();
		}
	}

	public void gererCollision() {
		Iterator<Ghost> i = Ghost.liste.iterator();
		while(this.hitting() && i.hasNext())
		{
			Ghost g = i.next();
			if(g.hitting()&& hitBoxManager.HitBoxManager.personnageHittingPersonnage(this.coord, g.coord))
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
				dead.stop();
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
