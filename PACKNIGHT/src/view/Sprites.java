package view;

import model.structure_terrain.Direction;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Sprites {

	public static final String Pacman = 		"assets/sprites/Pacman.png";
	public static final String Princess = 		"assets/sprites/Princess.png";
	public static final String Aleatoire =		"assets/sprites/Aleatoire.png";
	public static final String Berserk = 		"assets/sprites/Berserk.png";
	public static final String Intercepteur = 	"assets/sprites/Intercepteur.png";
	public static final String Spectrum = 		"assets/sprites/Spectrum.png";
	public static final String Suiveur = 		"assets/sprites/Suiveur.png";
	public static final String animation_sheet ="assets/sprites/animation_sheet.gif";
	
	model.structure_terrain.Direction direction = Direction.haut;
    private static final int FRAME_COLS = 2;     
    private static final int FRAME_ROWS = 4;  
    public static float vitesseAnimation = 0.25f;

    Animation           walkAnimationRight, walkAnimationDown, walkAnimationLeft, walkAnimationUp;
    Texture             walkSheet;
    TextureRegion[]     walkFrames, walkFramesUp, walkFramesLeft, walkFramesDown, walkFramesRight;
    TextureRegion[][] 	tmp;

    public Sprites(String pngPath){
    	walkSheet = new Texture(Gdx.files.internal(pngPath));
        tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/FRAME_COLS, walkSheet.getHeight()/FRAME_ROWS);
        
        walkFramesRight= new TextureRegion[] {tmp[0][0], tmp[0][1]};
        walkFramesDown = new TextureRegion[] {tmp[1][0], tmp[1][1]};
        walkFramesLeft = new TextureRegion[] {tmp[2][0], tmp[2][1]};
        walkFramesUp =   new TextureRegion[] {tmp[3][0], tmp[3][1]};
        
        walkAnimationRight = new Animation(vitesseAnimation, walkFramesRight);
        walkAnimationDown = new Animation(vitesseAnimation, walkFramesDown);
        walkAnimationLeft = new Animation(vitesseAnimation, walkFramesLeft);
        walkAnimationUp = new Animation(vitesseAnimation, walkFramesUp);
        
        direction = Direction.haut;
    }
    
    public Animation loadAnimation() {

    	switch (direction){
	        case droite : return walkAnimationRight;
	        case bas : return walkAnimationDown;
	        case gauche : return walkAnimationLeft;
	        case haut : return walkAnimationUp;
        }
		return null;
    	
    }

    public float getWidth(){
    	return walkSheet.getWidth()/FRAME_COLS;
    }
    
    public float getHeight(){
    	return walkSheet.getHeight()/FRAME_ROWS;
    }
}
