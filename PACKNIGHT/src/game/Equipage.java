package game;

import java.awt.Window;

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
	public Equipage(WindowGame g) {
		this.g = g;
	}
	

	PacKnight PACMAN_1= new PacKnight("J1",14,10,Direction.droite,new CoordonneesFloat(14, 10));
	//PacKnight PACMAN_2 = new PacKnight("J2",1,1,Direction.droite,new CoordonneesFloat(1, 1));
	//PacKnight PACMAN_3 = new PacKnight("J3",1,1,Direction.droite,new CoordonneesFloat(1, 1));
	//PacPrincess PACMAN_4 = new PacPrincess("J4",1,1,Direction.droite,new CoordonneesFloat(1, 1));

	Ghost GHOST_1 = new Ghost("1", 1, 1, Direction.droite,new CoordonneesFloat(1, 1));
	Ghost GHOST_2 = new Ghost("2", 1, 1, Direction.droite,new CoordonneesFloat(1, 1));
	Ghost GHOST_3 = new Ghost("3", 1, 1, Direction.droite,new CoordonneesFloat(1, 1));
	//Ghost GHOST_4 = new Ghost("4", 12, 1, Direction.droite,new CoordonneesFloat(1, 1));

	Automate aleatoire,berserk,suiveur, knight, princess;

	public void init(Terrain terrain){
		Personnage.initTerrain(terrain);
    	try{
    		aleatoire = new Automate("Automate/A_ALEATOIRE_AVEUGLE.xml",GHOST_1);
    		berserk = new Automate("Automate/A_BERSERK.xml",GHOST_2);
    		suiveur = new Automate("Automate/A_SUIVEUR.xml",GHOST_3);
    	//	knight = new Automate("Automate/Packnight.xml",PACMAN_2);
    	//	princess = new Automate("Automate/Princesse.xml",PACMAN_4);
    		
    	}catch(Exception e)  
    		{System.out.println(e);};

	}
	
	public void suivant() throws Exception{
		aleatoire.suivant();
	    berserk.suivant();
	    suiveur.suivant();
	   // princess.suivant();
	   // knight.suivant();
	}
}
