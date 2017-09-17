package usa.bios.animevostorg.ui.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import usa.bios.animevostorg.BuildConfig;
import usa.bios.animevostorg.R;
import usa.bios.animevostorg.ui.base.BaseActivity;
import usa.bios.animevostorg.ui.contentscreen.ContentActivity;
import usa.bios.animevostorg.ui.splashscreen.presenter.SplashScreenPresenter;
import usa.bios.animevostorg.ui.splashscreen.presenter.impl.SplashScreenPresenterImpl;
import usa.bios.animevostorg.utils.NullUtils;
import usa.bios.animevostorg.utils.TypefaceUtils;

/**
 * Created by Bios on 8/2/2017.
 */

public class SplashActivity extends BaseActivity implements SplashScreenView {
    private static final String FONT = "fonts/roomfer.ttf";

    private SplashScreenPresenter splashScreenPresenter;
    private TextView splashScreenPart1;
    private TextView splashScreenPart2;
    private TextView splashScreenPart3;
    private TextView screenVersion;

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
    public void appVersion() {
        if (NullUtils.isNotNull(screenVersion)) {
            screenVersion.setText(BuildConfig.VERSION_NAME);
        }
    }

    @Override
    public void launchActivity() {
        Intent toHomePage = new Intent(this, ContentActivity.class);
        startActivity(toHomePage);
        finish();
    }
}
