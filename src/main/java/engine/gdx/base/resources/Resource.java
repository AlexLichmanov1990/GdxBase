package engine.gdx.base.resources;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Resource<T> {

    private String name;
    private Class<T> type;
    private AssetLoaderParameters params;

    public Resource(final String name, final Class<T> type) {
        this(name, type, null);
    }

    public Resource(final String name, final Class<T> type, AssetLoaderParameters params) {
        this.name = name;
        this.type = type;
        this.params = params;
    }

    public String getName() {
        return this.name;
    }

    public Class<T> getType() {
        return this.type;
    }

    public AssetLoaderParameters getParams() {
        return this.params;
    }

    public void dispose() {
        if (this.type == BitmapFont.class) {
            ResourceManager.getInstance().get(this.name, BitmapFont.class).dispose();
        }
        if (this.type == Pixmap.class) {
            ResourceManager.getInstance().get(this.name, Pixmap.class).dispose();
        }
        if (this.type == Texture.class) {
            ResourceManager.getInstance().get(this.name, Texture.class).dispose();
        }
        if (this.type == TextureAtlas.class) {
            ResourceManager.getInstance().get(this.name, TextureAtlas.class).dispose();
        }
    }
}
