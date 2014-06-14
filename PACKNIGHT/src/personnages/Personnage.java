package personnages;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import structure_terrain.*;
import game.WindowGame;
import hitBoxManager.*;

public abstract class Personnage{

	protected static int tauxDeDeplacement = 4; //la taille du deplacement du personnage en pixel
	protected static Terrain terrain;
	public static List<Personnage> liste = new LinkedList<Personnage>();

	//coordonne du personnage en pixel
	//La coordonne corespond au pixel Haut-Gauche !!!
	protected CoordonneesFloat coord;
	protected String nom; //nom du personnage
	protected boolean nextDirectionSet;
	protected Direction nextDirection; //prochaine direction que prendra le personnage
	protected Direction direction; //direction actuelle du personnage
	protected boolean isAlive; //si le personnage est vivant
	
	/**
	 * Donne un nom, une poisition et une direction au personnage
	 * Ajoute le personnage a la liste des perso
	 * @author malek
	 */
	public Personnage(String nom, int x, int y, Direction d){
		this.nom = new String(nom);
		this.coord = new CoordonneesFloat(32*x,32*y);
		this.direction = d;
		this.nextDirectionSet = false;
		Personnage.liste.add(this);
	}

	/**
	 * renvoie vrai si la case devant this est disponible
	 * author : alex
	 */
	public boolean caseDevantDisponible()
	{
		return this.caseDisponible(this.direction);
	}
	
	/**
	 * renvoie vraie si la case dans la direction de this est disponible
	 * @param direction : la direction ou on veut regarder les disponibiliter
	 * @return
	 * author : alex
	 */
	public boolean caseDisponible(Direction direction)
	{
		boolean res = true;
		switch (direction)
		{
		case haut :
			res = Personnage.terrain.caseAcessible(coord.casDX(), coord.casBY(),direction);
			res = res && (Personnage.terrain.caseAcessible(coord.casGX(), coord.casBY(), direction));
			return res; 
		case bas :
			res = (Personnage.terrain.caseAcessible(coord.casGX(), coord.casHY(), direction));
			res = res && (Personnage.terrain.caseAcessible(coord.casDX(), coord.casHY(), direction));
			return res;
		case droite :
			res = (Personnage.terrain.caseAcessible(coord.casGX(), coord.casBY(), direction));
			res = res && (Personnage.terrain.caseAcessible(coord.casGX(), coord.casHY(), direction));
			return res;
		case gauche :
			res = (Personnage.terrain.caseAcessible(coord.casDX(), coord.casBY(), direction));
			res = res && (Personnage.terrain.caseAcessible(coord.casDX(), coord.casHY(), direction));
			return res;
		default :
			return false;
		}
	}

	/**
	 * Primitive avancer
	 * @require : this.caseDevantDisponible() == true
	 * author : alex
	 */
	public void avancer()
	{
		if(this.nextDirectionSet && caseDisponible(this.nextDirection))
		{
			this.direction = nextDirection;
			this.nextDirectionSet = false;
			this.avancerAux();
		}
		else
		{
			if(this.caseDevantDisponible())
				this.avancerAux();
		}
	}

	/**
	 * Ne fait pas de test, et avance
	 */
	protected void avancerAux()
	{
		switch(this.direction)
		{
		case droite :
			this.coord.x += tauxDeDeplacement;
			break;
		case gauche :
			this.coord.x -= tauxDeDeplacement;
			break;
		case haut :
			this.coord.y -= tauxDeDeplacement;
			break;
			
		case bas :
			this.coord.y += tauxDeDeplacement;
			break;
			
		default :
			break;
			}
	}
	/**
	 * gere la colision en fonction de sa position
	 * author : alex
	 */
	public abstract void gererCollision();
	
	/**
	 * Change la direction du personnage
	 * author : alex
	 */
	public void setDirection(Direction direction)
	{
		this.direction = direction;
	}

	/**
	 * Change la direction du personnage
	 * author : alex
	 */
	public void setNextDirection(Direction dir)
	{
		this.nextDirection = dir;
		this.nextDirectionSet = true;
	}
	
	//setter de base
	public void setCoord(CoordonneesFloat coord) {
		this.coord = coord;
	}
	
	//setter de base
	public void setCoord(int x, int y) {
		this.coord.x = x;
		this.coord.y = y;
	}
	
	
	//getter de base
	public CoordonneesFloat getCoord() {
		return coord;
	}
	
	/**
	 * @return la direction du Personnage
	 * @author malek
	 */
	public Direction getOrientation(){
		return this.direction;
	}

	/**
	 * @return Si le fantome est vivant*/
	public boolean getisAlive(){
		return isAlive;
	}

	/**
	 * Met à jour l'état vivant ou mort du fantome*/
	public void setIsAlive(boolean a){
		isAlive=a;
		
	}
	
	/**
	 * fait revivre le pacman
	 * NEED : determiner ou se situe les points de respawn
	 * author : alex
	 */
	public abstract void respawn();

	/**
	 * Initialise le terrain static pour tous les personnages. A NE FAIRE QU'UNE SEULE FOIS
	 * @author malek
	 */
	static public void initTerrain(Terrain terrain){
		Personnage.terrain = terrain; 
	}
	
	//get terrain
	public static Terrain getTerrain() {
		return terrain;
	}
	
	/**
	 * @return String contenant le terrain et le personnage
	 * @author malek
	 */
	public String toString(){
		String res=" Personnage \n"; // + ((c instanceof Automate)? "automatisé \n" : "non automatisé \n");
		for(int i=0; i<terrain.getHauteur(); i++){
			for(int j=0; j<terrain.getLargeur(); j++){
				if (i == this.getCoord().y && j == this.getCoord().x){
					switch (this.direction){
					case haut : res += "^";   break;
					case bas : res += "v";    break;
					case gauche : res += "<"; break;
					case droite : res += ">"; break;
					}
				}else{
					if (terrain.getCase(i, j).isAccessable()){
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

	/**
	 * Test si un objet est en contact d'un pacman
	 * author : alex
	 * @param cord : coordonée de l'objet a tester
	 * @return vrai si un pacman ou plus se trouve sur les coordonnée indiquer
	 */
	static public boolean personnagePresent(CoordonneesFloat position)
	{
		Iterator<Personnage> i= Personnage.liste.iterator();
		while(i.hasNext())
		{
			if(HitBoxManager.personnageHittingPersonnage(i.next().coord, position))
				return true;
		}
		return false;
	}
	
	/**
	 * @param position a tester
	 * @return null si pas de personnage, la reference du perso si il n'y a pas de perso renvoie null
	 */
	static public Personnage personnageReference(CoordonneesFloat position)
	{
		Iterator<Personnage> i= Personnage.liste.iterator();
		while(i.hasNext())
		{
			Personnage p = i.next();
			if(position.equals(p.coord))
				return p;
		}
		return null;
	}

}
