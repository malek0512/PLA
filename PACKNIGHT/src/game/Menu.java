package game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends StateBasedGame {

   public Menu() {
      super("PACKNIGHT : THE RETURN");
   }
   
   public void initStatesList(GameContainer container) {
	  addState(new Accueil());
      addState(new WindowGame());
      //addState(new Accueil());
      addState(new Choix());
   }
   
   public static void main(String[] argv) {
      try {
         AppGameContainer container = new AppGameContainer(new Menu());
         container.setDisplayMode(WindowGame.resolution_x,WindowGame.resolution_y,false);
         container.start();
      } catch (SlickException e) {
         e.printStackTrace();
      }
   }
}