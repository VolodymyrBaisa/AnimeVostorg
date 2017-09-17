package usa.bios.animevostorg.ui.splashscreen.presenter.impl;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.Disposable;
import usa.bios.animevostorg.ui.base.BaseView;
import usa.bios.animevostorg.ui.splashscreen.SplashScreenView;
import usa.bios.animevostorg.ui.splashscreen.presenter.SplashScreenInteractor;
import usa.bios.animevostorg.ui.splashscreen.presenter.SplashScreenPresenter;
import usa.bios.animevostorg.utils.NullUtils;

/**
 * Created by Bios on 8/2/2017.
 */

public class SplashScreenPresenterImpl implements SplashScreenPresenter {


    private WeakReference<SplashScreenView> splashScreenViewWeakReference;
    private SplashScreenInteractor splashScreenInteractor;
    private Disposable checkVersionApp;
    private Disposable launchActivity;

    @Override
    public void subscribe(BaseView baseView) {
        splashScreenViewWeakReference = new WeakReference<>((SplashScreenView) baseView);
        splashScreenInteractor = new SplashScreenInteractorImpl();
    }

    @Override
    public void unSubscribe() {
        if (NullUtils.isNotNull(splashScreenViewWeakReference))
            splashScreenViewWeakReference.clear();

        if (NullUtils.isNotNull(checkVersionApp) && !checkVersionApp.isDisposed())
            checkVersionApp.dispose();
        if (NullUtils.isNotNull(launchActivity) && !launchActivity.isDisposed())
            launchActivity.dispose();
    }

    @Override
    public void launchContentActivity() {
        SplashScreenView splashScreenView = splashScreenViewWeakReference.get();
        splashScreenView.appVersion();
        checkVersionApp = splashScreenInteractor.checkVersionApp(splashScreenView);
        launchActivity = splashScreenInteractor.launchActivity(splashScreenView);
    }
}
