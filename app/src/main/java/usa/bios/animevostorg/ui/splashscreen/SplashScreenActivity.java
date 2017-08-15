package usa.bios.animevostorg.ui.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import usa.bios.animevostorg.R;
import usa.bios.animevostorg.ui.contentscreen.ContentScreenActivity;
import usa.bios.animevostorg.ui.splashscreen.impl.SplashScreenPresenterImpl;
import usa.bios.animevostorg.utils.NullUtils;
import usa.bios.animevostorg.utils.TypefaceUtils;

/**
 * Created by Bios on 8/2/2017.
 */

public class SplashScreenActivity extends AppCompatActivity implements SplashScreenView {
    private static final String FONT = "fonts/roomfer.ttf";

    private SplashScreenPresenter splashScreenPresenter;
    private TextView splashScreenPart1;
    private TextView splashScreenPart2;
    private TextView splashScreenPart3;
    private TextView screenVersion;
    private ProgressBar loading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        init();
    }

    private void init() {
        splashScreenPresenter = new SplashScreenPresenterImpl();

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
        if (NullUtils.isNotNull(splashScreenPart1) && NullUtils.isNotNull(splashScreenPart2)
                && NullUtils.isNotNull(splashScreenPart3) && NullUtils.isNotNull(screenVersion)) {

            splashScreenPart1.setTypeface(TypefaceUtils.font(this, FONT));
            splashScreenPart2.setTypeface(TypefaceUtils.font(this, FONT));
            splashScreenPart3.setTypeface(TypefaceUtils.font(this, FONT));
            screenVersion.setTypeface(TypefaceUtils.font(this, FONT));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        splashScreenPresenter.subscribe(this);
        splashScreenPresenter.launchContentActivity();
    }

    @Override
    protected void onStop() {
        super.onStop();
        splashScreenPresenter.unSubscribe();
    }

    @Override
    public void setVersion(String version) {
        if (NullUtils.isNotNull(screenVersion) && NullUtils.isNotNull(version)) {
            screenVersion.setText(version);
        }
    }

    @Override
    public void setLoadingBar(boolean isLoading) {
        if (NullUtils.isNotNull(screenVersion) && NullUtils.isNotNull(loading))
            if (isLoading) {
                loading.setVisibility(View.VISIBLE);
                screenVersion.setVisibility(View.GONE);
            } else {
                loading.setVisibility(View.GONE);
                screenVersion.setVisibility(View.VISIBLE);
            }
    }

    @Override
    public void loadContentPage() {
        Intent toHomePage = new Intent(this, ContentScreenActivity.class);
        startActivity(toHomePage);
        finish();
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
