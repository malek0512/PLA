package game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import personnages.RunExternal;

public class Menu extends StateBasedGame {

	static AppGameContainer container;
	
	Equipage equip;
   public Menu(Equipage e) {
      super("PACKNIGHT : THE RETURN");
      this.equip = e;
   }
   
   public void initStatesList(GameContainer container) {
	  RunExternal.launch("make all");
	  addState(new Accueil());
      addState(new Choix());
      addState(new Multi());
      addState(new WindowGame(equip));
      addState(new Pause());
      addState(new Win ());
      addState(new Dead());

   }
   
   public static void main(String[] argv) {
      try {
         container = new AppGameContainer(new Menu(new EquipageVivienAlex()));
         container.setDisplayMode(WindowGame.resolution_x,WindowGame.resolution_y,false);
         container.start();
      } catch (SlickException e) {
         e.printStackTrace();
      }
   }
}