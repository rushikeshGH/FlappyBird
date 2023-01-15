package com.mygdx.flappybird;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Pipe {
    enum Orientation {
        TOP,
        BOTTOM,
    }

    private static final Texture pipe;
    private static final int height;

    public static int getHeight() {
        return height;
    }

    private final Orientation orientation;
    private final Vector2 position;

    static {
        pipe = new Texture("pipe.png");
        height = pipe.getHeight();
    }

    public Pipe(Orientation orientation, float x, float y) {
        this.orientation = orientation;
        this.position = new Vector2(x - (pipe.getWidth() / 2),
                orientation == Orientation.TOP ? y : y + pipe.getHeight());
    }

    public void update(float deltaTime) {
        this.position.x -= 60 * deltaTime;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(pipe,
                position.x,
                position.y);
    }
}
