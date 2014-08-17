package view;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

import model.personnages.Ghost;
import model.personnages.PacKnight;
import model.structure_terrain.Direction;
import view.Equipage;
import view.Jeu;
import view.Joueur;
import view.Sprites;

public class Demo_Berserk extends Equipage {

	public static void main(String[] args) {
	    new LwjglApplication((ApplicationListener) new Jeu(new Demo_Berserk()),Jeu.TITLE,Jeu.WIDTH,Jeu.HEIGHT);
	}

	@Override
	public void create() {
		Ghost.vision = 100;
		PacKnight PACMAN_1= new PacKnight("J1",17,17,Direction.droite, true);
		Ghost GHOST_1 = new Ghost("1");
		this.joueurFleche =  PACMAN_1;
		this.joueurCamera = PACMAN_1;
		Joueur PM_1 = new Joueur(Sprites.Pacman, PACMAN_1);
		Joueur FM_1 = new Joueur(Sprites.Berserk, GHOST_1,"fm_berserk.xml");
	}

}