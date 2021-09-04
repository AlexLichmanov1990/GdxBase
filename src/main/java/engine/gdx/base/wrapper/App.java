package engine.gdx.base.wrapper;

public class App {
    private static App instance;
    private AppWrapper wrapper;

    private App() {
    }

    public static App getInstance() {
        if (instance == null) instance = new App();
        return instance;
    }

    public AppWrapper getWrapper() {
        return wrapper;
    }

    public void setWrapper(AppWrapper wrapper) {
        this.wrapper = wrapper;
    }
}
