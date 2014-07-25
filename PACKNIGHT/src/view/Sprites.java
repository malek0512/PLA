package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Sprites {

	enum Direction {UP, RIGHT, DOWN, LEFT};
	
	static Direction direction = Direction.UP;
    private static final int FRAME_COLS = 2;     
    private static final int FRAME_ROWS = 4;  
    static float vitesseAnimation = 0.25f;

    Animation           walkAnimationRight, walkAnimationDown, walkAnimationLeft, walkAnimationUp;      // #3
    Texture             walkSheet;      // #4
    TextureRegion[]     walkFrames, walkFramesUp, walkFramesLeft, walkFramesDown, walkFramesRight;     // #5
    TextureRegion[][] 	tmp;

    public Sprites(String pngPath){
    	walkSheet = new Texture(Gdx.files.internal(pngPath));
        tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/FRAME_COLS, walkSheet.getHeight()/FRAME_ROWS);              // #10
        
        walkFramesRight= new TextureRegion[] {tmp[0][0], tmp[0][1]};
        walkFramesDown = new TextureRegion[] {tmp[1][0], tmp[1][1]};
        walkFramesLeft = new TextureRegion[] {tmp[2][0], tmp[2][1]};
        walkFramesUp =   new TextureRegion[] {tmp[3][0], tmp[3][1]};
        
        walkAnimationRight = new Animation(vitesseAnimation, walkFramesRight);
        walkAnimationDown = new Animation(vitesseAnimation, walkFramesDown);
        walkAnimationLeft = new Animation(vitesseAnimation, walkFramesLeft);
        walkAnimationUp = new Animation(vitesseAnimation, walkFramesUp);
        
        direction = Direction.UP;
    }
    
    public Animation loadAnimation() {

    	switch (direction){
	        case RIGHT : return walkAnimationRight;
	        case DOWN : return walkAnimationDown;
	        case LEFT : return walkAnimationLeft;
	        case UP : return walkAnimationUp;
        }
		return null;
    	
    }

}
