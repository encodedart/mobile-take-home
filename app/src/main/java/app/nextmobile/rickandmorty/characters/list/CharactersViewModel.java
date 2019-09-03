package app.nextmobile.rickandmorty.characters.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import app.nextmobile.rickandmorty.models.Character;
import app.nextmobile.rickandmorty.repos.ApiInterface;
import app.nextmobile.rickandmorty.repos.RequestInterface;
import app.nextmobile.rickandmorty.utils.ServiceLocator;

public class CharactersViewModel extends ViewModel implements CharactersListContract.Controller {

    private MutableLiveData<List<Character>> characters;
    private MutableLiveData<Boolean> onError = new MutableLiveData();
    private List<String> links;

    @Override
    public LiveData<List<Character>> getCharacters(List<String> links) {
        this.links = links;
        if (characters == null) {
            characters = new MutableLiveData<>();
            loadCharacters(links);
        }
        return characters;
    }

    @Override
    public void tryAgain() {
        loadCharacters(links);
    }

    @Override
    public LiveData<Boolean> getOnError() {
        return onError;
    }

    private void loadCharacters(List<String> links) {
        onError.setValue(false);
        ServiceLocator.getInstance().getCharacterManager().getCharacters(links, new RequestInterface<List<Character>>() {
            @Override
            public void onResult(List<Character> result) {
                if (result != null) {
                    characters.setValue(result);
                } else {
                    onError.setValue(true);
                }
            }

            @Override
            public void onError() {
                onError.setValue(true);
            }
        });
    }

}
