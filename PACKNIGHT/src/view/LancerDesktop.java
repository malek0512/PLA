package view;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class LancerDesktop {

	public static void main(String[] args) {
        new LwjglApplication(new LauncherScreen(),Jeu.TITLE,Jeu.WIDTH,Jeu.HEIGHT);
	}
}
