package equipages;

import game.Joueur;
import game.Menu;
import game.WindowGame;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import personnages.CoordonneesFloat;
import personnages.Direction;
import personnages.Ghost;
import personnages.PacKnight;



public class Equipage_Moyen extends Equipage{

		

		PacKnight PACMAN_1= new PacKnight("J1",17,17,Direction.droite,new CoordonneesFloat(17, 17), true);

		Ghost GHOST_1 = new Ghost("1", 2, 1, Direction.droite,new CoordonneesFloat(2, 1));
		Ghost GHOST_2 = new Ghost("2", 1, 1, Direction.droite,new CoordonneesFloat(20, 1));
		Ghost GHOST_3 = new Ghost("3", 1, 5, Direction.droite,new CoordonneesFloat(26, 1));
		Ghost GHOST_4 = new Ghost("4", 12, 1, Direction.droite,new CoordonneesFloat(18, 1));

		public Equipage_Moyen() {
			super();
			this.joueurFleche =  PACMAN_1;
			this.joueurLettre = null;
		}
		
		public void init() throws SlickException{
			Joueur PM_1 = new Joueur(this.g.SPRITE_PACMAN_1,this.g, PACMAN_1);

			Joueur FM_1 = new Joueur(this.g.SPRITE_GHOST_1,this.g, GHOST_1,"fm_suiveur.xml");
			Joueur FM_2 = new Joueur(this.g.SPRITE_GHOST_3,this.g, GHOST_2,"fm_berserk.xml");
			Joueur FM_3 = new Joueur(this.g.SPRITE_GHOST_2,this.g, GHOST_3,"fm_aleatoire.xml");
			Joueur FM_4 = new Joueur(this.g.SPRITE_GHOST_2,this.g, GHOST_4,"fm_aleatoire.xml");

		}
		
		
	   public static void main(String[] argv) {
		      try {
		         Menu.container = new AppGameContainer(new Menu(new Equipage_Moyen()));
		         Menu.container.setDisplayMode(WindowGame.resolution_x,WindowGame.resolution_y,false);
		         Menu.container.start();
		      } catch (SlickException e) {
		         e.printStackTrace();
		      }
		   }
}
