package equipages;

import game.Joueur;
import game.Menu;
import game.WindowGame;
import music.MusicManager;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import personnages.*;
import structure_terrain.CoordonneesFloat;
import structure_terrain.Direction;

public class Demo_Protection_Princesse extends Equipage {



	public Demo_Protection_Princesse() {
		super();

	}

	public void init() throws SlickException {
		Ghost.vision = 100;
		Ghost.tempsPasserEnPrison=0;
		MusicManager.mute=true;
		PacKnight PACMAN_1= new PacKnight("J1",26,29,Direction.droite,new CoordonneesFloat(26, 29),false);
		PacPrincess PACMAN_2= new PacPrincess("J2",1,1,Direction.droite,new CoordonneesFloat(18, 17));
		Ghost GHOST_1 = new Ghost("1");
		this.joueurFleche =  GHOST_1;
		this.joueurCamera = PACMAN_1;
		Joueur PM_1 = new Joueur(this.g.SPRITE_PACMAN_1,this.g, PACMAN_1,"packnight.xml");
		Joueur PM_2 = new Joueur(this.g.SPRITE_PACMAN_4,this.g, PACMAN_2,"pa_en_detresse.xml");
		Joueur FM_1 = new Joueur(this.g.SPRITE_SUIVEUR,this.g, GHOST_1);
	}

	   public static void main(String[] argv) {
		      try {
		         Menu.container = new AppGameContainer(new Menu(new Demo_Protection_Princesse()));
		         Menu.container.setDisplayMode(WindowGame.resolution_x,WindowGame.resolution_y,false);
		         Menu.container.start();
		      } catch (SlickException e) {
		         e.printStackTrace();
		      }
		   }
	   
}