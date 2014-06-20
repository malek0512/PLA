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

	private String MAP = "PACMAN.tmx";

	public String SPRITE_PACMAN_1 = "Spectrum.png";
	public String SPRITE_PACMAN_2 = "Pacman.png";
	public String SPRITE_PACMAN_3 = "Pacman.png";
	public String SPRITE_PACMAN_4 = "Pacman.png";

	public String SPRITE_GHOST_1 = "Leona.png";
	public String SPRITE_GHOST_2 = "Soraka.png";
	public String SPRITE_GHOST_3 = "Janna.png";
	public String SPRITE_GHOST_4 = "Lulu.png";

	protected static float xCamera = resolution_x / 2;
	protected static float yCamera = resolution_y / 2;

	static int time;
	private StateBasedGame game;
	protected static GameContainer container;
	private TiledMap map;
	private Terrain playground;
	private Image PACGUM, HEART, FOND_INTERFACE;
	public boolean moving = false;// A VERIFIER SI UTILE

	public int direction = 0;
	public static int taille_minimap = 4;

	public static int tuile_size = 32;
	protected static int largueur_map, hauteur_map;
	public int taillePersonnage = 32;
	
	
	static int Choix_Map = 0;

	public int getID() {
		return ID;
	}

	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		
		/*switch (Choix_Map)
		{
		case 0 : MAP = "PACMAN.tmx";break;
		case 1 : MAP = "FATMAP.tmx";break;
		}
		System.out.println("MAP : "+Choix_Map);
		
		this.game = game;
		container.setShowFPS(false);
		this.map = new TiledMap(CHEMIN_MAP.concat(MAP));
		largueur_map = map.getWidth();
		hauteur_map = map.getHeight();

		Terrain terrain = new Terrain(largueur_map, hauteur_map, 0);
		HEART = new Image("src/graphisme/main/ressources/map/image/Heart.png");
		PACGUM = new Image(
				"src/graphisme/main/ressources/map/tuiles/pacgomme.png");
		FOND_INTERFACE = new Image(
				"src/graphisme/main/ressources/map/image/Interface.jpg");

		Personnage.initTerrain(terrain);
		equip.init();

		Map.mapToTerrain(terrain, largueur_map, hauteur_map, map);
		playground = terrain;

		for (Joueur j : Joueur.liste) {
			j.sprite();
		}*/

	}
	
	public void enter(GameContainer container, StateBasedGame game) throws SlickException 
		{
		switch (Choix_Map)
		{
		case 0 : MAP = "PACMAN.tmx";break;
		case 1 : MAP = "FATMAP.tmx";break;
		}
		System.out.println("MAP : "+Choix_Map);
		
		this.game = game;
		container.setShowFPS(false);
		this.map = new TiledMap(CHEMIN_MAP.concat(MAP));
		largueur_map = map.getWidth();
		hauteur_map = map.getHeight();

		Terrain terrain = new Terrain(largueur_map, hauteur_map, 0);
		HEART = new Image("src/graphisme/main/ressources/map/image/Heart.png");
		PACGUM = new Image(
				"src/graphisme/main/ressources/map/tuiles/pacgomme.png");
		FOND_INTERFACE = new Image(
				"src/graphisme/main/ressources/map/image/Interface.jpg");

		Personnage.initTerrain(terrain);
		equip.init();

		Map.mapToTerrain(terrain, largueur_map, hauteur_map, map);
		playground = terrain;

		for (Joueur j : Joueur.liste) {
			j.sprite();
		}
		
		  
		}

	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {

		g.translate(container.getWidth() / 2 - xCamera, container.getHeight()
				/ 2 - (yCamera));

		this.map.render(largueur_map * taille_minimap, 0, 0);
		Interface_Joueur.drawPacGum(playground, PACGUM);

		for (Joueur j : Joueur.liste) {
			j.render(g);
		}

		Interface_Joueur.render(g, HEART, FOND_INTERFACE);
		Minimap(playground, g, -resolution_x / 2 + xCamera, -resolution_y / 2
				+ yCamera);
		if (time < 3000)
			g.drawString("GET READY ", resolution_x / 2, resolution_y / 2);

	}

	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

		if (Terrain.nb_pacgum == 0) {
			//Accueil.Music_Win.play();
			game.enterState(Win.ID, new FadeOutTransition(Color.black),
					new FadeInTransition(Color.black));
		}

		if (PacKnight.vie == 0) {
			//Accueil.Music_Dead.play();
			game.enterState(Dead.ID, new FadeOutTransition(Color.black),
					new FadeInTransition(Color.black));
		}

		time += delta;

		if (!(time < 3000)) {

			if (equip.joueurFleche.parametrable())
				equip.joueurFleche.avancer();
			else
				equip.joueurFleche.avancerAnimation();

			float w = container.getWidth() / 4;
			if (!(equip.joueurFleche.getCoord().x - xCamera > resolution_x / 2 || equip.joueurFleche
					.getCoord().x - xCamera < -resolution_x / 2)) {
				if (equip.joueurFleche.getCoord().x + largueur_map
						* taille_minimap > (xCamera + w)
						&& (equip.joueurFleche.getCoord().x + w < largueur_map
								* tuile_size))
					xCamera = equip.joueurFleche.getCoord().x - w
							+ largueur_map * taille_minimap;
				if (equip.joueurFleche.getCoord().x < (xCamera - w)
						&& (equip.joueurFleche.getCoord().x > w))
					xCamera = equip.joueurFleche.getCoord().x + w;
			} else if ((equip.joueurFleche.getCoord().x - xCamera > resolution_x / 2))
				xCamera = largueur_map * tuile_size - resolution_x / 2
						+ largueur_map * taille_minimap;
			else if ((equip.joueurFleche.getCoord().x - xCamera < -resolution_x / 2))
				xCamera = resolution_x / 2;

			float h = container.getHeight() / 4;
			if (!(equip.joueurFleche.getCoord().y - yCamera > resolution_y / 2 || equip.joueurFleche
					.getCoord().y - yCamera < -resolution_y / 2)) {
				if (equip.joueurFleche.getCoord().y > (yCamera + h)
						&& (equip.joueurFleche.getCoord().y + h < hauteur_map
								* tuile_size))
					yCamera = equip.joueurFleche.getCoord().y - h;
				if (equip.joueurFleche.getCoord().y < (yCamera - h)
						&& (equip.joueurFleche.getCoord().y > h))
					yCamera = equip.joueurFleche.getCoord().y + h;
			} else if ((equip.joueurFleche.getCoord().y - yCamera > resolution_y / 2))
				yCamera = hauteur_map * tuile_size - resolution_y / 2;
			else if ((equip.joueurFleche.getCoord().y - yCamera < -resolution_y / 2))
				yCamera = resolution_y / 2;

			try {
				equip.suivant();

			} catch (Exception e) {
				System.out.println(e);
			}
			Ghost.disparitionPacman();
		}
	}

	public void keyReleased(int key, char c) {
		switch (key) {
		case Input.KEY_UP:
			equip.joueurFleche.setNextDirection(Direction.haut);
			this.direction = 0;
			this.moving = true;
			break;
		case Input.KEY_LEFT:
			equip.joueurFleche.setNextDirection(Direction.gauche);
			this.direction = 1;
			this.moving = true;
			break;
		case Input.KEY_DOWN:
			equip.joueurFleche.setNextDirection(Direction.bas);
			this.direction = 2;
			this.moving = true;
			break;
		case Input.KEY_RIGHT:
			equip.joueurFleche.setNextDirection(Direction.droite);
			this.direction = 3;
			this.moving = true;
			break;

		case Input.KEY_Z:
			equip.joueurLettre.setNextDirection(Direction.haut);
			this.direction = 0;
			this.moving = true;
			break;
		case Input.KEY_Q:
			equip.joueurLettre.setNextDirection(Direction.gauche);
			this.direction = 1;
			this.moving = true;
			break;
		case Input.KEY_S:
			equip.joueurLettre.setNextDirection(Direction.bas);
			this.direction = 2;
			this.moving = true;
			break;
		case Input.KEY_D:
			equip.joueurLettre.setNextDirection(Direction.droite);
			this.direction = 3;
			this.moving = true;
			break;

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
			game.enterState(Pause.ID, new FadeOutTransition(Color.black),
					new FadeInTransition(Color.black));
			break;
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
