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
	
	/*Test s'il y a un mur seulement si le packman n'est pas un automate
	 *et met à jour la position de packman
	 */
	public void avancer()
	{
		switch(this.direction)
		{
		case haut :
			if (x!=0){
				if(est_automate()){
				x--;
				}
				else{
					if(Personnage.terrain.getTerrain()[x-1][y].getaccessCase()){
						x--;
					}
				}
			}
			else{
				if(est_automate()){
					x=terrain.getLargeur();
					}
					else{
						if(Personnage.terrain.getTerrain()[terrain.getLargeur()][y].getaccessCase()){
							x=terrain.getLargeur();
						}
					}
				
			}
			break;
		case bas :
			if (x!=terrain.getLargeur()){
				if(est_automate()){
				x++;
				}
				else{
					if(Personnage.terrain.getTerrain()[x+1][y].getaccessCase()){
						x++;
					}
				}
			}
			else{
				if(est_automate()){
					x=0;
					}
					else{
						if(Personnage.terrain.getTerrain()[0][y].getaccessCase()){
							x=0;
						}
					}
				
			}
			break;
		case gauche :
			if (y!=0){
				if(est_automate()){
				y--;
				}
				else{
					if(Personnage.terrain.getTerrain()[x][y-1].getaccessCase()){
						y--;
					}
				}
			}
			else{
				if(est_automate()){
					y=terrain.getLongueur();
					}
					else{
						if(Personnage.terrain.getTerrain()[x][terrain.getLongueur()].getaccessCase()){
							y=terrain.getLongueur();
						}
					}
				
			}
			break;
		case droite :
			if (y!=terrain.getLongueur()){
				if(est_automate()){
				y++;
				}
				else{
					if(Personnage.terrain.getTerrain()[x][y+1].getaccessCase()){
						y++;
					}
				}
			}
			else{
				if(est_automate()){
					y=0;
					}
					else{
						if(Personnage.terrain.getTerrain()[x][0].getaccessCase()){
							y=0;
						}
					}
				
			}
			break;
		default :
		
		
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
