package personnages;

import structure_terrain.Terrain;

public abstract class Personnage {

	protected static Terrain terrain;
	protected int x;
	protected int y;
	protected Direction direction;
	protected boolean automatise;
	
	/**
	 * Initialise le Personnage Robot 
	 * @author malek
	 */
	public Personnage(int x, int y, Direction d){
		this.x = x;
		this.y = y;
		this.direction = d;
	}
	
	
	protected boolean est_automate() {
		return automatise;
	}
	
	public void avancer(){
		switch(this.direction)
		{
		case haut :
			if(est_automate()){
			x++;
			}
			else{
				if(terrain.getTerrain()[x+1][y].getaccessCase()){
					x++;
				}
			}
			break;
		case bas :
			if(est_automate()){
				x--;
				}
				else{
					if(terrain.getTerrain()[x-1][y].getaccessCase()){
						x--;
					}
				}
			break;
		case droite :
			if(est_automate()){
				y++;
				}
				else{
					if(terrain.getTerrain()[x][y+1].getaccessCase()){
						y++;
					}
				}
			break;
		case gauche :
			if(est_automate()){
				y--;
				}
				else{
					if(terrain.getTerrain()[x][y-1].getaccessCase()){
						y--;
					}
				};
			break;
		default :
			break;
		}
	}

	/**
	 * @author malek
	 */
	public void tournerDroite(){
		switch (this.direction){
		case haut : this.direction = Direction.droite;
		case bas : this.direction = Direction.gauche;
		case gauche : this.direction = Direction.haut;
		case droite : this.direction = Direction.bas;
		}
	}
	
	/**
	 * @author malek
	 */
	public void tournerGauche(){
		switch (this.direction){
		case haut : this.direction = Direction.gauche;
		case bas : this.direction = Direction.droite;
		case gauche : this.direction = Direction.bas;
		case droite : this.direction = Direction.haut;
		}
	}
	
	/**
	 * @author malek
	 * @return position du robot, dans les variable passées en parametre
	 * @param x 
	 * @param y
	 */
	public void position(int x, int y){
		x = this.x;
		y = this.y;
	}

	/**
	 * @author malek
	 * @return la direction du Personnage
	 */
	public Direction getOrientation(){
		return this.direction;
	}
	
	/**
	 * @author malek
	 * @return dans les parametres la case devant le Personnage selon sa direction
	 * @param d
	 * @param x
	 * @param y
	 */
	public void positionDevant(Direction d, int x, int y){
		switch (this.direction){
		case haut : x=this.x; y=this.y-1;
		case bas : x=this.x; y=this.y+1;
		case gauche : x=this.x-1; y=this.y;
		case droite : x=this.x+1; y=this.y;
		}
	}
}
