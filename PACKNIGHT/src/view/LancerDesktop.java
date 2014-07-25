package view;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class LancerDesktop {

	public static void main(String[] args) {
        new LwjglApplication(new Jeu(),Jeu.TITLE,Jeu.WIDTH,Jeu.HEIGHT);
	}
}
