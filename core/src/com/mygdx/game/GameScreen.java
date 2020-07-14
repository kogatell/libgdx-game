package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameScreen implements Screen, ControllerListener {

    MyGdxGame game;
    Player player;
    boolean controllerActive;
    CameraHelper cameraHelper;
    SpriteBatch batch;
    FPSLogger fps;
    ShapeRenderer shape;
    BackgroundImage backgroundImage;
    ArrayList<GameObject> goToDestroy;
    ArrayList<GameObject> goToAdd;
    FollowEnemy followEnemy;
    ArrayList<GameObject> go;
    Sound explosionSound;
    public boolean hardcore;
    float asteroidSpawnTimer;
    float shootingEnemySpawnTimer;
    float chaseEnemySpawnTimeTimer;
    MainMenu menu;
    Camera camera2;
    Button left;
    Boolean canShoot;
    Random random;
    Music music;
    Button right;
    Button down;
    Button up;
    ParticleEffect explosion;
    Boolean isShooting;
    Button shoot;
    boolean powerUp;
    public GameScreen(MyGdxGame game) {
        this.game = game;
    }
    @Override
    public void show() {
        menu = new MainMenu(game);
        controllerActive = false;
        powerUp = false;
        isShooting = false;
        explosionSound = AssetManager.getInstance().explosionSound;
        explosion = new ParticleEffect();
        canShoot = true;
        explosion.load(Gdx.files.internal("explosionparticle.party"), Gdx.files.internal(""));
        go = new ArrayList<GameObject>();
        goToAdd = new ArrayList<GameObject>();
        goToDestroy = new ArrayList<GameObject>();
        player = new Player();
        go.add(player);
        right = new Button(player.position.x + Constants.viewportWidth/2 - 30,  10  , 10, 10, AssetManager.getInstance().rightArrow);
        left = new Button(player.position.x - Constants.viewportWidth/2 + 30,  10  , 10, 10, AssetManager.getInstance().leftarrow);
        down = new Button(player.position.x  - 120, 10   , 10, 10, AssetManager.getInstance().downArrow);
        up = new Button(player.position.x  - 100,  10  , 10, 10, AssetManager.getInstance().upArrow);
        shoot = new Button(player.position.x, 10, 10, 10, AssetManager.getInstance().laser);
        batch = new SpriteBatch();
        fps = new FPSLogger();
        shape = new ShapeRenderer();
        cameraHelper = new CameraHelper(Constants.viewportWidth, Constants.viewPortHeight, player);
        camera2 = new OrthographicCamera(Gdx.graphics.getWidth(), Constants.viewPortHeight); //Minimap
        go.add(new FollowEnemy(0, player));
        backgroundImage = new BackgroundImage();
        random = new Random();
        music = Gdx.audio.newMusic((Gdx.files.internal("music.mp3"))); //prod by me
        music.setLooping(true);
        music.setVolume(0.1f);
        //music.play();
        asteroidSpawnTimer = random.nextFloat() * (Constants.MAX_ASTEROID_SPAWN_TIME - Constants.MIN_ASTEROID_SPAWN_TIME) + Constants.MIN_ASTEROID_SPAWN_TIME;
        shootingEnemySpawnTimer = random.nextFloat() * (Constants.MAX_ASTEROID_SPAWN_TIME - Constants.MIN_ASTEROID_SPAWN_TIME) + Constants.MIN_ASTEROID_SPAWN_TIME;
        chaseEnemySpawnTimeTimer = random.nextFloat() * (Constants.MAX_CHASE_ENEMY_SPAWN_TIME - Constants.MIN_CHASE_ENEMY_SPAWN_TIME) + Constants.MIN_CHASE_ENEMY_SPAWN_TIME;
        //player.health = 3;
        Controllers.addListener(this);
        if(game.hardcore)
        {
            player.health = 1;
        }
        else
        {
            player.health = 3;
        }
        //Controllers.addListener().n;
    }

    @Override
    public void render(float delta) {
        //Gdx.app.log("Toco", "Render menu");
        fps.log();
        Gdx.gl.glClearColor(1f, 1f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //Gdx.gl.glViewport(0,0, Constants.viewportWidth, Constants);

        batch.setProjectionMatrix(cameraHelper.combined());
        batch.begin();
        update(delta);
        down.render(batch);
        up.render(batch);
        right.render(batch);
        left.render(batch);
        shoot.render(batch);
        explosion.draw(batch);


        for(GameObject go2: go)
        {
            go2.render(batch);
            go2.update(delta);
        }

        batch.end();

        batch.begin();
        Gdx.gl.glViewport(Gdx.graphics.getWidth() - 1020,(int)Gdx.graphics.getHeight() - 280, 1020, 280);
        batch.setProjectionMatrix(camera2.combined);
        for(GameObject go2: go)
        {
            go2.render(batch);
            go2.update(delta);
        }
        batch.end();

    }
    public void update(float delta)
    {
        explosion.update(delta);
        Vector2 touch = cameraHelper.getInputInGameWorld();
        if (Gdx.input.isTouched()) {
            System.out.println("X= "+touch.x+"Y= "+touch.y);
            //game.setScreen(game.gameScreen);
        }
        right.position.x = player.position.x + Constants.viewportWidth/2 - 30;
        right.bounds.setPosition(right.position.x, right.position.y);

        left.position.x = player.position.x + Constants.viewportWidth/2 - 10;
        left.bounds.setPosition(left.position.x, left.position.y);

        down.position.x = player.position.x - 30;
        down.bounds.setPosition(down.position.x, down.position.y);

        up.position.x = player.position.x  - 50;
        up.bounds.setPosition(up.position.x, up.position.y);

        shoot.position.x = player.position.x;
        shoot.bounds.setPosition(shoot.position.x, shoot.position.y);

        if(!controllerActive) {
            if (down.contains(touch.x, touch.y) && Gdx.input.isTouched()) {
                System.out.println("posicion boton" + down.bounds);
                player.isButtonDown = true;
            } else if (!down.contains(touch.x, touch.y) && !Gdx.input.isTouched()) player.isButtonDown = false;

            if (up.contains(touch.x, touch.y) && Gdx.input.isTouched()) {
                System.out.println("posicion boton" + up.bounds);
                player.isButtonPressedUp = true;
            }
            if (!up.contains(touch.x, touch.y) && !Gdx.input.isTouched()) player.isButtonPressedUp = false;

            if (up.contains(touch.x, touch.y) && Gdx.input.isTouched()) {
                System.out.println("posicion boton" + up.bounds);
                player.isButtonPressedUp = true;
            } else player.isButtonPressedUp = false;

            if (right.contains(touch.x, touch.y) && Gdx.input.isTouched()) {
                System.out.println("posicion boton" + right.bounds);
                player.isButtonPressedRight = true;
                System.out.println(player.dirX);
            } else player.isButtonPressedRight = false;

            if (left.contains(touch.x, touch.y) && Gdx.input.isTouched()) {
                player.isButtonPressedLeft = true;
            } else player.isButtonPressedLeft = false;

            if (shoot.contains(touch.x, touch.y) && Gdx.input.justTouched()) {
                isShooting = true;
            } else isShooting = false;
        }


        float y = Gdx.graphics.getHeight()-Gdx.input.getY();
        cameraHelper.update();
        //followEnemy.update(delta);
        backgroundImage.Update(delta, batch);
        //player.update(delta);
        asteroidSpawnTimer -= delta;
        shootingEnemySpawnTimer -= delta;
        chaseEnemySpawnTimeTimer -= delta;
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || isShooting && canShoot)
        {
            if(!powerUp)
            {
                Gdx.app.log("Disparo", "Bala");
                go.add(new Bullet(player.position.x + player.width/ 2 - Bullet.WIDTH / 2, player.position.y + player.height));
                player.isShooting = false;
            }
            else if(powerUp)
            {
                go.add(new Bullet(player.position.x + player.width/ 2 - Bullet.WIDTH / 2, player.position.y +player.height));
                go.add(new Bullet(player.position.x, player.position.y - Bullet.HEIGHT));
                go.add(new Bullet(player.position.x + player.width, player.position.y - Bullet.HEIGHT));
                powerUp = false;
            }
        }

        if (asteroidSpawnTimer <= 0) {
            //Gdx.app.error("Hola","Creo asteroide");
            asteroidSpawnTimer = random.nextFloat() * (Constants.MAX_ASTEROID_SPAWN_TIME - Constants.MIN_ASTEROID_SPAWN_TIME) + Constants.MIN_ASTEROID_SPAWN_TIME;
            go.add(new Asteroid(random.nextInt( (int) ((Constants.endBound) - Asteroid.WIDTH)) + (Constants.beginBound - Asteroid.WIDTH)));
        }
        if (shootingEnemySpawnTimer <= 0) {
            //Gdx.app.error("Hola","Creo ShootingEnemy");
            shootingEnemySpawnTimer = random.nextFloat() * (Constants.MAX_SHOOTING_ENEMY_SPAWN_TIME - Constants.MIN_SHOOTING_ENEMY_SPAWN_TIME) + Constants.MIN_SHOOTING_ENEMY_SPAWN_TIME;
            go.add(new ShootingEnemy(random.nextInt( (int) ((Constants.endBound) - ShootingEnemy.WIDTH)) + (Constants.beginBound - ShootingEnemy.WIDTH)));
        }
        if(chaseEnemySpawnTimeTimer <= 0)
        {
            //Gdx.app.error("Hola","Creo Chase Enemy");
            chaseEnemySpawnTimeTimer = random.nextFloat() * (Constants.MAX_CHASE_ENEMY_SPAWN_TIME - Constants.MIN_CHASE_ENEMY_SPAWN_TIME) + Constants.MIN_CHASE_ENEMY_SPAWN_TIME;
            go.add(new FollowEnemy(random.nextInt( (int) ((Constants.endBound) - FollowEnemy.WIDTH)) + (Constants.beginBound - FollowEnemy.WIDTH), player));
        }
        for(GameObject go2: go)
        {
            for(GameObject go1: go)
            {
                if(player.getCollisionRect().collidesWith(go1.getCollisionRect()) && go1.typeOfGO == 8)
                {
                    powerUp = true;
                    goToDestroy.add(go1);
                }
                for(int i = 2; i <= 7; i++) //Just enemies has this type
                {
                    if(go1.typeOfGO == i && go1.position.y <= -go1.height)
                    {
                        goToDestroy.add(go1);
                    }
                    if(go2.getCollisionRect().collidesWith(go1.getCollisionRect()) && go2.typeOfGO == 1 && go1.typeOfGO == i)
                    {
                        if(go1.typeOfGO == 4)
                        {
                            goToAdd.add(new ShootingPowerUp(go1.position.x, go1.position.y));
                        }
                        explosion.start();
                        explosion.setPosition(go1.position.x, go1.position.y);
                        //explosion.update(delta);
                        goToDestroy.add(go1);
                        goToDestroy.add(go2);
                        explosionSound.play();
                    }
                    if(go1.position.y < -go1.height)
                    {
                        goToDestroy.add(go1);
                    }
                    if(player.getCollisionRect().collidesWith(go1.getCollisionRect()) && go1.typeOfGO == i)
                    {
                        go1.getCollisionRect().move(-100, -100);
                        explosionSound.play();
                        goToDestroy.add(go1);
                        player.health --;
                        if(player.health == 0)
                        {
                            isShooting = false;
                            goToDestroy.addAll(go);
                            game.setScreen(menu);
                        }
                    }
                }
            }
            if(go2.typeOfGO == 1 && go2.position.y >= Constants.viewPortHeight + go2.height)
            {
                goToDestroy.add(go2);
                //Gdx.app.log("Destroyo", "Bala");
            }
            if(go2.typeOfGO == 2)
            {
                go2.shootingTimer -= delta;
                if (go2.shootingTimer <= 0) //Check when enemies can shoot
                {
                    go2.shootingTimer = random.nextFloat() * (Constants.MAX_SHOOTING_ENEMY_SHOOT_TIME - Constants.MIN_SHOOTING_ENEMY_SHOOT_TIME) + Constants.MIN_SHOOTING_ENEMY_SHOOT_TIME;
                    goToAdd.add(new EnemyBullet(go2.position.x, go2.position.y - go2.height));
                }
            }

        }

        go.addAll(goToAdd);
        goToAdd.removeAll(goToAdd);

        go.removeAll(goToDestroy);
    }

    @Override
    public void dispose() {
    }

    @Override
    public void pause() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void resume() {


    }

    @Override
    public void resize(int width, int height) {

        cameraHelper.resize(width, height);
    }

    @Override
    public void connected(Controller controller) {
        controllerActive = true;

    }

    @Override
    public void disconnected(Controller controller) {
        controllerActive = false;
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        Gdx.app.log("BOTON", String.valueOf(buttonCode));
        if(buttonCode == 0)
        {
            player.isButtonPressedLeft = true;
        }
        else player.isButtonPressedLeft = false;
        if(buttonCode == 2)
        {
            player.isButtonPressedRight = true;
        }
        else player.isButtonPressedRight = true;
        if(buttonCode == 3)
        {
            player.isButtonPressedUp = true;
        }
        else player.isButtonPressedUp = false;
        if(buttonCode == 1)
        {
            player.isButtonDown = true;
        } else player.isButtonDown = false;
        if(buttonCode == 5)
        {
            isShooting = true;
        } else isShooting = false;

        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        return false;
    }

    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        return false;
    }

    @Override
    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
        return false;
    }

    @Override
    public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
        return false;
    }
}
