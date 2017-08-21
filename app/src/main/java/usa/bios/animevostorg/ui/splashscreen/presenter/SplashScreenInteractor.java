package usa.bios.animevostorg.ui.splashscreen.presenter;

import io.reactivex.disposables.Disposable;
import usa.bios.animevostorg.ui.splashscreen.SplashScreenView;

/**
 * Created by Bios on 8/13/2017.
 */

public interface SplashScreenInteractor {
    Disposable loadVersion(SplashScreenView splashScreenView);

    Disposable loadPage(SplashScreenView splashScreenView);
}
