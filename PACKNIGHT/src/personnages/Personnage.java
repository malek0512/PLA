package personnages;

import controleur.Controleur;
import controleur.automate.Automate;
import controleur.iu.InterfaceUser;
import structure_terrain.Case;
import structure_terrain.Terrain;

public abstract class Personnage {

	protected static Terrain terrain;
	protected String nom;
	Coordonnees coord;
	protected Direction direction;
	//protected boolean automatise; Si controleur c instanceof iu alors non automatisé sinon automatisé
	protected Controleur c;
	
	/**
	 * Initialise le Personnage Robot 
	 * @author malek
	 */
	public Personnage(String nom, int x, int y, Direction d){
		coord = new Coordonnees(x,y);
		this.direction = d;
		this.nom = nom;
		terrain = new Terrain(10,10);
	}
	
	
	/*Test s'il y a un mur seulement si le packman n'est pas un automate
	 *et met à jour la position de packman
	 */
	public void avancer()
	{
		switch(this.direction)
		{
		case haut :
			if (coord.x!=0){
				if(isAutomatised()){
				coord.x--;
				}
				else{
					if(Personnage.terrain.getTerrain()[coord.x-1][coord.y].isAccessable()){
						coord.x--;
					}
				}
			}
			else{
				if(isAutomatised()){
					coord.x=terrain.getLargeur();
					}
					else{
						if(Personnage.terrain.getTerrain()[terrain.getLargeur()][coord.y].isAccessable()){
							coord.x=terrain.getLargeur();
						}
					}
				
			}
			break;
		case bas :
			if (coord.x!=terrain.getLargeur()){
				if(isAutomatised()){
				coord.x++;
				}
				else{
					if(Personnage.terrain.getTerrain()[coord.x+1][coord.y].isAccessable()){
						coord.x++;
					}
				}
			}
			else{
				if(isAutomatised()){
					coord.x=0;
					}
					else{
						if(Personnage.terrain.getTerrain()[0][coord.y].isAccessable()){
							coord.x=0;
						}
					}
				
			}
			break;
		case gauche :
			if (coord.y!=0){
				if(isAutomatised()){
				coord.y--;
				}
				else{
					if(Personnage.terrain.getTerrain()[coord.x][coord.y-1].isAccessable()){
						coord.y--;
					}
				}
			}
			else{
				if(isAutomatised()){
					coord.y=terrain.getLargeur();
					}
					else{
						if(Personnage.terrain.getTerrain()[coord.x][terrain.getLargeur()].isAccessable()){
							coord.y=terrain.getLargeur();
						}
					}
				
			}
			break;
		case droite :
			if (coord.y!=terrain.getLargeur()){
				if(isAutomatised()){
				coord.y++;
				}
				else{
					if(Personnage.terrain.getTerrain()[coord.x][coord.y+1].isAccessable()){
						coord.y++;
					}
				}
			}
			else{
				if(isAutomatised()){
					coord.y=0;
					}
					else{
						if(Personnage.terrain.getTerrain()[coord.x][0].isAccessable()){
							coord.y=0;
						}
					}
				
			}
			break;
		default :
		
		
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
	 * @return position du robot, dans les variable passées en parametre
	 * @author malek
	 * @param x 
	 * @param y
	 */
	public void position(Coordonnees coord){
		coord.x = this.coord.x;
		coord.y = this.coord.y;
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
	public void positionDevant(Coordonnees coord){
		switch (this.direction){
		case haut : coord.x=this.coord.x; coord.y=this.coord.y-1;   break;
		case bas : coord.x=this.coord.x; coord.y=this.coord.y+1;    break;
		case gauche : coord.x=this.coord.x-1; coord.y=this.coord.y; break;
		case droite : coord.x=this.coord.x+1; coord.y=this.coord.y; break;
		}
	}

	/**
	 * Fonction a terminer
	 * @author malek
	 * @return
	 * 	int CASE_OCCUPEE = 0;
		int CASE_LIBRE = 1;
		int CASE_GHOST = 2;
		int SORTIE_TERRAIN = 3;
	 */
	public int configCaseDevant(){
		positionDevant(this.coord);
		if (terrain.getCase(coord.x, coord.y).isAccessable()){
			return Automate.CASE_LIBRE;
		} else{
			switch (terrain.getCase(coord.x, coord.y).getObjet()) {
				case MUR: return Automate.CASE_OCCUPEE; 
				default: return Automate.CASE_OCCUPEE;
			}
		}
	}

	/**
	 * @return dans les parametres la case devant le Personnage selon sa direction
	 * @author malek
	 */
	public boolean isAutomatised(){
		return (c instanceof Automate);
	}
	
	/**
	 * @return String contenant le terrain et le personnage
	 * @author malek
	 */
	public String toString(){
		String res=" Personnage " + ((c instanceof Automate)? "non automatisé" : "automatisé");
		for(int i=0; i<terrain.getHauteur(); i++){
			for(int j=0; j<terrain.getLargeur(); j++){
				if (i == this.coord.x && j == this.coord.y){
					switch (this.direction){
					case haut : res += "^";
					case bas : res += "v";
					case gauche : res += "<";
					case droite : res += ">";
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
