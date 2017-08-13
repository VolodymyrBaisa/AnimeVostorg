package usa.bios.animevostorg.ui.splashscreen.impl;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.Disposable;
import usa.bios.animevostorg.BaseView;
import usa.bios.animevostorg.ui.splashscreen.LoadPageListener;
import usa.bios.animevostorg.ui.splashscreen.SplashScreenInteractor;
import usa.bios.animevostorg.ui.splashscreen.SplashScreenPresenter;
import usa.bios.animevostorg.ui.splashscreen.SplashScreenView;
import usa.bios.animevostorg.utils.NullUtils;

/**
 * Created by Bios on 8/2/2017.
 */

public class SplashScreenPresenterImpl implements SplashScreenPresenter, LoadPageListener {


    private WeakReference<SplashScreenView> splashScreenViewWeakReference;
    private SplashScreenInteractor splashScreenInteractor;
    private Disposable loadVersionDisposable;
    private Disposable loadPageDisposable;

    @Override
    public void subscribe(BaseView baseView) {
        splashScreenViewWeakReference = new WeakReference<>((SplashScreenView) baseView);
        splashScreenInteractor = new SplashScreenInteractorImpl(this);
    }

    @Override
    public void unSubscribe() {
        if (NullUtils.isNotNull(splashScreenViewWeakReference)) splashScreenViewWeakReference.clear();
        if(NullUtils.isNotNull(splashScreenInteractor)) splashScreenInteractor = null;
        if (NullUtils.isNotNull(loadVersionDisposable) && !loadVersionDisposable.isDisposed()) loadVersionDisposable.dispose();
        if (NullUtils.isNotNull(loadPageDisposable) && !loadPageDisposable.isDisposed()) loadPageDisposable.dispose();
    }

    @Override
    public void launchContentActivity() {
        loadVersionDisposable = splashScreenInteractor.loadVersion(splashScreenViewWeakReference.get());

    }

    @Override
    public void load() {
        loadPageDisposable = splashScreenInteractor.loadPage(splashScreenViewWeakReference.get());
    }
}
