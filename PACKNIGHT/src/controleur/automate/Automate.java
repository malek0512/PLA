package controleur.automate;

import java.util.List;

import controleur.Controleur;
import src.parser.Parser;
import sun.org.mozilla.javascript.commonjs.module.Require;
import personnages.Personnage;

public class Automate extends Controleur {

	//ENTREES : CASE_OCCUPE, CASE_LIBRE, SORTIE_TERRAIN, CASE_GHOST
	public final static int CASE_OCCUPEE = 0;
	public final static int CASE_LIBRE = 1;
	public final static int CASE_GHOST = 2;
	public final static int SORTIE_TERRAIN = 3;
	
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
		//Parser.parse("Fichier.xml",tableTransition,tableSortie,etatInitial,etatsFinals);
		this.nbEtat  = tableTransition.length;
		//this.personnage = p;
		this.etatCourant = etatInitial;
		this.nbTransition = 0;
		this.nbTransitionMax = 100;
	}
	
	public Automate()//String fichierXML)//, Personnage p)
	{
		tableTransition = new int[3][3];
		tableSortie = new int[3][3];
		
		tableTransition[0][0] = 0;
		tableTransition[0][1] = 1;
		tableTransition[0][2] = 2;
		tableTransition[1][0] = 0;
		tableTransition[1][1] = 0;
		tableTransition[1][2] = 2;
		tableTransition[2][0] = 2;
		tableTransition[2][1] = 2;
		tableTransition[2][2] = 2;
		
		tableSortie[0][0] = AVANCER;
		tableSortie[0][1] = AVANCER;
		tableSortie[0][2] = AVANCER;
		tableSortie[1][0] = AVANCER;
		tableSortie[1][1] = AVANCER;
		tableSortie[1][2] = AVANCER;
		tableSortie[2][0] = AVANCER;
		tableSortie[2][1] = AVANCER;
		tableSortie[2][2] = AVANCER;
		
		this.nbEtat  = tableTransition.length;
		//this.personnage = p;
		this.etatCourant = etatInitial;
		this.nbTransition = 0;
		this.nbTransitionMax = 100;
	}
	public void effectuerTransition(int Entree, int Sortie) throws Exception{
		if (!(nbTransition < nbTransitionMax)){
			if (Entree >= 0 && Entree<nbEntree && Sortie>=0 && Sortie<nbSortie)
				this.etatCourant = tableTransition[Entree][Sortie];
			else
				throw new Exception("Erreur du numero de l'entree ou de sortie");
		}
	}
	public void reinitialiser(){
		etatCourant = 0;
	}
	
	public String toString(){
		String res="";
		res += "Etat Courant : " + etatCourant + "\n Etat Initial : " + etatInitial + "\n Etats Finals : " + etatsFinals.toString();
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
}
