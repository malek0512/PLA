package equipages;

import game.Joueur;
import game.Menu;
import game.WindowGame;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import personnages.*;
import structure_terrain.Direction;

public class Demo_Berserk extends Equipage {



	public Demo_Berserk() {
		super();

	}

	/**
	 * opérationel
	 */
	public void init() throws SlickException {
		Ghost.vision = 100;
		PacKnight PACMAN_1= new PacKnight("J1",17,17,Direction.droite, true);
		Ghost GHOST_1 = new Ghost("1");
		this.joueurFleche =  PACMAN_1;
		this.joueurCamera = PACMAN_1;
		Joueur PM_1 = new Joueur(this.g.SPRITE_PACMAN_1,this.g, PACMAN_1);
		Joueur FM_1 = new Joueur(this.g.SPRITE_BERSERK,this.g, GHOST_1,"fm_berserk.xml");
	}

	   public static void main(String[] argv) {
		      try {
		         Menu.container = new AppGameContainer(new Menu(new Demo_Berserk()));
		         Menu.container.setDisplayMode(WindowGame.resolution_x,WindowGame.resolution_y,false);
		         Menu.container.start();
		      } catch (SlickException e) {
		         e.printStackTrace();
		      }
		   }
	   
}