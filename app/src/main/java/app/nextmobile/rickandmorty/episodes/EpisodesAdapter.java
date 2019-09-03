package app.nextmobile.rickandmorty.episodes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import app.nextmobile.rickandmorty.R;
import app.nextmobile.rickandmorty.models.Episode;

public class EpisodesAdapter extends PagedListAdapter<Episode, EpisodesViewHolder> {

    private View.OnClickListener onItemClickListener;

    EpisodesAdapter(View.OnClickListener onItemClickListener) {
        super(DIFF_CALLBACK);
        this.onItemClickListener = onItemClickListener;
    }

    @Nullable
    Episode getItemAtPosition(int pos) {
        return getItem(pos);
    }

    @NonNull
    @Override
    public EpisodesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_episode_list, parent, false);
        return new EpisodesViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodesViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    private static final DiffUtil.ItemCallback<Episode> DIFF_CALLBACK = new DiffUtil.ItemCallback<Episode>() {
        @Override
        public boolean areItemsTheSame(@NonNull Episode oldItem, @NonNull Episode newItem) {
            return oldItem.getId() == oldItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Episode oldItem, @NonNull Episode newItem) {
            return oldItem.getId() == oldItem.getId();
        }
    };
}
