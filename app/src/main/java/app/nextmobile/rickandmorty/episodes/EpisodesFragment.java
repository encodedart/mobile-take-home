package app.nextmobile.rickandmorty.episodes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import app.nextmobile.rickandmorty.R;
import app.nextmobile.rickandmorty.models.Episode;
import app.nextmobile.rickandmorty.utils.BaseFragment;
import app.nextmobile.rickandmorty.utils.Util;

public class EpisodesFragment extends BaseFragment {

    private EpisodesAdapter adapter;
    private EpisodesViewModel controller;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_episodes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hideBackButton();
        setTitle(getString(R.string.app_name));

        RecyclerView recyclerView = view.findViewById(R.id.episodes_list);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        adapter  = new EpisodesAdapter(v -> {
            EpisodesViewHolder holder = (EpisodesViewHolder) v.getTag();
            int pos = holder.getAdapterPosition();
            Episode episode = adapter.getItemAtPosition(pos);
            Util.navigateToCharactersList(getContext(), episode);
        });

        recyclerView.setAdapter(adapter);

        controller = new ViewModelProvider(this).get(EpisodesViewModel.class);

        controller.getEpisode().observe(this, episodes -> adapter.submitList(episodes));

        controller.getOnError().observe(this, this::setErrorStatus);
    }

    @Override
    protected void tryAgain() {
        setErrorStatus(false);
        controller.getEpisode().observe(this, episodes -> {
            adapter.submitList(episodes, () -> {
                setProgressBar(false);
            });
        });
    }
}
