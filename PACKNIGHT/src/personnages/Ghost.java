package personnages;

import java.util.Map;

import personnages.SmartGhost.AvisDeRecherche;
import structure_terrain.Terrain;

public class Ghost extends Personnage {

	// Structure qui repertorie l'ensemble des information d'un PM en fuite
	public class AvisDeRecherche {
		boolean repere, Mort;
		Coordonnees coord;
		int nbVu = 0;

		public AvisDeRecherche(Coordonnees c) {
			Mort = false;
			repere = true;
			coord = new Coordonnees(c);
		}
	}

	// Le central repertorie l'ensemble des information des PM en suite
	protected static Map<Pacman, AvisDeRecherche> central;

	public Ghost(String nom, int x, int y, Direction d) {
		super(nom, x, y, d);

	}

}