package game;



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

public class Win extends BasicGameState {
	   public static final int ID = 5;
	   private Image WIN;
	   private StateBasedGame game;

	   
	   public int getID() {
	      return ID;
	   }

	   public void init(GameContainer container, StateBasedGame game) throws SlickException {
		   	this.game = game;
		   	WIN = new Image("src/graphisme/main/ressources/map/image/Win.jpeg");

	   }
	   public void enter(GameContainer container, StateBasedGame game) throws SlickException 
		{
		   Joueur.liste.clear();
		   Personnage.init_personnage();
		}

	   public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		   		
		      WIN.draw(0,0);
		      g.setColor(Color.white);
		      g.drawString("You Win", 250, 100);
		      g.drawString("Main Menu (SPACE)", 250, 150);
		      
		     

	   }

	   
	   public void update(GameContainer container, StateBasedGame game, int delta) {
	   }
	   
	   public void keyReleased(int key, char c) {
	      switch (key) {
	      		case Input.KEY_SPACE: //Accueil.Music_Choix.loop();
	      		game.enterState(HighscoreEnter.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));break;
	      		case Input.KEY_M: if(Accueil.Music_Win.playing()) Accueil.Music_Win.pause() ;else Accueil.Music_Win.resume(); break;
	      		case Input.KEY_ESCAPE:Menu.container.exit(); break;

	      }
	   }



	public static void Pause_Game(Graphics g, Image PAUSE_IMAGE)
	{


	}

}