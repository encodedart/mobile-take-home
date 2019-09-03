package app.nextmobile.rickandmorty.repos;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import app.nextmobile.rickandmorty.models.Character;
import app.nextmobile.rickandmorty.models.Episodes;

/**
 * Network call API
 */

public class API implements ApiInterface {

    @Override
    public void getAllEpisode(String endpoint, final RequestInterface<Episodes> delegate) {
        HttpGetTask task = new HttpGetTask(new RequestInterface<String>() {
            @Override
            public void onResult(String result) {
                Episodes episodes = Episodes.fromJson(result);
                if (delegate != null) {
                    delegate.onResult(episodes);
                }
            }

            @Override
            public void onError() {
                if (delegate != null) {
                    delegate.onError();
                }
            }
        });
        task.execute(endpoint);
    }

    @Override
    public void getCharacters(String ids, final RequestInterface<List<Character>> delegate) {
        if (ids == null || ids.isEmpty()) {
            if (delegate != null) delegate.onError();
            return;
        }

        HttpGetTask task = new HttpGetTask(new RequestInterface<String>() {

            @Override
            public void onResult(String result) {
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    List<Character> characters = new ArrayList<>();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        characters.add(Character.fromJson(jsonArray.getJSONObject(i)));
                    }
                    if (delegate != null) delegate.onResult(characters);
                } catch (JSONException e) {
                    e.printStackTrace();
                    if (delegate != null) {
                        delegate.onError();
                    }
                }
            }

            @Override
            public void onError() {
                if (delegate != null) {
                    delegate.onError();
                }
            }
        });
        task.execute(Endpoint.character + ids);
    }

}
