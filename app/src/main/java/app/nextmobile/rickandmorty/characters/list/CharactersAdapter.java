package app.nextmobile.rickandmorty.characters.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import app.nextmobile.rickandmorty.R;
import app.nextmobile.rickandmorty.models.Character;

public class CharactersAdapter extends RecyclerView.Adapter<CharactersViewHolder> {

    private List<Character> data = new ArrayList<>();
    private View.OnClickListener onItemClickListener;

    public CharactersAdapter(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    protected void setData(List<Character> list) {
        this.data = list;
        notifyDataSetChanged();
    }

    @Nullable
    public Character getItemAtPosition(int position) {
        if (data.isEmpty() || position >= data.size() || position < 0) return null;
        return data.get(position);
    }

    @NonNull
    @Override
    public CharactersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character_over_view, parent, false);
        return new CharactersViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CharactersViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
