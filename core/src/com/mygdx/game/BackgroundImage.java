package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BackgroundImage {
    Texture background1, background2;
    public  float yMax, yCoordBg1, yCoordBg2;
    final int BACKGROUND_MOVE_SPEED = 10;
    BackgroundImage()
    {
        background1 = new Texture(Gdx.files.internal("background.jpg"));
        background2 = new Texture(Gdx.files.internal("background.jpg")); // identical
        yMax = Constants.viewPortHeight;
        yCoordBg1 = yMax*(-1);
        yCoordBg2 = 0;
    }
    public void Update(float deltaTime, SpriteBatch batch)
    {
        this.yCoordBg1 += this.BACKGROUND_MOVE_SPEED * deltaTime;
        this.yCoordBg2 = this.yCoordBg1 + this.yMax;
        if (this.yCoordBg1 >= 0) {
            this.yCoordBg1 = this.yMax*(-1); this.yCoordBg2 = 0;
        }
        batch.draw(this.background1, 0, this.yCoordBg1, 400, 400);
        batch.draw(this.background2, 0, this.yCoordBg2, 400 ,400);
    }
}
