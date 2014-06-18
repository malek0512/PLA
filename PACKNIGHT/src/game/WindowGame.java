package game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
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
import game.*;


public class WindowGame extends BasicGame {

	static int resolution_x = 800;
	static int resolution_y = 600;

	private String CHEMIN_SPRITE = "src/graphisme/main/ressources/map/sprites/";
	private String CHEMIN_MAP = "src/graphisme/main/ressources/map/";
	private String CHEMIN_MUSIC = "src/graphisme/main/ressources/music/";

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

	PacKnight PACMAN_1= new PacKnight("J1",14,10,Direction.droite,new CoordonneesFloat(1, 1));
	//PacKnight PACMAN_2 = new PacKnight("J2",1,1,Direction.droite,new CoordonneesFloat(1, 1));
	//PacKnight PACMAN_3 = new PacKnight("J3",1,1,Direction.droite,new CoordonneesFloat(1, 1));
	//PacKnight PACMAN_4 = new PacKnight("J4",1,1,Direction.droite,new CoordonneesFloat(1, 1));

	Ghost GHOST_1 = new Ghost("1", 1, 1, Direction.droite,new CoordonneesFloat(1, 1));
	Ghost GHOST_2 = new Ghost("2", 1, 1, Direction.droite,new CoordonneesFloat(1, 1));
	//Ghost GHOST_3 = new Ghost("3", 1, 5, Direction.droite,new CoordonneesFloat(1, 1));
	//Ghost GHOST_4 = new Ghost("4", 12, 1, Direction.droite,new CoordonneesFloat(1, 1));

	Automate aleatoire,berserk;

	static float xCamera = resolution_x/2;
	static float yCamera = resolution_y/2;

	private int direction = 0;
	public static int taille_minimap = 4;

	public static int tuile_size = 32;
	public static int largueur_map , hauteur_map ;
	private int taillePersonnage =32;

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

	public WindowGame() {
        super("PACKNIGHT : THE RETURN");
    }

    public void init(GameContainer container) throws SlickException {

    	container.setShowFPS(false);
        this.container = container;
        PACGUM = new Image("src/graphisme/main/ressources/map/tuiles/pacgomme.png");
        this.map = new TiledMap(CHEMIN_MAP.concat(MAP));
        largueur_map =map.getWidth();
        hauteur_map = map.getHeight();
        Terrain terrain = new Terrain(largueur_map,hauteur_map, 0);
        Personnage.initTerrain(terrain);
    	Map.mapToTerrain(terrain, largueur_map, hauteur_map, map);
    	playground = terrain;
    	
    	try{
    		aleatoire = new Automate("Automate/RAMA.xml",GHOST_1);
    		berserk = new Automate("Automate/A_BERSERK.xml",GHOST_2);
    	}catch(Exception e)  
    		{System.out.println(e);};
    			
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
        //M = background;
        //M.loop();
    }
    

    public void render(GameContainer container, Graphics g) throws SlickException {
    	g.translate(container.getWidth() / 2 -  this.xCamera, container.getHeight() / 2 - ( this.yCamera));
    	
		HEART = new Image("src/graphisme/main/ressources/map/image/Heart.png");
        
        this.map.render(largueur_map*taille_minimap,0, 2);
        
        Interface_Joueur.render(g,playground,PACGUM, HEART);
        
        int largueur_interface = largueur_map*taille_minimap;
        int hauteur_interface = hauteur_map*tuile_size;
        
        
        
        g.drawAnimation(animations_PACMAN_1[direction + (moving ? 4 : 0)], PACMAN_1.getCoord().x+largueur_interface, PACMAN_1.getCoord().y);
       // g.drawAnimation(animations_PACMAN_2[direction + (moving ? 4 : 0)], PACMAN_2.getCoord().x, PACMAN_2.getCoord().y);
        //g.drawAnimation(animations_PACMAN_3[direction + (moving ? 4 : 0)], PACMAN_3.getCoord().x, PACMAN_3.getCoord().y);
        //g.drawAnimation(animations_PACMAN_4[direction + (moving ? 4 : 0)], PACMAN_4.getCoord().x, PACMAN_4.getCoord().y);
       if(GHOST_1.getisAlive()) g.drawAnimation(animations_GHOST_1[direction + (moving ? 4 : 0)], GHOST_1.getCoord().x+largueur_interface, GHOST_1.getCoord().y);
        if(GHOST_2.getisAlive()) g.drawAnimation(animations_GHOST_2[direction + (moving ? 4 : 0)], GHOST_2.getCoord().x+largueur_interface, GHOST_2.getCoord().y);
       // if(GHOST_3.getisAlive()) g.drawAnimation(animations_GHOST_3[direction + (moving ? 4 : 0)], GHOST_3.getCoord().x, GHOST_3.getCoord().y);
       // if(GHOST_4.getisAlive()) g.drawAnimation(animations_GHOST_4[direction + (moving ? 4 : 0)], GHOST_4.getCoord().x, GHOST_4.getCoord().y);
        

        
        Minimap(playground, g,-resolution_x/2 + xCamera,-resolution_y/2 + yCamera);
        




        
		if(PAUSE==true)
		{
			PAUSE_IMAGE = new Image("src/graphisme/main/ressources/map/image/Pause.jpeg");
			PAUSE_IMAGE.draw(0,0);
			g.setColor(Color.white);
			g.drawString("Resume (P)", 250, 100);
			g.drawString("Main Menu (I'M WORKING ON IT >.<)", 250, 150);
			g.drawString("Quit Game (ESCAPE)", 250, 250);
		}
    }

    public void update(GameContainer container, int delta) throws SlickException {
		if(!PAUSE) {
	    	if (PACMAN_1.parametrable())
	    		PACMAN_1.avancer();
	    	else
	    		PACMAN_1.avancerAnimation();

	    	float w = container.getWidth() / 4;
	    	if(!(PACMAN_1.getCoord().x -xCamera > resolution_x/2 || PACMAN_1.getCoord().x -xCamera < -resolution_x/2))
	    	{

		        if (PACMAN_1.getCoord().x +largueur_map*taille_minimap > (this.xCamera + w ) && (PACMAN_1.getCoord().x + w   <  largueur_map*tuile_size))
		        	this.xCamera = PACMAN_1.getCoord().x - w + largueur_map*taille_minimap;
		        if (PACMAN_1.getCoord().x < (this.xCamera - w) && (PACMAN_1.getCoord().x > w )) 
		        	this.xCamera = PACMAN_1.getCoord().x + w;
	    	} 
	    	else if((PACMAN_1.getCoord().x -xCamera > resolution_x/2)) this.xCamera = largueur_map*tuile_size-resolution_x/2+largueur_map*taille_minimap ;
	    	else if((PACMAN_1.getCoord().x -xCamera < -resolution_x/2)) this.xCamera = resolution_x/2;

	        float h = container.getHeight() / 4;
	    	if(!(PACMAN_1.getCoord().y -yCamera > resolution_y/2 || PACMAN_1.getCoord().y -yCamera < -resolution_y/2))
	    	{

		        if (PACMAN_1.getCoord().y > (this.yCamera + h) && (PACMAN_1.getCoord().y + h < hauteur_map*tuile_size)) 
		        	this.yCamera = PACMAN_1.getCoord().y - h;
		        if (PACMAN_1.getCoord().y < (this.yCamera - h) && (PACMAN_1.getCoord().y > h))
		        	this.yCamera = PACMAN_1.getCoord().y + h;
	    	}
	    	else if((PACMAN_1.getCoord().y -yCamera > resolution_y/2)) this.yCamera = hauteur_map*tuile_size-resolution_y/2;
	    	else if((PACMAN_1.getCoord().y -yCamera < -resolution_y/2)) this.yCamera = resolution_y/2;


	        try
	        {
	       aleatoire.suivant();
	       // berserk.suivant();
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
			    case Input.KEY_UP:    PACMAN_1.setNextDirection(Direction.haut); this.direction= 0; this.moving = true; break;
			    case Input.KEY_LEFT:  PACMAN_1.setNextDirection(Direction.gauche);this.direction= 1; this.moving = true; break;
			    case Input.KEY_DOWN:  PACMAN_1.setNextDirection(Direction.bas);this.direction= 2; this.moving = true; break;
			    case Input.KEY_RIGHT: PACMAN_1.setNextDirection(Direction.droite);this.direction= 3; this.moving = true; break;

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
        g.setColor(Color.orange);
        g.fillRect(PACMAN_1.getCoord().CasCentre().x*taille_minimap+decalage_x, PACMAN_1.getCoord().CasCentre().y*taille_minimap+decalage_y,taille_minimap,taille_minimap);
        g.setColor(Color.red);
        g.fillRect(GHOST_1.getCoord().CasCentre().x*taille_minimap+decalage_x, GHOST_1.getCoord().CasCentre().y*taille_minimap+decalage_y,taille_minimap,taille_minimap);
        g.fillRect(GHOST_2.getCoord().CasCentre().x*taille_minimap+decalage_x, GHOST_2.getCoord().CasCentre().y*taille_minimap+decalage_y,taille_minimap,taille_minimap);
        
	}
}
