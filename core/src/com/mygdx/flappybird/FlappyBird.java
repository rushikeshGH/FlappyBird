package com.mygdx.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.List;

public class FlappyBird extends ApplicationAdapter {
    private Camera camera;
    private Viewport viewport;
    private SpriteBatch batch;
    private Texture background;
    private Texture ground;
    private float backgroundPositionX;
    private float groundPositionX;
    private Bird bird;
    private float timer;
    private List<PipePair> pipePairs = new ArrayList<PipePair>();
    private float lastY;

    @Override
    public void create() {
        camera = new OrthographicCamera(512, 288);
        viewport = new FitViewport(camera.viewportWidth, camera.viewportHeight, camera);
        batch = new SpriteBatch();
        background = new Texture("background.png");
        ground = new Texture("ground.png");
        bird = new Bird(camera.viewportWidth / 2, camera.viewportHeight / 2);

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
    }

    @Override
    public void render() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        backgroundPositionX -= (30 * deltaTime);
        backgroundPositionX = backgroundPositionX % 413;
        groundPositionX -= (60 * deltaTime);
        groundPositionX = groundPositionX % camera.viewportWidth;

        timer += deltaTime;

        if (timer > 2) {
            float x = camera.viewportWidth + 64;
            float y = (float) Math.max(-Pipe.getHeight() + 10,
                    Math.min(lastY + (-20 + 40 * Math.random()),
                            camera.viewportHeight - 90 - Pipe.getHeight()));
            lastY = y;
            PipePair pipePair = new PipePair(x, -Pipe.getHeight() + 10);
            pipePairs.add(pipePair);
            timer = 0;
        }

        bird.update(deltaTime);
        for (PipePair pipePair : pipePairs) {
            pipePair.update(deltaTime);
        }

        viewport.apply();
        camera.update();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(background, backgroundPositionX, 0);
        for (PipePair pipePair : pipePairs) {
            pipePair.draw(batch);
        }
        batch.draw(ground, groundPositionX, 0);
        bird.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
    }
}
