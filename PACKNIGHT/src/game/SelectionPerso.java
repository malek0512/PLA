package game;



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

import game.Menu;

public class SelectionPerso extends BasicGameState 
{ 
    private StateBasedGame game;
    private Image AI,PACKNIGHT,PRINCESS,FOND_SELECTION,MOUSE;
    private int PG_X,PG_Y;
    public static int Perso_1=1,Perso_2=1,Perso_3=1,Perso_4=1;
    private int choix = 1;
    
     
	   public static final int ID = 7;
	   boolean quit = false;

  
	   public int getID() 
	   {
		      return ID;
	 }
	   
    public void init(GameContainer container, StateBasedGame game) throws SlickException { 
    	this.game = game;
    	
    	FOND_SELECTION  = new Image("src/graphisme/main/ressources/map/image/SelectionPerso.jpeg");
    	AI = new Image("src/graphisme/main/ressources/map/image/AI.png");
    	PACKNIGHT = new Image("src/graphisme/main/ressources/map/image/Packnight.png");
    	PRINCESS = new Image("src/graphisme/main/ressources/map/image/Princess.png");
    	MOUSE  = new Image("src/graphisme/main/ressources/map/image/Mouse.png");
    	PG_X = WindowGame.resolution_x/2-50-39;
    	PG_Y = WindowGame.resolution_y/2-200+9 ;
    	
    } 
 
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
    {
         FOND_SELECTION.draw();
         if ( Perso_1 ==1)
         PACKNIGHT.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2-200);
         else AI.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2-200);
        	 
         if ( Perso_2 ==1)
         PACKNIGHT.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2-100);
         else AI.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2-100);
         
         if ( Perso_3 ==1)
         PACKNIGHT.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2);
         else AI.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2);
         
         if ( Perso_4 ==1)
         PRINCESS.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2+100);
         else AI.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2+100);
         
         MOUSE.draw(PG_X,PG_Y);
         
    } 
 
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {}
 
    
	   public void keyReleased(int key, char c) {
		      switch (key) {
		      		case Input.KEY_ENTER :
		      			if (quit)Menu.container.exit(); 
		      			else  
		      			{
		      				Accueil.Music_WindowGame.loop();
		      				game.enterState(WindowGame.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));break;
		      			}
		      				
		      		case Input.KEY_ESCAPE:Menu.container.exit(); break;
		      		case Input.KEY_M: if(Accueil.Music_Choix.playing()) Accueil.Music_Choix.pause() ;else Accueil.Music_Choix.resume(); break;

		      		case Input.KEY_DOWN:
		      			if(PG_Y < WindowGame.resolution_y/2+100)
		      			{
		      				PG_Y = PG_Y+100;
		      				choix++;
		      			}
		      			
	      				break;
	      			case Input.KEY_UP:
		      			if(PG_Y > WindowGame.resolution_y/2-100)
		      			{
		      				PG_Y = PG_Y-100;
		      				choix--;
		      			}
		      			break;
		      			
	      			case Input.KEY_LEFT:	
	      				switch(choix)
	      				{
	      				case 1 :if (Perso_1==0) Perso_1 = 1; else Perso_1 =0 ;break;
	      				case 2 :if (Perso_2==0) Perso_2 = 1; else Perso_2 =0 ;break;
	      				case 3 :if (Perso_3==0) Perso_3 = 1; else Perso_3 =0 ;break;
	      				case 4 :if (Perso_4==0) Perso_4 = 1; else Perso_4 =0 ;break;
	      				}
	      				break;
	      			case Input.KEY_RIGHT:	
	      				switch(choix)
	      				{
	      				case 1 :if (Perso_1==0) Perso_1 = 1; else Perso_1 =0 ;break;
	      				case 2 :if (Perso_2==0) Perso_2 = 1; else Perso_2 =0 ;break;
	      				case 3 :if (Perso_3==0) Perso_3 = 1; else Perso_3 =0 ;break;
	      				case 4 :if (Perso_4==0) Perso_4 = 1; else Perso_4 =0 ;break;
	      				}
	      				break;
	      				
		      }
	   }
}