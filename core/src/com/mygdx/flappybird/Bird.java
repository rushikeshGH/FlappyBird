package com.mygdx.flappybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Bird {
    private Texture bird;
    private Vector2 position;
    private Vector2 velocity = Vector2.Zero;

    public Bird(float x, float y) {
        bird = new Texture("bird.png");
        position = new Vector2(x, y);
    }

    public void update(float deltaTime) {
        velocity.y += 20 * Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            velocity.y = -5;
        }

        position.sub(velocity);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(bird, position.x - (bird.getWidth() / 2), position.y - (bird.getHeight() / 2));
    }
}
