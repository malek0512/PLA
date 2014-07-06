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

import game.Menu;

public class SelectionMapSolo extends BasicGameState 
{ 
    private StateBasedGame game;
    private Image MAP1,MAP2,MAP3,MAP4,FOND_SELECTION;
    private int PG_X,PG_Y;
    public static int choix_difficulte = 1;
    
     
	   public static final int ID = 10;

  
	   public int getID() 
	   {
		      return ID;
	 }
	   
    public void init(GameContainer container, StateBasedGame game) throws SlickException { 
    	this.game = game;
    	
    	FOND_SELECTION  = new Image("src/graphisme/main/ressources/map/image/SelectionPerso.jpeg");
    	MAP1 = new Image("src/graphisme/main/ressources/map/image/Map1.png");
    	MAP2 = new Image("src/graphisme/main/ressources/map/image/Map2.png");
    	MAP3 = new Image("src/graphisme/main/ressources/map/image/Map3.png");
    	MAP4 = new Image("src/graphisme/main/ressources/map/image/Map4.png");
    	PG_X = 100;
    	PG_Y = 50 ;
    	
    } 
 
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
    {
         FOND_SELECTION.draw();
         MAP1.draw(100, 50);
         MAP2.draw(500, 50);
         MAP3.draw(100, 350);
         MAP4.draw(500, 350);
		g.setColor(Color.white);
		g.drawRect(PG_X, PG_Y, 181, 200);
                  
    } 
 
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {}
 
    
	   public void keyReleased(int key, char c) {
		      switch (key) {
		      		case Input.KEY_ENTER :
	      				game.enterState(Difficulte.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));break;
		      				
		      		case Input.KEY_ESCAPE:Menu.container.exit(); break;
		      		case Input.KEY_M: if(Accueil.Music_Choix.playing()){ Accueil.Music_Choix.pause(); MusicManager.mute=true; }else {Accueil.Music_Choix.resume(); MusicManager.mute=false;}break;

		      		case Input.KEY_DOWN:
		      			if(PG_Y < 350)
		      			{
		      				PG_Y += 300;
		      				WindowGame.Choix_Map_Solo+= 2;
		      			}
		      			
	      				break;
	      			case Input.KEY_UP:
		      			if(PG_Y > 50)
		      			{
		      				PG_Y -= 300;
		      				WindowGame.Choix_Map_Solo -= 2;
		      			}
		      			break;
		      			
	      			case Input.KEY_RIGHT:
		      			if(PG_X < 500)
		      			{
		      				PG_X += 400;
		      				WindowGame.Choix_Map_Solo++;
		      			}
		      			break;
		      			
	      			case Input.KEY_LEFT:
		      			if(PG_X > 100)
		      			{
		      				PG_X -= 400;
		      				WindowGame.Choix_Map_Solo--;;
		      			}
		      			break;
		      }
	   }
}