package app.nextmobile.rickandmorty.episodes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.nextmobile.rickandmorty.R;
import app.nextmobile.rickandmorty.models.Episode;

public class EpisodesFragment extends Fragment implements EpisodesContract.View {

    private EpisodesController controller;
    private EpisodesAdapter adapter = new EpisodesAdapter();

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
