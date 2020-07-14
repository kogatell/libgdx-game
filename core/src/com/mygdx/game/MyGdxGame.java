package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends Game {
	SpriteBatch batch;
	Texture img;
	public MainMenu menu;
	public GameScreen gameScreen;
	public boolean hardcore;

	
	@Override
	public void create () {
		menu = new MainMenu(this);
		gameScreen = new GameScreen(this);
		setScreen(menu);
	}

	/*@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(AssetManager.getInstance().t, 0, 0);
		batch.end();
	}*/
	
	@Override
	public void dispose () {
	}
}
