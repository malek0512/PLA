package structure_terrain;

import game.WindowGame;

import org.newdawn.slick.Animation;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

import personnages.*;

public class Niveau1 extends Terrain {
	
	protected CoordonneesFloat coordFloat;
	private TiledMap map;
	
	public Niveau1()
	{
		super(50,28);
		/*for(int i=0;i<5;i++){
		this.terrain[i][4] = new Case(0);
		this.terrain[i][0] = new Case(0);}
		for(int j = 0; j< 5; j++){
			this.terrain[0][j] = new Case(0);
			this.terrain[4][j] = new Case(0);
		}
		this.terrain[2][2] = new Case(0);*/
		
	}
	/*	public Niveau1() throws SlickException
	{
		super(28,31);
		coordFloat = new CoordonneesFloat(0,0);
		creationTableau(28, 31,this.terrain);
		afficher();

	}/*
	
	public void creationTableau(int largueur, int hauteur,Case[][] terrain) throws SlickException
	{
 //       this.map = new TiledMap("src/graphisme/main/ressources/map/PACMAN.tmx");
			for(int i=0;i<largueur;i++)
				
			{
				for(int j=0;j<hauteur;j++)
				{
					System.out.println("x" + coordFloat.x);
					System.out.println("y" + coordFloat.y);
			      /*  Image tile = this.map.getTileImage((int)coordFloat.x,(int)coordFloat.y,this.map.getLayerIndex("logic"));
			        boolean vide = tile != null;
			        if (vide) 
			        {
						terrain[i][j] = new Case(0);
			        }
			        else
			        {
			        	terrain[i][j] = new Case(1);
			        }
			        coordFloat.y += 1;
				}
				coordFloat.x += 1;
				coordFloat.y =0;
			}
	}*/

}

