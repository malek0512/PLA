package controleur.automate;


import java.util.Iterator;
import java.util.List;

import personnages.Ghost;
import personnages.Pacman;
import personnages.*;


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
		return res.size()!=0;
		
	}
	/**
	 * Methode utilisant la centrale contrairement à dansrayon*/
	protected boolean vu(){
		boolean res=false;
		for(Iterator<Pacman> i = Pacman.liste.iterator();i.hasNext();){
			Pacman pac = i.next();
			if(dansRayon(((Ghost)auto.getPersonnage()).getVision())|| Ghost.central.containsKey(pac)){
				res=true;
			}
		}
		return res;
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
		
		return ((Ghost) auto.getPersonnage()).getEntendEtObei();
	}
	
	/**
	 * Fonction de test Automate.FM_DANS_RAYON. 
	 * Elle met a jour la liste des agresseurs de la princesse, si c'est la princesse qui l'utilise
	 * @param d
	 * @return True si un ou plusieurs pacman sont dans le rayon, du auto.personnage.
	 * @author malek
	 */
	protected boolean fmDansRayon(int d) {
		List violeur = fantomeEstDansRayon(d);
		if (auto.getPersonnage() instanceof PacPrincess)
			((PacPrincess) auto.getPersonnage()).violeurs = violeur;
		return violeur.size()!=0;
	}

	/**
	 * @return Vrai si la princesse, designe le knight, comme son sauveur ie elle s'identifie, lors de l'appel au secours
	 * @author malek
	 */
	public boolean enDetresse(){
		return ((PacKnight) auto.getPersonnage()).princesseEnDetresse!=null;
	}
}
