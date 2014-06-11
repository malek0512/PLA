package controleur.automate;


import personnages.Ghost;
import personnages.Pacman;

/**
 * Classe contenant l'ensemble des primitives de test 
 * @author malek
 */
public class PrimitivesTest extends Primitives {

	//TODO renseigner la doc, bien que petite
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
		
		if (Pacman.personnagePresent(auto.getPersonnage().getCoord()))
			return Automate.PM_DANS_RAYON_X;
		return -1;
	}
	
	/**
	 * @return Une ENTREE de l'automate. Selon la configuratoin de la case devant le robot
	 * @author malek
	 */
	public int configCaseDevant() {
		boolean caseDevantDispo = this.auto.getPersonnage().caseDevantDisponible();
		if (!caseDevantDispo)
			return Automate.CASE_OCCUPEE;
		else
			return Automate.CASE_LIBRE;
	}
	/**
	 * Specification de la méthode dans primitive
	 * Ne s'applique qu'au personnage de l'automate en cours d'utilisation*/
	public boolean estIntersection(){
		
		return estIntersection(auto.getPersonnage().getCoord());
	}
	/**
	 * utilise la méthode pacman est dans croix de primitive avec déjà l'argument personnage.
	 * 
	 * */
	public boolean dansCroix(){
		return pacmanEstDansCroix(auto.getPersonnage().getCoord());
	}
	
	public boolean caseAtteinte(){
		
		return ((Ghost) auto.getPersonnage()).getCompteurAction()==0;

	}
}
