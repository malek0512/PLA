package equipages;

import game.Joueur;
import game.Menu;
import game.WindowGame;
import music.MusicManager;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import personnages.*;
import structure_terrain.Direction;

public class Demo_LordCommandant extends Equipage {



	public Demo_LordCommandant() {
		super();

	}

	/**
	 * non opérationel
	 */
	
	public void init() throws SlickException {
		PacKnight.godMode=true;
		Ghost.powerRange = 100;
		Ghost.tempsPasserEnPrison = 0;
		
		PacKnight PACMAN_1= new PacKnight("J1",17,17,Direction.droite, true);
		Ghost GHOST_1 = new Ghost("1");
		Ghost GHOST_2 = new Ghost("2");
		Ghost GHOST_3 = new Ghost("3");
		Ghost GHOST_4 = new Ghost("4");
		
		this.joueurFleche =  PACMAN_1;
		this.joueurCamera = PACMAN_1;
		Joueur PM_1 = new Joueur(this.g.SPRITE_PACMAN_1,this.g, PACMAN_1);
		Joueur FM_1 = new Joueur(this.g.SPRITE_LORD,this.g, GHOST_1,"fm_immobile.xml");
		Joueur FM_2 = new Joueur(this.g.SPRITE_ALEATOIRE,this.g, GHOST_2,"fm_immobile.xml");
		Joueur FM_3 = new Joueur(this.g.SPRITE_ALEATOIRE,this.g, GHOST_3,"fm_immobile.xml");
		Joueur FM_4 = new Joueur(this.g.SPRITE_ALEATOIRE,this.g, GHOST_4,"fm_immobile.xml");
		
		
	}

	   public static void main(String[] argv) {
		      try {
		         Menu.container = new AppGameContainer(new Menu(new Demo_LordCommandant()));
		         Menu.container.setDisplayMode(WindowGame.resolution_x,WindowGame.resolution_y,false);
		         Menu.container.start();
		      } catch (SlickException e) {
		         e.printStackTrace();
		      }
		   }
	   
}