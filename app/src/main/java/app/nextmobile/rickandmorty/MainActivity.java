package app.nextmobile.rickandmorty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import app.nextmobile.rickandmorty.utils.Util;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            Util.navigateToEpisodesList(this);
        }
    }


}
