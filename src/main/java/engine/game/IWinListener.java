package engine.game;

public interface IWinListener {
    void onWin(Object... awards);

    void onLose();
}
