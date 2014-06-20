package game;

import java.awt.Window;
import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import personnages.CoordonneesFloat;
import personnages.Direction;
import personnages.Ghost;
import personnages.PacKnight;
import personnages.PacPrincess;
import personnages.Personnage;
import structure_terrain.Terrain;
import controleur.automate.Automate;

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
