package personnages;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import structure_terrain.*;
import hitBoxManager.*;

public abstract class Personnage {

	protected static int tauxDeDeplacement = 4; // la taille du deplacement du
												// personnage en pixel

	protected static Terrain terrain;
	public static List<Personnage> liste = new LinkedList<Personnage>();

	// coordonne du personnage en pixel
	// La coordonne corespond au pixel Haut-Gauche !!!
	protected CoordonneesFloat coord;
	protected String nom; // nom du personnage
	protected boolean nextDirectionSet;
	protected Direction nextDirection; // prochaine direction que prendra personnage
	protected Direction direction; // direction actuelle du personnage

	public boolean agonise = false; //boolean vrai si le personnage est en animation de mort
	protected int timerAnimation = 0; //timer pour les animations
	public List<CoordonneesFloat> ordre;

	
	/**
	 * Donne un nom, une poisition et une direction au personnage Ajoute le
	 * personnage a la liste des perso
	 * 
	 * @author malek
	 */
	public Personnage(String nom, int x, int y, Direction d) {
		this.nom = new String(nom);
		this.coord = new CoordonneesFloat(32 * x, 32 * y);
		this.direction = d;
		this.nextDirectionSet = false;
		Personnage.liste.add(this);
	}

	// getter de base
	public CoordonneesFloat getCoord() {
		return coord;
	}

	// getter de base
	public Direction getOrientation() {
		return this.direction;
	}

	/************************************************
	 * Methode dont l'utiliter n'est plus a prouver *
	 ***********************************************/

	/* METHODE POUR AUTOMATE */
	/**
	 * Ne fait pas de test, et avance Utiliser par les automates et c'est tout
	 */
	public void avancerAux() {
		switch (this.direction) {
		case droite:
			this.coord.x += tauxDeDeplacement;
			break;
		case gauche:
			this.coord.x -= tauxDeDeplacement;
			break;
		case haut:
			this.coord.y -= tauxDeDeplacement;
			break;

		case bas:
			this.coord.y += tauxDeDeplacement;
			break;

		default:
			break;
		}
		this.gererCollision();
	}

	/**
	 * Change la direction du personnage Utiliser par les automates uniquement !
	 * author : alex
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	/* METHODE POUR USER */
	/**
	 * Fait les test neccessaire pour savoir le personnage peut avancer Si il le
	 * peut, alors le fait avancer
	 * 
	 * author : alex
	 */
	public void avancer() {
		if (this.nextDirectionSet && caseDisponible(this.nextDirection)) {
			this.direction = nextDirection;
			this.nextDirectionSet = false;
			this.avancerAux();
		} else {
			if (this.caseDevantDisponible())
				this.avancerAux();
		}
	}

	/**
	 * Change la prochaine direction du personnage Utiliser par les utilisateur
	 * uniquement ! author : alex
	 */
	public void setNextDirection(Direction dir) {
		this.nextDirection = dir;
		this.nextDirectionSet = true;
	}

	/* RESTE */
	/**
	 * renvoie vrai si la case devant this est disponible author : alex
	 */
	public boolean caseDevantDisponible() {
		return this.caseDisponible(this.direction);
	}

	/**
	 * renvoie vraie si la case dans la direction de this est disponible
	 * 
	 * @param direction
	 *            : la direction ou on veut regarder les disponibiliter
	 * @return author : alex
	 */
	public boolean caseDisponible(Direction direction) {

		boolean res = true;
		switch (direction) {
		case haut:
			res = Personnage.terrain.caseAcessible(coord.casDX(),
					coord.casBY(), direction);
			res = res
					&& (Personnage.terrain.caseAcessible(coord.casGX(),
							coord.casBY(), direction));
			return res;
		case bas:
			res = (Personnage.terrain.caseAcessible(coord.casGX(),
					coord.casHY(), direction));
			res = res
					&& (Personnage.terrain.caseAcessible(coord.casDX(),
							coord.casHY(), direction));
			return res;
		case droite:
			res = (Personnage.terrain.caseAcessible(coord.casGX(),
					coord.casBY(), direction));
			res = res
					&& (Personnage.terrain.caseAcessible(coord.casGX(),
							coord.casHY(), direction));
			return res;
		case gauche:
			res = (Personnage.terrain.caseAcessible(coord.casDX(),
					coord.casBY(), direction));
			res = res
					&& (Personnage.terrain.caseAcessible(coord.casDX(),
							coord.casHY(), direction));
			return res;
		default:
			return false;
		}

	}

	/**
	 * gere la colision en fonction de sa position author : alex
	 */
	public abstract void gererCollision();

	/**
	 * fait revivre le pacman NEED : determiner ou se situe les points de
	 * respawn author : alex
	 */
	public abstract void respawn();

	/**
	 * Initialise le terrain static pour tous les personnages. A NE FAIRE QU'UNE
	 * SEULE FOIS
	 * 
	 * @author malek
	 */
	static public void initTerrain(Terrain terrain) {
		Personnage.terrain = terrain;
	}

	// get terrain
	public static Terrain getTerrain() {
		return terrain;

	}

	/**
	 * Test si un objet est en contact d'un pacman author : alex
	 * 
	 * @param cord: coordonée de l'objet a tester
	 * @return vrai si un pacman ou plus se trouve sur les coordonnée indiquer
	 */
	static public boolean personnagePresent(CoordonneesFloat position) {
		Iterator<Personnage> i = Personnage.liste.iterator();
		while (i.hasNext()) {
			if (HitBoxManager.personnageHittingPersonnage(i.next().coord,
					position))
				return true;
		}
		return false;
	}

	/**
	 * @param position a tester
	 * @return null si pas de personnage, la reference du perso si il n'y a pas
	 *         de perso renvoie null
	 */
	static public Personnage personnageReference(CoordonneesFloat position) {
		Iterator<Personnage> i = Personnage.liste.iterator();
		while (i.hasNext()) {
			Personnage p = i.next();
			if (position.equals(p.coord))
				return p;
		}
		return null;
	}

	/**
	 * le pacman meurt dans d'atroces souffrances author : alex
	 */
	public abstract void meurtDansDatroceSouffrance();

	/**
	 * renvoie vraie si les changement de direction ou si les action de move sont autorisé
	 * si false, lancer la fonction "avancerAnimation"
	 * @return
	 */
	public abstract boolean parametrable();
	
	/**
	 * fait avancer les animations, de mort, de deplacement auto, et tout le tralala
	 */
	public abstract void avancerAnimation();
	
	/**
	 * renvoie vraie si le personnage peut etre touché
	 * author : alex
	 */
	public abstract boolean hitting();

	/***********************************************
	 * fonction dont l'utiliter reste a prouver *
	 ***********************************************/

	// setter de base
	public void setCoord(CoordonneesFloat coord) {
		this.coord = coord;
	}

	/**
	 * Plus simple de changer la position du Personnage, plutot que d'acceder au coordonnée, et puis changer
	 * @param coord
	 */
	public void setCoord(Coordonnees coord) {
		this.coord = coord.toCoordonneesFloat(); //Creer eventuellement un setteur de coordonnées
	}
	
	// setter de base
	public void setCoord(int x, int y) {
		this.coord.x = x;
		this.coord.y = y;
	}
	/**
	 * @return String contenant le terrain et le personnage
	 * @author malek
	 */
	public String toString() {
		String res = " Personnage \n"; // + ((c instanceof Automate)?
										// "automatisé \n" :
										// "non automatisé \n");
		for (int i = 0; i < terrain.getHauteur(); i++) {
			for (int j = 0; j < terrain.getLargeur(); j++) {
				if (i == this.getCoord().y && j == this.getCoord().x) {
					switch (this.direction) {
					case haut:
						res += "^";
						break;
					case bas:
						res += "v";
						break;
					case gauche:
						res += "<";
						break;
					case droite:
						res += ">";
						break;
					}
				} else {
					if (terrain.getCase(i, j).isAccessable()) {
						res += "-";
					} else {
						res += "X";
					}
				}
			}
			res += "\n";
		}
		res += "\n";
		return res;
	}

}
