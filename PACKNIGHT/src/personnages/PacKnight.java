/**
 * author : Alex
 */
package personnages;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import structure_terrain.Terrain;

public class PacKnight extends Pacman{

	/**
	 * liste des PacKnight sur le terrain
	 */
	static public List<PacKnight> liste = new LinkedList<PacKnight>();
	public static int vie = 10;
	
	//La princesse aurait une action, qui permet de signaler sa detresse, en mettant son referenceur dans cette variable
	public PacPrincess princesseEnDetresse = null;
	//Contient le fantome apres lequel le knight est a la recherche
	public Ghost ghostEnChasse = null;
	
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
		if(vie > 0)
			respawn();
	}

	public void gererCollision() {
		Iterator<Ghost> i = Ghost.liste.iterator();
		while(i.hasNext())
		{
			Ghost g = i.next();
			if(g.hitting() && hitBoxManager.HitBoxManager.personnageHittingPersonnage(this.coord, g.coord))
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
		Terrain.nb_pacgum--;
	}
	
	/**
	 * renvoie vrai si le pac-knight est parametrable
	 */

	public boolean parametrable() {
		return !(this.agonise);
	}


	/**
	 * fait avancer les animations en cours d'un cran
	 */
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

	@Override
	public boolean hitting() {
		return !(agonise);
	}
	
}
