/**
 * author : Alex
 * Class des pacmans
 */
package personnages;

import structure_terrain.Terrain;

public class Pacman extends Personnage {
	
	public Pacman(Terrain terrain, int x,int y)
	{
		this.terrain = terrain;
		this.x = x;
		this.y = y;
	}
	
	/*Test s'il y a un mur seulement si le packman n'est pas un automate
	 *et met à jour la position de packman
	 */
	public void avancer()
	{
		switch(this.direction)
		{
		case haut :
			if(est_automate()){
			x++;
			}
			else{
				if(this.terrain.getTerrain()[x+1][y].getaccessCase()){
					x++;
				}
			}
			break;
		case bas :
			if(est_automate()){
				x--;
				}
				else{
					if(this.terrain.getTerrain()[x-1][y].getaccessCase()){
						x--;
					}
				}
			break;
		case droite :
			if(est_automate()){
				y++;
				}
				else{
					if(this.terrain.getTerrain()[x][y+1].getaccessCase()){
						y++;
					}
				}
			break;
		case gauche :
			if(est_automate()){
				y--;
				}
				else{
					if(this.terrain.getTerrain()[x][y-1].getaccessCase()){
						y--;
					}
				};
			break;
		default :
			break;
		}
	}
	
	
}
