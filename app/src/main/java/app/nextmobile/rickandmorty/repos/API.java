package app.nextmobile.rickandmorty.repos;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.nextmobile.rickandmorty.models.Character;
import app.nextmobile.rickandmorty.models.Episodes;

public class API {

    private String TAG = "API";

    public void getAllEpisode(String endpoint, final HttpRequestInterface<Episodes> delegate) {
        HttpGetTask task = new HttpGetTask(new HttpRequestInterface<String>() {
            @Override
            public void onResult(String result) {
                Episodes episodes = Episodes.fromJson(result);
                Log.e(TAG, episodes.toString());
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

    public void getCharacters(String ids, final HttpRequestInterface<List<Character>> delegate) {
        if (ids == null || ids.isEmpty()) {
            if (delegate != null) delegate.onError();
            return;
        }

        HttpGetTask task = new HttpGetTask(new HttpRequestInterface<String>() {

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

    public void getCharacters(List<String> links, final HttpRequestInterface<List<Character>> delegate) {
        String ids = getCharactersListString(links);
        getCharacters(ids, delegate);
    }

    private String getCharactersListString(List<String> list) {
        if (list == null || list.isEmpty()) return "";
        List<String> ids = new ArrayList<>();
        Pattern pattern = Pattern.compile("/\\d+$");
        for (String item : list) {
            Matcher matcher = pattern.matcher(item);
            if (matcher.find()) {
                ids.add(matcher.group(0).replace("/", ""));
            }
        }
        return TextUtils.join(",", ids);
    }
}
