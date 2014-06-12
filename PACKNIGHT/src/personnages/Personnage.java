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

	protected static float tauxDeDeplacement = (float) 0.5; //la taille du deplacement du personnage
	protected static Terrain terrain;
	private boolean isAlive;

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
			if(HitBoxManager.hitting(i.next().coordFloat, position))
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
	public boolean isMoving;
	// protected Coordonnees coord; a supprimer
	protected Direction direction;
	protected boolean vivant;
	/**
	 * Donne un nom, une poisition et une direction au personnage
	 * Ajoute le personnage a la liste des perso
	 * @author malek
	 */
	public Personnage(String nom, float x, float y, Direction d){
		this.nom = new String(nom);
		this.setCoord(new CoordonneesFloat(x,y));
		this.direction = d;
		this.estSurAxeX=true;
		this.estSurAxeY=true;
		this.estSurCase=true;
		this.isMoving = false;
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
		{
			System.out.println("case actuelle :" + coordFloat);
			System.out.println("case Destination :" + this.coordFloat.intoInt());
			return Personnage.terrain.getCase(this.coordFloat.intoInt(), direction).isAccessable();
		}
		else if(direction == Direction.haut || direction == Direction.bas)
			return !(estSurAxeY); 
		else
			return !(estSurAxeX);
	}
	
	private void majEstSurAxe()
	{
		estSurAxeX = (int) coordFloat.x == coordFloat.x;
		System.out.println(coordFloat.x +""+ estSurAxeX);
		estSurAxeY = (int) coordFloat.y == coordFloat.y;
		System.out.println(coordFloat.y +""+ estSurAxeY);
		estSurCase = estSurAxeX && estSurAxeY;
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
			this.coordFloat.y -= tauxDeDeplacement;
			break;
			
		case bas :
			this.coordFloat.y += tauxDeDeplacement;
			break;
			
		default :
			break;
		}
		this.majEstSurAxe();
		this.isMoving = false;
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
