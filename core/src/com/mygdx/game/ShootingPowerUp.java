package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class ShootingPowerUp extends GameObject{

    public static final float SPEED = 20;
    public static final float WIDTH = 3f;
    public static final float HEIGHT = 3f;



    Collision col;
    public boolean remove = false;

    public ShootingPowerUp (float x, float y) {
        this.position.x = x;
        this.position.y = y;
        this.width = WIDTH;
        this.height = HEIGHT;
        this.col = new Collision(x, y, WIDTH, HEIGHT);
        this.textureRegion = AssetManager.getInstance().powerUp;
        this.typeOfGO = 8;
    }
    @Override
    public void update (float deltaTime) {
        this.position.y -= SPEED * deltaTime;
        if (this.position.y < -HEIGHT)
            remove = true;
        Gdx.app.error("Sprite", this.position.y +"Posicion");
        col.move(this.position.x, this.position.y);
    }


    public void render (SpriteBatch batch) {
        batch.draw(this.textureRegion, this.position.x, this.position.y, width, height);
    }
    @Override
    public Collision getCollisionRect () {
        return col;
    }

    public float getX () {
        return this.position.x;
    }

    public float getY () {
        return this.position.y;
    }

}
