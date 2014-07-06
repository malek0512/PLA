package game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import equipages.Equipage;
import personnages.RunExternal;

public class Menu extends StateBasedGame {

	public static AppGameContainer container;
	
	Equipage equip;
   public Menu(Equipage e) {
      super("PACKNIGHT : THE RETURN");
      this.equip = e;
   }
   
   public void initStatesList(GameContainer container) {
	  RunExternal.launch("make all");
	  addState(new Accueil());
      addState(new Choix());
      addState(new SelectionPerso());
      addState(new WindowGame(equip));
      addState(new Win ());
      addState(new Dead());
      addState(new Difficulte());
      addState(new Free());
      addState(new SelectionMapSolo());
      addState(new HighscoreTable());
      addState(new HighscoreEnter());

   }
   
  
}