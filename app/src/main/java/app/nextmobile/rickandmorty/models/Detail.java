package app.nextmobile.rickandmorty.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Detail {
    private String name = "";
    private String url = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static Detail fromJson(JSONObject object) {
        Detail detail = new Detail();
        if (object != null) {
            try {
                detail.setName(object.getString("name"));
                detail.setUrl(object.getString("url"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return detail;
    }
}