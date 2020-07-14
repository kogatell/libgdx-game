package com.mygdx.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;

public class Button {
    public Texture buttonText;

    Rectangle bounds;
    Vector2 position;
    Vector2 dimension;
    TextureRegion icon;
    boolean sombrea;
    float imageWidth;

    public Button(float x, float y, float width, float height, TextureRegion img) {
        this.dimension = new Vector2(width, height);
        this.position = new Vector2(x, y);
        buttonText = createButtonTexture();
        icon = img;
        bounds = new Rectangle(position.x, position.y, dimension.x, dimension.y);
    }

    public void render(Batch batch) {
        if(sombrea)
        {
            //batch.setColor(Color.WHITE);
        }
        //else batch.setColor(Color.GRAY);
        batch.draw(buttonText, position.x, position.y, dimension.x, dimension.y);
        batch.draw(icon, this.position.x + dimension.x / 2 - dimension.y / 2, this.position.y, dimension.y, dimension.y);
    }

    private Texture createButtonTexture() {
        Pixmap pm = new Pixmap(10, 10, Pixmap.Format.RGBA8888);
        pm.setColor(0, 0, 1, 1);
        pm.drawRectangle(0, 0, 10, 10);
        pm.setColor(0, 0, 1, 1);
        pm.fillRectangle(1, 1, 8, 8);
        return new Texture(pm);
    }

    public boolean contains(float x, float y) {
        return bounds.contains(x, y);
    }

    public boolean click(float x, float y) {
        if (this.contains(x, y)) {
            return true;
        }
        return false;
    }
}
