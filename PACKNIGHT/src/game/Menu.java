package game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends StateBasedGame {

	static AppGameContainer container;
	
   public Menu() {
      super("PACKNIGHT : THE RETURN");
   }
   
   public void initStatesList(GameContainer container) {
	  addState(new Accueil());
      addState(new Choix());
      addState(new WindowGame());
      addState(new Pause());
      addState(new Win ());
      addState(new Dead());

   }
   
   public static void main(String[] argv) {
      try {
    	  //exception levée à la ligne en dessous. Ne peut plus rien tester :/
         container = new AppGameContainer(new Menu());
         container.setDisplayMode(WindowGame.resolution_x,WindowGame.resolution_y,false);
         container.start();
      } catch (SlickException e) {
         e.printStackTrace();
      }
   }
}