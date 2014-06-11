package personnages;

import game.*;

public class CoordonneesFloat {
	public float x;
	public float y;
	
	/**
	 * mediter quant a son utilisation...
	 * author : mysterious guy
	 */
	public CoordonneesFloat(int x,int y)
	{
		this.x = x * WindowGame.tuile_size;
		this.y = y * WindowGame.tuile_size;
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
}

