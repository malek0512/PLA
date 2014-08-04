package view;

import model.personnages.Ghost;
import model.personnages.Personnage;

public abstract class Equipage {

	
	public static Personnage joueurCamera;
	public static Personnage joueurFleche;
	public static Personnage joueurZQSD;
	public static Personnage joueurIJKL;
	public static Personnage joueur8456;

	
	public void render() {
		for(Joueur j:Joueur.liste)
			if (j.p.hitting() || j.p instanceof Ghost)
				j.render();
	}
	
	public void draw(){
		for(Joueur j : Joueur.liste){
			//Si le personnage peut etre touche <=> vivant, et si c'est un fantome, alors le dessiner
			if (j.p.hitting() || j.p instanceof Ghost)
				j.draw();
		}
	}
	public abstract void create();
}
