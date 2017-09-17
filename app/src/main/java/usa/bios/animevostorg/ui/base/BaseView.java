package usa.bios.animevostorg.ui.base;

import android.support.annotation.StringRes;

import java.io.File;

/**
 * Created by Bios on 9/16/2017.
 */

public interface BaseView {
    void onError(@StringRes int resId);

    void showMessage(@StringRes int resId);

    boolean isNetworkConnected();

    File getCacheDir();
}
