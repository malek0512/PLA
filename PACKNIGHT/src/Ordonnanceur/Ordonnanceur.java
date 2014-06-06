package Ordonnanceur;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import controleur.Controleur;
import personnages.Personnage;

public class Ordonnanceur {
	List<Controleur> fap;
	List<Controleur> processus;
	
	public Ordonnanceur(List<Controleur> c) {
		fap = new LinkedList<Controleur>(c);
		processus = new ArrayList<Controleur>(c);
	}
	
	public Controleur election(){
		if (!fap.isEmpty()){
			Controleur fap.remove(0);
			return fap.get(0);
		} else 
			return null;
	}
}
