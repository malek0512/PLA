package controleur.automate;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
	 * @param d
	 * @return True si un ou plusieurs pacman sont dans le rayon 
	 */
	protected boolean dansRayon(float d) {
		int n=0;
		List<Pacman> res = new LinkedList<Pacman>();
		res=pacmanEstDansRayon(auto.getPersonnage().getCoord(),d);
		Iterator<Pacman> i= res.iterator();
		while(i.hasNext())
		{
			n++;
			i.next();
		}
		return n!=0;
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
	
	public boolean isDead(){
		
		return !(auto.getPersonnage()).getisAlive();
	}
	
	public boolean isControled(){
		
		return ((Ghost) auto.getPersonnage()).getControle();
	}
	
}
