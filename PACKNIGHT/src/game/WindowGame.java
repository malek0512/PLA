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

import personnages.*;
import structure_terrain.Niveau1;
import structure_terrain.Terrain;

public class WindowGame extends BasicGame {
    private GameContainer container;
	private TiledMap map;
	private float x = 448, y = 320;
	private float xCamera = x, yCamera = y;
	private int direction = 0;
	private boolean moving = false;
	private Animation[] animations = new Animation[8];
	public static int largueur = 28, hauteur = 15;
	public static int tuile_size = 32;
	public static int largueur_map = 28, hauteur_map = 31;
	int taillePersonnage =32;
	int zonePersonnage = 5;
	Music M;
	PacKnight pacman = new PacKnight("j1",1,1,Direction.droite);
	
	public WindowGame() {
        super("PACKNIGHT : THE RETURN");
    }

    public void init(GameContainer container) throws SlickException {
        this.container = container;
        Terrain terrain = new Niveau1();
        terrain.afficher();
        Personnage.initTerrain(terrain);
        this.map = new TiledMap("src/graphisme/main/ressources/map/PACMAN2.tmx");
        SpriteSheet spriteSheet = new SpriteSheet("src/graphisme/main/ressources/map/sprites/PACMAN-SPRITES2.png", taillePersonnage, taillePersonnage);
        this.animations[0] = loadAnimation(spriteSheet, 0, 1, 0);
        this.animations[1] = loadAnimation(spriteSheet, 0, 1, 1);
        this.animations[2] = loadAnimation(spriteSheet, 0, 1, 2);
        this.animations[3] = loadAnimation(spriteSheet, 0, 1, 3);
        this.animations[4] = loadAnimation(spriteSheet, 1, 9, 0);
        this.animations[5] = loadAnimation(spriteSheet, 1, 9, 1);
        this.animations[6] = loadAnimation(spriteSheet, 1, 9, 2);
        this.animations[7] = loadAnimation(spriteSheet, 1, 9, 3);
        Music background = new Music("src/graphisme/main/ressources/music/AllBeat.ogg");
        M = background;
        M.loop();
    }
    

    public void render(GameContainer container, Graphics g) throws SlickException {
        g.translate(container.getWidth() / 2 - this.xCamera, container.getHeight() / 2 - this.yCamera);
        this.map.render(0, 0, 0);
        g.drawAnimation(animations[direction + (moving ? 4 : 0)], pacman.getCoord().pixelX(), pacman.getCoord().pixelY());
    }

    public void update(GameContainer container, int delta) throws SlickException {
        if (pacman.caseDevantDisponible())
        	pacman.avancer();
        else
        {
        if(pacman.getOrientation()==Direction.haut)
        	pacman.setDirection(Direction.droite);
        else if(pacman.getOrientation()==Direction.droite)
        	pacman.setDirection(Direction.bas);
        else if(pacman.getOrientation()==Direction.bas)
        	pacman.setDirection(Direction.gauche);
        else if(pacman.getOrientation()==Direction.gauche)
        	pacman.setDirection(Direction.haut);
        }

        float w = container.getWidth() / 4;
        if (pacman.getCoord().pixelX() > (this.xCamera + w) && (pacman.getCoord().pixelX() + w  <  largueur_map*tuile_size))
        	this.xCamera = pacman.getCoord().pixelX() - w;
        if (pacman.getCoord().pixelX() < (this.xCamera - w) && (pacman.getCoord().pixelX() > w)) 
        	this.xCamera = pacman.getCoord().pixelX() + w;
        float h = container.getHeight() / 4;
        if (pacman.getCoord().pixelY() > (this.yCamera + h) && (pacman.getCoord().pixelY() + h < hauteur_map*tuile_size)) 
        	this.yCamera = pacman.getCoord().pixelY() - h;
        if (pacman.getCoord().pixelY() < (this.yCamera - h) && (pacman.getCoord().pixelY() > h))
        	this.yCamera = pacman.getCoord().pixelY() + h;
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
	    case Input.KEY_UP:    pacman.setDirection(Direction.haut); this.direction= 0; this.moving = true; pacman.isMoving=true; break;
	    case Input.KEY_LEFT:  pacman.setDirection(Direction.gauche);this.direction= 1; this.moving = true;pacman.isMoving=true; break;
	    case Input.KEY_DOWN:  pacman.setDirection(Direction.bas);this.direction= 2; this.moving = true; pacman.isMoving=true; break;
	    case Input.KEY_RIGHT: pacman.setDirection(Direction.droite);this.direction= 3; this.moving = true; pacman.isMoving=true; break;
	    case Input.KEY_ESCAPE:container.exit(); break;
	    case Input.KEY_S:     this.moving = false; break;
	    case Input.KEY_M: if(this.M.playing()) this.M.pause() ;else this.M.resume(); break;
	    
	    
	    }
	}
	
//Useless pour l'instant

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
