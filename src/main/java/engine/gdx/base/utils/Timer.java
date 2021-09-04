package engine.gdx.base.utils;

public class Timer {
    private boolean started = false;
    private boolean looped = false;

    private float timeDefault;
    private float time;
    private float delay;
    private TimerListener listener;

    public Timer(float delay, float time, boolean loop) {
        this(delay, time, loop, null);
    }

    public Timer(float delay, float time, boolean loop, TimerListener listener) {
        this.delay = delay;
        this.timeDefault = time;
        this.looped = loop;
        setListener(listener);
        stop();
    }

    public Timer start() {
        if (isStarted()) return this;
        started = true;
        this.time = timeDefault;
        return this;
    }

    public Timer stop() {
        if (!isStarted()) return this;
        started = false;
        return this;
    }

    public boolean isStarted() {
        return this.started;
    }

    public void act(float delta) {
        if (!isStarted()) {
            return;
        }
        if (delay > 0) {
            delay -= delta;
            return;
        }
        time -= delta;
        if (time <= 0) {
            if (listener != null) {
                listener.onTimeOut();
            }
            if (looped) {
                time = timeDefault;
            }
        }
    }

    public void setListener(TimerListener listener) {
        this.listener = listener;
    }

    public interface TimerListener {
        void onTimeOut();
    }
}
