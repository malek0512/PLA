package view;

import model.personnages.Personnage;
import model.structure_terrain.Direction;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;

public class InputHandler implements InputProcessor {
	public static Personnage p = Equipage.joueurCamera;
	static public int tauxDeplacement = Personnage.tauxDeDeplacement;
	
	@Override
	public boolean keyDown(int arg0) {
		switch (arg0) {
			case Keys.ESCAPE : Gdx.app.exit(); break;
			case Keys.UP :
				Equipage.joueurFleche.setNextDirection(model.structure_terrain.Direction.bas);
				Equipage.joueurCamera.setNextDirection(model.structure_terrain.Direction.bas);
				break;
			case Keys.RIGHT :
				Equipage.joueurFleche.setNextDirection(model.structure_terrain.Direction.droite);
				Equipage.joueurCamera.setNextDirection(model.structure_terrain.Direction.droite);
				break;
			case Keys.LEFT :
				Equipage.joueurFleche.setNextDirection(model.structure_terrain.Direction.gauche);
				Equipage.joueurCamera.setNextDirection(model.structure_terrain.Direction.gauche);
				break;
			case Keys.DOWN :  
				Equipage.joueurFleche.setNextDirection(model.structure_terrain.Direction.haut);
				Equipage.joueurCamera.setNextDirection(model.structure_terrain.Direction.haut);
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

		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		
		return false;
	}

	// => destinée Android
	public static void manipulerAccelerometre() {
        if(Gdx.input.getAccelerometerY()>1)
        {
        	Equipage.joueurFleche.setNextDirection(model.structure_terrain.Direction.droite);
        }
        if(Gdx.input.getAccelerometerY()<-1)
        {
        	Equipage.joueurFleche.setNextDirection(model.structure_terrain.Direction.gauche);
        }
        if(Gdx.input.getAccelerometerX()>1)
        {
        	Equipage.joueurFleche.setNextDirection(model.structure_terrain.Direction.bas);
        }
        if(Gdx.input.getAccelerometerX()<-1)
        {
        	Equipage.joueurFleche.setNextDirection(model.structure_terrain.Direction.haut);
        }
       
//        // La direction initial du Lapin
//        if(Gdx.input.getAccelerometerX()<1 && Gdx.input.getAccelerometerY()<1 && Gdx.input.getAccelerometerX()>-1 && Gdx.input.getAccelerometerY()>-1 )
//        {
////	               animation_stop = true;
////	               regionCourante = regionInitial ;
//        }else
//        {
////	               animation_stop = false;
//        }
  }
	

}
