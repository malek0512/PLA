package game;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import game.Menu;

public class Choix extends BasicGameState implements ComponentListener
{ 
    private StateBasedGame game;
    private Image Quit,Solo,Multi,Pacgomme;
    private int PG_X,PG_Y;
     
	   public static final int ID = 3;
	   boolean quit = false;

  
	   public int getID() 
	   {
		      return ID;
	 }
	   
    public void init(GameContainer container, StateBasedGame game) throws SlickException { 
    	this.game = game;
    	Quit = new Image("src/graphisme/main/ressources/map/image/Quit.png");
    	Solo = new Image("src/graphisme/main/ressources/map/image/Solo.png");
    	Multi = new Image("src/graphisme/main/ressources/map/image/Multi.png");
    	Pacgomme  = new Image("src/graphisme/main/ressources/map/tuiles/pacgomme.png");
    	PG_X = WindowGame.resolution_x/2-90;
    	PG_Y = WindowGame.resolution_y/2-100 ;
    	
    } 
 
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
    {
         Quit.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2+100);
         Solo.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2-100);
         Multi.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2);
         Pacgomme.draw(PG_X,PG_Y);
         
    } 
 
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {}
 
    public void componentActivated(AbstractComponent source) {
       /* if (source == quit)
        {
            Menu.container.exit();
        }
       if (source == solo)
        {
    	 // Accueil.Music_WindowGame.loop();
    	  WindowGame.Choix_Map = 0;
    	  game.enterState(WindowGame.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
        }
       if (source == multi)
       {
           WindowGame.Choix_Map = 1;
    	   game.enterState(Multi.ID);
       }*/
         
    }
    
	   public void keyReleased(int key, char c) {
		      switch (key) {
		      		case Input.KEY_ENTER :if (quit)Menu.container.exit(); else  game.enterState(WindowGame.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));break;
		      		case Input.KEY_ESCAPE:Menu.container.exit(); break;
		      		case Input.KEY_M: if(Accueil.Music_Choix.playing()) Accueil.Music_Choix.pause() ;else Accueil.Music_Choix.resume(); break;

		      		case Input.KEY_DOWN:
		      			if(PG_Y < WindowGame.resolution_y/2)
		      			{
		      				PG_Y = WindowGame.resolution_y/2;
		      	            WindowGame.Choix_Map = 1;
		      	          quit = false;
		      			}
		      			else 
		      			{
		      				PG_Y = WindowGame.resolution_y/2+100;
		      				quit = true;
		      			}
		      			
	      				break;
	      			case Input.KEY_UP:
		      			if(PG_Y > WindowGame.resolution_y/2-100)
		      			{
		      				PG_Y = PG_Y-100;
		      	            WindowGame.Choix_Map = 0;
		      	          quit = false;
		      			}
		      	        else 
		      	        {
		      	        	PG_Y = WindowGame.resolution_y/2-100;
		      	        	quit = true;
		      	        }
		      			break;
		      }
	   }
}