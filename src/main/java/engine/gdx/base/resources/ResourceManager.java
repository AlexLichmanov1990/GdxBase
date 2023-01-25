package engine.gdx.base.resources;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import engine.gdx.base.screens.BaseScreen;

import java.util.ArrayList;

public class ResourceManager extends AssetManager {
    private static ResourceManager instance;
    private boolean disposed = false;

    private ResourceManager() {
        super();
    }

    public static ResourceManager getInstance() {
        if (instance == null || instance.disposed) {
            instance = new ResourceManager();
            instance.disposed = false;
        }
        return instance;
    }

    @Override
    public synchronized void dispose() {
        super.dispose();
        this.disposed = true;
    }

    public void loadSync(BaseScreen screen) {
        if (screen == null) return;
        ArrayList<Resource<?>> resources = screen.getResources();
        for (int i = 0; i < resources.size(); i++) {
            String name = resources.get(i).getName();
            Class type = resources.get(i).getType();
            AssetLoaderParameters params = resources.get(i).getParams();

            load(name, type, params);
            finishLoadingAsset(name);
        }
    }

    public synchronized void loadSync(AssetDescriptor desc) {
        loadSync(desc.fileName, desc.type, desc.params);
    }

    public synchronized <T> void loadSync(String fileName, Class<T> type) {
        loadSync(fileName, type, null);
    }

    public synchronized <T> void loadSync(String fileName, Class<T> type, AssetLoaderParameters<T> parameter) {
        super.load(fileName, type, parameter);
        finishLoading();
    }

    @Override
    public synchronized void load(AssetDescriptor desc) {
        super.load(desc);
    }

    public void load(BaseScreen screen) {
        if (screen == null) return;
        ArrayList<Resource<?>> resources = screen.getResources();
        for (int i = 0; i < resources.size(); i++) {
            String name = resources.get(i).getName();
            Class type = resources.get(i).getType();
            AssetLoaderParameters params = resources.get(i).getParams();

            load(name, type, params);
        }
    }

    public void unload(BaseScreen screen) {
        if (screen == null) return;
        ArrayList<Resource<?>> resources = screen.getResources();
        for (int i = 0; i < resources.size(); i++) {
            String name = resources.get(i).getName();

            unload(name);
        }
        screen.dispose();
    }
}
