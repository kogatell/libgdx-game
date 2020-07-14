package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;


public class PathEnemy extends GameObject{

    public static final int SPEED = 5;
    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;
    ArrayList<Vector2> pathPoints;
    float ang;
    Collision col;

    public PathEnemy (float x) {
        this.position.x = x;
        pathPoints = new ArrayList<>();
        this.width = WIDTH;
        this.height = HEIGHT;
        this.position.y = Constants.viewPortHeight;
        this.col = new Collision(x, this.position.y, WIDTH, HEIGHT);
        this.typeOfGO = 4;
        this.textureRegion = AssetManager.getInstance().asteroid;
        Vector2 pathPoint = new Vector2();
        pathPoint.x = this.position.x - Constants.viewportWidth;
        pathPoint.y = this.position.y - WIDTH * 2;
        pathPoints.set(0, pathPoint);
        pathPoint.y = WIDTH * 2;
        pathPoints.set(1, pathPoint);
        pathPoint.x = this.position.x + Constants.viewportWidth * 2;
    }
    @Override
    public void update (float deltaTime) {
        this.position.y -= SPEED * deltaTime;
        col.move(this.position.x, this.position.y);
        ang += 500f * deltaTime;
        Gdx.app.log("Rotacion", String.valueOf(ang));
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