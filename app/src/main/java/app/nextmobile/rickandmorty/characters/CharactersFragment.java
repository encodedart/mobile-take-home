package app.nextmobile.rickandmorty.characters;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import app.nextmobile.rickandmorty.R;
import app.nextmobile.rickandmorty.episodes.EpisodesController;
import app.nextmobile.rickandmorty.models.Character;
import app.nextmobile.rickandmorty.models.Episode;

public class CharactersFragment extends Fragment {

    private static final String EPISODE_DATA = "EPISODE DATA";

    private Episode episode;
    private CharactersViewModel controller;

    public static CharactersFragment getFragment(Episode episode) {
        CharactersFragment fragment = new CharactersFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EPISODE_DATA, episode);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_characters_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(EPISODE_DATA)) {
            episode = (Episode) bundle.getSerializable(EPISODE_DATA);
            Log.e("CHARS", episode.getName());
        }

        controller = new ViewModelProvider(this).get(CharactersViewModel.class);
        controller.getCharacters(episode.getCharacters()).observe(this, characters -> {
            Log.e("CHAR FRAG", characters.get(0).getName());
        });
    }
}
