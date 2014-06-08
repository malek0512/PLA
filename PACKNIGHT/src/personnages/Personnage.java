package personnages;

import controleur.Controleur;
import controleur.automate.Automate;
import controleur.iu.InterfaceUser;
import structure_terrain.Case;
import structure_terrain.Terrain;

public abstract class Personnage {

	protected static Terrain terrain;
	protected String nom;
	protected int nbVie;
	private Coordonnees coord;
	protected Direction direction;
	//protected boolean automatise; Si controleur c instanceof iu alors non automatisé sinon automatisé
	//protected Controleur c;
	
	/**
	 * Initialise le Personnage Robot. Par defaut automatise
	 * @author malek
	 */
	public Personnage(String nom, int x, int y, Direction d){
		this.nom = new String(nom);
		this.setCoord(new Coordonnees(x,y));
		this.direction = d;
		this.nbVie = 3; //A modifier ulterieurement
	}
	
//	public void insererAutomate(Automate a){
//		this.c = a;
//	}
	
	/**
	 * Initialise le terrain static pour tous les personnages. A NE FAIRE QU'UNE SEULE FOIS
	 * @author malek
	 * @param hauteur_terrain
	 * @param largeur_terrain
	 */
	public void initTerrain(int hauteur_terrain, int largeur_terrain){
		Personnage.terrain = new Terrain(hauteur_terrain,largeur_terrain); 
	}
	
	/*Test s'il y a un mur seulement si le packman n'est pas un automate
	 *et met à jour la position de packman
	 */
//	public void avancer()
//	{
//		switch(this.direction)
//		{
//		case haut :
//			if (getCoord().x!=0){
//				if(isAutomatised()){
//				getCoord().x--;
//				}
//				else{
//					if(Personnage.terrain.getTerrain()[getCoord().x-1][getCoord().y].isAccessable()){
//						getCoord().x--;
//					}
//				}
//			}
//			else{
//				if(isAutomatised()){
//					getCoord().x=terrain.getLargeur();
//					}
//					else{
//						if(Personnage.terrain.getTerrain()[terrain.getLargeur()][getCoord().y].isAccessable()){
//							getCoord().x=terrain.getLargeur();
//						}
//					}
//				
//			}
//			break;
//		case bas :
//			if (getCoord().x!=terrain.getLargeur()){
//				if(isAutomatised()){
//				getCoord().x++;
//				}
//				else{
//					if(Personnage.terrain.getTerrain()[getCoord().x+1][getCoord().y].isAccessable()){
//						getCoord().x++;
//					}
//				}
//			}
//			else{
//				if(isAutomatised()){
//					getCoord().x=0;
//					}
//					else{
//						if(Personnage.terrain.getTerrain()[0][getCoord().y].isAccessable()){
//							getCoord().x=0;
//						}
//					}
//				
//			}
//			break;
//		case gauche :
//			if (getCoord().y!=0){
//				if(isAutomatised()){
//				getCoord().y--;
//				}
//				else{
//					if(Personnage.terrain.getTerrain()[getCoord().x][getCoord().y-1].isAccessable()){
//						getCoord().y--;
//					}
//				}
//			}
//			else{
//				if(isAutomatised()){
//					getCoord().y=terrain.getLargeur();
//					}
//					else{
//						if(Personnage.terrain.getTerrain()[getCoord().x][terrain.getLargeur()].isAccessable()){
//							getCoord().y=terrain.getLargeur();
//						}
//					}
//				
//			}
//			break;
//		case droite :
//			if (getCoord().y!=terrain.getLargeur()){
//				if(isAutomatised()){
//				getCoord().y++;
//				}
//				else{
//					if(Personnage.terrain.getTerrain()[getCoord().x][getCoord().y+1].isAccessable()){
//						getCoord().y++;
//					}
//				}
//			}
//			else{
//				if(isAutomatised()){
//					getCoord().y=0;
//					}
//					else{
//						if(Personnage.terrain.getTerrain()[getCoord().x][0].isAccessable()){
//							getCoord().y=0;
//						}
//					}
//				
//			}
//			break;
//		default :
//		
//		
//		}
//	}

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
	 * Determine la nouvelle direction du robot selon la direction actuelle 
	 * @author malek
	 */
	public void tournerDroite(){
		switch (this.direction){
		case haut : this.direction = Direction.droite;  break;
		case bas : this.direction = Direction.gauche;   break;
		case gauche : this.direction = Direction.haut;  break;
		case droite : this.direction = Direction.bas;   break;
		}
	}
	
	/**
	 * Determine la nouvelle direction du robot selon la direction actuelle
	 * @author malek
	 */
	public void tournerGauche(){
		switch (this.direction){
		case haut : this.direction = Direction.gauche;  break;
		case bas : this.direction = Direction.droite;   break;
		case gauche : this.direction = Direction.bas;   break;
		case droite : this.direction = Direction.haut;  break;
		}
	}
	
	/**
	 * @return {@link Coordonnees}, position du robot
	 * @author malek
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

	/**
	 * @return dans les parametres la case devant le Personnage selon sa direction
	 * @author malek
	 */
//	public boolean isAutomatised(){
//		return (c instanceof Automate);
//	}
	
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
