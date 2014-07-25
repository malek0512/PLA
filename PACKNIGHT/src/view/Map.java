package view;

import model.structure_terrain.CoordPix;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

public class Map {

    //Map
    public static TiledMap map;
    public static TiledMapRenderer tiledMapRenderer;
    public static int gumLayer = 1;
	public static int wallLayer = 2;
	public static float unitScale = 0.75f;
	
	public static void create (){
		map = new TmxMapLoader().load("assets/maps/FATMAP.tmx");
		mapToTerrainInit(map);
		tiledMapRenderer = new OrthogonalTiledMapRenderer(map, unitScale);
	}

	public static void renderer(OrthographicCamera camera){
		tiledMapRenderer.setView(camera);
//		deletePacgumsRenderer(null);
		tiledMapRenderer.render(new int[] {wallLayer, gumLayer});
	}
	
	public static void dispose (){
		map.dispose();
	}
	
	public static int[][] mapToTerrainInit(TiledMap map){
		int Mur = 0, Gum = 1;
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("logic");
		int[][] terrain = new int[layer.getWidth()][layer.getHeight()];
		
		for (int x = 0; x < layer.getWidth(); x++) {
	         for (int y = 0; y < layer.getHeight(); y++) {
	            TiledMapTileLayer.Cell cell = layer.getCell(x, y);
	            if (cell == null)
	            	terrain[x][y] = Gum;
	            else
	            	terrain[x][y] = Mur;
	         }
	    }
		return terrain;
	}
	
	public static void deletePacgumsRenderer(int[][] terrain) {
		int gum = 1;
		TiledMapTileLayer gumLayer = (TiledMapTileLayer) map.getLayers().get("GUM");
		for (int x = 0; x < terrain.length; x++) {
	         for (int y = 0; y < terrain[x].length; y++) {
	            TiledMapTileLayer.Cell gumCell = gumLayer.getCell(x, y);
	            if (gumCell == null || terrain[x][y]==gum) {
	            	continue; // There is no cell
	            }
	            gumLayer.setCell(x, y, null);
	         }
	    }
	}
	
	static public boolean personnageHittingPersonnage(float x, float y) {
		Vector2 cordf=cord1.PixCentre();
		Vector2 cordp=cord2.PixCentre();
		return (Math.abs(cordf.x - cordp.x) < 2*hitBox) && (Math.abs(cordf.y - cordp.y) < 2*hitBox);
	}
	
	public boolean can_move(int[][] terrain, float move_x, float move_y){
		int tuileWidth = 32, tuileHeight = 32;
	      boolean next_move_allowed = true;
	      float playerX = Jeu.posX + move_x, playerY = Jeu.posY + move_y;
	      float playerXcase = playerX / tuileWidth;
	      float playerYcase = playerY / tuileHeight;
	      
//	      ArrayList<Collision> objArray = renderer.objArray;
//	      Rectangle player = hero.getHitbox();
//	      player.y = player.y + move_y;
//	      player.x = player.x + move_x;
//	            
//	      for (Collision obj : objArray) {      
//	         if (obj.getBounds().overlaps(player) && obj.isSolid()){
//	            next_move_allowed = false;      
//	         }   
//	      }   
	      
	      return next_move_allowed;
	      return (Math.abs(cordf.x - cordp.x) < 2*hitBox) && (Math.abs(cordf.y - cordp.y) < 2*hitBox)
	   }
	
}
