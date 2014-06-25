package graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import personnages.Personnage;
import structure_terrain.CoordCas;
import structure_terrain.Direction;
import structure_terrain.Terrain;


public class Graph {

	public Noeud table[][];
	private int hauteur;
	private int largeur;
	
	
	public Graph()
	{
		largeur =Personnage.terrain.largeur;
		hauteur =Personnage.terrain.hauteur;
		table = new Noeud[largeur][hauteur];
		for(int i = 0;i<largeur;i++)
		{
			for(int j =0;j<hauteur;j++)
			{
				if(Personnage.terrain.caseAcessible(new CoordCas(i,j)))
					table[i][j] = new Noeud(0);
				else
					table[i][j] = new Noeud(2);
			}
		}
	}
	
	private int couleur(CoordCas u, Direction d)
	{
		CoordCas tmp = new CoordCas(u);
		tmp.avancerDansDir(d);
		if(Personnage.terrain.estDansLeTerrain(tmp))
				return table[tmp.x][tmp.y].couleur;
		return 2;
	}
	
	private int nbBlanc(CoordCas u)
	{
		int adj = 0;
		for(Direction d : Direction.values())
		{
			if(Personnage.terrain.caseAcessible(u, d) && couleur(u,d) == 0 )
				adj++;
		}
		return adj;
	}
	
	private int nbGris(CoordCas u)
	{
		int adj = 0;
		for(Direction d : Direction.values())
		{
			if(Personnage.terrain.caseAcessible(u, d) && couleur(u,d) == 1 )
				adj++;
		}
		return adj;
	}
    
    private void removeMG(List<CoordCas> res, CoordCas c)
    {
    	Iterator<CoordCas> i = res.iterator();
    	int cpt = 0;
    	while(i.hasNext())
    	{
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
    public List<CoordCas> visiterLargeur(CoordCas noeud, int nbInter){
    
    int nbInterFind = 0;
    int nbInterSearch = nbBlanc(noeud);
    nbInter = 2;
    table[noeud.x][noeud.y].couleur = 2; 
	
	List<CoordCas> res =  new LinkedList<CoordCas>();
	List<CoordCas> file = new LinkedList<CoordCas>();

	file.add(noeud);
	//algo de parcours
	while (!file.isEmpty()){
		CoordCas cordCasEnTraitement = file.remove(0);
	    //calcule du nombre d'adjacent
		int nbBlanc = nbBlanc(cordCasEnTraitement);
		int nbGris = nbGris(cordCasEnTraitement);
		boolean autorisationDeSearch = ((nbInterSearch-1) + (nbInterFind-nbGris) + nbBlanc <= nbInter);
		nbInterSearch--;
		if(autorisationDeSearch)
		{
			for(Direction d : Direction.values())
			{
				if(Personnage.terrain.caseAcessible(cordCasEnTraitement,d))
				{
					CoordCas cordVoisine = new CoordCas(cordCasEnTraitement);
					cordVoisine.avancerDansDir(d);
					if(table[cordVoisine.x][cordVoisine.y].couleur == 0)
					{
						file.add(cordVoisine);
						nbInterSearch++;
					}
					else if(table[cordVoisine.x][cordVoisine.y].couleur == 1)
					{
						file.add(cordVoisine);
						removeMG(res, cordVoisine);
						nbInterFind--;
						table[cordVoisine.x][cordVoisine.y].couleur=2;
					}
				}
			}
			table[cordCasEnTraitement.x][cordCasEnTraitement.y].couleur = 2;
		}
		else
		{
			table[cordCasEnTraitement.x][cordCasEnTraitement.y].couleur = 1;
			nbInterFind++;
			nbInterSearch--;
		}
	}
    return res;
    }
}
