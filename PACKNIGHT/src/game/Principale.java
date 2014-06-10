/**
 * Cette objet est tout simplement le main, qui apelle game
 */
package game;


import game.WindowGame;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;


public class Principale {
static long duree = 10;




	public static void main(String[] args) throws SlickException {
	    new AppGameContainer(new WindowGame(), WindowGame.largueur*WindowGame.tuile_size, WindowGame.hauteur*WindowGame.tuile_size, false).start();
	}
}

