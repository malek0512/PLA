package game;



import highscores.Score;
import music.MusicManager;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import personnages.Personnage;

public class Win extends BasicGameState {
	   public static final int ID = 5;
	   private Image WIN;
	   private StateBasedGame game;

	   private UnicodeFont fontTest;
	   private TextField nomPerso;
	   public static String res="";
	   public static int score;
	   public int getID() {
	      return ID;
	   }

	   @SuppressWarnings("unchecked")
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		   	this.game = game;
		   	WIN = new Image("src/graphisme/main/ressources/map/image/Win.jpeg");
		   	fontTest = new UnicodeFont(new java.awt.Font("DejaVu Serif", java.awt.Font.PLAIN, 14));
			fontTest.addAsciiGlyphs();
			fontTest.addGlyphs(400,600);
			fontTest.getEffects().add(new ColorEffect(java.awt.Color.white));
			fontTest.loadGlyphs();
			nomPerso = new TextField(container, fontTest, 250, 130, 200, 30);
			nomPerso.setMaxLength(10);
			nomPerso.setBackgroundColor(Color.black);		

	   }
	   public void enter(GameContainer container, StateBasedGame game) throws SlickException 
		{
		   Joueur.liste.clear();
		   Personnage.init_personnage();
		   nomPerso.setText("");
		   score=Score.CalculateScoreFinal();
		}

	   public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		   		int decalage=0;
		      WIN.draw(0,0);
		      g.setColor(Color.white);
		      
		      if(WindowGame.Choix_Map!=4) {
		    	  decalage=50;
		    	  g.drawString("Enter you name", 250, 100);
		    	  nomPerso.render(container, g);
		    	  res=nomPerso.getText();
		    	  g.drawString(Integer.toString(score), 500, 130);
			  }
		      g.drawString("You Win", 250, 100-decalage);
		      g.drawString("Highscore Table (ENTER)", 250, 130+decalage);
		      g.drawString("Main Menu (SPACE)", 250, 160+decalage);
		      
	   }

	   
	   public void update(GameContainer container, StateBasedGame game, int delta) {
		   nomPerso.setFocus(true);
	   }
	   
	   public void keyReleased(int key, char c) {
	      switch (key) {
	      		case Input.KEY_SPACE: Accueil.Music_Choix.loop(); if(MusicManager.mute) Accueil.Music_Choix.pause();
	      							game.enterState(Choix.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black)); break;
	      		case Input.KEY_ENTER: 
	      							game.enterState(HighscoreTable.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));break;
	      		case Input.KEY_M: if(Accueil.Music_Win.playing()){Accueil.Music_Win.pause(); MusicManager.mute=true;} else{Accueil.Music_Win.resume(); MusicManager.mute=false;}break;
	      		case Input.KEY_ESCAPE:Menu.container.exit(); break;

	      }
	   }


	public static void Pause_Game(Graphics g, Image PAUSE_IMAGE)
	{


	}

}