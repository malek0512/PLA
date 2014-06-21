package equipages;

import game.Joueur;
import game.Menu;
import game.WindowGame;

import java.awt.Window;
import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import personnages.CoordonneesFloat;
import personnages.Direction;
import personnages.Ghost;
import personnages.PacKnight;
import personnages.PacPrincess;
import personnages.Personnage;
import structure_terrain.Terrain;
import controleur.automate.Automate;

public class Equipage_ARENE extends Equipage{
	

	PacKnight PACMAN_1= new PacKnight("J1",17,17,Direction.droite,new CoordonneesFloat(17, 17), true);
	PacKnight PACMAN_2 = new PacKnight("J2",23,16,Direction.droite,new CoordonneesFloat(23, 16), false);
	PacKnight PACMAN_3 = new PacKnight("J3",32,18,Direction.droite,new CoordonneesFloat(6, 18), false);
	PacPrincess PACMAN_4 = new PacPrincess("J4",5,15,Direction.droite,new CoordonneesFloat(17, 17));

	Ghost GHOST_1 = new Ghost("1", 2, 1, Direction.droite,new CoordonneesFloat(2, 1));
	Ghost GHOST_2 = new Ghost("2", 1, 1, Direction.droite,new CoordonneesFloat(20, 1));
	Ghost GHOST_3 = new Ghost("3", 1, 5, Direction.droite,new CoordonneesFloat(26, 1));
	Ghost GHOST_4 = new Ghost("4", 12, 1, Direction.droite,new CoordonneesFloat(18, 1));

	public Equipage_ARENE() {
		super();
		this.joueurCamera =  null;
		this.joueurZQSD = null;
	}
	
	public void init() throws SlickException{
		Joueur PM_1 = new Joueur(this.g.SPRITE_PACMAN_1,this.g, PACMAN_1,"packknight.xml");
		Joueur PM_2 = new Joueur(this.g.SPRITE_PACMAN_2,this.g, PACMAN_2, "packnight.xml");
		Joueur PM_3 = new Joueur(this.g.SPRITE_PACMAN_3,this.g, PACMAN_3, "packnight.xml");
		Joueur PM_4 = new Joueur(this.g.SPRITE_PACMAN_4,this.g, PACMAN_4, "princesse.xml");
		

		Joueur FM_1 = new Joueur(this.g.SPRITE_GHOST_1,this.g, GHOST_1,"fm_suiveur.xml");
		Joueur FM_2 = new Joueur(this.g.SPRITE_GHOST_2,this.g, GHOST_2,"fm_berserk.xml");
		Joueur FM_3 = new Joueur(this.g.SPRITE_GHOST_3,this.g, GHOST_3,"fm_aleatoire.xml");
		Joueur FM_4 = new Joueur(this.g.SPRITE_GHOST_4,this.g, GHOST_4,"fm_lord.xml");

	}
	
	
   public static void main(String[] argv) {
	      try {
	         Menu.container = new AppGameContainer(new Menu(new Equipage_ARENE()));
	         Menu.container.setDisplayMode(WindowGame.resolution_x,WindowGame.resolution_y,false);
	         Menu.container.start();
	      } catch (SlickException e) {
	         e.printStackTrace();
	      }
	   }
}