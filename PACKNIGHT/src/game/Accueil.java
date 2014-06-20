package game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Accueil extends BasicGameState {
	   public static final int ID = 1;
	   private Image ACCUEIL;
	   private StateBasedGame game;
	   
	   private static String CHEMIN_MUSIC = "src/graphisme/main/ressources/music/";
	   private static String MUSIC_ACCUEIL = "Batman.ogg";
	   private static String MUSIC_WINDOWGAME = "AllBeat.ogg";
	   private static String MUSIC_CHOIX = "Frozen.ogg";
	   private static String MUSIC_WIN = "Win.ogg";
	   private static String MUSIC_DEAD = "Game_Over.ogg";
	   
	   protected static Music Music_Accueil;
	   protected static Music Music_WindowGame;
	   protected static Music Music_Choix;
	   protected static Music Music_Win;
	   protected static Music Music_Dead;

		
	  
	   
	   public int getID() {
	      return ID;
	   }

	   public void init(GameContainer container, StateBasedGame game) throws SlickException {
		   	this.game = game;
		   	
		   //	Music_Accueil = new Music(CHEMIN_MUSIC.concat(MUSIC_ACCUEIL));
		   //	Music_WindowGame = new Music(CHEMIN_MUSIC.concat(MUSIC_WINDOWGAME));
		   	//Music_Choix = new Music(CHEMIN_MUSIC.concat(MUSIC_CHOIX));
		   //	Music_Win = new Music(CHEMIN_MUSIC.concat(MUSIC_WIN));
		   	//Music_Dead = new Music(CHEMIN_MUSIC.concat(MUSIC_DEAD));
		   	
		   //	Music_Accueil.loop();

	   }

	   public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
	      ACCUEIL = new Image("src/graphisme/main/ressources/map/image/Accueil.jpeg");
	      ACCUEIL.draw();
	      g.drawString("Press 'ENTER' to continue", WindowGame.resolution_x/2-100, WindowGame.resolution_y/2);
	      
	   }

	   public void update(GameContainer container, StateBasedGame game, int delta) {
	   }
	   
	   public void keyReleased(int key, char c) {
	      switch (key) {
	    	  case Input.KEY_ENTER: 
	    	  {
	    	      //Music_Choix.loop();
	    		  game.enterState(Choix.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));break;
	    	  }
	    	  case Input.KEY_M: if(Music_Accueil.playing()) Music_Accueil.pause() ;else Music_Accueil.resume(); break;
	    	  case Input.KEY_ESCAPE:Menu.container.exit(); break;

	      }
	   }
	}


