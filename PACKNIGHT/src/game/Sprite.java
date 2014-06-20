package game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

public class Sprite {
	
	private static Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
	    Animation animation = new Animation();
	    for (int x = startX; x < endX; x++) {
	        animation.addFrame(spriteSheet.getSprite(x, y), 100);
	    }
	    return animation;
	}
	
	public static void toSprite(Animation animation[],SpriteSheet Personnage){
		
	    animation[0] = loadAnimation(Personnage, 0, 1, 3);
	    animation[1] = loadAnimation(Personnage, 0, 1, 2);
	    animation[2] = loadAnimation(Personnage, 0, 1, 1);
	    animation[3] = loadAnimation(Personnage, 0, 1, 0);
	    animation[4] = loadAnimation(Personnage, 0, 2, 3);
	    animation[5] = loadAnimation(Personnage, 0, 2, 2);
	    animation[6] = loadAnimation(Personnage, 0, 2, 1);
	    animation[7] = loadAnimation(Personnage, 0, 2, 0);
	    
		}

}
