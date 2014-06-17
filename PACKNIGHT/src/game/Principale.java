/**
 * Cette objet est tout simplement le main, qui apelle game
 */
package game;

import game.WindowGame;
import game.WindowGameSave;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Principale {
static long duree = 10;

	public static void main(String[] args) throws SlickException {
		
	  	new AppGameContainer(new WindowGameSave2(),WindowGameSave2.resolution_x,WindowGameSave2.resolution_y,true).start();
	  //	new AppGameContainer(new WindowGame(),WindowGame.resolution_x,WindowGame.resolution_y,true).start();
	  //  new AppGameContainer(new WindowGame(),800,600,false).start();
	}
}

