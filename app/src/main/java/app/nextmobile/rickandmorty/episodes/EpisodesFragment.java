package app.nextmobile.rickandmorty.episodes;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.nextmobile.rickandmorty.R;
import app.nextmobile.rickandmorty.models.Episode;
import app.nextmobile.rickandmorty.utils.Util;

public class EpisodesFragment extends Fragment implements EpisodesContract.View {

    private EpisodesController controller;
    private EpisodesAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_episodes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.episodes_list);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        adapter  = new EpisodesAdapter(v -> {
            EpisodesViewHolder holder = (EpisodesViewHolder) v.getTag();
            int pos = holder.getAdapterPosition();
            Episode episode = adapter.getItemAtPosition(pos);
            Log.e("ADSDA", "Episode selected Name: " + episode.getName());
            Util.navigateToCharactersList(getContext(), episode);
        });

        recyclerView.setAdapter(adapter);

        controller = new ViewModelProvider(this).get(EpisodesController.class);
        controller.getEpisode().observe(this, episodes -> {
            adapter.setData(episodes);
        });
    }

    @Override
    public void updateList(List<Episode> episodes) {

    }
}
