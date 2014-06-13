package personnages;

import game.*;

public class CoordonneesFloat {
	public int x;
	public int y;
	
	public float distance(CoordonneesFloat c){
		float res;
		res=Math.abs(this.x-c.x)+Math.abs(this.y-c.y);
		return res;
	}
	public String toString()
	{return ""+ x + " " + y;}
	/**
	 * renvoie le pixel correspondant a la coordonne x
	 * author : alex
	 */
	public int NonPixelX()
	{
		return this.x / WindowGame.tuile_size;
	}
	
	/**
	 * renvoie le pixel correspondant a la coordonne y
	 * author : alex
	 */
	public int NonPixelY()
	{
		return this.y / WindowGame.tuile_size;
	}
	
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
	
	public boolean equals(CoordonneesFloat coord)
	{
		return (coord.x == this.x && coord.y == this.y);
	}
	
	/**
	 * renvoie la somme des composantes
	 * author : alex
	 */
	public float sommeXY()
	{
		return this.x + this.y;
	}
}

