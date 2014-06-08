package controleur.automate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import controleur.Controleur;
import controleur.automate.TableTransitionSortie.Triplet;
import src.parser.Parser;
import personnages.Coordonnees;
import personnages.Personnage;

/**
 * Toute primitive de test doit etre ajoutée dans cette classe, et se voit attribuée, une constante en public.
 * Une fois ajoutée en fin de fichier: 
 * 		* Ajouter un "case CONSTANTE : le_nom_de_votre_fct();" (dans la fonction getEntree())
 * 		* Ajouter un "case CONSTANTE : le_nom_de_votre_fct_action();" (dans la fonction suivant())
 * Toute primitive de sortie (d'action), se voit aussi attribué une constante en public, comme ci-dessous.
 * Un automate dispose de toute les fonctions (primitive) de test, renseigné ci dessous. Cependant les primitives
 * d'action (sortie) sont renseigné dans le personnage. Puisque tous les personnage ne possèdent pas les mêmes 
 * facultés. 
 * @author malek
 */
public class Automate extends Controleur {
	
	//ENTREES : CASE_OCCUPE, CASE_LIBRE, SORTIE_TERRAIN, CASE_GHOST
	public final static int CASE_LIBRE = 0;
	public final static int CASE_OCCUPEE = 1;
	public final static int SORTIE_TERRAIN = 2;
	public final static int CASE_GHOST = 3;
	public final static int PM_DANS_RAYON_X = 4;
	
	//SORTIES : AVANCER, GAUCHE, DROITE, RECHERCHER_PACMAN, SUIVRE_PACMAN (<=> Primitive)
	public final static int AVANCER = 0;
	public final static int GAUCHE = 1;
	public final static int DROIT = 2;
	public final static int RECHERCHER_PACMAN = 3;
	public final static int SUIVRE_PACMAN = 4;
	
	TableTransitionSortie tableTransitionSortie;
	
	private int etatCourant;
	private int nbEtat;
	private int nbEntree;
	private int nbSortie;
	private int nbTransition;
	private int nbTransitionMax;
	private int etatInitial;
	private List<Integer>etatsFinals;
	
	/*
	 * Prend un fichier XML et remplie les attributs de l'automate
	 */
//	public Automate(String fichierXML, Personnage p)
//	{
//		int tableTransition[][]; 
//		int tableSortie[][]; 
//		boolean tableEntreeValide[][];
//		
//		etatsFinals = new LinkedList<Integer>();
//		Parser.parse(fichierXML,tableTransition,tableSortie,etatInitial,etatsFinals);
//
//		//Initialisations des attributs
//		this.nbEtat  = tableTransition.length;
//		this.etatCourant = etatInitial;
//		this.nbTransition = 0;
//		this.nbTransitionMax = 100;
//		this.personnage = p;
//		
//		//Initialisation des la table d'entree sortie
//		tableTransitionSortie = new TableTransitionSortie(this.nbEtat);
//		tableTransitionSortie.initTransitionSortie(tableTransition, tableSortie, tableEntreeValide);
//	}
	
	//Automate pre defini utilisé pour un premier test
	public Automate()//String fichierXML)//, Personnage p)
	{
		int tableTransition[][]; 
		int tableSortie[][]; 
		boolean tableEntreeValide[][];
		
		tableTransition = new int[3][3];
		tableSortie = new int[3][3];
		tableEntreeValide = new boolean[3][3];
		etatsFinals = new LinkedList();
		
		tableTransition[0][0] = 0;
		tableTransition[0][1] = 0;
		tableTransition[0][2] = 1;
		tableTransition[1][0] = 1;
		tableTransition[1][1] = 1;
		tableTransition[1][2] = 1;
		
		tableSortie[0][0] = AVANCER;
		tableSortie[0][1] = DROIT;
		tableSortie[0][2] = DROIT;
		tableSortie[1][0] = DROIT;
		tableSortie[1][1] = DROIT;
		tableSortie[1][2] = DROIT;
		
		tableEntreeValide[0][0] = true;
		tableEntreeValide[0][1] = true;
		tableEntreeValide[0][2] = true;
		tableEntreeValide[1][0] = true;
		tableEntreeValide[1][1] = true;
		tableEntreeValide[1][2] = true;
		
		this.nbEtat  = 2;
		this.etatCourant = 0;
		this.nbTransition = 0;
		this.nbTransitionMax = 100;
		this.nbEntree = 3; // LIBRE - OCCUPE -SORTIE
		this.nbSortie = 2; //Avance et droit
		this.etatsFinals.add(1); //Ajout de l'etat final n° 1 a la liste des etats finals 
		//Initialisation des la table d'entree sortie
		tableTransitionSortie = new TableTransitionSortie(this.nbEtat);
		tableTransitionSortie.initTransitionSortie(tableTransition, tableSortie, tableEntreeValide);
	}
	
	/**
	 * @return Sortie de type automate
	 * @param Entree
	 * @throws Exception
	 * @author malek
	 */
	protected int effectuerTransition(int Entree) throws Exception{
		if (nbTransition < nbTransitionMax){
			if (Entree >= 0 && Entree<nbEntree){
				this.etatCourant = tableTransitionSortie.getEtatSuiv(this.etatCourant, Entree);
				return tableTransitionSortie.getSortie(this.etatCourant, Entree);
			} else
				throw new Exception("Erreur du numero de l'entree ou de sortie");
		}
		return -1;
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

		switch (sortieAutomate) {
		case Automate.AVANCER: personnage.avancerBetement(); break;
		case Automate.DROIT: personnage.tournerDroite(); break;
		case Automate.GAUCHE: personnage.tournerGauche(); break;
		
		}
		incrementerTransition();
	}



	/**
	 * 
	 * @return Entree de type automate, fonction a modifier, au fure et a mesure des ajouts de fonction de test
	 * @author malek
	 */
	public int getEntree(){
		Map<Integer, Triplet> entries = tableTransitionSortie.getEtatAll(this.etatCourant);
		for (Iterator<Integer> key = entries.keySet().iterator(); key.hasNext(); ){
			Integer wa = key.next();
			if ( entries.containsKey(wa) ){//entries.get(key).ok){
				switch ( wa ){ //key.next()){ //key corrspond a la constante ENTREE predefini plus haut
				case CASE_LIBRE: return configCaseDevant(); 
				case CASE_OCCUPEE: return configCaseDevant();
				case SORTIE_TERRAIN: return configCaseDevant();
				}
			}
		}
		return -1;
	}
	
	/**
	 * Reinitialise l'etat courant de l'automate a l'etat initial 0
	 * @author malek
	 */
	public void reinitialiserAutomate(){
		etatCourant = 0;
	}

	
	/**
	 * @return Etat courant
	 * @author malek
	 */
	public int getEtatCourant() {
		return etatCourant;
	}	
	/**
	 * @return True s'il l'automate est dans un etat final, False sinon
	 * @author malek
	 */
	public boolean isEtatFinal() {
		return this.etatsFinals.contains(this.etatCourant);
	}

	protected void incrementerTransition(){
		nbTransition++;
	}
	
	public String toString(){
		String res="";
		res += " Etat Courant : " + etatCourant + "\n Etat Initial : " + etatInitial + "\n Etats Finals : " + etatsFinals.toString() + "\n";
		res += "Table de Transition \n";
		for(int i=0; i<nbEtat; i++){
			for(int j=0; j<nbEntree; j++)
				res += " " + tableTransitionSortie.getEtatSuiv(i, j);
			res += "\n";
		}
		res += "Table de Sortie \n";
		for(int i=0; i<nbEtat; i++){
			for(int j=0; j<nbEntree; j++)
				res += " " + tableTransitionSortie.getSortie(i, j);
			res += "\n";
		}
		return res;
	}

	public String infoAutomate(){
		String res="";
		res += " Etat Courant : " + etatCourant + "\n Nb Transition : " + nbTransition + "\n Etats Finals : " + etatsFinals.toString() + "\n";
		return res;
	}

	/**************************************************************/
	/******************** FONCTIONS DE TEST ***********************/
	/**************************************************************/
	
	/**
	 * @return Une ENTREE de l'automate. Selon la configuratoin de la case devant le robot
	 * @author malek
	 */
	public int configCaseDevant() {
		Coordonnees caseDevant = this.personnage.positionDevant();
		if (caseDevant.x < 0
				|| caseDevant.x > Personnage.getTerrain().getLargeur() - 1
				|| caseDevant.y < 0
				|| caseDevant.y > Personnage.getTerrain().getHauteur() - 1) {
			return Automate.SORTIE_TERRAIN;
		} else if (Personnage.getTerrain().getCase(personnage.getCoord().x, personnage.getCoord().y).isAccessable()) {
			return Automate.CASE_LIBRE;
		} else {
			return Automate.CASE_OCCUPEE;
		}
	}
	
}