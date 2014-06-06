package automate;

import java.util.LinkedList;
import java.util.List;

import controleur.Controleur;
import personnages.Personnage;

public class Ordonnanceur {
	List<Controleur> fap;
	List<Controleur> processus;
	
	public Ordonnanceur() {
		fap = new LinkedList<Controleur>();
		processus = new LinkedList<Controleur>();
	}
}
