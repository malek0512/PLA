/**
 * legende :
 * 
 * ## Option 1 ##
 * Pix : les valeurs de retour sont en pixel
 * Cas : les valeurs de retour sont des numeros de case
 * 
 * ## Option 2 ##
 * B : bas
 * H : haut
 * G : gauche
 * D : droite
 * C : centre
 * 
 * ## Option 3 ##
 * X : renvoie seulement pour la valeur X
 * Y : renvoie seulement pour la valeur Y
 * 
 * si pas d'option 3, renvoie un objet CoordonneesFloat 
 * contenant dans X et Y les valeurs calculer avec option 3 X et Y
 */

package personnages;

import structure_terrain.Terrain;
import game.*;

public class CoordonneesFloat {
	
	public int x;
	public int y;

	final static private int size = WindowGame.tuile_size;

	
	/**
	 * constructeur de base
	 * @param x
	 * @param y
	 * author : alex
	 */
	public CoordonneesFloat(int x,int y)
	{
		this.x = x;
		this.y = y;
	}
	
	 /** constructeur de base
	 * @param x
	 * @param y
	 * author : alex
	 */
	public CoordonneesFloat(CoordonneesFloat coord)
	{
		this.x = coord.x;
		this.y = coord.y;
	}
	
	public CoordonneesFloat(CoordonneesFloat coord, Direction d)
	{
		switch(d)
		{
		case haut	: this.x=coord.x;	this.y=coord.y-1; break;
		case bas 	: this.x=coord.x;	this.y=coord.y+1; break;	
		case droite	: this.x=coord.x+1;	this.y=coord.y;	break;
		case gauche	: this.x=coord.x-1;	this.y=coord.y;	break;
		default:
			break;
		}
	}
	
	/**
	 * renvoie vrai si les coord corresponde a l'objet
	 * @param coord
	 * @return
	 */
	public boolean equals(CoordonneesFloat coord)
	{
		return (coord.x == this.x && coord.y == this.y);
	}
	
	/**
	 * renvoie la somme des composantes
	 * author : alex
	 */
	public int sommeXY()
	{
		return this.x + this.y;
	}
	
	public int distance(CoordonneesFloat c){
		int	res;
		res=Math.abs(this.x-c.x)+Math.abs(this.y-c.y);
		return res;
	}

	/**
	 * renvoie les coordonnes de la case correspondant au pixel
	 */
	public CoordonneesFloat CasHG(){
		return new CoordonneesFloat(x/size, y/size);
	}

	public CoordonneesFloat CasHD()
	{
		return new CoordonneesFloat((x + size-1) / size, y/ size);
	}
	
	public CoordonneesFloat CasBG()
	{
		return new CoordonneesFloat(x / size,(y+ size-1)/ size);
	}

	public CoordonneesFloat CasBD()
	{
		return new CoordonneesFloat((x+size-1) / size,(y+ size-1)/ size);
	}
	
	public CoordonneesFloat CasCentre()
	{
		return new CoordonneesFloat((x + (size/2))/size, (y + (size/2))/size);
	}
	
	public int casGX()
	{
		return (x/size);
	}
	
	public int casDX()
	{
		return ((x+size-1)/size);
	}
	
	public int casHY()
	{
		return (y/size);
	}
	
	public int casBY()
	{
		return ((y+ size -1)/size);
	}

	public CoordonneesFloat PixHG(){
		return new CoordonneesFloat(x, y);
	}

	public CoordonneesFloat PixHD()
	{
		return new CoordonneesFloat(x + size,y);
	}
	
	public CoordonneesFloat PixBG()
	{
		return new CoordonneesFloat(x,y+ size);
	}

	public CoordonneesFloat PixBD()
	{
		return new CoordonneesFloat(x+ size,y+ size);
	}

	
	public CoordonneesFloat PixCentre()
	{
		return new CoordonneesFloat(x + (size/2), y + (size/2));
	}

	/**
	 * @return : x abscisse d'un Pixel Gauche de la case*/
	public int PixGX()
	{
		return x;
	}
	/**
	 * @return : x abscisse d'un Pixel à Droite de la case*/
	public int PixDX()
	{
		return x+ size;
	}
	/**
	 * @return : y ordonnée du Pixel en Haut de la case*/
	public int PixHY()
	{
		return y;
	}
	/**
	 * @return : y ordonnée du Pixel en Bas de la case*/
	public int PixBY()
	{
		return y+ size;
	}

	/**
	 * renvoie la coordonne x du pixel centrale de la case de coordonne x
	 */
	public int PixcentrX()
	{
		return x + (size/2);
	}
	
	/**
	 * renvoie la coordonne x du pixel centrale de la case de coordonne x
	 */
	public int PixcentrY()
	{
		return y + (size/2);
	}

	/**
	 * fait avancer les coordonné en case d'une case vers la direction donné
	 * edit : prend en compte le tore 
	 * @param d
	 */
	public void avancerDansDir(Direction d)
	{
		Terrain t = Personnage.getTerrain();
		if(t.estCore(x, y, d))
		{
			switch(d)
			{
			case droite:
				x=0;
				break;
			case bas:
				y=0;
				break;
			case gauche:
				y=t.getLargeur()-1;
				break;
			case haut:
				x=t.getHauteur()-1;
				break;
			}
		}
		else if (t.caseAcessible(x, y, d))
		{
			switch(d)
			{
			case haut:
				y--;
				break;
			case bas:
				y++;
				break;
			case droite:
				x++;
				break;
			case gauche:
				x--;
				break;
			}
		}
	}
	
	public String toString()
	{return ""+ x + " " + y;}
	
	public Coordonnees toCoordonnees(){
		return new Coordonnees(this.x, this.y);
	}
	
	public double distance_square(CoordonneesFloat c){
		return (Math.pow(this.x-c.x,2)+Math.pow(this.y-c.y,2));
	}
}

