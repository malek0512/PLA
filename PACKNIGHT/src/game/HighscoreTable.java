package game;

import java.awt.Font;
import java.io.InputStream;

import highscores.HighscoreManager;
import highscores.Score;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.util.ResourceLoader;

public class HighscoreTable extends BasicGameState{
	
	public static final int ID=11;
	private StateBasedGame game;
	
	private Image Highscore;
	private TrueTypeFont font;
	private boolean antiAlias = true;
	private HighscoreManager hm;
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.game = game;
		try {
			InputStream inputStream = ResourceLoader.getResourceAsStream("src/graphisme/main/ressources/map/HighscoreHero.ttf");
			Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont = awtFont.deriveFont(32f); // set font size
			font = new TrueTypeFont(awtFont, antiAlias);
			
		} catch (Exception e) {
			e.printStackTrace();
			}
		 Highscore=new Image("src/graphisme/main/ressources/map/image/fond_highscores_etoiles.jpg");
	}
	public void enter(GameContainer container, StateBasedGame game) throws SlickException 
	{
		 hm.addScore(HighscoreEnter.res, Score.CalculateScoreFinal());
	}
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		 
		  Highscore.draw(0,0);
		  g.setColor(Color.white);
		  font.drawString(260, 45,"High Scores",Color.yellow);
	      g.drawString(hm.getHighscoreString(), 300, 100);
	      g.drawString("Main Menu (SPACE)", 300, 350);
	      
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
	}

	@Override
	public int getID() {
		return ID;
	}
	
	public void keyReleased(int key, char c) {
	      switch (key) {
	      		case Input.KEY_SPACE: Accueil.Music_Choix.loop();
	      		game.enterState(Choix.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));break;
	      		case Input.KEY_M: if(Accueil.Music_Win.playing()) Accueil.Music_Win.pause() ;else Accueil.Music_Win.resume(); break;
	      		case Input.KEY_ESCAPE:Menu.container.exit(); break;

	      }
	}
	
	
	
}
