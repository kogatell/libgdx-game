package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class ShootingEnemy extends GameObject{

    public static final int SPEED = 3;
    public static final float WIDTH = 6;
    public static final float HEIGHT = 6;



    Collision col;

    public ShootingEnemy (float x) {
        this.position.x = x;
        this.width = WIDTH;
        this.height = HEIGHT;
        this.position.y = Constants.viewPortHeight;
        this.col = new Collision(this.position.x, this.position.y, WIDTH, HEIGHT);
        this.textureRegion = AssetManager.getInstance().shootingEnemy;
        this.typeOfGO = 2;
    }
    @Override
    public void update (float deltaTime) {
        this.position.y -= SPEED * deltaTime;
        col.move(this.position.x, this.position.y);
    }

    @Override
    public void render (SpriteBatch batch) {
        batch.draw(this.textureRegion, this.position.x, this.position.y, width, height);
    }
    @Override
    public Collision getCollisionRect () {
        return col;
    }
}