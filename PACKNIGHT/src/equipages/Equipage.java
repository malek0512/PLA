package equipages;


import game.Joueur;
import game.WindowGame;

import org.newdawn.slick.SlickException;

import personnages.Personnage;

public abstract class Equipage {

	
	WindowGame g;
	public Personnage joueurCamera;
	public Personnage joueurFleche;
	public Personnage joueurZQSD;
	public Personnage joueurIJKL;
	public Personnage joueur8456;
	
	public Equipage() {
		this.g = new WindowGame(this);
	}

	public abstract void init() throws SlickException;
	
	public void suivant() throws Exception{
		for(Joueur j:Joueur.liste)
			j.suivant();
	}
}
