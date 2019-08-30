package app.nextmobile.rickandmorty.characters;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import app.nextmobile.rickandmorty.R;
import app.nextmobile.rickandmorty.models.Character;
import app.nextmobile.rickandmorty.utils.ImageLoader;

public class CharactersViewHolder extends RecyclerView.ViewHolder {

    private ImageView image;
    private TextView name;
    private TextView species;
    private TextView gender;
    private TextView status;

    public CharactersViewHolder(@NonNull View itemView, View.OnClickListener onItemClickListener) {
        super(itemView);

        image = itemView.findViewById(R.id.char_image);
        name = itemView.findViewById(R.id.char_name);
        status = itemView.findViewById(R.id.char_status);
        species = itemView.findViewById(R.id.char_species);
        View clickView = itemView.findViewById(R.id.char_select);
        clickView.setTag(this);
        clickView.setOnClickListener(onItemClickListener);
    }

    public void bind(Character item) {
        if (item.getStatus() == Character.CharacterStatus.DEAD) {
            status.setText("Dead");
            status.setTextColor(Color.RED);
        } else {
            status.setText("Alive");
            status.setTextColor(Color.WHITE);
        }
        name.setText(item.getName());
        species.setText(item.getSpecies() + ", " + item.getGender());
        if (!TextUtils.isEmpty(item.getImage())) {
            ImageLoader.getInstance().loadImageUrl(item.getImage(), image);
        }
    }
}
