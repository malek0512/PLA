package view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Rectangle;


public class Jeu extends ApplicationAdapter {
	
	public static final int WIDTH = 680;
	public static final int HEIGHT = WIDTH * 9 / 16;
	public static final String TITLE = "PACK-NIGHT : THE RETURN";
	public static final boolean USE_GL30 = false;
	public static final int tuile_size = 32;
	static float posX = 50f, posY = 50f;
	
	public Equipage equip;

	@Override
	public void create() {
		super.create();
		Gdx.input.setInputProcessor(new InputHandler());
		
		//Sprite
		equip = new EquipageMalek();
		
		
		//Map
		Map.create();
		
		//Camera
		Camera.create();
		
	}

	@Override
	public void dispose() {
		super.dispose();
		Map.dispose();
	}

	@Override
	public void pause() {
		super.pause();
	}

	public void render() {
		super.render();
		
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);  
		
        // Camera --------------------- /
		Camera.render();
		
        //Map : voir le detail dans la classe Map
        Map.renderer(Camera.camera);
		
        //Sprite : voir le detail dans la classe Joueur
        equip.suivant();

	}

	@Override
	public void resize(int width, int height) {
		super.resize(width,height);
//		camera.viewportHeight = height / 2.5f;
//		camera.viewportWidth = width / 2.5f;
	}

	@Override
	public void resume() {
		super.resume();
	}

}
