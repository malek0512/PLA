package controleur.automate;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import controleur.Controleur;
import controleur.automate.TableTransitionSortie.Triplet;
import src.parser.Parser;
import src.parser.Quad;
import personnages.Direction;
import personnages.Ghost;
import personnages.Personnage;

/**
 * Toute primitive de test doit etre ajoutée dans PrimitivesTest, et se voit attribuée, une constante en public, ci dessous.
 * Une fois ajoutée en fin de fichier: 
 * 		* Ajouter un "case CONSTANTE : le_nom_de_votre_fct();" (dans la fonction getEntree())
 * 		* Ajouter un "case CONSTANTE : le_nom_de_votre_fct_action();" (dans la fonction suivant())
 * Toute primitive de sortie (d'action) est ajoutée dans PrimitivesAction , se voit aussi attribué une constante en public, comme ci-dessous.
 * Un automate dispose de toute les fonctions (primitive) de test, renseigné ci dessous. 
 * @author malek
 */
public class Automate extends Controleur {
	
	PrimitivesTest primitivesTest = new PrimitivesTest(this);
	PrimitivesAction primitivesAction = new PrimitivesAction(this);
	
	//ENTREES : CASE_OCCUPE, CASE_LIBRE, SORTIE_TERRAIN, CASE_GHOST
	public final static int CASE_LIBRE = 0;
	public final static int CASE_OCCUPEE = 1;
	public final static int SORTIE_TERRAIN = 2;
	public final static int CASE_GHOST = 3;
	public final static int PM_DANS_RAYON_X = 4;
	public final static int PM_DANS_CROIX = 5;
	public final static int NON_PM_DANS_CROIX = 6;
	public final static int INTERSECTION = 7;
	public final static int NON_INTERSECTION = 8;
	public final static int CASE_ATTEINTE=9;
	public final static int FREE=10;
	public final static int NON_FREE=11;
	public final static int ETOILE=12;
	
	
	//SORTIES : AVANCER, GAUCHE, DROITE, RECHERCHER_PACMAN, SUIVRE_PACMAN (<=> Primitive)
	public final static int AVANCER = 0;
	public final static int GAUCHE = 1;
	public final static int DROIT = 2;
	public final static int HAUT = 3;
	public final static int BAS = 4;
	public final static int DIRECTION_ALEATOIRE = 7;
	public final static int PROCHAINE_DIRECTION = 8;
	public final static int CHEMIN_PLUS_COURT=9;
	public final static int AVANCER_VERS=10;
	public final static int END_LIFE=11;
	public final static int SPAWN=12;
	
	
	TableTransitionSortie tableTransitionSortie;
	
	private String etatCourant;
	private int nbEtat;
//	private int nbEntree;
//	private int nbSortie;
	private int nbTransition;
//	private int nbTransitionMax;
	private String etatInitial;
	private List<String>etatsFinals;
	private List<String>etatsBloquants;
	
	/*
	 * Prend un fichier XML et remplie les attributs de l'automate
	 */
	public Automate(String fichierXML, Personnage p) throws Exception
	{
		
		Parser parser = new Parser(fichierXML);
		
		Map<String,List<Quad>> liste = parser.parseTableau();
		etatInitial = parser.parseEtatInitiale();
		etatsFinals = parser.parseEtatFinal();
//		System.out.println(etatsFinals);
		etatsBloquants = parser.parseEtatBloquant();
		//Initialisations des attributs
		this.nbEtat  = liste.size();
//		this.nbTransition = 0;
//		this.nbTransitionMax = Integer.MAX_VALUE; //A Virer
		this.personnage = p;
		this.etatCourant = etatInitial;
		
		
		//Initialisation des la table d'entree sortie
		tableTransitionSortie = new TableTransitionSortie();
		tableTransitionSortie.initTransitionSortie(liste);
	}
	
	
	/**
	 * 
	 * @return la Sortie, correspondant a l'etatCourant et l'entree passé en parametre
	 * @param Entree
	 * @author malek
	 * @throws Exception 
	 */
	protected int effectuerTransition(int Entree) throws Exception {
//		if (nbTransition < nbTransitionMax){
//			if (tableTransitionSortie.getValide(this.etatCourant, Entree)){
//		System.out.println("Entreeee " + Entree);
				this.etatCourant = tableTransitionSortie.getEtatSuiv(this.etatCourant, Entree);
				return tableTransitionSortie.getSortie(this.etatCourant, Entree);
//			} else
//				throw new Exception("Erreur l'etatCourant n'a pas le droit d'effectuer ce test");
//		}
//		throw new Exception("Erreur le nombre de transition MAX atteint");
	}

	/**
	 * Fonction effectuant l'action suivante, selon l'entree et la sortie de l'automate.
	 * A modifier au fure et a mesure des ajout de fonction d'actions des personnages.
	 * Ne pas oublier de CASTER selon le type de votre personnage, afin d'avoir accès aux actions qui y sont décrites 
	 * @throws Exception
	 * @author malek
	 */
	public void suivant() throws Exception {
		int entreeAutomate = getEntree();
		int sortieAutomate = effectuerTransition(entreeAutomate);
//		System.out.println(nbEntreeValide());
		switch (sortieAutomate) {
		//TODO Ajouter chaque fonction d'action
		case Automate.AVANCER: personnage.avancer(); break;
		case Automate.DROIT: personnage.setDirection(Direction.droite); break;
		case Automate.GAUCHE: personnage.setDirection(Direction.gauche); break;
		case Automate.HAUT: personnage.setDirection(Direction.haut); break;
		case Automate.BAS: personnage.setDirection(Direction.bas); break;
		case Automate.DIRECTION_ALEATOIRE: primitivesAction.setDirectionAleatoire(getPersonnage()); break;
		case Automate.PROCHAINE_DIRECTION: primitivesAction.prochaineDirection(getPersonnage());break;
		case Automate.CHEMIN_PLUS_COURT: primitivesAction.directionCheminPlusCourt(getPersonnage()); break;
		case Automate.AVANCER_VERS: primitivesAction.avancerVersPoint(); break;
		case Automate.SPAWN:personnage.respawn();break;
		case Automate.RIEN:primitivesAction.pass(); break;
		
		}
		incrementerTransition();
	}


	/**
	 * A l'etatCourant X,
	 * Pour chaque entree de l'automate,
	 * 		On evalue appelle la fonction de test
	 * 		  
	 * Rmq : Dans le cas où l'etatCourantX ne possède aucune entree, On leve exception. (Il s'agirait d'un etat Puit)
	 * Pour chaque Entree de l'etatCourantX, nous appelons, dans l'ordre du parcours de la table de 
	 * Hashage (l'ordre des entier "Constante"), chacune des fonctions de test
	 * 
	 * Theoriquement, parmis toute les entrees a prtir de l'etatCourantX, une et seulement une fonction 
	 * est verifiée. La fonction getEntree() ne le verifie pas ! 
	 * 
	 * Elle renvoie l'Entree de l'automate, leve exception si aucune Entree n'est verifiée. 
	 * @return Entree de type automate, fonction a modifier, au fure et a mesure des ajouts de fonction de test
	 * @author malek
	 * @throws Exception 
	 */ 
	public int getEntree() throws Exception{
		Map<Integer, Triplet> entries = tableTransitionSortie.getEtatAll(this.etatCourant);
		//On parcours l'ensemble des Entree de l'automate, de l'etatCourant
		for (Iterator<Integer> key = entries.keySet().iterator(); key.hasNext(); ){
			Integer Entree = key.next();
			//if ( entries.get(Entree).ok ){
				switch ( Entree ){
				//TODO Ajouter chaque fonction de test
				case CASE_LIBRE: if (primitivesTest.configCaseDevant()==CASE_LIBRE) return CASE_LIBRE; break;
				case CASE_OCCUPEE: if (primitivesTest.configCaseDevant()==CASE_OCCUPEE) return CASE_OCCUPEE; break;
				case SORTIE_TERRAIN: if (primitivesTest.configCaseDevant()==SORTIE_TERRAIN) return SORTIE_TERRAIN;break;
				case PM_DANS_RAYON_X : if(primitivesTest.dansRayon(((Ghost) getPersonnage()).getVision())) return PM_DANS_RAYON_X; break;
				case INTERSECTION: if(primitivesTest.estIntersection()) return INTERSECTION; break;
				case NON_INTERSECTION: if(!primitivesTest.estIntersection()) return NON_INTERSECTION; break;
				case PM_DANS_CROIX: if(primitivesTest.dansCroix()) return PM_DANS_CROIX; break;
				case NON_PM_DANS_CROIX: if(!primitivesTest.dansCroix()) return NON_PM_DANS_CROIX; break;
				case FREE: if(!primitivesTest.isControled()) return FREE; break;
				case NON_FREE: if(primitivesTest.isControled()) return NON_FREE; break;
				case ETOILE: return ETOILE;
			//	}
			}
		}
		
		//Affichage des ENTREES dans le cas où : Aucune entree n'est valide  
		String Erreur = "C'est l'ETAT " + this.etatCourant + ", voici toutes mes ENTREES : ";
		for (Iterator<Integer> key = entries.keySet().iterator(); key.hasNext(); ){
			Integer Entree = key.next(); Erreur += Entree.intValue();
		}
		System.out.println(Erreur);
		throw new Exception("Erreur dans l'automate. L'etat courant ne possède aucune entree valide. Manque t-il une transition ?");
	}

	/**
	 * Fonction qui test le nombre d'entree valide de l'etatCourantX, a complementer au fure est a mesure 
	 * des ajout de primitive de test 
	 * En theorie return == 1
	 * @return le nombre d'entree valide de l'etatCourantX
	 * @author malek
	 * @throws Exception 
	 */
	public int nbEntreeValide() throws Exception {
		int nb = 0;
		Map<Integer, Triplet> entries = tableTransitionSortie.getEtatAll(this.etatCourant);
		//On parcout l'ensemble des Entree de l'automate, de l'etatCourant
		for (Iterator<Integer> key = entries.keySet().iterator(); key.hasNext(); ){
			Integer Entree = key.next();
			//if ( entries.get(Entree).ok ){ 
				switch ( Entree ){ 
				case CASE_LIBRE: if (primitivesTest.configCaseDevant()==CASE_LIBRE) nb++; break;
				case CASE_OCCUPEE: if (primitivesTest.configCaseDevant()==CASE_OCCUPEE) nb++; break;
				case SORTIE_TERRAIN: if (primitivesTest.configCaseDevant()==SORTIE_TERRAIN) nb++; break;
				case PM_DANS_RAYON_X : if (primitivesTest.dansRayon(3)) nb++; break;
				}
			//}
		}
		return nb;
	}

	/**
	 * Reinitialise l'etat courant de l'automate a l'etat initial 0
	 * @author malek
	 */
	public void reinitialiserAutomate(){
		etatCourant = etatInitial;
	}
	
	/**
	 * @return Etat courant
	 * @author malek
	 */
	public String getEtatCourant() {
		return etatCourant;
	}	

	/**
	 * @return True si l'automate est dans un etat final, False sinon
	 * @author malek
	 */
	public boolean isEtatFinal() {
		return this.etatsFinals.contains(this.etatCourant);
	}

	protected void incrementerTransition(){
		nbTransition++;
	}
	
	public String tableTransSortie(){
		return tableTransitionSortie.toString();
	}
	public String infoAutomate(){
		String res="";
		res += " Etat Courant : " + etatCourant + "\n Nb Transition : " + nbTransition + "\n Etats Finals : " + etatsFinals.toString() + "\n";
		return res;
	}

}