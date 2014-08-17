package view.screen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import view.Equipage;
import view.Jeu;
import view.MusicManager;
import view.MusicManager.typeSong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.MyScrolling;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Value;

public class ReglageScreen implements Screen {
	private Stage stage; // Contiens l'ensemble des acteur (boutons, fond)

	private HashMap<String, TextButton> automate ;
	Equipage equip;
	Table table;
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		Table.drawDebug(stage);
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
		final Image fond = new Image(new Texture(Gdx.files.internal("pictures/SelectionPerso.jpeg")));

		table = new Table();
		table.setBackground(fond.getDrawable());
		table .setFillParent(true);
		
		table.debug();
		table.debugTable();
		
		
		stage.addActor(table);
		
		
		// Chargement du Skin des boutons
		TextureAtlas buttonsAtlas = new TextureAtlas("pictures/output/buttons.atlas"); // ** charge l'image creer avec GDX TEXTURE PACKER **//
		Skin buttonSkin = new Skin();
		buttonSkin.addRegions(buttonsAtlas); // ** La decoupe en up et down**//
		BitmapFont font = new BitmapFont(); // ** font, avec possibilité de renseigner une font ". **//

		// Definition d'un style de bouton
		final TextButtonStyle style = new TextButtonStyle(); // ** Button properties **//
		style.up = buttonSkin.getDrawable("down");
		style.font = font;

//		automate = new HashMap<String, TextButton>() {
//			{put("Berserk", new TextButton("Berserk", style));}
//			{put("Berserk", new TextButton("Berserk", style));}
//		};

		//Dessiner les choix d'automate
		MyScrolling mm1 = new MyScrolling(new ArrayList<>(Arrays.asList("Berserk", "Suiveur", "Intercepteur")) , 
				Jeu.WIDTH / 2 + fond.getWidth()/4, Jeu.HEIGHT / 2 - fond.getHeight()*(2/4), 100, 25, "Player 1");
		mm1.addToStage(stage);
		
//		MyScrolling mm2 = new MyScrolling(new ArrayList<>(Arrays.asList("Berserk", "Suiveur", "Intercepteur")) , 
//				Jeu.WIDTH / 2 + fond.getWidth()/4, Jeu.HEIGHT / 2 - fond.getHeight()*(2/4), 100, 25, "Player 2");
//		mm2.addToStage(stage);
//		
//		MyScrolling mm3 = new MyScrolling(new ArrayList<>(Arrays.asList("Berserk", "Suiveur", "Intercepteur")) , 
//				Jeu.WIDTH / 2 + fond.getWidth()/4, Jeu.HEIGHT / 2 - fond.getHeight()*(2/4), 100, 25, "Player 3");
//		mm3.addToStage(stage);
//		
//		MyScrolling mm4 = new MyScrolling(new ArrayList<>(Arrays.asList("Berserk", "Suiveur", "Intercepteur")) , 
//				Jeu.WIDTH / 2 + fond.getWidth()/4, Jeu.HEIGHT / 2 - fond.getHeight()*(2/4), 100, 25, "Player 4");
//		mm4.addToStage(stage);
		
//		mm.setPosition(50, 50);
		
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
		final Image map1 = new Image(new Texture(Gdx.files.internal("pictures/Map1.png")));
		map1.setCenterPosition(Jeu.WIDTH / 2 - fond.getWidth()/4, Jeu.HEIGHT / 2 + fond.getHeight()/4);
		map1.addCaptureListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//					map1.addAction(Actions.sequence(Actions.fadeOut(0.1f)));
					rectMap.setBounds(map1.getX(), map1.getY()-1, map1.getWidth()+2, map1.getHeight()+2);
					return true;
				}
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				map1.addAction(Actions.sequence(Actions.fadeIn(0.1f)));
				}
		});
		
		
//		stage.addActor(map1);

		
		final Image map2 = new Image(new Texture(Gdx.files.internal("pictures/Map2.png")));
		map2.setCenterPosition(Jeu.WIDTH / 2 - fond.getWidth()/4, Jeu.HEIGHT / 2 - fond.getHeight()/4);
		map2.addCaptureListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
//				map2.addAction(Actions.sequence(Actions.fadeOut(0.1f)));
				rectMap.setBounds(map2.getX()-1, map2.getY()-1, map2.getWidth()+1, map2.getHeight()+1);
				return true;
			}
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				map2.addAction(Actions.sequence(Actions.fadeIn(0.1f)));
			}
		});
		
//		stage.addActor(map2);

		table.add(map1).expand();
		table.add(mm1).width(400);
		table.row();
		
		table.add(mm2).pad(20);
		table.row();
		
		table.add(map2).pad(50);
		table.add(mm3);
		
	}

	@Override
	public void hide() {
		MusicManager.pause(typeSong.selection);
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

}