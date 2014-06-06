package automate;

import personnages.Personnage;


public class Primitive {
	protected Personnage personnage;
	
	Primitive(Personnage personnage){
		this.personnage = personnage;
	}
	
	//j'utilise les methode pour avancer tourner du personnage, et decider de creer la primitive si y a un chemin
}
