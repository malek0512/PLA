package game;

import game.TestState2;
import game.TestState3;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.CrossStateTransition;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;

import personnages.*;
import structure_terrain.*;


public class WindowGame extends BasicGameState {

	public static final int ID = 1;
	
	public static int resolution_x = 800;
	public static int resolution_y = 600;

	private String CHEMIN_SPRITE = "src/graphisme/main/ressources/map/sprites/";
	private String CHEMIN_MAP = "src/graphisme/main/ressources/map/";
	private String CHEMIN_MUSIC = "src/graphisme/main/ressources/music/";

	Equipage equip = new Equipage(this);
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

	public static float xCamera = resolution_x/2;
	public static float yCamera = resolution_y/2;

	private int direction = 0;
	public static int taille_minimap = 4;

	public static int tuile_size = 32;
	public static int largueur_map , hauteur_map ;
	private int taillePersonnage =32;


	private StateBasedGame game;
    private GameContainer container;
	private TiledMap map;
	private Terrain playground;
	private Music M;
	private Image PACGUM,HEART,PAUSE_IMAGE;
	private boolean moving = false;//A VERIFIER SI UTILE
	private boolean PAUSE = false;


	private Animation[] animations_PACMAN_1 = new Animation[8];
	private Animation[] animations_PACMAN_2 = new Animation[8];
	private Animation[] animations_PACMAN_3 = new Animation[8];
	private Animation[] animations_PACMAN_4 = new Animation[8];
	private Animation[] animations_GHOST_1 = new Animation[8];
	private Animation[] animations_GHOST_2 = new Animation[8];
	private Animation[] animations_GHOST_3 = new Animation[8];
	private Animation[] animations_GHOST_4 = new Animation[8];
	

	public int getID()
	{
	      return ID;
	}
	
	
	
    public void init(GameContainer container,StateBasedGame game) throws SlickException 
    {
    	this.game = game;
    	container.setShowFPS(false);
        this.container = container;
        this.map = new TiledMap(CHEMIN_MAP.concat(MAP));        
        largueur_map =map.getWidth();
        hauteur_map = map.getHeight();
        
        Terrain terrain = new Terrain(largueur_map,hauteur_map, 0);
		HEART = new Image("src/graphisme/main/ressources/map/image/Heart.png");
        PACGUM = new Image("src/graphisme/main/ressources/map/tuiles/pacgomme.png");
		PAUSE_IMAGE = new Image("src/graphisme/main/ressources/map/image/Pause.jpeg");

		equip.init(terrain);
        
    	Map.mapToTerrain(terrain, largueur_map, hauteur_map, map);
    	playground = terrain;
    	    			
        SpriteSheet spriteSheet_PACMAN_1 = new SpriteSheet(CHEMIN_SPRITE.concat(SPRITE_PACMAN_1), taillePersonnage, taillePersonnage);
        SpriteSheet spriteSheet_PACMAN_2 = new SpriteSheet(CHEMIN_SPRITE.concat(SPRITE_PACMAN_2), taillePersonnage, taillePersonnage);
        SpriteSheet spriteSheet_PACMAN_3 = new SpriteSheet(CHEMIN_SPRITE.concat(SPRITE_PACMAN_3), taillePersonnage, taillePersonnage);
        SpriteSheet spriteSheet_PACMAN_4 = new SpriteSheet(CHEMIN_SPRITE.concat(SPRITE_PACMAN_4), taillePersonnage, taillePersonnage);
        SpriteSheet spriteSheet_GHOST_1 = new  SpriteSheet(CHEMIN_SPRITE.concat(SPRITE_GHOST_1), taillePersonnage, taillePersonnage);
        SpriteSheet spriteSheet_GHOST_2 = new  SpriteSheet(CHEMIN_SPRITE.concat(SPRITE_GHOST_2), taillePersonnage, taillePersonnage);
        SpriteSheet spriteSheet_GHOST_3 = new  SpriteSheet(CHEMIN_SPRITE.concat(SPRITE_GHOST_3), taillePersonnage, taillePersonnage);
        SpriteSheet spriteSheet_GHOST_4 = new  SpriteSheet(CHEMIN_SPRITE.concat(SPRITE_GHOST_4), taillePersonnage, taillePersonnage);

        Sprite.toSprite(animations_PACMAN_1,spriteSheet_PACMAN_1);
        Sprite.toSprite(animations_PACMAN_2,spriteSheet_PACMAN_2);
        Sprite.toSprite(animations_PACMAN_3,spriteSheet_PACMAN_3);
        Sprite.toSprite(animations_PACMAN_4,spriteSheet_PACMAN_4);
        Sprite.toSprite(animations_GHOST_1,spriteSheet_GHOST_1);
        Sprite.toSprite(animations_GHOST_2,spriteSheet_GHOST_2);
        Sprite.toSprite(animations_GHOST_3,spriteSheet_GHOST_3);
        Sprite.toSprite(animations_GHOST_4,spriteSheet_GHOST_4);
        
        Music background = new Music(CHEMIN_MUSIC.concat(MUSIC));
       // M = background;
       //M.loop();
    }
    

    public void render(GameContainer container,StateBasedGame game, Graphics g) throws SlickException {
    	g.translate(container.getWidth() / 2 -  xCamera, container.getHeight() / 2 - ( yCamera));
        
        this.map.render(largueur_map*taille_minimap,0, 2);
        Interface_Joueur.drawPacGum(playground,PACGUM);
        
        if(equip.GHOST_1.getisAlive()) g.drawAnimation(animations_GHOST_1[direction + (moving ? 4 : 0)], equip.GHOST_1.getCoord().x+largueur_map*taille_minimap, equip.GHOST_1.getCoord().y);
        if(equip.GHOST_2.getisAlive()) g.drawAnimation(animations_GHOST_2[direction + (moving ? 4 : 0)], equip.GHOST_2.getCoord().x+largueur_map*taille_minimap, equip.GHOST_2.getCoord().y);
        if(equip.GHOST_3.getisAlive()) g.drawAnimation(animations_GHOST_3[direction + (moving ? 4 : 0)], equip.GHOST_3.getCoord().x+largueur_map*taille_minimap, equip.GHOST_3.getCoord().y);
        if(equip.GHOST_4.getisAlive()) g.drawAnimation(animations_GHOST_4[direction + (moving ? 4 : 0)], equip.GHOST_4.getCoord().x+largueur_map*taille_minimap, equip.GHOST_4.getCoord().y);
        
        g.drawAnimation(animations_PACMAN_1[direction + (moving ? 4 : 0)], equip.PACMAN_1.getCoord().x+largueur_map*taille_minimap, equip.PACMAN_1.getCoord().y);
        g.drawAnimation(animations_PACMAN_2[direction + (moving ? 4 : 0)], equip.PACMAN_2.getCoord().x+largueur_map*taille_minimap, equip.PACMAN_2.getCoord().y);
        g.drawAnimation(animations_PACMAN_3[direction + (moving ? 4 : 0)], equip.PACMAN_3.getCoord().x+largueur_map*taille_minimap, equip.PACMAN_3.getCoord().y);
        g.drawAnimation(animations_PACMAN_4[direction + (moving ? 4 : 0)], equip.PACMAN_4.getCoord().x+largueur_map*taille_minimap, equip.PACMAN_4.getCoord().y);
        
        Interface_Joueur.render(g, HEART);
        Minimap(playground, g,-resolution_x/2 + xCamera,-resolution_y/2 + yCamera);
        
		if(PAUSE==true) Pause.Pause_Game(g,PAUSE_IMAGE);
			
    }

    
    public void update(GameContainer container,StateBasedGame game, int delta) throws SlickException {
		if(!PAUSE) {
	    	if (equip.PACMAN_1.parametrable())
	    		equip.PACMAN_1.avancer();
	    	else
	    		equip.PACMAN_1.avancerAnimation();

	    	float w = container.getWidth() / 4;
	    	if(!(equip.PACMAN_1.getCoord().x -xCamera > resolution_x/2 || equip.PACMAN_1.getCoord().x -xCamera < -resolution_x/2))
	    	{
		        if (equip.PACMAN_1.getCoord().x +largueur_map*taille_minimap > (xCamera + w ) && (equip.PACMAN_1.getCoord().x + w   <  largueur_map*tuile_size))
		        	xCamera = equip.PACMAN_1.getCoord().x - w + largueur_map*taille_minimap;
		        if (equip.PACMAN_1.getCoord().x < (xCamera - w) && (equip.PACMAN_1.getCoord().x > w )) 
		        	xCamera = equip.PACMAN_1.getCoord().x + w;
	    	} 
	    	else if((equip.PACMAN_1.getCoord().x -xCamera > resolution_x/2)) xCamera = largueur_map*tuile_size-resolution_x/2+largueur_map*taille_minimap ;
	    	else if((equip.PACMAN_1.getCoord().x -xCamera < -resolution_x/2)) xCamera = resolution_x/2;

	        float h = container.getHeight() / 4;
	    	if(!(equip.PACMAN_1.getCoord().y -yCamera > resolution_y/2 || equip.PACMAN_1.getCoord().y -yCamera < -resolution_y/2))
	    	{
		        if (equip.PACMAN_1.getCoord().y > (yCamera + h) && (equip.PACMAN_1.getCoord().y + h < hauteur_map*tuile_size)) 
		        	yCamera = equip.PACMAN_1.getCoord().y - h;
		        if (equip.PACMAN_1.getCoord().y < (yCamera - h) && (equip.PACMAN_1.getCoord().y > h))
		        	yCamera = equip.PACMAN_1.getCoord().y + h;
	    	}
	    	else if((equip.PACMAN_1.getCoord().y -yCamera > resolution_y/2)) yCamera = hauteur_map*tuile_size-resolution_y/2;
	    	else if((equip.PACMAN_1.getCoord().y -yCamera < -resolution_y/2)) yCamera = resolution_y/2;
	        
	    	try
	        {
	       equip.suivant();
	       
	        }
	        catch (Exception e) {System.out.println(e);}
	        Ghost.disparitionPacman();
		}
    }


	public void keyReleased(int key, char c) {
		if(PAUSE)
		{
		    switch (key) 
		    {
		    case Input.KEY_P: PAUSE = false; break;
		    }
		}
		else
		{
		    switch (key)
		    	{
			    case Input.KEY_UP:    equip.PACMAN_1.setNextDirection(Direction.haut); this.direction= 0; this.moving = true; break;
			    case Input.KEY_LEFT:  equip.PACMAN_1.setNextDirection(Direction.gauche);this.direction= 1; this.moving = true; break;
			    case Input.KEY_DOWN:  equip.PACMAN_1.setNextDirection(Direction.bas);this.direction= 2; this.moving = true; break;
			    case Input.KEY_RIGHT: equip.PACMAN_1.setNextDirection(Direction.droite);this.direction= 3; this.moving = true; break;

			  //  case Input.KEY_Z:    PACMAN_2.setNextDirection(Direction.haut); this.direction= 0; this.moving = true;  break;
			   // case Input.KEY_Q:  PACMAN_2.setNextDirection(Direction.gauche);this.direction= 1; this.moving = true; break;
			    //case Input.KEY_S:  PACMAN_2.setNextDirection(Direction.bas);this.direction= 2; this.moving = true;  break;
			    //case Input.KEY_D: PACMAN_2.setNextDirection(Direction.droite);this.direction= 3; this.moving = true; break;

			    case Input.KEY_P: PAUSE = true; break;
			    }
	    }
	    switch (key){
	    	case Input.KEY_ESCAPE:container.exit(); break;
		    case Input.KEY_M: if(this.M.playing()) this.M.pause() ;else this.M.resume(); break;
	    }
	    if (key == Input.KEY_2) {
	         GameState target = game.getState(TestState2.ID);
	         
	         final long start = System.currentTimeMillis();
	         CrossStateTransition t = new CrossStateTransition(target) {            
	            public boolean isComplete() {
	               return (System.currentTimeMillis() - start) > 2000;
	            }

	            public void init(GameState firstState, GameState secondState) {
	            }
	         };
	         
	         game.enterState(TestState2.ID, t, new EmptyTransition());
	      }
	      if (key == Input.KEY_3) {
	         game.enterState(TestState3.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
	      }
	}

	
	public void Minimap(Terrain terrain,Graphics g, float decalage_x,float decalage_y){
		for(int i=0;i<largueur_map;i++)
		{
			for(int j=0;j<hauteur_map;j++)
			{

		        if(terrain.terrain[i][j].caseValeur() == 0)
		        {	
		            g.setColor(Color.blue);
		            g.fillRect(i*taille_minimap+decalage_x, j*taille_minimap+decalage_y,taille_minimap,taille_minimap);
		        }
		        else if(terrain.terrain[i][j].caseValeur() == 2)
	        	{
		            g.setColor(Color.yellow);
		            g.fillRect(i*taille_minimap+decalage_x,j*taille_minimap+decalage_y,taille_minimap,taille_minimap);
		        }
		        else 
		        {
		            g.setColor(Color.black);
		            g.fillRect(i*taille_minimap+decalage_x, j*taille_minimap+decalage_y,taille_minimap,taille_minimap);
		        }
			}
		}	
        g.setColor(Color.red);
        g.fillRect(equip.GHOST_1.getCoord().CasCentre().x*taille_minimap+decalage_x, equip.GHOST_1.getCoord().CasCentre().y*taille_minimap+decalage_y,taille_minimap,taille_minimap);
        g.fillRect(equip.GHOST_2.getCoord().CasCentre().x*taille_minimap+decalage_x, equip.GHOST_2.getCoord().CasCentre().y*taille_minimap+decalage_y,taille_minimap,taille_minimap);
        g.fillRect(equip.GHOST_3.getCoord().CasCentre().x*taille_minimap+decalage_x, equip.GHOST_3.getCoord().CasCentre().y*taille_minimap+decalage_y,taille_minimap,taille_minimap);
        g.fillRect(equip.GHOST_4.getCoord().CasCentre().x*taille_minimap+decalage_x, equip.GHOST_4.getCoord().CasCentre().y*taille_minimap+decalage_y,taille_minimap,taille_minimap);
        
        g.setColor(Color.orange);
        g.fillRect(equip.PACMAN_4.getCoord().CasCentre().x*taille_minimap+decalage_x, equip.PACMAN_4.getCoord().CasCentre().y*taille_minimap+decalage_y,taille_minimap,taille_minimap);
        g.fillRect(equip.PACMAN_3.getCoord().CasCentre().x*taille_minimap+decalage_x, equip.PACMAN_3.getCoord().CasCentre().y*taille_minimap+decalage_y,taille_minimap,taille_minimap);
        g.fillRect(equip.PACMAN_2.getCoord().CasCentre().x*taille_minimap+decalage_x, equip.PACMAN_2.getCoord().CasCentre().y*taille_minimap+decalage_y,taille_minimap,taille_minimap);
        g.fillRect(equip.PACMAN_1.getCoord().CasCentre().x*taille_minimap+decalage_x, equip.PACMAN_1.getCoord().CasCentre().y*taille_minimap+decalage_y,taille_minimap,taille_minimap);
	}
}
