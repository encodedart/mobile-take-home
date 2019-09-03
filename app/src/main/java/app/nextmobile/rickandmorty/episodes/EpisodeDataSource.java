package app.nextmobile.rickandmorty.episodes;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import java.util.ArrayList;

import app.nextmobile.rickandmorty.models.Episode;
import app.nextmobile.rickandmorty.models.Episodes;
import app.nextmobile.rickandmorty.repos.ApiInterface;
import app.nextmobile.rickandmorty.repos.Endpoint;
import app.nextmobile.rickandmorty.repos.RequestInterface;
import app.nextmobile.rickandmorty.utils.ServiceLocator;

public class EpisodeDataSource extends PageKeyedDataSource<String, Episode> {

    private Runnable onError;

    public EpisodeDataSource(@NonNull Runnable onError) {
        this.onError = onError;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<String> params, @NonNull LoadInitialCallback<String, Episode> callback) {
        ServiceLocator.getInstance().getAPI().getAllEpisode(Endpoint.episodes, new RequestInterface<Episodes>() {
            @Override
            public void onResult(Episodes result) {
                if (result != null && result.getEpisodes() != null) {
                    callback.onResult(result.getEpisodes(), result.getPrev(), result.getNext());
                } else {
                    onError.run();
                }
            }

            @Override
            public void onError() {
                onError.run();
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Episode> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Episode> callback) {
        if (TextUtils.isEmpty(params.key)) {
            callback.onResult(new ArrayList<>(), null);
            return;
        }
        ServiceLocator.getInstance().getAPI().getAllEpisode(params.key, new RequestInterface<Episodes>() {
            @Override
            public void onResult(Episodes result) {
                if (result != null && result.getEpisodes() != null) {
                    callback.onResult(result.getEpisodes(), result.getNext());
                } else {
                    callback.onResult(new ArrayList<>(), null);
                }
            }

            @Override
            public void onError() {
                onError.run();
            }
        });
    }
}