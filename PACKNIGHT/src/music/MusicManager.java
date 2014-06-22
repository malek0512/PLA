package music;

import game.Accueil;
import game.WindowGame;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;;
public class MusicManager {

	
	private static boolean mute = false;
	
	private static String CHEMIN_MUSIC = "src/graphisme/main/ressources/music/";
	
	private static String Bruitage_Reperer = "Bruitage_Reperer.ogg";
	private static String Bruitage_Dead_Knigth = "Bruitage_Dead_Knigth.ogg"; 
	private static String Bruitage_Dead_Princess = "Bruitage_Dead_Princess.ogg";
	private static String Bruitage_GhostPower_Obey = "Bruitage_GhostPower_Obey.ogg";
	
	static private Music Reperer;
	static private Music Dead_Knigth;
	static private Music Dead_Princess;
	static private Music GhostPower_Obey;
	
	static private float positionMain;
	
	static public void init()
	{
		try {
			MusicManager.Reperer = new Music(CHEMIN_MUSIC.concat(Bruitage_Reperer));
			MusicManager.Dead_Knigth = new Music(CHEMIN_MUSIC.concat(Bruitage_Dead_Knigth));
			MusicManager.Dead_Princess = new Music(CHEMIN_MUSIC.concat(Bruitage_Dead_Princess));
			MusicManager.GhostPower_Obey = new Music(CHEMIN_MUSIC.concat(Bruitage_GhostPower_Obey));
		} catch (SlickException e) {System.out.println(e);}
	}
	
	static private void pauseActuel()
	{
		positionMain =Accueil.Music_WindowGame.getPosition();
		Accueil.Music_WindowGame.stop();
	}
	
	static private void startActuel()
	{
		if(!Accueil.Music_WindowGame.playing())
		{
			Accueil.Music_WindowGame.play();
			Accueil.Music_WindowGame.setPosition(positionMain);
		}
	}
	
	static public void UpDate()
	{
		if(!(Reperer.playing() || Dead_Knigth.playing() || Dead_Princess.playing() || GhostPower_Obey.playing()))
			MusicManager.startActuel();
	}
	
	static public void play_Reperer()
	{
		if(!mute)
		{MusicManager.pauseActuel();
		MusicManager.Reperer.play();}
	}
	
	static public void play_Dead_Knight()
	{
		if(!mute)
		{MusicManager.pauseActuel();
		MusicManager.Dead_Knigth.play();}
	}
	
	static public void play_Dead_Princess()
	{
		if(!mute)
		{MusicManager.pauseActuel();
		MusicManager.Dead_Princess.play();}
	}
	
	static public void play_GhostPower_Obey()
	{
		if(!mute)
		{MusicManager.pauseActuel();
		MusicManager.GhostPower_Obey.play();}
	}
}
