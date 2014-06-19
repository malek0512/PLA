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

public class Accueil extends BasicGameState {
	   public static final int ID = 1;
	   private Image PACGUM;
	   private StateBasedGame game;

	   
	   public int getID() {
	      return ID;
	   }

	   public void init(GameContainer container, StateBasedGame game) throws SlickException {
		   	this.game = game;

	   }

	   public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
	      PACGUM = new Image("src/graphisme/main/ressources/map/image/Accueil.png");
	      PACGUM.draw();
	   }

	   public void update(GameContainer container, StateBasedGame game, int delta) {
	   }
	   
	   public void keyReleased(int key, char c) {
	      switch (key) {
	    	  case Input.KEY_ENTER: game.enterState(WindowGame.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));break;
	    	  case Input.KEY_M: if(WindowGame.M.playing()) WindowGame.M.pause() ;else WindowGame.M.resume(); break;
	    	  case Input.KEY_ESCAPE:Menu.container.exit(); break;

	      }
	   }
	}


