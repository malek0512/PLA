package game;

import org.newdawn.slick.Image;
import org.newdawn.slick.tiled.TiledMap;

import structure_terrain.Case;
import structure_terrain.Terrain;

public class Map {
	
	public static void mapToTerrain(Terrain terrain, int largueur_map, int hauteur_map, TiledMap map){
		for(int i=0;i<largueur_map;i++)
		{
			for(int j=0;j<hauteur_map;j++)
			{
		        Image tile_vide = map.getTileImage(i,j,map.getLayerIndex("logic"));
		        boolean vide = tile_vide != null;
		        if (vide) terrain.terrain[i][j] = new Case(0);
		        else 
		        {
				        Image tile_pacgomme = map.getTileImage(i,j,map.getLayerIndex("GUM"));
				        boolean Pacgomme = tile_pacgomme == null;
				        if (!Pacgomme)
				        	{
				        	terrain.terrain[i][j] = new Case(2);
				        	Terrain.nb_pacgum++;
				        	}
				        else terrain.terrain[i][j] = new Case(1);
		        }
			}
		}
	}

}
