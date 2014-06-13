package personnages;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import structure_terrain.*;
import hitBoxManager.*;

public abstract class Personnage{

	protected static int tauxDeDeplacement = 1; //la taille du deplacement du personnage en pixel
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
	
	//coordonne du personnage en pixel
	//La coordonne corespond au pixel Haut-Gauche !!!
	protected CoordonneesFloat coord;
	protected String nom; //nom du personnage
	protected Direction direction; //direction actuelle du personnage
	protected boolean vivant; //si le personnage est vivia
	/**
	 * Donne un nom, une poisition et une direction au personnage
	 * Ajoute le personnage a la liste des perso
	 * @author malek
	 */
	public Personnage(String nom, int x, int y, Direction d){
		this.nom = new String(nom);
		this.setCoord(new CoordonneesFloat(x,y));
		this.direction = d;
		Personnage.liste.add(this);
	}

	/*
	// TODO private boolean collisionTiles()
	{
		//vérifier si un coin de l'image touche un mur?
		//pixel haut gauche
		i1 = cord.x/lageur_tile;
		j1 = cord.y/hauteur_tile;
		
		//pixel bas droite
		i2 = (cord.x + skin_largeur -1)/largeur_tile;
		j2 = (cord.y + skin_hauteur -1)/hauteur_tile;
		
		
		/**
		 * pour tester chaque pixel
		 */
		/*
		for(int i=i1; i<=i2; i++)
		{
			for(int j=j1; j<=j2;j++)
			{
				if(TileIsMur(i,j)
					return true;
			}
		}
		*/
	
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
		return Personnage.terrain.getCase(coord.NonPixelX(), coord.NonPixelY(), direction).isAccessable();
	}

	/**
	 * Primitive avancer
	 * @require : this.caseDevantDisponible() == true
	 * author : alex
	 */
	public void avancer()
	{
		//System.out.println("######################");
		//System.out.println("Direction du deplacement : " + this.direction);
		//System.out.println("x : " + coordFloat.x + " y : " +coordFloat.y);
		if(caseDevantDisponible())
		{
			System.out.println("Deplacement autorisé");
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
			
			//majEstSurAxe();
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
