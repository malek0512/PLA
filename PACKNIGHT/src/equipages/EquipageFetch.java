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

public class EquipageFetch extends Equipage {

	PacKnight PACMAN_1;
	Ghost GHOST_1;
	
	public EquipageFetch() {
		super();
		}

	public void init() throws SlickException {
		PACMAN_1= new PacKnight("J1",17,17,Direction.droite,new CoordonneesFloat(17, 17), false);
		GHOST_1 = new Ghost("1");
		this.joueurCamera =  PACMAN_1;
	
		Joueur PM_1 = new Joueur(this.g.SPRITE_PACMAN_1,this.g, PACMAN_1,"fm_ramasseur_pacgum.xml");
		Joueur FM_1 = new Joueur(this.g.SPRITE_SUIVEUR,this.g, GHOST_1,"fm_suiveur.xml");

	}

	   public static void main(String[] argv) {
		      try {
		         Menu.container = new AppGameContainer(new Menu(new EquipageFetch()));
		         Menu.container.setDisplayMode(WindowGame.resolution_x,WindowGame.resolution_y,false);
		         Menu.container.start();
		      } catch (SlickException e) {
		         e.printStackTrace();
		      }
		   }
	   
}