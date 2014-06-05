/**
 * author : Alex
 * Class des pacmans
 */
package personnages;

public class Pacman extends Personnage {
	
	public Pacman(Terrain terrain, int x,int y)
	{
		this.terrain = terrain;
		this.x = x;
		this.y = y;
	}
	
	public void avancer()
	{
		switch(this.direction)
		{
		case Direction.haut :
			
			break;
		case Direction.bas :
			
			break;
		case Direction.droite :
			
			break;
		case Direction.gauche :
			
			break;
		default :
			break;
		}
	}
	
	
}
