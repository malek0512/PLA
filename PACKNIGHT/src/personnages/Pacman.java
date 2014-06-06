/**
 * author : Alex et Rama
 * Class des pacmans
 * WARNING : Pensez à initialiser TERRAIN
 */
package personnages;

import structure_terrain.Terrain;

public class Pacman extends Personnage {
	
	public Pacman(Terrain terrain, int x,int y)
	{

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
			break;
		}
	}
	
	
}
