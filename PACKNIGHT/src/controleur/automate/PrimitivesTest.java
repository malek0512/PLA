package controleur.automate;

import java.util.LinkedList;
import java.util.List;

import personnages.Coordonnees;
import personnages.Pacman;
import personnages.Personnage;

/**
 * Classe contenant l'ensemble des primitives de test 
 * @author malek
 */
public class PrimitivesTest extends Primitives {

	
	public PrimitivesTest(Automate a) {
		super();
		this.auto = a;
	}

	/**
	 * Fonction de test Automate.PM_DANS_RAYON_X
	 * @param rayon
	 * @return -1 si PM pas dans un cercle de rayon R, Automate.PM_DANS_RAYON_X sinon 
	 */
	protected int dansRayon(int rayon) {
		
		if (Pacman.personnagePresent(auto.getPersonnage().position()))
			return Automate.PM_DANS_RAYON_X;
		return -1;
	}
	
	/**
	 * @return Une ENTREE de l'automate. Selon la configuratoin de la case devant le robot
	 * @author malek
	 * 
	 * rmq : pourquoi ne pas tout simplement considerer SORTIE_TERRAIN comme case OCCUPEE ?
	 * alex
	 */
	public int configCaseDevant() {
		Coordonnees caseDevant = auto.getPersonnage().positionDevant();
		if (caseDevant.x < 0
				|| caseDevant.x > Personnage.getTerrain().getLargeur() - 1
				|| caseDevant.y < 0
				|| caseDevant.y > Personnage.getTerrain().getHauteur() - 1) {
			return Automate.SORTIE_TERRAIN;
		} else if (Personnage.getTerrain().getCase(auto.getPersonnage().getCoord().x, auto.getPersonnage().getCoord().y).isAccessable()) {
			return Automate.CASE_LIBRE;
		} else {
			return Automate.CASE_OCCUPEE;
		}
	}
}
