package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EnemyBullet extends GameObject{
    public int SPEED = 6;
    public static final float WIDTH = 3;
    public static final float HEIGHT = 3;
    Collision col;
    public boolean remove = false;

    public EnemyBullet (float x, float y) {
        this.position.x = x;
        this.position.y = y;
        this.width = WIDTH;
        this.rotation = 180;
        this.height = HEIGHT;
        this.col = new Collision(this.position.x, this.position.y, WIDTH, HEIGHT);
        this.typeOfGO = 5;
        this.textureRegion = AssetManager.getInstance().laser;
    }
    @Override
    public void update (float deltaTime) {
        this.position.y -= SPEED * deltaTime;
        //Gdx.app.error("BulletEnemy", this.position.y+ "Bullet position");
        if (this.position.y > Gdx.graphics.getHeight())
            remove = true;
        col.move(this.position.x,this.position.y);
    }
    @Override
    public void render (SpriteBatch batch) {
        batch.draw(this.textureRegion, this.position.x, this.position.y, width/2, height/2, width, height, 1, 1, 180);
        //Gdx.app.error("cojones", this.position.x + this.position.y + "posicion");
    }
    @Override
    public Collision getCollisionRect () {
        return col;
    }
}
