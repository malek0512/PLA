package Tests;

import structure_terrain.Case;
import structure_terrain.Terrain;

public class test_terrain {

	public static void main(String[] args) {
		int j;
		Terrain terr=new Terrain(5,7);
		//setCase(int ligne, int colonne, Case ca)
		
		Case mur=new Case(0);
		Case chemin=new Case(1);
		
		for(j=0;j<5;j++){
			terr.setCase(j, 0, mur);
			terr.setCase(j, 6, mur);
		}
		for(j=0;j<5;j++){
			terr.setCase(j, 1, chemin);
			terr.setCase(j, 5, chemin);
		}
		
		for(j=0;j<5;j++){
			terr.setCase(j, 2,chemin);
			terr.setCase(j, 4, chemin);
		}
		terr.setCase(0, 3, chemin);
		terr.setCase(1, 3, mur);
		terr.setCase(2, 3, mur);
		terr.setCase(3, 3, mur);
		terr.setCase(4, 3, chemin);
		
		terr.afficher();
		
	}

}
