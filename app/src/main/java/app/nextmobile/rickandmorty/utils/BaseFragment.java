package app.nextmobile.rickandmorty.utils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import app.nextmobile.rickandmorty.MainActivity;

/**
 *
 * Base Fragment class
 * Provide some share utility methods
 */

public abstract class BaseFragment extends Fragment {

    protected void setProgressBar(boolean status) {
        if (status) {
            ((MainActivity) getActivity()).showProgressBar();
        } else {
            ((MainActivity) getActivity()).hideProgressBar();
        }
    }

    protected void setErrorStatus(boolean status) {
        if (status) {
            ((MainActivity)getActivity()).showError(() -> tryAgain());
        } else {
            ((MainActivity)getActivity()).hideError();
        }
    }

    protected void showBackButton() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    protected void hideBackButton() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(false);
    }

    protected void setTitle(String title) {
        getActivity().setTitle(title);
    }

    protected abstract void tryAgain();
}
