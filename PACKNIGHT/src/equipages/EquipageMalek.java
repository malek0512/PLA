package equipages;

import game.Joueur;
import game.Menu;
import game.WindowGame;

import java.awt.Window;
import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import personnages.Ghost;
import personnages.PacKnight;
import personnages.PacPrincess;
import personnages.Personnage;
import structure_terrain.CoordonneesFloat;
import structure_terrain.Direction;
import structure_terrain.Terrain;
import controleur.automate.Automate;

public class EquipageMalek extends Equipage{
	

	PacKnight PACMAN_1= new PacKnight("J1",17,17,Direction.droite,new CoordonneesFloat(1, 1), true);
	PacKnight PACMAN_2 = new PacKnight("J2",25,15,Direction.droite,new CoordonneesFloat(25, 15), false);
	PacKnight PACMAN_3 = new PacKnight("J3",6,15,Direction.droite,new CoordonneesFloat(6, 15), false);
	PacPrincess PACMAN_4 = new PacPrincess("J4",5,15,Direction.droite,new CoordonneesFloat(1, 1));

	Ghost GHOST_1 = new Ghost("1");
//	Ghost GHOST_2 = new Ghost("2", 1, 1, Direction.droite,new CoordonneesFloat(1, 1));
//	Ghost GHOST_3 = new Ghost("3", 1, 5, Direction.droite,new CoordonneesFloat(1, 1));
//	Ghost GHOST_4 = new Ghost("4", 12, 1, Direction.droite,new CoordonneesFloat(1, 1));

	public EquipageMalek() {
		super();
		this.joueurCamera =  GHOST_1;
		this.joueurFleche =  GHOST_1;
		this.joueurZQSD = PACMAN_1;
	}
	
	public void init() throws SlickException{
		Joueur PM_1 = new Joueur(this.g.SPRITE_PACMAN_1,this.g, PACMAN_1);
		Joueur PM_2 = new Joueur(this.g.SPRITE_PACMAN_2,this.g, PACMAN_2, "packnight.xml");
		Joueur PM_3 = new Joueur(this.g.SPRITE_PACMAN_3,this.g, PACMAN_3, "packnight.xml");
		Joueur PM_4 = new Joueur(this.g.SPRITE_PACMAN_4,this.g, PACMAN_4, "princesse.xml");
		

		Joueur FM_1 = new Joueur(this.g.SPRITE_ALEATOIRE,this.g, GHOST_1);
//		Joueur FM_2 = new Joueur(this.g.SPRITE_ALEATOIRE,this.g, GHOST_2);
//		Joueur FM_3 = new Joueur(this.g.SPRITE_ALEATOIRE,this.g, GHOST_3);
//		Joueur FM_4 = new Joueur(this.g.SPRITE_ALEATOIRE,this.g, GHOST_4);

	}
	
	
   public static void main(String[] argv) {
	      try {
	         Menu.container = new AppGameContainer(new Menu(new EquipageMalek()));
	         Menu.container.setDisplayMode(WindowGame.resolution_x,WindowGame.resolution_y,false);
	         Menu.container.start();
	      } catch (SlickException e) {
	         e.printStackTrace();
	      }
	   }
}