package graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import personnages.CoordonneesFloat;
import personnages.Direction;
import personnages.Personnage;
import structure_terrain.Terrain;
import structure_terrain.Terrain1;


public class Graph {

	public Noeud table[][];
	private int hauteur;
	private int largeur;
	
	
	public Graph(Terrain terrain)
	{
		largeur =terrain.getLargeur();
		hauteur =terrain.getHauteur();
		table = new Noeud[largeur][hauteur];
		for(int i = 0;i<largeur;i++)
		{
			for(int j =0;j<hauteur;j++)
			{
				table[i][j] = new Noeud();
			}
		}
	}
	
	private CoordonneesFloat getCaseDirection(CoordonneesFloat u, Direction d)
	{
		switch(d)
		{
		case bas : return new CoordonneesFloat(u.x,u.y+1);
		case haut : return new CoordonneesFloat(u.x, u.y-1);
		case droite : return new CoordonneesFloat(u.x+1, u.y);
		case gauche : return new CoordonneesFloat(u.x-1, u.y);
		default : return null;
		}
	}
	
	private int couleur(CoordonneesFloat u, Direction d)
	{
		switch(d)
		{
		case bas : 
			if(Personnage.getTerrain().caseAcessible(u.x, u.y+1))
				return table[u.x][u.y+1].couleur;
			break;
		case haut :
			if(Personnage.getTerrain().caseAcessible(u.x, u.y-1))
				return table[u.x][u.y-1].couleur;
		case droite :
			if(Personnage.getTerrain().caseAcessible(u.x+1, u.y))
				return table[u.x+1][u.y].couleur;
		case gauche :
			if(Personnage.getTerrain().caseAcessible(u.x-1, u.y))
				return table[u.x-1][u.y].couleur;
		}
		return 2;
	}
	
	private int nbAdjacent(CoordonneesFloat u)
	{
		int adj = 0;
		for(Direction d : Direction.values())
		{
			if(Personnage.getTerrain().caseAcessible(u.x, u.y, d) && couleur(u,d) == 0 )
				adj++;
		}
		return adj;
	}
	
	/** Pour remettre en situation initiale chaque sommet **/
    public void reset(){
    	for(int i =0; i<largeur;i++)
    	{
    		for(int j=0;j<hauteur;j++)
    			table[i][j].reset();
    	}
    }
    
    private void removeMG(List<CoordonneesFloat> res, CoordonneesFloat c)
    {
    	Iterator<CoordonneesFloat> i = res.iterator();
    	while(i.hasNext())
    	{
    		int cpt = 0;
    		if(i.next().equals(c))
    		{
    			res.remove(cpt);
    			break;
    		}
    		cpt++;
    	}
    }

    /**
     * @param noeud : coordonnée de la case a gank
     * @return : liste des points statégique pour le gank
     */
    public List<CoordonneesFloat> visiterLargeur(CoordonneesFloat noeud, int nbInter){
    
    int nbInterFind=0;
    int nbInterSearch = 0;
    
    Noeud init = table[noeud.x][noeud.y]; 
	init.couleur = 2; // noir
	
	List<CoordonneesFloat> res =  new LinkedList<CoordonneesFloat>();
	List<CoordonneesFloat> file = new LinkedList<CoordonneesFloat>();

	file.add(noeud);
	//algo de parcours
	while (!file.isEmpty()){
	    CoordonneesFloat u = file.remove(0);
	    Noeud ncourant = table[u.x][u.y];
	    //calcule du nombre d'adjacent
		int cptAdj = this.nbAdjacent(u);

		if(cptAdj <= nbInter - nbInterFind - nbInterSearch)
		{
			//mis a jours de interSearch
			nbInterSearch = nbInterSearch - 1 + cptAdj;
			//on ajoute tout les adjacent a la liste
			for(Direction d : Direction.values())
			{
				if(Personnage.getTerrain().caseAcessible(u.x, u.y, d))
				{
					CoordonneesFloat v = this.getCaseDirection(u, d); 
					if(Personnage.getTerrain().caseAcessible(v.x, v.y))
					{
					Noeud adj = table[v.x][v.y];
					if (adj.couleur==0) //blanc
					{
					    // adj.pere = ncourant;
					    adj.couleur=1; // gris 
					    file.add(v);
				    }
					else if (adj.couleur==1) //gris
					{
						//remove le adj gris trouver
						removeMG(res,v);
						//mis a jour du nombre d'inter trouver
						nbInterFind--;
						adj.couleur=2; //noir
						file.add(v);
					}
					}
				}
			}
			//fini avec ce noeud
			ncourant.couleur = 2;
		}
		else
		{
			//on passe l'état a gris
			ncourant.couleur = 1; //gris
			//on ajoute les coordone au resultat
			res.add(u);
			//on arrete de chercher une intersection
			nbInterSearch--;
			//on a trouver une intersectio
			nbInterFind++;
		}
	}
    return res;
    }
  

    public static void main(String[] args) {
    	Terrain terrain = new Terrain1(10, 10);
    	Personnage.initTerrain(terrain); 
    	CoordonneesFloat start = new CoordonneesFloat(1,1);
    	Graph g = new Graph(terrain);
    	List<CoordonneesFloat> l = g.visiterLargeur(start,2);
    	
    	Iterator<CoordonneesFloat> i = l.iterator();
    	while(i.hasNext())
    	{
    		CoordonneesFloat x = i.next();
    	}
    	terrain.afficher();
    			
    }
    
    
	
}
