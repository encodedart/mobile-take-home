package app.nextmobile.rickandmorty.episodes;

import java.util.List;

import app.nextmobile.rickandmorty.models.Episode;

public class EpisodesContract {

    interface View {
        void updateList(List<Episode> episodes);
    }

    interface Controller {
        void onEpisodeSelected(Episode episode);
    }
}
