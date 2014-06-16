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
import controleur.automate.Automate;
import personnages.*;
import structure_terrain.*;

//demande de "git add" cette classe afin de pouvor tester :)
import structure_terrain.Terrain;
/*
if (!Pacgomme)
{
terrain.terrain[i][j] = new Case(2);
Terrain.nb_pacgum++;
System.out.println(+Terrain.nb_pacgum);
}7
*/

public class WindowGame extends BasicGame {
	
	
	private String SPRITE_PACMAN_1 = "PACMAN-SPRITES2.png";
	private String SPRITE_PACMAN_2 = "PACMAN-SPRITES2.png";
	private String SPRITE_PACMAN_3 = "PACMAN-SPRITES2.png";
	private String SPRITE_PACMAN_4 = "PACMAN-SPRITES2.png";
	
	private String SPRITE_GHOST_1 = "Leona.png";
	private String SPRITE_GHOST_2 = "Soraka.png";
	private String SPRITE_GHOST_3 = "Janna.png";
	private String SPRITE_GHOST_4 = "Lulu.png";
	
	
	private String MAP = "PACMAN.tmx";
	private String MUSIC = "AllBeat.ogg";
	

	public static int tuile_size = 32;
	public static int largueur_map , hauteur_map ;
	int taillePersonnage =32;
	

	PacKnight PACMAN_1= new PacKnight("J1",1,1,Direction.droite,new CoordonneesFloat(1, 1));
	PacKnight PACMAN_2 = new PacKnight("J2",1,1,Direction.droite,new CoordonneesFloat(1, 1));
	PacKnight PACMAN_3 = new PacKnight("J3",1,1,Direction.droite,new CoordonneesFloat(1, 1));
	PacKnight PACMAN_4 = new PacKnight("J4",1,1,Direction.droite,new CoordonneesFloat(1, 1));

	Ghost GHOST_1 = new Ghost("1", 11, 11, Direction.droite);
	Ghost GHOST_2 = new Ghost("2", 8, 1, Direction.droite);
	Ghost GHOST_3 = new Ghost("3", 1, 5, Direction.droite);
	Ghost GHOST_4 = new Ghost("4", 12, 1, Direction.droite);
	
	Automate aleatoire;
	
	private String CHEMIN_SPRITE = "src/graphisme/main/ressources/map/sprites/";
	private String CHEMIN_MAP = "src/graphisme/main/ressources/map/";
	private String CHEMIN_MUSIC = "src/graphisme/main/ressources/music/";
			
    private GameContainer container;
	private TiledMap map;
	private Terrain playground;
	private float x = 0, y =0;
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
	
	private Music M;
	private Image PACGUM;
	protected CoordonneesFloat coordFloat;
	
	public WindowGame() {
        super("PACKNIGHT : THE RETURN");
    }

    public void init(GameContainer container) throws SlickException {
        this.container = container;
        PACGUM = new Image("src/graphisme/main/ressources/map/tuiles/pacgomme.png");
        this.map = new TiledMap(CHEMIN_MAP.concat(MAP));
        largueur_map =map.getWidth();
        hauteur_map = map.getHeight();
        Terrain terrain = new Terrain(largueur_map,hauteur_map, 0);
        Personnage.initTerrain(terrain);
    	mapToTerrain(terrain);
    	playground = terrain;
    	try{
    		aleatoire = new Automate("Automate/_build/ALEATOIRE.XML",GHOST_1);
    		}catch(Exception e)  {};
    			
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
    	int xBary = (PACMAN_1.getCoord().x + PACMAN_2.getCoord().x + PACMAN_3.getCoord().x + PACMAN_4.getCoord().x)/4;
    	int yBary = (PACMAN_1.getCoord().y + PACMAN_2.getCoord().y + PACMAN_3.getCoord().y + PACMAN_4.getCoord().y)/4;	
    	xCamera = xBary;
    	yCamera = yBary;
    	
        g.translate(container.getWidth() / 2 - this.xCamera, container.getHeight() / 2 - this.yCamera);
        this.map.render(0, 0, 0);
        drawPacGum(playground);
        g.drawAnimation(animations_PACMAN_1[direction + (moving ? 4 : 0)], PACMAN_1.getCoord().x, PACMAN_1.getCoord().y);
        g.drawAnimation(animations_PACMAN_2[direction + (moving ? 4 : 0)], PACMAN_2.getCoord().x, PACMAN_2.getCoord().y);
        g.drawAnimation(animations_PACMAN_3[direction + (moving ? 4 : 0)], PACMAN_3.getCoord().x, PACMAN_3.getCoord().y);
        g.drawAnimation(animations_PACMAN_4[direction + (moving ? 4 : 0)], PACMAN_4.getCoord().x, PACMAN_4.getCoord().y);
        if(GHOST_1.getisAlive()) g.drawAnimation(animations_GHOST_1[direction + (moving ? 4 : 0)], GHOST_1.getCoord().x, GHOST_1.getCoord().y);
        if(GHOST_2.getisAlive()) g.drawAnimation(animations_GHOST_2[direction + (moving ? 4 : 0)], GHOST_2.getCoord().x, GHOST_2.getCoord().y);
        if(GHOST_3.getisAlive()) g.drawAnimation(animations_GHOST_3[direction + (moving ? 4 : 0)], GHOST_3.getCoord().x, GHOST_3.getCoord().y);
        if(GHOST_4.getisAlive()) g.drawAnimation(animations_GHOST_4[direction + (moving ? 4 : 0)], GHOST_4.getCoord().x, GHOST_4.getCoord().y);
        
        System.out.println(+Terrain.nb_pacgum);
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
        
        try
        {
        aleatoire.suivant();
        }
        catch (Exception e) {}
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
		        Image tile_vide = this.map.getTileImage(i,j,this.map.getLayerIndex("logic"));
		        boolean vide = tile_vide != null;
		        if (vide) terrain.terrain[i][j] = new Case(0);
		        else 
		        {
				        Image tile_pacgomme = this.map.getTileImage(i,j,this.map.getLayerIndex("GUM"));
				        boolean Pacgomme = tile_pacgomme == null;
				        if (!Pacgomme)
				        	{
				        	terrain.terrain[i][j] = new Case(2);
				        	Terrain.nb_pacgum++;
				        	System.out.println(+Terrain.nb_pacgum);
				        	}
				        else terrain.terrain[i][j] = new Case(1);
		        }
			}
		}
	}
	
	public void drawPacGum(Terrain terrain){
			for(int i=0;i<largueur_map;i++)
			{
				for(int j=0;j<hauteur_map;j++)
				{
					if(terrain.terrain[i][j].caseValeur() == 2){
					PACGUM.draw(i*tuile_size,j*tuile_size);}
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
