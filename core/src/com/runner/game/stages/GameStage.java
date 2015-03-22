package com.runner.game.stages;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.runner.game.utils.WorldUtils;

/**
 * Created by cullycross on 3/22/15.
 */
public class GameStage extends Stage {

    private static final int VIEWPORT_WIDTH = 20;
    private static final int VIEWPORT_HEIGHT = 13;

    private World mWorld;
    private Body mGround;

    private final static float TIME_STEP = 1 / 300f;
    private float mAccumulator = 0f;

    private OrthographicCamera mCamera;
    private Box2DDebugRenderer mRenderer;

    public GameStage() {
        mWorld = WorldUtils.createWorld();
        mGround = WorldUtils.createGround(mWorld);

        mRenderer = new Box2DDebugRenderer();

        setupCamera();
    }

    private void setupCamera() {
        mCamera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        mCamera.position.set(mCamera.viewportWidth / 2, mCamera.viewportHeight / 2, 0f);
        mCamera.update();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        mAccumulator += delta;

        while(mAccumulator >= delta) {
            mWorld.step(TIME_STEP, 6, 2);
            mAccumulator -= TIME_STEP;
        }

        //TODO: implement interpolation
    }

    @Override
    public void draw() {
        super.draw();

        mRenderer.render(mWorld, mCamera.combined);
    }
}
