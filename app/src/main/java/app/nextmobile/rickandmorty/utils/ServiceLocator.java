package app.nextmobile.rickandmorty.utils;

import androidx.annotation.VisibleForTesting;

import app.nextmobile.rickandmorty.repos.API;
import app.nextmobile.rickandmorty.repos.ApiInterface;

/**
 * Service locator to provide Service nad Manager class
 * User swap method in Test to provide Mock Service
 */
public class ServiceLocator {

    private static ServiceLocator INSTANCE = null;
    private API api = new API();
    private ImageLoader imageLoader = new ImageLoader();
    private CharacterManager characterManager = new CharacterManager();

    private ServiceLocator() {

    }

    public static ServiceLocator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ServiceLocator();
        }
        return INSTANCE;
    }

    @VisibleForTesting
    public void sawp(ServiceLocator locator) {
        INSTANCE = locator;
    }

    public ApiInterface getAPI() {
        return api;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public CharacterManager getCharacterManager() {
        return characterManager;
    }

}
