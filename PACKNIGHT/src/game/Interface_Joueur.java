package game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import personnages.PacKnight;
import structure_terrain.Terrain;
import game.WindowGame;

public class Interface_Joueur {
	
	public Interface_Joueur()
	{

	}
	
	
	public static void render(Graphics g, Image HEART)
	{
        g.setColor(Color.gray);
        g.fillRect(-WindowGame.resolution_x/2 + WindowGame.xCamera,-WindowGame.resolution_y/2 + WindowGame.yCamera, WindowGame.largueur_map*WindowGame.taille_minimap,WindowGame.hauteur_map*WindowGame.tuile_size );
        drawHeart(-WindowGame.resolution_x/2 + WindowGame.xCamera,-WindowGame.resolution_y/2 + WindowGame.yCamera+WindowGame.hauteur_map*WindowGame.taille_minimap, HEART);
        g.setColor(Color.black);
        g.drawString("Time : " + WindowGame.time/1000, -WindowGame.resolution_x/2 + WindowGame.xCamera, -WindowGame.resolution_y/2 + WindowGame.yCamera+WindowGame.hauteur_map*WindowGame.taille_minimap+5*WindowGame.tuile_size);
	}
	
	public static void drawPacGum(Terrain terrain, Image PACGUM){
		for(int i=0;i<WindowGame.largueur_map;i++)
		{
			for(int j=0;j<WindowGame.hauteur_map;j++)
			{
				if(terrain.terrain[i][j].caseValeur() == 2){
					PACGUM.draw(i*WindowGame.tuile_size+WindowGame.largueur_map*WindowGame.taille_minimap,j*WindowGame.tuile_size);}
			}
		}
	}
	
	private static void drawHeart(float x, float y,  Image HEART)
	{
        int i = 0;
        while(i<PacKnight.vie ) 
        {	
        	if(i < 5)
        	{
				HEART.draw(x,y+i*WindowGame.tuile_size);
				i++;
        	}
        	else
        	{
	        	HEART.draw(x+WindowGame.tuile_size,y+(i-5)*WindowGame.tuile_size);
	        	i++;
        	}
        }

	}

}
