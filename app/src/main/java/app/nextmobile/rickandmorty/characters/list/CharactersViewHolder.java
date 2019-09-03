package app.nextmobile.rickandmorty.characters.list;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import app.nextmobile.rickandmorty.R;
import app.nextmobile.rickandmorty.models.Character;
import app.nextmobile.rickandmorty.utils.ServiceLocator;

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
        image.setTag(this);
        clickView.setOnClickListener(v -> onItemClickListener.onClick(image));
    }

    public void bind(Character item) {
        status.setText(item.getStatus().getType());
        if (item.getStatus() == Character.CharacterStatus.DEAD) {
            status.setTextColor(Color.RED);
        } else if (item.getStatus() == Character.CharacterStatus.ALIVE) {
            status.setTextColor(Color.WHITE);
        } else {
            status.setTextColor(Color.LTGRAY);
        }
        name.setText(item.getName());
        ViewCompat.setTransitionName(image, item.getName());
        species.setText(item.getSpecies() + ", " + item.getGender());
        if (!TextUtils.isEmpty(item.getImage())) {
            ServiceLocator.getInstance().getImageLoader().loadImageUrl(item.getImage(), image);
        }
    }
}
