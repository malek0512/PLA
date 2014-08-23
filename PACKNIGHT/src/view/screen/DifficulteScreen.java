package view.screen;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.swing.plaf.basic.BasicBorders.ButtonBorder;

import model.personnages.Ghost;
import model.personnages.PacKnight;
import model.structure_terrain.Direction;

import sun.awt.image.PixelConverter;
import view.Equipage;
import view.Jeu;
import view.Joueur;
import view.Map;
import view.MusicManager;
import view.Sprites;
import view.MusicManager.typeSong;
import view.screen.LauncherScreen.typeScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.MyScrolling;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sun.org.apache.regexp.internal.recompile;

public class DifficulteScreen implements Screen {

	private Stage stage; // Contiens l'ensemble des acteur (boutons, fond)
	private Table table;
	public static int choix_difficulte = 1;
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw(); 
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void show() {
//		MusicManager.playLoop(typeSong.gameOver); // Musiques

		stage = new Stage(); // Contiens l'ensemble des boutons : multiplexeur des inputs
		Gdx.input.setInputProcessor(stage); // ** stage is responsive **//

		// Chargement de l'image de fond
		final Image fond = new Image(new Texture(Gdx.files.internal("pictures/SelectionPerso.jpeg")));

		table = new Table();
		table.setBackground(fond.getDrawable());
		table .setFillParent(true);
		
		stage.addActor(table);
		
		// Chargement du Skin des boutons
		TextureAtlas buttonsAtlas = new TextureAtlas("pictures/output/buttons.atlas"); // ** charge l'image creer avec GDX TEXTURE PACKER **//
		Skin buttonSkin = new Skin();
		buttonSkin.addRegions(buttonsAtlas); // ** La decoupe en up et down**//
		BitmapFont font = new BitmapFont(); // ** font, avec possibilité de renseigner une font ". **//

		// Definition d'un style de bouton
		final TextButtonStyle style = new TextButtonStyle(); // ** Button properties **//
		style.up = buttonSkin.getDrawable("down");
		style.down = buttonSkin.getDrawable("up");
		style.font = font;

		
		//Dessiner bouton EASY
		TextButton easy = new TextButton("Easy", style);
		easy.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				choix_difficulte = 1;
				return true;
			}
		});
		
		//Dessiner bouton MEDIUM
		TextButton medium = new TextButton("Medium", style);
		medium.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				choix_difficulte = 2;
				return true;
			}
		});

		//Dessiner bouton HARD
		TextButton hard = new TextButton("Hard", style);
		hard.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				choix_difficulte = 3;
				return true;
			}
		});
		
		//Dessiner bouton Suivant
		TextButton suivant = new TextButton("Suivant", style);
		suivant.setBounds(Jeu.WIDTH - 150, 50, 100, 50);
		suivant.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		
		table.add(easy).expand();
		table.row();
		table.add(medium).expand();
		table.row();
		table.add(hard).expand();
		table.row();
		table.add(suivant).bottom().right().pad(10);
		
		
	}

	@Override
	public void hide() {
//		MusicManager.pause(typeSong.gameOver);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		MusicManager.dispose(typeSong.gameOver);
		stage.dispose();
	}

}
