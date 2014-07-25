package view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Jeu extends ApplicationAdapter {
	
	public static final int WIDTH = 680;
	public static final int HEIGHT = WIDTH * 9 / 16;
	public static final String TITLE = "PACK-NIGHT : THE RETURN";
	public static final boolean USE_GL30 = false;
	static float posX = 50f, posY = 50f;
	
	SpriteBatch batch;
	static Animation pacman ;
	static Sprites pacmanSprite ;
	SpriteBatch spriteBatch;        // #6

	float stateTime;    
	    
	
	// Camera --------------------- /
	OrthographicCamera camera;
	private Rectangle glViewport;
    private float rotationSpeed;


	@Override
	public void create() {
		super.create();
		Gdx.input.setInputProcessor(new InputHandler());
		batch = new SpriteBatch();
	    stateTime = 0f;
	    pacmanSprite = new Sprites("sprites/Pacman.png");

	    //Camera
	    camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.update();
		
		//Map
		Map.create();
		
		//A conserver
        glViewport = new Rectangle(0, 0, Jeu.WIDTH-20, Jeu.HEIGHT); //dimension du rectagle de vision != fenetre si menu
	    
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
		handleInput();
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);  
		
		pacman = pacmanSprite.loadAnimation();
        stateTime += Gdx.graphics.getDeltaTime();
		
        // Camera --------------------- /
        Gdx.graphics.getGL20().glViewport((int) glViewport.x, (int) glViewport.y,(int) glViewport.width, (int) glViewport.height);
        camera.update();
        
        //Map : voir le detail dans la classe Map
        Map.renderer(camera);
		
		batch.begin();
			batch.draw(pacman.getKeyFrame(stateTime, true), posX, posY); 
    
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

	private void handleInput() {
		int pas = InputHandler.tauxDeplacement*2;
		int boundWidth = WIDTH / 3, boundHeight = HEIGHT / 3;
		
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            camera.zoom += 0.02;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.Q)) {
            camera.zoom -= 0.02;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            camera.rotate(-rotationSpeed, 0, 0, 1);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.E)) {
            camera.rotate(rotationSpeed, 0, 0, 1);
        }
//////////////////////////////////////        
//        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && Jeu.posX<=boundWidth) {
////            	camera.translate(-pas, 0, 0);
//        	
//	    }
//	    if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && Jeu.posX>=boundWidth) {
//	            camera.translate(pas, 0, 0);
//	    }
//	    if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && Jeu.posY<=boundHeight) {
//	            camera.translate(0, -pas, 0);
//	    }
//	    if(Gdx.input.isKeyPressed(Input.Keys.UP) && Jeu.posY>=boundHeight) {
//	            camera.translate(0, pas, 0);
//	    }

        camera.position.x = posX;
    	camera.position.y = posY;
    }
	


}
