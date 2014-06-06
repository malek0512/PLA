package controleur.automate;

import java.util.List;

import controleur.Controleur;
import src.parser.Parser;
import sun.org.mozilla.javascript.commonjs.module.Require;
import personnages.Personnage;

public class Automate extends Controleur {

	//ENTREES : CASE_OCCUPE, CASE_LIBRE, SORTIE_TERRAIN, CASE_GHOST
	private static int CASE_OCCUPEE = 0;
	private static int CASE_LIBRE = 1;
	private static int CASE_GHOST = 2;
	private static int SORTIE_TERRAIN = 3;
	
	//SORTIES : AVANCER, GAUCHE, DROITE, RECHERCHER_PACMAN, SUIVRE_PACMAN (<=> Primitive)
	private static int AVANCER = 0;
	private static int GAUCHE = 1;
	private static int DROIT = 2;
	private static int RECHERCHER_PACMAN = 3;
	private static int SUIVRE_PACMAN = 4;
	
	private int tableTransition[][];
	private int tableSortie[][];
	private int etatCourant;
	private int nbEtat;
	private int nbEntree;
	private int nbSortie;
	private int etatInitial;
	private List<Integer>etatsFinals;
	private Personnage personnage;
	
	/*
	 * Prend un fichier XML et remplie les attributs de l'automate
	 */
	public Automate(String fichierXML, Personnage p)
	{
		Parser.parse("Fichier.xml",tableTransition,tableSortie,etatInitial,etatsFinals,nbEntree,nbSortie);
		this.nbEtat  = tableTransition.length;
		this.personnage = p;
		this.etatCourant = etatInitial;
	}
	
	public void effectuerTransition(int Entree, int Sortie){
		if (Entree >= 0 && Entree<nbEntree && Sortie>=0 && Sortie<nbSortie)
			this.etatCourant = tableTransition(Entree, Sortie);
		else
			throw new Exception("Erreur du numero de l'entree ou de sortie");
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
