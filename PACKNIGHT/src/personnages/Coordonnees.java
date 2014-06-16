package personnages;

public class Coordonnees {
	public int x; //les mettres private ou public change rien au vue de ce qu'on a
	public int y; // "			"			"			"			"			"
	
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
	
	public String toString(){
		return ""+ x + " " + y;
	}
	
	public boolean equals(Coordonnees dest)
	{
		return (dest.x == this.x && dest.y == this.y);
	}
	
	public int sommmeXY()
	{
		return x+y;
	}
	
	public int getAbscisse() {
		return x;
	}
	
	public void setAbscisse(int x) {
		this.x = x;
	}
	
	public int getOrdonnee() {
		return y;
	}

	public void setOrdonnee(int y) {
		this.y = y;
	}
	
	public double distance(Coordonnees c){
		return Math.sqrt((Math.pow(this.x-c.x,2)+Math.pow(this.y-c.y,2)));
	}
}
