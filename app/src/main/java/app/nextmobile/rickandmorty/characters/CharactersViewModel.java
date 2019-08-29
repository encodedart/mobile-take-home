package app.nextmobile.rickandmorty.characters;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import app.nextmobile.rickandmorty.models.Character;
import app.nextmobile.rickandmorty.repos.API;
import app.nextmobile.rickandmorty.repos.HttpRequestInterface;

public class CharactersViewModel extends ViewModel implements CharactersListContract.Controller {

    private MutableLiveData<List<Character>> characters;
    private API api = new API();

    @Override
    public LiveData<List<Character>> getCharacters(List<String> links) {
        if (characters == null) {
            characters = new MutableLiveData<>();
            loadCharacters(links);
        }
        return characters;
    }

    private void loadCharacters(List<String> links) {
        api.getCharacters(links, new HttpRequestInterface<List<Character>>() {
            @Override
            public void onResult(List<Character> result) {
                if (result != null) {
                    characters.setValue(result);
                }
            }

            @Override
            public void onError() {

            }
        });
    }

}
