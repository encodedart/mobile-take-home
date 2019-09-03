package app.nextmobile.rickandmorty.characters;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import app.nextmobile.rickandmorty.models.Character;
import app.nextmobile.rickandmorty.utils.ServiceLocator;

public class CharacterViewModel extends ViewModel implements CharacterContract {

    private MutableLiveData<Character> character;
    private int characterId = -1;

    @Override
    public LiveData<Character> getCharacter(int id) {
        characterId = id;
        if (character == null) {
            character = new MutableLiveData<>();
        }
        character.setValue(ServiceLocator.getInstance().getCharacterManager().getCharacterById(id));
        return character;
    }

    @Override
    public void setKillCharacter() {
        ServiceLocator.getInstance().getCharacterManager().killCharacter(characterId);
        character.setValue(ServiceLocator.getInstance().getCharacterManager().getCharacterById(characterId));
    }
}
