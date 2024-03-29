package game;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import controleur.automate.Automate;
import personnages.Pacman;
import personnages.Personnage;
import personnages.Ghost;

public class Joueur {
	public static List<Joueur> liste = new LinkedList<Joueur>();
	private int taillePersonnage;
	private String CHEMIN_SPRITE = "src/graphisme/main/ressources/map/sprites/";
	private String SPRITE;
	private Animation[] animations;
	public WindowGame game;
	private int direction=0;
	private boolean moving = true;
	
	SpriteSheet spriteSheet; 
	Personnage p;
	Automate auto=null;
	private final int NombreClignotement=20;//pair
	
	public Joueur(String SPRITE, WindowGame g, Personnage p) throws SlickException {
		super();
		this.taillePersonnage = g.taillePersonnage;
		this.SPRITE = SPRITE;
		this.game= g;
		this.animations = new Animation[8];
		spriteSheet = new SpriteSheet(CHEMIN_SPRITE.concat(this.SPRITE), taillePersonnage, taillePersonnage);
		this.p = p;
		liste.add(this);
		
	}
	public Joueur(String SPRITE, WindowGame g, Personnage p, String automate) throws SlickException {
		super();
		this.taillePersonnage = g.taillePersonnage;
		this.SPRITE = SPRITE;
		this.game= g;
		this.animations = new Animation[8];
		spriteSheet = new SpriteSheet(CHEMIN_SPRITE.concat(SPRITE), taillePersonnage, taillePersonnage);
		this.p = p;
    	try{
    		this.auto = new Automate("Automate/"+automate, p);
    	}catch(Exception e)  { System.out.println(e); }
    	
    	liste.add(this);
	}
		
	public void render(Graphics g){
		if ((p instanceof Ghost)){
			if(((Ghost) p).getisAlive()) 
				g.drawAnimation(animations[direction + (moving ? 4 : 0)], p.coord.x+WindowGame.largueur_map*WindowGame.taille_minimap, p.coord.y);
		} 
		else{
			if(!((Pacman) p).isInvincible){
				g.drawAnimation(animations[direction + (moving ? 4 : 0)], p.coord.x+WindowGame.largueur_map*WindowGame.taille_minimap, p.coord.y);
				((Pacman) p).compteurClignotement=0;
			}
			else{
				//On dessine le pacman un certain nombre de frame
				if(((Pacman) p).compteurClignotement<(NombreClignotement/2)){
					g.drawAnimation(animations[direction + (moving ? 4 : 0)], p.coord.x+WindowGame.largueur_map*WindowGame.taille_minimap, p.coord.y);
					((Pacman) p).compteurClignotement++;
				}
				else{
					((Pacman) p).compteurClignotement++;
					((Pacman) p).compteurClignotement=((Pacman) p).compteurClignotement%NombreClignotement;
				}
			}
		}
	}
	
	public void sprite(){
		Sprite.toSprite(animations,spriteSheet);
		
	}
	
	public void suivant () throws Exception{
		if(this.auto != null){
			auto.suivant();
		} else {
			if (p.parametrable())
				p.avancer();
			else
				p.avancerAnimation();
		}
		this.direction =  p.direction.ordinal();
	}
}
