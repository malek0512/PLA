package personnages;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import structure_terrain.*;
import structure_terrain.CoordPix.position;
import game.WindowGame;
import hitBoxManager.*;

public abstract class Personnage {

	protected static int tauxDeDeplacement = 4; // taille deplacement en pixel


	/**
	 * Initialise le terrain static pour tous les personnages. A NE FAIRE QU'UNE
	 * SEULE FOIS
	 * 
	 * @author malek
	 */
	static public void initTerrain(Terrain terrain) {
		Personnage.terrain = terrain;
	}

	public static Terrain terrain;
	public static List<Personnage> liste = new LinkedList<Personnage>();
	public static void init_personnage()
	{
		Personnage.liste.clear();
		Ghost.liste.clear();
		Pacman.liste.clear();
		PacKnight.liste.clear();
		PacPrincess.liste.clear();
		Ghost.central.clear();
	}
	
	// coordonne du personnage en pixel
	// La coordonne corespond au pixel Haut-Gauche !!!
	public CoordPix coord;
	public String nom; // nom du personnage
	public Direction direction; // direction actuelle du personnage
	
	protected boolean nextDirectionSet;
	protected Direction nextDirection; // prochaine direction que prendra personnage
	protected boolean agonise = false; //boolean vrai si le personnage est en animation de mort
	protected int timerAnimation = 0; //timer pour les animations
	protected List<CoordCas> ordre;

	
	/**
	 * Donne un nom, une poisition et une direction au personnage Ajoute le
	 * personnage a la liste des perso
	 * 
	 * @author malek
	 */
	public Personnage(String nom, int x, int y, Direction d) {
		this.nom = new String(nom);
		this.coord = new CoordPix(x*32-1,y*32-1,position.hg);
		this.direction = d;
		this.nextDirectionSet = false;
		Personnage.liste.add(this);
	}

	/************************************************
	 * Methode dont l'utiliter n'est plus a prouver *
	 ***********************************************/

	/* METHODE POUR AUTOMATE */
	/**
	 * Ne fait pas de test, et avance Utiliser par les automates et c'est tout
	 */
	public void avancerAux() {
		if(!Personnage.terrain.estCore(coord.CasCentre(), direction))
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
		else
		{
			switch (this.direction)
			{
			case droite :
				this.coord.x = 0;
				break;
			case gauche :
				this.coord.x = Personnage.terrain.pixelBordDroit() - WindowGame.tuile_size;
				break;
			case bas :
				this.coord.y = 0;
				break;
			case haut :
				this.coord.y = Personnage.terrain.pixelBordBas() - WindowGame.tuile_size;
				break;
			}
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
	public void avancer() 
	{
		if (this.nextDirectionSet) 
		{
			if(caseDisponible(this.nextDirection))
			{
				this.direction = nextDirection;
				this.nextDirectionSet = false;
				this.avancerAux();
			}
			else if(caseDevantDisponible())
			{
				this.avancerAux();
			}
		} 
		else 
			if (caseDevantDisponible())
				this.avancerAux();
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

		switch (direction) {
		case haut:
			return Personnage.terrain.caseAcessible(coord.CasBD(), direction) &&
					Personnage.terrain.caseAcessible(coord.CasBG(), direction);
		case bas:
			return (Personnage.terrain.caseAcessible(coord.CasHG(), direction))
					&& (Personnage.terrain.caseAcessible(coord.CasHD(), direction));
		case droite:
			return (Personnage.terrain.caseAcessible(coord.CasBG(), direction))
					&& (Personnage.terrain.caseAcessible(coord.CasHG(), direction));
		case gauche:
			return (Personnage.terrain.caseAcessible(coord.CasBD(), direction))
					&& (Personnage.terrain.caseAcessible(coord.CasHD(), direction));
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
	 * Test si un objet est en contact d'un pacman author : alex
	 * 
	 * @param cord: coordonée de l'objet a tester
	 * @return vrai si un pacman ou plus se trouve sur les coordonnée indiquer
	 */
	static public boolean hittingPerso(CoordPix position) {
		Iterator<Personnage> i = Personnage.liste.iterator();
		while (i.hasNext()) {
			if (HitBoxManager.personnageHittingPersonnage(i.next().coord,position))
				return true;
		}
		return false;
	}

	/**
	 * test si un perso est sur la case donné
	 * @param position
	 * @return
	 */
	static public boolean personnagePresent(CoordCas position)
	{
		Iterator<Ghost> i= Ghost.liste.iterator();
		while(i.hasNext())
		{
			if(position.equals(i.next().coord.CasCentre()))
				return true;
		}
		return false;
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

	
	/**
	 * @return String contenant le terrain et le personnage
	 * @author malek
	 * 
	public String toString() {
		String res = " Personnage \n"; // + ((c instanceof Automate)?
										// "automatisé \n" :
										// "non automatisé \n");
		for (int i = 0; i < terrain.hauteur; i++) {
			for (int j = 0; j < terrain.largeur; j++) {
				if (i == this.coord.y && j == this.coord.x) {
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
	*/
}
