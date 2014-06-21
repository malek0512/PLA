package equipages;


import game.*;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import personnages.CoordonneesFloat;
import personnages.Direction;
import personnages.Ghost;
import personnages.PacKnight;
import personnages.PacPrincess;
import personnages.Personnage;

public class EquipageQuentin extends equipages.Equipage {

	PacKnight PACMAN_1;
	PacKnight PACMAN_2;
	PacKnight PACMAN_3;
	PacPrincess PACMAN_4 ;

	Ghost GHOST_1; 
	Ghost GHOST_2; 
	Ghost GHOST_3; 
	Ghost GHOST_4; 
	
	public void init() throws SlickException {
		

		
		if(WindowGame.Choix_Map == 0 )
		{
			Joueur FM_1;
			Joueur FM_2;
			Joueur FM_3;
			Joueur FM_4;
			GHOST_1 = new Ghost("1", 6, 2, Direction.droite,new CoordonneesFloat(6, 2));
			GHOST_2 = new Ghost("2", 6, 3, Direction.droite,new CoordonneesFloat(6, 3));
			GHOST_3 = new Ghost("3", 6, 4, Direction.droite,new CoordonneesFloat(6, 4));
			GHOST_4 = new Ghost("4", 6, 5, Direction.droite,new CoordonneesFloat(6, 5));
			switch (Difficulte.choix_difficulte)
			{
			case 1 : 
				FM_1 = new Joueur(this.g.SPRITE_GHOST_1,this.g, GHOST_1,"fm_suiveur.xml");
				FM_2 = new Joueur(this.g.SPRITE_GHOST_2,this.g, GHOST_2,"fm_aleatoire_aveugle.xml");
				FM_3 = new Joueur(this.g.SPRITE_GHOST_3,this.g, GHOST_3,"fm_aleatoire.xml");
				FM_4 = new Joueur(this.g.SPRITE_GHOST_4,this.g, GHOST_4,"fm_aleatoire.xml");
				break;
			
			case 2 :
				FM_1 = new Joueur(this.g.SPRITE_GHOST_1,this.g, GHOST_1,"fm_aleatoire.xml");
				FM_2 = new Joueur(this.g.SPRITE_GHOST_2,this.g, GHOST_2,"fm_berserk.xml");
				FM_3 = new Joueur(this.g.SPRITE_GHOST_3,this.g, GHOST_3,"fm_suiveur.xml");
				FM_4 = new Joueur(this.g.SPRITE_GHOST_4,this.g, GHOST_4,"fm_aleatoire.xml");
				break;
				
			case 3 :
				FM_1 = new Joueur(this.g.SPRITE_GHOST_1,this.g, GHOST_1,"fm_berserk.xml");
				FM_2 = new Joueur(this.g.SPRITE_GHOST_2,this.g, GHOST_2,"fm_berserk.xml");
				FM_3 = new Joueur(this.g.SPRITE_GHOST_3,this.g, GHOST_3,"fm_suiveur.xml");
				FM_4 = new Joueur(this.g.SPRITE_GHOST_4,this.g, GHOST_4,"fm_intercepteur.xml");
				
				break;
				
				
			case 4 :
		         switch (Free.Ghost_1)
		         {
		         	case 1 :FM_1 = new Joueur(this.g.SPRITE_GHOST_1,this.g, GHOST_1,"fm_aleatoire.xml");break;
		         	case 2 :FM_1 = new Joueur(this.g.SPRITE_GHOST_1,this.g, GHOST_1,"fm_aleatoire_aveugle.xml");break;
		         	case 3 :FM_1 = new Joueur(this.g.SPRITE_GHOST_1,this.g, GHOST_1,"fm_suiveur.xml");break;
		         	case 4 :FM_1 = new Joueur(this.g.SPRITE_GHOST_1,this.g, GHOST_1,"fm_berserk.xml");break;
		         	case 5 :FM_1 = new Joueur(this.g.SPRITE_GHOST_1,this.g, GHOST_1,"fm_intercepteur.xml");;break;
		         	case 6 :FM_1 = new Joueur(this.g.SPRITE_GHOST_1,this.g, GHOST_1,"fm_lord.xml");;break;
		         		
		         }
		         
		         switch (Free.Ghost_2)
		         {
		         	case 1 :FM_2 = new Joueur(this.g.SPRITE_GHOST_2,this.g, GHOST_2,"fm_aleatoire.xml");break;
		         	case 2 :FM_2 = new Joueur(this.g.SPRITE_GHOST_2,this.g, GHOST_2,"fm_aleatoire_aveugle.xml");break;
		         	case 3 :FM_2 = new Joueur(this.g.SPRITE_GHOST_2,this.g, GHOST_2,"fm_suiveur.xml");break;
		         	case 4 :FM_2 = new Joueur(this.g.SPRITE_GHOST_2,this.g, GHOST_2,"fm_berserk.xml");break;
		         	case 5 :FM_2 = new Joueur(this.g.SPRITE_GHOST_2,this.g, GHOST_2,"fm_intercepteur.xml");;break;
		         	case 6 :FM_2 = new Joueur(this.g.SPRITE_GHOST_2,this.g, GHOST_2,"fm_lord.xml");;break;
		         		
		         }
		         
		         switch (Free.Ghost_3)
		         {
		         	case 1 :FM_3 = new Joueur(this.g.SPRITE_GHOST_3,this.g, GHOST_3,"fm_aleatoire.xml");break;
		         	case 2 :FM_3 = new Joueur(this.g.SPRITE_GHOST_3,this.g, GHOST_3,"fm_aleatoire_aveugle.xml");break;
		         	case 3 :FM_3 = new Joueur(this.g.SPRITE_GHOST_3,this.g, GHOST_3,"fm_suiveur.xml");break;
		         	case 4 :FM_3 = new Joueur(this.g.SPRITE_GHOST_3,this.g, GHOST_3,"fm_berserk.xml");break;
		         	case 5 :FM_3 = new Joueur(this.g.SPRITE_GHOST_3,this.g, GHOST_3,"fm_intercepteur.xml");;break;
		         	case 6 :FM_3 = new Joueur(this.g.SPRITE_GHOST_3,this.g, GHOST_3,"fm_lord.xml");;break;
		         		
		         }
		         
		         switch (Free.Ghost_4)
		         {
		         	case 1 :FM_4 = new Joueur(this.g.SPRITE_GHOST_4,this.g, GHOST_4,"fm_aleatoire.xml");break;
		         	case 2 :FM_4 = new Joueur(this.g.SPRITE_GHOST_4,this.g, GHOST_4,"fm_aleatoire_aveugle.xml");break;
		         	case 3 :FM_4 = new Joueur(this.g.SPRITE_GHOST_4,this.g, GHOST_4,"fm_suiveur.xml");break;
		         	case 4 :FM_4 = new Joueur(this.g.SPRITE_GHOST_4,this.g, GHOST_4,"fm_berserk.xml");break;
		         	case 5 :FM_4 = new Joueur(this.g.SPRITE_GHOST_4,this.g, GHOST_4,"fm_intercepteur.xml");;break;
		         	case 6 :FM_4 = new Joueur(this.g.SPRITE_GHOST_4,this.g, GHOST_4,"fm_lord.xml");;break;
		         		                                                         
		         }
				
				
				break;
			
			}
			PACMAN_1= new PacKnight("J1",1,1,Direction.droite,new CoordonneesFloat(1, 1), true);
			Joueur PM_1 = new Joueur(this.g.SPRITE_PACMAN_1,this.g, PACMAN_1);
			
			
			this.joueurCamera =  PACMAN_1;
			this.joueur8456 =  PACMAN_1;
		}
		
		else if (WindowGame.Choix_Map == 1)
		{
			GHOST_1 = new Ghost("1", 14, 14, Direction.droite,new CoordonneesFloat(14, 14));
			GHOST_2 = new Ghost("2", 14, 14, Direction.droite,new CoordonneesFloat(14, 14));
			GHOST_3 = new Ghost("3", 14, 14, Direction.droite,new CoordonneesFloat(14, 14));
			GHOST_4 = new Ghost("4", 14, 14, Direction.droite,new CoordonneesFloat(14, 14));
			
			if (SelectionPerso.Perso_1 == 1 ) PACMAN_1= new PacKnight("J1",1,20,Direction.droite,new CoordonneesFloat(1, 1), true);
			else PACMAN_1= new PacKnight("J1",17,17,Direction.droite,new CoordonneesFloat(1, 1), false);
			
			if (SelectionPerso.Perso_2 == 1 )PACMAN_2 = new PacKnight("J2",5,15,Direction.droite,new CoordonneesFloat(5, 15), true);
			else PACMAN_2 = new PacKnight("J2",5,15,Direction.droite,new CoordonneesFloat(5, 15), false);	
				
			if (SelectionPerso.Perso_3 == 1 )PACMAN_3 = new PacKnight("J3",5,10,Direction.droite,new CoordonneesFloat(1, 1), true);	
			else PACMAN_3 = new PacKnight("J3",5,10,Direction.droite,new CoordonneesFloat(1, 1), false);	
			
			PACMAN_4 = new PacPrincess("J4",1,1,Direction.droite,new CoordonneesFloat(1, 1));
				
				
			if (SelectionPerso.Perso_1 == 1 ){	
			Joueur PM_1 = new Joueur(this.g.SPRITE_PACMAN_1,this.g, PACMAN_1);}
			else {Joueur PM_1 = new Joueur(this.g.SPRITE_PACMAN_1,this.g, PACMAN_1, "packnight.xml");}
				
			if (SelectionPerso.Perso_2 == 1 ){
			Joueur PM_2 = new Joueur(this.g.SPRITE_PACMAN_2,this.g, PACMAN_2);}
			else {Joueur PM_2 = new Joueur(this.g.SPRITE_PACMAN_2,this.g, PACMAN_2, "packnight.xml");}	
			
			if (SelectionPerso.Perso_3 == 1 ){
			Joueur PM_3 = new Joueur(this.g.SPRITE_PACMAN_3,this.g, PACMAN_3);}
			else {Joueur PM_3 = new Joueur(this.g.SPRITE_PACMAN_3,this.g, PACMAN_3, "packnight.xml");}
			
			if (SelectionPerso.Perso_4 == 1 ){
			Joueur PM_4 = new Joueur(this.g.SPRITE_PACMAN_4,this.g, PACMAN_4);}
			else {Joueur PM_4 = new Joueur(this.g.SPRITE_PACMAN_4,this.g, PACMAN_4, "princesse.xml");}
			
	
			Joueur FM_1 = new Joueur(this.g.SPRITE_GHOST_1,this.g, GHOST_1,"fm_berserk.xml");
			Joueur FM_2 = new Joueur(this.g.SPRITE_GHOST_2,this.g, GHOST_2,"fm_suiveur.xml");
			Joueur FM_3 = new Joueur(this.g.SPRITE_GHOST_3,this.g, GHOST_3,"fm_intercepteur.xml");
			Joueur FM_4 = new Joueur(this.g.SPRITE_GHOST_4,this.g, GHOST_4,"fm_lord.xml");
			

		}
	}
	

	
	public EquipageQuentin() {
		super();
		this.joueurCamera =  PACMAN_1;
		this.joueurFleche =  PACMAN_1;
	}

	   public static void main(String[] argv) {
		      try {
		         Menu.container = new AppGameContainer(new Menu(new EquipageQuentin()));
		         Menu.container.setDisplayMode(WindowGame.resolution_x,WindowGame.resolution_y,false);
		         Menu.container.start();
		      } catch (SlickException e) {
		         e.printStackTrace();
		      }
		   }
	   
}