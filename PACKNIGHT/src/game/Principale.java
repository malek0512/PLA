/**
 * Cette objet est tout simplement le main, qui apelle game
 */
package game;

import game.WindowGame;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Principale {
static long duree = 10;


/*Les differentes resolutions possible sont
 * 
 * WindowGame.largueur*WindowGame*tuile_size,WindowGame.hauteur*WindowGame*tuile_size
 * 1600,900
 * 1024,768
 * 800,600
 * 1440,900
 * 1360,768
 */

	public static void main(String[] args) throws SlickException {
		
		
	    new AppGameContainer(new WindowGame(),1600,900,true).start();
	}
}

