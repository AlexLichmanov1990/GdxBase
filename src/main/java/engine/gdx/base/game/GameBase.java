package engine.gdx.base.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import engine.gdx.base.resources.ResourceManager;
import engine.gdx.base.screens.BaseScreen;

public abstract class GameBase extends Game {
    private float gameSpeed = 1f;

    private BaseScreen currentScreen;
    private BaseScreen loadingScreen;
    private BaseScreen screenToLoad;

    public GameBase() {
        gameSpeed = 1f;
    }

    public float getGameSpeed() {
        return gameSpeed;
    }

    public void setGameSpeed(float gamespeed) {
        gameSpeed = gamespeed;
    }

    public BaseScreen getCurrentScreen() {
        return this.currentScreen;
    }

    private void setCurrentScreen(BaseScreen currentScreen) {
        this.currentScreen = currentScreen;
    }

    public BaseScreen getLoadingScreen() {
        return this.loadingScreen;
    }

    public void setLoadingScreen(BaseScreen loadingScreen) {
        if (this.loadingScreen != null) {
            this.loadingScreen.dispose();
            ResourceManager.getInstance().unload(this.loadingScreen);
        }
        this.loadingScreen = loadingScreen;
        ResourceManager.getInstance().loadSync(loadingScreen);
    }

    public BaseScreen getScreenToLoad() {
        return this.screenToLoad;
    }

    private void setScreenToLoad(BaseScreen screenToLoad) {
        this.screenToLoad = screenToLoad;
    }

    public void setScreen(final BaseScreen screenToLoad) {
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                showLoading();
                setScreenToLoad(screenToLoad);

                // TODO Матчить ресурсы и оставлять те, которые будут на след экране
                ResourceManager.getInstance().unload(getCurrentScreen());
                ResourceManager.getInstance().load(getScreenToLoad());
            }
        });
    }

    protected void showLoading() {
        if (getLoadingScreen() != null) {
            getLoadingScreen().initStage();
            super.setScreen(getLoadingScreen());
            Gdx.input.setInputProcessor(null);
        }
    }

    protected void onLoaded() {
        if (getScreenToLoad() != null) {
            if (getCurrentScreen() != null) {
                getCurrentScreen().dispose();
            }
            setCurrentScreen(getScreenToLoad());
            setScreenToLoad(null);

            getCurrentScreen().initStage();
            super.setScreen(getCurrentScreen());
            Gdx.input.setInputProcessor(getCurrentScreen().getStage());
        }
    }

    @Override
    public void render() {
        super.render();
        if (ResourceManager.getInstance().update()) {
            onLoaded();
        }
    }
}
