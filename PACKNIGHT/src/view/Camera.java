package view;

import model.structure_terrain.CoordPix;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;

public class Camera {

	public static OrthographicCamera camera;
	public static Rectangle glViewport;
    private static float rotationSpeed;
    private static CoordPix joueurCamera = Equipage.joueurCamera.coordPix;
    private static float xCamera, yCamera;
    private static float resolution_x = Jeu.WIDTH, resolution_y = Jeu.HEIGHT;
    private static float largeur_map = ((TiledMapTileLayer) Map.map.getLayers().get(Map.wallLayer)).getWidth();
    private static float hauteur_map = ((TiledMapTileLayer) Map.map.getLayers().get(Map.wallLayer)).getHeight();
    
	public static void create(){
	    camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.update();

		xCamera = camera.position.x;
		yCamera = camera.position.y;
		//A conserver
        glViewport = new Rectangle(0, 0, Jeu.WIDTH-20, Jeu.HEIGHT); //dimension du rectangle de vision != fenetre si menu
	}
	
	public static void render(){
		handleInput();
		Gdx.graphics.getGL20().glViewport((int) glViewport.x, (int) glViewport.y,(int) glViewport.width, (int) glViewport.height);
        camera.update();
	}
	
	private static void handleInput() {
		int pas = InputHandler.tauxDeplacement*2;
		
		int boundWidth = Jeu.WIDTH / 3, boundHeight = Jeu.HEIGHT / 3;
		
        if(Gdx.input.isKeyPressed(Input.Keys.Z)) {
            camera.zoom += 0.02;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.E)) {
            camera.zoom -= 0.02;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            camera.rotate(-rotationSpeed, 0, 0, 1);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.X)) {
            camera.rotate(rotationSpeed, 0, 0, 1);
        }
//////////////////////////////////////        
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && joueurCamera.x<=boundWidth) {
            	camera.translate(-pas, 0, 0);
        	
	    }
	    if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && joueurCamera.x>=boundWidth) {
	            camera.translate(pas, 0, 0);
	    }
	    if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && joueurCamera.y<=boundHeight) {
	            camera.translate(0, -pas, 0);
	    }
	    if(Gdx.input.isKeyPressed(Input.Keys.UP) && joueurCamera.y>=boundHeight) {
	            camera.translate(0, pas, 0);
	    }

        camera.position.x = joueurCamera.x;
    	camera.position.y = joueurCamera.y;
    	
//    	if(joueurCamera!=null)
//    	{
//
//    	float w = Jeu.WIDTH / 4;
//    	if (! (joueurCamera.x - xCamera > resolution_x / 2 || joueurCamera.x - xCamera < -resolution_x / 2)) {
//    		if (joueurCamera.x + largeur_map* taille_minimap > (xCamera + w)&& (joueurCamera.x + w < largeur_map* Map.tuileSize))
//    			xCamera = joueurCamera.x - w+ largeur_map * taille_minimap;
//    		if (joueurCamera.x < (xCamera - w)&& (joueurCamera.x > w))
//    			xCamera = joueurCamera.x + w;
//    	} else if ((joueurCamera.x - xCamera > resolution_x / 2))
//    		xCamera = largeur_map * Map.tuileSize - resolution_x / 2+ largeur_map * taille_minimap;
//    	else if ((joueurCamera.x - xCamera < -resolution_x / 2))
//    		xCamera = resolution_x / 2;
//
//    	float h = Jeu.HEIGHT / 4;
//    	if (!(joueurCamera.y - yCamera > resolution_y / 2 || joueurCamera.y - yCamera < -resolution_y / 2)) {
//    		if (joueurCamera.y > (yCamera + h)&& (joueurCamera.y + h < hauteur_map* Map.tuileSize))
//    			yCamera = joueurCamera.y - h;
//    		if (joueurCamera.y < (yCamera - h)&& (joueurCamera.y > h))
//    			yCamera = joueurCamera.y + h;
//    	} else if ((joueurCamera.y - yCamera > resolution_y / 2))
//    		yCamera = hauteur_map * Map.tuileSize - resolution_y / 2;
//    	else if ((joueurCamera.y - yCamera < -resolution_y / 2))
//    		yCamera = resolution_y / 2;
//    	}
    }


}
