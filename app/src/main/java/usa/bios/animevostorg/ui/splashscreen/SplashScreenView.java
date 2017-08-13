package usa.bios.animevostorg.ui.splashscreen;

import android.support.annotation.StringRes;

import java.io.File;

import usa.bios.animevostorg.BaseView;

/**
 * Created by Bios on 8/2/2017.
 */

public interface SplashScreenView extends BaseView {
    void setVersion(String version);
    void setLoadingBar(boolean isLoading);
    void loadContentPage();
    void showError(@StringRes int error);
    File getCacheDir();
}
