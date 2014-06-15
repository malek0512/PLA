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
	
	
	private String SPRITE_PACMAN_1 = "PACMAN-SPRITES2.png";
	private String SPRITE_PACMAN_2 = "PACMAN-SPRITES2.png";
	private String SPRITE_PACMAN_3 = "PACMAN-SPRITES2.png";
	private String SPRITE_PACMAN_4 = "PACMAN-SPRITES2.png";
	
	private String SPRITE_GHOST_1 = "TEST.png";
	private String SPRITE_GHOST_2 = "TEST.png";
	private String SPRITE_GHOST_3 = "TEST.png";
	private String SPRITE_GHOST_4 = "TEST.png";
	
	
	private String MAP = "PACMAN.tmx";
	private String MUSIC = "AllBeat.ogg";
	
	public static int largueur = 28, hauteur = 15;
	public static int tuile_size = 32;
	public static int largueur_map , hauteur_map ;
	int taillePersonnage =32;
	

	PacKnight PACMAN_1= new PacKnight("J1",1,1,Direction.droite,new CoordonneesFloat(1, 1));
	PacKnight PACMAN_2 = new PacKnight("J2",5,1,Direction.droite,new CoordonneesFloat(1, 1));
	PacKnight PACMAN_3 = new PacKnight("J3",10,11,Direction.droite,new CoordonneesFloat(1, 1));
	PacKnight PACMAN_4 = new PacKnight("J4",10,11,Direction.droite,new CoordonneesFloat(1, 1));

	Ghost GHOST_1 = new Ghost("TEST", 1, 2, Direction.droite);
	Ghost GHOST_2 = new Ghost("TEST", 8, 1, Direction.droite);
	Ghost GHOST_3 = new Ghost("TEST", 1, 5, Direction.droite);
	Ghost GHOST_4 = new Ghost("TEST", 12, 1, Direction.droite);
	
	private String CHEMIN_SPRITE = "src/graphisme/main/ressources/map/sprites/";
	private String CHEMIN_MAP = "src/graphisme/main/ressources/map/";
	private String CHEMIN_MUSIC = "src/graphisme/main/ressources/music/";
			
    private GameContainer container;
	private TiledMap map;
	private float x = 448, y = 320;
	private float xCamera = x, yCamera = y;
	private int direction = 0;
	private boolean moving = false;

	private Animation[] animations_PACMAN_1 = new Animation[8];
	private Animation[] animations_PACMAN_2 = new Animation[8];
	private Animation[] animations_PACMAN_3 = new Animation[8];
	private Animation[] animations_PACMAN_4 = new Animation[8];
	private Animation[] animations_GHOST_1 = new Animation[8];
	private Animation[] animations_GHOST_2 = new Animation[8];
	private Animation[] animations_GHOST_3 = new Animation[8];
	private Animation[] animations_GHOST_4 = new Animation[8];
	
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
    	mapToTerrain(terrain);

    			
        SpriteSheet spriteSheet_PACMAN_1 = new SpriteSheet(CHEMIN_SPRITE.concat(SPRITE_PACMAN_1), taillePersonnage, taillePersonnage);
        SpriteSheet spriteSheet_PACMAN_2 = new SpriteSheet(CHEMIN_SPRITE.concat(SPRITE_PACMAN_2), taillePersonnage, taillePersonnage);
        SpriteSheet spriteSheet_PACMAN_3 = new SpriteSheet(CHEMIN_SPRITE.concat(SPRITE_PACMAN_3), taillePersonnage, taillePersonnage);
        SpriteSheet spriteSheet_PACMAN_4 = new SpriteSheet(CHEMIN_SPRITE.concat(SPRITE_PACMAN_4), taillePersonnage, taillePersonnage);
        SpriteSheet spriteSheet_GHOST_1 = new  SpriteSheet(CHEMIN_SPRITE.concat(SPRITE_GHOST_1), taillePersonnage, taillePersonnage);
        SpriteSheet spriteSheet_GHOST_2 = new  SpriteSheet(CHEMIN_SPRITE.concat(SPRITE_GHOST_2), taillePersonnage, taillePersonnage);
        SpriteSheet spriteSheet_GHOST_3 = new  SpriteSheet(CHEMIN_SPRITE.concat(SPRITE_GHOST_3), taillePersonnage, taillePersonnage);
        SpriteSheet spriteSheet_GHOST_4 = new  SpriteSheet(CHEMIN_SPRITE.concat(SPRITE_GHOST_4), taillePersonnage, taillePersonnage);

        toSprite(animations_PACMAN_1,spriteSheet_PACMAN_1);
        toSprite(animations_PACMAN_2,spriteSheet_PACMAN_2);
        toSprite(animations_PACMAN_3,spriteSheet_PACMAN_3);
        toSprite(animations_PACMAN_4,spriteSheet_PACMAN_4);
        toSprite(animations_GHOST_1,spriteSheet_GHOST_1);
        toSprite(animations_GHOST_2,spriteSheet_GHOST_2);
        toSprite(animations_GHOST_3,spriteSheet_GHOST_3);
        toSprite(animations_GHOST_4,spriteSheet_GHOST_4);
        
        Music background = new Music(CHEMIN_MUSIC.concat(MUSIC));
        M = background;
        M.loop();
    }
    

    public void render(GameContainer container, Graphics g) throws SlickException {
        g.translate(container.getWidth() / 2 - this.xCamera, container.getHeight() / 2 - this.yCamera);
        this.map.render(0, 0, 0);
        g.drawAnimation(animations_PACMAN_1[direction + (moving ? 4 : 0)], PACMAN_1.getCoord().x, PACMAN_1.getCoord().y);
        g.drawAnimation(animations_PACMAN_2[direction + (moving ? 4 : 0)], PACMAN_2.getCoord().x, PACMAN_2.getCoord().y);
        g.drawAnimation(animations_PACMAN_3[direction + (moving ? 4 : 0)], PACMAN_3.getCoord().x, PACMAN_3.getCoord().y);
        g.drawAnimation(animations_PACMAN_4[direction + (moving ? 4 : 0)], PACMAN_4.getCoord().x, PACMAN_4.getCoord().y);
        g.drawAnimation(animations_GHOST_1[direction + (moving ? 4 : 0)], GHOST_1.getCoord().x, GHOST_1.getCoord().y);
        g.drawAnimation(animations_GHOST_2[direction + (moving ? 4 : 0)], GHOST_2.getCoord().x, GHOST_2.getCoord().y);
        g.drawAnimation(animations_GHOST_3[direction + (moving ? 4 : 0)], GHOST_3.getCoord().x, GHOST_3.getCoord().y);
        g.drawAnimation(animations_GHOST_4[direction + (moving ? 4 : 0)], GHOST_4.getCoord().x, GHOST_4.getCoord().y);
    }

    public void update(GameContainer container, int delta) throws SlickException {

    	//if (pacman.caseDevantDisponible())
    		PACMAN_1.avancer();
        /**else
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
    	*/
    	if (PACMAN_2.caseDevantDisponible())
    		PACMAN_2.avancer();
    	else
        {
        if(PACMAN_2.getOrientation()==Direction.haut)
        	PACMAN_2.setDirection(Direction.droite);
        else if(PACMAN_2.getOrientation()==Direction.droite)
        	PACMAN_2.setDirection(Direction.bas);
        else if(PACMAN_2.getOrientation()==Direction.bas)
        	PACMAN_2.setDirection(Direction.gauche);
        else if(PACMAN_2.getOrientation()==Direction.gauche)
        	PACMAN_2.setDirection(Direction.haut);
        }
    	
    	
        float w = container.getWidth() / 4;
        if (PACMAN_1.getCoord().x > (this.xCamera + w) && (PACMAN_1.getCoord().x + w  <  largueur_map*tuile_size))
        	this.xCamera = PACMAN_1.getCoord().x - w;
        if (PACMAN_1.getCoord().x < (this.xCamera - w) && (PACMAN_1.getCoord().x > w)) 
        	this.xCamera = PACMAN_1.getCoord().x + w;
        float h = container.getHeight() / 4;
        if (PACMAN_1.getCoord().y > (this.yCamera + h) && (PACMAN_1.getCoord().y + h < hauteur_map*tuile_size)) 
        	this.yCamera = PACMAN_1.getCoord().y - h;
        if (PACMAN_1.getCoord().y < (this.yCamera - h) && (PACMAN_1.getCoord().y > h))
        	this.yCamera = PACMAN_1.getCoord().y + h;
    }


	public void keyReleased(int key, char c) {
	    switch (key) {
	    case Input.KEY_UP:    PACMAN_1.setNextDirection(Direction.haut); this.direction= 0; this.moving = true; break;
	    case Input.KEY_LEFT:  PACMAN_1.setNextDirection(Direction.gauche);this.direction= 1; this.moving = true; break;
	    case Input.KEY_DOWN:  PACMAN_1.setNextDirection(Direction.bas);this.direction= 2; this.moving = true; break;
	    case Input.KEY_RIGHT: PACMAN_1.setNextDirection(Direction.droite);this.direction= 3; this.moving = true; break;

	    case Input.KEY_Z:    PACMAN_2.setNextDirection(Direction.haut); this.direction= 0; this.moving = true;  break;
	    case Input.KEY_Q:  PACMAN_2.setNextDirection(Direction.gauche);this.direction= 1; this.moving = true; break;
	    case Input.KEY_S:  PACMAN_2.setNextDirection(Direction.bas);this.direction= 2; this.moving = true;  break;
	    case Input.KEY_D: PACMAN_2.setNextDirection(Direction.droite);this.direction= 3; this.moving = true; break;
	    
	    case Input.KEY_ESCAPE:container.exit(); break;
	    case Input.KEY_P:     this.moving = false; break;
	    case Input.KEY_M: if(this.M.playing()) this.M.pause() ;else this.M.resume(); break;
	    
	    }
	}
	
	
	private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
	    Animation animation = new Animation();
	    for (int x = startX; x < endX; x++) {
	        animation.addFrame(spriteSheet.getSprite(x, y), 100);
	    }
	    return animation;
	}

	public void mapToTerrain(Terrain terrain){
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
	}
	
	public void toSprite(Animation animation[],SpriteSheet Personnage){
	
    animation[0] = loadAnimation(Personnage, 0, 1, 0);
    animation[1] = loadAnimation(Personnage, 0, 1, 1);
    animation[2] = loadAnimation(Personnage, 0, 1, 2);
    animation[3] = loadAnimation(Personnage, 0, 1, 3);
    animation[4] = loadAnimation(Personnage, 1, 9, 0);
    animation[5] = loadAnimation(Personnage, 1, 9, 1);
    animation[6] = loadAnimation(Personnage, 1, 9, 2);
    animation[7] = loadAnimation(Personnage, 1, 9, 3);
    
	}
	
}
