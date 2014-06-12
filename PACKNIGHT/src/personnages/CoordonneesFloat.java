package personnages;

import game.*;

public class CoordonneesFloat {
	public float x;
	public float y;
	
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
	public float pixelX()
	{
		return this.x * WindowGame.tuile_size;
	}
	
	/**
	 * renvoie le pixel correspondant a la coordonne y
	 * author : alex
	 */
	public float pixelY()
	{
		return this.y * WindowGame.tuile_size;
	}
	
	/**
	 * constructeur de base
	 * @param x
	 * @param y
	 * author : alex
	 */
	public CoordonneesFloat(float x,float y)
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
	 * renvoie les coordonne casté en int
	 * @require : les coordones de this doivent avoir leur partie decimal a 0 !!!
	 * @author alex
	 */
	public Coordonnees intoInt()
	{
		return new Coordonnees((int) x, (int) y);
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

