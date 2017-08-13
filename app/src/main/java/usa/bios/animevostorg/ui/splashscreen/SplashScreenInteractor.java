package usa.bios.animevostorg.ui.splashscreen;

import io.reactivex.disposables.Disposable;

/**
 * Created by Bios on 8/13/2017.
 */

public interface SplashScreenInteractor {
    Disposable loadVersion(SplashScreenView splashScreenView);

    Disposable loadPage(SplashScreenView splashScreenView);
}
