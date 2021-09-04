package engine.managers;

import java.util.ArrayList;

public class PauseManager {
    private static PauseManager instance;

    private ArrayList<PauseListener> observers;
    private int pauses;

    private PauseManager() {
        pauses = 0;
        observers = new ArrayList<>();
    }

    public static PauseManager getInstance() {
        if (instance == null) instance = new PauseManager();
        return instance;
    }

    public void pause() {
        pauses++;
        if (pauses < 0) pauses = 0;
        if (isPaused()) {
            notifyOnPause();
        }
    }

    public void unpause() {
        pauses--;
        if (pauses < 0) pauses = 0;
        if (!isPaused()) {
            notifyOnUnpause();
        }
    }

    public boolean isPaused() {
        return pauses > 0;
    }

    private void notifyOnPause() {
        for (int i = 0; i < this.observers.size(); i++) {
            this.observers.get(i).onPause();
        }
    }

    private void notifyOnUnpause() {
        for (int i = 0; i < this.observers.size(); i++) {
            this.observers.get(i).onUnpause();
        }
    }

    public void addPauseListener(PauseListener listener) {
        this.observers.add(listener);
    }

    public void removePauseListener(PauseListener listener) {
        this.observers.remove(listener);
    }

    public interface PauseListener {
        void onPause();

        void onUnpause();
    }
}
