package music;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;;
public class MusicManager {

	
	private static String CHEMIN_MUSIC = "src/graphisme/main/ressources/music/";
	private static String MUSIC_REPERER = "Batman.ogg";
	
	static private Music reperer;
	
	static public void init()
	{
		try {
			MusicManager.reperer = new Music(CHEMIN_MUSIC.concat(MUSIC_REPERER));
		} catch (SlickException e) {System.out.println("haha");}
	}
	
	static public void playReperer()
	{
		MusicManager.reperer.play();
	}
	
	static public void stopReperer()
	{
		MusicManager.reperer.stop();
	}
}
