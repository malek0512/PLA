package personnages;

import game.WindowGame;

public class Coordonnees {
	public int x; //les mettres private ou public change rien au vue de ce qu'on a
	public int y; // "			"			"			"			"			"
	final static private int size = WindowGame.tuile_size;
	
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
	
	public boolean equals(Coordonnees dest)
	{
		return (dest.x == this.x && dest.y == this.y);
	}
	
	public int sommmeXY()
	{
		return x+y;
	}
	
	public void setCoordonnées(int x, int y){
		this.x = x; this.y = y;
	}
	
	public double distance(Coordonnees c){
		return Math.sqrt((Math.pow(this.x-c.x,2)+Math.pow(this.y-c.y,2)));
	}

	public double distance_square(Coordonnees c){
		return (Math.pow(this.x-c.x,2)+Math.pow(this.y-c.y,2));
	}
	
	public boolean equals(CoordonneesFloat coord)
	{
		return (coord.x == this.x && coord.y == this.y);
	}
	//inutile déjà donné par CasHG
	public Coordonnees caseFromPixel(){
		return new Coordonnees(this.x/size, this.y/size);
	}
	//ne donne que le premier pixel en haut à gauche de la case x,y utile?
	public Coordonnees pixelFromCase(){
		return new Coordonnees(this.x*size, this.y*size);
	}
	
	public CoordonneesFloat toCoordonneesFloat(){
		return new CoordonneesFloat(this.x, this.y);
	}
	
	/**
	 * renvoie la somme des composantes
	 * author : alex
	 */
	public int sommeXY()
	{
		return this.x + this.y;
	}
	
	/**
	 * renvoie les coordonnes de la case correspondant au pixel
	 */
	public Coordonnees CasHG(){
		return new Coordonnees(x/size, y/size);
	}

	public Coordonnees CasHD()
	{
		return new Coordonnees((x + size-1) / size, y/ size);
	}
	
	public Coordonnees CasBG()
	{
		return new Coordonnees(x / size,(y+ size-1)/ size);
	}

	public Coordonnees CasBD()
	{
		return new Coordonnees((x+size-1) / size,(y+ size-1)/ size);
	}
	
	public Coordonnees CasCentre()
	{
		return new Coordonnees((x + (size/2))/size, (y + (size/2))/size);
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

	public Coordonnees PixHG(){
		return new Coordonnees(x, y);
	}

	public Coordonnees PixHD()
	{
		return new Coordonnees(x + size,y);
	}
	
	public Coordonnees PixBG()
	{
		return new Coordonnees(x,y+ size);
	}

	public Coordonnees PixBD()
	{
		return new Coordonnees(x+ size,y+ size);
	}

	
	public Coordonnees PixCentre()
	{
		return new Coordonnees(x + (size/2), y + (size/2));
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

	public String toString()
	{return ""+ x + " " + y;}
}
