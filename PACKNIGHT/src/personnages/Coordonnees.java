package personnages;

public class Coordonnees {
	int x;
	int y;
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
}
