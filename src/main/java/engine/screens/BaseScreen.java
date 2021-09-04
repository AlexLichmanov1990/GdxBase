package engine.screens;

import engine.exceptions.IllegalResourceException;
import engine.resources.Resource;
import engine.stages.BaseStage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.graphics.GL20;

import java.util.ArrayList;

public abstract class BaseScreen implements Screen {
    private BaseStage stage;
    private ArrayList<Resource<?>> resources;

    public BaseScreen() {
        this.resources = new ArrayList<Resource<?>>();
        try {
            initBaseResources();
            initResources();
        } catch (IllegalResourceException e) {
            e.printStackTrace();
        }
    }

    public BaseStage getStage() {
        return stage;
    }

    public void setStage(BaseStage stage) {
        this.stage = stage;
    }

    public <T> void addResource(String name, Class<T> type) throws IllegalResourceException {
        addResource(name, type, null);
    }

    public <T> void addResource(String name, Class<T> type, AssetLoaderParameters params) throws IllegalResourceException {
        addRes(name, type, params);
    }

    private <T> void addRes(String name, Class<T> type, AssetLoaderParameters params) throws IllegalResourceException {
        for (int i = 0; i < resources.size(); i++) {
            if (this.resources.get(i).getName().equals(name)) {
                throw new IllegalResourceException();
            }
        }
        this.resources.add(new Resource<T>(name, type, params));
    }

    public ArrayList<Resource<?>> getResources() {
        return this.resources;
    }

    @Override
    public void show() {
        stage.onShow();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.5f, .5f, .5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (stage != null) {
            stage.act(delta);
            stage.draw();
        }
    }

    @Override
    public void resize(int width, int height) {
        if (stage != null) {
            this.stage.resize(width, height);
        }
    }

    public abstract void initStage();

    public abstract void initResources() throws IllegalResourceException;

    public abstract void initBaseResources() throws IllegalResourceException;

    @Override
    public void pause() {
        this.stage.pause();
    }

    @Override
    public void resume() {
        this.stage.resume();
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        this.stage.dispose();
    }
}
