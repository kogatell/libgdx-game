package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import javax.xml.soap.Text;
import java.util.ArrayList;

public class GameObject {
    public Vector2 position;
    public float speed;
    public float width;
    public float height;
    public float shootingTimer = 2f;
    float _x;
    int typeOfGO;
    Texture texture;
    TextureRegion textureRegion;
    TextureAtlas atlas;
    ParticleEffect _particleEffect = new ParticleEffect();
    float rotation;

    public GameObject() {
        position = new Vector2();
        speed = 0.0f;
    }



    public void giveParticleEffect(String s) {
        _particleEffect.load(Gdx.files.internal(s), Gdx.files.internal(""));
    }


    public void render (SpriteBatch batch){

    }
    public void update(float delta)
    {

    }
    public Collision getCollisionRect()
    {
        return null;

    }

}
