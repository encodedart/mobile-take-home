package app.nextmobile.rickandmorty.episodes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.nextmobile.rickandmorty.R;
import app.nextmobile.rickandmorty.models.Episode;

public class EpisodesAdapter extends RecyclerView.Adapter<EpisodesViewHolder> {

    private List<Episode> episodes = new ArrayList<>();

    public void setData(List<Episode> episodes) {
        this.episodes = episodes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EpisodesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_episode_list, parent, false);
        return new EpisodesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodesViewHolder holder, int position) {
        holder.bind(episodes.get(position));
    }

    @Override
    public int getItemCount() {
        return episodes.size();
    }

}