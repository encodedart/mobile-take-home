package app.nextmobile.rickandmorty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import app.nextmobile.rickandmorty.utils.Util;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private Group errorGroup;
    private TextView tryAgainBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            Util.navigateToEpisodesList(this);
        }

        progressBar = findViewById(R.id.wait_indicator);
        errorGroup = findViewById(R.id.error_group);
        tryAgainBtn = findViewById(R.id.try_again);
    }

    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    public void showError(Runnable tryAgain) {
        hideProgressBar();
        tryAgainBtn.setOnClickListener(v -> tryAgain.run());
        errorGroup.setVisibility(View.VISIBLE);
    }

    public void hideError() {
        tryAgainBtn.setOnClickListener(null);
        errorGroup.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        }
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getSupportFragmentManager().popBackStackImmediate();
        }
        return true;
    }
}
