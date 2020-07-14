package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;


public class HUD {

    ArrayList<Button> elements;

    public HUD(){
        elements = new ArrayList<Button>();
    }

    public void add(Button go){
        elements.add(go);
    }


    public void render(SpriteBatch batch) {
        for(Button he : elements){
            he.render(batch);
        }
    }

    public boolean click(float x, float y) {

        for(Button b : elements){
            if(b.click(x,y)){
                return true;
            }
        }
        return false;
    }

    public void sombrea(float x, float y) {

        for(Button b : elements){
            if(b.contains(x,y)){
                b.sombrea = true;
            }
            else{
                b.sombrea = false;
            }
        }
    }
}
