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
	
	static private CoordonneesFloat teteDeliste;
	
	private int distance(CoordonneesFloat c1, CoordonneesFloat c2)
	{
		return Math.abs(c1.x - c2.x) + Math.abs(c1.y - c2.y);
	}
	
	private int distance(CoordonneesFloat c1)
	{
		return distance(c1,Aetoile.teteDeliste);
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
	
	public Aetoile(CoordonneesFloat teteDeliste)
	{
		ouvert = new LinkedList<NoeudEtoile>();
		fermer = new LinkedList<NoeudEtoile>();
		Aetoile.teteDeliste = teteDeliste;
	}
	
	/**
	 * reinit le graph pour aetoile
	 */
	public void reinit()
	{
		ouvert = new LinkedList<NoeudEtoile>();
		fermer = new LinkedList<NoeudEtoile>();
	}
	
	/**
	 * extrait le plus petit element
	 * @param list
	 * @return
	 */
	private NoeudEtoile extract(List<NoeudEtoile> list)
	{
		int min = Integer.MAX_VALUE;
		int indice=0;
		int indiceMin = 0;
		NoeudEtoile res = null;
		Iterator<NoeudEtoile> i = list.iterator();
		while(i.hasNext())
		{
			NoeudEtoile tmp = i.next();
			System.out.println("d : " + tmp.distance);
			if(tmp.distance < min)
			{
				min = tmp.distance;
				res = tmp;
				indiceMin = indice;
			}
			indice++;
		}
		System.out.println("min trouvé : " + min);
		list.remove(indiceMin);
		return res;
	}
	
	/**
	 * renvoie la l'itinairaire
	 * @param queueDeListe
	 * @return
	 */
	public List<CoordonneesFloat> algo(CoordonneesFloat queueDeListe)
	{
		boolean continu = true;
		NoeudEtoile init = new NoeudEtoile(distance(queueDeListe), null, queueDeListe);
		ouvert.add(init);
		while(!ouvert.isEmpty() && continu)
		{
			NoeudEtoile m = extract(ouvert);
			fermer.add(m);
			
			for(Direction d : Direction.values())
			{
				System.out.println("case tester : " + m);
				System.out.println("Direction : " + d);
				if(Personnage.getTerrain().caseAcessible(m.cord.x, m.cord.y, d))
				{	
					System.out.println("Accepter");
					CoordonneesFloat cordFi = new CoordonneesFloat(m.cord,d);
					NoeudEtoile fi = new NoeudEtoile(distance(cordFi), m, cordFi);
					System.out.println("fils : "+fi);
					
					fi.pere = m;
					
					System.out.println();
					if (fi.cord.equals(Aetoile.teteDeliste))
					{
						init = fi;
						continu=false;
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
		res.add(queueDeListe);
		return res;
	}
	
	   public static void main(String[] args) {
		   	
		   	Terrain terrain = new Terrain1(10, 10);
	    	Personnage.initTerrain(terrain); 
	    	
	    	CoordonneesFloat start = new CoordonneesFloat(1,1);
	    	CoordonneesFloat finish = new CoordonneesFloat(4,6);
	    	
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


