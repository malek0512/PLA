package controleur.automate;


import java.util.List;

import personnages.Ghost;
import personnages.Pacman;


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
	 * @param d
	 * @return True si un ou plusieurs pacman sont dans le rayon 
	 */
	protected boolean dansRayon(int d) {
		List<Pacman> res=pacmanEstDansRayon(auto.getPersonnage().getCoord(),d);
		System.out.println(res.size());
		return res.size()!=0;
		
	}
	
	/**
	 * @return Une ENTREE de l'automate. Selon la configuratoin de la case devant le robot
	 * @author malek
	 */
	public Automate.Entree configCaseDevant() {
		boolean caseDevantDispo = this.auto.getPersonnage().caseDevantDisponible();
		if (!caseDevantDispo)
			return Automate.Entree.CASE_OCCUPEE;
		else
			return Automate.Entree.CASE_LIBRE;
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
	/**
	 * @return : Vrai si le déplacment du fantôme s'est fait d'une case*/
	public boolean caseAtteinte(){
		return auto.getPersonnage().getCoord().CasHG().equals(auto.getPersonnage().getCoord().CasBD());
	}
	/**
	 * @return : Vrai si le fantôme est controllé par le fantôme Lord*/
	public boolean isControled(){
		
		return ((Ghost) auto.getPersonnage()).getControle();
	}
	
	/**
	 * Fonction de test Automate.FM_DANS_RAYON
	 * @param d
	 * @return True si un ou plusieurs pacman sont dans le rayon 
	 */
	protected boolean fmDansRayon(int d) {
		return this.fantomeEstDansRayon(d).size()!=0;
	}
}
