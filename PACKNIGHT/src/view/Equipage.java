package view;

import model.personnages.Personnage;

public abstract class Equipage {

	
	public static Personnage joueurCamera;
	public static Personnage joueurFleche;
	public static Personnage joueurZQSD;
	public static Personnage joueurIJKL;
	public static Personnage joueur8456;

	
	public void suivant() {
		for(Joueur j:Joueur.liste)
				j.render();
	}
}
