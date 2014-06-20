package game;


import org.newdawn.slick.SlickException;

import personnages.Personnage;

public abstract class Equipage {

	
	WindowGame g;
	Personnage joueurFleche;
	Personnage joueurLettre;
	
	public Equipage() {
		this.g = new WindowGame(this);
	}

	public abstract void init() throws SlickException;
	
	public void suivant() throws Exception{
		for(Joueur j:Joueur.liste)
			j.suivant();
	}
}
