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
import usa.bios.animevostorg.ui.custom.title.TitleView;
import usa.bios.animevostorg.ui.splashscreen.presenter.SplashScreenPresenter;
import usa.bios.animevostorg.ui.splashscreen.presenter.impl.SplashScreenPresenterImpl;
import usa.bios.animevostorg.utils.NullUtils;
import usa.bios.animevostorg.utils.TypefaceUtils;

/**
 * Created by Bios on 8/2/2017.
 */

public class SplashActivity extends BaseActivity implements SplashScreenView {

    private SplashScreenPresenter splashScreenPresenter;
    private TitleView splashScreenTitleView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        init();
    }

    private void init() {
        splashScreenPresenter = new SplashScreenPresenterImpl();

        splashScreenTitleView = (TitleView) findViewById(R.id.splashTitleView);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
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
        if (NullUtils.isNotNull(splashScreenTitleView)) {
            splashScreenTitleView.setTextVersion(BuildConfig.VERSION_NAME);
        }
    }

    @Override
    public void launchActivity() {
        Intent toHomePage = new Intent(this, ContentActivity.class);
        startActivity(toHomePage);
        finish();
    }
}
