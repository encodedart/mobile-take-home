package app.nextmobile.rickandmorty.episodes;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import app.nextmobile.rickandmorty.models.Episode;


public class EpisodesViewModel extends ViewModel implements EpisodesContract.Controller {

    private MutableLiveData<Boolean> onError = new MutableLiveData<>();

    public LiveData<PagedList<Episode>> getEpisode() {
        onError.setValue(false);
        DataSource.Factory<String, Episode> episodeDataSource = new DataSource.Factory<String, Episode>() {
            MutableLiveData<EpisodeDataSource> sourceLiveData = new MutableLiveData();

            @NonNull
            @Override
            public DataSource<String, Episode> create() {
                EpisodeDataSource source = new EpisodeDataSource(() -> {
                    onError.setValue(true);
                });
                sourceLiveData.postValue(source);
                return source;
            }
        };
        return new LivePagedListBuilder(episodeDataSource, 15).build();
    }

    public LiveData<Boolean> getOnError() {
        return onError;
    }

    @Override
    public void onEpisodeSelected(Episode episode) {

    }

}
