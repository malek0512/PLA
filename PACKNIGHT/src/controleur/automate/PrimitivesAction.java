package controleur.automate;

import java.util.Iterator;
import java.util.List;

import personnages.Coordonnees;
import personnages.Direction;
import personnages.Ghost;
import personnages.Pacman;
import structure_terrain.Case;

/**
 * Classe contenant l'ensemble des primitives d'action 
 * @author malek
 */
public class PrimitivesAction extends Primitives{

	public PrimitivesAction(Automate a) {
		super();
		this.auto = a;
	}	

}
