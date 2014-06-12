package graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.naming.NamingEnumeration;

import personnages.Coordonnees;
import personnages.CoordonneesFloat;
import personnages.Direction;
import personnages.Personnage;
import structure_terrain.Terrain;
//import structure_terrain.Terrain1;
import structure_terrain.TerrainTest1;

public class Graph {

	static public Noeud table[][];
	static private int hauteur;
	static private int largeur;
	
	
	public Graph(Terrain terrain)
	{
		largeur =terrain.getLargeur();
		hauteur =terrain.getHauteur();
		table = new Noeud[largeur][hauteur];
	}
	
	public Graph(TerrainTest1 terrain) {
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
	
	/** Pour remettre en situation initiale chaque sommet **/
    public final void reset(){
    	for(int i =0; i<largeur;i++)
    	{
    		for(int j=0;j<hauteur;j++)
    			table[i][j].reset();
    	}
    }

    public static List<Coordonnees> visiterLargeur(Coordonnees noeud, int nbInter){
    	
    int nbInterFind=0;
    int nbInterSearch = 0;
    Noeud init = table[noeud.x][noeud.y]; 
	init.couleur = 2; // noir
	
	List<Coordonnees> res =  new LinkedList<Coordonnees>();
	List<Coordonnees> file = new LinkedList<Coordonnees>();

	//initialisation du nbInterSearch
	for(Direction d : Direction.values())
	{
		if(Personnage.getTerrain().getCase(noeud , d).isAccessable())
			{
				nbInterSearch++;
				file.add(Personnage.getTerrain().getCoordonnees(noeud,d));
			}
	}
	
	//algo de parcours
	while (!file.isEmpty()){
	    Coordonnees u = file.remove(0);
	    Noeud ncourant = table[u.x][u.y];
	    //calcule du nombre d'adjacent
		int cptAdj=0;
		for(Direction d : Direction.values())
		{
			if(Personnage.getTerrain().getCase(u,d).isAccessable())
				cptAdj++;
		}
		cptAdj--; //on retire le papa des adjacente
		if(cptAdj <= nbInter - nbInterFind - (nbInterSearch - 1))
		{
			//mis a jours de interSearch
			nbInterSearch = nbInterSearch - 1 + cptAdj;
			//on ajoute tout les adjacent a la liste
			for(Direction d : Direction.values())
			{
				if(Personnage.getTerrain().getCase(u,d).isAccessable())
				{
					Coordonnees v = Personnage.getTerrain().getCoordonnees(u,d); 
					Noeud adj = table[v.x][v.y];
					if (adj.couleur==0) //blanc
					{
					    adj.pere = ncourant;
					    adj.couleur=1; // gris 
					    file.add(v);
				    }
					else if (adj.couleur==1) //gris
					{
						//remove le adj gris trouver
						//TODO
						//mis a jout du nombre d'inter trouver
						nbInterFind--;
						adj.couleur=2; //noir
						file.add(v);
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
    public void a_star(CoordonneesFloat coord){
    	
    }

    public static void main(String[] args) {
    	TerrainTest1 terrain = new TerrainTest1(10, 10);
    	Personnage.initTerrain(terrain); 
    	Coordonnees start = new Coordonnees(1,1);
    	Graph g = new Graph(terrain);
    	
    	List<Coordonnees> l = Graph.visiterLargeur(start,3);
    	
    	Iterator<Coordonnees> i = l.iterator();
    	while(i.hasNext())
    	{
    		Coordonnees x = i.next();
    		terrain.setCase(x.x, x.y, 2);
    	}
    	terrain.afficher();
    			
    }
    
    
	
}
