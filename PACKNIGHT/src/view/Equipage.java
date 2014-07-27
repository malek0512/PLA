package view;

import model.personnages.PacKnight;
import model.personnages.Personnage;
import model.structure_terrain.Direction;

public abstract class Equipage {

	
	public static Personnage joueurCamera;
	public static Personnage joueurFleche;
	public static Personnage joueurZQSD;
	public static Personnage joueurIJKL;
	public static Personnage joueur8456;

	
	public void render() {
		for(Joueur j:Joueur.liste)
				j.render();
	}
	
	public void draw(){
		for(Joueur j : Joueur.liste)
			j.draw();
	}
	public abstract void create();
}
