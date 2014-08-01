package view;

import model.personnages.Personnage;
import model.structure_terrain.CoordPix;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;

public class Camera {

	/** REMARQUE
	 * Toute les fonction static ont pour but d'alleger la classe Jeu.java principale. Pour ainsi séparer la gestion de
	 * la camera et celle de la map etc...
	 */
	
	public static OrthographicCamera camera;
	public static Rectangle glViewport;
    private static float rotationSpeed;
    private static CoordPix joueurCamera = Equipage.joueurCamera.coordPix;
    private static float xCamera, yCamera;
    private static float resolution_x = Jeu.WIDTH, resolution_y = Jeu.HEIGHT;
    private static float largeur_map = ((TiledMapTileLayer) Map.map.getLayers().get(Map.wallLayer)).getWidth();
    private static float hauteur_map = ((TiledMapTileLayer) Map.map.getLayers().get(Map.wallLayer)).getHeight();
    
    static int pas = Personnage.tauxDeDeplacement;
	static int boundWidth = Jeu.WIDTH / 2 + 32;
	static int boundHeight = Jeu.HEIGHT / 2 + 32;
	static int boundWidthComplementaire = Jeu.WIDTH - boundWidth;
	static int boundHeightComplementaire = Jeu.HEIGHT - boundHeight;
	
	public static void create(){
	    camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.update();

		camera.position.x = Jeu.WIDTH/2;
		camera.position.y = Jeu.HEIGHT/2;

		//A conserver
        glViewport = new Rectangle(0, 0, Jeu.WIDTH - 0, Jeu.HEIGHT); //dimension du rectangle de vision != fenetre si menu
	}
	
	public static void render(){
		handleInput();
		Gdx.graphics.getGL20().glViewport((int) glViewport.x, (int) glViewport.y,(int) glViewport.width, (int) glViewport.height);
        camera.update();
	}
	
	private static void handleInput() {
		
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

        

/** Scrolling mis en commentaire en periode de test 
 * */ 
        camera.position.x = joueurCamera.x;
    	camera.position.y = joueurCamera.y;
//        if (camera.position.x - 0 >= Jeu.WIDTH/2 && camera.position.x <= Map.mapWidth - Jeu.WIDTH/2){
//        	//La camera n'affiche jamais la surface hors terrain
//        	System.out.println(Math.abs(camera.position.x - joueurCamera.x) + " " + boundWidthComplementaire );
//        	if (camera.position.x - joueurCamera.x >= 0 && Math.abs(camera.position.x - joueurCamera.x) > boundWidthComplementaire ){
//        		//Si pacman est a gauche du centre de la camera, et uea.position.x - posX >= 0  la distance(camera_centre, pacman) depasse pas la lmite
//        		camera.translate(-InputHandler.tauxDeplacement, 0, 0);
//        	} else if (camera.position.x - joueurCamera.x <= 0 && Math.abs(camera.position.x - joueurCamera.x) > boundWidthComplementaire ){
//        		//Si pacman est a droite du centre de la camera, et que la distance(camera_centre, pacman) depasse la limite
//        		camera.translate(InputHandler.tauxDeplacement, 0, 0);
//        	}
//        }
//        
//        if (camera.position.y - 0 >= Jeu.HEIGHT/2 && camera.position.y <= Map.mapHeight - Jeu.HEIGHT/2){
//        	if (camera.position.y - joueurCamera.y >= 0 && Math.abs(camera.position.y - joueurCamera.y) > boundHeightComplementaire ){ 
//        		//Si pacman est en dessous du centre de la camera, et que la distance(camera_centre, pacman) depasse pas la lmite
//        		camera.translate(0,-InputHandler.tauxDeplacement, 0);
//        	} else if (camera.position.y - joueurCamera.y <= 0 && Math.abs(camera.position.y - joueurCamera.y) > boundHeightComplementaire ){
//        		//Si pacman est au dessus du centre de la camera, et que la distance(camera_centre, pacman) depasse la limite
//        		camera.translate(0,InputHandler.tauxDeplacement, 0);
//        	}
//        }
    }


}
