package personnages;

public class Coordonnees {
	public int x; //les mettres private ou public change rien au vue de ce qu'on a
	public int y; // "			"			"			"			"			"

	public boolean equals(Coordonnees dest)
	{
		return (dest.x == this.x && dest.y == this.y);
	}
	
	public int sommmeXY()
	{
		return x+y;
	}
	/**
	 * @return the x
	 */
	public int getAbscisse() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setAbscisse(int x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public int getOrdonnee() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setOrdonnee(int y) {
		this.y = y;
	}
	
	public Coordonnees(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public Coordonnees(Coordonnees c) {
		super();
		this.x = c.x;
		this.y = c.y;
	}
}
