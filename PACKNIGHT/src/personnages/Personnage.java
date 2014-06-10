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

	static public List<Personnage> liste = new LinkedList<Personnage>();
	
	protected static Terrain terrain;

	/**
	 * Initialise le terrain static pour tous les personnages. A NE FAIRE QU'UNE SEULE FOIS
	 * @author malek
	 */
	static public void initTerrain(Terrain terrain){
		Personnage.terrain = terrain; 
	}
	
	/**
	 * @param position ou on veut savoir si un personnage si trouve
	 * @return renvoie vrai si un objet Personnage se trouve sur la position indiquer
	 */
	static public boolean personnagePresent(Coordonnees position)
	{
		Iterator<Personnage> i= Personnage.liste.iterator();
		while(i.hasNext())
		{
			if(position.equals(i.next().coord))
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
			if(position.equals(p.coord))
				return p;
		}
		return null;
	}
	
	protected String nom;
	protected Coordonnees coord;
	protected Direction direction;
	
	//protected boolean automatise; Si controleur c instanceof iu alors non automatisé sinon automatisé
	//protected Controleur c;
	
	/**
	 * Donne un nom, une poisition et une direction au personnage
	 * @author malek
	 */
	public Personnage(String nom, int x, int y, Direction d){
		this.nom = new String(nom);
		this.setCoord(new Coordonnees(x,y));
		this.direction = d;
		Personnage.liste.add(this);
	}
	
//	public void insererAutomate(Automate a){
//		this.c = a;
//	}
	
	/**
	 * Test si le personnage peut avancer
	 * author : alex
	 */
	public boolean peutAvancer()
	{
		switch(this.direction)
		{
		case droite :
			return Personnage.terrain.getCase(coord.x+1, coord.y).isAccessable();
			
		case gauche :
			return Personnage.terrain.getCase(coord.x-1, coord.y).isAccessable();
			
		case haut :
			return Personnage.terrain.getCase(coord.x, coord.y+1).isAccessable();
			
		case bas :
			return Personnage.terrain.getCase(coord.x, coord.y-1).isAccessable();
		default :
			return false; //pour eviter des erreurs de compile...
		}
	}
	
	/**
	 * Primitive avancer
	 * author : alex
	 */
	public void avancer()
	{
		switch(this.direction)
		{
		case droite :
			if(this.coord.x==terrain.getLargeur()-1)
				coord.x = 0 ;
			else
				coord.x++;
			break;
			
		case gauche :
			if(this.coord.x==0)
				coord.x = terrain.getLargeur()-1;
			else
				coord.x--;
			break;
			
		case haut :
			if(this.coord.y==terrain.getHauteur()-1)
				coord.y = 0;
			else
				coord.y++;
			break;
			
		case bas :
			if(this.coord.y==0)
				coord.y = terrain.getHauteur()-1;
			else
				coord.y--;
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
	
	/**
	 * setter coord
	 */
	public void positionner(Coordonnees coord){
		coord.x = this.coord.x;
		coord.y = this.coord.y;
	}
	
	/**
	 * getter coord
	 */
	public Coordonnees position(){
		return new Coordonnees(this.getCoord());
	}

	/**
	 * @return la direction du Personnage
	 * @author malek
	 */
	public Direction getOrientation(){
		return this.direction;
	}
	


	/**
	 * @return dans les parametres la case devant le Personnage selon sa direction
	 * @author malek
	 */
	/*
	public boolean isAutomatised(){
		return (c instanceof Automate);
	}
	*/
	
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

	public Coordonnees getCoord() {
		return coord;
	}

	public void setCoord(Coordonnees coord) {
		this.coord = coord;
	}
	public static Terrain getTerrain() {
		return terrain;
	}
}
