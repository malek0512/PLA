package game;

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

public class HighscoreEnter extends BasicGameState{
	
	public static final int ID=12;
	private StateBasedGame game;
	
	private UnicodeFont fontTest;
	private TextField nomPerso;
	private Image Highscore;
	public static String res="";
	@SuppressWarnings("unchecked")
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.game = game;
		fontTest = new UnicodeFont(new java.awt.Font("DejaVu Serif", java.awt.Font.PLAIN, 13));
		fontTest.addAsciiGlyphs();
		fontTest.addGlyphs(400,600);
		fontTest.getEffects().add(new ColorEffect(java.awt.Color.white));
		fontTest.loadGlyphs();
		nomPerso = new TextField(container, fontTest, 250, 100, 200, 30);
		nomPerso.setMaxLength(10);
		nomPerso.setBackgroundColor(Color.black);		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		  Highscore=new Image("src/graphisme/main/ressources/map/image/Win.jpeg");
		  Highscore.draw(0,0);
		  g.setColor(Color.white);
		  nomPerso.render(container, g);
		  res=nomPerso.getText();
		  g.drawString("Enter you name", 250, 50);
		  g.drawString("Highscore Table (ENTER)", 250, 170);
	      g.drawString("Main Menu (SPACE)", 250, 220);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		nomPerso.setFocus(true);
	}

	@Override
	public int getID() {
		return ID;
	}
	
	public void keyReleased(int key, char c) {
	      switch (key) {
	      		case Input.KEY_SPACE: Accueil.Music_Choix.loop();
	      		game.enterState(Choix.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));break;
	      		case Input.KEY_ENTER: //Accueil.Music_Win.loop();
	      		game.enterState(HighscoreTable.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));break;
	      		case Input.KEY_M: if(Accueil.Music_Win.playing()) Accueil.Music_Win.pause() ;else Accueil.Music_Win.resume(); break;
	      		case Input.KEY_ESCAPE:Menu.container.exit(); break;

	      }
	}

	
	
}
