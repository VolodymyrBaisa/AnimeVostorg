package usa.bios.animevostorg.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import io.reactivex.Observable;
import usa.bios.animevostorg.R;
import usa.bios.animevostorg.helpers.NullHelper;
import usa.bios.animevostorg.helpers.TypefaceHelper;
import usa.bios.animevostorg.presenters.ISplashScreenPresenter;
import usa.bios.animevostorg.presenters.ISplashScreenView;
import usa.bios.animevostorg.presenters.impl.SplashScreenPresenter;

/**
 * Created by Bios on 8/2/2017.
 */

public class SplashScreenActivity extends AppCompatActivity implements ISplashScreenView {
    private ISplashScreenPresenter splashScreenPresenter;
    private TextView splashScreenPart1;
    private TextView splashScreenPart2;
    private TextView splashScreenPart3;
    private TextView screenVersion;
    private ProgressBar loading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_activity);
        init();
    }

    private void init() {
        splashScreenPresenter = new SplashScreenPresenter();

        splashScreenPart1 = (TextView) findViewById(R.id.splashScreenPart1);
        splashScreenPart2 = (TextView) findViewById(R.id.splashScreenPart2);
        splashScreenPart3 = (TextView) findViewById(R.id.splashScreenPart3);
        screenVersion = (TextView) findViewById(R.id.splashScreenVersion);

        loading = (ProgressBar) findViewById(R.id.splashScreenLoading);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setTypeface();
    }

    private void setTypeface() {
        if (!NullHelper.isNull(splashScreenPart1) && !NullHelper.isNull(splashScreenPart2)
                && !NullHelper.isNull(splashScreenPart3) && !NullHelper.isNull(screenVersion)) {

            String font = "fonts/roomfer.ttf";
            splashScreenPart1.setTypeface(TypefaceHelper.font(this, font));
            splashScreenPart2.setTypeface(TypefaceHelper.font(this, font));
            splashScreenPart3.setTypeface(TypefaceHelper.font(this, font));
            screenVersion.setTypeface(TypefaceHelper.font(this, font));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        splashScreenPresenter.subscribe(this);
        splashScreenPresenter.loadVersion();
    }

    @Override
    protected void onStop() {
        super.onStop();
        splashScreenPresenter.unSubscribe();
    }

    @Override
    public void loadVersion(String version) {
        if (!NullHelper.isNull(screenVersion) && !NullHelper.isNull(version)) {
            screenVersion.setText(version);
        }
    }

    @Override
    public void loadingBar(boolean isLoading) {
        if (!NullHelper.isNull(screenVersion) && !NullHelper.isNull(loading))
            if (isLoading) {
                loading.setVisibility(View.VISIBLE);
                screenVersion.setVisibility(View.GONE);
            } else {
                loading.setVisibility(View.GONE);
                screenVersion.setVisibility(View.VISIBLE);
            }
    }

    @Override
    public void startPage() {
        splashScreenPresenter.loadPage(this);
    }

    @Override
    public File getCacheDir() {
        return getBaseContext().getCacheDir();
    }

    @Override
    public void showError(@StringRes int error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}
