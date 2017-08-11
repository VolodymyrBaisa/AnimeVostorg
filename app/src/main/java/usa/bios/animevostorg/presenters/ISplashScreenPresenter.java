package usa.bios.animevostorg.presenters;

import android.content.Context;

/**
 * Created by Bios on 8/6/2017.
 */

public interface ISplashScreenPresenter extends IBasePresenter {
    void loadVersion();
    void loadPage(Context context);
}
