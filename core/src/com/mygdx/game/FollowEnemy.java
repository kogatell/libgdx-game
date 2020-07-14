package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class FollowEnemy extends GameObject {
    public static final float WIDTH = 7;
    public static final float HEIGHT = 7;
    GameObject _player;
    float angle;
    Collision col;
    public boolean remove = false;
    public FollowEnemy (float x, GameObject player){
        this.position.x = x;
        this.width = WIDTH;
        this.height = HEIGHT;
        this.position.y = Constants.viewPortHeight;
        _player = player;
        this.col = new Collision(this.position.x, this.position.y, WIDTH, HEIGHT);
        this.textureRegion = AssetManager.getInstance().chaseEnemy;
        this.typeOfGO = 3;
    }
    public void update (float deltaTime) {
        if (this.position.y > Gdx.graphics.getHeight())
            remove = true;
        col.move(this.position.x,this.position.y);
        lookAtTarg(_player.position);
    }
    public void lookAtTarg(Vector2 target) {
        float diffX = target.x - this.position.x;
        float diffY = target.y - this.position.y;
        float angle = (float) Math.atan2(diffY, diffX);

        this.position.x += 0.2 * Math.cos(angle);
        this.position.y += 0.2 * Math.sin(angle);
        float rot = (float) Math.atan2(target.y - this.position.y, target.x - this.position.x);
        rot = (float) (angle * (180 / Math.PI) - 90f);
        //_x = MathUtils.cos(angle);
        //setAngle(rot);
        this.rotation = rot;
    }
    public void render(SpriteBatch batch) {
        //super.render(batch);
        batch.draw(this.textureRegion, this.position.x, this.position.y, width/2, height/2, width, height, 1, 1, this.rotation);
    }
    @Override
    public Collision getCollisionRect () {
        return col;
    }

}
