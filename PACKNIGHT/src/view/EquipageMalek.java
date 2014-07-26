package view;

import model.personnages.PacKnight;
import model.structure_terrain.Direction;

public class EquipageMalek extends Equipage{
	

	public EquipageMalek() {
		PacKnight PACMAN_1= new PacKnight("J1",17,17,Direction.droite, true);
		
		Equipage.joueurCamera =  PACMAN_1;
		Equipage.joueurFleche =  PACMAN_1;
		
		Joueur PM_1 = new Joueur("sprites/Pacman.png", PACMAN_1);
	}
	
}