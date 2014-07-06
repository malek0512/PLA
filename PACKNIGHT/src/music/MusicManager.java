package music;


import game.Accueil;

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
	
	static private Sound Reperer;
	static private Sound PerduDeVue;
	static private Sound Dead_Knigth;
	static private Sound Dead_Princess;
	static private Sound GhostPower_Obey;
	
	static public void init()
	{
		try {
			MusicManager.Reperer = new Sound(CHEMIN_MUSIC.concat(Bruitage_Reperer));
			MusicManager.Dead_Knigth = new Sound(CHEMIN_MUSIC.concat(Bruitage_Dead_Knigth));
			MusicManager.Dead_Princess = new Sound(CHEMIN_MUSIC.concat(Bruitage_Dead_Princess));
			MusicManager.GhostPower_Obey = new Sound(CHEMIN_MUSIC.concat(Bruitage_GhostPower_Obey));
			MusicManager.PerduDeVue = new Sound(CHEMIN_MUSIC.concat(Bruitage_PerduDeVue));
			
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
		
		Accueil.Music_Win.loop();
		if(mute)
			Accueil.Music_Win.pause();
	}
	
	static public void play_Game_over(){

		Accueil.Music_Dead.loop();
		if(mute)
			Accueil.Music_Dead.pause();
	}
	
}
