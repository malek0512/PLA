package game;



import music.MusicManager;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import personnages.Personnage;

public class Dead extends BasicGameState {
	   public static final int ID = 6;
	   private Image DEAD;
	   private StateBasedGame game;

	   
	   public int getID() {
	      return ID;
	   }

	   public void init(GameContainer container, StateBasedGame game) throws SlickException {
		   	this.game = game;
		   	DEAD = new Image("src/graphisme/main/ressources/map/image/Dead.jpg");

	   }
	   
	   public void enter(GameContainer container, StateBasedGame game) throws SlickException 
	 		{
	 		   Joueur.liste.clear();
	 		   Personnage.init_personnage();
	 		   Win.res="";
	 		}

	   public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		   
		      DEAD.draw(0,0);
		      g.setColor(Color.white);
		      g.drawString("You Lost", 250, 100);
		      g.drawString("Highscore Table (ENTER)", 250, 150);
		      g.drawString("Main Menu (SPACE)", 250, 200);
		      
	   }

	   
	   public void update(GameContainer container, StateBasedGame game, int delta) {
	   }
	   
	   public void keyReleased(int key, char c) {
	      switch (key) {
	      		
	      		case Input.KEY_SPACE: Accueil.Music_Choix.loop();if(MusicManager.mute) Accueil.Music_Choix.pause();
	      		game.enterState(Choix.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));break;
	      		case Input.KEY_ENTER: Accueil.Music_Win.loop(); if(MusicManager.mute) Accueil.Music_Win.pause();
	      		game.enterState(HighscoreTable.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));break;
	      		case Input.KEY_M: if(Accueil.Music_Dead.playing()) {Accueil.Music_Dead.pause(); MusicManager.mute=true;} else {Accueil.Music_Dead.resume(); MusicManager.mute=false;} break;
	      		case Input.KEY_ESCAPE:Menu.container.exit(); break;

	      }
	   }



	public static void Pause_Game(Graphics g, Image PAUSE_IMAGE)
	{


	}


}
