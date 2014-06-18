package game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Pause {
	
	public static void Pause(Graphics g, Image PAUSE_IMAGE)
	{
		PAUSE_IMAGE.draw(0,0);
		g.setColor(Color.white);
		g.drawString("Resume (P)", 250, 100);
		g.drawString("Main Menu (I'M WORKING ON IT >.<)", 250, 150);
		g.drawString("Quit Game (ESCAPE)", 250, 250);

	}

}
