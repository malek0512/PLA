/**
 * Cette objet contient toutes les infos de notre monde
 * Il fait un peu office de ordonnanceur
 */

package game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

import personnages.*;
import structure_terrain.*;

//demande de "git add" cette classe afin de pouvor tester :)
import structure_terrain.Terrain;
	

public class WindowGame extends BasicGame {
	
	
	private String SPRITE = "PACMAN-SPRITES2.png";
	private String MAP = "FATMAP.tmx";
	private String MUSIC = "AllBeat.ogg";
	
	public static int largueur = 28, hauteur = 15;
	public static int tuile_size = 32;
	public static int largueur_map , hauteur_map ;
	int taillePersonnage =32;
	
	PacKnight pacman = new PacKnight("j1",20,20,Direction.droite);
	PacKnight pacman2 = new PacKnight("j2",21,14,Direction.droite);
	
	private String CHEMIN_SPRITE = "src/graphisme/main/ressources/map/sprites/";
	private String CHEMIN_MAP = "src/graphisme/main/ressources/map/";
	private String CHEMIN_MUSIC = "src/graphisme/main/ressources/music/";
			
    private GameContainer container;
	private TiledMap map;
	private float x = 448, y = 320;
	private float xCamera = x, yCamera = y;
	private int direction = 0;
	private boolean moving = false;
	private Animation[] animations = new Animation[8];
	private Animation[] animations2 = new Animation[8];
	Music M;
	protected CoordonneesFloat coordFloat;
	
	public WindowGame() {
        super("PACKNIGHT : THE RETURN");
    }

    public void init(GameContainer container) throws SlickException {
        this.container = container;

        this.map = new TiledMap(CHEMIN_MAP.concat(MAP));
        largueur_map =map.getWidth();
        hauteur_map = map.getHeight();
        Terrain terrain = new Terrain(largueur_map,hauteur_map);
        Personnage.initTerrain(terrain);
        
		for(int i=0;i<largueur_map;i++)
		{
			for(int j=0;j<hauteur_map;j++)
			{
		        Image tile = this.map.getTileImage(i,j,this.map.getLayerIndex("logic"));
		        boolean vide = tile != null;
		        if (vide) terrain.terrain[i][j] = new Case(0);
		        else terrain.terrain[i][j] = new Case(1);
			}
		}
        terrain.afficher();		
    			
        SpriteSheet spriteSheet = new SpriteSheet(CHEMIN_SPRITE.concat(SPRITE), taillePersonnage, taillePersonnage);
        SpriteSheet spriteSheet2 = new SpriteSheet(CHEMIN_SPRITE.concat(SPRITE), taillePersonnage, taillePersonnage);
        this.animations[0] = loadAnimation(spriteSheet, 0, 1, 0);
        this.animations[1] = loadAnimation(spriteSheet, 0, 1, 1);
        this.animations[2] = loadAnimation(spriteSheet, 0, 1, 2);
        this.animations[3] = loadAnimation(spriteSheet, 0, 1, 3);
        this.animations[4] = loadAnimation(spriteSheet, 1, 9, 0);
        this.animations[5] = loadAnimation(spriteSheet, 1, 9, 1);
        this.animations[6] = loadAnimation(spriteSheet, 1, 9, 2);
        this.animations[7] = loadAnimation(spriteSheet, 1, 9, 3);
        this.animations2[0] = loadAnimation(spriteSheet2, 0, 1, 0);
        this.animations2[1] = loadAnimation(spriteSheet2, 0, 1, 1);
        this.animations2[2] = loadAnimation(spriteSheet2, 0, 1, 2);
        this.animations2[3] = loadAnimation(spriteSheet2, 0, 1, 3);
        this.animations2[4] = loadAnimation(spriteSheet2, 1, 9, 0);
        this.animations2[5] = loadAnimation(spriteSheet2, 1, 9, 1);
        this.animations2[6] = loadAnimation(spriteSheet2, 1, 9, 2);
        this.animations2[7] = loadAnimation(spriteSheet2, 1, 9, 3);
        Music background = new Music(CHEMIN_MUSIC.concat(MUSIC));
        M = background;
        M.loop();
    }
    

    public void render(GameContainer container, Graphics g) throws SlickException {
        g.translate(container.getWidth() / 2 - this.xCamera, container.getHeight() / 2 - this.yCamera);
        this.map.render(0, 0, 0);
        g.drawAnimation(animations[direction + (moving ? 4 : 0)], pacman.getCoord().pixelX(), pacman.getCoord().pixelY());
        g.drawAnimation(animations2[direction + (moving ? 4 : 0)], pacman2.getCoord().pixelX(), pacman2.getCoord().pixelY());
    }

    public void update(GameContainer container, int delta) throws SlickException {
        if (pacman.caseDevantDisponible())
        	pacman.avancer();
        if (pacman2.caseDevantDisponible())
        	pacman2.avancer();
        
    
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
	    case Input.KEY_UP:    pacman.setDirection(Direction.haut); this.direction= 0; this.moving = true; break;
	    case Input.KEY_LEFT:  pacman.setDirection(Direction.gauche);this.direction= 1; this.moving = true; break;
	    case Input.KEY_DOWN:  pacman.setDirection(Direction.bas);this.direction= 2; this.moving = true; break;
	    case Input.KEY_RIGHT: pacman.setDirection(Direction.droite);this.direction= 3; this.moving = true; break;
	    case Input.KEY_ESCAPE:container.exit(); break;
	    case Input.KEY_P:     this.moving = false; break;
	    case Input.KEY_M: if(this.M.playing()) this.M.pause() ;else this.M.resume(); break;
	    
	    case Input.KEY_Z:    pacman2.setDirection(Direction.haut); this.direction= 0; this.moving = true; pacman2.isMoving=true; break;
	    case Input.KEY_Q:  pacman2.setDirection(Direction.gauche);this.direction= 1; this.moving = true;pacman2.isMoving=true; break;
	    case Input.KEY_S:  pacman2.setDirection(Direction.bas);this.direction= 2; this.moving = true; pacman2.isMoving=true; break;
	    case Input.KEY_D: pacman2.setDirection(Direction.droite);this.direction= 3; this.moving = true; pacman2.isMoving=true; break;
	    
	    
	    }
	}
	
}
