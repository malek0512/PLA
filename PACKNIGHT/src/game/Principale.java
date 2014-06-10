/**
 * Cette objet est tout simplement le main, qui apelle game
 */
package game;

import game.WindowGame;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;


public class Principale {
static long duree = 10;
private static int largeur = 28, longueur = 20;



	public static void main(String[] args) throws SlickException {
	    new AppGameContainer(new WindowGame(), largeur*32, longueur*32, false).start();
	}
}

