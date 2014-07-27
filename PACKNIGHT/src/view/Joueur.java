package view;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Animation;

import controller.Automate;
import model.personnages.*;

public class Joueur {
	public static List<Joueur> liste = new LinkedList<Joueur>();

	public Animation pacmanAnimation ;
	public Sprites pacmanSprite ;
	
	Personnage p;
	Automate auto=null;

	public Joueur(String sprite, Personnage p) {
		this.p = p;
//		pacmanSprite = new Sprites(Sprites.Princess);
		pacmanSprite = new Sprites(sprite);
		liste.add(this);
	}

		
	/**
	 * Methode a executer avant le bach.begin() dans Jeu.java
	 */
	public void render(){
		//L'automate ou le user fait avancer le personnage
		try {
			suivant();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		//Permet de choisir l'annimation de pacman parmis les 4 directions possible
		pacmanSprite.direction = p.direction;
		
		//Charge l'annimation de pacman
        pacmanAnimation = pacmanSprite.loadAnimation();
        
        System.out.println(p.toString());
//        try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	/**
	 * Methode dessinant le sprite du personnage, selon les coordones coordPix du personnage
	 */
	public void draw(){
		Jeu.batch.draw(pacmanAnimation.getKeyFrame(Jeu.stateTime, true), p.coordPix.PixBG().x - pacmanSprite.getWidth()/2, p.coordPix.PixBG().y - pacmanSprite.getHeight()/2);
	}
	
	
	private void suivant () throws Exception{
		if(this.auto != null){
			auto.suivant();
		} else {
			if (p.parametrable())
				p.avancer();
			else
				p.avancerAnimation();
		}
	}
	
//	public Joueur(String sprite) {
//	this.p = new PacKnight("mainJoueur", 1, 1, Direction.gauche, true);
//	create(sprite);
//	liste.add(this);
//}
//
//	public Joueur(String sprite, Personnage p, String automate) {
//		
//		try{
//    		this.auto = new Automate("Automate/"+automate, p);
//    	}catch(Exception e)  { System.out.println(e); }
//		
//		this.p = p;
//		create(sprite);
//		
//    	liste.add(this);
//	}

}
