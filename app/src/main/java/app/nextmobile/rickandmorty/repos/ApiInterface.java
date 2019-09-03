package app.nextmobile.rickandmorty.repos;

import java.util.List;

import app.nextmobile.rickandmorty.models.Character;
import app.nextmobile.rickandmorty.models.Episodes;

public interface ApiInterface {
    void getAllEpisode(String endpoint, final RequestInterface<Episodes> delegate);
    void getCharacters(String ids, final RequestInterface<List<Character>> delegate);
//    void getCharacters(List<String> links, final RequestInterface<List<Character>> delegate);
}
