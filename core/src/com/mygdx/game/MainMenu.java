package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;


import java.util.concurrent.CopyOnWriteArrayList;

public class MainMenu implements Screen {

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

    public MainMenu(MyGdxGame game) {
        this.game = game;
        font = new BitmapFont();
        batch = new SpriteBatch();
        target = new GameObject();
        target.position.set(0,0);
        background = AssetManager.getInstance().backgroundImage;
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cameraHelper = new CameraHelper(Constants.viewportWidth, Constants.viewPortHeight, target);
        //b1 = new Button ("Hardcore mode", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2 + 150, 300, 100);
        b2 = new Button(Gdx.graphics.getWidth()/2 - 150, Gdx.graphics.getHeight()/2 + 150, 300, 100, AssetManager.getInstance().startButton);
        b3 = new Button(Gdx.graphics.getWidth()/2 - 150, Gdx.graphics.getHeight()/2, 300, 100, AssetManager.getInstance().hardcore);
        hardcore = AssetManager.getInstance().hardcore;
        start = AssetManager.getInstance().startButton;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        //update
        float y = Gdx.graphics.getHeight()-Gdx.input.getY();
        Vector3 touch = new Vector3(Gdx.input.getX(), Gdx.graphics.getHeight()-Gdx.input.getY(), 0);
        if (Gdx.input.isTouched()) {
            System.out.println("X= "+touch.x+"Y= "+touch.y);
            //game.setScreen(game.gameScreen);
        }
        //Gdx.app.log("Toco", "Rend2er menu");

        //render

        batch.begin();

        font.draw(batch, "Click Anywhere to start", 0, 0, Constants.viewportWidth /2, Align.center, true);
        //b1.render(batch);
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        b2.render(batch);
        b3.render(batch);
        if(b2.click(Gdx.input.getX(), y) && Gdx.input.justTouched())
        {
            game.hardcore = false;
            game.setScreen(game.gameScreen);
        }
        if(b3.click((Gdx.input.getX()), y) && Gdx.input.justTouched())
        {
            game.hardcore = true;
            game.setScreen(game.gameScreen);
        }

        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("Camara", width+ " "+height);
        cameraHelper.resize(width, height);
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
        batch.dispose();
        font.dispose();
    }
}