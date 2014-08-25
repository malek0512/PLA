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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sun.org.apache.regexp.internal.recompile;

public class MenuScreen implements Screen {

	private Stage stage; // Contiens l'ensemble des acteur (boutons, fond)
	private Equipage equipe;
	private List<MyScrolling> fantomes = new LinkedList<>();
	MyScrolling auto1;
	MyScrolling auto2;
	MyScrolling auto3;
	MyScrolling auto4;
	
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
//		MusicManager.play(typeSong.selection); // Musiques

		stage = new Stage(); // Contiens l'ensemble des boutons : multiplexeur des inputs
		Gdx.input.setInputProcessor(stage); // ** stage is responsive **//

		// Chargement de l'image de fond
		final Image fond = new Image(new Texture(Gdx.files.internal(LauncherScreen.selectionPersonnage)));
		fond.setCenterPosition(Jeu.WIDTH / 2, Jeu.HEIGHT / 2);
		stage.addActor(fond);

		// Chargement du Skin des boutons
		TextureAtlas buttonsAtlas = new TextureAtlas(LauncherScreen.buttons); // ** charge l'image creer avec GDX TEXTURE PACKER **//
		Skin buttonSkin = new Skin();
		buttonSkin.addRegions(buttonsAtlas); // ** La decoupe en up et down**//
		BitmapFont font = new BitmapFont(); // ** font, avec possibilité de renseigner une font ". **//

		// Definition d'un style de bouton
		final TextButtonStyle style = new TextButtonStyle(); // ** Button properties **//
		style.up = buttonSkin.getDrawable("down");
		style.down = buttonSkin.getDrawable("up");
		style.font = font;

		ArrayList<String> listeAutomates = (ArrayList<String>) Equipage.getListOfPersonnage();
		
		//Dessiner les choix d'automate
		auto1 = new MyScrolling(listeAutomates, 
				Jeu.WIDTH / 2 + fond.getWidth()/4, Jeu.HEIGHT / 2 + fond.getHeight()/4, 100, 25, "Player 1");
		auto1.addToStage(stage);
		fantomes.add(auto1);
		
		auto2 = new MyScrolling(listeAutomates, 
				Jeu.WIDTH / 2 + fond.getWidth()/4, Jeu.HEIGHT / 2 + fond.getHeight()/8, 100, 25, "Player 2");
		auto2.addToStage(stage);
		fantomes.add(auto2);
		
		auto3 = new MyScrolling(listeAutomates, 
				Jeu.WIDTH / 2 + fond.getWidth()/4, Jeu.HEIGHT / 2 - fond.getHeight()/8, 100, 25, "Player 3");
		auto3.addToStage(stage);
		fantomes.add(auto3);
		
		auto4 = new MyScrolling(listeAutomates, 
				Jeu.WIDTH / 2 + fond.getWidth()/4, Jeu.HEIGHT / 2 - fond.getHeight()/4, 100, 25, "Player 4");
		auto4.addToStage(stage);
		fantomes.add(auto4);
		
		//Dessiner bouton Suivant
		TextButton suivant = new TextButton("Suivant", style);
		suivant.setBounds(Jeu.WIDTH - 150, 50, 100, 50);
		suivant.addListener(new InputListener(){
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
			public void touchUp(InputEvent event, float x, float y,	int pointer, int button) {
				initializeEquipe();
				typeScreen.JEU.setScreen(new Jeu(equipe));
				LauncherScreen.setNextScreen(typeScreen.JEU);
			}
		});
		stage.addActor(suivant);
		
		
		//Dessiner un rectangle autour de la map
		final Actor rectMap = new Image(){
			ShapeRenderer rectangle = new ShapeRenderer();

			public void draw(Batch batch, float parentAlpha) {
				super.draw(batch, parentAlpha);
				batch.end();
					rectangle.begin(ShapeType.Line);
						rectangle.rect(getX(), getY(), getWidth(), getHeight());
						rectangle.setColor(1,1,1,1);
					rectangle.end();
				batch.begin();
			}
		};
		stage.addActor(rectMap);
				
		//Chargment du choix des Maps
		final Image map1 = new Image(new Texture(Gdx.files.internal(LauncherScreen.map1)));
		map1.setCenterPosition(Jeu.WIDTH / 2 - fond.getWidth()/4, Jeu.HEIGHT / 2 + fond.getHeight()/4);
		map1.addCaptureListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					Map.setMap(Map.map1);
//					map1.addAction(Actions.sequence(Actions.fadeOut(0.1f)));
					rectMap.setBounds(map1.getX()-1, map1.getY()-1, map1.getWidth()+2, map1.getHeight()+2);
					return true;
				}
//				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//				super.touchUp(event, x, y, pointer, button);
//				map1.addAction(Actions.sequence(Actions.fadeIn(0.1f)));
//				}
		});
		
		stage.addActor(map1);

		
		final Image map2 = new Image(new Texture(Gdx.files.internal(LauncherScreen.map2)));
		map2.setCenterPosition(Jeu.WIDTH / 2 - fond.getWidth()/4, Jeu.HEIGHT / 2 - fond.getHeight()/4);
		map2.addCaptureListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Map.setMap(Map.map2);
//				map2.addAction(Actions.sequence(Actions.fadeOut(0.1f)));
				rectMap.setBounds(map2.getX()-1, map2.getY()-1, map2.getWidth()+2, map2.getHeight()+2);
				return true;
			}
//			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
//				super.touchUp(event, x, y, pointer, button);
//				map2.addAction(Actions.sequence(Actions.fadeIn(0.1f)));
//			}
		});
		
		stage.addActor(map2);
		
		
	}

	@Override
	public void hide() {
		MusicManager.pause(typeSong.selection);
//		System.out.println("ahh clear");
//		fantomes.clear();
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
		MusicManager.dispose(typeSong.selection);
		stage.dispose();
	}

	private void initializeEquipe() {
		equipe = new Equipage() {
			
			public void create() {
				Ghost.vision = 100;
				PacKnight.vie = 1;
				PacKnight PACMAN_1 = new PacKnight("J1",17,17,Direction.droite, true);
				Joueur.liste.clear();
				new Joueur(Sprites.Pacman, PACMAN_1);
				this.joueurFleche = PACMAN_1;
				this.joueurCamera = PACMAN_1;
				
				for(MyScrolling p : fantomes){
					if (p.getEC() != "None") {
						System.out.println(p.getEC()+"\n");
						new Joueur(Equipage.automate.get(p.getEC())[1], new Ghost(""), Equipage.automate.get(p.getEC())[0]);
					}
				}

				fantomes.clear(); // A ne faire qu'ici et non pas dans suivant listener ou hide
				
			}
		};
	}
}
