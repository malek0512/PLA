package game;

import org.newdawn.slick.Color;
import game.WindowGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Choix extends BasicGameState {
	   public static final int ID = 3;
	   private String[] options = new String[] {"Start Game","Credits","Highscores","Instructions","Exit"};
	   /** The index of the selected option */
	   private int selected;
	   /** The game holding this state */
	   private StateBasedGame game;
	   
	   public int getID() {
	      return ID;
	   }

	   public void init(GameContainer container, StateBasedGame game) throws SlickException {
	      this.game = game;
	   }

	   public void render(GameContainer container, StateBasedGame game, Graphics g) {
	      g.setColor(Color.blue);
	      g.drawString("This is State 3", 200, 50);
	      g.setColor(Color.white);
	      
	      for (int i=0;i<options.length;i++) {
	         if (selected == i) {
	            g.drawRect(200,190+(i*50),400,50);
	         }
	      }
	   }

	   public void update(GameContainer container, StateBasedGame game, int delta) {
	   }

	   public void keyReleased(int key, char c) {
		  switch (key)
		  {
		  case Input.KEY_U : game.enterState(WindowGame.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black)); break;
		    case Input.KEY_M: if(WindowGame.M.playing()) WindowGame.M.pause() ;else WindowGame.M.resume(); break;
	    //case Input.KEY_ESCAPE:container.exit(); break;		   
	      }

	   }
	}