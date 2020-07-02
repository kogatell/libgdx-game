package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class AssetManager {
    private static AssetManager instance;
    public Texture t;
    private AssetManager()
    {
        t = new Texture(Gdx.files.internal("badlogic.jpg"));
    }
    public static AssetManager getInstance()
    {
        if (instance == null)
        {
            instance = new AssetManager();
        }
        return instance;
    }
}
