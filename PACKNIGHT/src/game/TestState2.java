package game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class TestState2 extends BasicGameState {
	   public static final int ID = 2;
	   private float ang;
	   private StateBasedGame game;
	   
	   public int getID() {
	      return ID;
	   }

	   public void init(GameContainer container, StateBasedGame game) throws SlickException {
	      this.game = game;
	   }

	   public void render(GameContainer container, StateBasedGame game, Graphics g) {
	      g.setColor(Color.green);
	      g.drawString("This is State 2", 200, 50);
	      
	      g.rotate(400,300,ang);
	   }

	   public void update(GameContainer container, StateBasedGame game, int delta) {
	      ang += delta * 0.1f;
	   }
	   
	   public void keyReleased(int key, char c) {
	      if (key == Input.KEY_1) {
	         game.enterState(WindowGame.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
	      }
	      if (key == Input.KEY_3) {
	         game.enterState(TestState3.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
	      }
	   }
	}


