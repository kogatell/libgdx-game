package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.security.PublicKey;

public class AssetManager {
    private static AssetManager instance;
    public Texture backgroundImage;
    public TextureRegion player;
    public TextureRegion chaseEnemy;
    public TextureAtlas atlas;
    public TextureRegion asteroid;
    public TextureRegion shield;
    public TextureRegion rightArrow;
    public TextureRegion shootingEnemy;
    public TextureRegion hardcore;
    public TextureRegion startButton;
    Music mainMenuMusic;
    public TextureRegion laser;
    public Texture loose;
    public TextureRegion leftarrow;
    TextureAtlas atlas2;
    TextureAtlas atlasArrows;
    public TextureRegion explosion;
    public TextureRegion downArrow;
    public TextureRegion upArrow;
    public TextureRegion powerUp;
    public Sound explosionSound;
    private AssetManager()
    {
        atlas = new TextureAtlas(Gdx.files.internal("AtlasGame.atlas"));
        atlas2 = new TextureAtlas(Gdx.files.internal("Atlas2.atlas"));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("Explosion+7.mp3"));
        explosion = atlas2.findRegion("explosion");
        atlasArrows = new TextureAtlas("arrows.atlas");
        powerUp = atlas.findRegion("DoubleShot");
        downArrow = atlasArrows.findRegion("downarrow");
        upArrow = atlasArrows.findRegion("uparrow");
        asteroid = atlas.findRegion("Asteroid Brown");
        backgroundImage = new Texture(Gdx.files.internal("background.jpg"));
        rightArrow = atlas2.findRegion("rightarrow");
        leftarrow = atlas2.findRegion("leftarrow");
        player = atlas.findRegion("spaceship");
        chaseEnemy = atlas.findRegion("ChaseSpaceship");
        startButton = atlas.findRegion("startbutton");
        loose = new Texture(Gdx.files.internal("youlose.png"));
        hardcore = atlas.findRegion("hardcore");
        shield = atlas.findRegion("spr_shield");
        laser = atlas.findRegion("mega-laser-1");
        shootingEnemy = atlas.findRegion("ufo");
        mainMenuMusic = Gdx.audio.newMusic((Gdx.files.internal("newnew.mp3")));
        mainMenuMusic.setLooping(true);
        mainMenuMusic.setVolume(0.1f);
        mainMenuMusic.play();
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
