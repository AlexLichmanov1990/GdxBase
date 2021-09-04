package engine.utils;

import engine.managers.TimersManager;

public class Timer {
    private boolean scalable = true;
    private boolean started = false;
    private boolean looped = false;
    private boolean disposable = true;

    private float timeDefault;
    private float time;
    private float delay;
    private TimerListener listener;

    public Timer(float delay, float time, boolean loop) {
        this(delay, time, loop, true);
    }

    public Timer(float delay, float time, boolean loop, boolean scalable) {
        this(delay, time, loop, scalable, null);
    }

    public Timer(float delay, float time, boolean loop, boolean scalable, TimerListener listener) {
        this(delay, time, loop, scalable, listener, true);
    }

    public Timer(float delay, float time, boolean loop, boolean scalable, TimerListener listener, boolean disposable) {
        this.disposable = disposable;
        this.delay = delay;
        this.timeDefault = time;
        this.looped = loop;
        this.scalable = scalable;
        setListener(listener);
        TimersManager.getInstance().addTimer(this);
        stop();
    }

    public boolean isScalable() {
        return this.scalable;
    }

    public Timer start() {
        if (isStarted()) return this;
        started = true;
        this.time = delay;
        return this;
    }

    public Timer stop() {
        if (!isStarted()) return this;
        started = false;
        return this;
    }

    public float getTime() {
        return this.time;
    }

    public Timer setTime(float time) {
        this.timeDefault = time;
        this.time = timeDefault;
        return this;
    }

    public Timer setTimeDefault(float time) {
        this.timeDefault = time;
        return this;
    }

    public Timer setLooped(boolean looped) {
        this.looped = looped;
        return this;
    }

    public boolean isDisposable() {
        return this.disposable;
    }

    public Timer setDisposable(boolean disposable) {
        this.disposable = disposable;
        return this;
    }

    public boolean isStarted() {
        return this.started;
    }

    public void act(float delta) {
        if (!started) return;
        this.time -= delta;
        if (this.time <= 0) {
            this.time = timeDefault;
            if (this.listener != null) {
                this.listener.onTimeOut();
            }
            if (!looped) {
                TimersManager.getInstance().removeTimer(this);
            }
        }
    }

    public void dispose() {
        TimersManager.getInstance().removeTimer(this);
    }

    public void setListener(TimerListener listener) {
        this.listener = listener;
    }

    public interface TimerListener {
        void onTimeOut();
    }
}
