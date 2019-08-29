package app.nextmobile.rickandmorty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import app.nextmobile.rickandmorty.episodes.EpisodesFragment;
import app.nextmobile.rickandmorty.repos.API;
import app.nextmobile.rickandmorty.repos.Endpoint;
import app.nextmobile.rickandmorty.repos.HttpRequestInterface;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigateToEpisodesList();
    }

    public void navigateToEpisodesList() {
        Fragment fragment = new EpisodesFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content, fragment, "EPISODES LIST")
                .addToBackStack("EPISODES LIST")
                .commit();
    }
}
