package view;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import controller.Automate;
import model.personnages.*;
import model.structure_terrain.Direction;

public class Joueur {
	public static List<Joueur> liste = new LinkedList<Joueur>();
	public static SpriteBatch batch;
	
	public Animation pacman ;
	public Sprites pacmanSprite ;
	public float stateTime;
	
	Personnage p;
	Automate auto=null;
	
	public Joueur(String sprite) {
		create(sprite);
		this.p = new PacKnight("mainJoueur", 2, 1, Direction.droite, false);
		
		liste.add(this);
	}
	
	public Joueur(String sprite, Personnage p) {
		create(sprite);
		this.p = p;
		liste.add(this);
	}
	
	public Joueur(String sprite, Personnage p, String automate) {
		create(sprite);
		try{
    		this.auto = new Automate("Automate/"+automate, p);
    	}catch(Exception e)  { System.out.println(e); }
		
		this.p = p;
    	liste.add(this);
	}
		
	private void create (String sprite){
		//Sprite
//		 pacmanSprite.setSize(256, 256);
//		 sprite.setPosition(200, 200);
		batch = new SpriteBatch();
	    stateTime = 0f;
	    pacmanSprite = new Sprites(sprite);
	    

	}
	
	public void render(){
		//Sprite
		try {
			suivant();
		} catch (Exception e) {
			System.out.println(e);
		}
		
        pacman = pacmanSprite.loadAnimation();
        stateTime += Gdx.graphics.getDeltaTime();
        batch.setProjectionMatrix(Camera.camera.combined); //Colle le sprit a la map (ou camera j'en sais rien)
        
		batch.begin();
			batch.draw(pacman.getKeyFrame(stateTime, true), p.coordPix.PixCentre().x, p.coordPix.PixCentre().y); 
		batch.end();
	    System.out.println(p.coordPix.PixCentre().x);
	    System.out.println(p.coordPix.PixCentre().y);
		
	}
	
	public void suivant () throws Exception{
		if(this.auto != null){
			auto.suivant();
		} else {
			if (true) //p.parametrable())
				p.avancer();
			else
				p.avancerAnimation();
		}
	}
}
