package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Player extends GameObject{
    Collision col;
    public static final float WIDTH = 10;
    public static final float HEIGHT = 10;
    public float dirX;
    public float dirY;
    public boolean isDead;
    public int health;
    //public ArrayList<Bullet> bullets;
    boolean isShooting;
    public boolean isButtonPressedRight;
    public boolean isButtonPressedLeft;
    public boolean isButtonPressedUp;
    public boolean isButtonDown;
    public boolean isButtonShoot;
    public Player(){
        super();
        this.position.x = 200;
        this.position.y = 20;
        isShooting = false;
        isDead = false;
        dirX = 0;
        dirY = 0;
        this.speed = 10;
        this.width = WIDTH;
        this.height = HEIGHT;
        this.typeOfGO = 0;
        this.textureRegion = AssetManager.getInstance().player;
        this.col = new Collision(this.position.x, this.position.y, this.width, this.height);
        //bullets = new ArrayList<Bullet>();
    }
    public Vector2 GetPosition()
    {
        return this.position;
    }
    public void update(float deltaTime){
        if(Gdx.app.getType() == Application.ApplicationType.Desktop || Gdx.app.getType() == Application.ApplicationType.Android)
        {
            //Gdx.app.log("Presiono", "la key");
            Boolean a = Gdx.input.isKeyPressed(Input.Keys.A);
            Boolean d = Gdx.input.isKeyPressed(Input.Keys.D);
            Boolean w = Gdx.input.isKeyPressed(Input.Keys.W);
            Boolean s = Gdx.input.isKeyPressed(Input.Keys.S);
            Boolean space = Gdx.input.isKeyPressed(Input.Keys.SPACE);
            int p = 2;
            if(space || isButtonShoot)
            {
                shoot();
            }
            if (a || isButtonPressedLeft)
            {
                dirX = -1;
            }
            else if (d || isButtonPressedRight)
            {
                dirX = 1;
            }
            else
            {
                dirX = 0;
            }
            if(w || isButtonPressedUp)
            {
                dirY = 1;
            }
            else if(s || isButtonDown)
            {
                dirY = -1;
            }
            else
            {
                dirY = 0;
            }
        }
        else if(Gdx.app.getType() == Application.ApplicationType.Android)
        {
            dirX = Gdx.input.getGyroscopeZ();
            dirY = Gdx.input.getGyroscopeX();
        }
        //else if()
        col.move(this.position.x,this.position.y);
        this.position.x += (this.speed * deltaTime) * dirX;
        this.position.y += (this.speed * deltaTime) * dirY;
        if(this.position.x < Constants.beginBound)//change this for real values
        {
            this.position.x = Constants.beginBound;
        }
        if(this.position.y < 0)
        {
            this.position.y = 0;
        }
        if(this.position.y > Constants.endBound)
        {
            this.position.y = Constants.endBound;
        }
    }
    @Override
    public Collision getCollisionRect () {
        return col;
    }
    public void shoot()
    {
        if(!isShooting) {
            isShooting = true;
        }
        else isShooting = false;
    }
    public void render(SpriteBatch batch)
    {
        batch.draw(AssetManager.getInstance().player, position.x, position.y, width/2, height/2, width, height, 1, 1, 0);
    }
}
