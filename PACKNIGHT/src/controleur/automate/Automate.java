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
import src.parser.Parser2;
import src.parser.Quad;
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
	
	PrimitivesTest primitivesTest = new PrimitivesTest(this);
	PrimitivesAction primitivesAction = new PrimitivesAction(this);
	
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
	public Automate(String fichierXML, Personnage p) throws Exception
	{
		
		Parser2 parser = new Parser2(fichierXML);
		this.etatCourant = parser.parseEtatInitiale();
		ArrayList<List<Quad>> liste = parser.parseTableau();
		
		//Initialisations des attributs
		this.nbEtat  = liste.size();
		
		this.nbTransition = 0;
		//this.nbTransitionMax = parser.parseTransitionMax();
		this.personnage = p;
		
		//Initialisation des la table d'entree sortie
		tableTransitionSortie = new TableTransitionSortie(this.nbEtat);
		tableTransitionSortie.initTransitionSortie(liste);
	}
	
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
		
		tableTransition[0][CASE_LIBRE] = 0;
		tableTransition[0][CASE_OCCUPEE] = 0;
		tableTransition[0][SORTIE_TERRAIN] = 1;
		tableTransition[1][CASE_LIBRE] = 1;
		tableTransition[1][CASE_OCCUPEE] = 1;
		tableTransition[1][SORTIE_TERRAIN] = 1;
		
		tableSortie[0][0] = AVANCER;
		tableSortie[0][1] = DROIT;
		tableSortie[0][2] = DROIT;
		tableSortie[1][0] = DROIT;
		tableSortie[1][1] = DROIT;
		tableSortie[1][2] = DROIT;
		
		tableEntreeValide[0][0] = true;
		tableEntreeValide[0][1] = false;
		tableEntreeValide[0][2] = true;
		tableEntreeValide[1][0] = true;
		tableEntreeValide[1][1] = false;
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
	 * 
	 * @return la Sortie, correspondant a l'etatCourant et l'entree passé en parametre
	 * @param Entree
	 * @throws Exception Si le nombre de transition max atteint
	 * @throws Exception Si l'etatCourant n'est pas censé a avoir a effectuer ce test. Ie le booleen de validité du test == false
	 * @author malek
	 */
	protected int effectuerTransition(int Entree) throws Exception{
		if (nbTransition < nbTransitionMax){
			if (tableTransitionSortie.getValide(this.etatCourant, Entree)){
				this.etatCourant = tableTransitionSortie.getEtatSuiv(this.etatCourant, Entree);
				return tableTransitionSortie.getSortie(this.etatCourant, Entree);
			} else
				throw new Exception("Erreur l'etatCourant n'a pas le droit d'effectuer ce test");
		}
		throw new Exception("Erreur le nombre de transition MAX atteint");
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
		System.out.println(nbEntreeValide());
		switch (sortieAutomate) {
		//TODO Ajouter chaque fonction d'action
		//Edit : Le personnage peut faire un demi-pour en une seul fois, les fonctions
		//tourner a droite ou a gauche n'a pas son sens dans un jeux d'arcade
		//comme pacman, quand tu appuie sur bas, quelque soit ta position, tu passe
		//a direction bas ^^
		//utilise la fonction personnage.setDirection(direction);
		//case Automate.AVANCER: personnage.avancerBetement(); break;
		//case Automate.DROIT: personnage.tournerDroite(); break;
		//case Automate.GAUCHE: personnage.tournerGauche(); break;
		
		
		}
		incrementerTransition();
	}


	/**
	 * A l'etatCourant X,
	 * Pour chaque entree de l'automate,
	 * 		si le booleen associé a cette Entree == true, c'est qu'alors l'etat courant possede
	 * 		une transition avec cette Entree (Test).  
	 * Rmq : Dans le cas où l'etatCourantX ne possède aucune entree, On leve exception. (Il s'agirait d'un etat Puit)
	 * Pour chaque Entree de l'etatCourantX, nous appelons, dans l'ordre du parcours de la table de 
	 * Hashage, chacune des fonctions de test
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
		//On parcout l'ensemble des Entree de l'automate, de l'etatCourant
		for (Iterator<Integer> key = entries.keySet().iterator(); key.hasNext(); ){
			Integer Entree = key.next();
			if ( entries.get(Entree).ok ){
				switch ( Entree ){
				//TODO Ajouter chaque fonction de test
				case CASE_LIBRE: return primitivesTest.configCaseDevant(); 
				case CASE_OCCUPEE: return primitivesTest.configCaseDevant();
				case SORTIE_TERRAIN: return primitivesTest.configCaseDevant();
				case PM_DANS_RAYON_X : int X=primitivesTest.dansRayon(3); if (X!=-1) return X; break;
				}
			}
		}
		throw new Exception("Erreur dans l'automate. L'etat courant ne possède aucune entree valide");
	}

	/**
	 * Fonction qui test le nombre d'entree valide de l'etatCourantX, a complementer au fure est a mesure 
	 * des ajout de primitive de test 
	 * En theorie return == 1
	 * @return le nombre d'entree valide de l'etatCourantX
	 * @author malek
	 */
	public int nbEntreeValide() {
		int nb = 0;
		Map<Integer, Triplet> entries = tableTransitionSortie.getEtatAll(this.etatCourant);
		//On parcout l'ensemble des Entree de l'automate, de l'etatCourant
		for (Iterator<Integer> key = entries.keySet().iterator(); key.hasNext(); ){
			Integer Entree = key.next();
			if ( entries.get(Entree).ok ){ 
				switch ( Entree ){ 
				case CASE_LIBRE: if (primitivesTest.configCaseDevant()==CASE_LIBRE) nb++; break;
				case CASE_OCCUPEE: if (primitivesTest.configCaseDevant()==CASE_OCCUPEE) nb++; break;
				case SORTIE_TERRAIN: if (primitivesTest.configCaseDevant()==SORTIE_TERRAIN) nb++; break;
				case PM_DANS_RAYON_X : if (primitivesTest.dansRayon(3)==PM_DANS_RAYON_X) nb++; break;
				}
			}
		}
		return nb;
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
	 * @return True si l'automate est dans un etat final, False sinon
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
	

	
}