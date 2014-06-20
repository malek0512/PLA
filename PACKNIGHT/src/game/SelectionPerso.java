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

public class SelectionPerso extends BasicGameState 
{ 
    private StateBasedGame game;
    private Image Un_P,Deux_P,Trois_P,Quatre_P,Mouse;
    private int PG_X,PG_Y;
     
	   public static final int ID = 7;
	   boolean quit = false;

  
	   public int getID() 
	   {
		      return ID;
	 }
	   
    public void init(GameContainer container, StateBasedGame game) throws SlickException { 
    	this.game = game;
    	Un_P = new Image("src/graphisme/main/ressources/map/image/1P.png");
    	Deux_P = new Image("src/graphisme/main/ressources/map/image/2P.png");
    	Trois_P = new Image("src/graphisme/main/ressources/map/image/3P.png");
    	Quatre_P  = new Image("src/graphisme/main/ressources/map/image/4P.png");
    	Mouse  = new Image("src/graphisme/main/ressources/map/tuiles/pacgomme.png");
    	PG_X = WindowGame.resolution_x/2-190;
    	PG_Y = WindowGame.resolution_y/2-100 ;
    	
    } 
 
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
    {
         Un_P.draw(WindowGame.resolution_x/2-150, WindowGame.resolution_y/2-100);
         Deux_P.draw(WindowGame.resolution_x/2+50, WindowGame.resolution_y/2-100);
         Trois_P.draw(WindowGame.resolution_x/2-150, WindowGame.resolution_y/2);
         Quatre_P.draw(WindowGame.resolution_x/2+50, WindowGame.resolution_y/2);
         Mouse.draw(PG_X,PG_Y);
         
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
		      				game.enterState(WindowGame.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));break;
		      				}
		      				else game.enterState(SelectionPerso.ID);break;
		      			}
		      				
		      			
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