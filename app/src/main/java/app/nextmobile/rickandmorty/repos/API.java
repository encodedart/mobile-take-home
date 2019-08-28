package app.nextmobile.rickandmorty.repos;

import android.util.Log;

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

    
}
