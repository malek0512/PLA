package automate;

/**
 * Numéro Entree 1 : test boire
 * Entree 0 : test manger
 * 
 * 
 */

import controleur.Controleur;
import src.parser.Parser;
import personnages.Personnage;

public class Automate extends Controleur {

	private int tableEntree[][]; //Etat courant :: Numero d'entre -> Etat suivant
	private String tableSortie[][]; //Etat courant :: Numero d'entre -> Action
	private int etatCourant;
	private Personnage personnage;
	
	/*
	 * Prend un fichier XML et remplie les attributs de l'automate
	 */
	public Automate(String fichierXML)
	{
		
	}
	
}
