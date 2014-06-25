package music;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
public class MusicManager {

	
	public static boolean mute = false;
	
	private static String CHEMIN_MUSIC = "src/graphisme/main/ressources/music/";
	
	private static String Bruitage_Reperer = "Bruitage_Reperer.ogg";
	private static String Bruitage_PerduDeVue = "Bruitage_PerduDeVue.ogg";
	private static String Bruitage_Dead_Knigth = "Bruitage_Dead_Knigth.ogg"; 
	private static String Bruitage_Dead_Princess = "Bruitage_Dead_Princess.ogg";
	private static String Bruitage_GhostPower_Obey = "Bruitage_GhostPower_Obey.ogg";
	private static String Music_Win = "Win.ogg";
	private static String Music_Game_over = "Game_Over.ogg";
	
	static private Sound Reperer;
	static private Sound PerduDeVue;
	static private Sound Dead_Knigth;
	static private Sound Dead_Princess;
	static private Sound GhostPower_Obey;
	static private Music Game_over;
	static private Music Win; 
	
	static public void init()
	{
		try {
			MusicManager.Reperer = new Sound(CHEMIN_MUSIC.concat(Bruitage_Reperer));
			MusicManager.Dead_Knigth = new Sound(CHEMIN_MUSIC.concat(Bruitage_Dead_Knigth));
			MusicManager.Dead_Princess = new Sound(CHEMIN_MUSIC.concat(Bruitage_Dead_Princess));
			MusicManager.GhostPower_Obey = new Sound(CHEMIN_MUSIC.concat(Bruitage_GhostPower_Obey));
			MusicManager.PerduDeVue = new Sound(CHEMIN_MUSIC.concat(Bruitage_PerduDeVue));
			MusicManager.Game_over = new Music(CHEMIN_MUSIC.concat(Music_Game_over));
			MusicManager.Win = new Music(CHEMIN_MUSIC.concat(Music_Win));
			
		} catch (SlickException e) {System.out.println(e);}
	}
	static public void play_Reperer()	{
		if(!mute)
			MusicManager.Reperer.play();
	}
	
	static public void play_Dead_Knight()
	{
		if(!mute)
			MusicManager.Dead_Knigth.play();
	}
	
	static public void play_Dead_Princess()
	{
		if(!mute)
			MusicManager.Dead_Princess.play();
	}
	
	static public void play_GhostPower_Obey()
	{
		if(!mute)
			MusicManager.GhostPower_Obey.play();
	}

	static public void play_PerduDeVue()
	{
		if(!mute)
			MusicManager.PerduDeVue.play();
	}
	
	static public void play_Win(){
		
		if(!mute)
			MusicManager.Win.play();
	}
	static public void play_Game_over(){
		
		if(!mute)
			MusicManager.Game_over.play();
	}
	
}
