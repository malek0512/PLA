package graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import personnages.CoordonneesFloat;
import personnages.Direction;
import personnages.Personnage;
import structure_terrain.Terrain;
import structure_terrain.Terrain1;

public class Aetoile {

	private List<NoeudEtoile> ouvert;
	
	private List<NoeudEtoile> fermer;
	
	static private CoordonneesFloat dest;
	
	private int distance(CoordonneesFloat c1, CoordonneesFloat c2)
	{
		return Math.abs(c1.x - c2.x) + Math.abs(c1.y - c2.y);
	}
	
	private int distance(CoordonneesFloat c1)
	{
		return distance(c1,Aetoile.dest);
	}
	
	private boolean appartient(List<NoeudEtoile> l, NoeudEtoile fi)
	{
		Iterator<NoeudEtoile> i = l.iterator();
		while(i.hasNext())
		{
			if(fi.equals(i.next()))
				return true;
		}
		return false;
	}
	
	public Aetoile(CoordonneesFloat dest)
	{
		ouvert = new LinkedList<NoeudEtoile>();
		fermer = new LinkedList<NoeudEtoile>();
		Aetoile.dest = dest;
	}
	
	private NoeudEtoile extract(List<NoeudEtoile> list)
	{
		int min = 255;
		int indice=0;
		int indiceMin = 0;
		NoeudEtoile res = null;
		Iterator<NoeudEtoile> i = list.iterator();
		while(i.hasNext())
		{
			NoeudEtoile tmp = i.next();
			if(tmp.distance < min)
			{
				min = tmp.distance;
				res = tmp;
				indiceMin = indice;
			}
			indice++;
		}
		list.remove(indiceMin);
		return res;
	}
	
	public List<CoordonneesFloat> algo(CoordonneesFloat src)
	{
		NoeudEtoile init = new NoeudEtoile(distance(src), null, src);
		ouvert.add(init);
		
		while(!ouvert.isEmpty())
		{
			NoeudEtoile m = extract(ouvert);
			fermer.add(m);
			
			for(Direction d : Direction.values())
			{
				if(Personnage.getTerrain().caseAcessible(m.cord.x, m.cord.y, d))
				{	
					CoordonneesFloat cordFi = new CoordonneesFloat(m.cord,d);
					NoeudEtoile fi = new NoeudEtoile(distance(cordFi), m, cordFi);
					
					fi.pere = m;
					
					if (fi.cord.equals(Aetoile.dest))
					{
						init = fi;
						break;
					}
					else if(!appartient(ouvert,fi) && !appartient(fermer,fi))
						ouvert.add(fi);
				}
			}
		}
		
		List<CoordonneesFloat> res = new LinkedList<CoordonneesFloat>();
		//on crée la liste de resultat et on la retourne
		while(init.pere != null)
		{
			res.add(init.cord);
			init = init.pere;
		}
		res.add(src);
		res.remove(0);
		return res;
	}
	
	   public static void main(String[] args) {
		   	
		   	Terrain terrain = new Terrain1(10, 10);
	    	Personnage.initTerrain(terrain); 
	    	
	    	CoordonneesFloat start = new CoordonneesFloat(1,1);
	    	CoordonneesFloat finish = new CoordonneesFloat(3,6);
	    	
	    	Aetoile a = new Aetoile(start);
	    	List<CoordonneesFloat> l = a.algo(finish);
	    	
	    	Iterator<CoordonneesFloat> i = l.iterator();
	    	while(i.hasNext())
	    	{
	    		
	    		CoordonneesFloat x = i.next();
	    		System.out.println(x);
	    		Personnage.getTerrain().setCase(x.x, x.y, 2);
	    	}
	    	terrain.afficher();
	    }
}


