package usa.bios.animevostorg.presenters;

import android.support.annotation.StringRes;

import java.io.File;

/**
 * Created by Bios on 8/2/2017.
 */

public interface ISplashScreenView extends IBaseView {
    void loadVersion(String version);
    void loadingBar(boolean isLoading);
    void startPage();
    void showError(@StringRes int error);
    File getCacheDir();
}
