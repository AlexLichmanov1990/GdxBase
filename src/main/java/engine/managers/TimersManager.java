package engine.managers;

import engine.utils.Timer;

import java.util.ArrayList;

public class TimersManager {
    private static TimersManager instance;
    private ArrayList<Timer> timers;

    private TimersManager() {
        this.timers = new ArrayList<Timer>();
    }

    public static TimersManager getInstance() {
        if (instance == null) instance = new TimersManager();
        return instance;
    }

    public void addTimer(Timer timer) {
        this.timers.add(timer);
    }

    public void removeTimer(Timer timer) {
        this.timers.remove(timer);
    }

    public void clearTimers() {
        timers.clear();
    }

    public void clearDisposable() {
        for (int i = this.timers.size() - 1; i >= 0; i--) {
            Timer t = this.timers.get(i);
            if (t.isDisposable()) {
                removeTimer(t);
            }
        }
    }

    public void act(float delta) {
        for (int i = 0; i < this.timers.size(); i++) {
            Timer t = this.timers.get(i);
            if (t.isScalable()) {
                t.act(delta * BaseGameManager.getInstance().getGameSpeed());
            } else {
                t.act(delta);
            }
        }
    }
}
