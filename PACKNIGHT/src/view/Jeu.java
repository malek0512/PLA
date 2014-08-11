package view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.model.Animation;
import com.badlogic.gdx.math.Rectangle;


public class Jeu extends ApplicationAdapter {
	
	public static final int WIDTH = 1080;
	public static final int HEIGHT = 720; //WIDTH * 9 / 16;
	public static final String TITLE = "PACK-NIGHT : THE RETURN";
	public static final boolean USE_GL30 = false;
	public static final int tuile_size = 32;
	public static SpriteBatch batch;
	public static float stateTime;
	public static Equipage equipe;

	public Jeu(Equipage equipe){
		super();
		this.equipe = equipe;
	}
	
	@Override
	public void create() {
		super.create();
		
		
		Gdx.input.setInputProcessor(new InputHandler());
		
		//Sprite
		batch = new SpriteBatch(); //Feuille sur laquelle les sprites sont dessinés
		
	    stateTime = 0f;
//	    pacmanSprite = new S		prites(Sprites.Princess);
	    
		//Map
		Map.create();
		
		equipe.create();
		
		//Camera
		Camera.create();
		
		
	}

	@Override
	public void dispose() {
		super.dispose();
		Map.dispose();
		Jeu.batch.dispose();
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
        Map.render(Camera.camera);
		
        //Sprite
        stateTime += Gdx.graphics.getDeltaTime(); //Calcul de decalage Delta entre chaque rafraichissement du sprite

        equipe.render(); //Voir Joueur.render()
        batch.setProjectionMatrix(Camera.camera.combined); //Colle le sprit a la map (ou camera je sais plus)
//        mama = pacmanSprite.loadAnimation();
        
		batch.begin();
			equipe.draw();
//			batch.draw(mama.getKeyFrame(stateTime, true), 50, 50); 
			
		batch.end();
		

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
