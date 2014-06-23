package graph;

import structure_terrain.CoordonneesFloat;

public class NoeudEtoile {

    public int distance; // 0 blanc // 1 gris // 2 noir
    public NoeudEtoile pere;
    public CoordonneesFloat cord;
    
	public NoeudEtoile(int d, NoeudEtoile p, CoordonneesFloat c)
	{
		distance =d;
		pere = p;
		cord = c;
	}
	
	public boolean equals(NoeudEtoile p)
	{
		return this.cord.equals(p.cord);
	}
	
	public String toString()
	{
		return "coord : " + this.cord + " d : " + this.distance;
		
	}
}
