package equipages;

import game.Joueur;
import game.Menu;
import game.WindowGame;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import personnages.*;

public class Demo_suiveur extends Equipage {

	
	PacKnight PACMAN_1;
	Ghost GHOST_1;

	public Demo_suiveur() {
		super();
	}

	public void init() throws SlickException {
		PACMAN_1 = new PacKnight("J1",17,17,Direction.droite,new CoordonneesFloat(17, 17), true);
		GHOST_1 = new Ghost("1");
		Ghost.tempsPasserEnPrison = 0;
		this.joueurFleche =  PACMAN_1;
		this.joueurCamera = PACMAN_1;
		Joueur PM_1 = new Joueur(this.g.SPRITE_PACMAN_1,this.g, PACMAN_1);
		Joueur FM_1 = new Joueur(this.g.SPRITE_SUIVEUR,this.g, GHOST_1,"fm_suiveur.xml");
	}

	   public static void main(String[] argv) {
		      try {
		         Menu.container = new AppGameContainer(new Menu(new Demo_suiveur()));
		         Menu.container.setDisplayMode(WindowGame.resolution_x,WindowGame.resolution_y,false);
		         Menu.container.start();
		      } catch (SlickException e) {
		         e.printStackTrace();
		      }
		   }
	   
}