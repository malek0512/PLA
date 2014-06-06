package automate;

import java.util.LinkedList;
import java.util.List;

import personnages.Personnage;

public class Ordonnanceur {
	List<Objet> fap;
	List<Objet> processus;
	
	public Ordonnanceur() {
		fap = new LinkedList<Objet>();
		processus = new LinkedList<Objet>();
	}
}
