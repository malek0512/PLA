package view;

import my.work.jeu.Sprites.Direction;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;

public class InputHandler implements InputProcessor {
	static public int tauxDeplacement = 4;
	
	@Override
	public boolean keyDown(int arg0) {
		switch (arg0) {
			case Keys.ESCAPE : Gdx.app.exit(); break;
			case Keys.UP :
				Jeu.posY = Jeu.posY + (float) tauxDeplacement;
//				Jeu.pacman.setY(Jeu.pacman.getY()+tauxDeplacement);
				Sprites.direction = Direction.UP;
				break;
			case Keys.RIGHT :
				Jeu.posX = Jeu.posX + (float) tauxDeplacement;
//				Jeu.pacman.setX(Jeu.pacman.getX()+tauxDeplacement); 
				Sprites.direction = Direction.RIGHT;
				break;
			case Keys.LEFT :
				Jeu.posX = Jeu.posX - (float) tauxDeplacement;
//				Jeu.pacman.setX(Jeu.pacman.getX()-tauxDeplacement); 
				Sprites.direction = Direction.LEFT;
				break;
			case Keys.DOWN :  
				Jeu.posY = Jeu.posY - (float) tauxDeplacement;
//				Jeu.pacman.setY(Jeu.pacman.getY()-tauxDeplacement);
				Sprites.direction = Direction.DOWN;
				break;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		return false;
	}

	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
	 	Vector3 clickCoordinates = new Vector3(screenX,screenY,0);
        Vector3 position = camera.unproject(clickCoordinates);
        TextureMapObject character = (TextureMapObject)tiledMap.getLayers().get("objects").getObjects().get(0);
        character.setX((float)position.x);
        character.setY((float)position.y);
        return true;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}

  private void ManipulerLapin_accelerometre() // => destinée Android
  {
        // Les directions principales
        if(Gdx.input.getAccelerometerY()>1)
        {
               Jeu.posX+=tauxDeplacement;
//               type_animation=7;
        }
        if(Gdx.input.getAccelerometerY()<-1)
        {
               Jeu.posX-=tauxDeplacement;
//               type_animation=3;
        }
        if(Gdx.input.getAccelerometerX()>1)
        {
               Jeu.posY-=tauxDeplacement;
//               type_animation=0;
        }
        if(Gdx.input.getAccelerometerX()<-1)
        {
               Jeu.posY+=tauxDeplacement;
//               type_animation=4;
        }
       
        // La direction initial du Lapin
        if(Gdx.input.getAccelerometerX()<1 && Gdx.input.getAccelerometerY()<1 && Gdx.input.getAccelerometerX()>-1 && Gdx.input.getAccelerometerY()>-1 )
        {
//               animation_stop = true;
//               regionCourante = regionInitial ;
        }else
        {
//               animation_stop = false;
        }
  }
     
//****************** Manipulation entrées accéléromètre***********************************//
}
