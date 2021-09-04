package engine.gdx.base.managers;

import engine.gdx.base.game.GameBase;
import engine.gdx.base.game.IGameProperties;
import engine.gdx.base.screens.BaseScreen;

public class BaseGameManager<T extends GameBase> {
    protected static BaseGameManager instance = null;
    private T game;
    private IGameProperties gameProperties;

    protected BaseGameManager() {
    }

    public static BaseGameManager getInstance() {
        if (instance == null) instance = new BaseGameManager();
        return instance;
    }

    public T getGame() {
        return game;
    }

    public void setGame(T game) {
        this.game = game;
    }

    public float getGameSpeed() {
        return game.getGameSpeed();
    }

    public void setGameSpeed(float gamespeed) {
        game.setGameSpeed(gamespeed);
    }

    public void setScreen(BaseScreen screen) {
        game.setScreen(screen);
    }

    public IGameProperties getGameProperties() {
        return gameProperties;
    }

    public void setGameProperties(IGameProperties gameProps) {
        this.gameProperties = gameProps;
    }

}
