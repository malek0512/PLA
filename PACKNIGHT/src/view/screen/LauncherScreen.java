package view.screen;

import java.util.HashMap;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Game;

public class LauncherScreen extends Game {

//	public static HashMap<typeScreen, Screen> screenList = new HashMap<typeScreen,Screen>();
	
	public static enum typeScreen { //Les referenceur des screen sont implictement static et final
		ACCUEIL (new AccueilScreen()), 
		CHOIX (new ChoixMultiJoueurScreen()),
		MENU (new MenuScreen()), 
		REGLAGE (new ReglageScreen()),
		JEU (null), //Initialisé par MenuScreen
		PERDU (new PerduScreen()), 
		GAGNER (new GagnerScreen()),
		DIFFICULTE (new DifficulteScreen()),
		PAUSE (null);
		;
		private Screen value; 
		private typeScreen (Screen s) {
			this.value = s;
		}
		
		public void dispose(typeScreen t){
			this.value.dispose();
		}

		public void setScreen(Screen t){
			this.value=t;
		}
	}; 
	
	private static typeScreen screenCourant;
	private static typeScreen screenPrecedent;
	
	@Override
	public void create() {
		screenCourant = typeScreen.DIFFICULTE;
		screenPrecedent = screenCourant;
		setScreen(screenCourant.value);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void render() {
		if (screenCourant != screenPrecedent) {
			screenPrecedent = screenCourant;
			setScreen(screenCourant.value);
		}

		screenCourant.value.render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	public void dispose() {
		for(typeScreen s : typeScreen.values())
			s.value.dispose();
	}
	
	public static void setNextScreen (typeScreen t){
		screenCourant = t;
	}

}
