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

public class Free extends BasicGameState 
{ 
    private StateBasedGame game;
    private Image BERSERK,RANDOM_G,RANDOM_BLIND,HUNTER,LORD,INTERCEPTOR,FOND_SELECTION,MOUSE;
    private int PG_X,PG_Y;
    public static int Ghost_1=1,Ghost_2=1,Ghost_3=1,Ghost_4=1;
    private int choix = 1;
    
     
	   public static final int ID = 9;
	   boolean quit = false;

  
	   public int getID() 
	   {
		      return ID;
	 }
	   
    public void init(GameContainer container, StateBasedGame game) throws SlickException { 
    	this.game = game;
    	
    	FOND_SELECTION  = new Image("src/graphisme/main/ressources/map/image/SelectionPerso.jpeg");
    	BERSERK = new Image("src/graphisme/main/ressources/map/image/Berserk.png");
    	RANDOM_G = new Image("src/graphisme/main/ressources/map/image/Random.png");
    	RANDOM_BLIND = new Image("src/graphisme/main/ressources/map/image/Random_Blind.png");
    	HUNTER = new Image("src/graphisme/main/ressources/map/image/Follower.png");
    	LORD = new Image("src/graphisme/main/ressources/map/image/Lord.png");
    	INTERCEPTOR = new Image("src/graphisme/main/ressources/map/image/Interceptor.png");
    	MOUSE  = new Image("src/graphisme/main/ressources/map/image/Mouse.png");
    	PG_X = WindowGame.resolution_x/2-50-39;
    	PG_Y = WindowGame.resolution_y/2-200+9 ;
    	
    } 
 
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException 
    {
         FOND_SELECTION.draw();
         switch (Ghost_1)
         {
         	case 1 :RANDOM_G.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2-200);break;
         	case 2 :RANDOM_BLIND.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2-200);break;
         	case 3 :HUNTER.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2-200);break;
         	case 4 :BERSERK.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2-200);break;
         	case 5 :INTERCEPTOR.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2-200);break;
         	case 6 :LORD.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2-200);break;
         		
         }
         
         switch (Ghost_2)
         {
         	case 1 :RANDOM_G.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2-100);break;
         	case 2 :RANDOM_BLIND.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2-100);break;
         	case 3 :HUNTER.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2-100);break;
         	case 4 :BERSERK.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2-100);break;
         	case 5 :INTERCEPTOR.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2-100);break;
         	case 6 :LORD.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2-100);break;
         		
         }
         
         switch (Ghost_3)
         {
         	case 1 :RANDOM_G.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2);break;
         	case 2 :RANDOM_BLIND.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2);break;
         	case 3 :HUNTER.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2);break;
         	case 4 :BERSERK.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2);break;
         	case 5 :INTERCEPTOR.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2);break;
         	case 6 :LORD.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2);break;
         		
         }
         
         switch (Ghost_4)
         {
         	case 1 :RANDOM_G.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2+100);break;
         	case 2 :RANDOM_BLIND.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2+100);break;
         	case 3 :HUNTER.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2+100);break;
         	case 4 :BERSERK.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2+100);break;
         	case 5 :INTERCEPTOR.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2+100);break;
         	case 6 :LORD.draw(WindowGame.resolution_x/2-50, WindowGame.resolution_y/2+100);break;
         		
         }
         
         
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
	      				case 1 :if (Ghost_1>1) Ghost_1 -= 1;break;
	      				case 2 :if (Ghost_2>1) Ghost_2 -= 1;break;
	      				case 3 :if (Ghost_3>1) Ghost_3 -= 1;break;
	      				case 4 :if (Ghost_4>1) Ghost_4 -= 1;break;
	      				}
	      				break;
	      			case Input.KEY_RIGHT:	
	      				switch(choix)
	      				{
	      				case 1 :if (Ghost_1<6) Ghost_1 += 1;break; 
	      				case 2 :if (Ghost_2<6) Ghost_2 += 1;break; 
	      				case 3 :if (Ghost_3<6) Ghost_3 += 1;break; 
	      				case 4 :if (Ghost_4<6) Ghost_4 += 1;break; 
	      				}
	      				break;
	      				
		      }
	   }
}