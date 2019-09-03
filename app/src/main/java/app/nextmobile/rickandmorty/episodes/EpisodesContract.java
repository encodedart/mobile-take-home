package app.nextmobile.rickandmorty.episodes;

import androidx.lifecycle.LiveData;

import java.util.List;

import app.nextmobile.rickandmorty.models.Episode;

public class EpisodesContract {

    interface Controller {
        void onEpisodeSelected(Episode episode);
        LiveData<Boolean> getOnError();
    }
}
