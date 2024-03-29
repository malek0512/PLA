package game;


import music.MusicManager;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import personnages.Personnage;
import game.Menu;

public class Choix extends BasicGameState 
{ 
    private StateBasedGame game;
    private Image Quit,Solo,Multi,Mouse,FOND_CHOIX;
    private int PG_X,PG_Y;
     
	   public static final int ID = 3;
	   boolean quit = false;

  
	   public int getID() 
	   {
		      return ID;
	   }
	   
    public void init(GameContainer container, StateBasedGame game) throws SlickException { 
    	this.game = game;
    	FOND_CHOIX = new Image("src/graphisme/main/ressources/map/image/Choix.jpg");
    	Quit = new Image("src/graphisme/main/ressources/map/image/Quit.png");
    	Solo = new Image("src/graphisme/main/ressources/map/image/Solo.png");
    	Multi = new Image("src/graphisme/main/ressources/map/image/Multi.png");
    	Mouse  = new Image("src/graphisme/main/ressources/map/image/Mouse.png");
    	PG_X = 9;
    	PG_Y = 359 ;
    	
    } 
 
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
    {
    	FOND_CHOIX.draw();
        Quit.draw(550, 350);
        Solo.draw(50, 350);
        Multi.draw(300, 350);
        Mouse.draw(PG_X,PG_Y);
    } 
    
    public void enter(GameContainer container, StateBasedGame game) throws SlickException 
	{
    	Personnage.init_personnage();
	}
 
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {}
 
    
	   public void keyReleased(int key, char c) {  
		   switch (key) {
		      		case Input.KEY_ENTER :
		      			if (quit)Menu.container.exit(); 
		      			else  
		      			{
		      				if(WindowGame.Choix_Map == 0)
		      				{
		      				game.enterState(SelectionMapSolo.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));break;
		      				}
		      				else game.enterState(SelectionPerso.ID);break;
		      			}
		      				
		      			
		      		case Input.KEY_ESCAPE:Menu.container.exit(); break;
		      		case Input.KEY_M: if(Accueil.Music_Choix.playing()){ Accueil.Music_Choix.pause(); MusicManager.mute=true; }else {Accueil.Music_Choix.resume(); MusicManager.mute=false;}break;

		      		case Input.KEY_RIGHT:
		      			if(PG_X < 250)
		      			{
		      				PG_X = 259;
		      	            WindowGame.Choix_Map = 4;
		      	          quit = false;
		      			}
		      			else 
		      			{
		      				PG_X = 509;
		      				quit = true;
		      			}
		      			
	      				break;
	      			case Input.KEY_LEFT:
		      			if(PG_X > 300)
		      			{
		      				PG_X = 259;
		      	            WindowGame.Choix_Map = 4;
		      	          quit = false;
		      			}
		      	        else 
		      	        {
		      	        	PG_X = 9;
		      	        	WindowGame.Choix_Map = 0;
		      	        	quit = false;
		      	        }
		      			break;
		      }
	   }
}