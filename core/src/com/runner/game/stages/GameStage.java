package com.runner.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.runner.game.actors.Enemy;
import com.runner.game.actors.Ground;
import com.runner.game.actors.Runner;
import com.runner.game.utils.BodyUtils;
import com.runner.game.utils.WorldUtils;

/**
 * Created by cullycross on 3/22/15.
 */
public class GameStage extends Stage implements ContactListener {

    private static final int VIEWPORT_WIDTH = 20;
    private static final int VIEWPORT_HEIGHT = 13;

    private World mWorld;
    private Ground mGround;
    private Runner mRunner;

    private final static float TIME_STEP = 1 / 300f;
    private float mAccumulator = 0f;

    private OrthographicCamera mCamera;
    private Box2DDebugRenderer mRenderer;

    private Rectangle mScreenRightSide;
    private Rectangle mScreenLeftSide;

    private Vector3 mTouchPoint;

    public GameStage() {
        setupWorld();

        mRenderer = new Box2DDebugRenderer();

        setupCamera();
        setupTouchControlAreas();
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        if(mRunner.isDodging()) {
            mRunner.stopDodge();
        }

        return super.touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        translateScreenToWorldCoordinates(screenX, screenY);

        if(rightSideTouched(mTouchPoint.x, mTouchPoint.y)) {
            mRunner.jump();
        } else if(leftSideTouched(mTouchPoint.x, mTouchPoint.y)) {
            mRunner.dodge();
        }
        return super.touchDown(screenX, screenY, pointer, button);
    }

    private void setupCamera() {
        mCamera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        mCamera.position.set(mCamera.viewportWidth / 2, mCamera.viewportHeight / 2, 0f);
        mCamera.update();
    }

    private void setupWorld() {
        mWorld = WorldUtils.createWorld();
        mWorld.setContactListener(this);
        setupGround();
        setupRunner();
        createEnemy();
    }

    private void setupGround() {
        mGround = new Ground(WorldUtils.createGround(mWorld));
        addActor(mGround);
    }

    private void setupRunner() {
        mRunner = new Runner(WorldUtils.createRunner(mWorld));
        addActor(mRunner);
    }

    private void createEnemy() {
        Enemy enemy = new Enemy(WorldUtils.createEnemy(mWorld));
        addActor(enemy);
    }

    private void setupTouchControlAreas() {
        mTouchPoint = new Vector3();
        mScreenLeftSide = new Rectangle(0, 0, getCamera().viewportWidth / 2,
                getCamera().viewportHeight);
        mScreenRightSide = new Rectangle(getCamera().viewportWidth / 2, 0, getCamera().viewportWidth / 2,
                getCamera().viewportHeight);
        Gdx.input.setInputProcessor(this);
    }

    private void translateScreenToWorldCoordinates(int x, int y) {
        getCamera().unproject(mTouchPoint.set(x, y, 0));
    }

    private boolean rightSideTouched(float x, float y) {
        return mScreenRightSide.contains(x, y);
    }

    private boolean leftSideTouched(float x, float y) {
        return mScreenLeftSide.contains(x, y);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        Array<Body> bodies = new Array<Body>(mWorld.getBodyCount());
        mWorld.getBodies(bodies);

        for (Body body : bodies) {
            update(body);
        }

        mAccumulator += delta;

        while(mAccumulator >= delta) {
            mWorld.step(TIME_STEP, 6, 2);
            mAccumulator -= TIME_STEP;
        }

        //TODO: implement interpolation
    }

    private void update(Body body) {
        if (!BodyUtils.bodyInBounds(body)) {
            if (BodyUtils.isEnemy(body) && !mRunner.isHit()) {
                createEnemy();
            }
            mWorld.destroyBody(body);
        }
    }

    @Override
    public void draw() {
        super.draw();

        mRenderer.render(mWorld, mCamera.combined);
    }

    @Override
    public void beginContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        if ((BodyUtils.isRunner(a) && BodyUtils.isEnemy(b)) ||
                (BodyUtils.isEnemy(a) && BodyUtils.isRunner(b))) {
            mRunner.hit();
        } else if((BodyUtils.isRunner(a) && BodyUtils.isGround(b)) ||
                (BodyUtils.isGround(a) && BodyUtils.isRunner(b))) {
            mRunner.landed();
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
