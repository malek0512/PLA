/**
 * Cette objet contient toutes les infos de notre monde
 * Il fait un peu office de ordonnanceur
 */

package game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

public class WindowGame extends BasicGame {
    private GameContainer container;
	private TiledMap map;
	private float x = 360, y = 320;
	private float xCamera = x, yCamera = y;
	private int direction = 0;
	private boolean moving = false;
	private Animation[] animations = new Animation[8];

	public WindowGame() {
        super("Lesson 1 :: WindowGame");
    }

    public void init(GameContainer container) throws SlickException {
        this.container = container;
        this.map = new TiledMap("src/graphisme/main/ressources/map/PACMAN.tmx");
        SpriteSheet spriteSheet = new SpriteSheet("src//graphisme/main/ressources/map/sprites/PACMAN-SPRITES.png", 56, 56);
        this.animations[0] = loadAnimation(spriteSheet, 0, 1, 0);
        this.animations[1] = loadAnimation(spriteSheet, 0, 1, 1);
        this.animations[2] = loadAnimation(spriteSheet, 0, 1, 2);
        this.animations[3] = loadAnimation(spriteSheet, 0, 1, 3);
        this.animations[4] = loadAnimation(spriteSheet, 1, 9, 0);
        this.animations[5] = loadAnimation(spriteSheet, 1, 9, 1);
        this.animations[6] = loadAnimation(spriteSheet, 1, 9, 2);
        this.animations[7] = loadAnimation(spriteSheet, 1, 9, 3);
        Music background = new Music("src//graphisme/main/ressources/music/Requiem.ogg");
        background.loop();
    }
    
    
	/**
	 * Cette fonction met a jours les informations du jeux
	 */

    public void render(GameContainer container, Graphics g) throws SlickException {
        g.translate(container.getWidth() / 2 - this.x, 0);
            g.translate(0, container.getHeight() / 2 - this.y);   	
        this.map.render(0, 0, 0);
        g.drawAnimation(animations[direction + (moving ? 4 : 0)], x, y);
    }

    public void update(GameContainer container, int delta) throws SlickException {
        if (this.moving) {
            float futurX = getFuturX(delta);
            float futurY = getFuturY(delta);
                this.x = futurX;
                this.y = futurY;
        }
           
    }



	private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
	    Animation animation = new Animation();
	    for (int x = startX; x < endX; x++) {
	        animation.addFrame(spriteSheet.getSprite(x, y), 100);
	    }
	    return animation;
	}



	public void keyReleased(int key, char c) {
	    switch (key) {
	    case Input.KEY_UP:    this.direction = 0; this.moving = true; break;
	    case Input.KEY_LEFT:  this.direction = 1; this.moving = true; break;
	    case Input.KEY_DOWN:  this.direction = 2; this.moving = true; break;
	    case Input.KEY_RIGHT: this.direction = 3; this.moving = true; break;
	    case Input.KEY_ESCAPE:container.exit(); break;
	    }
	}
	


	private float getFuturX(int delta) {
	    float futurX = this.x;
	    switch (this.direction) {
	    case 1: futurX = this.x - .1f * delta; break;
	    case 3: futurX = this.x + .1f * delta; break;
	    }
	    return futurX;
	}
	
	private float getFuturY(int delta) {
	    float futurY = this.y;
	    switch (this.direction) {
	    case 0: futurY = this.y - .1f * delta; break;
	    case 2: futurY = this.y + .1f * delta; break;
	    }
	    return futurY;
	}
	
}
