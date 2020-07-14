package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet extends GameObject{
    public int SPEED = 200;
    public static final float WIDTH = 4;
    public static final float HEIGHT = 4;
    Collision col;
    public boolean remove = false;

    public Bullet (float x, float y) {
        this.position.x = x;
        this.position.y = y;
        this.width = WIDTH;
        this.height = HEIGHT;
        this.textureRegion = AssetManager.getInstance().laser;
        this.col = new Collision(this.position.x, this.position.y, WIDTH, HEIGHT);
        this.typeOfGO = 1;
    }
    @Override
    public void update (float deltaTime) {
        this.position.y += SPEED * deltaTime;
        if (this.position.y > Gdx.graphics.getHeight())
            remove = true;
        col.move(this.position.x,this.position.y);
    }
    @Override
    public void render (SpriteBatch batch) {
        batch.draw(this.textureRegion, this.position.x, this.position.y, width, height);
        //Gdx.app.error("BulletPosition", this.position.x + this.position.y + "posicion");
    }
    @Override
    public Collision getCollisionRect () {
        return col;
    }


}

