package app.nextmobile.rickandmorty.characters;

import androidx.lifecycle.LiveData;

import app.nextmobile.rickandmorty.models.Character;

public interface CharacterContract {
    LiveData<Character> getCharacter(int id);
    void setKillCharacter();
}
