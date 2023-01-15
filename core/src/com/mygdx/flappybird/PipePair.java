package com.mygdx.flappybird;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class PipePair {
    private Vector2 position;
    private Pipe[] pipes = new Pipe[2];

    public PipePair(float x, float y) {
        this.position = new Vector2(x, y);

        pipes[0] = new Pipe(Pipe.Orientation.BOTTOM, x, y);
        pipes[1] = new Pipe(Pipe.Orientation.TOP, x, y + 90);
    }

    public void update(float deltaTime) {
        for (Pipe pipe : pipes) {
            pipe.update(deltaTime);
        }
    }

    public void draw(SpriteBatch batch) {
        for (Pipe pipe : pipes) {
            pipe.draw(batch);
        }
    }
}
