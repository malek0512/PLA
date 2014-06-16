package personnages;

public class RobotIdiot extends Personnage {

	public RobotIdiot(int x, int y, Direction d) {
		super("RobotIdiot", 0, 0, d);
	}

	@Override
	public void gererCollision() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void respawn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void meurtDansDatroceSouffrance() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return Une ENTREE de l'automate. Voir les constante d'ENTREE et SORTIE
	 *         dans classe Automate
	 * @author malek
	 */
//	public int configCaseDevant() {
//		Coordonnees caseDevant = positionDevant();
//		if (caseDevant.x < 0
//				|| caseDevant.x > Personnage.terrain.getLargeur() - 1
//				|| caseDevant.y < 0
//				|| caseDevant.y > Personnage.terrain.getHauteur() - 1) {
//			return Automate.SORTIE_TERRAIN;
//		} else if (terrain.getCase(getCoord().x, getCoord().y).isAccessable()) {
//			return Automate.CASE_LIBRE;
//		} else {
//			return Automate.CASE_OCCUPEE;
//		}
//	}

//	public void suivant() throws Exception {
//		int entreeAutomate = configCaseDevant();
//		//int sortieAutomate = ((Automate) this.c)
//		//		.effectuerTransition(entreeAutomate);
//
//		switch (sortieAutomate) {
//		case Automate.AVANCER:
//			avancerBetement();
//			break;
//		case Automate.DROIT:
//			tournerDroite();
//			break;
//		case Automate.GAUCHE:
//			tournerGauche();
//			break;
//		}
//		this.getControleur().incrementerTransition();
//	}

	/**
	 * Selon l'automate pre defini si, l'etat courant est dans un etat final,
	 * c'est que le robot est sorti
	 * 
	 * @return True si le PM est sortie
	 * @author malek
	 */
//	public boolean estSortie() {
//		return ((Automate) c).isEtatFinal();
//	}

//	public Automate getControleur() {
//		return (Automate) c;
//	}

}
