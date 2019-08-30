package app.nextmobile.rickandmorty.models;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.nextmobile.rickandmorty.utils.Util;


public class Character implements Serializable {

    public enum CharacterStatus {
        DEAD, ALIVE
    }

    private int id;
    private String name;
    private CharacterStatus status;
    private String species;
    private String type;
    private String gender;
    private Detail origin;
    private Detail location;
    private String image;
    private List<String> episode;
    private String url;
    private Date created;

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

    public CharacterStatus getStatus() {
        return status;
    }

    public void setStatus(CharacterStatus status) {
        this.status = status;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Detail getOrigin() {
        return origin;
    }

    public void setOrigin(Detail origin) {
        this.origin = origin;
    }

    public void setOrigin(JSONObject object) {
        this.origin = Detail.fromJson(object);
    }

    public Detail getLocation() {
        return location;
    }

    public void setLocation(Detail location) {
        this.location = location;
    }

    public void setLocation(JSONObject object) {
        this.location = Detail.fromJson(object);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getEpisode() {
        return episode;
    }

    public void setEpisode(List<String> episode) {
        this.episode = episode;
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

    public void setStatus(String statusString) {
        if (TextUtils.isEmpty(statusString)) return;
        if (statusString.equalsIgnoreCase("alive")) {
            status = CharacterStatus.ALIVE;
        } else {
            status = CharacterStatus.DEAD;
        }
    }

    public static Character fromJson(String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            return Character.fromJson(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
            return new Character();
        }
    }

    public static Character fromJson(JSONObject jsonObject) {
        Character character = new Character();
        try {
            character.setId(jsonObject.getInt("id"));
            character.setName(jsonObject.getString("name"));
            character.setStatus(jsonObject.getString("status"));
            character.setSpecies(jsonObject.getString("species"));
            character.setType(jsonObject.getString("type"));
            character.setGender(jsonObject.getString("gender"));
            character.setOrigin(jsonObject.getJSONObject("origin"));
            character.setLocation(jsonObject.getJSONObject("location"));
            character.setImage(jsonObject.getString("image"));

            JSONArray jsonArray = jsonObject.getJSONArray("episode");
            List<String> episodes = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                episodes.add(jsonArray.getString(i));
            }
            character.setEpisode(episodes);

            character.setUrl(jsonObject.getString("url"));
            character.setCreated(jsonObject.getString("created"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return character;
    }
}

class Detail {
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
