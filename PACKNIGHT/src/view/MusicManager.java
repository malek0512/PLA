package view;

import com.badlogic.gdx.audio.Sound;


public class MusicManager {

	public static boolean mute = false;
	
	static public enum typeSong {
		accueil		    ("assets/songs/Batman.ogg"),
		win			    ("assets/songs/Win.ogg"),      
		gameOver	    ("assets/songs/Game_Over.ogg"),
		allBeat		    ("assets/songs/AllBeat.ogg"),
		selection	    ("assets/songs/Selection.ogg"),
//		frozen		    ("assets/songs/Frozen.ogg"),
//		game		    ("assets/songs/Game.ogg"),     
		Reperer     	("assets/songs/Bruitage_Reperer.ogg"),
		PerduDeVue     	("assets/songs/Bruitage_PerduDeVue.ogg"),
		Dead_Knigth    	("assets/songs/Bruitage_Dead_Knigth.ogg"),
		Dead_Princess   ("assets/songs/Bruitage_Dead_Princess.ogg"),
		GhostPower_Obey ("assets/songs/Bruitage_GhostPower_Obey.ogg");
		
		private String value;
		private typeSong(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}

	static public void loadSongs() {
		for(typeSong s : typeSong.values()) {
			Jeu.assets.load(s.getValue(), Sound.class);
		}	
	}
	
	static public void playLoop(typeSong t) {
		if(!mute) {
			if (! Jeu.assets.isLoaded(t.value)) {
				Jeu.assets.load(t.getValue(), Sound.class);
				Jeu.assets.finishLoading();
			}
			
			Jeu.assets.get(t.value, Sound.class).loop();
		}
	}
	
	static public void playOnce(typeSong t) {
		if(!mute) {
			if (! Jeu.assets.isLoaded(t.value)) {
				Jeu.assets.load(t.getValue(), Sound.class);
				Jeu.assets.finishLoading();
			}
			
			Jeu.assets.get(t.value, Sound.class).play();
		}
	}
	
	static public void dispose(typeSong t) {
		if (Jeu.assets.isLoaded(t.value))
			Jeu.assets.unload(t.value);	
	}
	
	static public void pause(typeSong t) {
		if (Jeu.assets.isLoaded(t.value))
			Jeu.assets.get(t.value, Sound.class).pause();;
	}
	
}
