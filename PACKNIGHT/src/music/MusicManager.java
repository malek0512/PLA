package music;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;;
public class MusicManager {

	static public Music reperer;
	
	static public void init()
	{
		try {
			reperer = new Music("src/graphisme/main/ressources/music/Batman.ogg");
		} catch (SlickException e) {}
	}
}
