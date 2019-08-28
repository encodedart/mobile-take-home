package app.nextmobile.rickandmorty.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Episodes implements Serializable {
    private int count = 0;
    private int pages = 0;
    private String next = "";
    private String prev = "";
    private List<Episode> episodes = new ArrayList<>();

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    static public Episodes fromJson(String data) {

        Episodes result = new Episodes();

        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONObject info = jsonObject.getJSONObject("info");
            result.setCount(info.getInt("count"));
            result.setPages(info.getInt("pages"));
            result.setNext(info.getString("next"));
            result.setPrev(info.getString("prev"));
            JSONArray list = jsonObject.getJSONArray("results");
            List<Episode> episodes = new ArrayList<>();
            for (int i = 0; i < list.length(); i++) {
                JSONObject item = list.getJSONObject(i);
                episodes.add(Episode.fromJson(item));
            }
            result.setEpisodes(episodes);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
