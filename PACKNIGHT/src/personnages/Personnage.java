package personnages;

import structure_terrain.Terrain;

public abstract class Personnage {

	protected static Terrain terrain;
	protected int x;
	protected int y;
	protected Direction direction;

	protected boolean automatise;
	
	protected boolean est_automate() {
		return automatise;
	}
	
	public void avancer()
	{
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

}
