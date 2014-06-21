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

public class Difficulte extends BasicGameState 
{ 
    private StateBasedGame game;
    private Image EASY,HARD,FREE,NORMAL,FOND_SELECTION,MOUSE;
    private int PG_X,PG_Y;
    public static int choix_difficulte = 1;
    
     
	   public static final int ID = 8;

  
	   public int getID() 
	   {
		      return ID;
	 }
	   
    public void init(GameContainer container, StateBasedGame game) throws SlickException { 
    	this.game = game;
    	
    	FOND_SELECTION  = new Image("src/graphisme/main/ressources/map/image/SelectionPerso.jpeg");
    	EASY = new Image("src/graphisme/main/ressources/map/image/Easy.png");
    	NORMAL = new Image("src/graphisme/main/ressources/map/image/Normal.png");
    	HARD = new Image("src/graphisme/main/ressources/map/image/Hard.png");
    	FREE = new Image("src/graphisme/main/ressources/map/image/Free.png");
    	MOUSE  = new Image("src/graphisme/main/ressources/map/image/Mouse.png");
    	PG_X = WindowGame.resolution_x/2-50-39;
    	PG_Y = WindowGame.resolution_y/2-200+9 ;
    	
    } 
 
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
    {
         FOND_SELECTION.draw();
         EASY.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2-200);
         NORMAL.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2-100);
         HARD.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2);
         FREE.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2+100);
         
         MOUSE.draw(PG_X,PG_Y);
         
    } 
 
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {}
 
    
	   public void keyReleased(int key, char c) {
		      switch (key) {
		      		case Input.KEY_ENTER :
	      				if (choix_difficulte == 4)
	      				{game.enterState(Free.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
	      				break;
	      				}
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
		      				choix_difficulte++;
		      			}
		      			
	      				break;
	      			case Input.KEY_UP:
		      			if(PG_Y > WindowGame.resolution_y/2-100)
		      			{
		      				PG_Y = PG_Y-100;
		      				choix_difficulte--;
		      			}
		      			break;
		      			
	      				
		      }
	   }
}