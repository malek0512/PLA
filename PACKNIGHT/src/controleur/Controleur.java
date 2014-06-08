package controleur;

import personnages.Personnage;

public abstract class Controleur {
	protected Personnage personnage;

	// Fonction a supprimer des que la contructeur de Automate fonctionne, ie
	// parser fonctionne
	public void setPersonnage(Personnage p) {
		this.personnage = p;
	}

	public Personnage getPersonnage() {
		return this.personnage;
	}

}
