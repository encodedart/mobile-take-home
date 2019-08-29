package app.nextmobile.rickandmorty.episodes;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import app.nextmobile.rickandmorty.R;
import app.nextmobile.rickandmorty.models.Episode;

public class EpisodesViewHolder extends RecyclerView.ViewHolder {

    private TextView title;
    private TextView episodeName;
    private TextView charCount;
    private TextView airedDate;

    private View.OnClickListener onItemClickListener;

    public EpisodesViewHolder(@NonNull View itemView, View.OnClickListener onItemClickListener) {
        super(itemView);
        itemView.setTag(this);
        this.onItemClickListener = onItemClickListener;
        title = itemView.findViewById(R.id.episodeTitle);
        episodeName = itemView.findViewById(R.id.episodeName);
        charCount = itemView.findViewById(R.id.characterCount);
        airedDate = itemView.findViewById(R.id.airedDate);
        itemView.setOnClickListener(onItemClickListener);
    }

    public void bind(Episode item) {
        title.setText(item.getName());
        episodeName.setText(item.getEpisode());
        airedDate.setText("Air date: " + item.getAirDate());
        charCount.setText("Characters: " + item.getCharacters().size());
    }

}
