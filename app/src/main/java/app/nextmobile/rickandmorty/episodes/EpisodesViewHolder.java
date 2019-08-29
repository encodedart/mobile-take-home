package app.nextmobile.rickandmorty.episodes;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import app.nextmobile.rickandmorty.R;
import app.nextmobile.rickandmorty.models.Episode;

public class EpisodesViewHolder extends RecyclerView.ViewHolder {

    private TextView title;

    public EpisodesViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.episodeTitle);
    }

    public void bind(Episode item) {
        title.setText(item.getName());
    }
}
