package app.nextmobile.rickandmorty.episodes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import app.nextmobile.rickandmorty.models.Episode;
import app.nextmobile.rickandmorty.models.Episodes;
import app.nextmobile.rickandmorty.repos.API;
import app.nextmobile.rickandmorty.repos.Endpoint;
import app.nextmobile.rickandmorty.repos.HttpRequestInterface;

public class EpisodesController extends ViewModel implements EpisodesContract.Controller {

    private MutableLiveData<List<Episode>> episodes;
    private API api = new API();


    public LiveData<List<Episode>> getEpisode() {
        if (episodes == null) {
            episodes = new MutableLiveData<>();
            loadEpisodes();
        }
        return episodes;
    }

    private void loadEpisodes() {
        api.getAllEpisode(Endpoint.episodes, new HttpRequestInterface<Episodes>() {
            @Override
            public void onResult(Episodes result) {
                if (result != null && result.getEpisodes() != null) {
                    episodes.setValue(result.getEpisodes());
                }
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void onEpisodeSelected(Episode episode) {

    }
}
