package hitBoxManager;

import personnages.CoordonneesFloat;

public class HitBoxManager {

	//taille de la hitBox des personnages
	//on peut voir la valeur comme un cercle 
	static private int hitBox = 7;

	/**
	 * Warning : les tuiles doivent etre carrées
	 */
	static private int tilesSize = game.WindowGame.tuile_size;
	
	
	/**
	 * calcul si deux personnages se touche
	 * @param cord1 : les coords du premier personnage
	 * @param cord2 : les coords du second personnage
	 * @return vraie si les deux personnages se touche
	 */
	static public boolean personnageHittingPersonnage(CoordonneesFloat cord1,CoordonneesFloat cord2)
	{
		return (Math.abs(cord1.x - cord2.x) < 2*hitBox) && (Math.abs(cord1.y - cord2.y) < 2*hitBox);
	}
}
