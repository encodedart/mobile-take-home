package app.nextmobile.rickandmorty.characters.list;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import app.nextmobile.rickandmorty.R;
import app.nextmobile.rickandmorty.models.Character;
import app.nextmobile.rickandmorty.models.Episode;
import app.nextmobile.rickandmorty.utils.BaseFragment;
import app.nextmobile.rickandmorty.utils.Util;

public class CharactersFragment extends BaseFragment {

    private static final String EPISODE_DATA = "EPISODE DATA";

    private Episode episode;
    private CharactersAdapter adapter;
    private CharactersViewModel controller;

    public static CharactersFragment getFragment(Episode episode) {
        CharactersFragment fragment = new CharactersFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(EPISODE_DATA, episode);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_episodes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showBackButton();
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(EPISODE_DATA)) {
            episode = (Episode) bundle.getSerializable(EPISODE_DATA);
            setTitle(episode.getName());
        }

        RecyclerView recyclerView = view.findViewById(R.id.episodes_list);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridItemDecoration(10, 2));

        adapter  = new CharactersAdapter(v -> {
            CharactersViewHolder holder = (CharactersViewHolder) v.getTag();
            int pos = holder.getAdapterPosition();
            Character character = adapter.getItemAtPosition(pos);
            Util.navigateToCharacter(getContext(), character, v);
        });

        recyclerView.setAdapter(adapter);

        setProgressBar(true);
        controller = new ViewModelProvider(this).get(CharactersViewModel.class);
        controller.getCharacters(episode.getCharacters()).observe(this, characters -> {
            adapter.setData(characters);
            setProgressBar(false);
        });

        controller.getOnError().observe(this, this::setErrorStatus);
    }

    @Override
    protected void tryAgain() {
        setErrorStatus(false);
        setProgressBar(true);
        controller.tryAgain();
    }

    public class GridItemDecoration extends RecyclerView.ItemDecoration {

        private int mSizeGridSpacingPx;
        private int mGridSize;

        private boolean mNeedLeftSpacing = false;

        public GridItemDecoration(int gridSpacingPx, int gridSize) {
            mSizeGridSpacingPx = gridSpacingPx;
            mGridSize = gridSize;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int frameWidth = (int) ((parent.getWidth() - (float) mSizeGridSpacingPx * (mGridSize - 1)) / mGridSize);
            int padding = parent.getWidth() / mGridSize - frameWidth;
            int itemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewAdapterPosition();
            if (itemPosition < mGridSize) {
                outRect.top = 0;
            } else {
                outRect.top = mSizeGridSpacingPx;
            }
            if (itemPosition % mGridSize == 0) {
                outRect.left = 0;
                outRect.right = padding;
                mNeedLeftSpacing = true;
            } else if ((itemPosition + 1) % mGridSize == 0) {
                mNeedLeftSpacing = false;
                outRect.right = 0;
                outRect.left = padding;
            } else if (mNeedLeftSpacing) {
                mNeedLeftSpacing = false;
                outRect.left = mSizeGridSpacingPx - padding;
                if ((itemPosition + 2) % mGridSize == 0) {
                    outRect.right = mSizeGridSpacingPx - padding;
                } else {
                    outRect.right = mSizeGridSpacingPx / 2;
                }
            } else if ((itemPosition + 2) % mGridSize == 0) {
                mNeedLeftSpacing = false;
                outRect.left = mSizeGridSpacingPx / 2;
                outRect.right = mSizeGridSpacingPx - padding;
            } else {
                mNeedLeftSpacing = false;
                outRect.left = mSizeGridSpacingPx / 2;
                outRect.right = mSizeGridSpacingPx / 2;
            }
            outRect.bottom = 0;
        }
    }

}
