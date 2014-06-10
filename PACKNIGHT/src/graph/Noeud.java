package graph;

import java.util.List;

import personnages.Coordonnees;
import structure_terrain.Terrain;

public class Noeud {

    public int couleur; // 0 blanc // 1 gris // 2 noir
    public Noeud pere;
    
    
	public Noeud()
	{
		couleur =0;
		pere = null;
	}
	
	public void reset()
	{
		couleur = 0;
		pere = null;
	}
	
	public List<Coordonnees> listAdj()
	{
		return null;
	}
	
}
