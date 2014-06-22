package equipages;

import game.Joueur;
import game.Menu;
import game.WindowGame;
import music.MusicManager;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import personnages.*;

public class Demo_pacPrincess extends Equipage {



	public Demo_pacPrincess() {
		super();

	}

	public void init() throws SlickException {
		Ghost.vision = 100;
		MusicManager.mute=true;
		PacPrincess PACMAN_1= new PacPrincess("J1",17,17,Direction.droite,new CoordonneesFloat(17, 17));
		Ghost GHOST_1 = new Ghost("1");
		Ghost GHOST_3 = new Ghost("3");
		this.joueurFleche =  PACMAN_1;
		this.joueurCamera = PACMAN_1;
		Joueur PM_1 = new Joueur(this.g.SPRITE_PACMAN_4,this.g, PACMAN_1,"pa_fuit.xml");
		Joueur FM_1 = new Joueur(this.g.SPRITE_SUIVEUR,this.g, GHOST_1,"fm_suiveur.xml");
		Joueur FM_3 = new Joueur(this.g.SPRITE_INTERCEPTEUR,this.g, GHOST_3,"fm_intercepteur.xml");
	}

	   public static void main(String[] argv) {
		      try {
		         Menu.container = new AppGameContainer(new Menu(new Demo_pacPrincess()));
		         Menu.container.setDisplayMode(WindowGame.resolution_x,WindowGame.resolution_y,false);
		         Menu.container.start();
		      } catch (SlickException e) {
		         e.printStackTrace();
		      }
		   }
	   
}