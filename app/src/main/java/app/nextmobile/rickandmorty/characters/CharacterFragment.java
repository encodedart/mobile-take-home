package app.nextmobile.rickandmorty.characters;

import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import app.nextmobile.rickandmorty.R;
import app.nextmobile.rickandmorty.models.Character;
import app.nextmobile.rickandmorty.utils.BaseFragment;
import app.nextmobile.rickandmorty.utils.ServiceLocator;

public class CharacterFragment extends BaseFragment {

    private static final String CHARACTER_ID = "CHARACTER ID";
    private CharacterViewModel controller;

    private ImageView charImage;
    private Button killButton;
    private TextView statusText;

    public static CharacterFragment getFragment(int charId) {
        CharacterFragment fragment = new CharacterFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(CHARACTER_ID, charId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postponeEnterTransition();
        setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_character, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showBackButton();
        Bundle bundle = getArguments();
        int charId = -1;
        if (bundle != null && bundle.containsKey(CHARACTER_ID)) {
            charId = bundle.getInt(CHARACTER_ID);
        }

        charImage = view.findViewById(R.id.char_image);
        killButton = view.findViewById(R.id.killBtn);
        statusText = view.findViewById(R.id.status);

        controller = new ViewModelProvider(this).get(CharacterViewModel.class);
        controller.getCharacter(charId).observe(this, this::updateView);
    }

    private void updateView(Character character) {
        setTitle(character.getName());
        charImage.setTransitionName(character.getName());
        ServiceLocator.getInstance().getImageLoader().loadImageUrl(character.getImage(), charImage);
        startPostponedEnterTransition();

        ((TextView)getView().findViewById(R.id.name)).setText(character.getName());
        statusText.setText(character.getStatus().getType());
        ((TextView)getView().findViewById(R.id.species)).setText(character.getSpecies());
        ((TextView)getView().findViewById(R.id.gender)).setText(character.getGender());
        ((TextView)getView().findViewById(R.id.origin)).setText(character.getOrigin().getName());
        ((TextView)getView().findViewById(R.id.location)).setText(character.getLocation().getName());
        if (character.getStatus() == Character.CharacterStatus.DEAD) {
            killButton.setVisibility(View.GONE);
        } else {
            killButton.setOnClickListener(v -> controller.setKillCharacter());
            statusText.setText(character.getStatus().getType());
        }
    }

    @Override
    protected void tryAgain() {

    }
}
