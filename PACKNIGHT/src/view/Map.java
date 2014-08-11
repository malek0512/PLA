package view;

import model.personnages.Personnage;
import model.structure_terrain.Case;
import model.structure_terrain.CoordPix;
import model.structure_terrain.Terrain;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

public class Map {

	/** REMARQUE
	 * Toute les fonction static ont pour but d'alleger la classe Jeu.java principale. Pour ainsi séparer la gestion de
	 * la camera et celle de la map etc...
	 */
	
    //Map
    public static TiledMap map;
    public static TiledMapRenderer tiledMapRenderer;
    public static int tuileSize = 32;
    public static int mapWidth = -1;
    public static int mapHeight = -1;
    public static int collisionLayer = 0;
    public static int gumLayer = 1;
	public static int wallLayer = 2;
	public static float unitScale = 1f;
	
	/**
	 * Initialise tout ce qui est relatif a la map
	 * Fonction appellée dans la classe princiaple Jeu.ajava
	 */
	public static void create (){
		map = new TmxMapLoader().load("maps/PACMAN.tmx"); //Charge la Map
		mapWidth = ((TiledMapTileLayer) map.getLayers().get(wallLayer)).getWidth()*32;
		mapHeight = ((TiledMapTileLayer) map.getLayers().get(wallLayer)).getWidth()*32;
		Personnage.terrain = new Terrain( mapToTerrainInit(map) ); //Initialise le terrain (virtuelle) de personnage
		tiledMapRenderer = new OrthogonalTiledMapRenderer(map, unitScale);
	}

	/**
	 * Fonction 
	 * @param camera
	 * @author malek
	 */
	public static void render(OrthographicCamera camera){
		tiledMapRenderer.setView(camera);
		deletePacgumsRender();
		tiledMapRenderer.render(new int[] {wallLayer, gumLayer, collisionLayer});
	}
	
	public static void dispose (){
		map.dispose();
	}
	
//	public static int[][] mapToTerrainInit(TiledMap map){
//		int Mur = 0, Gum = 1;
//		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("logic");
//		int[][] terrain = new int[layer.getHeight()][layer.getWidth()];
//		
//		for (int x = 0; x < layer.getWidth(); x++) {
//	         for (int y = 0; y < layer.getHeight(); y++) {
//	            TiledMapTileLayer.Cell cell = layer.getCell(x, y);
//	            if (cell == null)
//	            	terrain[y][x] = Gum;
//	            else
//	            	terrain[y][x] = Mur;
//	         }
//	    }
//		return terrain;
//	}

	/**
	 * Convertie la map.tmx en un terrain. Vous remarquerez que dans ma conception je fais
	 * terrain[y][x] <-- layer.getCell(x, y); Ce qui je pense a la source principale de l'incapatibilité avec la 
	 * fonction avancer()
	 * @param map
	 * @return terrain
	 * @author malek
	 */
	public static Case[][] mapToTerrainInit(TiledMap map){
		int Mur = Case.Mur, Gum = Case.Pacgum;
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("logic");
		Case[][] terrain = new Case[layer.getWidth()][layer.getHeight()];
		String res = "";
		for (int x = 0; x < layer.getWidth(); x++) {
	         for (int y = 0; y < layer.getHeight(); y++) {
	            TiledMapTileLayer.Cell cell = layer.getCell(x, y);
	            terrain[x][y] = new Case(0);
	            if (cell == null){
	            	terrain[x][y].setAcessCase(Gum);
	            	res += " . ";
	            }else {
	            	terrain[x][y].setAcessCase(Mur);
	            	res += " X ";
	            }
	         }
	         res += "\n";
	    }


		System.out.println(res);
		System.out.println("----------------------------------------------------------------");
		
		String res2 = "";
		for(int i=0; i<terrain.length; i++){
			for(int j=0; j<terrain[i].length; j++){
				res2 += terrain[i][j].isAccessable()? "." : "X";
			}
	         res2 += "\n";
		}
		System.out.println(res);
		System.out.println("Height"+ layer.getHeight() + " Width" + layer.getWidth());
		return terrain;
	}
	
	/**
	 * Syncronise les pacgum mangé entre le terrain et la fenetre graphique
	 * @author malek 
	 */
	public static void deletePacgumsRender() {
		Case[][] terrain = Personnage.terrain.terrain;
		TiledMapTileLayer gumLayer = (TiledMapTileLayer) map.getLayers().get("GUM");
		for (int x = 0; x < terrain.length; x++) {
	         for (int y = 0; y < terrain[x].length; y++) {
	            TiledMapTileLayer.Cell gumCell = gumLayer.getCell(x, y);
	            if (gumCell == null || terrain[x][y].getAccessCase()==Case.Pacgum
	            		|| terrain[x][y].getAccessCase()==Case.Mur) {
	            	continue; // There is no cell
	            }
	            gumLayer.setCell(x, y, null);
	         }
	    }
	}
	
	
	public boolean can_move(int[][] terrain, float move_x, float move_y){
		int tuileWidth = 32, tuileHeight = 32;
	      boolean next_move_allowed = true;
//	      float playerX = Jeu.posX + move_x, playerY = Jeu.posY + move_y;
//	      float playerXcase = playerX / tuileWidth;
//	      float playerYcase = playerY / tuileHeight;
	      
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
//	      return (Math.abs(cordf.x - cordp.x) < 2*hitBox) && (Math.abs(cordf.y - cordp.y) < 2*hitBox)
	   }
	
}
