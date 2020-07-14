package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GameOver implements Screen {
    MyGdxGame game;
    TextureRegion hardcore;
    TextureRegion start;
    TextureRegion mute;
    GameScreen gameScreen;
    BitmapFont font;
    SpriteBatch batch;
    GameObject target;
    Texture background;
    public static CameraHelper cameraHelper;
    Button b1;
    Button b2;
    Button b3;;

    public GameOver(MyGdxGame game) {
        this.game = game;
        font = new BitmapFont();
        batch = new SpriteBatch();
        target = new GameObject();
        target.position.set(0,0);
        background = AssetManager.getInstance().backgroundImage;
        cameraHelper = new CameraHelper(Constants.viewportWidth, Constants.viewPortHeight, target);
        b2 = new Button(Gdx.graphics.getWidth()/2 , Gdx.graphics.getHeight()/2 + 150, 300, 100, AssetManager.getInstance().startButton);
        b3 = new Button(Gdx.graphics.getWidth()/2 - 150, Gdx.graphics.getHeight()/2, 300, 100, AssetManager.getInstance().hardcore);
        hardcore = AssetManager.getInstance().hardcore;
        start = AssetManager.getInstance().startButton;
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
