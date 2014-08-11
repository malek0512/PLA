package view;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;


public class MusicManager {

	private static HashMap<typeSong, Sound> songList= new HashMap<typeSong, Sound>();
	public static boolean mute = false;
	
	static public enum typeSong {
		selection	    ("songs/Selection.ogg"),
		win			    ("songs/Win.ogg"),      
		gameOver	    ("songs/Game_Over.ogg"),
		accueil		    ("songs/Batman.ogg"),   
		frozen		    ("songs/Frozen.ogg"),   
		allBeat		    ("songs/AllBeat.ogg"),  
		game		    ("songs/Game.ogg"),     
		Reperer     	("songs/Bruitage_Reperer.ogg"),
		PerduDeVue     	("songs/Bruitage_PerduDeVue.ogg"),
		Dead_Knigth    	("songs/Bruitage_Dead_Knigth.ogg"),
		Dead_Princess   ("songs/Bruitage_Dead_Princess.ogg"),
		GhostPower_Obey ("songs/Bruitage_GhostPower_Obey.ogg");
		
		private String value;
		private typeSong(String value) {
			this.value = value;
		}
		
	}

	static public void play(typeSong t) {
		if(!mute) {
			if (! songList.containsKey(t))
				songList.put(t, Gdx.audio.newSound(Gdx.files.internal(t.value)));	
			
			songList.get(t).loop();
		}
	}
	
	static public void dispose(typeSong t) {
		if (songList.containsKey(t))
			songList.remove(t).dispose();	
	}
	
	static public void pause(typeSong t) {
		if (songList.containsKey(t))
			songList.get(t).pause();	
	}
	
//	static public void play_Win(){
//		
//		Accueil.Music_Win.loop();
//		if(mute)
//			Accueil.Music_Win.pause();
//	}
//	
//	static public void play_Game_over(){
//
//		Accueil.Music_Dead.loop();
//		if(mute)
//			Accueil.Music_Dead.pause();
//	}
	
}
