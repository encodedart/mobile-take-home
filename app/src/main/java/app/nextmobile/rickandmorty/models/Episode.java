package app.nextmobile.rickandmorty.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.nextmobile.rickandmorty.utils.Util;

public class Episode implements Serializable {
    private int id = 0;
    private String name = "";
    private String airDate = "";
    private String episode = "";
    private List<String> characters = new ArrayList<>();
    private String url = "";
    private Date created = new Date(); //": "2017-11-10T12:56:35.875Z;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public List<String> getCharacters() {
        return characters;
    }

    public void setCharacters(List<String> characters) {
        this.characters = characters;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setCreated(String dateString) {
        this.created = Util.dateFromString(dateString);
    }

    public static Episode fromJson(JSONObject object) {
        Episode episode = new Episode();
        try {
            episode.setId(object.getInt("id"));
            episode.setName(object.getString("name"));
            episode.setAirDate(object.getString("air_date"));
            episode.setEpisode(object.getString("episode"));
            episode.setUrl(object.getString("url"));
            JSONArray chars = object.getJSONArray("characters");
            List<String> charsList = new ArrayList<>();
            for (int i = 0; i < chars.length(); i++) {
                charsList.add(chars.getString(i));
            }
            episode.setCharacters(charsList);
            episode.setCreated(object.getString("created"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return episode;
    }
}
