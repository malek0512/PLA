package game;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;

import equipages.*;
import game.Menu;
import personnages.*;
import structure_terrain.*;

public class WindowGame extends BasicGameState {

	public static final int ID = 2;

	private String CHEMIN_MAP = "src/graphisme/main/ressources/map/";
	public static int resolution_x = 800;
	public static int resolution_y = 600;

	// EquipageMalek equip = new EquipageMalek(this);
	// EquipageVivienAlex equip = new EquipageVivienAlex(this);
	// Equipage equip = new Equipage(this);
	public WindowGame(Equipage equip) {
		this.equip = equip;
	}

	Equipage equip;

	private String MAP = "FATMAP.tmx";

	public String SPRITE_PACMAN_1 = "Pacman.png";
	public String SPRITE_PACMAN_2 = "Pacman.png";
	public String SPRITE_PACMAN_3 = "Pacman.png";
	public String SPRITE_PACMAN_4 = "Princess.png";

	public String SPRITE_GHOST_1 = "Leona.png";
	public String SPRITE_GHOST_2 = "Lulu.png";
	public String SPRITE_GHOST_3 = "Janna.png";
	public String SPRITE_GHOST_4 = "Spectrum.png";

	protected static float xCamera = resolution_x / 2;
	protected static float yCamera = resolution_y / 2;

	static int time;
	private StateBasedGame game;
	protected static GameContainer container;
	private TiledMap map;
	private Terrain playground;
	private Image PACGUM, HEART, FOND_INTERFACE,PAUSE;
	public boolean moving = true;

	public int direction = 0;
	public static int taille_minimap = 4;

	public static int tuile_size = 32;
	protected static int largueur_map, hauteur_map;
	public int taillePersonnage = 32;
	
	boolean pause = false;
	
	
	public static int Choix_Map = 0;

	public int getID() {
		return ID;
	}

	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
	}
	
	public void enter(GameContainer container, StateBasedGame game) throws SlickException 
		{
		switch (Choix_Map)
		{
		case 0 : MAP = "PACMAN.tmx";PacKnight.vie = 10; break;
		case 1 : MAP = "FATMAP.tmx";PacKnight.vie = 20;PacPrincess.vie = 3; break;
		}
		
		time = 0;
		
		
		this.game = game;
		container.setShowFPS(false);
		this.map = new TiledMap(CHEMIN_MAP.concat(MAP));
		largueur_map = map.getWidth();
		hauteur_map = map.getHeight();

		Terrain terrain = new Terrain(largueur_map, hauteur_map, 0);
		HEART = new Image("src/graphisme/main/ressources/map/image/Heart.png");
		PACGUM = new Image("src/graphisme/main/ressources/map/tuiles/pacgomme.png");
		FOND_INTERFACE = new Image("src/graphisme/main/ressources/map/image/Interface.jpg");
		PAUSE = new Image("src/graphisme/main/ressources/map/image/Pause.jpeg");

		Personnage.initTerrain(terrain);
		equip.init();

		Map.mapToTerrain(terrain, largueur_map, hauteur_map, map);
		playground = terrain;

		for (Joueur j : Joueur.liste) {
			j.sprite();
		}
		
		/*
		 * modification des personnages en fonction de la carte
		 */
		switch(Choix_Map)
		{
		case 0 : Ghost.modeMulti=false; break;
		case 1 : Ghost.modeMulti=true;break;
		}
		Ghost.init();
		  
		}

	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		if(pause)
		{
		      PAUSE.draw(0,0);
		      g.setColor(Color.white);
		      g.drawString("Resume (P)", 250, 100);
		      g.drawString("Main Menu (SPACE)", 250, 150);
		      g.drawString("Quit Game (ESCAPE)", 250, 200);	
		}
		else
		{

		g.translate(container.getWidth() / 2 - xCamera, container.getHeight()
				/ 2 - (yCamera));

		this.map.render(largueur_map * taille_minimap, 0, 2);
		Interface_Joueur.drawPacGum(playground, PACGUM);

		for (Joueur j : Joueur.liste) {
			j.render(g);
		}

		Interface_Joueur.render(g, HEART, FOND_INTERFACE);
		Minimap(playground, g, -resolution_x / 2 + xCamera, -resolution_y / 2
				+ yCamera);
		if (time < 3893)
			g.drawString("GET READY ", resolution_x / 2, resolution_y / 2);
		}

	}

	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

		
		if(!pause)
		{
			
			if (Terrain.nb_pacgum == 0) {
				Accueil.Music_Win.play();
				game.enterState(Win.ID, new FadeOutTransition(Color.black),
						new FadeInTransition(Color.black));
			}
	
			if (PacKnight.vie == 0 || PacPrincess.vie == 0) {
				Accueil.Music_Dead.play();
				game.enterState(Dead.ID, new FadeOutTransition(Color.black),
						new FadeInTransition(Color.black));
			}
	
			time += delta;
	
			if (!(time < 3000)) {
				if(equip.joueurCamera!=null)
				{
				if (equip.joueurCamera.parametrable())
					equip.joueurCamera.avancer();
				else
					equip.joueurCamera.avancerAnimation();
	
				float w = container.getWidth() / 4;
				if (!(equip.joueurCamera.getCoord().x - xCamera > resolution_x / 2 || equip.joueurCamera.getCoord().x - xCamera < -resolution_x / 2)) {
					if (equip.joueurCamera.getCoord().x + largueur_map* taille_minimap > (xCamera + w)&& (equip.joueurCamera.getCoord().x + w < largueur_map* tuile_size))
						xCamera = equip.joueurCamera.getCoord().x - w+ largueur_map * taille_minimap;
					if (equip.joueurCamera.getCoord().x < (xCamera - w)&& (equip.joueurCamera.getCoord().x > w))
						xCamera = equip.joueurCamera.getCoord().x + w;
				} else if ((equip.joueurCamera.getCoord().x - xCamera > resolution_x / 2))
					xCamera = largueur_map * tuile_size - resolution_x / 2+ largueur_map * taille_minimap;
				else if ((equip.joueurCamera.getCoord().x - xCamera < -resolution_x / 2))
					xCamera = resolution_x / 2;
	
				float h = container.getHeight() / 4;
				if (!(equip.joueurCamera.getCoord().y - yCamera > resolution_y / 2 || equip.joueurCamera.getCoord().y - yCamera < -resolution_y / 2)) {
					if (equip.joueurCamera.getCoord().y > (yCamera + h)&& (equip.joueurCamera.getCoord().y + h < hauteur_map* tuile_size))
						yCamera = equip.joueurCamera.getCoord().y - h;
					if (equip.joueurCamera.getCoord().y < (yCamera - h)&& (equip.joueurCamera.getCoord().y > h))
						yCamera = equip.joueurCamera.getCoord().y + h;
				} else if ((equip.joueurCamera.getCoord().y - yCamera > resolution_y / 2))
					yCamera = hauteur_map * tuile_size - resolution_y / 2;
				else if ((equip.joueurCamera.getCoord().y - yCamera < -resolution_y / 2))
					yCamera = resolution_y / 2;
				}
				try {
					equip.suivant();
					
	
				} catch (Exception e) {
					System.out.println(e);
				}
				Ghost.disparitionPacman();
			}
		}
	}

	public void keyReleased(int key, char c) {
		if (!pause)
		{
				
				if(equip.joueurFleche!=null)
				{
				switch (key) 
					{
					case Input.KEY_UP:
						equip.joueurFleche.setNextDirection(Direction.haut);
						break;
					case Input.KEY_LEFT:
						equip.joueurFleche.setNextDirection(Direction.gauche);
						break;
					case Input.KEY_DOWN:
						equip.joueurFleche.setNextDirection(Direction.bas);
						break;
					case Input.KEY_RIGHT:
						equip.joueurFleche.setNextDirection(Direction.droite);
						break;
					}			
				}
			
			if(equip.joueurZQSD!=null)
			{
			switch (key)
				{
				case Input.KEY_Z:
					equip.joueurZQSD.setNextDirection(Direction.haut);
					break;
				case Input.KEY_Q:
					equip.joueurZQSD.setNextDirection(Direction.gauche);
					break;
				case Input.KEY_S:
					equip.joueurZQSD.setNextDirection(Direction.bas);
					break;
				case Input.KEY_D:
					equip.joueurZQSD.setNextDirection(Direction.droite);
					break;
				}
			}
			
			if(equip.joueurIJKL!=null)
				{
				switch (key) {
				case Input.KEY_I:
					equip.joueurIJKL.setNextDirection(Direction.haut);
					break;
				case Input.KEY_J:
					equip.joueurIJKL.setNextDirection(Direction.gauche);
					break;
				case Input.KEY_K:
					equip.joueurIJKL.setNextDirection(Direction.bas);
					break;
				case Input.KEY_L:
					equip.joueurIJKL.setNextDirection(Direction.droite);
					break;
				}
			}
			
			if(equip.joueur8456!=null)
				{
				switch (key) {
				case Input.KEY_NUMPAD8:
					equip.joueur8456.setNextDirection(Direction.haut);
					break;
				case Input.KEY_NUMPAD4:
					equip.joueur8456.setNextDirection(Direction.gauche);
					break;
				case Input.KEY_NUMPAD5:
					equip.joueur8456.setNextDirection(Direction.bas);
					break;
				case Input.KEY_NUMPAD6:
					equip.joueur8456.setNextDirection(Direction.droite);
					break;
				}
			}
	
			switch (key) {
			case Input.KEY_M:
				if (Accueil.Music_WindowGame.playing())
					Accueil.Music_WindowGame.pause();
				else
					Accueil.Music_WindowGame.resume();
				break;
			case Input.KEY_ESCAPE: /* RunExternal.launch("make clean"); */
				Menu.container.exit();
				break;
			case Input.KEY_P:
				if (pause) pause = false;else pause =true;
				break;
			}
		}
		else 
		{
		      switch (key) {
	      		case Input.KEY_SPACE: Accueil.Music_Choix.loop();
	      		if (pause) pause = false;else pause =true;
	      		game.enterState(Choix.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));break;
	      		case Input.KEY_P: if (pause) pause = false;else pause =true;break;
	      		case Input.KEY_M: if(Accueil.Music_WindowGame.playing()) Accueil.Music_WindowGame.pause() ;else Accueil.Music_WindowGame.resume(); break;
	      		case Input.KEY_ESCAPE:Menu.container.exit(); break;
		      }
		}
	}

	public void Minimap(Terrain terrain, Graphics g, float decalage_x,
			float decalage_y) {
		for (int i = 0; i < largueur_map; i++) {
			for (int j = 0; j < hauteur_map; j++) {

				if (terrain.terrain[i][j].caseValeur() == 0) {
					g.setColor(Color.blue);
					g.fillRect(i * taille_minimap + decalage_x, j
							* taille_minimap + decalage_y, taille_minimap,
							taille_minimap);
				} else if (terrain.terrain[i][j].caseValeur() == 2) {
					g.setColor(Color.yellow);
					g.fillRect(i * taille_minimap + decalage_x, j
							* taille_minimap + decalage_y, taille_minimap,
							taille_minimap);
				} else {
					g.setColor(Color.black);
					g.fillRect(i * taille_minimap + decalage_x, j
							* taille_minimap + decalage_y, taille_minimap,
							taille_minimap);
				}
			}
		}

		for (Joueur j : Joueur.liste) {
			if (j.p instanceof Ghost) {
				g.setColor(Color.red);
			} else {
				g.setColor(Color.green);
			}
			g.fillRect(j.p.getCoord().CasCentre().x * taille_minimap
					+ decalage_x, j.p.getCoord().CasCentre().y * taille_minimap
					+ decalage_y, taille_minimap, taille_minimap);

		}
	}
}
