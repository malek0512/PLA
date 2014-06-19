package game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import personnages.Personnage;
import personnages.Ghost;

public class Joueur {

	private int taillePersonnage;
	private String CHEMIN_SPRITE = "src/graphisme/main/ressources/map/sprites/";
	private String SPRITE;
	private Animation[] animations;
	public WindowGame game;
	SpriteSheet spriteSheet; 
	Personnage p;
	
	public Joueur(String SPRITE, WindowGame g, Personnage p) throws SlickException {
		super();
		this.taillePersonnage = g.taillePersonnage;
		this.SPRITE = SPRITE;
		this.game= g;
		this.animations = new Animation[8];
		spriteSheet = new SpriteSheet(CHEMIN_SPRITE.concat(SPRITE), taillePersonnage, taillePersonnage);
		this.p = p;
		
	}
	
	public void render(Graphics g){
		if ((p instanceof Ghost)){
			if(((Ghost) p).getisAlive()) 
				g.drawAnimation(animations[game.direction + (game.moving ? 4 : 0)], p.getCoord().x+WindowGame.largueur_map*WindowGame.taille_minimap, p.getCoord().y);
		} else
			g.drawAnimation(animations[game.direction + (game.moving ? 4 : 0)], p.getCoord().x+WindowGame.largueur_map*WindowGame.taille_minimap, p.getCoord().y);
	}
	
	public void sprite(){
		Sprite.toSprite(animations,spriteSheet);
	}
	
	public void suivant (){
		
	}
}
