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
    private MouseOverArea solo;
    private MouseOverArea multi;
    private MouseOverArea quit;
    private StateBasedGame game;
     
	   public static final int ID = 3;

  
	   public int getID() 
	   {
		      return ID;
	 }
	   
    public void init(GameContainer container, StateBasedGame game) throws SlickException { 
    	this.game = game;
        quit = new MouseOverArea(container, new Image("src/graphisme/main/ressources/map/image/Quit.png"), WindowGame.resolution_x/2+50, WindowGame.resolution_y/2-15, this);
        quit.setMouseOverColor(new Color(0.9f,0.9f,0.9f,1f));
         
        solo = new MouseOverArea(container, new Image("src/graphisme/main/ressources/map/image/Solo.png"), WindowGame.resolution_x/2-150, WindowGame.resolution_y/2-15, this);
        solo.setMouseOverColor(new Color(0.9f,0.9f,0.9f,1f));
 
        multi = new MouseOverArea(container, new Image("src/graphisme/main/ressources/map/image/Multi.png"), WindowGame.resolution_x/2-250, WindowGame.resolution_y/2-105, this);
        multi.setMouseOverColor(new Color(0.9f,0.9f,0.9f,1f));
    } 
 
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
         
        quit.render(container, g);
        solo.render(container, g);
        multi.render(container, g);
         
    } 
 
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {}
 
    public void componentActivated(AbstractComponent source) {
        if (source == quit)
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
    	   // Accueil.Music_WindowGame.loop();
    	   WindowGame.Choix_Map = 1;
    	  game.enterState(WindowGame.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
       }
         
    }
    
	   public void keyReleased(int key, char c) {
		      switch (key) {
		      		case Input.KEY_ESCAPE:Menu.container.exit(); break;
		      		case Input.KEY_M: if(Accueil.Music_Choix.playing()) Accueil.Music_Choix.pause() ;else Accueil.Music_Choix.resume(); break;

		      }
		   }
 
}