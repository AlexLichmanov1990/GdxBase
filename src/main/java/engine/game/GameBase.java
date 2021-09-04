package engine.game;

import engine.managers.BaseGameManager;
import engine.managers.TimersManager;
import engine.resources.ResourceManager;
import engine.screens.BaseScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public abstract class GameBase extends Game {
    private float gameSpeed = 1f;

    private BaseScreen currentScreen;
    private BaseScreen loadingScreen;
    private BaseScreen screenToLoad;

    public GameBase() {
        BaseGameManager.getInstance().setGame(this);
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
            TimersManager.getInstance().clearDisposable();
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
        float delta = Gdx.graphics.getDeltaTime();
        TimersManager.getInstance().act(delta);
    }
}
