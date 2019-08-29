package app.nextmobile.rickandmorty.utils;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import app.nextmobile.rickandmorty.R;
import app.nextmobile.rickandmorty.characters.CharactersFragment;
import app.nextmobile.rickandmorty.episodes.EpisodesFragment;
import app.nextmobile.rickandmorty.models.Episode;

public class Util {

    public static void navigateToEpisodesList(Context context) {
        Log.e("MAIN","Nav to Episodes List");
        Fragment fragment = new EpisodesFragment();
        ((AppCompatActivity) context).getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content, fragment, "EPISODES LIST")
                .addToBackStack("EPISODES LIST")
                .commit();
    }

    public static void navigateToCharactersList(Context context, Episode episode) {
        Fragment fragment = CharactersFragment.getFragment(episode);
        ((AppCompatActivity) context).getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content, fragment, "CHARS LIST")
                .addToBackStack("CHARS LIST")
                .commit();
    }

    @Nullable
    public static Date dateFromString(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.CANADA);
        try {
            return format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
