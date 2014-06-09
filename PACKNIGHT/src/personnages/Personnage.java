/**
 * Edit :
 * J'ai mit la fonction personnage en abstract, elle doit pas etre instancier
 * j'ai mit la fonction d'init terrain en final, qu'on puisse l'utiliser sans passer par une instance
 * d'une fonction ^^ et pour qu'on puisse l'apeler que par Personnage
 */

package personnages;

import structure_terrain.*;

public abstract class Personnage{

	protected static Terrain terrain;
	/**
	 * Initialise le terrain static pour tous les personnages. A NE FAIRE QU'UNE SEULE FOIS
	 * @author malek
	 * @param hauteur_terrain
	 * @param largeur_terrain
	 */
	final public void initTerrain(Terrain terrain){
		Personnage.terrain = terrain; 
	}
	
	protected String nom;
	private Coordonnees coord;
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
	 * @return dans les parametres la case devant le Personnage selon sa direction actuelle
	 * @author malek
	 * @param d
	 * @param x
	 * @param y
	 */
	/*
	public Coordonnees positionDevant(){
		Coordonnees coord = new Coordonnees(0,0);
		switch (this.direction){
		case haut : coord.x=this.getCoord().x; coord.y=this.getCoord().y-1;   break;
		case bas : coord.x=this.getCoord().x; coord.y=this.getCoord().y+1;    break;
		case gauche : coord.x=this.getCoord().x-1; coord.y=this.getCoord().y; break;
		case droite : coord.x=this.getCoord().x+1; coord.y=this.getCoord().y; break;
		}
		return coord;
	}
	*/

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
						res += " ";
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
