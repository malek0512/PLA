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
	List<Joueur> liste;
	Personnage joueurFleche;
	Personnage joueurLettre;
	
	public Equipage() {
		this.g = new WindowGame(this);
		this.liste = new LinkedList<Joueur>();

	}


	public abstract void init() throws SlickException;
	public abstract void suivant() throws Exception;
	
}
