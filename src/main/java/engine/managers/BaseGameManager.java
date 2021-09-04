package engine.managers;

import engine.game.GameBase;
import engine.game.IGameProperties;
import engine.game.IWinListener;
import engine.screens.BaseScreen;

import java.util.ArrayList;

public class BaseGameManager {
    protected static BaseGameManager instance = null;
    private ArrayList<IWinListener> winListeners;
    private GameBase game;
    private IGameProperties gameProperties;
    protected BaseGameManager() {
        winListeners = new ArrayList<>();
    }

    public static BaseGameManager getInstance() {
        if (instance == null) instance = new BaseGameManager();
        return instance;
    }

    public GameBase getGame() {
        return game;
    }

    public void setGame(GameBase game) {
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

    public void addWinListener(IWinListener winListener) {
        winListeners.add(winListener);
    }

    public void removeWinListener(IWinListener winListener) {
        winListeners.remove(winListener);
    }

    public void onWin(Object... award) {
        for (int i = 0; i < this.winListeners.size(); i++) {
            this.winListeners.get(i).onWin(award);
        }
    }

    public void onLose() {
        for (int i = 0; i < this.winListeners.size(); i++) {
            this.winListeners.get(i).onLose();
        }
    }
}
