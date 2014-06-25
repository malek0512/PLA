package graph;

public class Noeud {

    public int couleur; // 0 blanc // 1 gris // 2 noir
	
	public Noeud(int couleur)
	{
		this.couleur = couleur;
	}
	
	public void reset()
	{
		couleur = 0;
	}
}
