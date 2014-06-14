package hitBoxManager;

import personnages.CoordonneesFloat;

public class HitBoxManager {

	//taille de la hitBox des personnages
	//on peut voir la valeur comme un cercle 
	static private int hitBox = 1;

	/**
	 * Warning : les tuiles doivent etre carré
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
		int i1 = cord1.x;
		int j1 = cord1.y;
		
		int u1 = cord2.x;
		int v1 = cord2.y;
		
		if(i1 + tilesSize + hitBox < u1 - hitBox)
		{
			//le pixel haut-droite 1 est plus a gauche que le pixel haut-gauche 2
			return false;
		}
		if (i1 - hitBox < u1 + tilesSize + hitBox)
		{
			//le pixel haut-droite 2 est plus a gauche que le pixel haut-gauche 1
			return false;
		}
		if(j1 + hitBox + tilesSize < v1 - hitBox)
		{
			//le pixel bas-droite 1 est plus a gauche que le pixel bas-droite 2
			return false;
		}
		if(j1 - hitBox < v1 + tilesSize + hitBox)
		{
			//le pixel bas-droite 2 est plus a gauche que le pixel bas-droite 1
			return false;
		}
		return true;
		
	}
}
