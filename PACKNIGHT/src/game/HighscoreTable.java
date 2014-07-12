package game;

import java.awt.Font;
import java.io.InputStream;
import java.util.ArrayList;

import highscores.HighscoreManager;
import highscores.Score;
import music.MusicManager;

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
	private TrueTypeFont fontTable;
	private boolean antiAlias = true;
	private static HighscoreManager hm = new HighscoreManager();
	private static ArrayList<Score> scores;
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.game = game;
		font=initFont(38f);
		fontTable=initFont(24f);
		 Highscore=new Image("src/graphisme/main/ressources/map/image/fond_highscores_etoiles.jpg");
	}
	
	public void enter(GameContainer container, StateBasedGame game) throws SlickException 
	{
		if(Win.res!="")
			hm.addScore(Win.res, Win.score);
		scores=hm.getScores();
	}
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		 
		  Highscore.draw(0,0);
		  g.setColor(Color.white);
		  font.drawString(270, 45,"High Scores",Color.yellow);
		  drawHighscoreTable(scores, fontTable, 260, 100);
	      g.drawString("Main Menu (SPACE)", 310, 430);
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
	      		case Input.KEY_SPACE: Accueil.Music_Choix.loop(); if(MusicManager.mute) Accueil.Music_Choix.pause();
	      		game.enterState(Choix.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));break;
	      		case Input.KEY_M: if(Accueil.Music_Win.playing()){Accueil.Music_Win.pause(); MusicManager.mute=true;} else{Accueil.Music_Win.resume(); MusicManager.mute=false;}break;
	      		case Input.KEY_ESCAPE:Menu.container.exit(); break;

	      }
	}
	 private void drawHighscoreTable(ArrayList<Score> scores, TrueTypeFont font,float x, float y){
		 
		 int i = 0;
		 float t=0;
	        int j = scores.size();
	        if (j > hm.getMax()) {
	            j = hm.getMax();
	        }
	        while (i < j) {
	        	font.drawString(x, y + t, i+1 + ".", Color.blue);
	        	font.drawString(x+70, y + t, scores.get(i).getName(), Color.green);
	        	font.drawString(x+220, y + t, String.valueOf(scores.get(i).getScore()), Color.orange);
	        	t+=30;
	        	i++;}
	        
	 }
	 
	 private TrueTypeFont initFont(float size){
		 try {
				InputStream inputStream = ResourceLoader.getResourceAsStream("src/graphisme/main/ressources/map/HighscoreHero.ttf");
				Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
				awtFont = awtFont.deriveFont(size); // set font size
				return new TrueTypeFont(awtFont, antiAlias);
				
			} catch (Exception e) {
				e.printStackTrace();
				}
		return null;
		 
			
	 }
	
	
}
