package game;

import java.awt.Window;
import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.SlickException;

import personnages.CoordonneesFloat;
import personnages.Direction;
import personnages.Ghost;
import personnages.PacKnight;
import personnages.PacPrincess;
import personnages.Personnage;
import structure_terrain.Terrain;
import controleur.automate.Automate;

public class Equipage {

	WindowGame g;
	List<Joueur> liste = new LinkedList<Joueur>();
	
	public Equipage(WindowGame g) {
		this.g = g;
	}

	Automate aleatoire,berserk, knight, princess, suiveur;
	PacKnight PACMAN_1= new PacKnight("J1",17,17,Direction.droite,new CoordonneesFloat(1, 1), true);
	PacKnight PACMAN_2 = new PacKnight("J2",5,15,Direction.droite,new CoordonneesFloat(1, 1), false);
//	PacKnight PACMAN_3 = new PacKnight("J3",5,10,Direction.droite,new CoordonneesFloat(1, 1), false);
	PacPrincess PACMAN_4 = new PacPrincess("J4",1,1,Direction.droite,new CoordonneesFloat(1, 1));

//	Ghost GHOST_1 = new Ghost("1", 2, 1, Direction.droite,new CoordonneesFloat(2, 1));
	Ghost GHOST_2 = new Ghost("2", 1, 1, Direction.droite,new CoordonneesFloat(1, 1));
//	Ghost GHOST_3 = new Ghost("3", 1, 5, Direction.droite,new CoordonneesFloat(1, 1));
//	Ghost GHOST_4 = new Ghost("4", 12, 1, Direction.droite,new CoordonneesFloat(1, 1));

	public void init() throws SlickException{

		liste.add(new Joueur(this.g.SPRITE_PACMAN_1,this.g, PACMAN_1));
		liste.add(new Joueur(this.g.SPRITE_PACMAN_2,this.g, PACMAN_2));
//		liste.add(new Joueur(this.g.SPRITE_PACMAN_3,this.g, PACMAN_3));
		liste.add(new Joueur(this.g.SPRITE_PACMAN_4,this.g, PACMAN_4));
		
//		liste.add(new Joueur(this.g.SPRITE_GHOST_1,this.g, GHOST_1));
		liste.add(new Joueur(this.g.SPRITE_GHOST_2,this.g, GHOST_2));
//		liste.add(new Joueur(this.g.SPRITE_GHOST_3,this.g, GHOST_3));
//		liste.add(new Joueur(this.g.SPRITE_GHOST_4,this.g, GHOST_4));
		
    	try{
//    		aleatoire = new Automate("Automate/fm_aleatoire_aveugle.xml",GHOST_1);
    		berserk = new Automate("Automate/fm_berserk.xml",GHOST_2);
//    		suiveur = new Automate("Automate/A_SUIVEUR.xml",GHOST_3);
    		princess = new Automate("Automate/princesse.xml",PACMAN_4);
    		knight = new Automate("Automate/packnight.xml",PACMAN_2);

    		
    	}catch(Exception e)  
    		{System.out.println(e);};
	}
	
	public void suivant() throws Exception{
//		aleatoire.suivant();
//		suiveur.suivant();
	    berserk.suivant();
	    princess.suivant();
	    knight.suivant();
	}
}
