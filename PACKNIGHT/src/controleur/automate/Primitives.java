package controleur.automate;

import java.util.List;

import personnages.Coordonnees;
import personnages.Direction;
import structure_terrain.Case;

/**
 * Classe contenant l'ensemble de fonction intermedaire permettant l'elaboration des primitives 
 * de test et d'action 
 * @author malek
 */
public class Primitives {
	Automate auto;
	
	/**
	 * @author malek
	 * @param c
	 * @param d
	 * @return
	 */
	protected Coordonnees getCase(Coordonnees c, Direction d){
		Coordonnees coord = new Coordonnees(c);
		switch (d){
		case haut :   coord.y--;  break;
		case bas :    coord.y++;   break;
		case gauche : coord.x--;  break;
		case droite : coord.x++;   break;
		}
		return coord;
	}
	/**
	 * @author malek
	 * @param coord
	 * @return
	 */
	protected boolean estDansLeTerrain(Coordonnees coord){
		return (coord.x < 0
		|| coord.x > auto.getPersonnage().getTerrain().getLargeur() - 1
		|| coord.y < 0
		|| coord.y > auto.getPersonnage().getTerrain().getHauteur() - 1);
	}

	//Fonction non au point, attendre que case soit un objet PM ou GHOST
	//Il faudra pour cela maj la valeur qd le robot est initialiser et se deplace
	protected boolean caseEstPM(Coordonnees c){
		return ((auto.getPersonnage().getTerrain().getCase(c.y, c.x)) instanceof Case); //instanceof GHOST;
	}
	
	/**
	 * Fonction recursive qui renvoie toute les coordonnees voisine dans un certain cercle de rayon R
	 * @require La liste des coordonnees en parametre != NULL 
	 * @author malek
	 */
	protected void dansRayon(Coordonnees position, List<Coordonnees> l, int rayon) {
		if (rayon>=1){
			Coordonnees caseNord, caseSud, caseEst, caseOuest;
			caseNord = getCase(position, Direction.haut);
			caseSud = getCase(position, Direction.bas);
			caseOuest = getCase(position, Direction.gauche);
			caseEst = getCase(position, Direction.droite);
			//l.add(caseNord); l.add(caseSud); l.add(caseEst); l.add(caseOuest);
			dansRayon(caseNord, l, rayon--);  l.add(caseNord);
			dansRayon(caseSud, l, rayon--);   l.add(caseSud);
			dansRayon(caseOuest, l, rayon--); l.add(caseOuest);
			dansRayon(caseEst, l, rayon--);   l.add(caseEst);
			
		}
	}
}
