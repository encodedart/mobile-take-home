package app.nextmobile.rickandmorty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import app.nextmobile.rickandmorty.repos.API;
import app.nextmobile.rickandmorty.repos.HttpRequestInterface;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        API api = new API();
        api.getAllEpisode();
    }
}
