package controleur;

import personnages.Personnage;

public abstract class Controleur {
	protected Personnage personnage;

	public Personnage getPersonnage() {
		return this.personnage;
	}

}
