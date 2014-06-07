package controleur.automate;

import java.util.LinkedList;
import java.util.List;

import controleur.Controleur;
import src.parser.Parser;
import sun.org.mozilla.javascript.commonjs.module.Require;
import personnages.Personnage;

//Author Malek
public class Automate extends Controleur {

	//ENTREES : CASE_OCCUPE, CASE_LIBRE, SORTIE_TERRAIN, CASE_GHOST
	public final static int CASE_LIBRE = 0;
	public final static int CASE_OCCUPEE = 1;
	public final static int SORTIE_TERRAIN = 2;
	public final static int CASE_GHOST = 3;
	
	//SORTIES : AVANCER, GAUCHE, DROITE, RECHERCHER_PACMAN, SUIVRE_PACMAN (<=> Primitive)
	public final static int AVANCER = 0;
	public final static int GAUCHE = 1;
	public final static int DROIT = 2;
	public final static int RECHERCHER_PACMAN = 3;
	public final static int SUIVRE_PACMAN = 4;
	
	private int tableTransition[][];
	private int tableSortie[][];
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
	public Automate(String fichierXML)//, Personnage p)
	{
		etatsFinals = new LinkedList<Integer>();
		Parser.parse("Fichier.xml",tableTransition,tableSortie,etatInitial,etatsFinals);
		this.nbEtat  = tableTransition.length;
		this.etatCourant = etatInitial;
		this.nbTransition = 0;
		this.nbTransitionMax = 100;
	}
	
	//Automate pre defini utilisé pour un premier test
	public Automate()//String fichierXML)//, Personnage p)
	{
		tableTransition = new int[3][3];
		tableSortie = new int[3][3];
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
		
		this.nbEtat  = 2;
		this.etatCourant = 0;
		this.nbTransition = 0;
		this.nbTransitionMax = 100;
		this.nbEntree = 3; // LIBRE - OCCUPE -SORTIE
		this.nbSortie = 2; //Avance et droit
		this.etatsFinals.add(1); //Ajout de l'etat final n° 1 a la liste des etats finals 
	}
	
	/**
	 * @return Sortie de type automate
	 * @param Entree
	 * @throws Exception
	 * @author malek
	 */
	public int effectuerTransition(int Entree) throws Exception{
		if (nbTransition < nbTransitionMax){
			if (Entree >= 0 && Entree<nbEntree){
				this.etatCourant = tableTransition[this.etatCourant][Entree];
				return tableSortie[this.etatCourant][Entree];
			} else
				throw new Exception("Erreur du numero de l'entree ou de sortie");
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

	public void incrementerTransition(){
		nbTransition++;
	}
	
	public String toString(){
		String res="";
		res += " Etat Courant : " + etatCourant + "\n Etat Initial : " + etatInitial + "\n Etats Finals : " + etatsFinals.toString() + "\n";
		res += "Table de Transition \n";
		for(int i=0; i<nbEtat; i++){
			for(int j=0; j<nbEntree; j++)
				res += " " + tableTransition[i][j];
			res += "\n";
		}
		res += "Table de Sortie \n";
		for(int i=0; i<nbEtat; i++){
			for(int j=0; j<nbEntree; j++)
				res += " " + tableSortie[i][j];
			res += "\n";
		}
		return res;
	}

	public String infoAutomate(){
		String res="";
		res += " Etat Courant : " + etatCourant + "\n Nb Transition : " + nbTransition + "\n Etats Finals : " + etatsFinals.toString() + "\n";
		return res;
	}
}