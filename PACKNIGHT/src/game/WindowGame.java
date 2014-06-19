package game;

import game.Choix;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;

import game.Menu;
import personnages.*;
import structure_terrain.*;


public class WindowGame extends BasicGameState {

	public static final int ID = 2;
	
	protected static int resolution_x = 800;
	protected static int resolution_y = 600;

	private String CHEMIN_MAP = "src/graphisme/main/ressources/map/";
	private String CHEMIN_MUSIC = "src/graphisme/main/ressources/music/";

	Equipage equip = new Equipage(this);
	
	public String SPRITE_PACMAN_1 = "PACMAN-SPRITES2.png";
	public String SPRITE_PACMAN_2 = "PACMAN-SPRITES2.png";
	public String SPRITE_PACMAN_3 = "PACMAN-SPRITES2.png";
	public String SPRITE_PACMAN_4 = "PACMAN-SPRITES2.png";

	public String SPRITE_GHOST_1 = "Leona.png";
	public String SPRITE_GHOST_2 = "Soraka.png";
	public String SPRITE_GHOST_3 = "Janna.png";
	public String SPRITE_GHOST_4 = "Lulu.png";

	private String MAP = "PACMAN.tmx";
	private String MUSIC = "AllBeat.ogg";

	protected static float xCamera = resolution_x/2;
	protected static float yCamera = resolution_y/2;

	public int direction = 0;
	public static int taille_minimap = 4;

	public static int tuile_size = 32;
	protected static int largueur_map , hauteur_map ;
	public int taillePersonnage =32;


	private StateBasedGame game;
    protected static GameContainer container;
	private TiledMap map;
	private Terrain playground;
	protected static Music M;
	private Image PACGUM,HEART;
	public boolean moving = false;//A VERIFIER SI UTILE


	public int getID()
	{
	      return ID;
	}
	
	
	
    public void init(GameContainer container,StateBasedGame game) throws SlickException 
    {
    	this.game = game;
    	container.setShowFPS(false);
        this.map = new TiledMap(CHEMIN_MAP.concat(MAP));        
        largueur_map =map.getWidth();
        hauteur_map = map.getHeight();
        
        Terrain terrain = new Terrain(largueur_map,hauteur_map, 0);
		HEART = new Image("src/graphisme/main/ressources/map/image/Heart.png");
        PACGUM = new Image("src/graphisme/main/ressources/map/tuiles/pacgomme.png");

		Personnage.initTerrain(terrain);
		equip.init();
        
    	Map.mapToTerrain(terrain, largueur_map, hauteur_map, map);
    	playground = terrain;
    	
    	for(Joueur j: equip.liste){
    		j.sprite();
    	}

    	Music background = new Music(CHEMIN_MUSIC.concat(MUSIC));
        M = background;
        M.loop();
    }
    

    public void render(GameContainer container,StateBasedGame game, Graphics g) throws SlickException {
    	
    	g.translate(container.getWidth() / 2 -  xCamera, container.getHeight() / 2 - ( yCamera));
        
        this.map.render(largueur_map*taille_minimap,0, 2);
        Interface_Joueur.drawPacGum(playground,PACGUM);
        
        for(Joueur j : equip.liste){
        	j.render(g);
        }

        Interface_Joueur.render(g, HEART);
        Minimap(playground, g,-resolution_x/2 + xCamera,-resolution_y/2 + yCamera);
			
    }

    
    public void update(GameContainer container,StateBasedGame game, int delta) throws SlickException {
    	
    	if (Terrain.nb_pacgum == 0) game.enterState(Win.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
    	if (PacKnight.vie == 0) game.enterState(Dead.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
    	
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


	public void keyReleased(int key, char c) {
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

			    }
	    switch (key){
		    case Input.KEY_M: if(M.playing()) M.pause() ;else M.resume(); break;
		   // case Input.KEY_SPACE : game.enterState(Choix.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black)); break;
	    	case Input.KEY_ESCAPE:Menu.container.exit(); break;
	    	case Input.KEY_P: game.enterState(Pause.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));break;
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
        
        for(Joueur j: equip.liste){
        	if (j.p instanceof Ghost){
        		g.setColor(Color.red);
        	} else {
        		g.setColor(Color.orange);
        	}
        	g.fillRect(j.p.getCoord().CasCentre().x*taille_minimap+decalage_x, j.p.getCoord().CasCentre().y*taille_minimap+decalage_y,taille_minimap,taille_minimap);
        }
	}
}
