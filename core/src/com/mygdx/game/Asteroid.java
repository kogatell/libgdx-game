package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Asteroid extends GameObject{

    public static final int SPEED = 5;
    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;
    float ang;
    Collision col;

    public Asteroid (float x) {
        this.position.x = x;
        this.width = WIDTH;
        this.height = HEIGHT;
        this.position.y = Constants.viewPortHeight;
        this.col = new Collision(x, this.position.y, WIDTH, HEIGHT);
        this.typeOfGO = 4;
        this.textureRegion = AssetManager.getInstance().asteroid;
    }
    @Override
    public void update (float deltaTime) {
        this.position.y -= SPEED * deltaTime;
        col.move(this.position.x, this.position.y);
        ang += 500f * deltaTime;
        //Gdx.app.log("Rotacion", String.valueOf(ang));
    }

    @Override
    public void render (SpriteBatch batch) {
        batch.draw(this.textureRegion, this.position.x, this.position.y, width/2, height/2, width, height, 1, 1, ang);
    }
    @Override
    public Collision getCollisionRect () {
        return col;
    }
}