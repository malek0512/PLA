/**
 * Edit :
 * J'ai mit la fonction personnage en abstract, elle doit pas etre instancier
 * j'ai mit la fonction d'init terrain en final, qu'on puisse l'utiliser sans passer par une instance
 * d'une fonction ^^ et pour qu'on puisse l'appeler que par Personnage
 */

package personnages;




import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import structure_terrain.*;


public abstract class Personnage{

	protected static float tauxDeDeplacement = (float) 0.2; //la taille du deplacement du personnage
	protected static Terrain terrain;
	
	public static List<Personnage> liste = new LinkedList<Personnage>();
	
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
	 * @param position ou on veut savoir si un personnage si trouve
	 * @return renvoie vrai si un objet Personnage se trouve sur la position indiquer
	 */
	static public boolean personnagePresent(CoordonneesFloat position)
	{
		Iterator<Personnage> i= Personnage.liste.iterator();
		while(i.hasNext())
		{
			if(position.equals(i.next().coordFloat))
				return true;
		}
		return false;
	}
	
	/**
	 * @param position a tester
	 * @return null si pas de personnage, la reference du perso si il n'y a pas de perso renvoie null
	 */
	static public Personnage personnageReference(Coordonnees position)
	{
		Iterator<Personnage> i= Personnage.liste.iterator();
		while(i.hasNext())
		{
			Personnage p = i.next();
			if(position.equals(p.coordFloat))
				return p;
		}
		return null;
	}
	
	/**
	 * Pour la gestion des collision
	 */
	boolean estSurAxeX; //vraie si la coord x est à 0
	boolean estSurAxeY; //vraie si la coord y est à 0
	protected CoordonneesFloat coordFloat;
	boolean estSurCase; //vraie si et seulement si les boolean estSurAxe(X ou Y) sont vrai
	protected String nom;
	// protected Coordonnees coord; a supprimer
	protected Direction direction;
	
	/**
	 * Donne un nom, une poisition et une direction au personnage
	 * Ajoute le personnage a la liste des perso
	 * @author malek
	 */
	public Personnage(String nom, float x, float y, Direction d){
		this.nom = new String(nom);
		this.setCoord(new CoordonneesFloat(x,y));
		this.direction = d;
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
		if (estSurCase)
			return Personnage.terrain.getCase(this.coordFloat.intoInt(), direction).isAccessable();
		else if(direction == Direction.haut || direction == Direction.bas)
			return !(estSurAxeY); 
		else
			return !(estSurAxeX);
	}
	
	/**
	 * Primitive avancer
	 * @require : this.caseDevantDisponible() == true
	 * author : alex
	 */
	public void avancer()
	{
		switch(this.direction)
		{
		case droite :
			this.coordFloat.x += tauxDeDeplacement;
			break;
		case gauche :
			this.coordFloat.x -= tauxDeDeplacement;
			break;
		case haut :
			this.coordFloat.y += tauxDeDeplacement;
			break;
			
		case bas :
			this.coordFloat.y -= tauxDeDeplacement;
			break;
			
		default :
			break;
		}
	}


	/**
	 * Avance Betement
	 * @author malek
	 */
	public void avancerBetement(){
		switch (this.direction){
		case haut : this.getCoord().y--;  break;
		case bas : this.getCoord().y++;   break;
		case gauche : this.getCoord().x--;  break;
		case droite : this.getCoord().x++;   break;
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
	
	
	//setter de base
	public void setCoord(CoordonneesFloat coord) {
		this.coordFloat = coord;
	}
	
	//setter de base
	public void setCoord(float x, float y) {
		this.coordFloat.x = x;
		this.coordFloat.y = y;
	}
	
	
	//getter de base
	public CoordonneesFloat getCoord() {
		return coordFloat;
	}
	
	/**
	 * @return la direction du Personnage
	 * @author malek
	 */
	public Direction getOrientation(){
		return this.direction;
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


}
