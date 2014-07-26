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

    //Map
    public static TiledMap map;
    public static TiledMapRenderer tiledMapRenderer;
    public static int tuileSize = 32;
    public static int collisionLayer = 0;
    public static int gumLayer = 1;
	public static int wallLayer = 2;
	public static float unitScale = 0.75f;
	
	public static void create (){
		map = new TmxMapLoader().load("assets/maps/PACMAN.tmx"); //Charge la Map 
		Personnage.terrain = new Terrain( mapToTerrainInit(map) ); //Initialise le terrain (virtuelle) de personnage
		tiledMapRenderer = new OrthogonalTiledMapRenderer(map, unitScale);
	}

	public static void renderer(OrthographicCamera camera){
		tiledMapRenderer.setView(camera);
		deletePacgumsRender();
		tiledMapRenderer.render(new int[] {wallLayer, gumLayer, collisionLayer});
	}
	
	public static void dispose (){
		map.dispose();
	}
	
	public static Case[][] mapToTerrainInit(TiledMap map){
		int Mur = Case.Mur, Gum = Case.Pacgum;
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("logic");
		Case[][] terrain = new Case[layer.getHeight()][layer.getWidth()];
		String res = "";
		for (int y = 0; y < layer.getHeight() ; y++) {
	         for (int x = 0; x < layer.getWidth(); x++) {
	            TiledMapTileLayer.Cell cell = layer.getCell(x, y);
	            terrain[y][x] = new Case(0);
	            if (cell == null){
	            	terrain[y][x].setAcessCase(Gum);
	            	res += " . ";
	            }else {
	            	terrain[y][x].setAcessCase(Mur);
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
		System.out.println("Heiht"+ layer.getHeight() + "Wodht" + layer.getWidth());
		return terrain;
	}
	
	public static void deletePacgumsRender() {
		Case[][] terrain = Personnage.terrain.terrain;
		TiledMapTileLayer gumLayer = (TiledMapTileLayer) map.getLayers().get("GUM");
		for (int x = 0; x < terrain.length; x++) {
	         for (int y = 0; y < terrain[x].length; y++) {
	            TiledMapTileLayer.Cell gumCell = gumLayer.getCell(y, x);
	            if (gumCell == null || terrain[x][y].getAccessCase()==Case.Pacgum) {
	            	continue; // There is no cell
	            }
	            gumLayer.setCell(y, x, null);
	         }
	    }
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
//	      return (Math.abs(cordf.x - cordp.x) < 2*hitBox) && (Math.abs(cordf.y - cordp.y) < 2*hitBox)
	   }
	
}
