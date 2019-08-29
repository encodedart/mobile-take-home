package app.nextmobile.rickandmorty.characters;

import androidx.lifecycle.LiveData;

import java.util.List;

import app.nextmobile.rickandmorty.models.Character;

public class CharactersListContract {

    interface Controller {
        LiveData<List<Character>> getCharacters(List<String> links);
    }
}
